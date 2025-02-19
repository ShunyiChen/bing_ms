package com.ruoyi.filingsystem.dto;

import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Data;

@Data
public class BoxNumberExistDTO extends BaseEntity {
    private String boxNumber;
    private Long deptId;
}
