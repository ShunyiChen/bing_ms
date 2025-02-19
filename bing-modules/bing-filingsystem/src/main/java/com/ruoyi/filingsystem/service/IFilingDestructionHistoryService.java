package com.ruoyi.filingsystem.service;

import com.ruoyi.filingsystem.dto.DestructionHistorySearchDTO;
import com.ruoyi.filingsystem.vo.FilingBoxHistoryVO;
import com.ruoyi.filingsystem.vo.FilingFileHistoryVO;

import java.util.List;

public interface IFilingDestructionHistoryService {

    List<FilingFileHistoryVO> getFileHistory(DestructionHistorySearchDTO dto);

    List<FilingBoxHistoryVO> getBoxHistory(DestructionHistorySearchDTO dto);
}
