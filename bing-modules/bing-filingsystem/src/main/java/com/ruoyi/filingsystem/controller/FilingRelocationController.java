package com.ruoyi.filingsystem.controller;

import com.github.pagehelper.PageHelper;
import com.ruoyi.common.core.constant.HttpStatus;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.filingsystem.domain.FilingFileProfile;
import com.ruoyi.filingsystem.dto.*;
import com.ruoyi.filingsystem.service.IFilingRelocationService;
import com.ruoyi.filingsystem.vo.FilingBoxFilesVO;
import com.ruoyi.filingsystem.vo.FilingCreateBoxVO;
import com.ruoyi.filingsystem.vo.FilingFileBoxVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("filing/relocation")
public class FilingRelocationController extends BaseController {

    @Autowired
    private IFilingRelocationService service;

    /**************** Insert File to Box ***********************/
    @PostMapping("/searchByBoxNumber")
    public TableDataInfo searchByBoxNumber(@RequestBody InsertFileToBoxDTO dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize()).setReasonable(true);
        List<FilingBoxFilesVO> results = service.searchByBoxNumber(dto);
        return getDataTable(results);
    }

    @PostMapping("/createBoxNumber")
    public AjaxResult createBoxNumber(@RequestBody CreateBoxDTO dto) {
        FilingCreateBoxVO vo = new FilingCreateBoxVO(
                dto.getBoxPrefix() + dto.getBoxSuffix(),
                dto.getAreaLocation(),
                "",
                dto.getDeptId(),
                dto.getBoxPrefix(),
                dto.getBoxSuffix());
        int count = service.existCheck(dto);
        if(count == 0) {
            service.createBoxNumber(dto);
            return new AjaxResult(HttpStatus.SUCCESS, "0", vo);
        }

        InsertFileToBoxDTO d = new InsertFileToBoxDTO();
        d.setBoxNumber(dto.getBoxPrefix()+dto.getBoxSuffix());
        d.setDeptId(dto.getDeptId());
        List<FilingBoxFilesVO> list = service.searchByBoxNumber(d);
        vo.setFileIDs(list.get(0).getFileIDs());
        return new AjaxResult(HttpStatus.SUCCESS, "1", vo);
    }

    @PostMapping("/fetchByFileID")
    public AjaxResult fetchByFileID(@RequestBody FetchFileDTO dto) {
        FilingFileProfile ffp = service.fetchByFileID(dto);
        return success(ffp);
    }

    @PostMapping("/confirm")
    public AjaxResult confirm(@RequestBody InsertFileToBoxDTO dto) {
        return toAjax(service.insertFileToBox(dto));
    }

    /**************** Move Box to Box ***********************/
    @PostMapping("/moveBoxToBox")
    public AjaxResult moveBoxToBox(@RequestBody MoveBoxToBoxDTO dto) {
        return success(service.moveBoxToBox(dto));
    }

    @PostMapping(value = "/existCheck")
    public AjaxResult existCheck(@RequestBody CheckBoxExistDTO dto){
        CreateBoxDTO cbd = new CreateBoxDTO();
        cbd.setBoxPrefix(dto.getPrefix());
        cbd.setBoxSuffix(dto.getSuffix());
        cbd.setDeptId(dto.getDeptId());
        return success(service.existCheck(cbd));
    }

    /**************** Move File to Box ***********************/
    @PostMapping("/moveFileToBox")
    public AjaxResult moveFileToBox(@RequestBody List<MoveFileToBoxDTO> params) {
        return toAjax(service.moveFileToBox(params));
    }

    @PostMapping(value = "/checksIfAllBoxesExist")
    public AjaxResult checksIfAllBoxesExist(@RequestBody ChecksIfAllBoxesExistDTO dto){
        return success(service.checksIfAllBoxesExist(dto));
    }

    /**************** Move File to Office ***********************/
    @PostMapping("/moveFileToOffice")
    public AjaxResult moveFileToOffice(@RequestBody List<MoveFileToOfficeDTO> params) {
        return success(service.moveFileToOffice(params));
    }

    @PostMapping("/getLocationList")
    public AjaxResult getLocationList(GetLocationsDTO dto) {
        return success(service.getLocationList(dto));
    }

    @PostMapping("/getAllLocations")
    public AjaxResult getAllLocations() {
        return success(service.getAllLocations());
    }
}
