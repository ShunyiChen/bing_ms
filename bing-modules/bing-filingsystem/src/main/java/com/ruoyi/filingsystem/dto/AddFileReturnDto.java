package com.ruoyi.filingsystem.dto;

import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddFileReturnDto extends BaseEntity {
    /** 文件类型 */
    @NotBlank
    private String fileType;

    /** 文件类型 */
    @NotBlank
    private String fileID;
}
