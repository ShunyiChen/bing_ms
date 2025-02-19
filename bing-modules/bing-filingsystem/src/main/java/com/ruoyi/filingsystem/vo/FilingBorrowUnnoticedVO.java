package com.ruoyi.filingsystem.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilingBorrowUnnoticedVO {
    private Long borrowID;
    private String oldSystemfileID;
    private String fileDescription;
    private String clientGroupName;
    private String clientCompanyName;
    private String clientNumber;
    private String fileType;
    private LocalDateTime yearEndDate;
    private String areaLocation;
    private String staffId;
    private String staffName;
    private LocalDateTime LoanDate;
    private String staffEmail;
}
