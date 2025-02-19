package com.ruoyi.filingsystem.dto;

import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.List;

@Data
public class UpdateBoxBorrowStateDTO extends BaseEntity {
    private Long deptId;
    private List<Long> lstId;
    private Integer borrowState;
    private String borrowerStaffId;
    private String borrowerName;
}
