package com.ruoyi.filingsystem.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilingCreateBoxVO {
    private String boxNumber;
    private String areaLocation;
    private String fileIDs;
    private Long deptId;
    private String boxPrefix;
    private String boxSuffix;
}
