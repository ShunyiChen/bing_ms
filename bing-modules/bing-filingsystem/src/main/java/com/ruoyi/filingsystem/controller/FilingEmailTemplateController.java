package com.ruoyi.filingsystem.controller;

import java.util.List;

import com.ruoyi.filingsystem.domain.FilingEmailTemplate;
import com.ruoyi.filingsystem.service.IFilingEmailTemplateService;
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
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 邮件模板的_管理人员操作Controller
 * 
 * @author ruoyi
 * @date 2024-03-25
 */
@RestController
@RequestMapping("filing/template")
public class FilingEmailTemplateController extends BaseController
{
    @Autowired
    private IFilingEmailTemplateService filingEmailTemplateService;

    /**
     * 查询邮件模板的_管理人员操作列表
     */
    @RequiresPermissions("filingsystem:template:list")
    @GetMapping("/list")
    public TableDataInfo list(FilingEmailTemplate filingEmailTemplate)
    {
        startPage();
        List<FilingEmailTemplate> list = filingEmailTemplateService.selectFilingEmailTemplateList(filingEmailTemplate);
        return getDataTable(list);
    }

    /**
     * 导出邮件模板的_管理人员操作列表
     */
    @RequiresPermissions("filingsystem:template:export")
    @Log(title = "邮件模板的_管理人员操作", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FilingEmailTemplate filingEmailTemplate)
    {
        List<FilingEmailTemplate> list = filingEmailTemplateService.selectFilingEmailTemplateList(filingEmailTemplate);
        ExcelUtil<FilingEmailTemplate> util = new ExcelUtil<FilingEmailTemplate>(FilingEmailTemplate.class);
        util.exportExcel(response, list, "邮件模板的_管理人员操作数据");
    }

    /**
     * 获取邮件模板的_管理人员操作详细信息
     */
    @RequiresPermissions("filingsystem:template:query")
    @GetMapping(value = "/{templateID}")
    public AjaxResult getInfo(@PathVariable("templateID") Long templateID)
    {
        return success(filingEmailTemplateService.selectFilingEmailTemplateByTemplateID(templateID));
    }

    /**
     * 新增邮件模板的_管理人员操作
     */
    @RequiresPermissions("filingsystem:template:add")
    @Log(title = "邮件模板的_管理人员操作", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FilingEmailTemplate filingEmailTemplate)
    {
        return toAjax(filingEmailTemplateService.insertFilingEmailTemplate(filingEmailTemplate));
    }

    /**
     * 修改邮件模板的_管理人员操作
     */
    @RequiresPermissions("filingsystem:template:edit")
    @Log(title = "邮件模板的_管理人员操作", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FilingEmailTemplate filingEmailTemplate)
    {
        return toAjax(filingEmailTemplateService.updateFilingEmailTemplate(filingEmailTemplate));
    }

    /**
     * 删除邮件模板的_管理人员操作
     */
    @RequiresPermissions("filingsystem:template:remove")
    @Log(title = "邮件模板的_管理人员操作", businessType = BusinessType.DELETE)
	@DeleteMapping("/{templateIDs}")
    public AjaxResult remove(@PathVariable Long[] templateIDs)
    {
        return toAjax(filingEmailTemplateService.deleteFilingEmailTemplateByTemplateIDs(templateIDs));
    }
}
