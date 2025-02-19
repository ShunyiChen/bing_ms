package com.ruoyi.filingsystem.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilingBoxFilesVO {
    private Long boxID;
    private String boxNumber;
    private String fileIDs;
    private String boxPrefix;
    private String boxSuffix;
    private String isDPNs;
    private String borrowStates;
    private String fileStates;
    private String isMoveds;
    private String areaLocation;
    private Long deptId;
}
