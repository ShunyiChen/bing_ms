package com.ruoyi.filingsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.web.domain.BaseEntity;
import com.ruoyi.common.core.xss.Xss;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class AddFileDto extends BaseEntity {
    /** 文件所属部门TAX，咨询，审计....等等 */
    @NotNull(message = "DeptId cannot be null")
    @Min(1)
    @Max(1000)
    private Long deptId;
    /** 文件所属地区 */
    @NotBlank(message = "Location cannot be empty")
    @Size(min = 0, max = 255, message = "Location length cannot exceed 255 characters")
    private String areaLocation;
    /** 档案内容 */
    @Xss(message = "FileDescription must not contain script characters")
    @NotBlank(message = "FileDescription cannot be empty")
    @Size(min = 0, max = 255, message = "FileDescription length cannot exceed 255 characters")
    private String fileDescription;
    /** 客户编号 */
    @Xss(message = "ClientCode must not contain script characters")
    @NotBlank(message = "ClientCode cannot be empty")
    @Size(min = 0, max = 50, message = "ClientCode length cannot exceed 50 characters")
    private String clientNumber;
    /** ccid */
    @Size(min = 0, max = 100, message = "CCID length cannot exceed 100 characters")
    private String ccId;
    /** 是否禁止销毁 */
    @NotBlank(message = "DPN cannot be empty")
    @Size(min = 0, max = 1, message = "DPN length cannot exceed 1 characters")
    private String isDPN;
    /** 保存时间 默认10+1， 可以20 ，30,40,50，长期保存1000 */
    @NotNull(message = "RetentionPeriod cannot be null")
    @Min(1)
    @Max(1000)
    private Long retentionPeriod = 10L;
    /** 客户公司名称 */
    @Xss(message = "ClientName must not contain script characters")
    @NotBlank(message = "ClientName cannot be empty")
    @Size(min = 0, max = 2048, message = "ClientName length cannot exceed 2048 characters")
    private String clientCompanyName;
    /** 文件类型 */
    @Xss(message = "FileType must not contain script characters")
    @NotBlank(message = "FileType cannot be empty")
    @Size(min = 0, max = 10, message = "FileType length cannot exceed 10 characters")
    private String fileType;
    /** 财年结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "YearEndDate cannot be null")
    private LocalDate yearEndDate;
    /** 审计报告时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate auditReportDate;
    /** 项目编号 */
    @Xss(message = "EngagementCode must not contain script characters")
    @NotBlank(message = "EngagementCode cannot be empty")
    @Size(min = 0, max = 100, message = "EngagementCode length cannot exceed 100 characters")
    private String engagementNumber;
    /** 审计负责人 */
    @Xss(message = "EIC must not contain script characters")
    @NotBlank(message = "EIC cannot be empty")
    @Size(min = 0, max = 255, message = "EIC length cannot exceed 255 characters")
    private String engagementEIC;
    /** 审计负责人 */
    @Email(message = "EIC Email format incorrect")
    @NotBlank(message = "EIC email cannot be empty")
    @Size(min = 0, max = 100, message = "EIC Email length cannot exceed 100 characters")
    private String engagementEICEmail;
    /** 审计合伙人 */
    @Xss(message = "PIC must not contain script characters")
    @NotBlank(message = "PIC cannot be empty")
    @Size(min = 0, max = 255, message = "PIC length cannot exceed 255 characters")
    private String engagementPartner;
    /** 审计合伙人 */
    @Email(message = "PIC Email format incorrect")
    @NotBlank(message = "PIC Email cannot be empty")
    @Size(min = 0, max = 100, message = "PIC Email length cannot exceed 100 characters")
    private String engagementPartnerEmail;
    /** 移动到其他areaLocation位置 */
    @Xss(message = "MoveTo must not contain script characters")
    @Size(min = 0, max = 255, message = "MoveTo length cannot exceed 255 characters")
    private String moveTo;
    /** 文件备注 */
    @Xss(message = "Remarks must not contain script characters")
    @Size(min = 0, max = 255, message = "Remarks length cannot exceed 255 characters")
    private String fileremarks;
    /** 客户组名称 */
    @Xss(message = "Group must not contain script characters")
    @Size(min = 0, max = 255, message = "Group length cannot exceed 255 characters")
    private String clientGroupName;
    /** 档案盒号码 */
    @Xss(message = "BoxNumber must not contain script characters")
    @Size(min = 0, max = 50, message = "BoxNumber length cannot exceed 50 characters")
    private String boxNumber;
    /** 文件入库申请人的staffid */
    @Xss(message = "StaffID/LPN must not contain script characters")
    @NotBlank(message = "StaffID/LPN cannot be empty")
    @Size(min = 0, max = 100, message = "StaffID/LPN length cannot exceed 100 characters")
    private String staffId;
    /** 文件入库申请人的名字 */
    @Xss(message = "Requester must not contain script characters")
    @NotBlank(message = "Requester cannot be empty")
    @Size(min = 0, max = 100, message = "Requester length cannot exceed 100 characters")
    private String requesterName;
    private String serviceLine;
}
