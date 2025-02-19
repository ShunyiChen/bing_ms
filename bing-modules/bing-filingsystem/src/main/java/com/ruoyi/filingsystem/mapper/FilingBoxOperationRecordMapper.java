package com.ruoyi.filingsystem.mapper;

import java.util.List;
import com.ruoyi.filingsystem.domain.FilingBoxOperationRecord;
import com.ruoyi.filingsystem.dto.BatchInsertBoxHistoryDTO;
import com.ruoyi.filingsystem.dto.FilingBoxSearchOperationRecordDto;
import org.apache.ibatis.annotations.Param;

/**
 * 用于记录盒子操作历史的,整箱操作用Mapper接口
 * 
 * @author victor.xiong
 * @date 2024-11-18
 */
public interface FilingBoxOperationRecordMapper 
{
    /**
     * 查询用于记录盒子操作历史的,整箱操作用
     * 
     * @param id 用于记录盒子操作历史的,整箱操作用主键
     * @return 用于记录盒子操作历史的,整箱操作用
     */
    public FilingBoxOperationRecord selectFilingBoxOperationRecordById(Long id);

    /**
     * 查询用于记录盒子操作历史的,整箱操作用列表
     * 
     * @param filingBoxOperationRecord 用于记录盒子操作历史的,整箱操作用
     * @return 用于记录盒子操作历史的,整箱操作用集合
     */
    public List<FilingBoxOperationRecord> selectFilingBoxOperationRecordList(FilingBoxOperationRecord filingBoxOperationRecord);

    /**
     * 新增用于记录盒子操作历史的,整箱操作用
     * 
     * @param filingBoxOperationRecord 用于记录盒子操作历史的,整箱操作用
     * @return 结果
     */
    int insertFilingBoxOperationRecord(FilingBoxOperationRecord filingBoxOperationRecord);

    /**
     * 批量插入历史记录
     *
     * @param dto
     */
    void batchInsertHistoryRecord(BatchInsertBoxHistoryDTO dto);

    /**
     * 修改用于记录盒子操作历史的,整箱操作用
     * 
     * @param filingBoxOperationRecord 用于记录盒子操作历史的,整箱操作用
     * @return 结果
     */
    public int updateFilingBoxOperationRecord(FilingBoxOperationRecord filingBoxOperationRecord);

    /**
     * 删除用于记录盒子操作历史的,整箱操作用
     * 
     * @param id 用于记录盒子操作历史的,整箱操作用主键
     * @return 结果
     */
    public int deleteFilingBoxOperationRecordById(Long id);

    /**
     * 批量删除用于记录盒子操作历史的,整箱操作用
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFilingBoxOperationRecordByIds(Long[] ids);

    List<FilingBoxOperationRecord> selectList(FilingBoxSearchOperationRecordDto filingFileOperationRecord);

    List<FilingBoxOperationRecord> selectFilingFileOperationRecordList(@Param("List") String[] filingFileOperationRecord);
}
