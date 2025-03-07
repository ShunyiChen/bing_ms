package com.bing.bing.controller;

import java.util.List;

import com.bing.bing.dto.BatchAddDTO;
import com.bing.common.core.web.controller.BaseController;
import com.bing.common.core.web.domain.AjaxResult;
import com.bing.common.core.web.page.TableDataInfo;
import com.bing.common.log.annotation.Log;
import com.bing.common.log.enums.BusinessType;
import com.bing.common.security.annotation.RequiresPermissions;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bing.bing.domain.BingPatientRecord;
import com.bing.bing.service.IBingPatientRecordService;

/**
 * 【病案管理】Controller
 * 
 * @author Simeon
 * @date 2025-02-25
 */
@RestController
@RequestMapping("/record")
public class BingPatientRecordController extends BaseController
{
    @Autowired
    private IBingPatientRecordService bingPatientRecordService;

    /**
     * 批量新增病案
     */
    @RequiresPermissions("bing:record:add")
    @Log(title = "病案", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BatchAddDTO batchAddDTO) {
        if(batchAddDTO.getData().isEmpty()) {
           return error("病案列表不能为空");
        }
        int count = bingPatientRecordService.checksIfListIsExist(batchAddDTO);
        if(count > 0) {
            return error("病案列表不能重复导入");
        }
        return toAjax(bingPatientRecordService.insertBingPatientRecord(batchAddDTO));
    }

    /**
     * 查询病案列表
     */
    @RequiresPermissions("bing:record:list")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody BingPatientRecord bingPatientRecord)
    {
//        startPage();
        PageHelper.startPage(bingPatientRecord.getPageNum(), bingPatientRecord.getPageSize()).setReasonable(true);
        List<BingPatientRecord> list = bingPatientRecordService.selectBingPatientRecordList(bingPatientRecord);
        return getDataTable(list);
    }

   /**
     * 根据病案号查询病案详细信息
     */
    @RequiresPermissions("bing:record:query")
    @GetMapping(value = "/{medicalRecordNumber}")
    public AjaxResult findByMedicalRecordNumber(@PathVariable("medicalRecordNumber") String medicalRecordNumber) {
        return success(bingPatientRecordService.findByMedicalRecordNumber(medicalRecordNumber));
    }

//    /**
//     * 导出病案列表
//     */
//    @RequiresPermissions("bing:record:export")
//    @Log(title = "病案", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, BingPatientRecord bingPatientRecord)
//    {
//        List<BingPatientRecord> list = bingPatientRecordService.selectBingPatientRecordList(bingPatientRecord);
//        ExcelUtil<BingPatientRecord> util = new ExcelUtil<BingPatientRecord>(BingPatientRecord.class);
//        util.exportExcel(response, list, "病案数据");
//    }
//
    /**
     * 获取病案详细信息
     */
    @RequiresPermissions("bing:record:query")
    @GetMapping(value = "/id/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(bingPatientRecordService.selectBingPatientRecordById(id));
    }
//
//    /**
//     * 新增病案
//     */
//    @RequiresPermissions("bing:record:add")
//    @Log(title = "病案", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@RequestBody BingPatientRecord bingPatientRecord)
//    {
//        return toAjax(bingPatientRecordService.insertBingPatientRecord(bingPatientRecord));
//    }
//
    /**
     * 修改病案
     */
    @RequiresPermissions("bing:record:edit")
    @Log(title = "病案", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    public AjaxResult edit(@RequestBody BingPatientRecord bingPatientRecord)
    {
        return toAjax(bingPatientRecordService.updateBingPatientRecord(bingPatientRecord));
    }
//
//    /**
//     * 删除病案
//     */
//    @RequiresPermissions("bing:record:remove")
//    @Log(title = "病案", businessType = BusinessType.DELETE)
//	@DeleteMapping("/{ids}")
//    public AjaxResult remove(@PathVariable Long[] ids)
//    {
//        return toAjax(bingPatientRecordService.deleteBingPatientRecordByIds(ids));
//    }

    /**
     * 批量更新病案
     */
    @RequiresPermissions("bing:record:edit")
    @PostMapping("/batchUpdate")
    public AjaxResult batchUpdate(@RequestBody BingPatientRecord bingPatientRecord)
    {
        List<BingPatientRecord> list = bingPatientRecordService.selectBingPatientRecordList(bingPatientRecord);

        return success();
    }
}
