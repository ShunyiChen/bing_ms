package com.ruoyi.filingsystem.service;

import com.ruoyi.filingsystem.domain.FilingAttachment;
import com.ruoyi.filingsystem.domain.FilingBoxOperationRecord;
import com.ruoyi.filingsystem.domain.FilingBoxProfile;
import com.ruoyi.filingsystem.dto.*;
import com.ruoyi.filingsystem.vo.FilingStaffVO;

import java.util.List;


/**
 * 盒子_仅整箱操作Service接口
 * 
 * @author ruoyi
 * @date 2024-11-18
 */
public interface IFilingBoxProfileService 
{
    /**
     * 根据ID查询
     *
     * @param dto
     * @return
     */
    FilingBoxProfile findBoxProfileByID(FindBoxProfileDTO dto);

    /**
     * BoxProfile多条件查询
     *
     * @param dto
     * @return
     */
    List<FilingBoxProfile> searchBoxProfileList(SearchBoxProfileDTO dto);

    /**
     * 新增盒子_仅整箱操作
     * 
     * @param dto 盒子_仅整箱操作
     * @return 新增对象
     */
    AddBoxReturnDTO insertFilingBoxProfile(AddBoxProfileDTO dto);

    /**
     * 修改盒子_仅整箱操作
     * 
     * @param dto 盒子_仅整箱操作
     */
    void updateFilingBoxProfile(UpdateBoxProfileDTO dto);

    /**
     * BoxProfile 导出
     *
     * @param dto
     * @return
     */
    List<FilingBoxProfile> exportSelectedData(ExportRecordsDTO dto);

    /**
     * 批量销毁箱子
     *
     * @param array
     * @return
     */
    int destroyBoxes(DestroyBoxProfileDTO[] array);

    /**
     * 批量提取箱子
     *
     * @param array
     * @return
     */
    int permOutBoxes(PermOutBoxProfileDTO[] array);

    /**
     * 编辑Charge code
     *
     * @param array
     * @return
     */
    int editCharge(EditChargeDTO[] array);

    /**
     * 保存附件
     *
     * @param dto
     * @return
     */
    void saveAttachmentFiles(SaveAttachmentsDTO dto);

    /**
     * 获取附件列表
     *
     * @param dto
     * @return
     */
    List<FilingAttachment> getFilingAttachmentList(GetAttachmentsDTO dto);

    /**
     * 删除附件（更改状态为inactive使其变无效）
     *
     * @param dto
     */
    void removeApprovalFiles(RemoveAttachmentDTO dto);

    /**
     * BoxProfileEditHistory多条件查询
     *
     * @param dto
     * @return
     */
    List<FilingBoxOperationRecord> searchBoxEditHistoryList(SearchBoxProfileDTO dto);

    /**
     * BoxEditHistory 导出
     *
     * @param dto
     * @return
     */
    List<FilingBoxOperationRecord> exportBoxEditHistoryList(ExportRecordsDTO dto);

}
