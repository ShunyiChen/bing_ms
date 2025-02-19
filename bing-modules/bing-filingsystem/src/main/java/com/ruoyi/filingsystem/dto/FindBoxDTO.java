package com.ruoyi.filingsystem.dto;

import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Data;

@Data
public class FindBoxDTO extends BaseEntity {
    private String boxPrefix;
    private String boxSuffix;
    private Long deptId;
}
