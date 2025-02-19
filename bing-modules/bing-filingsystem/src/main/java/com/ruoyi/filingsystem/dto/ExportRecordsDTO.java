package com.ruoyi.filingsystem.dto;

import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class ExportRecordsDTO extends BaseEntity {
    /** 导出选中的box borrow记录 */
    private List<Key> multipleSelection;
    private Long deptId;
    private List<Long> lstId;
    private List<Long> lstDeptId;

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
    private Date updatedTimeFrom;
    private Date updatedTimeTo;
    /** Retention日期范围 */
    private Date retentionDateFrom;
    private Date retentionDateTo;
    /** 销毁日期范围 */
    private Date destructionTimeFrom;
    private Date destructionTimeTo;
    /** 创建日期范围 */
    private Date createdDateFrom;
    private Date createdDateTo;
    private String boxRemark;
    private String accountCode;
    private Integer borrowState;

    /** 盒子ID */
    private Long boxId;
    /** 盒子ID */
    private String boxNumber;
    /** 归还时的ChargeCode */
    private String returnChargeCode;
    /** 员工LPN */
    private String staffId;
    /** 员工姓名 */
    private String staffName;
    /** 员工Email */
    private String staffEmail;
    private Integer status;
    /** 0-未通知 1-已通知 */
    private Integer notify;

    //后端接收不到multipleSelection参数，所以需优雅的转换下
    public void initializeMultipleSelection() {
        if (lstId != null && lstDeptId != null &&!lstId.isEmpty() && !lstDeptId.isEmpty() && lstId.size() == lstDeptId.size()) {
            multipleSelection = IntStream.range(0, lstId.size())
                    .mapToObj(i -> new Key(lstId.get(i), lstDeptId.get(i)))
                    .collect(Collectors.toList());
        }
    }

    @Data
    @AllArgsConstructor
    public static class Key {
        private Long id;
        private Long deptId;
    }
}
