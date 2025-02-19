package com.ruoyi.filingsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class DestructionFileSearchDto extends BaseEntity {
    private String fileID;
    private String fileType;
    private String boxNumber;
    private String boxPrefix;
    private String boxSuffix;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date retentionDateFrom;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date retentionDateTo;
    private boolean includeDestructionRecords;
    private Long deptId;
    private Integer pageNum;
    private Integer pageSize;
}
