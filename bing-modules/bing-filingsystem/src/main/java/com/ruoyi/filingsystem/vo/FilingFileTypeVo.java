package com.ruoyi.filingsystem.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilingFileTypeVo implements Serializable {


    /** 文件类型，唯一约束 */
    @NotBlank
    private String typeName;

    /** 描述 */
    @NotBlank
    private String fileDescription;
}
