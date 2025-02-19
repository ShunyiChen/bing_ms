package com.ruoyi.filingsystem.dto;

import lombok.Data;
/**
 * CDI登录返回DTO
 *
 * @author Simeon
 */
@Data
public class CDILoginTokenDTO {
    private String status;
    private Res response;

    @Data
    public static class Res {
        private String token;
    }
}
