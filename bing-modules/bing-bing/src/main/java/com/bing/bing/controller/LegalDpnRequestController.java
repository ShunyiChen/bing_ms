package com.bing.bing.controller;

import cn.hutool.core.lang.Validator;
import com.bing.bing.domain.LegalDpnRequest;
import com.bing.bing.dto.*;
import com.bing.bing.service.ILegalDpnRequestService;
import com.bing.system.api.model.*;
import com.github.pagehelper.PageHelper;
import com.bing.common.core.constant.SecurityConstants;
import com.bing.common.core.domain.R;
import com.bing.common.core.utils.StringUtils;
import com.bing.common.core.web.controller.BaseController;
import com.bing.common.core.web.domain.AjaxResult;
import com.bing.common.core.web.page.TableDataInfo;
import com.bing.common.log.annotation.Log;
import com.bing.common.log.enums.BusinessType;
import com.bing.common.security.annotation.RequiresPermissions;
import com.bing.system.api.RemoteCDIService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * DPNRequestController
 *
 * @author Simeon
 * @date 2024-11-19
 */
@RestController
@RequestMapping("/dpnrequest")
public class LegalDpnRequestController extends BaseController
{
    private final ILegalDpnRequestService legalDpnRequestService;
    private final RemoteCDIService remoteCDIService;

    public LegalDpnRequestController(ILegalDpnRequestService legalDpnRequestService, RemoteCDIService remoteCDIService) {
        this.legalDpnRequestService = legalDpnRequestService;
        this.remoteCDIService = remoteCDIService;
    }

    /**
     * 新增DPNRequest
     */
    @RequiresPermissions("legaldatabase:DPNRequest:add")
    @Log(title = "DPNRequest", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody LegalDpnRequest legalDpnRequest)
    {
        legalDpnRequestService.insertLegalDpnRequest(legalDpnRequest);
        return success(legalDpnRequest.getId());
    }

    /**
     * 查询DPNRequest列表
     */
    @RequiresPermissions("legaldatabase:DPNRequest:list")
    @PostMapping("/list")
    public LegalDpnRequestListDTO list(@RequestBody LegalDpnRequestFilterDTO dto)
    {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize()).setReasonable(true);
        List<LegalDpnRequest> list = legalDpnRequestService.selectLegalDpnRequestList(dto);
        TableDataInfo table = getDataTable(list);
        LegalDpnRequestListDTO outDTO = new LegalDpnRequestListDTO();
        outDTO.setTable(table);
        LegalDpnRequestCountDTO count = legalDpnRequestService.count(dto);
        outDTO.setCount(count);
        return outDTO;
    }

    /**
     * 获取DPNRequest详细信息
     */
    @RequiresPermissions("legaldatabase:DPNRequest:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(legalDpnRequestService.selectLegalDpnRequestById(id));
    }

    /**
     * 修改DPNRequest
     */
    @RequiresPermissions("legaldatabase:DPNRequest:edit")
    @Log(title = "DPNRequest", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    public AjaxResult edit(@Validated @RequestBody LegalDpnRequest legalDpnRequest)
    {
        return toAjax(legalDpnRequestService.updateLegalDpnRequest(legalDpnRequest));
    }

    @RequiresPermissions("legaldatabase:DPNRequest:bulkApproval")
    @Log(title = "DPNRequest", businessType = BusinessType.UPDATE)
    @PostMapping("/bulkApproval")
    public AjaxResult bulkApproval()
    {
        return success(legalDpnRequestService.bulkApproval());
    }

    @RequiresPermissions("legaldatabase:DPNRequest:cdiApiCall")
    @PostMapping("/fetchEmployeeInfoByLPN")
    public AjaxResult fetchEmployeeInfoByLPN(@RequestBody LPN param){
        if(StringUtils.isEmpty(param.getLpn()) || param.getLpn().length() > 50) {
            return error("Oops! It looks like the input is not correct. Can you please check it again?");
        }
        R<CDIListEmployee> r = remoteCDIService.getEmployeeByLPN(param, SecurityConstants.INNER);
        if(r.getData() == null || r.getData().getResponse().length == 0) {
            return error("The input:"+param.getLpn()+" you entered is not in the database.");
        }

        CDIListEmployeeDTO outDto = new CDIListEmployeeDTO();
        CDIListEmployee.Response[] dtoResponses = r.getData().getResponse();
        CDIListEmployeeDTO.Response[] employeeResponses = new CDIListEmployeeDTO.Response[dtoResponses.length];
        for (int i = 0; i < dtoResponses.length; i++) {
            employeeResponses[i] = new CDIListEmployeeDTO.Response();
            employeeResponses[i].setLPN(dtoResponses[i].getLPN());
            employeeResponses[i].setGPN(dtoResponses[i].getGPN());
            employeeResponses[i].setSel_EY_Rank(dtoResponses[i].getSel_EY_Rank());
            employeeResponses[i].setFirst_Name_1(dtoResponses[i].getFirst_Name_1());
            employeeResponses[i].setLast_Name_1(dtoResponses[i].getLast_Name_1());
            employeeResponses[i].setSel_Service_Line(dtoResponses[i].getSel_Service_Line());
            employeeResponses[i].setDesc_Management_Unit(dtoResponses[i].getDesc_Management_Unit());
            employeeResponses[i].setInternet_Addr(dtoResponses[i].getInternet_Addr());
        }
        outDto.setResponse(employeeResponses);
        return success(outDto);
    }

    @RequiresPermissions("legaldatabase:DPNRequest:cdiApiCall")
    @PostMapping("/getOneEmployeeByEmail")
    public AjaxResult getOneEmployeeByEmail(@RequestBody Email param){
        R<CDIOneEmployee> r = remoteCDIService.getOneEmployeeByEmail(param, SecurityConstants.INNER);
        if(StringUtils.isEmpty(param.getEmail())
                || param.getEmail().length() > 100
                || !Validator.isEmail(param.getEmail())) {
            return error("Hmm, the email parameter seems off. Please check and try again.");
        }
        else if(r.getData().getResponse().length == 0) {
            return error("Hmm, the Email Address you entered isn't recognized. Would you like to check the list of available emails?");
        }
        CDIGetOneEmployeeByEmailDTO outDto = new CDIGetOneEmployeeByEmailDTO();
        CDIListEmployee.Response[] dtoResponses = r.getData().getResponse();
        CDIListEmployeeDTO.Response[] employeeResponses = new CDIListEmployeeDTO.Response[dtoResponses.length];
        for (int i = 0; i < dtoResponses.length; i++) {
            employeeResponses[i] = new CDIListEmployeeDTO.Response();
            employeeResponses[i].setLPN(dtoResponses[i].getLPN());
            employeeResponses[i].setGPN(dtoResponses[i].getGPN());
            employeeResponses[i].setSel_EY_Rank(dtoResponses[i].getSel_EY_Rank());
            employeeResponses[i].setFirst_Name_1(dtoResponses[i].getFirst_Name_1());
            employeeResponses[i].setLast_Name_1(dtoResponses[i].getLast_Name_1());
            employeeResponses[i].setSel_Service_Line(dtoResponses[i].getSel_Service_Line());
            employeeResponses[i].setDesc_Management_Unit(dtoResponses[i].getDesc_Management_Unit());
            employeeResponses[i].setInternet_Addr(dtoResponses[i].getInternet_Addr());
        }
        outDto.setResponse(employeeResponses);
        return success(outDto);
    }

    @RequiresPermissions("legaldatabase:DPNRequest:cdiApiCall")
    @PostMapping("/getEngagementByEID")
    public AjaxResult getEngagementByEID(@RequestBody EID param){
        if(StringUtils.isEmpty(param.getEid()) || param.getEid().length() > 8) {
            return error("Oops! It looks like the input is not correct. Can you please check it again?");
        }
        R<CDIEngagement> r = remoteCDIService.getEngagementByEID(param, SecurityConstants.INNER);
        if(r.getData() == null || r.getData().getResponse().length == 0) {
            return error("The input:"+param.getEid()+" you entered is not in the database.");
        }
        CDIGetEngagementByEIDDTO outDto = new CDIGetEngagementByEIDDTO();
        CDIEngagement.Response[] dtoResponses = r.getData().getResponse();
        CDIGetEngagementByEIDDTO.Response[] engagementResponses = new CDIGetEngagementByEIDDTO.Response[dtoResponses.length];
        for (int i = 0; i < dtoResponses.length; i++) {
            engagementResponses[i] = new CDIGetEngagementByEIDDTO.Response();
            engagementResponses[i].setEID(dtoResponses[i].getEID());
            engagementResponses[i].setC_LNM(dtoResponses[i].getC_LNM());
        }
        outDto.setResponse(engagementResponses);
        return success(outDto);
    }



//    /**
//     * 过滤DPNRequest列表
//     */
//    @RequiresPermissions("legaldatabase:DPNRequest:filter")
//    @PostMapping("/doFilter")
//    public TableDataInfo doFilter(@RequestBody LegalDpnRequestFilterDTO dto)
//    {
//        if(StringUtils.isEmpty(dto.getFindWhat()) &&
//                (dto.getScope() == null || dto.getScope().length == 0)) {
//            return getDataTable(new ArrayList<>());
//        }
//        PageHelper.startPage(dto.getPageNum(), dto.getPageSize()).setReasonable(true);
//        List<LegalDpnRequest> list = legalDpnRequestService.doFilter(dto);
//        return getDataTable(list);
//    }

//
//    /**
//     * 导出DPNRequest列表
//     */
////    @RequiresPermissions("legaldatabase:DPNRequest:export")
//    @Log(title = "DPNRequest", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, LegalDpnRequest legalDpnRequest)
//    {
//        List<LegalDpnRequest> list = legalDpnRequestService.selectLegalDpnRequestList(legalDpnRequest);
//        ExcelUtil<LegalDpnRequest> util = new ExcelUtil<LegalDpnRequest>(LegalDpnRequest.class);
//        util.exportExcel(response, list, "DPNRequest数据");
//    }
//
//
//    /**
//     * 删除DPNRequest
//     */
////    @RequiresPermissions("legaldatabase:DPNRequest:remove")
//    @Log(title = "DPNRequest", businessType = BusinessType.DELETE)
//	@DeleteMapping("/{ids}")
//    public AjaxResult remove(@PathVariable Long[] ids)
//    {
//        return toAjax(legalDpnRequestService.deleteLegalDpnRequestByIds(ids));
//    }
}
