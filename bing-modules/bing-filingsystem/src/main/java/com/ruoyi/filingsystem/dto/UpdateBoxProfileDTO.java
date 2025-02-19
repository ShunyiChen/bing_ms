package com.ruoyi.filingsystem.dto;

import com.ruoyi.common.core.web.domain.BaseEntity;
import com.ruoyi.common.core.xss.Xss;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.Date;

@Data
public class UpdateBoxProfileDTO extends BaseEntity {
    /** 记录ID */
    private Long id;
    /** 地区ID,用于权限管理 */
    @NotNull(message = "DeptId cannot be null")
    @Min(1)
    @Max(1000)
    private Long deptId;
    /** 盒子所属业务部门 */
    @Xss(message = "Box Department must not contain script characters")
    @NotBlank(message = "boxDepartment cannot be empty")
    @Size(min = 0, max = 255, message = "boxDepartment length cannot exceed 255 characters")
    private String boxDepartment;
    /** 盒子号，例如BJ2013360   可能盒子号修改  ，需要做唯一校验 */
    @Xss(message = "Box Number must not contain script characters")
    @NotBlank(message = "Box Number cannot be empty")
    @Size(min = 0, max = 255, message = "Box Number length cannot exceed 255 characters")
    private String boxNumber;
    /** 供应商提供的盒子编码 */
    @Xss(message = "vendorBarcode must not contain script characters")
    @NotBlank(message = "vendorBarcode cannot be empty")
    @Size(min = 0, max = 255, message = "vendorBarcode length cannot exceed 255 characters")
    private String vendorBarcode;
    /** 盒子到期时间-到期提醒  每月1号扫描 当月到期的箱子 发送提醒到公邮,以及箱子的批准人 */
    @Xss(message = "retentionDate must not contain script characters")
    @NotBlank(message = "retentionDate cannot be empty")
    @Size(min = 0, max = 255, message = "retentionDate length cannot exceed 255 characters")
    private Date retentionDate;
    /** 项目编号-费用统计用  手动输入   八位校验 */
    @Xss(message = "chargeCode must not contain script characters")
    @NotBlank(message = "chargeCode cannot be empty")
    @Size(min = 0, max = 255, message = "chargeCode length cannot exceed 255 characters")
    private String chargeCode;
    /** 批准人的staffid   lpn     */
    @Xss(message = "approvedstaffidLpn must not contain script characters")
    @NotBlank(message = "approvedstaffidLpn cannot be empty")
    @Size(min = 0, max = 255, message = "approvedstaffidLpn length cannot exceed 255 characters")
    private String approverLPN;
    /** 批准人邮箱   自动匹配---后面确认一下 */
    @Xss(message = "approverEmail must not contain script characters")
    @NotBlank(message = "approverEmail cannot be empty")
    @Size(min = 0, max = 255, message = "approverEmail length cannot exceed 255 characters")
    private String approverEmail;
    /** 盒子入库申请人的staffid  LPN */
    @Xss(message = "staffidLpn must not contain script characters")
    @NotBlank(message = "staffidLpn cannot be empty")
    @Size(min = 0, max = 255, message = "staffidLpn length cannot exceed 255 characters")
    private String requesterLPN;
    /** 盒子入库申请人的邮箱 自动匹配---后面确认一下  */
    @Xss(message = "requesterEmail must not contain script characters")
    @NotBlank(message = "requesterEmail cannot be empty")
    @Size(min = 0, max = 255, message = "requesterEmail length cannot exceed 255 characters")
    private String requesterEmail;
    /** 盒子所属地区 */
    @Xss(message = "areaLocation must not contain script characters")
    @NotBlank(message = "areaLocation cannot be empty")
    @Size(min = 0, max = 255, message = "areaLocation length cannot exceed 255 characters")
    private String areaLocation;
    /** 部门账号（业务部门的） */
    @Xss(message = "AccountCode must not contain script characters")
    @NotBlank(message = "AccountCode cannot be empty")
    @Size(min = 0, max = 255, message = "AccountCode length cannot exceed 255 characters")
    private String accountCode;
    /** 盒子备注，描述 */
    @Xss(message = "boxRemark must not contain script characters")
    @NotBlank(message = "boxRemark cannot be empty")
    @Size(min = 0, max = 255, message = "boxRemark length cannot exceed 255 characters")
    private String boxRemark;

    private String boxStatus;
    private String borrowerStaffId;
    private String borrowerName;
    private Date destructionTime;
    private String destructionPeople;

    private String vendorNumber;
    private String supportingDocument;
}
