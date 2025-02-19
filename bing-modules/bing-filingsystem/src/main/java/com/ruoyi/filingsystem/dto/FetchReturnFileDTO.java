package com.ruoyi.filingsystem.dto;

import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FetchReturnFileDTO extends BaseEntity {
    private String fileID;
}
