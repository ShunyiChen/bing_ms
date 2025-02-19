package com.ruoyi.filingsystem.service.impl;

import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.datascope.annotation.DataScope;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.filingsystem.domain.FilingFileProfile;
import com.ruoyi.filingsystem.domain.FilingFileOperationRecord;
import com.ruoyi.filingsystem.dto.FetchEditHistoryBorrowStatesDTO;
import com.ruoyi.filingsystem.dto.FetchEditHistoryDTO;
import com.ruoyi.filingsystem.dto.RecoverDTO;
import com.ruoyi.filingsystem.mapper.FilingEditHistoryMapper;
import com.ruoyi.filingsystem.mapper.FilingFileOperationRecordMapper;
import com.ruoyi.filingsystem.service.IFilingEditHistoryService;
import com.ruoyi.filingsystem.utils.enums.EnumFileOperationState;
import com.ruoyi.filingsystem.vo.FilingEditHistoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 用于记录文档借阅和归还情况的_管理人员操作Service业务层处理
 *
 * @author ruoyi
 * @date 2024-03-25
 */
@Service
public class FilingEditHistoryServiceImpl implements IFilingEditHistoryService {
    @Autowired
    private FilingEditHistoryMapper filingEditHistoryMapper;
    @Autowired
    private FilingFileOperationRecordMapper filingFileOperationRecordMapper;

    @Override
    @DataScope(deptAlias = "ffor")
    public List<FilingEditHistoryVO> fetchEditHistory(FetchEditHistoryDTO dto) {
        populateBoxNumber(dto);
        List<FilingEditHistoryVO> list = filingEditHistoryMapper.fetchEditHistoryList(dto);
        if(!list.isEmpty()) {
            List<FetchEditHistoryBorrowStatesDTO> listDTO = new ArrayList<>();
            for(FilingEditHistoryVO vo : list) {
                FetchEditHistoryBorrowStatesDTO d = new FetchEditHistoryBorrowStatesDTO();
                d.setDeptId(vo.getDeptId());
                d.setOldSystemfileID(vo.getOldSystemfileID());
                listDTO.add(d);
            }
            List<FilingEditHistoryVO> borrowStates = filingEditHistoryMapper.fetchEditHistoryBorrowStates(listDTO);
            list.forEach(e -> {
                Optional<FilingEditHistoryVO> opt = borrowStates.stream()
                        .filter(f -> e.getOldSystemfileID().equals(f.getOldSystemfileID())
                                && e.getDeptId().equals(f.getDeptId())).findFirst();
                opt.ifPresent(box -> e.setBorrowState(box.getBorrowState()));
            });
        }
        return list;
    }

    private void populateBoxNumber(FetchEditHistoryDTO fetchEditHistoryDTO) {
        if(!StringUtils.isEmpty(fetchEditHistoryDTO.getBoxPrefix())
                || !StringUtils.isEmpty(fetchEditHistoryDTO.getBoxSuffix())) {
            if(!StringUtils.isEmpty(fetchEditHistoryDTO.getBoxSuffix())) {
                String output = fetchEditHistoryDTO.getBoxSuffix().replaceAll("^0+", "");
                fetchEditHistoryDTO.setBoxSuffix(output);
            }
            fetchEditHistoryDTO.setBoxNumber(fetchEditHistoryDTO.getBoxPrefix() + fetchEditHistoryDTO.getBoxSuffix());
        }
    }

    @Override
    @DataScope(deptAlias = "ffor")
    public List<FilingEditHistoryVO> exportEditHistory(FetchEditHistoryDTO dto) {
        populateBoxNumber(dto);
        return filingEditHistoryMapper.fetchEditHistoryList(dto);
    }

    @Override
    public FilingFileProfile getHistoryByHistID(Long HistID) {
        return filingEditHistoryMapper.getHistoryByHistID(HistID);
    }

    @Override
    public int recover(RecoverDTO dto) {
        int updated = filingEditHistoryMapper.recover(dto);

        // 添加操作记录
        FilingFileOperationRecord fileOperationRecord = new FilingFileOperationRecord();
        fileOperationRecord.setFileIDs(Stream.of(dto.getFileID()).collect(Collectors.toList()));
        fileOperationRecord.setEditor(SecurityUtils.getUsername());
        fileOperationRecord.setAction(EnumFileOperationState.RECOVER.getState());
        filingFileOperationRecordMapper.insertFilingFileOperationRecord(fileOperationRecord);
        return updated;
    }

}
