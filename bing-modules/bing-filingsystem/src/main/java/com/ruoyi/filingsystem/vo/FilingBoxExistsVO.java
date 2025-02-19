package com.ruoyi.filingsystem.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilingBoxExistsVO {
    private String boxNumber;
    private int count;
}
