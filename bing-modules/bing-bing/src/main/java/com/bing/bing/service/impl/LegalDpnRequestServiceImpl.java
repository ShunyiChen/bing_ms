package com.bing.bing.service.impl;

import com.bing.bing.domain.LegalDpnRequest;
import com.bing.bing.dto.BulkApprovalReturnDTO;
import com.bing.bing.dto.LegalDpnRequestCountDTO;
import com.bing.bing.dto.LegalDpnRequestFilterDTO;
import com.bing.bing.mapper.LegalDpnRequestMapper;
import com.bing.bing.service.ILegalDpnRequestService;
import com.bing.bing.utils.SendEmailUtil;
import com.bing.bing.utils.enums.DPNRequestStatus;
import com.bing.bing.utils.enums.OfficeEnum;
import com.bing.bing.utils.enums.UserEmailEnum;
import com.bing.common.core.utils.DateUtils;
import com.bing.common.security.utils.SecurityUtils;
import com.bing.system.api.model.LoginUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * DPNRequestService业务层处理
 * 
 * @author Simeon
 * @date 2024-11-19
 */
@Service
public class LegalDpnRequestServiceImpl implements ILegalDpnRequestService 
{
    private static final ExecutorService executor = Executors.newFixedThreadPool(10);
    private static final String URL = "https://cbsapp-uat.ey.com.cn/legaldb/dpnrequest/view?id=";
    private final LegalDpnRequestMapper legalDpnRequestMapper;

    public LegalDpnRequestServiceImpl(LegalDpnRequestMapper legalDpnRequestMapper) {
        this.legalDpnRequestMapper = legalDpnRequestMapper;
    }

    /**
     * 新增DPNRequest
     *
     * @param legalDpnRequest DPNRequest
     * @return 结果
     */
    @Override
    public int insertLegalDpnRequest(LegalDpnRequest legalDpnRequest)
    {
        legalDpnRequest.setStatus(DPNRequestStatus.WAITING_FOR_CONFIRMATION.getStatus());
        LoginUser loginUser = SecurityUtils.getLoginUser();
        legalDpnRequest.setCreatedBy(loginUser.getUsername());
        return legalDpnRequestMapper.insertLegalDpnRequest(legalDpnRequest);
    }

    /**
     * 获取DPNRequest总数
     *
     * @param dto
     * @return request总数
     */
    @Override
    public LegalDpnRequestCountDTO count(LegalDpnRequestFilterDTO dto) {
        return legalDpnRequestMapper.count(dto);
    }

    /**
     * 查询DPNRequest列表
     *
     * @param dto
     * @return DPNRequest
     */
    @Override
    public List<LegalDpnRequest> selectLegalDpnRequestList(LegalDpnRequestFilterDTO dto)
    {
        return legalDpnRequestMapper.selectLegalDpnRequestList(dto);
    }

    /**
     * 查询DPNRequest
     *
     * @param id DPNRequest主键
     * @return DPNRequest
     */
    @Override
    public LegalDpnRequest selectLegalDpnRequestById(Long id)
    {
        return legalDpnRequestMapper.selectLegalDpnRequestById(id);
    }

    /**
     * 修改DPNRequest
     *
     * @param legalDpnRequest DPNRequest
     * @return 结果
     */
    @Override
    public int updateLegalDpnRequest(LegalDpnRequest legalDpnRequest)
    {
        approval(legalDpnRequest);
        LoginUser loginUser = SecurityUtils.getLoginUser();
        legalDpnRequest.setUpdateBy(loginUser.getUsername());
        return legalDpnRequestMapper.updateLegalDpnRequest(legalDpnRequest);
    }

    /**
     * Bulk Approval
     *
     * @return 影响结果数
     */
    @Override
    public BulkApprovalReturnDTO bulkApproval() {
        BulkApprovalReturnDTO dto = new BulkApprovalReturnDTO();
        List<LegalDpnRequest> requestList = legalDpnRequestMapper.comparePendingList();
        // 统计不同状态的数量
        Map<String, Long> statusCountMap = requestList.stream()
                .collect(Collectors.groupingBy(LegalDpnRequest::getNewStatus, Collectors.counting()));
        dto.setApprovedNum(statusCountMap.getOrDefault("Approved", 0L).intValue());
        dto.setPendingLegalReviewNum(statusCountMap.getOrDefault("Pending Legal Review", 0L).intValue());
        if(!requestList.isEmpty()) {
            requestList.forEach(this::approval);
            int updates = legalDpnRequestMapper.batchUpdateLegalDpnRequest(requestList);
            dto.setUpdates(updates);
        }
        return dto;
    }

    @Override
    public void approval(LegalDpnRequest dpnRequest) {
        if (!dpnRequest.getStatus().equals(dpnRequest.getNewStatus())) {
            LoginUser loginUser = SecurityUtils.getLoginUser();
            StringBuilder values = new StringBuilder();
            String str = "";
            String name = "";
            String title = "";
            boolean isOtherOffice =false;
            boolean isHK =false;
            String email = dpnRequest.getCreatedBy()+";";
            if(dpnRequest.getNewStatus().equals(DPNRequestStatus.APPROVED.getStatus())) {
                name = dpnRequest.getCreatedBy();
                str = "Please be advised your DPN request has been approved." +
                        "Click <a href='"+ URL + dpnRequest.getId() + "'>here</a> to check the details.";
                title = "Your DPN request has been approved";
                sendEmailByStatus(name, str, dpnRequest, values, title, email, true);
                dpnRequest.setApproved(DateUtils.getNowDate());
                dpnRequest.setApprover(loginUser.getUsername());
            }
            else if(dpnRequest.getNewStatus().equals(DPNRequestStatus.PENDING_LEGAL_REVIEW.getStatus())) {
                name = "Legal Team";
                str = "There is a new DPN request pending for your confirmation,please click <a href='"+ URL + dpnRequest.getId() + "'>here</a> to check the details." +
                        "You need to click the Edit button on the top left corner of the page to enter Edit mode,and then change the Status and save the record.";
                title = "New DPN Request is waiting for your confirmation";
                email = UserEmailEnum.LEGAL_TEAM.title();
                sendEmailByStatus(name, str, dpnRequest, values, title, email, true);
            }
            else if(dpnRequest.getNewStatus().equals(DPNRequestStatus.REJECTED.getStatus())) {
                StringBuilder one = new StringBuilder();
                name = dpnRequest.getCreatedBy();
                str = "Please be advised a new DPN request has been rejected.Click <a href='" + URL + dpnRequest.getId() + "'>here</a> to check the details.";
                title = "Your DPN Request has been rejected";
                sendEmailByStatus(name, str, dpnRequest, one, title, email, true);
                StringBuilder two = new StringBuilder();
                name = "team";
                str = "Please be advised a new DPN request has been rejected.Related staff name is " + dpnRequest.getStaffName() + ".Click <a href='" + URL + dpnRequest.getId() + "'>here</a> to check the details.";
                title = "A New DPN Request has been rejected - " + dpnRequest.getOffice();

                //如果办公室名字不在枚举列表，大陆的发邮件给Charlie.Chi@cn.ey.com;  香港、澳门的发邮件给Tom.See@hk.ey.com
                OfficeEnum officeEnum = null;
                try {
                    officeEnum = OfficeEnum.titleOf(dpnRequest.getOffice());
                } catch (Exception e) {
                    isOtherOffice= true;
                }
                if(isOtherOffice){
                    String Create_email = email.toLowerCase();

//                    if (Create_email.endsWith("mo.ey.com") || Create_email.endsWith("hk.ey.com")){
//                        //香港和澳门的
//                        email="Tom.See@hk.ey.com;";
//                        if(Create_email.endsWith("hk.ey.com")){
//                            isHK=true;
//                        }
//
//                    }else {
//                        //除开香港澳门之外的
//                        email="Charlie.Chi@cn.ey.com;";
//                    }
                    email = "Simeon.Chen@cn.ey.com";
                }else {
                    //  正常的 能找到办公室的，就发给对应办公室的联系人
                    email = UserEmailEnum.valueOf(officeEnum.value());
                }
                sendEmailByStatus(name, str, dpnRequest, two, title, email, false);
                name = "FIDS team";
                str = "There is a Legal DPN record rejected by Legal team,please click <a href='" + URL + dpnRequest.getId() + "'>this link</a> to check the details," +
                        "then go to IT to take the hard disk,and then click the Edit button at the top left corner in the link to change" +
                        "the record status from 'Rejected' to 'FIDS Received',and put in the 'FIDS received by' person,as well as 'Received Date',then save the record.";
                title = "Legal DPN Requested - Please take the hard disk from IT";
                email = UserEmailEnum.FIDS.title();
                if(isHK){
                    sendEmailByStatus(name, str, dpnRequest, values, title, email, false);
                }
                dpnRequest.setRejected(DateUtils.getNowDate());
                dpnRequest.setRejecter(loginUser.getUsername());
            }
            else if(dpnRequest.getNewStatus().equals(DPNRequestStatus.FIDS_RECEIVED.getStatus())) {
                name = dpnRequest.getCreatedBy();
                str = "The hard disk of your Legal DPN record has been received by FIDS team.Click <a href='" + URL + dpnRequest.getId() + "'>here</a> to view the record.";
                title = "Legal DPN Requested - Please take the hard disk from IT";
                sendEmailByStatus(name, str, dpnRequest, values, title, email, true);
            }
        }
    }

    private void sendEmailByStatus(String name, String str, LegalDpnRequest dpnRequest, StringBuilder values, String title, String email, Boolean isRequestInformation) {
        values.append("Hi ")
                .append(name)
                .append(",<br/>")
                .append("<br/>")
                .append(str);
        if (isRequestInformation) {
            values.append("<br/>")
                    .append("<br/>")
                    .append("User information is listed below:<br/>")
                    .append("<br/>")
                    .append("Staff No.:")
                    .append(dpnRequest.getStaffNo())
                    .append("<br/>")
                    .append("<br/>")
                    .append("Email Address:")
                    .append(dpnRequest.getEmail())
                    .append("<br/>")
                    .append("<br/>")
                    .append("Staff Name:")
                    .append(dpnRequest.getStaffName())
                    .append("<br/>")
                    .append("<br/>")
                    .append("Office:")
                    .append(dpnRequest.getOffice())
                    .append("<br/>")
                    .append("<br/>")
                    .append("Department/Service Line:")
                    .append(dpnRequest.getServiceLine())
                    .append("<br/>")
                    .append("<br/>")
                    .append("EY Rank:")
                    .append(dpnRequest.getRankName())
                    .append("<br/>")
                    .append("<br/>");
        }

        values.append("Thanks.<br/>")
                .append("<br/>")
                .append("<span style='color:red;font-style:italic'>*DO NOT REPLY TO THIS EMAIL.THE RECEIVING MAILBOX WILL NOT BE MONITORED.*</span>");
        //分割收件人地址
        String[] listEmail = {"Simeon.Chen@cn.ey.com"};//email.split(";");
        // 异步发信避免线程阻塞
        CompletableFuture.runAsync(() -> SendEmailUtil.sendEmail(listEmail, title, values.toString()), executor);
    }
}
