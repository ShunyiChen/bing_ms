package com.ruoyi.filingsystem.dto;

import lombok.Data;

@Data
public class CDIResponseDTO {
    private int status;
    private boolean success;
    private String msg;
    private String msgDev;
}
