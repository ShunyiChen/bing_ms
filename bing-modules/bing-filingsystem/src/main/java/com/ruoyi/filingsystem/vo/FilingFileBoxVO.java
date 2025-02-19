package com.ruoyi.filingsystem.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilingFileBoxVO implements Serializable {
    private String boxPrefix;
    private String boxSuffix;
    private Long deptId;

}
