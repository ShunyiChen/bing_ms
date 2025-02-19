package com.ruoyi.filingsystem.service;

import com.ruoyi.filingsystem.domain.FilingFileProfile;
import com.ruoyi.filingsystem.dto.DestructionBoxConfirmDTO;
import com.ruoyi.filingsystem.dto.DestructionFileConfirmDto;
import com.ruoyi.filingsystem.dto.DestructionFileSearchDto;
import com.ruoyi.filingsystem.vo.FilingBoxFilesVO;

import java.util.List;

public interface IFilingDestructionService {

    List<FilingFileProfile> getFileList(DestructionFileSearchDto dto);

    List<FilingBoxFilesVO> getBoxList(DestructionFileSearchDto dto);

    int destructFile(DestructionFileConfirmDto dto);

    int destructBox(DestructionBoxConfirmDTO dto);
}
