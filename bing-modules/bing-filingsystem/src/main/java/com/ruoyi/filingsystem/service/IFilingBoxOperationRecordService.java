package com.ruoyi.filingsystem.service;

import java.util.List;
import com.ruoyi.filingsystem.domain.FilingBoxOperationRecord;
import com.ruoyi.filingsystem.dto.FilingBoxSearchOperationRecordDto;

/**
 * 用于记录盒子操作历史的,整箱操作用Service接口
 * 
 * @author victor.xiong
 * @date 2024-11-18
 */
public interface IFilingBoxOperationRecordService 
{



    List<FilingBoxOperationRecord> selectList(FilingBoxSearchOperationRecordDto filingFileOperationRecord);

    List<FilingBoxOperationRecord> selectFilingFileOperationRecordList(FilingBoxSearchOperationRecordDto filingFileOperationRecord);
}
