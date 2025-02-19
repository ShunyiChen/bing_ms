package com.ruoyi.filingsystem.service.impl;

import com.ruoyi.common.datascope.annotation.DataScope;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.filingsystem.domain.FilingAttachment;
import com.ruoyi.filingsystem.domain.FilingBoxOperationRecord;
import com.ruoyi.filingsystem.domain.FilingBoxProfile;
import com.ruoyi.filingsystem.dto.*;
import com.ruoyi.filingsystem.mapper.FilingBoxOperationRecordMapper;
import com.ruoyi.filingsystem.mapper.FilingBoxProfileMapper;
import com.ruoyi.filingsystem.service.IFilingBoxProfileService;
import com.ruoyi.filingsystem.utils.enums.EnumBoxOperationState;
import com.ruoyi.filingsystem.utils.enums.EnumBoxState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


/**
 * 盒子_仅整箱操作Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-11-18
 */
@Service
public class FilingBoxProfileServiceImpl implements IFilingBoxProfileService
{
    @Autowired
    private FilingBoxProfileMapper filingBoxProfileMapper;
    @Autowired
    private FilingBoxOperationRecordMapper filingBoxOperationRecordMapper;

    @Override
    public FilingBoxProfile findBoxProfileByID(FindBoxProfileDTO dto) {
        return filingBoxProfileMapper.findBoxProfileByID(dto);
    }

    @Override
    @DataScope(deptAlias = "T")
    public List<FilingBoxProfile> searchBoxProfileList(SearchBoxProfileDTO dto) {
        return filingBoxProfileMapper.searchBoxProfileList(dto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AddBoxReturnDTO insertFilingBoxProfile(AddBoxProfileDTO dto) {
        dto.setCreateBy(SecurityUtils.getUsername());
        dto.setBoxStatus(EnumBoxState.IN.getState());
        filingBoxProfileMapper.insertFilingBoxProfile(dto);

        FilingBoxOperationRecord operationRecord = new FilingBoxOperationRecord();
        operationRecord.setId(dto.getId());
        operationRecord.setDeptId(dto.getDeptId());
        operationRecord.setOperator(SecurityUtils.getUsername());
        operationRecord.setAction(EnumBoxOperationState.ORIGINAL.getState());
        filingBoxOperationRecordMapper.insertFilingBoxOperationRecord(operationRecord);
        return new AddBoxReturnDTO(dto.getBoxNumber());
    }

    /**
     * 修改盒子_仅整箱操作
     * 
     * @param dto 盒子_仅整箱操作
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateFilingBoxProfile(UpdateBoxProfileDTO dto) {
        dto.setUpdateBy(SecurityUtils.getUsername());
        filingBoxProfileMapper.updateFilingBoxProfile(dto);

        FilingBoxOperationRecord operationRecord = new FilingBoxOperationRecord();
        operationRecord.setId(dto.getId());
        operationRecord.setDeptId(dto.getDeptId());
        operationRecord.setOperator(SecurityUtils.getUsername());
        operationRecord.setAction(EnumBoxOperationState.EDIT.getState());
        filingBoxOperationRecordMapper.insertFilingBoxOperationRecord(operationRecord);
    }

    @Override
    public List<FilingBoxProfile> exportSelectedData(ExportRecordsDTO dto) {
        List<FilingBoxProfile> result;
        // 导出选中
        if(dto.getMultipleSelection() != null) {
            String tableName = "filing_temp_box_profile_export";
            filingBoxProfileMapper.dropTempTable(tableName);
            filingBoxProfileMapper.createTempTable(tableName);

            //按id从小到大排序，决定导出顺序
            List<ExportRecordsDTO.Key> sortedList = dto.getMultipleSelection().stream()
                    .sorted(Comparator.comparingLong(ExportRecordsDTO.Key::getId))
                    .collect(Collectors.toList());
            filingBoxProfileMapper.insertTempTable(tableName, sortedList);

            SearchBoxProfileDTO searchDTO = new SearchBoxProfileDTO();
            List<SearchBoxProfileDTO.Key> lst = dto.getMultipleSelection().stream()
                    .map(val -> new SearchBoxProfileDTO.Key(val.getId(), val.getDeptId()))
                    .collect(Collectors.toList());
            searchDTO.setMultipleSelection(lst);
            searchDTO.setDeptId(dto.getDeptId());
            searchDTO.setParams(dto.getParams());
            result = searchBoxProfileList(searchDTO);
            filingBoxProfileMapper.dropTempTable(tableName);
        }
        // 导出全部
        else {
            SearchBoxProfileDTO searchDTO = new SearchBoxProfileDTO();
            searchDTO.setDeptId(dto.getDeptId());
            searchDTO.setParams(dto.getParams());
            result = searchBoxProfileList(searchDTO);
        }
        return result;
    }

    @Override
    public int destroyBoxes(DestroyBoxProfileDTO[] array) {
        AtomicInteger updated = new AtomicInteger();
        // 将数组转换为列表
        List<DestroyBoxProfileDTO> list = Arrays.asList(array);
        Map<Long, List<DestroyBoxProfileDTO>> groupedByDeptId = list.stream()
                .collect(Collectors.groupingBy(DestroyBoxProfileDTO::getDeptId));
        groupedByDeptId.forEach((deptId, values) -> {
            List<Long> lstId = values.stream().map(DestroyBoxProfileDTO::getId).collect(Collectors.toList());
            updated.addAndGet(filingBoxProfileMapper.destroyBox(deptId,
                    lstId, SecurityUtils.getUsername(), values.getFirst().getChargeCode()));

            BatchInsertBoxHistoryDTO d = new BatchInsertBoxHistoryDTO();
            d.setLstId(lstId);
            d.setDeptId(deptId);
            d.setLstId(lstId);
            d.setOperator(SecurityUtils.getUsername());
            d.setAction(EnumBoxOperationState.DESTROY.getState());
            filingBoxOperationRecordMapper.batchInsertHistoryRecord(d);
        });
        return updated.get();
    }

    @Override
    public int permOutBoxes(PermOutBoxProfileDTO[] array) {
        AtomicInteger updated = new AtomicInteger();
        // 将数组转换为列表
        List<PermOutBoxProfileDTO> list = Arrays.asList(array);
        Map<Long, List<PermOutBoxProfileDTO>> groupedByDeptId = list.stream()
                .collect(Collectors.groupingBy(PermOutBoxProfileDTO::getDeptId));
        groupedByDeptId.forEach((deptId, values) -> {
            List<Long> lstId = values.stream().map(PermOutBoxProfileDTO::getId).collect(Collectors.toList());
            updated.addAndGet(filingBoxProfileMapper.permOutBox(deptId,
                    lstId, SecurityUtils.getUsername(), values.getFirst().getChargeCode()));

            BatchInsertBoxHistoryDTO d = new BatchInsertBoxHistoryDTO();
            d.setLstId(lstId);
            d.setDeptId(deptId);
            d.setLstId(lstId);
            d.setOperator(SecurityUtils.getUsername());
            d.setAction(EnumBoxOperationState.PERM_OUT.getState());
            filingBoxOperationRecordMapper.batchInsertHistoryRecord(d);
        });
        return updated.get();
    }

    @Override
    public int editCharge(EditChargeDTO[] array) {
        AtomicInteger updated = new AtomicInteger();
        // 将数组转换为列表
        List<EditChargeDTO> list = Arrays.asList(array);
        Map<Long, List<EditChargeDTO>> groupedByDeptId = list.stream()
                .collect(Collectors.groupingBy(EditChargeDTO::getDeptId));
        groupedByDeptId.forEach((deptId, values) -> {
            List<Long> lstId = values.stream().map(EditChargeDTO::getId).collect(Collectors.toList());
            updated.addAndGet(filingBoxProfileMapper.editCharge(deptId,
                    lstId, SecurityUtils.getUsername(), values.getFirst().getChargeCode()));

            BatchInsertBoxHistoryDTO d = new BatchInsertBoxHistoryDTO();
            d.setLstId(lstId);
            d.setDeptId(deptId);
            d.setLstId(lstId);
            d.setOperator(SecurityUtils.getUsername());
            d.setAction(EnumBoxOperationState.EDIT_CHARGE.getState());
            filingBoxOperationRecordMapper.batchInsertHistoryRecord(d);
        });
        return updated.get();
    }

    @Override
    public void saveAttachmentFiles(SaveAttachmentsDTO dto) {
        List<FilingAttachment> list = new ArrayList<>();
        dto.getKeys().forEach(k -> {
            for(FilingAttachment entity: dto.getAttachments()) {
                FilingAttachment file = new FilingAttachment();
                file.setFileName(entity.getFileName());
                file.setFileType(entity.getFileType());
                file.setFileSize(entity.getFileSize());
                file.setFilePath(entity.getFilePath());
                file.setDeptId(k.getDeptId());
                file.setBoxProfileId(k.getId());
                file.setStatus("active");
                file.setUploader(SecurityUtils.getUsername());
                file.setUsage(entity.getUsage());
                list.add(file);
            }
        });
        //按DeptId分组，插入附件记录
        Map<Long, List<FilingAttachment>> groupedByDepartment = list.stream()
                .collect(Collectors.groupingBy(FilingAttachment::getDeptId));
        groupedByDepartment.forEach((deptId, attachments) -> {
            filingBoxProfileMapper.insertApprovalFiles(attachments, deptId);
        });

        // 按DeptID分组,插入历史记录
        List<SaveAttachmentsDTO.Key> keys = dto.getKeys();
        Map<Long, List<SaveAttachmentsDTO.Key>> map = keys.stream()
                .collect(Collectors.groupingBy(SaveAttachmentsDTO.Key::getDeptId));
        map.forEach((deptId, values) -> {
            BatchInsertBoxHistoryDTO d = new BatchInsertBoxHistoryDTO();
            d.setDeptId(deptId);
            List<Long> lstId = values.stream().map(SaveAttachmentsDTO.Key::getId).collect(Collectors.toList());
            d.setLstId(lstId);
            d.setOperator(SecurityUtils.getUsername());
            d.setAction(EnumBoxOperationState.UPLOAD_ATTACHMENT.getState());
            filingBoxOperationRecordMapper.batchInsertHistoryRecord(d);
        });
    }

    @Override
    @DataScope(deptAlias = "T")
    public List<FilingAttachment> getFilingAttachmentList(GetAttachmentsDTO dto) {
        Map<Long, List<Long>> map = new LinkedHashMap<>();
        for (int i = 0; i < dto.getLstDeptId().size(); i++) {
            Long deptId = dto.getLstDeptId().get(i);
            Long id = dto.getLstId().get(i);

            // 使用 computeIfAbsent 来简化代码
            map.computeIfAbsent(deptId, k -> new ArrayList<>()).add(id);
        }
        List<FilingAttachment> results = new ArrayList<>();
        map.forEach((deptId, list) -> {
            FindAttachmentsDTO fad = new FindAttachmentsDTO();
            fad.setParams(dto.getParams());
            fad.setDeptId(deptId);
            fad.setLstId(list);
            List<FilingAttachment> lst = filingBoxProfileMapper.findFilingAttachmentList(fad);
            results.addAll(lst);
        });
        return results;
    }

    @Override
    public void removeApprovalFiles(RemoveAttachmentDTO dto) {
        filingBoxProfileMapper.deactivateApprovalFile(dto);

        FilingBoxOperationRecord operationRecord = new FilingBoxOperationRecord();
        operationRecord.setId(dto.getBoxId());
        operationRecord.setDeptId(dto.getDeptId());
        operationRecord.setOperator(SecurityUtils.getUsername());
        operationRecord.setAction(EnumBoxOperationState.DELETE_ATTACHMENT.getState());
        filingBoxOperationRecordMapper.insertFilingBoxOperationRecord(operationRecord);
    }

    @Override
    @DataScope(deptAlias = "T")
    public List<FilingBoxOperationRecord> searchBoxEditHistoryList(SearchBoxProfileDTO dto) {
        return filingBoxProfileMapper.searchBoxEditHistoryList(dto);
    }

    @Override
    public List<FilingBoxOperationRecord> exportBoxEditHistoryList(ExportRecordsDTO dto) {
        List<FilingBoxOperationRecord> result;
        // 导出选中
        if(dto.getMultipleSelection() != null) {
            String tableName = "filing_temp_box_edit_history_export";
            filingBoxProfileMapper.dropTempTable(tableName);
            filingBoxProfileMapper.createTempTable(tableName);

            //按id从小到大排序，决定导出顺序
            List<ExportRecordsDTO.Key> sortedList = dto.getMultipleSelection().stream()
                    .sorted(Comparator.comparingLong(ExportRecordsDTO.Key::getId))
                    .collect(Collectors.toList());
            filingBoxProfileMapper.insertTempTable(tableName, sortedList);

            SearchBoxProfileDTO searchDTO = new SearchBoxProfileDTO();
            List<SearchBoxProfileDTO.Key> lst = dto.getMultipleSelection().stream()
                    .map(val -> new SearchBoxProfileDTO.Key(val.getId(), val.getDeptId()))
                    .collect(Collectors.toList());
            searchDTO.setMultipleSelection(lst);
            searchDTO.setDeptId(dto.getDeptId());
            searchDTO.setParams(dto.getParams());
            result = searchBoxEditHistoryList(searchDTO);
            filingBoxProfileMapper.dropTempTable(tableName);
        }
        // 导出全部
        else {
            SearchBoxProfileDTO searchDTO = new SearchBoxProfileDTO();
            searchDTO.setDeptId(dto.getDeptId());
            searchDTO.setParams(dto.getParams());
            result = searchBoxEditHistoryList(searchDTO);
        }
        return result;
    }
}
