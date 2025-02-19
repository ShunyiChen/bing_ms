package com.ruoyi.filingsystem.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FilingFetchReturnFileVO {
    private String oldSystemfileID;
    private String clientGroupName;
    private String clientCompanyName;
    private LocalDateTime yearEndDate;
    private String fileType;
    private String fileDescription;
    private String status;
}
