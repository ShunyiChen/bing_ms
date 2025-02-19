package com.ruoyi.filingsystem.service.impl;

import com.ruoyi.common.datascope.annotation.DataScope;
import com.ruoyi.filingsystem.dto.FetchOnLoanRecordDTO;
import com.ruoyi.filingsystem.mapper.FilingOnLoanRecordMapper;
import com.ruoyi.filingsystem.service.IFilingOnLoanRecordService;
import com.ruoyi.filingsystem.vo.FilingOnLoanRecordVO;
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
public class FilingOnLoanRecordServiceImpl implements IFilingOnLoanRecordService {
    @Autowired
    private FilingOnLoanRecordMapper filingOnLoanRecordMapper;

    @Override
    @DataScope(deptAlias = "fb")
    public List<FilingOnLoanRecordVO> fetchOnLoanRecordList(FetchOnLoanRecordDTO fetchReturnFileDTO) {
        return filingOnLoanRecordMapper.fetchOnLoanRecordList(fetchReturnFileDTO);
    }
}
