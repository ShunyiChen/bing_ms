package com.bing.bing.service;

import java.util.List;
import com.bing.bing.domain.BingPatientRecord;
import com.bing.bing.dto.BatchAddDTO;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author Simeon
 * @date 2025-02-25
 */
public interface IBingPatientRecordService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public BingPatientRecord selectBingPatientRecordById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param bingPatientRecord 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<BingPatientRecord> selectBingPatientRecordList(BingPatientRecord bingPatientRecord);

    /**
     * 新增【批量添加病案】
     * 
     * @param batchAddDTO 【要添加的病案列表】
     * @return 结果
     */
    public int insertBingPatientRecord(BatchAddDTO batchAddDTO);

    /**
     * 修改【请填写功能名称】
     * 
     * @param bingPatientRecord 【请填写功能名称】
     * @return 结果
     */
    public int updateBingPatientRecord(BingPatientRecord bingPatientRecord);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteBingPatientRecordByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteBingPatientRecordById(Long id);
}
