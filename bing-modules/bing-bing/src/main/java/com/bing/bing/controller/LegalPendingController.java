package com.bing.bing.controller;

import com.bing.bing.domain.LegalPending;
import com.bing.bing.service.ILegalPendingService;
import com.github.pagehelper.PageHelper;
import com.bing.common.core.exception.legaldatabase.LegalDatabaseException;
import com.bing.common.core.utils.StringUtils;
import com.bing.common.core.utils.file.FileTypeUtils;
import com.bing.common.core.web.controller.BaseController;
import com.bing.common.core.web.domain.AjaxResult;
import com.bing.common.core.web.page.TableDataInfo;
import com.bing.common.log.annotation.Log;
import com.bing.common.log.enums.BusinessType;
import com.bing.common.security.annotation.RequiresPermissions;
import com.bing.common.security.utils.SecurityUtils;
import com.bing.system.api.model.LoginUser;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * PendingController
 *
 * @author Simeon
 * @date 2024-11-23
 */
@RestController
@RequestMapping("/pending")
public class LegalPendingController extends BaseController
{
    private final ILegalPendingService legalPendingService;

    public LegalPendingController(ILegalPendingService legalPendingService) {
        this.legalPendingService = legalPendingService;
    }

    /**
     * 查询Pending列表
     */
    @RequiresPermissions("legaldatabase:PendingList:list")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody LegalPending legalPending)
    {
        PageHelper.startPage(legalPending.getPageNum(), legalPending.getPageSize()).setReasonable(true);
        List<LegalPending> list = legalPendingService.selectLegalPendingList(legalPending);
        return getDataTable(list);
    }

    /**
     * 获取Pending详细信息
     */
    @RequiresPermissions("legaldatabase:PendingList:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(legalPendingService.selectLegalPendingById(id));
    }

    /**
     * 新增Pending
     */
    @RequiresPermissions("legaldatabase:PendingList:add")
    @Log(title = "Pending", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody LegalPending legalPending)
    {
        legalPendingService.insertLegalPending(legalPending);
        return success(legalPending.getId());
    }

    @RequiresPermissions("legaldatabase:PendingList:bulkImport")
    @Log(title = "Pending", businessType = BusinessType.INSERT)
    @PostMapping("/bulkImport")
    public AjaxResult bulkImport(@RequestParam("file") MultipartFile file)
    {
        if (!file.isEmpty()) {
            int dataStartRowNumber = 19;
            LoginUser loginUser = SecurityUtils.getLoginUser();
            String extension = FileTypeUtils.getExtension(file);
            if (!StringUtils.equalsAnyIgnoreCase(extension, new String[]{"xlsx"})) {
                return error("The file format is incorrect. Please upload an " + Arrays.toString(new String[]{"xlsx"}) + " format");
            }
            List<LegalPending> pendingList;
            try {
                pendingList = legalPendingService.bulkImport(file);
                // 检查无数据
                if(pendingList.isEmpty()) {
                    return error("Data not found");
                }

                // 使用 IntStream 和 Lambda 表达式为每个对象设置行号
                IntStream.range(0, pendingList.size())
                        .forEach(i -> {
                            pendingList.get(i).setLineNumber(i + dataStartRowNumber);
                            pendingList.get(i).setCreateBy(loginUser.getSysUser().getEmail());
                        }); // 行号从 1 开始
                // 校验某个属性不为空
                List<LegalPending> invalidEntries = pendingList.stream()
                        .filter(p -> StringUtils.isEmpty(p.getLpn()) ||
                                StringUtils.isEmpty(p.getGpn()) ||
                                StringUtils.isEmpty(p.getEmailAddress())
                        )
                        .collect(Collectors.toList());
                if(!invalidEntries.isEmpty()) {
                    // 获取所有行号并转换成字符串
                    String lineNumbers = invalidEntries.stream()
                            .map(legalPending -> String.valueOf(legalPending.getLineNumber()))
                            .collect(Collectors.joining(", ")); // 用逗号分隔
                    return error("Row "+lineNumbers+": LPN, GPN, or Email Address cannot be empty.");
                }

                // 校验字符串长度不能超长
                List<LegalPending> invalidLengthEntries = pendingList.stream()
                        .filter(p -> StringUtils.length(p.getLpn()) > 20 ||
                                StringUtils.length(p.getGpn()) > 30 ||
                                StringUtils.length(p.getEmailAddress()) > 100 ||
                                StringUtils.length(p.getNameInBatchApprovalList()) > 100 ||
                                StringUtils.length(p.getUserName()) > 50
                        )
                        .collect(Collectors.toList());
                if(!invalidLengthEntries.isEmpty()) {
                    // 获取所有行号并转换成字符串
                    String lineNumbers = invalidLengthEntries.stream()
                            .map(legalPending -> String.valueOf(legalPending.getLineNumber()))
                            .collect(Collectors.joining(", ")); // 用逗号分隔
                    return error("Row "+lineNumbers+": String content too long.");
                }
            } catch (LegalDatabaseException e) {
                return error(e.getMessage());
            }
            // 批量插入Pending
            legalPendingService.bulkInsertLegalPending(pendingList);
        }
        return success();
    }

    /**
     * 修改Pending
     */
    @RequiresPermissions("legaldatabase:PendingList:edit")
    @Log(title = "Pending", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    public AjaxResult edit(@Validated @RequestBody LegalPending legalPending)
    {
        return toAjax(legalPendingService.updateLegalPending(legalPending));
    }

//    /**
//     * 删除Pending
//     */
//    @RequiresPermissions("legaldatabase:PendingList:remove")
//    @Log(title = "Pending", businessType = BusinessType.DELETE)
//	@DeleteMapping("/{ids}")
//    public AjaxResult remove(@PathVariable Long[] ids)
//    {
//        return toAjax(legalPendingService.deleteLegalPendingByIds(ids));
//    }

//    /**
//     * 导出Pending列表
//     */
//    @RequiresPermissions("legaldatabase:PendingList:export")
//    @Log(title = "Pending", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, LegalPending legalPending)
//    {
//        List<LegalPending> list = legalPendingService.selectLegalPendingList(legalPending);
//        ExcelUtil<LegalPending> util = new ExcelUtil<LegalPending>(LegalPending.class);
//        util.exportExcel(response, list, "Pending数据");
//    }
}
