package com.ruoyi.filingsystem.vo;

import com.ruoyi.common.core.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilingBoxHistoryVO {
    @Excel(name = "Box No")
    private String boxNumber;
    @Excel(name = "File ID")
    private String fileIds;
    @Excel(name = "Destruction Officer")
    private String destroyer;
    @Excel(name = "File Destruction Date")
    private String destroyTime;
    private String areaLocation;
}
