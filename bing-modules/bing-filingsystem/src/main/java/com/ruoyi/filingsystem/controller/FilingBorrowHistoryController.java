package com.ruoyi.filingsystem.controller;

import cn.hutool.core.util.IdUtil;
import com.github.pagehelper.PageHelper;
import com.ruoyi.common.core.constant.SystemMessageConstants;
import com.ruoyi.common.core.exception.filingsystem.FilingSystemException;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.filingsystem.dto.FetchBorrowHistoryDTO;
import com.ruoyi.filingsystem.service.IFilingBorrowHistoryService;
import com.ruoyi.filingsystem.utils.RedisLockUtils;
import com.ruoyi.filingsystem.vo.FilingBorrowHistoryVO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.ruoyi.common.core.constant.CacheConstants.FILE_EXPORT_LOCK_KEY;

@RestController
@RequestMapping("filing/borrowhistory")
public class FilingBorrowHistoryController extends BaseController {
    private final IFilingBorrowHistoryService filingBorrowHistoryService;
    private final RedisService redisService;

    @Autowired
    public FilingBorrowHistoryController(IFilingBorrowHistoryService filingBorrowHistoryService, RedisService redisService) {
        this.filingBorrowHistoryService = filingBorrowHistoryService;
        this.redisService = redisService;
    }

    @RequiresPermissions("filingsystem:borrowhistory:query")
    @Log(title = "BorrowHistory_Fetch", businessType = BusinessType.OTHER)
    @PostMapping("/fetchBorrowHistory")
    public TableDataInfo fetchBorrowHistory(@RequestBody FetchBorrowHistoryDTO fetchBorrowHistoryDTO) {
        PageHelper.startPage(fetchBorrowHistoryDTO.getPageNum(), fetchBorrowHistoryDTO.getPageSize()).setReasonable(true);
        List<FilingBorrowHistoryVO> result = filingBorrowHistoryService.fetchBorrowHistory(fetchBorrowHistoryDTO);
        return getDataTable(result);
    }

    @RequiresPermissions("filingsystem:borrowhistory:export")
    @Log(title = "BorrowHistory_Export", businessType = BusinessType.EXPORT)
    @PostMapping("/exportSearchData")
    public void exportSearchData(HttpServletResponse response, FetchBorrowHistoryDTO fetchBorrowHistoryDTO) {
        if(fetchBorrowHistoryDTO.getDeptId() == null) {
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
            List<FilingBorrowHistoryVO> list = filingBorrowHistoryService.fetchBorrowHistory(fetchBorrowHistoryDTO);
            ExcelUtil<FilingBorrowHistoryVO> util = new ExcelUtil<>(FilingBorrowHistoryVO.class);
            util.exportExcel(response, list, "Borrow History");
        } finally {
            redisLockUtils.unlock(FILE_EXPORT_LOCK_KEY, uuid);
        }
    }
}
