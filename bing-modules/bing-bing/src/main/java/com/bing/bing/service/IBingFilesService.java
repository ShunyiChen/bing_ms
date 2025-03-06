package com.bing.bing.service;

import java.util.List;
import com.bing.bing.domain.BingFiles;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author Simeon
 * @date 2025-03-04
 */
public interface IBingFilesService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public BingFiles selectBingFilesById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param bingFiles 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<BingFiles> selectBingFilesList(BingFiles bingFiles);

    /**
     * 新增【请填写功能名称】
     * 
     * @param bingFiles 【请填写功能名称】
     * @return 结果
     */
    public int insertBingFiles(BingFiles bingFiles);

    /**
     * 修改【请填写功能名称】
     * 
     * @param bingFiles 【请填写功能名称】
     * @return 结果
     */
    public int updateBingFiles(BingFiles bingFiles);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteBingFilesByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteBingFilesById(Long id);
}
