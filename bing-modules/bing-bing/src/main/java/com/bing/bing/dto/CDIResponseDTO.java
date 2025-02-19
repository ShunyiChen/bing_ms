package com.bing.bing.dto;

import lombok.Data;

@Data
public class CDIResponseDTO {
    private int status;
    private boolean success;
    private String msg;
    private String msgDev;
}
