package com.ruoyi.filingsystem.dto;

import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Data;

@Data
public class RecoverDTO extends BaseEntity {
    private Long histID;
    private String fileID;
}
