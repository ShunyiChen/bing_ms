package com.ruoyi.filingsystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.datascope.annotation.DataScope;
import com.ruoyi.filingsystem.domain.FilingFileProfile;
import com.ruoyi.filingsystem.domain.FilingEmailTemplate;
import com.ruoyi.filingsystem.domain.FilingFileLoanReturn;
import com.ruoyi.filingsystem.dto.*;
import com.ruoyi.filingsystem.mapper.FilingBorrowRecordMapper;
import com.ruoyi.filingsystem.mapper.FilingEmailTemplateMapper;
import com.ruoyi.filingsystem.mapper.FilingFileOperationRecordMapper;
import com.ruoyi.filingsystem.mapper.FilingFileProfileMapper;
import com.ruoyi.filingsystem.service.IFilingBorrowRecordService;
import com.ruoyi.filingsystem.utils.SendEmailUtil;
import com.ruoyi.filingsystem.utils.enums.EnumOfficeEmail;
import com.ruoyi.filingsystem.utils.enums.EnumSendEmailOption;
import com.ruoyi.filingsystem.vo.FilingBorrowRecordVO;
import com.ruoyi.filingsystem.vo.FilingFetchLoanFileVO;
import com.ruoyi.filingsystem.vo.FilingFetchReturnFileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class FilingBorrowRecordServiceImpl implements IFilingBorrowRecordService {
    @Autowired
    private FilingBorrowRecordMapper filingBorrowRecordMapper;
    @Autowired
    private FilingFileProfileMapper filingFileProfileMapper;
    @Autowired
    private FilingFileOperationRecordMapper filingFileOperationRecordMapper;

    @Autowired
    private FilingEmailTemplateMapper filingEmailTemplateMapper;

    @Override
    @DataScope(deptAlias = "fb")
    public List<FilingBorrowRecordVO> getBorrowRecordsByStaffID(GetBorrowRecordDTO dto) {
        return filingBorrowRecordMapper.getBorrowRecordsByStaffID(dto);
    }

    @Override
    @DataScope(deptAlias = "ffp")
    public FilingFetchLoanFileVO fetchBorrowFile(FetchBorrowFileDTO dto) {
        return filingBorrowRecordMapper.fetchBorrowFile(dto);
    }

    @Override
    public int borrowConfirm(BorrowConfirmDTO dto) {
        String[] fileIDs = dto.getFileIDs();
        //发送借阅通知邮件
        List<FilingFileProfile> filingFileProfiles = filingFileProfileMapper.selectFileByFileIDs(fileIDs);
        String areaLocation = filingFileProfiles.get(0).getAreaLocation();
        String OfficeEmail = EnumOfficeEmail.valueOf(areaLocation).getEmail();
        String emailList=dto.getStaffEmail()+";";
        String systemDate = filingBorrowRecordMapper.getSystemDate();
        EmailParams params = new EmailParams(OfficeEmail,EnumSendEmailOption.LoanFile,dto.getStaffID(),dto.getStaffName(),emailList,systemDate,filingFileProfiles);
        CompletableFuture.runAsync(() -> {
            // 发送借阅的邮件
            SendEmail(params.enumSendEmailOption,params.officeEmail, params.staffID, params.staffName, params.emailList,params.borrowDateTime,params.filingFileProfiles);
        });
        // 批量更新借阅人
        filingBorrowRecordMapper.updateBorrower(dto);
        // 确认并插入借阅记录
        List<InsertBorrowConfirmDTO> borrowFileList = new ArrayList<>();
        for(int i = 0; i < dto.getFileIDs().length; i++) {
            InsertBorrowConfirmDTO insertBorrowConfirmDTO = new InsertBorrowConfirmDTO();
            insertBorrowConfirmDTO.setFileID(dto.getFileIDs()[i]);
            insertBorrowConfirmDTO.setDeptId(dto.getDeptIds()[i]);
            insertBorrowConfirmDTO.setStatus(dto.getStatus());
            insertBorrowConfirmDTO.setStaffID(dto.getStaffID());
            insertBorrowConfirmDTO.setStaffName(dto.getStaffName());
            insertBorrowConfirmDTO.setStaffEmail(dto.getStaffEmail());
            borrowFileList.add(insertBorrowConfirmDTO);
        }
        return filingBorrowRecordMapper.borrowConfirm(borrowFileList);
    }

    @Override
    @DataScope(deptAlias = "ffp")
    public FilingFetchReturnFileVO fetchReturnFile(FetchReturnFileDTO dto) {
        return filingBorrowRecordMapper.fetchReturnFile(dto);
    }

    @Override
    public int returnConfirm(String[] fileIDs) {
        //对归还的文件进行分组   按照借阅人分组
        List<FilingFileLoanReturn> ListLoanReturn = filingBorrowRecordMapper.selectByFileIDs(fileIDs);
        String systemDate = filingBorrowRecordMapper.getSystemDate();
        Map<String, List<FilingFileLoanReturn>> groupedByUser = ListLoanReturn.stream()
                .collect(Collectors.groupingBy(FilingFileLoanReturn::getBorrowerGPN));
        for (Map.Entry<String, List<FilingFileLoanReturn>> entry : groupedByUser.entrySet()) {
            String Returnlpn = entry.getKey();  // 获取键
            List<FilingFileLoanReturn> filingFileLoanReturns = entry.getValue();  // 获取值
            FilingFileLoanReturn filingFileLoanReturn = filingFileLoanReturns.get(0);
            String areaLocation = filingFileLoanReturn.getAreaLocation();
            String staffName = filingFileLoanReturn.getStaffName();
            String borrowerEmail = filingFileLoanReturn.getBorrowerEmail();
            //获取办公室的公邮地址
            String OfficeEmail = EnumOfficeEmail.valueOf(areaLocation).getEmail();
            //邮件发送给那些人
            String emailList=borrowerEmail+";";
            List<FilingFileProfile> filingFileProfiles = BeanUtil.copyToList(filingFileLoanReturns, FilingFileProfile.class);

            EmailParams params = new EmailParams(OfficeEmail,EnumSendEmailOption.ReturnFile,Returnlpn,staffName,emailList,systemDate,filingFileProfiles);
            CompletableFuture.runAsync(() -> {
                // 发送借阅的邮件
                SendEmail(params.enumSendEmailOption,params.officeEmail, params.staffID, params.staffName, params.emailList,params.borrowDateTime,params.filingFileProfiles);
            });
        }
        // 批量恢复借阅人
        BorrowConfirmDTO dto = new BorrowConfirmDTO();
        dto.setFileIDs(fileIDs);
        filingBorrowRecordMapper.recoverBorrower(dto);
        // 确认并恢复借阅记录
        return filingBorrowRecordMapper.returnConfirm(fileIDs);
    }


    public void SendEmail(EnumSendEmailOption enumSendEmailOption, String OfficeEmail, String lpn, String name, String emailsList, String dateTime, List<FilingFileProfile> filingFileLoanReturnList){
        StringBuilder tableBuilder=new StringBuilder();
        String  title="";
        String bodyStart = "";
        String bodyEnd = "";
        FilingEmailTemplate filingEmailTemplate;
        switch (enumSendEmailOption) {
            case LoanFile:
                title="Notify of File Borrowing";
                Long id1=1L;
                filingEmailTemplate = filingEmailTemplateMapper.selectFilingEmailTemplateByTemplateID(id1);

                bodyStart = filingEmailTemplate.getBodyStart().replace("[username]", name).replace("[lpn]", lpn).replace("[date1]", dateTime).replace("[filetotal]", Integer.toString(filingFileLoanReturnList.size())).replace("[note]","<h1>"+"Note that this is a system-generated report, please do not respond to this email"+"<br>"+"For assistance,<br>"+"please contact:  "+OfficeEmail+"</h1>");
                bodyEnd = filingEmailTemplate.getBodyEnd().replace("[OfficeEmail]", OfficeEmail).replace("[date2]", dateTime);

                break;
            case ReturnFile:
                title="Notify of File Returning";
                Long id2=2L;
                filingEmailTemplate = filingEmailTemplateMapper.selectFilingEmailTemplateByTemplateID(id2);
                bodyStart = filingEmailTemplate.getBodyStart().replace("[username]", name).replace("[lpn]", lpn).replace("[date1]", dateTime).replace("[filetotal]", Integer.toString(filingFileLoanReturnList.size())).replace("[note]","<h1>"+"Note that this is a system-generated report, please do not respond to this email"+"<br>"+"For assistance,<br>"+"please contact:  "+OfficeEmail+"</h1>");
                bodyEnd = filingEmailTemplate.getBodyEnd().replace("[OfficeEmail]", OfficeEmail).replace("[date2]", dateTime);
                break;
        }
        tableBuilder.append("<html><body>");
        tableBuilder.append(bodyStart);
        tableBuilder.append("<table border=\"1\"><tr><th>FileID</th><th>Group</th><th>Client Name</th><th>Client Code</th><th>Year End</th><th>Type</th><th>Description</th></tr>");
        for (FilingFileProfile file : filingFileLoanReturnList) {
            tableBuilder.append("<tr>")
                    .append("<td>").append(file.getFileID()).append("</td>")
                    .append("<td>").append(file.getClientGroupName()).append("</td>")
                    .append("<td>").append(file.getClientCompanyName()).append("</td>")
                    .append("<td>").append(file.getClientNumber()).append("</td>")
                    .append("<td>").append(Objects.isNull(file.getYearEndDate())?' ':file.getYearEndDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).append("</td>")
                    .append("<td>").append(file.getFileType()).append("</td>")
                    .append("<td>").append(file.getFileDescription()).append("</td>")
                    .append("</tr>");
        }
        tableBuilder.append("</table>");
        tableBuilder.append(bodyEnd);
        tableBuilder.append("</body></html>");
        String HtmlBody = tableBuilder.toString();
        // TODO 后面去掉这个收件人   这是测试用的收件人邮箱
//        emailsList+="1024495167@qq.com;";
        String[] emailList1 = emailsList.split(";");
        SendEmailUtil.sendEmail(emailList1,title,HtmlBody,OfficeEmail);

    }
    class EmailParams {
        final String officeEmail;
        final String staffID;
        final String staffName;
        final String emailList;
        final String borrowDateTime;
        final List<FilingFileProfile> filingFileProfiles;
        final EnumSendEmailOption enumSendEmailOption;
        EmailParams(String officeEmail,EnumSendEmailOption enumSendEmailOption,String staffID, String staffName, String emailList, String borrowDateTime, List<FilingFileProfile> filingFileProfiles) {
            this.officeEmail = officeEmail;
            this.staffID = staffID;
            this.staffName = staffName;
            this.emailList = emailList;
            this.borrowDateTime = borrowDateTime;
            this.filingFileProfiles = filingFileProfiles;
            this.enumSendEmailOption=enumSendEmailOption;
        }
    }
}
