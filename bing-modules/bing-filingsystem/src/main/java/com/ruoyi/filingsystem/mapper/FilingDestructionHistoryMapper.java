package com.ruoyi.filingsystem.mapper;

import com.ruoyi.filingsystem.dto.DestructionHistorySearchDTO;
import com.ruoyi.filingsystem.vo.FilingBoxHistoryVO;
import com.ruoyi.filingsystem.vo.FilingFileHistoryVO;

import java.util.List;

public interface FilingDestructionHistoryMapper {

    List<FilingFileHistoryVO> findFileHistory(DestructionHistorySearchDTO dto);

    List<FilingBoxHistoryVO> findBoxHistory(DestructionHistorySearchDTO dto);
}
