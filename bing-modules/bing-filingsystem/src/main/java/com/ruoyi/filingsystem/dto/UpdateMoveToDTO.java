package com.ruoyi.filingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMoveToDTO {
    private String[] fileIDs;
    private String moveTo;
    private String isMoved;
}
