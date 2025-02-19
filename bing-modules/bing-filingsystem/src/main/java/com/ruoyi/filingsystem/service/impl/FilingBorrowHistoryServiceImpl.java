package com.ruoyi.filingsystem.service.impl;

import com.ruoyi.common.datascope.annotation.DataScope;
import com.ruoyi.filingsystem.dto.FetchBorrowHistoryDTO;
import com.ruoyi.filingsystem.mapper.FilingBorrowHistoryMapper;
import com.ruoyi.filingsystem.service.IFilingBorrowHistoryService;
import com.ruoyi.filingsystem.vo.FilingBorrowHistoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用于记录文档借阅和归还情况的_管理人员操作Service业务层处理
 *
 * @author ruoyi
 * @date 2024-03-25
 */
@Service
public class FilingBorrowHistoryServiceImpl implements IFilingBorrowHistoryService {
    @Autowired
    private FilingBorrowHistoryMapper filingBorrowHistoryMapper;

    @Override
    @DataScope(deptAlias = "fb")
    public List<FilingBorrowHistoryVO> fetchBorrowHistory(FetchBorrowHistoryDTO dto) {
        return filingBorrowHistoryMapper.fetchBorrowHistoryList(dto);
    }
}
