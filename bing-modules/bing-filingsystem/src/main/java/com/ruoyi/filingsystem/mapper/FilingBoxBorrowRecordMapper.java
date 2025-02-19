package com.ruoyi.filingsystem.mapper;

import com.ruoyi.filingsystem.domain.FilingBoxBorrowRecord;
import com.ruoyi.filingsystem.dto.ExportRecordsDTO;
import com.ruoyi.filingsystem.dto.GetBoxesOverdueDTO;
import com.ruoyi.filingsystem.dto.SearchBoxBorrowRecordsDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 盒子借阅记录-整箱借阅Mapper接口
 * 
 * @author victor.xiong
 * @date 2024-11-18
 */
public interface FilingBoxBorrowRecordMapper {

    /**
     * 获取借出逾期超过1个月的盒子列表
     *
     * @param dto
     * @return
     */
    List<FilingBoxBorrowRecord> retrieveBoxesOverdueByOneMonth(GetBoxesOverdueDTO dto);

    /**
     * 新增盒子借阅记录
     *
     * @param deptId
     * @param list
     */
    void insertBoxBorrowRecords(@Param("deptId") Long deptId, @Param("list") List<FilingBoxBorrowRecord> list);


    /**
     * 查询盒子借阅记录列表
     *
     * @param dto
     * @return
     */
    List<FilingBoxBorrowRecord> searchBoxBorrowRecords(SearchBoxBorrowRecordsDTO dto);

    /**
     * 批量修改charge code
     *
     * @param deptId
     * @param lstId
     * @param chargeCode
     * @param returnChargeCode
     * @param updateBy
     */
    void changeChargeCode(@Param("deptId") Long deptId, @Param("lstId") List<Long> lstId,
                          @Param("chargeCode") String chargeCode,
                          @Param("returnChargeCode") String returnChargeCode,
                          @Param("updateBy") String updateBy);

    /**
     * 为导出创建临时表
     */
    void createTempTable();

    /**
     * 插入临时表
     *
     * @param multipleSelection
     */
    void insertTempTable(@Param("list") List<ExportRecordsDTO.Key> multipleSelection);

    /**
     * 删除临时表
     */
    void dropTempTable();

    /**
     * 批量修改借阅记录状态
     *
     * @param deptId
     * @param lstId
     * @param status
     * @param returnChargeCode
     * @param updateBy
     */
    void changeBoxBorrowStatus(@Param("deptId") Long deptId, @Param("lstId") List<Long> lstId,
                               @Param("status") Integer status,
                               @Param("returnChargeCode") String returnChargeCode,
                               @Param("updateBy") String updateBy);

    /**
     * 获取所以逾期1月未还的借阅记录
     *
     * @return
     */
    List<FilingBoxBorrowRecord> retrieveAllOverdueBoxes();

    /**
     * 批量更新通知状态
     *
     * @param deptId
     * @param lstId
     */
    void batchUpdateNotice(@Param("deptId") Long deptId, @Param("lstId") List<Long> lstId);
}
