package com.ruoyi.filingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilingBoxBorrowBoxConfirmDTO {
    private String staffID;
    private String staffName;
    private String staffEmail;
    private String boxState;
    private String[] areaLocation;
    private String[] deptId;

    private String[] vendorBarcodeList;

}
