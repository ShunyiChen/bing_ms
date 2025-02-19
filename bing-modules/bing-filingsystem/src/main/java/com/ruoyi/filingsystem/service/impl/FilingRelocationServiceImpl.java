package com.ruoyi.filingsystem.service.impl;

import com.ruoyi.common.datascope.annotation.DataScope;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.filingsystem.domain.FilingFileOperationRecord;
import com.ruoyi.filingsystem.domain.FilingFileProfile;
import com.ruoyi.filingsystem.domain.FilingLocation;
import com.ruoyi.filingsystem.dto.*;
import com.ruoyi.filingsystem.mapper.FilingFileOperationRecordMapper;
import com.ruoyi.filingsystem.mapper.FilingRelocationMapper;
import com.ruoyi.filingsystem.service.IFilingFileProfileService;
import com.ruoyi.filingsystem.service.IFilingRelocationService;
import com.ruoyi.filingsystem.utils.enums.EnumFileOperationState;
import com.ruoyi.filingsystem.vo.FilingBoxExistsVO;
import com.ruoyi.filingsystem.vo.FilingBoxFilesVO;
import com.ruoyi.filingsystem.vo.FilingFileBoxVO;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FilingRelocationServiceImpl implements IFilingRelocationService {
    @Autowired
    private FilingRelocationMapper filingRelocationMapper;
    @Autowired
    private FilingFileOperationRecordMapper filingFileOperationRecordMapper;
    @Autowired
    private IFilingFileProfileService filingFileProfileService;

    @Override
    @DataScope(deptAlias = "ffb")
    public List<FilingBoxFilesVO> searchByBoxNumber(InsertFileToBoxDTO dto) {
        List<FilingBoxFilesVO> list = filingRelocationMapper.searchByBoxNumber(dto);
        if(!list.isEmpty()) {
            List<SearchFileIDsDTO> listDTO = new ArrayList<>();
            for(FilingBoxFilesVO vo : list) {
                SearchFileIDsDTO d = new SearchFileIDsDTO();
                d.setBoxNumber(vo.getBoxNumber());
                d.setDeptId(vo.getDeptId());
                listDTO.add(d);
            }
            List<FilingBoxFilesVO> fileIDs = filingRelocationMapper.searchFileIDsByBoxNumber(listDTO);
            list.forEach(e -> {
                Optional<FilingBoxFilesVO> opt = fileIDs.stream()
                        .filter(f -> e.getBoxNumber().equals(f.getBoxNumber())
                                && e.getDeptId().equals(f.getDeptId())).findFirst();
                opt.ifPresent(box -> e.setFileIDs(box.getFileIDs()));
            });
        }
        return list;
    }

    @Override
    public void createBoxNumber(CreateBoxDTO dto) {
        String userName = SecurityUtils.getLoginUser().getUsername();
        dto.setCreateBy(userName);
        dto.setBoxPrefix(dto.getBoxPrefix().toUpperCase());
        dto.setBoxSuffix(dto.getBoxSuffix().toUpperCase());
        filingRelocationMapper.createBoxNumber(dto);
    }

    @Override
    @DataScope(deptAlias = "r")
    public FilingFileProfile fetchByFileID(FetchFileDTO dto) {
        return filingRelocationMapper.fetchByFileID(dto);
    }

    @Override
    public int insertFileToBox(InsertFileToBoxDTO dto) {
        int rows = filingRelocationMapper.insertFileToBox(dto);

        // 添加操作记录
        FilingFileOperationRecord fileOperationRecord = new FilingFileOperationRecord();
        fileOperationRecord.setFileIDs(Arrays.asList(dto.getFileIDs()));
        fileOperationRecord.setEditor(SecurityUtils.getUsername());
        fileOperationRecord.setAction(EnumFileOperationState.EDIT.getState());
        filingFileOperationRecordMapper.insertFilingFileOperationRecord(fileOperationRecord);

        return rows;
    }

    @Override
    public int moveBoxToBox(MoveBoxToBoxDTO dto) {
        InsertFileToBoxDTO oldDTO = new InsertFileToBoxDTO();
        oldDTO.setBoxNumber(dto.getBoxNumber());
        oldDTO.setDeptId(dto.getDeptId());
        List<FilingBoxFilesVO> list = filingRelocationMapper.searchByBoxNumber(oldDTO);
        String[] fileIDList = {};
        for(FilingBoxFilesVO vo : list) {
            if(!StringUtils.isEmpty(vo.getFileIDs())) {
                String[] fileIDs = vo.getFileIDs().split(",");
                fileIDList = ArrayUtils.addAll(fileIDList, fileIDs);
            }
        }
        if(fileIDList.length > 0) {
            InsertFileToBoxDTO insertFileToBoxDTO = new InsertFileToBoxDTO();
            insertFileToBoxDTO.setBoxNumber(dto.getBoxNumberMoveTo());
            insertFileToBoxDTO.setFileIDs(fileIDList);
            return insertFileToBox(insertFileToBoxDTO);
        }
        return 0;
    }

    @Override
    public int moveFileToBox(List<MoveFileToBoxDTO> params) {
        int rows = filingRelocationMapper.moveFileToBox(params);

        // 添加操作记录
        FilingFileOperationRecord fileOperationRecord = new FilingFileOperationRecord();
        fileOperationRecord.setFileIDs(params.stream().map(MoveFileToBoxDTO::getFileID).collect(Collectors.toList()));
        fileOperationRecord.setEditor(SecurityUtils.getUsername());
        fileOperationRecord.setAction(EnumFileOperationState.EDIT.getState());
        filingFileOperationRecordMapper.insertFilingFileOperationRecord(fileOperationRecord);

        return rows;
    }

    @Override
    public int moveFileToOffice(List<MoveFileToOfficeDTO> params) {
        int rows = filingRelocationMapper.moveToOffice(params);

        // 添加操作记录
        FilingFileOperationRecord fileOperationRecord = new FilingFileOperationRecord();
        fileOperationRecord.setFileIDs(params.stream().map(MoveFileToOfficeDTO::getFileID).collect(Collectors.toList()));
        fileOperationRecord.setEditor(SecurityUtils.getUsername());
        fileOperationRecord.setAction(EnumFileOperationState.EDIT.getState());
        filingFileOperationRecordMapper.insertFilingFileOperationRecord(fileOperationRecord);

        return rows;
    }

    @Override
    @DataScope(deptAlias = "ffb")
    public int existCheck(CreateBoxDTO dto) {
        return filingRelocationMapper.existCheck(dto);
    }

    @Override
    @DataScope(deptAlias = "filing_location")
    public List<FilingLocation> getLocationList(GetLocationsDTO dto) {
        return filingRelocationMapper.findLocationList(dto);
    }

    @Override
    public List<FilingLocation> getAllLocations() {
        return filingRelocationMapper.findAllLocations();
    }

    @Override
    @DataScope(deptAlias = "ffb")
    public List<FilingBoxExistsVO> checksIfAllBoxesExist(ChecksIfAllBoxesExistDTO dto) {
        List<FilingBoxExistsVO> list = new ArrayList<>();
        List<FilingFileBoxVO> lstBox = filingRelocationMapper.findAllBoxes(dto);
        for (CheckBoxExistDTO d : dto.getList()) {
            boolean matched = lstBox.stream()
                    .anyMatch(v -> v.getBoxPrefix().equalsIgnoreCase(d.getPrefix()) && v.getBoxSuffix().equalsIgnoreCase(d.getSuffix()) && v.getDeptId().intValue() == d.getDeptId().intValue());
            if(matched) {
                FilingBoxExistsVO vo = new FilingBoxExistsVO(d.getPrefix()+d.getSuffix(), 1);
                list.add(vo);
            } else {
                FilingBoxExistsVO vo = new FilingBoxExistsVO(d.getPrefix()+d.getSuffix(), 0);
                list.add(vo);
            }
        }
        return list;
    }
}
