package com.ruoyi.filingsystem.controller;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.IdUtil;
import com.github.pagehelper.PageHelper;
import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.common.core.constant.SystemMessageConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.exception.filingsystem.FilingSystemException;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.filingsystem.domain.FilingFileProfile;
import com.ruoyi.filingsystem.dto.*;
import com.ruoyi.filingsystem.service.IFilingFileProfileService;
import com.ruoyi.filingsystem.utils.RedisLockUtils;
import com.ruoyi.filingsystem.utils.RedisSpinLock;
import com.ruoyi.filingsystem.vo.FilingEngagementVO;
import com.ruoyi.filingsystem.vo.FilingExportBarcodeVO;
import com.ruoyi.filingsystem.vo.FilingStaffVO;
import com.ruoyi.system.api.RemoteCDIService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ruoyi.common.core.constant.CacheConstants.FILE_EXPORT_LOCK_KEY;

/**
 * 文档_管理人员操作Controller
 * 
 * @author Simeon
 *
 * @date 2024-03-25
 */
@RestController
@RequestMapping("filing/profile")
public class FilingFileProfileController extends BaseController
{
    private final IFilingFileProfileService service;
    private final RedisService redisService;
    private final RemoteCDIService remoteCDIService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // 因为Spring在接受前台传入的数组时，就会出现256的IndexOutOfBoundsException异常，这里设置成最大10000长度
        binder.setAutoGrowCollectionLimit(10000);
    }

    @Autowired
    public FilingFileProfileController(IFilingFileProfileService service, RedisService redisService, RemoteCDIService remoteCDIService) {
        this.service = service;
        this.redisService = redisService;
        this.remoteCDIService = remoteCDIService;
    }

    @PostMapping(value = "/checkBoxNumberExists")
    public AjaxResult checkBoxNumberExists(@RequestBody BoxNumberExistDTO dto){
        return success(service.checkBoxNumberExists(dto));
    }

    @RequiresPermissions("filingsystem:profile:move")
    @PostMapping(value = "/moveToLocation")
    public AjaxResult moveToLocation(@RequestBody MoveToLocationDTO dto){
        return success(service.moveToLocation(dto));
    }

    @RequiresPermissions("filingsystem:profile:changetype")
    @PostMapping(value = "/changeType")
    public AjaxResult changeType(@RequestBody ChangeTypeDTO dto){
        return success(service.changeType(dto));
    }

    @RequiresPermissions("filingsystem:profile:changeDPN")
    @PostMapping(value = "/changeDPN")
    public AjaxResult changeDPN(@RequestBody ChangeDPNDTO dto){
        return success(service.changeDPN(dto));
    }

    @PostMapping(value = "/{fileID}")
    public AjaxResult getProfile(@PathVariable("fileID") String fileID){
        return success(service.selectFilingFileProfileByFileID(fileID));
    }

    @RequiresPermissions("filingsystem:profile:edit")
    @Log(title = "文档_管理人员操作", businessType = BusinessType.INSERT)
    @PostMapping("/updateFile")
    public AjaxResult updateFile(@Validated @RequestBody UpdateFileDTO updateFileDTO) {
       service.updateFilingFileProfile(updateFileDTO);
       return success(service.selectFilingFileProfileByFileID(updateFileDTO.getOldSystemfileID()));
    }

    /**
     * 批量导出BarCode
     *
     * @param response
     * @param searchFileDto
     */
    @RequiresPermissions("filingsystem:profile:export")
    @Log(title = "文档_管理人员操作", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void exportBarcodeList(HttpServletResponse response, SearchFileDTO searchFileDto) {
        // 必须选择一个地区，避免数据量过大导出失败
        if(searchFileDto.getDeptId() == null) {
            if(searchFileDto.getFileIDFrom() != null && searchFileDto.getFileIDFromTo() != null) {
                // do nothing
            } else {
                throw new FilingSystemException(SystemMessageConstants.SELECT_A_LOCATION_FOR_EXPORTING);
            }
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
            List<FilingExportBarcodeVO> list = service.exportBarcodeList(searchFileDto);
            ExcelUtil<FilingExportBarcodeVO> util = new ExcelUtil<>(FilingExportBarcodeVO.class);
            util.exportExcel(response, list, "FILING SYSTEM");
        } finally {
            redisLockUtils.unlock(FILE_EXPORT_LOCK_KEY, uuid);
        }
    }

    @RequiresPermissions("filingsystem:profile:add")
    @Log(title = "文档_管理人员操作", businessType = BusinessType.INSERT)
    @PostMapping("/addFile")
    public AjaxResult add(@Validated @RequestBody AddFileDto addFileDto) {
        RedisSpinLock redisSpinLock = new RedisSpinLock(redisService.redisTemplate);
        String key = "filingsystem:lockKey_"+addFileDto.getAreaLocation();
        String value = Thread.currentThread().getId() + ""; // 以当前线程ID作为唯一标识
        long expireTime = 4; // 锁过期时间4秒   单次添加 4秒足够完成了
        long timeout = 5000;  // 尝试获取锁的最大等待时间5秒
        if (redisSpinLock.tryLock(key, value, expireTime, timeout)) {
            try {
                AddFileReturnDto addFileReturnDto;
                try {
                    addFileReturnDto = (AddFileReturnDto)service.insertFilingFileProfile(addFileDto);
                } catch (Exception e) {
                    return error("An exception occurred while adding the file.\n"+e.getMessage());
                }
                return success(addFileReturnDto);
            } finally {
                redisSpinLock.unlock(key, value);
            }
        } else {
            logger.info("获取锁失败");
            throw new FilingSystemException(SystemMessageConstants.FAILED_TO_OBTAIN_LOCK_MESSAGE);
        }
    }

    /**
     * 查询文档_默认接口 list查询
     */
    @RequiresPermissions("filingsystem:profile:query")
    @PostMapping("/getFilelist")
    public TableDataInfo list(@RequestBody SearchFileDTO searchFileDto) {
        PageHelper.startPage(searchFileDto.getPageNum(), searchFileDto.getPageSize()).setReasonable(true);
        List<FilingFileProfile> list = service.searchProfileListByDto(searchFileDto);
        return getDataTable(list);
    }

    @PostMapping(value = "/reminder/over6MonthsOutstandingFiles")
    public AjaxResult over6MonthsOutstandingFiles(){
        service.reminderOver6MonthsOutstandingFiles();
        return success();
    }

    @RequiresPermissions("filingsystem:profile:export")
    @Log(title = "文档_管理人员操作", businessType = BusinessType.EXPORT)
    @PostMapping("/exportSearchData")
    public void exportSearchData(HttpServletResponse response, SearchFileDTO searchFileDto) {
        if(searchFileDto.getDeptId() == null) {
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
            List<FilingFileProfile> list = service.exportData(searchFileDto);
            ExcelUtil<FilingFileProfile> util = new ExcelUtil<>(FilingFileProfile.class);
            util.exportExcel(response, list, "File Profile");
        } finally {
            redisLockUtils.unlock(FILE_EXPORT_LOCK_KEY, uuid);
        }
    }

    @PostMapping("/getEngagementByEID")
    public AjaxResult getEngagementByEID(@RequestBody EID param){
        if(StringUtils.isEmpty(param.getEid()) || param.getEid().length() > 8) {
            return error("Oops! It looks like the EID parameter is not correct. Can you please check it again?");
        }
        R<CDIEngagement> r = remoteCDIService.getEngagementByEID(param, SecurityConstants.INNER);
        if(r.getData() == null || r.getData().getResponse().length == 0) {
            return error("The entered EID is not valid. Please enter another.");
        }
        FilingEngagementVO vo = new FilingEngagementVO();
        vo.setEngagementCode(param.getEid());
        vo.setEngagementName(r.getData().getResponse()[0].getC_LNM());
        vo.setServiceLine(r.getData().getResponse()[0].getSUBSL_DESCR());
        vo.setCcid(r.getData().getResponse()[0].getCCID());
        vo.setEic(r.getData().getResponse()[0].getNAME_EM());
        vo.setEicEmail(r.getData().getResponse()[0].getEMAIL_EM());
        vo.setPic(r.getData().getResponse()[0].getNAME_EP());
        vo.setPicEmail(r.getData().getResponse()[0].getEMAIL_EP());
        vo.setClientName(r.getData().getResponse()[0].getC_LNM());
        vo.setClientCode(r.getData().getResponse()[0].getCCID());
        vo.setChargeable(r.getData().getResponse()[0].getE_TYPE_CLS_DESCR());
        return success(vo);
    }

    @PostMapping("/fetchStaffByEmail")
    public AjaxResult fetchStaffByEmail(@RequestBody Email param){
        R<CDIOneEmployee> r = remoteCDIService.getOneEmployeeByEmail(param, SecurityConstants.INNER);
        if(StringUtils.isEmpty(param.getEmail())
                || param.getEmail().length() > 100
                || !Validator.isEmail(param.getEmail())) {
            return error("Hmm, the email parameter seems off. Please check and try again.");
        }
        else if(r.getData().getResponse().length == 0) {
            return error("Hmm, the Email Address you entered isn't recognized. Would you like to check the list of available emails?");
        }
        String staffName = r.getData().getResponse()[0].getFirst_Name_1();
        String staffEmail = r.getData().getResponse()[0].getInternet_Addr();
        String lpn = r.getData().getResponse()[0].getLPN();
        return success(new FilingStaffVO(staffName, staffEmail, lpn));
    }
}
