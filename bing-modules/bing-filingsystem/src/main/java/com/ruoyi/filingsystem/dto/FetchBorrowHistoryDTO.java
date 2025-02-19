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
public class FetchBorrowHistoryDTO extends BaseEntity {
    private Long fileID;
    /** 文件类型 */
    private String fileType;
    /** 客户公司名称 */
    private String clientCompanyName;
    /** 客户编号 */
    private String clientNumber;
    /** 文件备注 */
    private String fileremarks;
    /** 客户组名称 */
    private String clientGroupName;
    private String borrower;
    private String borrowerStaffId;
    /** 借阅时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date borrowDateFrom;
    /** 借阅时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date borrowDateTo;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date returnedDateFrom;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date returnedDateTo;
    /** 审计报告时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date auditReportDateFrom;
    /** 审计报告时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date auditReportDateTo;
    /** 财年结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date yearEndDateFrom;
    /** 财年结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date yearEndDateTo;
    private Long deptId;
    private String fileDescription;
    /** pageNum(非字段) 页数 */
    private Integer pageNum;
    /** pageSize(非字段) 每页数量 */
    private Integer pageSize;
}
