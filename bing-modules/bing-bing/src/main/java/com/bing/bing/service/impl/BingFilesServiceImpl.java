package com.bing.bing.service.impl;

import java.util.List;

import com.bing.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bing.bing.mapper.BingFilesMapper;
import com.bing.bing.domain.BingFiles;
import com.bing.bing.service.IBingFilesService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author Simeon
 * @date 2025-03-04
 */
@Service
public class BingFilesServiceImpl implements IBingFilesService 
{
    @Autowired
    private BingFilesMapper bingFilesMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public BingFiles selectBingFilesById(Long id)
    {
        return bingFilesMapper.selectBingFilesById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param bingFiles 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<BingFiles> selectBingFilesList(BingFiles bingFiles)
    {
        return bingFilesMapper.selectBingFilesList(bingFiles);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param bingFiles 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertBingFiles(BingFiles bingFiles)
    {
        return bingFilesMapper.insertBingFiles(bingFiles);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param bingFiles 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateBingFiles(BingFiles bingFiles)
    {
        bingFiles.setUpdateTime(DateUtils.getNowDate());
        return bingFilesMapper.updateBingFiles(bingFiles);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteBingFilesByIds(Long[] ids)
    {
        return bingFilesMapper.deleteBingFilesByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteBingFilesById(Long id)
    {
        return bingFilesMapper.deleteBingFilesById(id);
    }
}
