package com.bing.bing.controller;

import java.util.List;

import com.bing.bing.domain.BingFiles;
import com.bing.bing.dto.AddFileDTO;
import com.bing.bing.service.IBingFilesService;
import com.bing.common.core.utils.poi.ExcelUtil;
import com.bing.common.core.web.controller.BaseController;
import com.bing.common.core.web.domain.AjaxResult;
import com.bing.common.core.web.page.TableDataInfo;
import com.bing.common.log.annotation.Log;
import com.bing.common.log.enums.BusinessType;
import com.bing.common.security.annotation.RequiresPermissions;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 【病案材料】Controller
 * 
 * @author Simeon
 * @date 2025-03-04
 */
@RestController
@RequestMapping("/files")
public class BingFilesController extends BaseController
{
    @Autowired
    private IBingFilesService bingFilesService;

    /**
     * 查询【病案材料】列表
     */
    @RequiresPermissions("bing:files:list")
    @GetMapping("/list")
    public TableDataInfo list(BingFiles bingFiles)
    {
        startPage();
        List<BingFiles> list = bingFilesService.selectBingFilesList(bingFiles);
        return getDataTable(list);
    }

    /**
     * 根据病案ID获取文件列表
     *
     * @param param
     * @return
     */
    @RequiresPermissions("bing:files:list")
    @PostMapping("/findFiles")
    public List<BingFiles> findFiles(@RequestBody BingFiles param) {
        return bingFilesService.selectBingFilesList(param);
    }

    /**
     * 导出【病案材料】列表
     */
    @RequiresPermissions("bing:files:export")
    @Log(title = "【病案材料】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BingFiles bingFiles)
    {
        List<BingFiles> list = bingFilesService.selectBingFilesList(bingFiles);
        ExcelUtil<BingFiles> util = new ExcelUtil<BingFiles>(BingFiles.class);
        util.exportExcel(response, list, "【病案材料】数据");
    }

    /**
     * 获取【病案材料】详细信息
     */
    @RequiresPermissions("bing:files:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(bingFilesService.selectBingFilesById(id));
    }

    /**
     * 新增【病案材料】
     */
    @RequiresPermissions("bing:files:add")
    @Log(title = "【病案材料】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BingFiles bingFiles) {
        bingFilesService.insertBingFiles(bingFiles);
        return success(new AddFileDTO(bingFiles.getId()));
    }

    /**
     * 修改【病案材料】
     */
    @RequiresPermissions("bing:files:edit")
    @Log(title = "【病案材料】", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    public AjaxResult edit(@RequestBody BingFiles bingFiles)
    {
        return toAjax(bingFilesService.updateBingFiles(bingFiles));
    }

    /**
     * 删除【病案材料】
     */
    @RequiresPermissions("bing:files:remove")
    @Log(title = "【病案材料】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bingFilesService.deleteBingFilesByIds(ids));
    }
}
