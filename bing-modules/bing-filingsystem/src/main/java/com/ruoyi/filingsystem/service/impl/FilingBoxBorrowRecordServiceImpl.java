package com.ruoyi.filingsystem.service.impl;

import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.datascope.annotation.DataScope;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.filingsystem.domain.FilingBoxBorrowRecord;
import com.ruoyi.filingsystem.domain.FilingBoxProfile;
import com.ruoyi.filingsystem.dto.*;
import com.ruoyi.filingsystem.mapper.FilingBoxBorrowRecordMapper;
import com.ruoyi.filingsystem.mapper.FilingBoxProfileMapper;
import com.ruoyi.filingsystem.service.IFilingBoxBorrowRecordService;
import com.ruoyi.filingsystem.service.IFilingEmailService;
import com.ruoyi.filingsystem.utils.enums.EnumOfficeEmail;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * 盒子借阅记录-整箱借阅Service业务层处理
 * 
 * @author victor.xiong
 * @date 2024-11-18
 */
@Service
public class FilingBoxBorrowRecordServiceImpl implements IFilingBoxBorrowRecordService
{
    private final FilingBoxBorrowRecordMapper mapper;
    private final FilingBoxProfileMapper boxProfileMapper;
    private final IFilingEmailService filingEmailService;

    @Autowired
    public FilingBoxBorrowRecordServiceImpl(FilingBoxBorrowRecordMapper mapper,
                                            FilingBoxProfileMapper boxProfileMapper,
                                            IFilingEmailService filingEmailService) {
        this.mapper = mapper;
        this.boxProfileMapper = boxProfileMapper;
        this.filingEmailService = filingEmailService;
    }

    @Override
    @DataScope(deptAlias = "T")
    public List<FilingBoxBorrowRecord> retrieveBoxesOverdueByOneMonth(GetBoxesOverdueDTO dto) {
        return mapper.retrieveBoxesOverdueByOneMonth(dto);
    }

    @Override
    public void insertBoxBorrowRecords(AddBoxBorrowRecordsDTO dto) {
        //按DeptId分组，插入附件记录
        Map<Long, List<FilingBoxProfile>> groupedByDeptId = dto.getList().stream()
                .collect(Collectors.groupingBy(FilingBoxProfile::getDeptId));
        groupedByDeptId.forEach((deptId, boxProfileList) -> {
            List<FilingBoxBorrowRecord> lstRecord = new ArrayList<>();
            for(FilingBoxProfile f: boxProfileList) {
                FilingBoxBorrowRecord record = new FilingBoxBorrowRecord();
                record.setBoxId(f.getId());
                record.setDeptId(f.getDeptId());
                record.setBoxNumber(f.getBoxNumber());
                record.setVendorBarcode(f.getVendorBarcode());
                record.setChargeCode(dto.getChargeCode());// 填借阅的charge code
                record.setStaffId(dto.getStaffId());
                record.setStaffName(dto.getStaffName());
                record.setStaffEmail(dto.getStaffEmail());
                record.setStatus(dto.getStatus());
                if(dto.getStatus() == 0) {
                    record.setReturnDate(DateUtils.getNowDate());
                } else {
                    record.setLoanDate(DateUtils.getNowDate());
                }
                record.setNotify(0);
                lstRecord.add(record);
            }
            // 插入借阅记录
            mapper.insertBoxBorrowRecords(deptId, lstRecord);

            //更新盒子表借阅状态
            UpdateBoxBorrowStateDTO updateDTO = new UpdateBoxBorrowStateDTO();
            updateDTO.setDeptId(deptId);
            List<Long> lstId = boxProfileList.stream().map(FilingBoxProfile::getId).collect(Collectors.toList());
            updateDTO.setLstId(lstId);
            updateDTO.setBorrowerStaffId(dto.getStaffId());
            updateDTO.setBorrowerName(dto.getStaffName());
            updateDTO.setBorrowState(dto.getStatus());
            boxProfileMapper.updateBoxBorrowState(updateDTO);

            // 借阅，发邮件通知
            CompletableFuture.runAsync(() -> {
                try {
                    FilingBoxBorrowRecord firstRecord = lstRecord.getFirst();
                    String staffId = firstRecord.getStaffId();
                    String staffName = firstRecord.getStaffName();
                    String staffEmail = firstRecord.getStaffEmail();
                    List<String> lstBoxNumber = lstRecord.stream().map(FilingBoxBorrowRecord::getBoxNumber).collect(Collectors.toList());

                    String subject = "Notify of Box Borrowing";
                    String filingRoom = EnumOfficeEmail.getEmailByDeptId(deptId);
                    filingEmailService.sendReminderEmailForBorrowingUsers(subject,
                            staffId, staffName, staffEmail, filingRoom, lstBoxNumber);
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            });
        });
    }

    @Override
    @DataScope(deptAlias = "T")
    public List<FilingBoxBorrowRecord> searchBoxBorrowRecords(SearchBoxBorrowRecordsDTO dto) {
        return mapper.searchBoxBorrowRecords(dto);
    }

    @Override
    public void changeChargeCode(ChangeChargeCodeDTO dto) {
        Map<Long, List<ChangeChargeCodeDTO.Key>> groupedByDeptId = dto.getKeys().stream()
                .collect(Collectors.groupingBy(ChangeChargeCodeDTO.Key::getDeptId));
        groupedByDeptId.forEach((deptId, values) -> {
            List<Long> lstId = values.stream().map(ChangeChargeCodeDTO.Key::getId).collect(Collectors.toList());
            mapper.changeChargeCode(deptId, lstId,
                    dto.getChargeCode(), dto.getReturnChargeCode(), SecurityUtils.getUsername());
        });
    }

    @Override
    public List<FilingBoxBorrowRecord> exportBoxBorrowRecords(ExportRecordsDTO dto) {
        List<FilingBoxBorrowRecord> result;
        // 导出选中
        if(dto.getMultipleSelection() != null) {
            mapper.dropTempTable();
            mapper.createTempTable();
            //按id从小到大排序，决定导出顺序
            List<ExportRecordsDTO.Key> sortedList = dto.getMultipleSelection().stream()
                    .sorted(Comparator.comparingLong(ExportRecordsDTO.Key::getId))
                    .collect(Collectors.toList());
            mapper.insertTempTable(sortedList);
            SearchBoxBorrowRecordsDTO searchDTO = new SearchBoxBorrowRecordsDTO();
            List<SearchBoxBorrowRecordsDTO.Key> lst = dto.getMultipleSelection().stream()
                    .map(val -> new SearchBoxBorrowRecordsDTO.Key(val.getId(), val.getDeptId()))
                    .collect(Collectors.toList());

            searchDTO.setMultipleSelection(lst);
            searchDTO.setDeptId(dto.getDeptId());
            searchDTO.setParams(dto.getParams());
            result = searchBoxBorrowRecords(searchDTO);
            mapper.dropTempTable();
        }
        // 导出全部
        else {
            SearchBoxBorrowRecordsDTO searchDTO = new SearchBoxBorrowRecordsDTO();
            searchDTO.setDeptId(dto.getDeptId());
            searchDTO.setParams(dto.getParams());
            result = searchBoxBorrowRecords(searchDTO);
        }
        return result;
    }

    @Override
    public void changeBoxBorrowStatus(ChangeBoxBorrowStatusDTO dto) {
        Map<Long, List<ChangeBoxBorrowStatusDTO.Key>> groupedByDeptId = dto.getKeys().stream()
                .collect(Collectors.groupingBy(ChangeBoxBorrowStatusDTO.Key::getDeptId));
        groupedByDeptId.forEach((deptId, values) -> {
            List<Long> lstId = values.stream().map(ChangeBoxBorrowStatusDTO.Key::getId).collect(Collectors.toList());
            mapper.changeBoxBorrowStatus(deptId, lstId, dto.getStatus(),
                    dto.getReturnChargeCode(), SecurityUtils.getUsername());

            //更新盒子表借阅状态
            UpdateBoxBorrowStateDTO updateDTO = new UpdateBoxBorrowStateDTO();
            updateDTO.setDeptId(deptId);
            List<Long> lstBoxId = values.stream().map(ChangeBoxBorrowStatusDTO.Key::getBoxId).collect(Collectors.toList());
            updateDTO.setLstId(lstBoxId);
            updateDTO.setBorrowerStaffId(null);
            updateDTO.setBorrowerName(null);
            updateDTO.setBorrowState(0);
            boxProfileMapper.updateBoxBorrowState(updateDTO);

            if(!values.isEmpty()) {
                // 归还，发邮件通知
                CompletableFuture.runAsync(() -> {
                    try {
                        ChangeBoxBorrowStatusDTO.Key k = values.getFirst();
                        String staffId = k.getStaffId();
                        String staffName = k.getStaffName();
                        String staffEmail = k.getStaffEmail();
                        List<String> lstBoxNumber = values.stream().map(ChangeBoxBorrowStatusDTO.Key::getBoxNo).collect(Collectors.toList());

                        String subject = "Notify of File Returning";
                        String filingRoom = EnumOfficeEmail.getEmailByDeptId(deptId);
                        filingEmailService.sendReminderEmailForReturningUsers(subject,
                                staffId, staffName, staffEmail, filingRoom, lstBoxNumber);
                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    }
                });
            }

        });
    }

    @Override
    public void over1MonthsOutstandingBoxes() {
        List<FilingBoxBorrowRecord> list = mapper.retrieveAllOverdueBoxes();
        Map<Long, List<FilingBoxBorrowRecord>> groupedByDeptId = list.stream()
                .collect(Collectors.groupingBy(FilingBoxBorrowRecord::getDeptId));
        groupedByDeptId.forEach((deptId, values) -> {
            List<Long> lstId = values.stream().map(FilingBoxBorrowRecord::getId).collect(Collectors.toList());
            mapper.batchUpdateNotice(deptId, lstId);
        });
        Map<String, List<FilingBoxBorrowRecord>> groupedByStaffId = list.stream()
                .collect(Collectors.groupingBy(FilingBoxBorrowRecord::getStaffId));
        groupedByStaffId.forEach((staffId, values) -> {
            // 逾期，发邮件提醒
            if(!values.isEmpty()) {
                FilingBoxBorrowRecord firstRecord = values.getFirst();
                String staffName = firstRecord.getStaffName();
                String staffEmail = firstRecord.getStaffEmail();
                List<String> lstBoxNumber = values.stream().map(FilingBoxBorrowRecord::getBoxNumber).collect(Collectors.toList());
                CompletableFuture.runAsync(() -> {
                    try {
                        String subject = "Reminder: File Box Loan Overdue by 1 Month";
                        String filingRoom = EnumOfficeEmail.getEmailByDeptId(firstRecord.getDeptId());
                        filingEmailService.sendReminderEmailForOverdueUsers(subject,
                                staffId, staffName, staffEmail, filingRoom, lstBoxNumber);
                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        });
    }

}
