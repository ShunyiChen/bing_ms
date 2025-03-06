package com.bing.bing.service;

import java.util.List;
import com.bing.bing.domain.BingPatientRecord;
import com.bing.bing.dto.BatchAddDTO;

/**
 * 病案Service接口
 * 
 * @author Simeon
 * @date 2025-02-25
 */
public interface IBingPatientRecordService 
{
    /**
     * 通过病案号获取病案详细信息
     *
     * @param medicalRecordNumber 病案号
     * @return
     */
    BingPatientRecord findByMedicalRecordNumber(String medicalRecordNumber);

    /**
     * 查询病案列表
     * 
     * @param bingPatientRecord 病案
     * @return 病案集合
     */
    List<BingPatientRecord> selectBingPatientRecordList(BingPatientRecord bingPatientRecord);

    /**
     * 检查新增病案列表是否已添加过
     *
     * @param batchAddDTO
     * @return 记录数
     */
    int checksIfListIsExist(BatchAddDTO batchAddDTO);
    
    /**
     * 新增【批量添加病案】
     * 
     * @param batchAddDTO 【要添加的病案列表】
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
     * 批量删除病案
     * 
     * @param ids 需要删除的病案主键集合
     * @return 结果
     */
    int deleteBingPatientRecordByIds(Long[] ids);

    /**
     * 删除病案信息
     * 
     * @param id 病案主键
     * @return 结果
     */
    int deleteBingPatientRecordById(Long id);

    /**
     * 查询病案
     *
     * @param id 病案主键
     * @return 病案
     */
    BingPatientRecord selectBingPatientRecordById(Long id);
}
