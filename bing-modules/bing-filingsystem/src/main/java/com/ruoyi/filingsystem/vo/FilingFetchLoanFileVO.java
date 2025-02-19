package com.ruoyi.filingsystem.vo;

import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilingFetchLoanFileVO extends BaseEntity {
    private String oldSystemfileID;
    private String clientGroupName;
    private String clientCompanyName;
    private Date yearEndDate;
    private String fileType;
    private String fileDescription;
    private String status;
    private long deptId;
    private String areaLocation;
}
