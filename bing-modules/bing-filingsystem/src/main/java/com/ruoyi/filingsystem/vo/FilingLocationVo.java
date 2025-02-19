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
public class FilingLocationVo implements Serializable {
    /** 位置名称，缩写 唯一约束 */
    @NotBlank
    private String locationName;

    /** 位置描述 */
    @NotBlank
    private String description;


}
