package com.bing.bing.mapper;

import java.util.List;
import com.bing.bing.domain.BingFiles;

/**
 * 分类材料Mapper接口
 * 
 * @author Simeon
 * @date 2025-03-04
 */
public interface BingFilesMapper 
{
    /**
     * 查询分类材料
     * 
     * @param id 分类材料主键
     * @return 分类材料
     */
    public BingFiles selectBingFilesById(Long id);

    /**
     * 查询分类材料列表
     * 
     * @param bingFiles 分类材料
     * @return 分类材料集合
     */
    List<BingFiles> selectBingFilesList(BingFiles bingFiles);

    /**
     * 查询分类材料列表
     *
     * @param list
     * @return
     */
    List<BingFiles> selectBingFilesByRecordIds(List<Long> list);
    
    /**
     * 新增分类材料
     * 
     * @param bingFiles 分类材料
     * @return 结果
     */
    public int insertBingFiles(BingFiles bingFiles);

    /**
     * 修改分类材料
     * 
     * @param bingFiles 分类材料
     * @return 结果
     */
    public int updateBingFiles(BingFiles bingFiles);

    /**
     * 删除分类材料
     * 
     * @param id 分类材料主键
     * @return 结果
     */
    public int deleteBingFilesById(Long id);

    /**
     * 批量删除分类材料
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBingFilesByIds(Long[] ids);
}
