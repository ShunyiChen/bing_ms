package com.ruoyi.filingsystem.domain;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 用于记录文档借阅和归还情况的_管理人员操作对象 filing_file_loan_return
 * 
 * @author ruoyi
 * @date 2024-03-25
 */




@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilingFileLoanReturn extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 借阅归还记录ID，主键自增加 */
    private Long loanReturnID;

    /** 文档ID */
    @Excel(name = "文档ID")
    private Long fileID;

    /** 操作人(管理人员 ，二期由系统默认操作) */
    @Excel(name = "操作人邮箱")
    private String operateEmail;




    /** 借阅人GPN/LPN/员工号 */
    @Excel(name = "借阅人GPN/LPN/员工号")
    private String borrowerGPN;

    /** 借阅人GPN/LPN/员工号 */
    @Excel(name = "借阅人名字")
    private String staffName;

    /** 借阅人邮箱 */
    @Excel(name = "借阅人邮箱")
    private String borrowerEmail;

    /** 借阅日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "借阅日期", width = 30, dateFormat = "yyyy-MM-dd")
    private LocalDateTime loanDate;

    /** 归还日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "归还日期", width = 30, dateFormat = "yyyy-MM-dd")
    private LocalDateTime returnDate;

    /** 借阅到期时间，开始时间+默认借阅周期（例如默认30天） */
    @Excel(name = "借阅到期时间，开始时间+默认借阅周期", readConverterExp = "例=如默认30天")
    private LocalDateTime dueDate;

    /** 邮件发送标识，用于记录是否发送邮件 */
    @Excel(name = "邮件发送标识，用于记录是否发送邮件")
    private String emailMark;

    /** 归还状态，用于操作文档归还 */
    @Excel(name = "归还状态，用于操作文档归还")
    private String returnStatus;

    /** 转手标志 默认0未转手， 1转手 */
    @Excel(name = "转手标志 默认0未转手， 1转手")
    private Long transferMark;

    /** 转手记录，顺序记录之前的借阅人邮箱，用户文件转手用 */
    @Excel(name = "转手记录，顺序记录之前的借阅人邮箱，用户文件转手用")
    private String transferHistory;



    /** 客户组名称 */
    private String clientGroupName;


    /** 客户公司名称 */
    private String clientCompanyName;

    /** 客户编号 */
    private String clientNumber;


    /** 文件类型 */
    private String fileType;

    /** 财年结束时间 */
    private LocalDateTime yearEndDate;

    /** 档案内容 */
    private String fileDescription;


    /** 文件所属地区 */
    private String areaLocation;




//
//    public void setLoanReturnID(Long loanReturnID)
//    {
//        this.loanReturnID = loanReturnID;
//    }
//
//    public Long getLoanReturnID()
//    {
//        return loanReturnID;
//    }
//    public void setFileID(Long fileID)
//    {
//        this.fileID = fileID;
//    }
//
//    public Long getFileID()
//    {
//        return fileID;
//    }
//    public void setBorrowerGPN(String borrowerGPN)
//    {
//        this.borrowerGPN = borrowerGPN;
//    }
//
//    public String getBorrowerGPN()
//    {
//        return borrowerGPN;
//    }
//    public void setBorrowerEmail(String borrowerEmail)
//    {
//        this.borrowerEmail = borrowerEmail;
//    }
//
//    public String getBorrowerEmail()
//    {
//        return borrowerEmail;
//    }
//    public void setLoanDate(Date loanDate)
//    {
//        this.loanDate = loanDate;
//    }
//
//    public Date getLoanDate()
//    {
//        return loanDate;
//    }
//    public void setReturnDate(Date returnDate)
//    {
//        this.returnDate = returnDate;
//    }
//
//    public Date getReturnDate()
//    {
//        return returnDate;
//    }
//    public void setDueDate(Date dueDate)
//    {
//        this.dueDate = dueDate;
//    }
//
//    public Date getDueDate()
//    {
//        return dueDate;
//    }
//    public void setEmailMark(String emailMark)
//    {
//        this.emailMark = emailMark;
//    }
//
//    public String getEmailMark()
//    {
//        return emailMark;
//    }
//    public void setReturnStatus(String returnStatus)
//    {
//        this.returnStatus = returnStatus;
//    }
//
//    public String getReturnStatus()
//    {
//        return returnStatus;
//    }
//    public void setTransferMark(Long transferMark)
//    {
//        this.transferMark = transferMark;
//    }
//
//    public Long getTransferMark()
//    {
//        return transferMark;
//    }
//    public void setTransferHistory(String transferHistory)
//    {
//        this.transferHistory = transferHistory;
//    }
//
//    public String getTransferHistory()
//    {
//        return transferHistory;
//    }
//
//    @Override
//    public String toString() {
//        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
//            .append("loanReturnID", getLoanReturnID())
//            .append("fileID", getFileID())
//            .append("borrowerGPN", getBorrowerGPN())
//            .append("borrowerEmail", getBorrowerEmail())
//            .append("loanDate", getLoanDate())
//            .append("returnDate", getReturnDate())
//            .append("dueDate", getDueDate())
//            .append("emailMark", getEmailMark())
//            .append("returnStatus", getReturnStatus())
//            .append("transferMark", getTransferMark())
//            .append("transferHistory", getTransferHistory())
//            .toString();
//    }
}
