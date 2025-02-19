package com.ruoyi.filingsystem.vo;

import com.ruoyi.common.core.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilingBorrowRecordVO {
    @Excel(name = "File ID")
    private String oldSystemfileID;
    @Excel(name = "Group")
    private String clientGroupName;
    @Excel(name = "Client Name")
    private String clientCompanyName;
    @Excel(name = "Y.E.D.", dateFormat="yyyy-MM-dd")
    private Date yearEndDate;
    @Excel(name = "File Type")
    private String fileType;
    @Excel(name = "Description")
    private String fileDescription;
    @Excel(name = "Location")
    private String areaLocation;
    @Excel(name = "Remarks")
    private String fileremarks;
    @Excel(name = "Borrow Date", dateFormat="yyyy-MM-dd")
    private Date loanDate;
    @Excel(name = "Box No")
    private String boxNumber;
}
