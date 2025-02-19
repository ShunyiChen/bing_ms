package com.bing.bing.service;

import java.util.List;
import com.bing.bing.domain.LegalPending;
import com.bing.common.core.exception.legaldatabase.LegalDatabaseException;
import org.springframework.web.multipart.MultipartFile;

/**
 * PendingService接口
 * 
 * @author Simeon
 * @date 2024-11-23
 */
public interface ILegalPendingService 
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
     * 插入Pending
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
     * 批量导入
     *
     * @param file
     * @return
     */
    List<LegalPending> bulkImport(MultipartFile file) throws LegalDatabaseException;


    /**
     * 批量插入Pending
     *
     * @param list
     */
    void bulkInsertLegalPending(List<LegalPending> list);

//    /**
//     * 批量删除Pending
//     *
//     * @param ids 需要删除的Pending主键集合
//     * @return 结果
//     */
//    public int deleteLegalPendingByIds(Long[] ids);
//
//    /**
//     * 删除Pending信息
//     *
//     * @param id Pending主键
//     * @return 结果
//     */
//    public int deleteLegalPendingById(Long id);
}
