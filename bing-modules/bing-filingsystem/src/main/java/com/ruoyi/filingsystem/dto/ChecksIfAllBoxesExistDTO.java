package com.ruoyi.filingsystem.dto;

import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.List;

@Data
public class ChecksIfAllBoxesExistDTO extends BaseEntity {
    private List<CheckBoxExistDTO> list;
}
