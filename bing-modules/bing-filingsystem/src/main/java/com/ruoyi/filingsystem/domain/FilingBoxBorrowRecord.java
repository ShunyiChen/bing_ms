package com.ruoyi.filingsystem.domain;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 盒子借阅记录-整箱借阅对象 filing_box_borrowrecord
 * 
 * @author victor.xiong
 * @date 2024-11-18
 */
@Data
public class FilingBoxBorrowRecord extends BaseEntity {
    /** $column.columnComment */
    private Long id;
    /** 盒子ID */
    @Excel(name = "盒子ID")
    private Long boxId;
    /** 部门ID */
    @Excel(name = "部门ID")
    private Long deptId;
    /** 盒子ID */
    @Excel(name = "盒子号")
    private String boxNumber;
    /** 供应商提供的箱子编码 */
    @Excel(name = "供应商提供的箱子编码")
    private String vendorBarcode;
    /** 项目计费编码 */
    @Excel(name = "借阅收费代码")
    private String chargeCode;
    @Excel(name = "归还收费代码")
    private String returnChargeCode;
    /** 员工LPN */
    @Excel(name = "员工LPN")
    private String staffId;
    /** 员工姓名 */
    @Excel(name = "员工姓名")
    private String staffName;
    /** 员工Email */
    @Excel(name = "员工邮箱")
    private String staffEmail;
    @Excel(name = "借出状态（0-归还状态 1-借阅状态）")
    private int status;
    /** 借阅日期 */
    @Excel(name = "借阅日期", width = 30, dateFormat = "yyyy-MM-dd hh:mm:ss")
    private Date loanDate;
    /** 归还日期 */
    @Excel(name = "归还日期", width = 30, dateFormat = "yyyy-MM-dd hh:mm:ss")
    private Date returnDate;
    /** 0-未通知 1-已通知 */
    @Excel(name = "通知状态（0-未通知 1-已通知）")
    private int notify;
}
