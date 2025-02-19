package com.bing.bing.mapper;

import java.util.List;
import com.bing.bing.domain.LegalPending;

/**
 * PendingMapper接口
 * 
 * @author Simeon
 * @date 2024-11-23
 */
public interface LegalPendingMapper 
{
    /**
     * 查询Pending
     * 
     * @param id Pending主键
     * @return Pending
     */
    LegalPending selectLegalPendingById(Long id);

    /**
     * 查询Pending列表
     * 
     * @param legalPending Pending
     * @return Pending集合
     */
    List<LegalPending> selectLegalPendingList(LegalPending legalPending);

    /**
     * 新增Pending
     * 
     * @param legalPending Pending
     * @return 结果
     */
    int insertLegalPending(LegalPending legalPending);

    /**
     * 修改Pending
     * 
     * @param legalPending Pending
     * @return 结果
     */
    int updateLegalPending(LegalPending legalPending);

    /**
     * 批量插入Pending
     *
     * @param list
     */
    void bulkInsertLegalPending(List<LegalPending> list);

//    /**
//     * 删除Pending
//     *
//     * @param id Pending主键
//     * @return 结果
//     */
//    public int deleteLegalPendingById(Long id);
//
//    /**
//     * 批量删除Pending
//     *
//     * @param ids 需要删除的数据主键集合
//     * @return 结果
//     */
//    public int deleteLegalPendingByIds(Long[] ids);
}
