package com.ruoyi.filingsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FetchEditHistoryDTO extends BaseEntity {
    private Long fileIDFrom;
    private Long fileIDFromTo;
    /** 文件所属地区 */
    private String areaLocation;
    /** 档案内容 */
    private String fileDescription;
    /** 客户组名称 */
    private String clientGroupName;
    /** 客户编号 */
    private String clientNumber;
    /** 档案盒号码 */
    private String boxNumber;
    /** 盒号前缀 */
    private String boxPrefix;
    /** 盒号后缀 */
    private String boxSuffix;
    /** 文件类型 */
    private String fileType;
    /** 客户公司名称 */
    private String clientCompanyName;
    /** 审计负责人邮箱 */
    private String engagementEICEmail;
    /** 审计合伙人邮箱 */
    private String engagementPartnerEmail;
    /** 项目编号 */
    private String engagementNumber;
    private String ccId;
    /** 文件所属部门TAX，咨询，审计....等等 */
    private Long deptId;
    /** 文件备注 */
    private String fileremarks;
    /** 财年结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date yearEndDateFrom;
    /** 财年结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date yearEndDateTo;
    /** 审计报告时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date auditReportDateFrom;
    /** 审计报告时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date auditReportDateTo;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date retentionDateFrom;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date retentionDateTo;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationDateFrom;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationDateTo;
    private String isDPN;
    private Integer borrowState;
    private String fileState;
    private String staffId;
    private String requesterName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTimeFrom;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTimeTo;

    /** pageNum(非字段) 页数 */
    private Integer pageNum;
    /** pageSize(非字段) 每页数量 */
    private Integer pageSize;
}
