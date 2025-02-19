package com.ruoyi.filingsystem.dto;

import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilingBoxSearchOperationRecordDto  extends BaseEntity {

    private Long deptId;

    private String chargeCode;

    private String vendorBarcode;

    private String boxNumber;

    /** pageNum(非字段) 页数 */
    private Integer pageNum;
    /** pageSize(非字段) 每页数量 */
    private Integer pageSize;
    /** 选中的box id  （主键，部分导出box使用）*/
    private String[] multipleSelection;

}
