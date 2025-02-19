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
import com.ruoyi.filingsystem.dto.FetchOnLoanRecordDTO;
import com.ruoyi.filingsystem.service.IFilingOnLoanRecordService;
import com.ruoyi.filingsystem.utils.RedisLockUtils;
import com.ruoyi.filingsystem.vo.FilingOnLoanRecordVO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.ruoyi.common.core.constant.CacheConstants.FILE_EXPORT_LOCK_KEY;

@RestController
@RequestMapping("filing/onloanrecord")
public class FilingOnLoanRecordController extends BaseController {
    private final IFilingOnLoanRecordService filingOnLoanRecordService;
    private final RedisService redisService;
    @Autowired
    public FilingOnLoanRecordController(IFilingOnLoanRecordService filingOnLoanRecordService, RedisService redisService) {
        this.filingOnLoanRecordService = filingOnLoanRecordService;
        this.redisService = redisService;
    }

    @RequiresPermissions("filingsystem:onloanrecord:query")
    @Log(title = "OnLoanRecord_Fetch", businessType = BusinessType.OTHER)
    @PostMapping("/fetchOnLoanRecord")
    public TableDataInfo fetchOnLoanRecord(@RequestBody FetchOnLoanRecordDTO fetchOnLoanRecordDto) {
        PageHelper.startPage(fetchOnLoanRecordDto.getPageNum(), fetchOnLoanRecordDto.getPageSize()).setReasonable(true);
        List<FilingOnLoanRecordVO> result = filingOnLoanRecordService.fetchOnLoanRecordList(fetchOnLoanRecordDto);
        return getDataTable(result);
    }

    @RequiresPermissions("filingsystem:onloanrecord:export")
    @Log(title = "OnLoanRecord_Export", businessType = BusinessType.EXPORT)
    @PostMapping("/exportSearchData")
    public void exportSearchData(HttpServletResponse response, FetchOnLoanRecordDTO fetchOnLoanRecordDto) {
        if(fetchOnLoanRecordDto.getDeptId() == null) {
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
            List<FilingOnLoanRecordVO> list = filingOnLoanRecordService.fetchOnLoanRecordList(fetchOnLoanRecordDto);
            ExcelUtil<FilingOnLoanRecordVO> util = new ExcelUtil<>(FilingOnLoanRecordVO.class);
            util.exportExcel(response, list, "On Loan Record");
        } finally {
            redisLockUtils.unlock(FILE_EXPORT_LOCK_KEY, uuid);
        }
    }
}
