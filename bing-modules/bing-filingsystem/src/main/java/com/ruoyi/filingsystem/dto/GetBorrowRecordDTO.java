package com.ruoyi.filingsystem.dto;

import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetBorrowRecordDTO extends BaseEntity {
    private String staffID;
    private String staffName;
    /** pageNum(非字段) 页数 */
    private Integer pageNum;
    /** pageSize(非字段) 每页数量 */
    private Integer pageSize;
}
