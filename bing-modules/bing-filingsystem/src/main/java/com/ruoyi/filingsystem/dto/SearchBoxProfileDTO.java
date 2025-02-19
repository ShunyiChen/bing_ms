package com.ruoyi.filingsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchBoxProfileDTO extends BaseEntity {
    /** 部门/城市ID */
    private Long deptId;
    /** 供应商提供的盒子编码 */
    private String vendorBarcode;
    /** 盒子状态（正常、销毁） */
    private String boxStatus;
    /** 盒子所属业务部门 */
    private String boxDepartment;
    /** 盒子号 */
    private String boxNo;
    /** 项目编号-费用统计用  手动输入   八位校验 */
    private String chargeCode;
    /** 批准人的LPN    */
    private String approverLPN;
    /** 批准人的LPN    */
    private String approverEmail;
    /** 申请人的LPN    */
    private String requesterLPN;
    /** 申请人的Email   */
    private String requesterEmail;
    /** 更新日期范围 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTimeFrom;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTimeTo;
    /** Retention日期范围 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date retentionDateFrom;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date retentionDateTo;
    /** 销毁日期范围 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date destructionTimeFrom;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date destructionTimeTo;
    /** 创建日期范围 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDateFrom;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDateTo;
    private String boxRemark;
    private String accountCode;
    private Integer borrowState;

    /** pageNum(非字段) 页数 */
    private Integer pageNum;
    /** pageSize(非字段) 每页数量 */
    private Integer pageSize;
    /** 选中的box number */
    private List<Key> multipleSelection;

    @Data
    @AllArgsConstructor
    public static class Key {
        private Long id;
        private Long deptId;
    }
}
