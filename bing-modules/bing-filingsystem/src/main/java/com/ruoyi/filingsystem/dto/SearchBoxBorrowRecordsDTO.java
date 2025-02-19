package com.ruoyi.filingsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SearchBoxBorrowRecordsDTO extends BaseEntity {
    /** 盒子ID */
    private Long boxId;
    /** 部门ID */
    private Long deptId;
    /** 盒子ID */
    private String boxNumber;
    /** 供应商提供的箱子编码 */
    private String vendorBarcode;
    /** 借阅时的ChargeCode */
    private String chargeCode;
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

    /** 借阅时间范围 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date loanDateFrom;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date loanDateTo;

    /** 归还时间范围 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date returnDateFrom;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date returnDateTo;

    /** 更新ChargeCode时间范围 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTimeFrom;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTimeTo;

    /** pageNum(非字段) 页数 */
    private Integer pageNum;
    /** pageSize(非字段) 每页数量 */
    private Integer pageSize;
    /** 选中 box borrow id + deptId */
    private List<Key> multipleSelection;

    @Data
    @AllArgsConstructor
    public static class Key {
        private Long id; // box borrow id
        private Long deptId;
    }
}
