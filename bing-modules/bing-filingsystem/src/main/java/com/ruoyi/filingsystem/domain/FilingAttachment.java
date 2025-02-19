package com.ruoyi.filingsystem.domain;

import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class FilingAttachment extends BaseEntity {
    private Long id;
    private Long deptId;
    private String fileName;
    private String fileType;
    private Integer fileSize;
    private String filePath;
    private String uploader;
    private Date uploadDate;
    private String status;
    private Long boxProfileId;
    private int usage;
}
