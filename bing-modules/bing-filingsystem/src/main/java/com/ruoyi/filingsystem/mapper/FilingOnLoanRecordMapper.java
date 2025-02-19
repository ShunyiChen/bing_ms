package com.ruoyi.filingsystem.mapper;

import com.ruoyi.filingsystem.dto.FetchOnLoanRecordDTO;
import com.ruoyi.filingsystem.vo.FilingOnLoanRecordVO;

import java.util.List;

/**
 * 用于记录文档借阅情况的_管理人员操作Mapper接口
 * 
 * @author ruoyi
 * @date 2024-03-25
 */
public interface FilingOnLoanRecordMapper {
    List<FilingOnLoanRecordVO> fetchOnLoanRecordList(FetchOnLoanRecordDTO fetchReturnFileDTO);
}
