package com.ruoyi.filingsystem.dto;

import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Data;

@Data
public class RemoveAttachmentDTO extends BaseEntity {
    private Long fileId;
    private Long boxId;
    private Long deptId;
}
