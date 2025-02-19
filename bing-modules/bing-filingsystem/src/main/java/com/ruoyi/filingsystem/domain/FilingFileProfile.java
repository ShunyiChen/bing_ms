package com.ruoyi.filingsystem.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 文档_管理人员操作对象 filing_file_profile
 * 
 * @author ruoyi
 * @date 2024-03-25
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilingFileProfile extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /** 文件ID，主键自增加 */
    private Long fileID;

    /** 老系统文件的id */
    @Excel(name = "FileID")
    private String oldSystemfileID;
    /** 客户组名称 */
    @Excel(name = "Group")
    private String clientGroupName;
    /** 客户公司名称 */
    @Excel(name = "Client Name")
    private String clientCompanyName;
    /** 客户编号 */
    @Excel(name = "Client Code")
    private String clientNumber;
    /** 财年结束时间 */
    @Excel(name = "Year End", width = 30, dateFormat = "yyyy-MM-dd")
    private LocalDate yearEndDate;
    /** 审计报告时间 */
    @Excel(name = "AuditReportDate", width = 30, dateFormat = "yyyy-MM-dd")
    private LocalDate auditReportDate;
    /** 存档到期时间 */
    @Excel(name = "RetentionDate", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate retentionDate;
    /** 文件类型 */
    @Excel(name = "Type")
    private String fileType;
    /** 档案内容 */
    @Excel(name = "Description")
    private String fileDescription;
    /** 文件所属地区 */
    @Excel(name = "Location")
    private String areaLocation;
    /** 文件备注 */
    @Excel(name = "Remarks")
    private String fileremarks;
    /** 创建时间 */
    @Excel(name = "CreationDate", width = 30, dateFormat = "yyyy-MM-dd")
    private LocalDateTime creationDate;
    /** 档案盒号码 */
    @Excel(name = "BoxNum")
    private String boxNumber;
    /** 移动到其他areaLocation位置 */
    @Excel(name = "Moved To")
    private String moveTo;
    @Excel(name = "Requester")
    private String requesterName;
    @Excel(name = "RequestDate", width = 30, dateFormat = "yyyy-MM-dd")
    private LocalDateTime requestDate;
    /** 老系统文件标识0老文件，1新文件  老系统指外部供应商的系统 */
    private Long oldSystemFileMark;
    /** 文件所属部门TAX，咨询，审计....等等 */
    private Long deptId;
    /** 文件状态（正常、销毁） */
    @Excel(name = "File State")
    private String fileState;
    /** ccid */
    @Excel(name = "CCID")
    private String ccId;
    /** 是否禁止销毁 */
    @Excel(name = "IsDPN?")
    private String isDPN;
    /** 项目编号 */
    @Excel(name = "EngagementCode?")
    private String engagementNumber;
    /** 审计负责人 */
    @Excel(name = "EIC")
    private String engagementEIC;
    /** 审计负责人 */
    @Excel(name = "EIC Email")
    private String engagementEICEmail;
    /** 审计合伙人 */
    @Excel(name = "PIC")
    private String engagementPartner;
    /** 审计合伙人 */
    @Excel(name = "PIC Email")
    private String engagementPartnerEmail;
    /** 文件barcode码 */
    private String barCode;
    /** 文件借阅状态  in  out   */
    private Integer borrowState;
    /** 借阅人ID */
    @Excel(name = "Borrower StaffID")
    private String borrowerStaffId;
    /** 借阅人 */
    @Excel(name = "Borrower")
    private String borrower;
    @Excel(name = "Avail")
    private String avail;
    /** 文件销毁时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "Destruction Time", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime DestructionTime;
    /** 文件销毁操作人 */
    @Excel(name = "Destruction Operator")
    private String DestructionPeople;
    /** 文件入库申请人的staffid */
    @Excel(name = "StaffID")
    private String staffId;
    /** 文件入库申请人的名字 */
    @Excel(name = "RetentionPeriod")
    private Integer retentionPeriod;
    private String isMoved;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;
    private String serviceLine;
}
