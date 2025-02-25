package com.bing.bing.service.impl;

import java.util.List;

import com.bing.bing.dto.BatchAddDTO;
import com.bing.common.core.utils.DateUtils;
import com.bing.common.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bing.bing.mapper.BingPatientRecordMapper;
import com.bing.bing.domain.BingPatientRecord;
import com.bing.bing.service.IBingPatientRecordService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author Simeon
 * @date 2025-02-25
 */
@Service
public class BingPatientRecordServiceImpl implements IBingPatientRecordService 
{
    @Autowired
    private BingPatientRecordMapper bingPatientRecordMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public BingPatientRecord selectBingPatientRecordById(Long id)
    {
        return bingPatientRecordMapper.selectBingPatientRecordById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param bingPatientRecord 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<BingPatientRecord> selectBingPatientRecordList(BingPatientRecord bingPatientRecord)
    {
        return bingPatientRecordMapper.selectBingPatientRecordList(bingPatientRecord);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param batchAddDTO 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertBingPatientRecord(BatchAddDTO batchAddDTO)
    {
        batchAddDTO.getData().forEach(e -> {
            e.setCreateBy(SecurityUtils.getUsername());
        });
        return bingPatientRecordMapper.insertBingPatientRecord(batchAddDTO);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param bingPatientRecord 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateBingPatientRecord(BingPatientRecord bingPatientRecord)
    {
        bingPatientRecord.setUpdateTime(DateUtils.getNowDate());
        return bingPatientRecordMapper.updateBingPatientRecord(bingPatientRecord);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteBingPatientRecordByIds(Long[] ids)
    {
        return bingPatientRecordMapper.deleteBingPatientRecordByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteBingPatientRecordById(Long id)
    {
        return bingPatientRecordMapper.deleteBingPatientRecordById(id);
    }
}
