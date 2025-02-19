package com.ruoyi.filingsystem.controller;

import com.github.pagehelper.PageHelper;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.filingsystem.dto.DestructionBoxConfirmDTO;
import com.ruoyi.filingsystem.dto.DestructionFileConfirmDto;
import com.ruoyi.filingsystem.dto.DestructionFileSearchDto;
import com.ruoyi.filingsystem.service.IFilingDestructionService;
import com.ruoyi.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("filing/destruction")
public class FilingDestructionController extends BaseController {
    @Autowired
    private IFilingDestructionService service;

    @Log(title = "销毁-文件检索", businessType = BusinessType.OTHER)
    @PostMapping("/fileSearch")
    public TableDataInfo fileSearch(@RequestBody DestructionFileSearchDto dto){
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize()).setReasonable(true);
        return getDataTable(service.getFileList(dto));
    }

    @Log(title = "销毁-盒子检索", businessType = BusinessType.OTHER)
    @PostMapping("/boxSearch")
    public TableDataInfo boxSearch(@RequestBody DestructionFileSearchDto dto){
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize()).setReasonable(true);
        return getDataTable(service.getBoxList(dto));
    }

    @Log(title = "文件销毁-确认销毁", businessType = BusinessType.UPDATE)
    @PostMapping("/fileConfirm")
    public AjaxResult fileConfirm(@RequestBody String[] fileIDs){
        DestructionFileConfirmDto dto = new DestructionFileConfirmDto();
        dto.setFileIDs(fileIDs);
        dto.setDestructionTime(DateUtils.getTime());
        // 获取当前的用户
        LoginUser loginUser = SecurityUtils.getLoginUser();
        dto.setDestructionPeople(loginUser.getUsername());
        int updated = service.destructFile(dto);
        return toAjax(updated);
    }

    @Log(title = "盒子销毁-确认销毁", businessType = BusinessType.UPDATE)
    @PostMapping("/boxConfirm")
    public AjaxResult boxConfirm(@RequestBody DestructionBoxConfirmDTO dto){
        // 获取当前的用户
        LoginUser loginUser = SecurityUtils.getLoginUser();
        dto.setDestroyer(loginUser.getUsername());
        int updated = service.destructBox(dto);
        return toAjax(updated);
    }
}
