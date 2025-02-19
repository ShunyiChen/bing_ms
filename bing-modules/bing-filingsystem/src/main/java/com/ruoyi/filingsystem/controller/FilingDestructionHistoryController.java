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
import com.ruoyi.filingsystem.dto.DestructionHistorySearchDTO;
import com.ruoyi.filingsystem.service.IFilingDestructionHistoryService;
import com.ruoyi.filingsystem.utils.RedisLockUtils;
import com.ruoyi.filingsystem.vo.FilingBoxHistoryVO;
import com.ruoyi.filingsystem.vo.FilingFileHistoryVO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.ruoyi.common.core.constant.CacheConstants.FILE_EXPORT_LOCK_KEY;

@RestController
@RequestMapping("filing/destructionhistory")
public class FilingDestructionHistoryController extends BaseController {
    private final IFilingDestructionHistoryService service;
    private final RedisService redisService;

    @Autowired
    public FilingDestructionHistoryController(IFilingDestructionHistoryService service, RedisService redisService) {
        this.service = service;
        this.redisService = redisService;
    }

    @RequiresPermissions("filingsystem:destructionhistory:query")
    @Log(title = "文件销毁履历-文件检索", businessType = BusinessType.OTHER)
    @PostMapping("/getFileHistory")
    public TableDataInfo getFileHistory(@RequestBody DestructionHistorySearchDTO dto){
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize()).setReasonable(true);
        List<FilingFileHistoryVO> list = service.getFileHistory(dto);
        return getDataTable(list);
    }

    @RequiresPermissions("filingsystem:destructionhistory:query")
    @Log(title = "盒子销毁履历-盒子检索", businessType = BusinessType.OTHER)
    @PostMapping("/getBoxHistory")
    public TableDataInfo getBoxHistory(@RequestBody DestructionHistorySearchDTO dto){
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize()).setReasonable(true);
        return getDataTable(service.getBoxHistory(dto));
    }

    @RequiresPermissions("filingsystem:destructionhistory:export")
    @Log(title = "文件销毁履历-导出", businessType = BusinessType.EXPORT)
    @PostMapping("/exportFileHistory")
    public void exportFileHistory(HttpServletResponse response, DestructionHistorySearchDTO dto) {
        if(dto.getDeptId() == null) {
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
            List<FilingFileHistoryVO> list = service.getFileHistory(dto);
            ExcelUtil<FilingFileHistoryVO> util = new ExcelUtil<>(FilingFileHistoryVO.class);
            util.exportExcel(response, list, "File Destruction History");
        } finally {
            redisLockUtils.unlock(FILE_EXPORT_LOCK_KEY, uuid);
        }
    }

    @RequiresPermissions("filingsystem:destructionhistory:export")
    @Log(title = "盒子销毁履历-导出", businessType = BusinessType.EXPORT)
    @PostMapping("/exportBoxHistory")
    public void exportBoxHistory(HttpServletResponse response, DestructionHistorySearchDTO dto) {
        List<FilingBoxHistoryVO> list = service.getBoxHistory(dto);
        ExcelUtil<FilingBoxHistoryVO> util = new ExcelUtil<>(FilingBoxHistoryVO.class);
        util.exportExcel(response, list, "Box Destruction History");
    }
}
