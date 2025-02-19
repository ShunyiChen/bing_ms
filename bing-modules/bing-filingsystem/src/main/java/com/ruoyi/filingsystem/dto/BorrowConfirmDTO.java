package com.ruoyi.filingsystem.dto;

import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowConfirmDTO extends BaseEntity {
    private String staffID;
    private String staffName;
    private String staffEmail;
    private Integer status;
    private String[] fileIDs;
    private long[] deptIds;
}
