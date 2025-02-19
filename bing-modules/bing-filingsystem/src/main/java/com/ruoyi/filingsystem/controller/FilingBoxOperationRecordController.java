package com.ruoyi.filingsystem.controller;

import java.util.List;

import jakarta.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageHelper;
import com.ruoyi.filingsystem.dto.FilingBoxSearchOperationRecordDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.filingsystem.domain.FilingBoxOperationRecord;
import com.ruoyi.filingsystem.service.IFilingBoxOperationRecordService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 用于记录盒子操作历史的,整箱操作用Controller
 * 
 * @author victor.xiong
 * @date 2024-11-18
 */
@RestController
@RequestMapping("filing/boxOperation")
public class FilingBoxOperationRecordController extends BaseController
{
    @Autowired
    private IFilingBoxOperationRecordService filingBoxOperationRecordService;





    @RequiresPermissions("filingsystem:operation:query")
    @Log(title = "查询box操作记录-二期", businessType = BusinessType.OTHER)
    @PostMapping("/getOperationList")
    public TableDataInfo getOperationList(@RequestBody FilingBoxSearchOperationRecordDto filingBoxSearchOperationRecordDto)
    {
        PageHelper.startPage(filingBoxSearchOperationRecordDto.getPageNum(), filingBoxSearchOperationRecordDto.getPageSize()).setReasonable(true);
        List<FilingBoxOperationRecord> list = filingBoxOperationRecordService.selectList(filingBoxSearchOperationRecordDto);
         return getDataTable(list);
    }


    @RequiresPermissions("filingsystem:operation:export")
    @Log(title = "导出box操作记录-二期", businessType = BusinessType.EXPORT)
    @PostMapping("/exportBoxOperation")
    public void exportBoxOperation(HttpServletResponse response, FilingBoxSearchOperationRecordDto filingBoxSearchOperationRecordDto)
    {
        List<FilingBoxOperationRecord> list = filingBoxOperationRecordService.selectFilingFileOperationRecordList(filingBoxSearchOperationRecordDto);

        ExcelUtil<FilingBoxOperationRecord> util = new ExcelUtil<>(FilingBoxOperationRecord.class);
        util.exportExcel(response, list, "export");
    }

}
