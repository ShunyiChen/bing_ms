package com.bing.bing.mapper;

import java.util.List;
import com.bing.bing.domain.BingPatientRecord;
import com.bing.bing.dto.BatchAddDTO;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author Simeon
 * @date 2025-02-25
 */
 public interface BingPatientRecordMapper
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
     BingPatientRecord selectBingPatientRecordById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param bingPatientRecord 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
     List<BingPatientRecord> selectBingPatientRecordList(BingPatientRecord bingPatientRecord);

    /**
     * 新增【请填写功能名称】
     * 
     * @param batchAddDTO 【请填写功能名称】
     * @return 结果
     */
     int insertBingPatientRecord(BatchAddDTO batchAddDTO);

    /**
     * 修改【请填写功能名称】
     * 
     * @param bingPatientRecord 【请填写功能名称】
     * @return 结果
     */
     int updateBingPatientRecord(BingPatientRecord bingPatientRecord);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
     int deleteBingPatientRecordById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
     int deleteBingPatientRecordByIds(Long[] ids);
}
