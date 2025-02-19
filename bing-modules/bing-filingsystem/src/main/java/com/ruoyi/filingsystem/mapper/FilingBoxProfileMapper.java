package com.ruoyi.filingsystem.mapper;

import com.ruoyi.filingsystem.domain.FilingAttachment;
import com.ruoyi.filingsystem.domain.FilingBoxOperationRecord;
import com.ruoyi.filingsystem.domain.FilingBoxProfile;
import com.ruoyi.filingsystem.dto.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 盒子_仅整箱操作Mapper接口
 * 
 * @author ruoyi
 * @date 2024-11-18
 */
public interface FilingBoxProfileMapper 
{
    /**
     * 单个查询
     *
     * @param dto
     * @return
     */
    FilingBoxProfile findBoxProfileByID(FindBoxProfileDTO dto);

    /**
     * 搜索列表
     *
     * @param dto
     * @return
     */
    List<FilingBoxProfile> searchBoxProfileList(SearchBoxProfileDTO dto);

    /**
     * 新增盒子_仅整箱操作
     * 
     * @param dto 盒子_仅整箱操作
     * @return 结果
     */
    int insertFilingBoxProfile(AddBoxProfileDTO dto);

    /**
     * 修改盒子
     * 
     * @param dto 盒子_仅整箱操作
     */
    void updateFilingBoxProfile(UpdateBoxProfileDTO dto);

    /**
     * 创建临时表
     */
    void createTempTable(@Param("tbName") String tbName);

    /**
     * 插入临时表
     *
     * @param tbName
     * @param multipleSelection
     */
    void insertTempTable(@Param("tbName") String tbName,
                         @Param("list") List<ExportRecordsDTO.Key> multipleSelection);

    /**
     * 删除临时表
     *
     * @param tbName
     */
    void dropTempTable(@Param("tbName") String tbName);

    /**
     * 批量销毁箱子
     *
     * @param deptId
     * @param lstId
     * @param updateBy
     * @param chargeCode
     * @return
     */
    int destroyBox(@Param("deptId") Long deptId,
                   @Param("lstId") List<Long> lstId,
                   @Param("updateBy") String updateBy,
                   @Param("chargeCode") String chargeCode);

    /**
     * 批量提取箱子
     *
     * @param deptId
     * @param lstId
     * @param updateBy
     * @param chargeCode
     * @return
     */
    int permOutBox(@Param("deptId") Long deptId,
                   @Param("lstId") List<Long> lstId,
                   @Param("updateBy") String updateBy,
                   @Param("chargeCode") String chargeCode);

    /**
     * 批量修改ChargeCode
     *
     * @param deptId
     * @param lstId
     * @param updateBy
     * @param chargeCode
     * @return
     */
    int editCharge(@Param("deptId") Long deptId,
                   @Param("lstId") List<Long> lstId,
                   @Param("updateBy") String updateBy,
                   @Param("chargeCode") String chargeCode);

    /**
     * 批量插入附件记录
     *
     * @param list
     * @param deptId
     * @return
     */
    int insertApprovalFiles(@Param("list") List<FilingAttachment> list, @Param("deptId") Long deptId);

    /**
     * 根据多个boxId查询附件列表
     *
     * @param dto
     * @return
     */
    List<FilingAttachment> findFilingAttachmentList(FindAttachmentsDTO dto);

    /**
     * 使附件变无效
     *
     * @param dto
     */
    void deactivateApprovalFile(RemoveAttachmentDTO dto);

    /**
     * BoxEditHistory多条件查找
     *
     * @param dto
     * @return
     */
    List<FilingBoxOperationRecord> searchBoxEditHistoryList(SearchBoxProfileDTO dto);

    /**
     * 批量更新盒子借阅状态
     *
     * @param dto
     */
    void updateBoxBorrowState(UpdateBoxBorrowStateDTO dto);














//
//    List<FilingBoxProfile> selectFilingBoxProfileByIdS(@Param("idList") List<String> ids);
//
//    String getDBDateTimeNow();
//
//    FilingBoxProfile SearchBoxByVenderBarcode(@Param("code") String vendorBarcode);
//
//    Integer updateBoxBorrowState(@Param("List") String[] vendorBarcodeList,
//                                 @Param("staffId") String staffID,
//                                 @Param("email")String staffEmail,
//                                 @Param("name") String staffName,
//                                 @Param("BoxState") String boxState);
//
//    void updateBoxBorrowState_Return(@Param("List")String[] venderBarcodeList,@Param("BoxState") String boxState);
//
//    void addApproveFileOSSurl(@Param("url")String ossUrl,@Param("List") List<String> approveFileUrlIds);
//
//    void addExcelOSS(@Param("url")String ossUrl,@Param("List") List<String> approveFileUrlIds);
}
