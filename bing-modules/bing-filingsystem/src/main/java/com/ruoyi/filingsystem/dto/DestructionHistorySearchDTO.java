package com.ruoyi.filingsystem.dto;

import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Data;

@Data
public class DestructionHistorySearchDTO extends BaseEntity {
    private String fileID;
    private String fileType;
    private String boxNumber = "";
    private String retentionDateFrom;
    private String retentionDateTo;
    private Long deptId; // 盒子检索用
    private Integer pageNum;
    private Integer pageSize;
}
