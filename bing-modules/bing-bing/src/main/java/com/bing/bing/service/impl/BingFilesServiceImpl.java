package com.bing.bing.service.impl;

import java.util.List;

import com.bing.common.core.utils.DateUtils;
import com.bing.common.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bing.bing.mapper.BingFilesMapper;
import com.bing.bing.domain.BingFiles;
import com.bing.bing.service.IBingFilesService;

/**
 * 分类材料Service业务层处理
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
     * 查询分类材料
     * 
     * @param id 分类材料主键
     * @return 分类材料
     */
    @Override
    public BingFiles selectBingFilesById(Long id)
    {
        return bingFilesMapper.selectBingFilesById(id);
    }

    /**
     * 查询分类材料列表
     * 
     * @param bingFiles 分类材料
     * @return 分类材料
     */
    @Override
    public List<BingFiles> selectBingFilesList(BingFiles bingFiles) {
        return bingFilesMapper.selectBingFilesList(bingFiles);
    }

    /**
     * 
     * @param recordIdList
     * @return
     */
    @Override
    public List<BingFiles> selectBingFilesByRecordIds(List<Long> recordIdList) {
        return bingFilesMapper.selectBingFilesByRecordIds(recordIdList);
    }

    /**
     * 新增分类材料
     * 
     * @param bingFiles 分类材料
     * @return 结果
     */
    @Override
    public int insertBingFiles(BingFiles bingFiles)
    {
        return bingFilesMapper.insertBingFiles(bingFiles);
    }

    /**
     * 修改分类材料
     * 
     * @param bingFiles 分类材料
     * @return 结果
     */
    @Override
    public int updateBingFiles(BingFiles bingFiles)
    {
        bingFiles.setUpdateBy(SecurityUtils.getUsername());
        return bingFilesMapper.updateBingFiles(bingFiles);
    }

    /**
     * 批量删除分类材料
     * 
     * @param ids 需要删除的分类材料主键
     * @return 结果
     */
    @Override
    public int deleteBingFilesByIds(Long[] ids)
    {
        return bingFilesMapper.deleteBingFilesByIds(ids);
    }

    /**
     * 删除分类材料信息
     * 
     * @param id 分类材料主键
     * @return 结果
     */
    @Override
    public int deleteBingFilesById(Long id)
    {
        return bingFilesMapper.deleteBingFilesById(id);
    }
}
