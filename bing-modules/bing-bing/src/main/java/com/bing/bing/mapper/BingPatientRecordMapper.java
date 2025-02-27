package com.bing.bing.mapper;

import java.util.List;
import com.bing.bing.domain.BingPatientRecord;
import com.bing.bing.dto.BatchAddDTO;

/**
 * 病案Mapper接口
 * 
 * @author Simeon
 * @date 2025-02-25
 */
 public interface BingPatientRecordMapper
{
    /**
     * 查询病案
     * 
     * @param id 病案主键
     * @return 病案
     */
     BingPatientRecord selectBingPatientRecordById(Long id);

    /**
     * 查询病案列表
     * 
     * @param bingPatientRecord 病案
     * @return 病案集合
     */
     List<BingPatientRecord> selectBingPatientRecordList(BingPatientRecord bingPatientRecord);

    /**
     * 检查新增病案列表是否已存在
     *
     * @param list 病案号列表
     * @return 记录数
     */
     int checksIfListIsExist(List<String> list);
     
    /**
     * 新增病案
     * 
     * @param batchAddDTO 病案
     * @return 结果
     */
     int insertBingPatientRecord(BatchAddDTO batchAddDTO);

    /**
     * 修改病案
     * 
     * @param bingPatientRecord 病案
     * @return 结果
     */
     int updateBingPatientRecord(BingPatientRecord bingPatientRecord);

    /**
     * 删除病案
     * 
     * @param id 病案主键
     * @return 结果
     */
     int deleteBingPatientRecordById(Long id);

    /**
     * 批量删除病案
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
     int deleteBingPatientRecordByIds(Long[] ids);
}
