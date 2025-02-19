package com.ruoyi.filingsystem.vo;

import com.ruoyi.common.core.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilingFileHistoryVO {
    @Excel(name = "Box No")
    private String boxNumber;
    @Excel(name = "File ID")
    private String oldSystemfileID;
    @Excel(name = "Group")
    private String clientGroupName;
    @Excel(name = "Client Name")
    private String clientCompanyName;
    @Excel(name = "File Type")
    private String fileType;
    @Excel(name = "Binder No")
    private String fileDescription;
    @Excel(name = "Retention Date")
    private String retentionDate;
    @Excel(name = "AuditReport Date")
    private String auditReportDate;
    @Excel(name = "Destruction Officer")
    private String destructionPeople;
    @Excel(name = "File Destruction Date")
    private String destructionTime;
    private String areaLocation;
}
