package com.ruoyi.filingsystem.vo;

import com.ruoyi.common.core.annotation.Excel;
import lombok.Data;

@Data
public class FilingOnLoanRecordVO {
    @Excel(name = "File ID")
    private String oldSystemfileID;
    @Excel(name = "Group")
    private String clientGroupName;
    @Excel(name = "Client Name")
    private String clientCompanyName;
    @Excel(name = "Client Code")
    private String clientNumber;
    @Excel(name = "File Type")
    private String fileType;
    @Excel(name = "Y.E.D.")
    private String yearEndDate;
    @Excel(name = "A.R.D.")
    private String auditReportDate;
    @Excel(name = "Retention Date")
    private String retentionDate;
    @Excel(name = "Expire Date")
    private String expireDate;
    @Excel(name = "Description")
    private String fileDescription;
    @Excel(name = "Staff Name")
    private String staffName;
    @Excel(name = "Staff ID")
    private String staffId;
    @Excel(name = "Borrow Date")
    private String borrowDate;
    @Excel(name = "Location")
    private String areaLocation;
    @Excel(name = "Box No")
    private String boxNumber;
    @Excel(name = "Remarks")
    private String fileremarks;
}
