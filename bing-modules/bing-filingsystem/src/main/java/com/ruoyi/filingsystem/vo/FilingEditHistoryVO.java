package com.ruoyi.filingsystem.vo;

import com.ruoyi.common.core.annotation.Excel;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FilingEditHistoryVO {
    private Long fileID;
    @Excel(name = "File ID")
    private String oldSystemfileID;
    @Excel(name = "Group")
    private String clientGroupName;
    @Excel(name = "Client Name")
    private String clientCompanyName;
    @Excel(name = "Client Code")
    private String clientNumber;
    @Excel(name = "Y.E.D.")
    private String yearEndDate;
    @Excel(name = "A.R.D.")
    private String auditReportDate;
    @Excel(name = "Retention Date")
    private String retentionDate;
    @Excel(name = "File Type")
    private String fileType;
    @Excel(name = "Description")
    private String fileDescription;
    @Excel(name = "Location")
    private String areaLocation;
    @Excel(name = "Remarks")
    private String fileremarks;
    @Excel(name = "Creation Date")
    private String creationDate;
    @Excel(name = "Box No")
    private String boxNumber;
    @Excel(name = "Moved To")
    private String moveTo;
    @Excel(name = "Requester")
    private String requesterName;
    @Excel(name = "Editor")
    private String editor;
    @Excel(name = "Action")
    private String action;
    @Excel(name = "Request Date")
    private String requestDate;
    @Excel(name = "Update Time")
    private String updateTime;
    private Integer borrowState;
    private String engagementNumber;
    private String engagementEIC;
    private String engagementEICEmail;
    private String engagementPartner;
    private String engagementPartnerEmail;
    private String barCode;
    private String borrowerStaffId;
    private String borrower;
    private String avail;
    private LocalDateTime DestructionTime;
    private String DestructionPeople;
    private String staffId;
    private Integer retentionPeriod;
    private String isMoved;
    private String fileState;
    private String ccId;
    private String isDPN;
    private Long deptId;
}
