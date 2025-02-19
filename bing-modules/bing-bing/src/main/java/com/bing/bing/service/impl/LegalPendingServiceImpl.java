package com.bing.bing.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.bing.bing.domain.LegalPending;
import com.bing.bing.mapper.LegalPendingMapper;
import com.bing.bing.service.ILegalPendingService;
import com.bing.common.core.exception.legaldatabase.LegalDatabaseException;
import com.bing.common.security.utils.SecurityUtils;
import com.bing.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * PendingService业务层处理
 * 
 * @author Simeon
 * @date 2024-11-23
 */
@Service
public class LegalPendingServiceImpl implements ILegalPendingService 
{
    @Autowired
    private LegalPendingMapper legalPendingMapper;

    /**
     * 查询Pending
     * 
     * @param id Pending主键
     * @return Pending
     */
    @Override
    public LegalPending selectLegalPendingById(Long id)
    {
        return legalPendingMapper.selectLegalPendingById(id);
    }

    /**
     * 查询Pending列表
     * 
     * @param legalPending Pending
     * @return Pending
     */
    @Override
    public List<LegalPending> selectLegalPendingList(LegalPending legalPending)
    {
        return legalPendingMapper.selectLegalPendingList(legalPending);
    }

    /**
     * 新增Pending
     * 
     * @param legalPending Pending
     * @return 结果
     */
    @Override
    public int insertLegalPending(LegalPending legalPending)
    {
        LoginUser user = SecurityUtils.getLoginUser();
        legalPending.setCreateBy(user.getUsername());
        return legalPendingMapper.insertLegalPending(legalPending);
    }

    /**
     * 修改Pending
     * 
     * @param legalPending Pending
     * @return 结果
     */
    @Override
    public int updateLegalPending(LegalPending legalPending)
    {
        LoginUser user = SecurityUtils.getLoginUser();
        legalPending.setUpdateBy(user.getUsername());
        return legalPendingMapper.updateLegalPending(legalPending);
    }

    @Override
    public List<LegalPending> bulkImport(MultipartFile file) throws LegalDatabaseException {
        int maxRows = 500;
        final int[] rowCount = {0};
        int headRowNumber = 18;
        List<LegalPending> pendingList = new ArrayList<>();
        try (InputStream inputStream = file.getInputStream()) {
            EasyExcel.read(inputStream, LegalPending.class, new ReadListener<LegalPending>() {
                @Override
                public void invoke(LegalPending data, AnalysisContext context) {

                    // 处理读取到的数据
                    pendingList.add(data);
                    rowCount[0]++;
                    if (rowCount[0] >= maxRows) {
                        throw new LegalDatabaseException("Reached the maximum row limit of " + maxRows + ". Stopping read");
                    }
                }

                @Override
                public void doAfterAllAnalysed(AnalysisContext context) {
                    // 读取完成后的逻辑
                }
            }).sheet().headRowNumber(headRowNumber).doRead();
        } catch (Exception e) {
            throw new LegalDatabaseException(e.getMessage());
        }
        return pendingList;
    }

    @Override
    public void bulkInsertLegalPending(List<LegalPending> list) {
        legalPendingMapper.bulkInsertLegalPending(list);
    }

//    /**
//     * 批量删除Pending
//     *
//     * @param ids 需要删除的Pending主键
//     * @return 结果
//     */
//    @Override
//    public int deleteLegalPendingByIds(Long[] ids)
//    {
//        return legalPendingMapper.deleteLegalPendingByIds(ids);
//    }
//
//    /**
//     * 删除Pending信息
//     *
//     * @param id Pending主键
//     * @return 结果
//     */
//    @Override
//    public int deleteLegalPendingById(Long id)
//    {
//        return legalPendingMapper.deleteLegalPendingById(id);
//    }
}
