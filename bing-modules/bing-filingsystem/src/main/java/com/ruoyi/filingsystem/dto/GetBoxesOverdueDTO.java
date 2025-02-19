package com.ruoyi.filingsystem.dto;

import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Data;

@Data
public class GetBoxesOverdueDTO extends BaseEntity {
    private String staffId;
    private String staffName;
    /** pageNum(非字段) 页数 */
    private Integer pageNum;
    /** pageSize(非字段) 每页数量 */
    private Integer pageSize;
}
