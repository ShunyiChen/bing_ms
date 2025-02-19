package com.ruoyi.filingsystem.dto;

import lombok.Data;

import java.util.List;

@Data
public class DestructionFileConfirmDto {
    private String[] fileIDs;
    private String destructionTime;
    private String destructionPeople;
}
