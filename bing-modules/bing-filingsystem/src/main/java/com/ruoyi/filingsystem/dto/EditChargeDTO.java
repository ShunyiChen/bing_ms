package com.ruoyi.filingsystem.dto;

import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Data;

@Data
public class EditChargeDTO extends BaseEntity {
    private Long id;
    private Long deptId;
    private String chargeCode;
}
