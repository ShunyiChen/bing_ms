package com.ruoyi.filingsystem.service;

import com.ruoyi.filingsystem.domain.FilingFileProfile;
import com.ruoyi.filingsystem.dto.FetchEditHistoryDTO;
import com.ruoyi.filingsystem.dto.RecoverDTO;
import com.ruoyi.filingsystem.dto.UpdateFileDTO;
import com.ruoyi.filingsystem.vo.FilingEditHistoryVO;

import java.util.List;

/**
 * 用于记录文档借阅和归还情况的_管理人员操作Service接口
 *
 * @author ruoyi
 * @date 2024-03-25
 */
public interface IFilingEditHistoryService {
    List<FilingEditHistoryVO> fetchEditHistory(FetchEditHistoryDTO dto);
    List<FilingEditHistoryVO> exportEditHistory(FetchEditHistoryDTO dto);
    FilingFileProfile getHistoryByHistID(Long histID);
    int recover(RecoverDTO dto);
}
