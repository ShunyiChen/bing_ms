package com.ruoyi.filingsystem.mapper;

import com.ruoyi.filingsystem.domain.FilingFileProfile;
import com.ruoyi.filingsystem.dto.DestructionBoxConfirmDTO;
import com.ruoyi.filingsystem.dto.DestructionFileConfirmDto;
import com.ruoyi.filingsystem.dto.DestructionFileSearchDto;
import com.ruoyi.filingsystem.dto.SearchFileIDsDTO;
import com.ruoyi.filingsystem.vo.FilingBoxFilesVO;

import java.util.List;

public interface FilingDestructionMapper {

    List<FilingFileProfile> findFileList(DestructionFileSearchDto dto);

    List<FilingBoxFilesVO> findBoxList(DestructionFileSearchDto dto);

    List<FilingBoxFilesVO> searchFileIDsByBoxNumber(List<SearchFileIDsDTO> list);

    int updateFileProfile(DestructionFileConfirmDto dto);

    List<String> findFileIDsByBoxes(DestructionBoxConfirmDTO dto);

    void updateFileBox(DestructionBoxConfirmDTO dto);

    void insertFileBoxFileID(DestructionBoxConfirmDTO dto);
}
