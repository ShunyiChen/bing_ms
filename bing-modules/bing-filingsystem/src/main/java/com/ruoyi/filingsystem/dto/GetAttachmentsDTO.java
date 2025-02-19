package com.ruoyi.filingsystem.dto;

import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.List;

@Data
public class GetAttachmentsDTO extends BaseEntity {
    private List<Long> lstId; //list of box id
    private List<Long> lstDeptId;
}
