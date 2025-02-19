package com.ruoyi.filingsystem.controller;

import cn.hutool.core.util.IdUtil;
import com.github.pagehelper.PageHelper;
import com.ruoyi.common.core.constant.SystemMessageConstants;
import com.ruoyi.common.core.exception.filingsystem.FilingSystemException;
import com.ruoyi.common.core.utils.bean.BeanUtils;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.filingsystem.domain.FilingBoxBorrowRecord;
import com.ruoyi.filingsystem.domain.FilingBoxProfile;
import com.ruoyi.filingsystem.dto.*;
import com.ruoyi.filingsystem.service.IFilingBoxBorrowRecordService;
import com.ruoyi.filingsystem.utils.RedisLockUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static com.ruoyi.common.core.constant.CacheConstants.FILE_EXPORT_LOCK_KEY;

/**
 * 盒子借阅记录-整箱借阅Controller
 * 
 * @author victor.xiong
 * @date 2024-11-18
 */
@RestController
@RequestMapping("filing/boxprofile/borrowrecord")
public class FilingBoxBorrowRecordController extends BaseController {
    private final IFilingBoxBorrowRecordService service;
    private final RedisService redisService;

    @Autowired
    public FilingBoxBorrowRecordController(IFilingBoxBorrowRecordService service, RedisService redisService) {
        this.service = service;
        this.redisService = redisService;
    }

    @RequiresPermissions("filingsystem:boxlend:list")
    @PostMapping(value = "/retrieveBoxesOverdueByOneMonth")
    public TableDataInfo retrieveBoxesOverdueByOneMonth(@RequestBody GetBoxesOverdueDTO dto){
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize()).setReasonable(true);
        List<FilingBoxBorrowRecord> list = service.retrieveBoxesOverdueByOneMonth(dto);
        return getDataTable(list);
    }

    @RequiresPermissions("filingsystem:boxlend:export")
    @PostMapping("/exportBoxesOverdueByOneMonth")
    public void exportBoxesOverdueByOneMonth(HttpServletResponse response, GetBoxesOverdueDTO dto){
        String uuid = IdUtil.fastUUID();
        boolean lock;
        RedisLockUtils redisLockUtils = null;
        try {
            redisLockUtils = new RedisLockUtils(redisService.redisTemplate);
            lock = redisLockUtils.lock(FILE_EXPORT_LOCK_KEY, uuid, 15);
            if(!lock) {
                throw new FilingSystemException(SystemMessageConstants.DOWNLOADS_ARE_IN_HIDE_DEMAND_MESSAGE);
            }
            List<FilingBoxBorrowRecord> list = service.retrieveBoxesOverdueByOneMonth(dto);
            ExcelUtil<FilingBoxBorrowRecord> util = new ExcelUtil<>(FilingBoxBorrowRecord.class);
            util.exportExcel(response, list, "Outstanding Boxes", "Staff ID: "+dto.getStaffId()+" Borrower: "+dto.getStaffName());
        } finally {
            redisLockUtils.unlock(FILE_EXPORT_LOCK_KEY, uuid);
        }
    }

    @RequiresPermissions("filingsystem:boxlend:add")
    @PostMapping(value = "/addBoxBorrowRecords")
    public AjaxResult addBoxBorrowRecords(@Validated @RequestBody AddBoxBorrowRecordsDTO dto){
        service.insertBoxBorrowRecords(dto);
        return success();
    }

    @RequiresPermissions("filingsystem:boxlend:query")
    @PostMapping(value = "/searchBoxBorrowRecords")
    public TableDataInfo searchBoxBorrowRecords(@RequestBody SearchBoxBorrowRecordsDTO dto){
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize()).setReasonable(true);
        List<FilingBoxBorrowRecord> list = service.searchBoxBorrowRecords(dto);
        return getDataTable(list);
    }

    @RequiresPermissions("filingsystem:boxlend:edit")
    @PostMapping(value = "/changeChargeCode")
    public AjaxResult changeChargeCode(@RequestBody ChangeChargeCodeDTO dto){
        service.changeChargeCode(dto);
        return success();
    }

    @RequiresPermissions("filingsystem:boxlend:export")
    @PostMapping(value = "/exportBoxBorrowRecords")
    public void exportBoxBorrowRecords(HttpServletResponse response, ExportRecordsDTO dto){
        if(dto.getDeptId() == null) {
            throw new FilingSystemException(SystemMessageConstants.SELECT_A_LOCATION_FOR_EXPORTING);
        }
        // 只导出选中的部分
        dto.initializeMultipleSelection();
        // 根据查询条件导出全部
        if(dto.getMultipleSelection() == null) {
            SearchBoxBorrowRecordsDTO query = new SearchBoxBorrowRecordsDTO();
            BeanUtils.copyBeanProp(query, dto);
            List<FilingBoxBorrowRecord> list = service.searchBoxBorrowRecords(query);
            List<ExportRecordsDTO.Key> keys = list.stream().map(e -> new ExportRecordsDTO.Key(e.getId(), e.getDeptId())).collect(Collectors.toList());
            dto.setMultipleSelection(keys);
        }
        String uuid = IdUtil.fastUUID();
        boolean lock;
        RedisLockUtils redisLockUtils = null;
        try {
            redisLockUtils = new RedisLockUtils(redisService.redisTemplate);
            lock = redisLockUtils.lock(FILE_EXPORT_LOCK_KEY, uuid, 15);
            if(!lock) {
                throw new FilingSystemException(SystemMessageConstants.DOWNLOADS_ARE_IN_HIDE_DEMAND_MESSAGE);
            }
            List<FilingBoxBorrowRecord> list = service.exportBoxBorrowRecords(dto);
            ExcelUtil<FilingBoxBorrowRecord> util = new ExcelUtil<>(FilingBoxBorrowRecord.class);
            util.exportExcel(response, list, "Box Borrow History");
        } finally {
            redisLockUtils.unlock(FILE_EXPORT_LOCK_KEY, uuid);
        }
    }

    @RequiresPermissions("filingsystem:boxlend:edit")
    @PostMapping(value = "/changeBoxBorrowStatus")
    public AjaxResult changeBoxBorrowStatus(@RequestBody ChangeBoxBorrowStatusDTO dto){
        service.changeBoxBorrowStatus(dto);
        return success();
    }

    @PostMapping(value = "/reminder/over1MonthsOutstandingBoxes")
    public AjaxResult over1MonthsOutstandingBoxes(){
        service.over1MonthsOutstandingBoxes();
        return success();
    }
}
