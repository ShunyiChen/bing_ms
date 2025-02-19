package com.ruoyi.filingsystem.service.impl;

import com.ruoyi.common.datascope.annotation.DataScope;
import com.ruoyi.filingsystem.dto.DestructionHistorySearchDTO;
import com.ruoyi.filingsystem.mapper.FilingDestructionHistoryMapper;
import com.ruoyi.filingsystem.service.IFilingDestructionHistoryService;
import com.ruoyi.filingsystem.vo.FilingBoxHistoryVO;
import com.ruoyi.filingsystem.vo.FilingFileHistoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilingDestructionHistoryServiceImpl implements IFilingDestructionHistoryService {

    @Autowired
    private FilingDestructionHistoryMapper mapper;

    @Override
    @DataScope(deptAlias = "r")
    public List<FilingFileHistoryVO> getFileHistory(DestructionHistorySearchDTO dto) {
        return mapper.findFileHistory(dto);
    }

    @Override
    @DataScope(deptAlias = "ffb")
    public List<FilingBoxHistoryVO> getBoxHistory(DestructionHistorySearchDTO dto) {
        return mapper.findBoxHistory(dto);
    }
}
