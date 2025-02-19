package com.ruoyi.filingsystem.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.filingsystem.domain.FilingFileOperationRecord;
import com.ruoyi.filingsystem.service.IFilingFileOperationRecordService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用于记录文档操作历史的_管理人员查询用，自动录入的Controller
 * 
 * @author ruoyi
 * @date 2024-03-25
 */
@RestController
@RequestMapping("filing/operation")
public class FilingFileOperationRecordController extends BaseController
{
    @Autowired
    private IFilingFileOperationRecordService filingFileOperationRecordService;

    /**
     * 查询用于记录文档操作历史的_管理人员查询用，自动录入的列表
     */
    @RequiresPermissions("filingsystem:operation:list")
    @GetMapping("/list")
    public TableDataInfo list(FilingFileOperationRecord filingFileOperationRecord)
    {
        startPage();
        List<FilingFileOperationRecord> list = filingFileOperationRecordService.selectFilingFileOperationRecordList(filingFileOperationRecord);
        return getDataTable(list);
    }

    /**
     * 导出用于记录文档操作历史的_管理人员查询用，自动录入的列表
     */
    @RequiresPermissions("filingsystem:operation:export")
    @Log(title = "用于记录文档操作历史的_管理人员查询用，自动录入的", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FilingFileOperationRecord filingFileOperationRecord)
    {
        List<FilingFileOperationRecord> list = filingFileOperationRecordService.selectFilingFileOperationRecordList(filingFileOperationRecord);
        ExcelUtil<FilingFileOperationRecord> util = new ExcelUtil<FilingFileOperationRecord>(FilingFileOperationRecord.class);
        util.exportExcel(response, list, "用于记录文档操作历史的_管理人员查询用，自动录入的数据");
    }

    /**
     * 获取用于记录文档操作历史的_管理人员查询用，自动录入的详细信息
     */
    @RequiresPermissions("filingsystem:operation:query")
    @GetMapping(value = "/{recordID}")
    public AjaxResult getInfo(@PathVariable("recordID") Long recordID)
    {
        return success(filingFileOperationRecordService.selectFilingFileOperationRecordByRecordID(recordID));
    }

    /**
     * 新增用于记录文档操作历史的_管理人员查询用，自动录入的
     */
//    @RequiresPermissions("filingsystem:operation:add")
    @Log(title = "用于记录文档操作历史的_管理人员查询用，自动录入的", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FilingFileOperationRecord filingFileOperationRecord)
    {
        return toAjax(filingFileOperationRecordService.insertFilingFileOperationRecord(filingFileOperationRecord));
    }

    /**
     * 修改用于记录文档操作历史的_管理人员查询用，自动录入的
     */
    @RequiresPermissions("filingsystem:operation:edit")
    @Log(title = "用于记录文档操作历史的_管理人员查询用，自动录入的", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FilingFileOperationRecord filingFileOperationRecord)
    {
        return toAjax(filingFileOperationRecordService.updateFilingFileOperationRecord(filingFileOperationRecord));
    }

    /**
     * 删除用于记录文档操作历史的_管理人员查询用，自动录入的
     */
    @RequiresPermissions("filingsystem:operation:remove")
    @Log(title = "用于记录文档操作历史的_管理人员查询用，自动录入的", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIDs}")
    public AjaxResult remove(@PathVariable Long[] recordIDs)
    {
        return toAjax(filingFileOperationRecordService.deleteFilingFileOperationRecordByRecordIDs(recordIDs));
    }
}
