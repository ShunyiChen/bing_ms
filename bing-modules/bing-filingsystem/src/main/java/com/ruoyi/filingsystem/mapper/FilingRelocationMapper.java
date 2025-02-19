package com.ruoyi.filingsystem.mapper;

import com.ruoyi.filingsystem.domain.FilingFileProfile;
import com.ruoyi.filingsystem.domain.FilingLocation;
import com.ruoyi.filingsystem.dto.*;
import com.ruoyi.filingsystem.vo.FilingBoxFilesVO;
import com.ruoyi.filingsystem.vo.FilingFileBoxVO;

import java.util.List;
import java.util.Map;

public interface FilingRelocationMapper {
    List<FilingBoxFilesVO> searchByBoxNumber(InsertFileToBoxDTO dto);

    List<FilingBoxFilesVO> searchFileIDsByBoxNumber(List<SearchFileIDsDTO> list);

    void createBoxNumber(CreateBoxDTO dto);

    FilingFileProfile fetchByFileID(FetchFileDTO dto);

    int insertFileToBox(InsertFileToBoxDTO dto);

    int moveFileToBox(List<MoveFileToBoxDTO> params);

    int existCheck(CreateBoxDTO dto);

    List<FilingLocation> findLocationList(GetLocationsDTO dto);

    List<FilingLocation> findAllLocations();

    int moveToOffice(List<MoveFileToOfficeDTO> params);

    List<FilingFileBoxVO> findAllBoxes(ChecksIfAllBoxesExistDTO dto);
}
