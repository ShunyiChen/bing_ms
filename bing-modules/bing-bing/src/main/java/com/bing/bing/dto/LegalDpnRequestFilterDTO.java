package com.bing.bing.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.bing.common.core.annotation.Excel;
import com.bing.common.core.web.domain.BaseEntity;
import com.bing.common.core.xss.Xss;
import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * 过滤DPN Request列表
 *
 * @author Simeon
 * @date 2024-11-23
 */
@Data
public class LegalDpnRequestFilterDTO extends BaseEntity implements Serializable {
    /** gpn员工编号 */
    @Excel(name = "gpn员工编号")
    @Xss(message = "Staff No must not contain script characters")
    @NotBlank(message = "Staff No must not be blank")
    @Size(max = 100, message = "Length of Staff No must not exceed 100 characters")
    private String staffNo;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    @Xss(message = "LPN must not contain script characters")
    @NotBlank(message = "LPN must not be blank")
    @Size(max = 100, message = "Length of LPN must not exceed 100 characters")
    private String lpn;

    /** 员工名称 */
    @Excel(name = "员工名称")
    @Xss(message = "Staff Name must not contain script characters")
    @NotBlank(message = "Staff Name must not be blank")
    @Size(max = 100, message = "Length of Staff Name must not exceed 100 characters")
    private String staffName;

    /** 邮箱 */
    @Excel(name = "邮箱")
    @Email(message = "Email is not a well-formed email address")
    @Xss(message = "Email must not contain script characters")
    @NotBlank(message = "Email must not be blank")
    @Size(max = 100, message = "Length of Email must not exceed 100 characters")
    private String email;

    /** 办公地点 */
    @Excel(name = "办公地点")
    @Xss(message = "Office must not contain script characters")
    @NotBlank(message = "Office must not be blank")
    @Size(max = 100, message = "Length of Office must not exceed 100 characters")
    private String office;

    /** 部门 */
    @Excel(name = "部门")
    @Xss(message = "Service Line must not contain script characters")
    @NotBlank(message = "Service Line must not be blank")
    @Size(max = 100, message = "Length of Service Line must not exceed 100 characters")
    private String serviceLine;

    /** EY等级名称 */
    @Excel(name = "EY等级名称")
    @Xss(message = "Rank Name must not contain script characters")
    @NotBlank(message = "Rank Name must not be blank")
    @Size(max = 100, message = "Length of Rank Name must not exceed 100 characters")
    private String rankName;

    /** 创建人 */
    @Excel(name = "创建人")
    @Xss(message = "Creator Name must not contain script characters")
    @Size(max = 100, message = "Length of Creator Name must not exceed 100 characters")
    private String createdBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date created;

    /** 审核状态 */
    @Excel(name = "审核状态")
    @Xss(message = "Status must not contain script characters")
    @Size(max = 50, message = "Length of Status must not exceed 50 characters")
    private String status;

    /** 驳回的理由 */
    @Excel(name = "驳回的理由")
    @Xss(message = "Reject Reason must not contain script characters")
    @Size(max = 100, message = "Length of Reject Reason must not exceed 100 characters")
    private String rejectReason;

    /** 归还时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "归还时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date returnedDate;

    /** 设备类型 */
    @Excel(name = "设备类型")
    @Xss(message = "Device Type must not contain script characters")
    @NotBlank(message = "Device Type must not be blank")
    @Size(max = 100, message = "Length of Device Type must not exceed 100 characters")
    private String deviceType;

    /** 设备编号 */
    @Excel(name = "设备编号")
    @Xss(message = "Asset No must not contain script characters")
    @NotBlank(message = "Asset No must not be blank")
    @Size(max = 100, message = "Length of Asset No must not exceed 100 characters")
    private String eyAssetNo;

    /** 序列号 */
    @Excel(name = "序列号")
    @Xss(message = "Serial No must not contain script characters")
    @Size(max = 100, message = "Length of Serial No must not exceed 100 characters")
    private String serialNo;

    /** 接收者 */
    @Excel(name = "接收者")
    @Xss(message = "Receiver must not contain script characters")
    @NotBlank(message = "Receiver must not be blank")
    @Size(max = 100, message = "Length of Receiver must not exceed 100 characters")
    private String receivedBy;

    /** 合约编码 */
    @Excel(name = "合约编码")
    @Xss(message = "Engagement Code must not contain script characters")
    @Size(max = 100, message = "Length of Engagement Code must not exceed 100 characters")
    private String engagementCode;

    /** 合约名称 */
    @Excel(name = "合约名称")
    @Xss(message = "Engagement Name must not contain script characters")
    @Size(max = 100, message = "Length of Engagement Name must not exceed 100 characters")
    private String engagementName;

    /** 关闭的理由 */
    @Excel(name = "关闭的理由")
    @Xss(message = "Cancel Reason must not contain script characters")
    @Size(max = 100, message = "Length of Cancel Reason must not exceed 100 characters")
    private String cancelReason;

    /** FIDS接收者 */
    @Excel(name = "FIDS接收者")
    @Xss(message = "FIDS Receiver must not contain script characters")
    @Size(max = 50, message = "Length of FIDS Receiver must not exceed 50 characters")
    private String fidsReceivedBy;

    /** FIDS接收日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "FIDS接收日期", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date fidsReceivedDate;

    /** 设备位置 */
    @Excel(name = "设备位置")
    @Xss(message = "Device Location must not contain script characters")
    @NotBlank(message = "Device Location must not be blank")
    @Size(max = 100, message = "Length of Device Location must not exceed 100 characters")
    private String deviceLocation;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date approved;

    /** 驳回时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "驳回时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date rejected;

    /** 审核人 */
    @Excel(name = "审核人")
    @Xss(message = "Approver must not contain script characters")
    @Size(max = 100, message = "Length of Approver must not exceed 100 characters")
    private String approver;

    /** 驳回人 */
    @Excel(name = "驳回人")
    @Xss(message = "Rejecter must not contain script characters")
    @Size(max = 100, message = "Length of Rejecter must not exceed 100 characters")
    private String rejecter;

    @Xss(message = "New Status must not contain script characters")
    @Size(max = 50, message = "Length of New Status must not exceed 50 characters")
    /** 新状态 */
    private String newStatus;
    private String[] offices;
    private Date[] createdDateRange;
    private Date[] returnedDateRange;
    private Date[] fidsReceivedDateRange;
    private Date[] approvedDateRange;
    private Date[]  rejectedDateRange;
    private Integer pageNum;
    private Integer pageSize;
    private String findWhat;
    private String[] scope;
}
