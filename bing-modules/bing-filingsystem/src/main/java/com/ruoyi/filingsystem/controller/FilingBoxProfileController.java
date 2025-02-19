package com.ruoyi.filingsystem.controller;

import cn.hutool.core.util.IdUtil;
import com.github.pagehelper.PageHelper;
import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.common.core.constant.SystemMessageConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.exception.filingsystem.FilingSystemException;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.bean.BeanUtils;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.filingsystem.domain.FilingAttachment;
import com.ruoyi.filingsystem.domain.FilingBoxOperationRecord;
import com.ruoyi.filingsystem.domain.FilingBoxProfile;
import com.ruoyi.filingsystem.dto.*;
import com.ruoyi.filingsystem.service.IFilingBoxProfileService;
import com.ruoyi.filingsystem.utils.RedisLockUtils;
import com.ruoyi.filingsystem.utils.RedisSpinLock;
import com.ruoyi.filingsystem.vo.FilingStaffVO;
import com.ruoyi.system.api.RemoteCDIService;
import com.ruoyi.system.api.model.CDIListEmployee;
import com.ruoyi.system.api.model.LPN;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.ruoyi.common.core.constant.CacheConstants.FILE_EXPORT_LOCK_KEY;

/**
 * 盒子_仅整箱操作Controller
 *
 * @author victor.xiong
 * @date 2024-11-19
 */
@RestController
@RequestMapping("filing/boxprofile")
public class FilingBoxProfileController extends BaseController
{
    private final IFilingBoxProfileService filingBoxProfileService;
    private final RedisService redisService;
    private final RemoteCDIService remoteCDIService;
    private static final String KEY_PREFIX = "filingsystem:box_lockKey_";

    @Autowired
    public FilingBoxProfileController(IFilingBoxProfileService service, RedisService redisService, RemoteCDIService remoteCDIService) {
        this.filingBoxProfileService = service;
        this.redisService = redisService;
        this.remoteCDIService = remoteCDIService;
    }

    @RequiresPermissions("filingsystem:boxprofile:query")
    @PostMapping(value = "/getBoxProfileById")
    public AjaxResult getBoxProfileById(@RequestBody FindBoxProfileDTO dto){
        return success(filingBoxProfileService.findBoxProfileByID(dto));
    }

    @RequiresPermissions("filingsystem:boxprofile:query")
    @PostMapping("/getBoxProfileList")
    public TableDataInfo list(@RequestBody SearchBoxProfileDTO dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize()).setReasonable(true);
        List<FilingBoxProfile> list = filingBoxProfileService.searchBoxProfileList(dto);

        GetAttachmentsDTO gad = new GetAttachmentsDTO();
        List<Long> lstId = new ArrayList<>();
        List<Long> lstDeptId = new ArrayList<>();
        for(FilingBoxProfile box: list) {
            lstId.add(box.getId());
            lstDeptId.add(box.getDeptId());
        }
        gad.setLstId(lstId);
        gad.setLstDeptId(lstDeptId);
        gad.setParams(dto.getParams());
        List<FilingAttachment> attachmentList = filingBoxProfileService.getFilingAttachmentList(gad);
        for(FilingBoxProfile box: list) {
            List<FilingAttachment> approvalFiles = attachmentList.stream().filter(a -> a.getBoxProfileId().intValue() == box.getId().intValue()
                    && a.getDeptId().intValue() == box.getDeptId().intValue()
                    && a.getUsage() == 1).toList();
            box.setApprovalFiles(approvalFiles);

            List<FilingAttachment> fileDetails = attachmentList.stream().filter(a -> a.getBoxProfileId().intValue() == box.getId().intValue()
                    && a.getDeptId().intValue() == box.getDeptId().intValue()
                    && a.getUsage() == 2).toList();
            box.setFileDetails(fileDetails);
        }
        return getDataTable(list);
    }

    @RequiresPermissions("filingsystem:boxprofile:add")
    @Log(title = "box整箱添加_管理人员操作", businessType = BusinessType.INSERT)
    @PostMapping("/addBox")
    public AjaxResult addBox(@Validated @RequestBody AddBoxProfileDTO dto) {
        RedisSpinLock redisSpinLock = new RedisSpinLock(redisService.redisTemplate);
        String key = KEY_PREFIX + dto.getAreaLocation();
        String value = Thread.currentThread().getId() + ""; // 以当前线程ID作为唯一标识
        long expireTime = 4; // 锁过期时间4秒   单次添加 4秒足够完成了
        long timeout = 5000;  // 尝试获取锁的最大等待时间5秒
        if (redisSpinLock.tryLock(key, value, expireTime, timeout)) {
            try {
                AddBoxReturnDTO addBoxReturnDTO;
                try {
                    addBoxReturnDTO = filingBoxProfileService.insertFilingBoxProfile(dto);
                } catch (Exception e) {
                    return error("An exception occurred while adding the box.\n"+e.getMessage());
                }
                return success(addBoxReturnDTO);
            } finally {
                redisSpinLock.unlock(key, value);
            }
        } else {
            logger.info("获取锁失败");
            throw new FilingSystemException(SystemMessageConstants.FAILED_TO_OBTAIN_LOCK_MESSAGE);
        }
    }

    /**
     * 修改盒子_仅整箱操作
     */
    @RequiresPermissions("filingsystem:boxprofile:edit")
    @Log(title = "修改盒子_仅整箱操作", businessType = BusinessType.UPDATE)
    @PostMapping("/updateBox")
    public AjaxResult updateBox(@Validated @RequestBody UpdateBoxProfileDTO dto) {
        filingBoxProfileService.updateFilingBoxProfile(dto);
        FindBoxProfileDTO findBoxProfileDTO = new FindBoxProfileDTO();
        findBoxProfileDTO.setId(dto.getId());
        findBoxProfileDTO.setDeptId(dto.getDeptId());
        return success(filingBoxProfileService.findBoxProfileByID(findBoxProfileDTO));
    }

    @RequiresPermissions("filingsystem:boxprofile:export")
    @PostMapping("/exportBoxProfileList")
    public void exportBoxProfileList(HttpServletResponse response, ExportRecordsDTO dto) {
        if(dto.getDeptId() == null) {
            throw new FilingSystemException(SystemMessageConstants.SELECT_A_LOCATION_FOR_EXPORTING);
        }
        // 只导出选中的部分
        dto.initializeMultipleSelection();
        // 根据查询条件导出全部
        if(dto.getMultipleSelection() == null) {
            SearchBoxProfileDTO query = new SearchBoxProfileDTO();
            BeanUtils.copyBeanProp(query, dto);
            List<FilingBoxProfile> list = filingBoxProfileService.searchBoxProfileList(query);
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
            List<FilingBoxProfile> list = filingBoxProfileService.exportSelectedData(dto);
            ExcelUtil<FilingBoxProfile> util = new ExcelUtil<>(FilingBoxProfile.class);
            util.exportExcel(response, list, "Box Profile");
        } finally {
            redisLockUtils.unlock(FILE_EXPORT_LOCK_KEY, uuid);
        }
    }

    @RequiresPermissions("filingsystem:boxprofile:edit")
    @PostMapping("/destroyBoxes")
    public AjaxResult destroyBoxes(@RequestBody DestroyBoxProfileDTO[] params) {
        return success(filingBoxProfileService.destroyBoxes(params));
    }

    @RequiresPermissions("filingsystem:boxprofile:edit")
    @PostMapping("/permOutBoxes")
    public AjaxResult permOutBoxes(@RequestBody PermOutBoxProfileDTO[] params) {
        return success(filingBoxProfileService.permOutBoxes(params));
    }

    @RequiresPermissions("filingsystem:boxprofile:edit")
    @PostMapping("/editCharge")
    public AjaxResult editCharge(@RequestBody EditChargeDTO[] params) {
        return success(filingBoxProfileService.editCharge(params));
    }

    @RequiresPermissions("filingsystem:boxprofile:edit")
    @PostMapping("/saveAttachments")
    public AjaxResult saveAttachments(@RequestBody SaveAttachmentsDTO dto) {

        filingBoxProfileService.saveAttachmentFiles(dto);

        GetAttachmentsDTO gad = new GetAttachmentsDTO();
        List<Long> lstId = new ArrayList<>();
        List<Long> lstDeptId = new ArrayList<>();
        dto.getKeys().forEach(k -> {
            lstId.add(k.getId());
            lstDeptId.add(k.getDeptId());
        });
        gad.setLstId(lstId);
        gad.setLstDeptId(lstDeptId);
        gad.setParams(dto.getParams());
        return success(filingBoxProfileService.getFilingAttachmentList(gad));
    }

    @RequiresPermissions("filingsystem:boxprofile:edit")
    @PostMapping("/removeAttachment")
    public AjaxResult removeApprovalFiles(@RequestBody RemoveAttachmentDTO dto) {
        filingBoxProfileService.removeApprovalFiles(dto);
        return success();
    }

    @RequiresPermissions("filingsystem:boxedithistory:query")
    @PostMapping("/searchBoxEditHistoryList")
    public TableDataInfo searchBoxEditHistoryList(@RequestBody SearchBoxProfileDTO dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize()).setReasonable(true);
        List<FilingBoxOperationRecord> list = filingBoxProfileService.searchBoxEditHistoryList(dto);

        GetAttachmentsDTO gad = new GetAttachmentsDTO();
        List<Long> lstId = new ArrayList<>();
        List<Long> lstDeptId = new ArrayList<>();
        for(FilingBoxOperationRecord box: list) {
            lstId.add(box.getBoxId());
            lstDeptId.add(box.getDeptId());
        }
        gad.setLstId(lstId);
        gad.setLstDeptId(lstDeptId);
        gad.setParams(dto.getParams());
        return getDataTable(list);
    }

    @RequiresPermissions("filingsystem:boxedithistory:export")
    @PostMapping("/exportBoxEditHistory")
    public void exportSearchEditHistory(HttpServletResponse response, ExportRecordsDTO dto) {
        if(dto.getDeptId() == null) {
            throw new FilingSystemException(SystemMessageConstants.SELECT_A_LOCATION_FOR_EXPORTING);
        }
        // 根据导出选中的
        dto.initializeMultipleSelection();
        // 根据查询条件导出全部
        if(dto.getMultipleSelection() == null) {
            SearchBoxProfileDTO query = new SearchBoxProfileDTO();
            BeanUtils.copyBeanProp(query, dto);
            List<FilingBoxOperationRecord> list = filingBoxProfileService.searchBoxEditHistoryList(query);
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
            List<FilingBoxOperationRecord> list = filingBoxProfileService.exportBoxEditHistoryList(dto);
            ExcelUtil<FilingBoxOperationRecord> util = new ExcelUtil<>(FilingBoxOperationRecord.class);
            util.exportExcel(response, list, "Box Edit History");
        } finally {
            redisLockUtils.unlock(FILE_EXPORT_LOCK_KEY, uuid);
        }
    }

    @PostMapping("/fetchStaffByID")
    public AjaxResult fetchStaffByID(@RequestBody FetchStaffIDDTO dto){
        if(StringUtils.isEmpty(dto.getLpn()) || dto.getLpn().length() > 20) {
            return error("Oops! It looks like the `Staff ID` parameter is not correct. Can you please check it again?");
        }
        R<CDIListEmployee> r = remoteCDIService.getEmployeeByLPN(new LPN(dto.getLpn()), SecurityConstants.INNER);
        if(r.getData() == null || r.getData().getResponse().length == 0) {
            return error("The Staff ID you entered is not in the database.");
        }
        String staffName = r.getData().getResponse()[0].getFirst_Name_1() + " " + r.getData().getResponse()[0].getLast_Name_1();
        String staffEmail = r.getData().getResponse()[0].getInternet_Addr();
        String lpn = r.getData().getResponse()[0].getLPN();
        return success(new FilingStaffVO(staffName, staffEmail, lpn));
    }
}
