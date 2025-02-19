package com.ruoyi.filingsystem.dto;

import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoveToLocationDTO extends BaseEntity {
    private String fileID;
    private String moveTo;
    private String isMoved;
}
