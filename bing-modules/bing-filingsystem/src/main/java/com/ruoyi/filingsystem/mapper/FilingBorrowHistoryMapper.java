package com.ruoyi.filingsystem.mapper;

import com.ruoyi.filingsystem.dto.FetchBorrowHistoryDTO;
import com.ruoyi.filingsystem.vo.FilingBorrowHistoryVO;

import java.util.List;

/**
 * 用于记录文档借阅和归还情况的_管理人员操作Mapper接口
 * 
 * @author ruoyi
 * @date 2024-03-25
 */
public interface FilingBorrowHistoryMapper {

    List<FilingBorrowHistoryVO> fetchBorrowHistoryList(FetchBorrowHistoryDTO dto);
}
