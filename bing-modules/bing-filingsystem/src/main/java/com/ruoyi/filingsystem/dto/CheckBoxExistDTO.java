package com.ruoyi.filingsystem.dto;

import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Data;

@Data
public class CheckBoxExistDTO extends BaseEntity {
    private String prefix;
    private String suffix;
    private Long deptId;
}
