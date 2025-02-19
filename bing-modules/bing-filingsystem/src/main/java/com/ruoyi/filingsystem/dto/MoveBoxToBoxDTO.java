package com.ruoyi.filingsystem.dto;

import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Data;

@Data
public class MoveBoxToBoxDTO extends BaseEntity {
    /** 源BoxNumber */
    private String boxNumber;
    /** 目标BoxNumber */
    private String boxNumberMoveTo;
    private Long deptId;
}
