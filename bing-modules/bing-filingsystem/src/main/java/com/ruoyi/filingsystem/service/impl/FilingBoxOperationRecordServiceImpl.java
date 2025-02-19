package com.ruoyi.filingsystem.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.filingsystem.dto.FilingBoxSearchOperationRecordDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.filingsystem.mapper.FilingBoxOperationRecordMapper;
import com.ruoyi.filingsystem.domain.FilingBoxOperationRecord;
import com.ruoyi.filingsystem.service.IFilingBoxOperationRecordService;

/**
 * 用于记录盒子操作历史的,整箱操作用Service业务层处理
 * 
 * @author victor.xiong
 * @date 2024-11-18
 */
@Service
public class FilingBoxOperationRecordServiceImpl implements IFilingBoxOperationRecordService 
{
    @Autowired
    private FilingBoxOperationRecordMapper filingBoxOperationRecordMapper;

    @Override
    public List<FilingBoxOperationRecord> selectList(FilingBoxSearchOperationRecordDto filingFileOperationRecord) {
//查询  编辑记录用
        List<FilingBoxOperationRecord>  list = filingBoxOperationRecordMapper.selectList(filingFileOperationRecord);

        return list;
    }

    @Override
    public List<FilingBoxOperationRecord> selectFilingFileOperationRecordList(FilingBoxSearchOperationRecordDto filingFileOperationRecord) {
//导出编辑记录用


        String[] multipleSelection = filingFileOperationRecord.getMultipleSelection();
        List<FilingBoxOperationRecord>  list=new ArrayList<>();

        if(multipleSelection != null){
            //部分导出 调用专用接口
          list=filingBoxOperationRecordMapper.selectFilingFileOperationRecordList(multipleSelection);
        }else {
            //全部导出 调用查询接口
         list=filingBoxOperationRecordMapper.selectList(filingFileOperationRecord);
        }
        return list;
    }
}
