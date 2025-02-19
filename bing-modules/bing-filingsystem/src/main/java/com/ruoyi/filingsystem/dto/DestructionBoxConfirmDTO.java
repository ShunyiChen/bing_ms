package com.ruoyi.filingsystem.dto;

import lombok.Data;

@Data
public class DestructionBoxConfirmDTO {
    private Long[] boxIds;
    private String[] boxNumbers;
    private String destroyer;
}
