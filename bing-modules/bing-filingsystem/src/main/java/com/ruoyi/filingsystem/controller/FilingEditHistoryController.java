package com.ruoyi.filingsystem.controller;

import cn.hutool.core.util.IdUtil;
import com.github.pagehelper.PageHelper;
import com.ruoyi.common.core.constant.SystemMessageConstants;
import com.ruoyi.common.core.exception.filingsystem.FilingSystemException;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.filingsystem.dto.FetchEditHistoryDTO;
import com.ruoyi.filingsystem.dto.RecoverDTO;
import com.ruoyi.filingsystem.service.IFilingEditHistoryService;
import com.ruoyi.filingsystem.utils.RedisLockUtils;
import com.ruoyi.filingsystem.vo.FilingEditHistoryVO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ruoyi.common.core.constant.CacheConstants.FILE_EXPORT_LOCK_KEY;

@RestController
@RequestMapping("filing/edithistory")
public class FilingEditHistoryController extends BaseController {
    private final IFilingEditHistoryService filingEditHistoryService;
    private final RedisService redisService;
    @Autowired
    public FilingEditHistoryController(IFilingEditHistoryService filingEditHistoryService, RedisService redisService) {
        this.filingEditHistoryService = filingEditHistoryService;
        this.redisService = redisService;
    }

    @RequiresPermissions("filingsystem:edithistory:query")
    @Log(title = "EditHistory_Fetch", businessType = BusinessType.OTHER)
    @PostMapping("/fetchEditHistory")
    public TableDataInfo fetchEditHistory(@RequestBody FetchEditHistoryDTO fetchEditHistoryDTO) {
        PageHelper.startPage(fetchEditHistoryDTO.getPageNum(), fetchEditHistoryDTO.getPageSize()).setReasonable(true);
        List<FilingEditHistoryVO> result = filingEditHistoryService.fetchEditHistory(fetchEditHistoryDTO);
        return getDataTable(result);
    }

    @RequiresPermissions("filingsystem:edithistory:export")
    @Log(title = "EditHistory_Export", businessType = BusinessType.EXPORT)
    @PostMapping("/exportSearchData")
    public void exportSearchData(HttpServletResponse response, FetchEditHistoryDTO fetchEditHistoryDTO) {
        if(fetchEditHistoryDTO.getDeptId() == null) {
            throw new FilingSystemException(SystemMessageConstants.SELECT_A_LOCATION_FOR_EXPORTING);
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
            List<FilingEditHistoryVO> list = filingEditHistoryService.exportEditHistory(fetchEditHistoryDTO);
            ExcelUtil<FilingEditHistoryVO> util = new ExcelUtil<>(FilingEditHistoryVO.class);
            util.exportExcel(response, list, "Edit History");
        } finally {
            redisLockUtils.unlock(FILE_EXPORT_LOCK_KEY, uuid);
        }
    }

    @RequiresPermissions("filingsystem:edithistory:query")
    @PostMapping(value = "/{HistID}")
    public AjaxResult getHistoryByHistID(@PathVariable("HistID") Long HistID){
        return success(filingEditHistoryService.getHistoryByHistID(HistID));
    }

    @RequiresPermissions("filingsystem:edithistory:recover")
    @PostMapping(value = "/recover")
    public AjaxResult recover(@RequestBody RecoverDTO recoverDTO){
        return toAjax(filingEditHistoryService.recover(recoverDTO));
    }
}
