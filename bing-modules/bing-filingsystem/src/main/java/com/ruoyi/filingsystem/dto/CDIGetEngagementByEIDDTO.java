package com.ruoyi.filingsystem.dto;

import lombok.Data;

@Data
public class CDIGetEngagementByEIDDTO extends CDIResponseDTO {
    private Response[] response;
    @Data
    public static class Response {
        private String C_LNM;
        private String EID;
    }
}
