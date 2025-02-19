package com.ruoyi.filingsystem.service;

import com.ruoyi.filingsystem.domain.FilingFileProfile;
import com.ruoyi.filingsystem.dto.*;
import com.ruoyi.filingsystem.vo.FilingExportBarcodeVO;

import java.util.List;

/**
 * 文档_管理人员操作Service接口
 * 
 * @author ruoyi
 * @date 2024-03-25
 */
public interface IFilingFileProfileService
{
    int checkBoxNumberExists(BoxNumberExistDTO dto);

    FilingFileProfile moveToLocation(MoveToLocationDTO dto);

    List<FilingFileProfile> changeType(ChangeTypeDTO dto);

    List<FilingFileProfile> changeDPN(ChangeDPNDTO dto);

    /**
     * 导出Barcode列表
     *
     * @param dto
     * @return
     */
    List<FilingExportBarcodeVO> exportBarcodeList(SearchFileDTO dto);

    /**
     * 导出Profile数据列表
     *
     * @param dto
     * @return
     */
    List<FilingFileProfile> exportData(SearchFileDTO dto);

    /**
     * 查询文档_管理人员操作
     * 
     * @param fileID 文档_管理人员操作主键
     * @return 文档_管理人员操作
     */
    FilingFileProfile selectFilingFileProfileByFileID(String fileID);

    /**
     * 查询文档_管理人员操作列表
     * 
     * @param searchFileDto 文档_管理人员操作
     * @return 文档_管理人员操作集合
     */
    List<FilingFileProfile> searchProfileListByDto(SearchFileDTO searchFileDto);

    /**
     * 新增文档_管理人员操作
     * 
     * @param addFileDto 文档_管理人员操作
     * @return 结果
     */
    Object insertFilingFileProfile(AddFileDto addFileDto);

    /**
     * 修改文档_管理人员操作
     *
     * @param updateFileDTO 文档_管理人员操作
     */
    void updateFilingFileProfile(UpdateFileDTO updateFileDTO);

    void reminderOver6MonthsOutstandingFiles();
}
