package com.ruoyi.filingsystem.mapper;

import com.ruoyi.filingsystem.domain.FilingFileProfile;
import com.ruoyi.filingsystem.dto.FetchEditHistoryBorrowStatesDTO;
import com.ruoyi.filingsystem.dto.FetchEditHistoryDTO;
import com.ruoyi.filingsystem.dto.RecoverDTO;
import com.ruoyi.filingsystem.vo.FilingEditHistoryVO;

import java.util.List;

/**
 * 用于记录文档编辑情况的_管理人员操作Mapper接口
 * 
 * @author ruoyi
 * @date 2024-03-25
 */
public interface FilingEditHistoryMapper {

    List<FilingEditHistoryVO> fetchEditHistoryList(FetchEditHistoryDTO dto);
    List<FilingEditHistoryVO> fetchEditHistoryBorrowStates(List<FetchEditHistoryBorrowStatesDTO> list);
    FilingFileProfile getHistoryByHistID(Long HistID);
    int recover(RecoverDTO dto);
}
