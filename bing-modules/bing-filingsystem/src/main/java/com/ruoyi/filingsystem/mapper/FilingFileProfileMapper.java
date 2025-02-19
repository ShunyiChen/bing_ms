package com.ruoyi.filingsystem.mapper;

import com.ruoyi.filingsystem.domain.FilingFileProfile;
import com.ruoyi.filingsystem.dto.BoxNumberExistDTO;
import com.ruoyi.filingsystem.dto.SearchFileDTO;
import com.ruoyi.filingsystem.dto.UpdateFileDTO;
import com.ruoyi.filingsystem.dto.UpdateMoveToDTO;
import com.ruoyi.filingsystem.vo.FilingBorrowUnnoticedVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文档_管理人员操作Mapper接口
 * 
 * @author ruoyi
 * @date 2024-03-25
 */
public interface FilingFileProfileMapper
{
    int checkBoxNumberExists(BoxNumberExistDTO dto);

    int updateMoveTo(UpdateMoveToDTO dto);

    int batchUpdateFileTypes(List<FilingFileProfile> dto);

    int batchUpdateDPN(List<FilingFileProfile> dto);

    List<FilingFileProfile> exportBarcodeList(SearchFileDTO dto);

    /**
     * 查询文档_管理人员操作
     * 
     * @param fileID 文档_管理人员操作主键
     * @return 文档_管理人员操作
     */
    FilingFileProfile selectFilingFileProfileByFileID(String fileID);

    /**
     * 新增文档_管理人员操作
     * 
     * @param filingFileProfile 文档_管理人员操作
     * @return 结果
     */
    int insertFilingFileProfile(FilingFileProfile filingFileProfile);

    /**
     * 修改文档_管理人员操作
     * 
     * @param updateFileDTO 文档_管理人员操作
     * @return 结果
     */
    int updateFilingFileProfile(UpdateFileDTO updateFileDTO);

    /**
     * 多条件查询Profile表
     * @param searchFileDto
     * @return
     */
    List<FilingFileProfile> searchProfileListByDto(SearchFileDTO searchFileDto);

    /**
     * 创建SelectedFileID临时表
     */
    void createTempTable();

    /**
     * 往临时表插入数据
     * @param multipleSelection
     */
    void insertTempTable(@Param("array") String[] multipleSelection);

    /**
     * 删除临时表
     */
    void dropTempTable();

    List<FilingFileProfile> selectFileByFileIDs(@Param("array") String[] oldSystemfileIDs);

    FilingFileProfile selectMaxFileIdByArealocation(@Param("area") String arealocation);

    List<FilingBorrowUnnoticedVO> findOver6MonthsOutstandingUnnoticed();

    void updateUnnoticedStatus(List<Long> borrowIDs);

    List<FilingFileProfile> findByFileIds(String[] fileIDs);
}
