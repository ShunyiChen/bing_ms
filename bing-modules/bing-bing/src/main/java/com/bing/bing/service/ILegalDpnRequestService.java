package com.bing.bing.service;

import com.bing.bing.domain.LegalDpnRequest;
import com.bing.bing.dto.BulkApprovalReturnDTO;
import com.bing.bing.dto.LegalDpnRequestCountDTO;
import com.bing.bing.dto.LegalDpnRequestFilterDTO;

import java.util.List;

/**
 * DPNRequestService接口
 * 
 * @author Simeon
 * @date 2024-11-19
 */
public interface ILegalDpnRequestService 
{

    /**
     * 新增DPNRequest
     *
     * @param legalDpnRequest DPNRequest
     * @return 结果
     */
    int insertLegalDpnRequest(LegalDpnRequest legalDpnRequest);

    /**
     * 获取DPNRequest总数
     *
     * @return request总数
     */
    LegalDpnRequestCountDTO count(LegalDpnRequestFilterDTO dto);

    /**
     * 查询DPNRequest列表
     *
     * @param dto
     * @return
     */
    List<LegalDpnRequest> selectLegalDpnRequestList(LegalDpnRequestFilterDTO dto);

    /**
     * 查询DPNRequest
     *
     * @param id DPNRequest主键
     * @return DPNRequest
     */
    LegalDpnRequest selectLegalDpnRequestById(Long id);

    /**
     * 修改DPNRequest
     *
     * @param legalDpnRequest DPNRequest
     * @return 结果
     */
    int updateLegalDpnRequest(LegalDpnRequest legalDpnRequest);

    /**
     * Bulk Approval
     *
     * @return 影响结果数
     */
    BulkApprovalReturnDTO bulkApproval();

    /**
     * Approval
     *
     * @param dpnRequest
     */
    void approval(LegalDpnRequest dpnRequest);








//
//    /**
//     * 批量删除DPNRequest
//     *
//     * @param ids 需要删除的DPNRequest主键集合
//     * @return 结果
//     */
//    public int deleteLegalDpnRequestByIds(Long[] ids);
//
//    /**
//     * 删除DPNRequest信息
//     *
//     * @param id DPNRequest主键
//     * @return 结果
//     */
//    public int deleteLegalDpnRequestById(Long id);
}
