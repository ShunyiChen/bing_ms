package com.ruoyi.filingsystem.mapper;

import com.ruoyi.filingsystem.domain.FilingFileOperationRecord;

import java.util.List;

/**
 * 用于记录文档操作历史的_管理人员查询用，自动录入的Mapper接口
 * 
 * @author ruoyi
 * @date 2024-03-25
 */
public interface FilingFileOperationRecordMapper
{
    /**
     * 查询用于记录文档操作历史的_管理人员查询用，自动录入的
     * 
     * @param recordID 用于记录文档操作历史的_管理人员查询用，自动录入的主键
     * @return 用于记录文档操作历史的_管理人员查询用，自动录入的
     */
    public FilingFileOperationRecord selectFilingFileOperationRecordByRecordID(Long recordID);

    /**
     * 查询用于记录文档操作历史的_管理人员查询用，自动录入的列表
     * 
     * @param filingFileOperationRecord 用于记录文档操作历史的_管理人员查询用，自动录入的
     * @return 用于记录文档操作历史的_管理人员查询用，自动录入的集合
     */
    public List<FilingFileOperationRecord> selectFilingFileOperationRecordList(FilingFileOperationRecord filingFileOperationRecord);

    /**
     * 新增用于记录文档操作历史的_管理人员查询用，自动录入的
     * 
     * @param filingFileOperationRecord 用于记录文档操作历史的_管理人员查询用，自动录入的
     * @return 结果
     */
    public int insertFilingFileOperationRecord(FilingFileOperationRecord filingFileOperationRecord);

    /**
     * 修改用于记录文档操作历史的_管理人员查询用，自动录入的
     * 
     * @param filingFileOperationRecord 用于记录文档操作历史的_管理人员查询用，自动录入的
     * @return 结果
     */
    public int updateFilingFileOperationRecord(FilingFileOperationRecord filingFileOperationRecord);

    /**
     * 删除用于记录文档操作历史的_管理人员查询用，自动录入的
     * 
     * @param recordID 用于记录文档操作历史的_管理人员查询用，自动录入的主键
     * @return 结果
     */
    public int deleteFilingFileOperationRecordByRecordID(Long recordID);

    /**
     * 批量删除用于记录文档操作历史的_管理人员查询用，自动录入的
     * 
     * @param recordIDs 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFilingFileOperationRecordByRecordIDs(Long[] recordIDs);
}
