package com.ruoyi.filingsystem.dto;

import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Data;

@Data
public class MoveFileToBoxDTO extends BaseEntity {
     private String fileID;
     private String newBoxNumber;
}
