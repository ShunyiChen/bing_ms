package com.bing.bing.service.impl;

import com.bing.bing.domain.BingPatientRecord;
import com.bing.bing.dto.BatchAddDTO;
import com.bing.bing.mapper.BingPatientRecordMapper;
import com.bing.bing.service.IBingPatientRecordService;
import com.bing.common.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 病案Service业务层处理
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
     * 查询病案
     * 
     * @param id 病案主键
     * @return 病案
     */
    @Override
    public BingPatientRecord selectBingPatientRecordById(Long id)
    {
        return bingPatientRecordMapper.selectBingPatientRecordById(id);
    }

    @Override
    public BingPatientRecord findByMedicalRecordNumber(String medicalRecordNumber) {
        return bingPatientRecordMapper.findByMedicalRecordNumber(medicalRecordNumber);
    }

    /**
     * 查询病案列表
     * 
     * @param bingPatientRecord 病案
     * @return 病案
     */
    @Override
    public List<BingPatientRecord> selectBingPatientRecordList(BingPatientRecord bingPatientRecord) {
        return bingPatientRecordMapper.selectBingPatientRecordList(bingPatientRecord);
    }

    @Override
    public int checksIfListIsExist(BatchAddDTO batchAddDTO) {
        List<String> list = batchAddDTO.getData().stream().map(BingPatientRecord::getMedicalRecordNumber).toList();
        return bingPatientRecordMapper.checksIfListIsExist(list);
    }

    /**
     * 新增病案
     * 
     * @param batchAddDTO 病案
     * @return 结果
     */
    @Override
    public int insertBingPatientRecord(BatchAddDTO batchAddDTO) {
        batchAddDTO.getData().forEach(e -> {
            e.setCreateBy(SecurityUtils.getUsername());
        });
        return bingPatientRecordMapper.insertBingPatientRecord(batchAddDTO);
    }

    /**
     * 修改病案
     * 
     * @param bingPatientRecord 病案
     * @return 结果
     */
    @Override
    public int updateBingPatientRecord(BingPatientRecord bingPatientRecord) {
        return bingPatientRecordMapper.updateBingPatientRecord(bingPatientRecord);
    }

    /**
     * 批量删除病案
     * 
     * @param ids 需要删除的病案主键
     * @return 结果
     */
    @Override
    public int deleteBingPatientRecordByIds(Long[] ids) {
        return bingPatientRecordMapper.deleteBingPatientRecordByIds(ids);
    }

    /**
     * 删除病案信息
     * 
     * @param id 病案主键
     * @return 结果
     */
    @Override
    public int deleteBingPatientRecordById(Long id)
    {
        return bingPatientRecordMapper.deleteBingPatientRecordById(id);
    }
}
