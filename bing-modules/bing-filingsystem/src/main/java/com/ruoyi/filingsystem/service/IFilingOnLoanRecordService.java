package com.ruoyi.filingsystem.service;

import com.ruoyi.filingsystem.dto.FetchOnLoanRecordDTO;
import com.ruoyi.filingsystem.vo.FilingOnLoanRecordVO;

import java.util.List;

/**
 * 用于记录文档借阅和归还情况的_管理人员操作Service接口
 *
 * @author ruoyi
 * @date 2024-03-25
 */
public interface IFilingOnLoanRecordService {

    List<FilingOnLoanRecordVO> fetchOnLoanRecordList(FetchOnLoanRecordDTO fetchReturnFileDTO);
}
