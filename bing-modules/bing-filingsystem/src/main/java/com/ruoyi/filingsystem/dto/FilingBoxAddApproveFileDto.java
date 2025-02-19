package com.ruoyi.filingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilingBoxAddApproveFileDto {



    private List<String> approveFileUrlIds;
    private String ossUrl;
    private Boolean isExcelUrl;
}
