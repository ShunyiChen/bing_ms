package com.ruoyi.filingsystem.dto;

import lombok.Data;

@Data
public class ChangeTypeDTO {
    private String[] fileIDs;
    private String newFileType;
}
