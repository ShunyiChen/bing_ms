package com.ruoyi.filingsystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.datascope.annotation.DataScope;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.filingsystem.domain.FilingFileProfile;
import com.ruoyi.filingsystem.domain.FilingEmailTemplate;
import com.ruoyi.filingsystem.domain.FilingFileOperationRecord;
import com.ruoyi.filingsystem.dto.*;
import com.ruoyi.filingsystem.mapper.FilingFileProfileMapper;
import com.ruoyi.filingsystem.mapper.FilingEmailTemplateMapper;
import com.ruoyi.filingsystem.mapper.FilingFileOperationRecordMapper;
import com.ruoyi.filingsystem.service.IFilingFileProfileService;
import com.ruoyi.filingsystem.utils.SendEmailUtil;
import com.ruoyi.filingsystem.utils.enums.*;
import com.ruoyi.filingsystem.vo.FilingBorrowUnnoticedVO;
import com.ruoyi.filingsystem.vo.FilingExportBarcodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 文档_管理人员操作Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-25
 */
@Service
public class FilingFileProfileServiceImpl implements IFilingFileProfileService {
    @Autowired
    private FilingFileProfileMapper filingFileProfileMapper;
    @Autowired
    private FilingFileOperationRecordMapper filingFileOperationRecordMapper;
    @Autowired
    private FilingEmailTemplateMapper filingEmailTemplateMapper;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public int checkBoxNumberExists(BoxNumberExistDTO dto) {
        return filingFileProfileMapper.checkBoxNumberExists(dto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public FilingFileProfile moveToLocation(MoveToLocationDTO dto) {
        // moveTo变更
        UpdateMoveToDTO updateMoveToDTO = new UpdateMoveToDTO();
        updateMoveToDTO.setFileIDs(new String[]{dto.getFileID()});
        updateMoveToDTO.setMoveTo(dto.getMoveTo());
        updateMoveToDTO.setIsMoved(dto.getIsMoved());
        filingFileProfileMapper.updateMoveTo(updateMoveToDTO);

        // 添加操作记录
        FilingFileOperationRecord fileOperationRecord = new FilingFileOperationRecord();
        fileOperationRecord.setFileIDs(Stream.of(dto.getFileID()).collect(Collectors.toList()));
        fileOperationRecord.setEditor(SecurityUtils.getUsername());
        fileOperationRecord.setAction(EnumFileOperationState.EDIT.getState());
        filingFileOperationRecordMapper.insertFilingFileOperationRecord(fileOperationRecord);
        return filingFileProfileMapper.selectFilingFileProfileByFileID(dto.getFileID());
    }

    /**
     * 修改文档_管理人员操作
     *
     * @param updateFileDTO 文档_管理人员操作
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateFilingFileProfile(UpdateFileDTO updateFileDTO) {
        if(updateFileDTO.getNewFileState().equals(EnumFileState.LOSE.getState())) {
            updateFileDTO.setBoxNumber(null);
            updateFileDTO.setFileState(updateFileDTO.getNewFileState());
        } else {
            if(StringUtils.isEmpty(updateFileDTO.getBoxNumber())) {
                updateFileDTO.setFileState(EnumFileState.OFFICE.getState());
            } else {
                updateFileDTO.setFileState(EnumFileState.WAREHOUSE.getState());
            }
        }
        // 更新
        filingFileProfileMapper.updateFilingFileProfile(updateFileDTO);

        // 添加操作记录
        FilingFileOperationRecord fileOperationRecord = new FilingFileOperationRecord();
        fileOperationRecord.setFileIDs(Stream.of(updateFileDTO.getOldSystemfileID()).collect(Collectors.toList()));
        fileOperationRecord.setEditor(SecurityUtils.getUsername());
        fileOperationRecord.setAction(EnumFileOperationState.EDIT.getState());
        filingFileOperationRecordMapper.insertFilingFileOperationRecord(fileOperationRecord);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<FilingFileProfile> changeType(ChangeTypeDTO dto) {
        List<FilingFileProfile> list = filingFileProfileMapper.findByFileIds(dto.getFileIDs());
        for(FilingFileProfile profile : list) {
            profile.setFileType(dto.getNewFileType());
        }
        // 批量更改fileType,isDPN和RetentionDate
        int rows = filingFileProfileMapper.batchUpdateFileTypes(list);
        // 添加操作记录
        FilingFileOperationRecord fileOperationRecord = new FilingFileOperationRecord();
        fileOperationRecord.setFileIDs(list.stream().map(FilingFileProfile::getOldSystemfileID).collect(Collectors.toList()));
        fileOperationRecord.setEditor(SecurityUtils.getUsername());
        fileOperationRecord.setAction(EnumFileOperationState.EDIT.getState());
        filingFileOperationRecordMapper.insertFilingFileOperationRecord(fileOperationRecord);
        return filingFileProfileMapper.findByFileIds(dto.getFileIDs());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<FilingFileProfile> changeDPN(ChangeDPNDTO dto) {
        List<FilingFileProfile> list = filingFileProfileMapper.findByFileIds(dto.getFileIDs());
        for(FilingFileProfile profile : list) {
            profile.setIsDPN(dto.getIsDPN());
        }
        // 批量更改isDPN和retentionDate
        filingFileProfileMapper.batchUpdateDPN(list);
        // 添加操作记录
        FilingFileOperationRecord fileOperationRecord = new FilingFileOperationRecord();
        fileOperationRecord.setFileIDs(list.stream().map(FilingFileProfile::getOldSystemfileID).collect(Collectors.toList()));
        fileOperationRecord.setEditor(SecurityUtils.getUsername());
        fileOperationRecord.setAction(EnumFileOperationState.EDIT.getState());
        filingFileOperationRecordMapper.insertFilingFileOperationRecord(fileOperationRecord);
        return filingFileProfileMapper.findByFileIds(dto.getFileIDs());
    }

    @Override
    public List<FilingExportBarcodeVO> exportBarcodeList(SearchFileDTO searchFileDto) {
        if(searchFileDto.getMultipleSelection() != null) {
            filingFileProfileMapper.createTempTable();
            filingFileProfileMapper.insertTempTable(searchFileDto.getMultipleSelection());
        }
        populateBoxNumber(searchFileDto);
        List<FilingFileProfile> list = filingFileProfileMapper.searchProfileListByDto(searchFileDto);
        if(searchFileDto.getMultipleSelection() != null) {
            filingFileProfileMapper.dropTempTable();
        }
        List<FilingExportBarcodeVO> results = new ArrayList<>();
        for(FilingFileProfile profile : list) {
            FilingExportBarcodeVO vo = new FilingExportBarcodeVO();
            vo.setOldSystemfileID(profile.getOldSystemfileID());
            vo.setClientNumber(profile.getClientNumber());
            vo.setClientGroupName(profile.getClientGroupName());
            vo.setClientCompanyName(profile.getClientCompanyName());
            if(profile.getYearEndDate() != null) {
                int d = profile.getYearEndDate().getDayOfMonth();
                String m = profile.getYearEndDate().getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
                int y = profile.getYearEndDate().getYear();
                vo.setD(d+"");
                vo.setM(m.toUpperCase());
                vo.setY(y+"");
            } else {
                vo.setD("");
                vo.setM("");
                vo.setY("");
            }
            String clientCompanyName = profile.getClientCompanyName();

            String out=StringUtils.substring(clientCompanyName, 0, 76);

            int length = out.length();

            if (length>50){
                vo.setCn1(StringUtils.substring(clientCompanyName, 0, 26));
                vo.setCn2(StringUtils.substring(clientCompanyName, 26, 51));
                vo.setCn3(StringUtils.substring(clientCompanyName, 51, 76));
            } else if (length >25){
                vo.setCn1(StringUtils.substring(clientCompanyName, 0, 26));
                vo.setCn2(StringUtils.substring(clientCompanyName, 26, 51));
            }else {
                vo.setCn1(StringUtils.substring(clientCompanyName, 0, 26));
            }
            vo.setDe1(profile.getFileDescription());
            results.add(vo);
        }
        return results;
    }

    /**
     * 查询文档_管理人员操作
     * 
     * @param fileID 文档_管理人员操作主键
     * @return 文档_管理人员操作
     */
    @Override
    public FilingFileProfile selectFilingFileProfileByFileID(String fileID)
    {
        return filingFileProfileMapper.selectFilingFileProfileByFileID(fileID);
    }

    /**
     * 查询文档_管理人员操作列表
     * 
     * @param searchFileDto 文档_管理人员操作
     * @return 文档_管理人员操作
     */
    @Override
    @DataScope(deptAlias = "filling_file_profile")
    public List<FilingFileProfile> searchProfileListByDto(SearchFileDTO searchFileDto) {
        populateBoxNumber(searchFileDto);
        return filingFileProfileMapper.searchProfileListByDto(searchFileDto);
    }

    @Override
    public List<FilingFileProfile> exportData(SearchFileDTO searchFileDto) {
        if(searchFileDto.getMultipleSelection() != null) {
            filingFileProfileMapper.createTempTable();
            filingFileProfileMapper.insertTempTable(searchFileDto.getMultipleSelection());
        }
        List<FilingFileProfile> result = searchProfileListByDto(searchFileDto);
        if(searchFileDto.getMultipleSelection() != null) {
            filingFileProfileMapper.dropTempTable();
        }
        return result;
    }

    /**
     * 给箱子号赋值
     * 
     * @param searchFileDto
     */
    private void populateBoxNumber(SearchFileDTO searchFileDto) {
        if(!StringUtils.isEmpty(searchFileDto.getBoxPrefix()) || !StringUtils.isEmpty(searchFileDto.getBoxSuffix())) {
            if(!StringUtils.isEmpty(searchFileDto.getBoxSuffix())) {
                String output = searchFileDto.getBoxSuffix().replaceAll("^0+", "");
                searchFileDto.setBoxSuffix(output);
            }
            searchFileDto.setBoxNumber(searchFileDto.getBoxPrefix() + searchFileDto.getBoxSuffix());
        }
    }

    private long getGenerateFileID(FilingFileProfile fileProfile, String areaLocation) {
        long startID = Long.parseLong(EnumStartFileID.getStartID(areaLocation));

        if (fileProfile == null) {
            return startID + 1L;
        }

        long currentID = fileProfile.getFileID();
        return Math.max(startID, currentID) + 1L;
    }

    /**
     * 新增文档_管理人员操作
     * 
     * @param addFileDto 文档_管理人员操作
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Object insertFilingFileProfile(AddFileDto addFileDto){
        FilingFileProfile fileProfile = filingFileProfileMapper.selectMaxFileIdByArealocation(addFileDto.getAreaLocation());
        String newFileID = getGenerateFileID(fileProfile, addFileDto.getAreaLocation()) + "";
        FilingFileProfile filingFileProfile = BeanUtil.copyProperties(addFileDto, FilingFileProfile.class);
        if(!StringUtils.isEmpty(addFileDto.getBoxNumber())) {
            filingFileProfile.setFileState(EnumFileState.WAREHOUSE.getState());
        } else {
            filingFileProfile.setFileState(EnumFileState.OFFICE.getState());
        }
        filingFileProfile.setBorrowState(EnumBorrowState.YES.getState());
        filingFileProfile.setOldSystemfileID(newFileID);
        filingFileProfile.setOldSystemFileMark(1L);// 1是新数据

        // 插入file
        filingFileProfileMapper.insertFilingFileProfile(filingFileProfile);
        AddFileReturnDto addFileReturnDto = new AddFileReturnDto();
        addFileReturnDto.setFileType(filingFileProfile.getFileType());
        addFileReturnDto.setFileID(newFileID);

        // 添加操作记录
        FilingFileOperationRecord fileOperationRecord = new FilingFileOperationRecord();
        fileOperationRecord.setFileIDs(Stream.of(newFileID).collect(Collectors.toList()));
        fileOperationRecord.setEditor(SecurityUtils.getUsername());
        fileOperationRecord.setAction(EnumFileOperationState.ORIGINAL.getState());
        filingFileOperationRecordMapper.insertFilingFileOperationRecord(fileOperationRecord);

        return addFileReturnDto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void reminderOver6MonthsOutstandingFiles() {
        List<FilingBorrowUnnoticedVO> list = filingFileProfileMapper.findOver6MonthsOutstandingUnnoticed();
        // 分组 依据staffID分组
        LocalDateTime returnDateTime = LocalDateTime.now();
        Map<String, List<FilingBorrowUnnoticedVO>> groupedByUserStaffId = list.stream()
                .collect(Collectors.groupingBy(FilingBorrowUnnoticedVO::getStaffId));
        for (Map.Entry<String, List<FilingBorrowUnnoticedVO>> entry : groupedByUserStaffId.entrySet()) {
            String Returnlpn = entry.getKey();  // 获取键
            List<FilingBorrowUnnoticedVO> FilingBorrowUnnoticedVOS = entry.getValue();  // 获取值
            FilingBorrowUnnoticedVO filingFileLoanReturn = FilingBorrowUnnoticedVOS.get(0);
            String areaLocation = filingFileLoanReturn.getAreaLocation();
            String staffName = filingFileLoanReturn.getStaffName();
            String borrowerEmail = filingFileLoanReturn.getStaffEmail();
            //获取办公室的公邮地址
            String OfficeEmail = EnumOfficeEmail.valueOf(areaLocation).getEmail();
            //邮件发送给那些人
            String emailList=borrowerEmail+";";
            EmailParams params = new EmailParams(OfficeEmail,EnumSendEmailOption.expireTime,Returnlpn,staffName,emailList,returnDateTime,FilingBorrowUnnoticedVOS);
            CompletableFuture.runAsync(() -> {
                // 发送超期通知邮件
                SendEmail(params.enumSendEmailOption,params.officeEmail, params.staffID, params.staffName, params.emailList,params.borrowDateTime,params.FilingBorrowUnnoticedVOList);
            });
        }
        // 批量更新提醒状态
        List<Long> borrowIds = list.stream().map(FilingBorrowUnnoticedVO::getBorrowID).collect(Collectors.toList());
        if(!borrowIds.isEmpty()) {
            filingFileProfileMapper.updateUnnoticedStatus(borrowIds);
        }
    }

    public void SendEmail(EnumSendEmailOption enumSendEmailOption, String OfficeEmail, String lpn, String name, String emailsList, LocalDateTime dateTime, List<FilingBorrowUnnoticedVO> filingFileLoanReturnList){
        StringBuilder tableBuilder=new StringBuilder();
        String  title="";
        String bodyStart = "";
        String bodyEnd = "";
        FilingEmailTemplate filingEmailTemplate;
        switch (enumSendEmailOption) {
            case expireTime:
                title="Late File Return for 6 Months";
                Long id3=3L;
                filingEmailTemplate = filingEmailTemplateMapper.selectFilingEmailTemplateByTemplateID(id3);
                bodyStart = filingEmailTemplate.getBodyStart().replace("[username]", name).replace("[lpn]", lpn).replace("[date1]", dateTime.format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss"))).replace("[filetotal]", Integer.toString(filingFileLoanReturnList.size())).replace("[note]","<h1>"+"Note that this is a system-generated report, please do not respond to this email"+"<br>"+"For assistance,<br>"+"please contact:  "+OfficeEmail+"</h1>");
                bodyEnd = filingEmailTemplate.getBodyEnd().replace("[OfficeEmail]", OfficeEmail).replace("[date2]", dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                break;
        }
        // 生成 HTML 表格
        tableBuilder.append("<html><body>");
        tableBuilder.append(bodyStart);
        tableBuilder.append("<table border=\"1\"><tr><th>FileID</th><th>Group</th><th>Client Name</th><th>Client Code</th><th>Year End</th><th>Type</th><th>Description</th><th>LoanDate</th></tr>");
        for (FilingBorrowUnnoticedVO file : filingFileLoanReturnList) {
            tableBuilder.append("<tr>")
                    .append("<td>").append(file.getOldSystemfileID()).append("</td>")
                    .append("<td>").append(file.getClientGroupName()).append("</td>")
                    .append("<td>").append(file.getClientCompanyName()).append("</td>")
                    .append("<td>").append(file.getClientNumber()).append("</td>")
                    .append("<td>").append(Objects.isNull(file.getYearEndDate())?' ':file.getYearEndDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).append("</td>")
                    .append("<td>").append(file.getFileType()).append("</td>")
                    .append("<td>").append(file.getFileDescription()).append("</td>")
                    .append("<td>").append(file.getLoanDate().format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss"))).append("</td>")
                    .append("</tr>");
        }
        tableBuilder.append("</table>");
        tableBuilder.append(bodyEnd);
        tableBuilder.append("</body></html>");
        String HtmlBody = tableBuilder.toString();
        String[] emailList1 = {"Simeon.Chen@cn.ey.com"};// emailsList.split(";");
        SendEmailUtil.sendEmail(emailList1,title,HtmlBody,OfficeEmail);
    }

    class EmailParams {
        final String officeEmail;
        final String staffID;
        final String staffName;
        final String emailList;
        final LocalDateTime borrowDateTime;
        final List<FilingBorrowUnnoticedVO> FilingBorrowUnnoticedVOList;
        final EnumSendEmailOption enumSendEmailOption;
        EmailParams(String officeEmail, EnumSendEmailOption enumSendEmailOption,String staffID, String staffName, String emailList, LocalDateTime borrowDateTime, List<FilingBorrowUnnoticedVO> filingFileProfiles) {
            this.officeEmail = officeEmail;
            this.staffID = staffID;
            this.staffName = staffName;
            this.emailList = emailList;
            this.borrowDateTime = borrowDateTime;
            this.FilingBorrowUnnoticedVOList = filingFileProfiles;
            this.enumSendEmailOption=enumSendEmailOption;
        }
    }
}
