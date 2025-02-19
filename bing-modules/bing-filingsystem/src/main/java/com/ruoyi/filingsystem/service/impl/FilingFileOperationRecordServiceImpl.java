package com.ruoyi.filingsystem.service.impl;

import com.ruoyi.filingsystem.domain.FilingFileOperationRecord;
import com.ruoyi.filingsystem.mapper.FilingFileOperationRecordMapper;
import com.ruoyi.filingsystem.service.IFilingFileOperationRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用于记录文档操作历史的_管理人员查询用，自动录入的Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-25
 */
@Service
public class FilingFileOperationRecordServiceImpl implements IFilingFileOperationRecordService
{
    @Autowired
    private FilingFileOperationRecordMapper filingFileOperationRecordMapper;

    /**
     * 查询用于记录文档操作历史的_管理人员查询用，自动录入的
     * 
     * @param recordID 用于记录文档操作历史的_管理人员查询用，自动录入的主键
     * @return 用于记录文档操作历史的_管理人员查询用，自动录入的
     */
    @Override
    public FilingFileOperationRecord selectFilingFileOperationRecordByRecordID(Long recordID)
    {
        return filingFileOperationRecordMapper.selectFilingFileOperationRecordByRecordID(recordID);
    }

    /**
     * 查询用于记录文档操作历史的_管理人员查询用，自动录入的列表
     * 
     * @param filingFileOperationRecord 用于记录文档操作历史的_管理人员查询用，自动录入的
     * @return 用于记录文档操作历史的_管理人员查询用，自动录入的
     */
    @Override
    public List<FilingFileOperationRecord> selectFilingFileOperationRecordList(FilingFileOperationRecord filingFileOperationRecord)
    {
        return filingFileOperationRecordMapper.selectFilingFileOperationRecordList(filingFileOperationRecord);
    }

    /**
     * 新增用于记录文档操作历史的_管理人员查询用，自动录入的
     * 
     * @param filingFileOperationRecord 用于记录文档操作历史的_管理人员查询用，自动录入的
     * @return 结果
     */
    @Override
    public int insertFilingFileOperationRecord(FilingFileOperationRecord filingFileOperationRecord)
    {
        return filingFileOperationRecordMapper.insertFilingFileOperationRecord(filingFileOperationRecord);
    }

    /**
     * 修改用于记录文档操作历史的_管理人员查询用，自动录入的
     * 
     * @param filingFileOperationRecord 用于记录文档操作历史的_管理人员查询用，自动录入的
     * @return 结果
     */
    @Override
    public int updateFilingFileOperationRecord(FilingFileOperationRecord filingFileOperationRecord)
    {
        return filingFileOperationRecordMapper.updateFilingFileOperationRecord(filingFileOperationRecord);
    }

    /**
     * 批量删除用于记录文档操作历史的_管理人员查询用，自动录入的
     * 
     * @param recordIDs 需要删除的用于记录文档操作历史的_管理人员查询用，自动录入的主键
     * @return 结果
     */
    @Override
    public int deleteFilingFileOperationRecordByRecordIDs(Long[] recordIDs)
    {
        return filingFileOperationRecordMapper.deleteFilingFileOperationRecordByRecordIDs(recordIDs);
    }

    /**
     * 删除用于记录文档操作历史的_管理人员查询用，自动录入的信息
     * 
     * @param recordID 用于记录文档操作历史的_管理人员查询用，自动录入的主键
     * @return 结果
     */
    @Override
    public int deleteFilingFileOperationRecordByRecordID(Long recordID)
    {
        return filingFileOperationRecordMapper.deleteFilingFileOperationRecordByRecordID(recordID);
    }
}
