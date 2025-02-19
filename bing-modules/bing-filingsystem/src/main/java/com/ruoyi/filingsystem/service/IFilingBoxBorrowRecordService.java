package com.ruoyi.filingsystem.service;

import com.ruoyi.filingsystem.domain.FilingBoxBorrowRecord;
import com.ruoyi.filingsystem.dto.*;

import java.util.List;

/**
 * 盒子借阅记录-整箱借阅Service接口
 * 
 * @author victor.xiong
 * @date 2024-11-18
 */
public interface IFilingBoxBorrowRecordService {
    /**
     * 获取借出逾期超过1个月的盒子列表
     *
     * @param dto 员工LPN
     * @return
     */
    List<FilingBoxBorrowRecord> retrieveBoxesOverdueByOneMonth(GetBoxesOverdueDTO dto);

    /**
     * 插入借阅记录
     *
     * @param dto
     */
    void insertBoxBorrowRecords(AddBoxBorrowRecordsDTO dto);

    /**
     * 查询盒子借阅记录列表
     *
     * @param dto
     * @return
     */
    List<FilingBoxBorrowRecord> searchBoxBorrowRecords(SearchBoxBorrowRecordsDTO dto);

    /**
     * 批量修改ChargeCode
     *
     * @param dto
     */
    void changeChargeCode(ChangeChargeCodeDTO dto);

    /**
     * 导出借阅记录列表
     *
     * @param dto
     * @return
     */
    List<FilingBoxBorrowRecord> exportBoxBorrowRecords(ExportRecordsDTO dto);

    /**
     * 批量修改借阅记录状态
     *
     */
    void changeBoxBorrowStatus(ChangeBoxBorrowStatusDTO dto);

    /**
     * 逾期一个月未还箱子的发出邮件提醒
     */
    void over1MonthsOutstandingBoxes();
}
