package com.ruoyi.filingsystem.controller;

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
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.filingsystem.dto.*;
import com.ruoyi.filingsystem.service.IFilingBorrowRecordService;
import com.ruoyi.filingsystem.utils.RedisLockUtils;
import com.ruoyi.filingsystem.vo.FilingBorrowRecordVO;
import com.ruoyi.filingsystem.vo.FilingFetchReturnFileVO;
import com.ruoyi.filingsystem.vo.FilingStaffVO;
import com.ruoyi.system.api.RemoteCDIService;
import com.ruoyi.system.api.RemoteUserService;
import com.ruoyi.system.api.model.CDIListEmployee;
import com.ruoyi.system.api.model.LPN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

import static com.ruoyi.common.core.constant.CacheConstants.FILE_EXPORT_LOCK_KEY;

@RestController
@RequestMapping("filing/borrowrecord")
public class FilingBorrowRecordController extends BaseController {
    private final IFilingBorrowRecordService filingBorrowRecordService;
    private final RedisService redisService;
    private final RemoteCDIService remoteCDIService;
    @Autowired
    public FilingBorrowRecordController(IFilingBorrowRecordService filingBorrowRecordService,
                                        RedisService redisService,
                                        RemoteCDIService remoteCDIService) {
        this.filingBorrowRecordService = filingBorrowRecordService;
        this.redisService = redisService;
        this.remoteCDIService = remoteCDIService;
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

    @PostMapping("/getBorrowRecordsByStaffID")
    public TableDataInfo getBorrowRecordsByStaffID(@RequestBody GetBorrowRecordDTO dto){
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize()).setReasonable(true);
        return getDataTable(filingBorrowRecordService.getBorrowRecordsByStaffID(dto));
    }

    @PostMapping("/fetchBorrowFile")
    public AjaxResult fetchBorrowFile(@RequestBody FetchBorrowFileDTO dto){
        return success(filingBorrowRecordService.fetchBorrowFile(dto));
    }

    @PostMapping("/borrowConfirm")
    public AjaxResult borrowConfirm(@RequestBody BorrowConfirmDTO dto){
        return success(filingBorrowRecordService.borrowConfirm(dto));
    }

    @PostMapping("/exportOutstandingFiles")
    public void exportOutstandingFiles(HttpServletResponse response, GetBorrowRecordDTO dto){
        String uuid = IdUtil.fastUUID();
        boolean lock;
        RedisLockUtils redisLockUtils = null;
        try {
            redisLockUtils = new RedisLockUtils(redisService.redisTemplate);
            lock = redisLockUtils.lock(FILE_EXPORT_LOCK_KEY, uuid, 15);
            if(!lock) {
                throw new FilingSystemException(SystemMessageConstants.DOWNLOADS_ARE_IN_HIDE_DEMAND_MESSAGE);
            }
            List<FilingBorrowRecordVO> list = filingBorrowRecordService.getBorrowRecordsByStaffID(dto);
            ExcelUtil<FilingBorrowRecordVO> util = new ExcelUtil<>(FilingBorrowRecordVO.class);
            util.exportExcel(response, list, "Over 6 Months Outstanding Files", "Staff ID: "+dto.getStaffID()+" Borrower: "+dto.getStaffName());
        } finally {
            redisLockUtils.unlock(FILE_EXPORT_LOCK_KEY, uuid);
        }
    }

    @PostMapping("/fetchReturnFile")
    public AjaxResult fetchReturnFile(@RequestBody FetchReturnFileDTO dto) {
        FilingFetchReturnFileVO vo = filingBorrowRecordService.fetchReturnFile(dto);
        if(vo == null) {
            vo = new FilingFetchReturnFileVO();
            vo.setOldSystemfileID(dto.getFileID());
            vo.setStatus("Not in Database");
        }
        return success(vo);
    }

    @PostMapping("/returnConfirm")
    public AjaxResult returnConfirm(@RequestBody String[] fileIDs){
        return success(filingBorrowRecordService.returnConfirm(fileIDs));
    }

}
