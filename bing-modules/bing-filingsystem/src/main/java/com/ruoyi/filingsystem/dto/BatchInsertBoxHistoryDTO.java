package com.ruoyi.filingsystem.dto;

import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BatchInsertBoxHistoryDTO extends BaseEntity {
    private Long deptId;
    private List<Long> lstId;
    private String action;
    private String operator;
}
