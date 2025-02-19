package com.ruoyi.filingsystem.dto;

import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Data;

@Data
public class InsertFileToBoxDTO extends BaseEntity {
    /** BoxNumber */
    private String boxNumber;
    /** 确认文件ID数组 */
    private String[] fileIDs;
    private Long deptId;
    /** 页码 */
    private Integer pageNum;
    /** 每页显示最大行数 */
    private Integer pageSize;
}
