package com.ruoyi.filingsystem.vo;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Data;

@Data
public class FilingExportBarcodeVO extends BaseEntity {
    @Excel(name = "Fileid")
    private String oldSystemfileID;

    @Excel(name = "ccode")
    private String clientNumber;

    @Excel(name = "Group")
    private String clientGroupName;

    @Excel(name = "Client Name")
    private String clientCompanyName;

    @Excel(name = "D")
    private String d;

    @Excel(name = "M")
    private String m;

    @Excel(name = "Y")
    private String y;

    @Excel(name = "CN1")
    private String cn1;

    @Excel(name = "CN2")
    private String cn2;

    @Excel(name = "CN3")
    private String cn3;

    @Excel(name = "DE1")
    private String de1;

    @Excel(name = "DE2")
    private String de2;
}
