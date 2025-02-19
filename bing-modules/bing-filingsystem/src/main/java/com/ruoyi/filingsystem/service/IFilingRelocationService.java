package com.ruoyi.filingsystem.service;

import com.ruoyi.filingsystem.domain.FilingFileProfile;
import com.ruoyi.filingsystem.domain.FilingLocation;
import com.ruoyi.filingsystem.dto.*;
import com.ruoyi.filingsystem.vo.FilingBoxExistsVO;
import com.ruoyi.filingsystem.vo.FilingBoxFilesVO;
import com.ruoyi.filingsystem.vo.FilingCreateBoxVO;

import java.util.List;

public interface IFilingRelocationService {
    List<FilingBoxFilesVO> searchByBoxNumber(InsertFileToBoxDTO dto);

    void createBoxNumber(CreateBoxDTO dto);

    FilingFileProfile fetchByFileID(FetchFileDTO dto);

    int insertFileToBox(InsertFileToBoxDTO dto);

    int moveBoxToBox(MoveBoxToBoxDTO dto);

    int moveFileToBox(List<MoveFileToBoxDTO> params);

    int moveFileToOffice(List<MoveFileToOfficeDTO> params);

    int existCheck(CreateBoxDTO dto);

    List<FilingLocation> getLocationList(GetLocationsDTO dto);

    List<FilingLocation> getAllLocations();

    List<FilingBoxExistsVO> checksIfAllBoxesExist(ChecksIfAllBoxesExistDTO dto);
}
