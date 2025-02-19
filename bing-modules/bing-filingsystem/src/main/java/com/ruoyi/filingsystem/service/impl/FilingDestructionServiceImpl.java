package com.ruoyi.filingsystem.service.impl;

import com.ruoyi.common.datascope.annotation.DataScope;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.filingsystem.domain.FilingFileProfile;
import com.ruoyi.filingsystem.domain.FilingFileOperationRecord;
import com.ruoyi.filingsystem.dto.DestructionBoxConfirmDTO;
import com.ruoyi.filingsystem.dto.DestructionFileConfirmDto;
import com.ruoyi.filingsystem.dto.SearchFileIDsDTO;
import com.ruoyi.filingsystem.mapper.FilingFileOperationRecordMapper;
import com.ruoyi.filingsystem.utils.enums.EnumFileOperationState;
import com.ruoyi.filingsystem.dto.DestructionFileSearchDto;
import com.ruoyi.filingsystem.mapper.FilingDestructionMapper;
import com.ruoyi.filingsystem.service.IFilingDestructionService;
import com.ruoyi.filingsystem.vo.FilingBoxFilesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class FilingDestructionServiceImpl implements IFilingDestructionService {

    @Autowired
    private FilingDestructionMapper mapper;
    @Autowired
    private FilingFileOperationRecordMapper filingFileOperationRecordMapper;

    @Override
    @DataScope(deptAlias = "r")
    public List<FilingFileProfile> getFileList(DestructionFileSearchDto dto) {
        return mapper.findFileList(dto);
    }

    @Override
    @DataScope(deptAlias = "ffb")
    public List<FilingBoxFilesVO> getBoxList(DestructionFileSearchDto dto) {
        List<FilingBoxFilesVO> list = mapper.findBoxList(dto);
        if(!list.isEmpty()) {
            List<SearchFileIDsDTO> listDTO = new ArrayList<>();
            for(FilingBoxFilesVO vo : list) {
                SearchFileIDsDTO d = new SearchFileIDsDTO();
                d.setBoxNumber(vo.getBoxNumber());
                d.setDeptId(vo.getDeptId());
                listDTO.add(d);
            }
            List<FilingBoxFilesVO> fileIDs = mapper.searchFileIDsByBoxNumber(listDTO);
            list.forEach(e -> {
                Optional<FilingBoxFilesVO> opt = fileIDs.stream()
                        .filter(f -> e.getBoxNumber().equals(f.getBoxNumber())
                                && e.getDeptId().equals(f.getDeptId())).findFirst();
                opt.ifPresent(box -> {
                    e.setFileIDs(box.getFileIDs());
                    e.setIsDPNs(box.getIsDPNs());
                    e.setBorrowStates(box.getBorrowStates());
                    e.setFileStates(box.getFileStates());
                    e.setIsMoveds(box.getIsMoveds());
                });
            });
        }
        return list;
    }

    @Override
    public int destructFile(DestructionFileConfirmDto dto) {
        int rows = mapper.updateFileProfile(dto);

        // 添加操作记录
        FilingFileOperationRecord fileOperationRecord = new FilingFileOperationRecord();
        fileOperationRecord.setFileIDs(Arrays.asList(dto.getFileIDs()));
        fileOperationRecord.setEditor(SecurityUtils.getUsername());
        fileOperationRecord.setAction(EnumFileOperationState.DESTROY.getState());
        filingFileOperationRecordMapper.insertFilingFileOperationRecord(fileOperationRecord);

        return rows;
    }

    @Override
    public int destructBox(DestructionBoxConfirmDTO dto) {
        // 更新盒子状态
        mapper.updateFileBox(dto);
        // 插入盒子销毁记录
        mapper.insertFileBoxFileID(dto);
        // 更新文件状态
        List<String> fileIDs = mapper.findFileIDsByBoxes(dto);
        if(!fileIDs.isEmpty()) {
            // 销毁文件
            DestructionFileConfirmDto fileConfirmDto = new DestructionFileConfirmDto();
            fileConfirmDto.setFileIDs(fileIDs.stream().toArray(String[]::new));
            fileConfirmDto.setDestructionPeople(dto.getDestroyer());
            int updated = mapper.updateFileProfile(fileConfirmDto);

            // 添加操作记录
            FilingFileOperationRecord fileOperationRecord = new FilingFileOperationRecord();
            fileOperationRecord.setFileIDs(fileIDs);
            fileOperationRecord.setEditor(SecurityUtils.getUsername());
            fileOperationRecord.setAction(EnumFileOperationState.DESTROY.getState());
            filingFileOperationRecordMapper.insertFilingFileOperationRecord(fileOperationRecord);
            return updated;
        }
        return 1;
    }

}
