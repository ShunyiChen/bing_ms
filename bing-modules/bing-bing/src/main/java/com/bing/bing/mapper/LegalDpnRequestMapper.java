package com.bing.bing.mapper;

import com.bing.bing.domain.LegalDpnRequest;
import com.bing.bing.dto.LegalDpnRequestCountDTO;
import com.bing.bing.dto.LegalDpnRequestFilterDTO;

import java.util.List;

/**
 * DPNRequestMapper接口
 *
 * @author Simeon
 * @date 2024-11-19
 */
public interface LegalDpnRequestMapper {
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
     * @param dto
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
     * 与PendingList列表做比较，检查是否包含了Dpn Request数据，并返回新的request列表
     *
     * @return 新的dpn request列表
     */
    List<LegalDpnRequest> comparePendingList();

    /**
     * 批量修改DPNRequest
     *
     * @param list 列表参数
     * @return 更新结果数
     */
    int batchUpdateLegalDpnRequest(List<LegalDpnRequest> list);

//
//    /**
//     * 删除DPNRequest
//     *
//     * @param id DPNRequest主键
//     * @return 结果
//     */
//    public int deleteLegalDpnRequestById(Long id);
//
//    /**
//     * 批量删除DPNRequest
//     *
//     * @param ids 需要删除的数据主键集合
//     * @return 结果
//     */
//    public int deleteLegalDpnRequestByIds(Long[] ids);
}
