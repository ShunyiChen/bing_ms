package com.ruoyi.filingsystem.dto;

import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.List;

@Data
public class SearchFileIDsDTO extends BaseEntity {
    private String boxNumber;
    private Long deptId;
}
