package com.ruoyi.filingsystem.dto;

import lombok.Data;

@Data
public class InsertBorrowConfirmDTO {
    private String staffID;
    private String staffName;
    private String staffEmail;
    private Integer status;
    private String fileID;
    private long deptId;
}
