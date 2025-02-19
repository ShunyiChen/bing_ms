package com.ruoyi.filingsystem.vo;

import com.ruoyi.common.core.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilingFileLoanReturnVo implements Serializable  {
    //借阅历史返回前端的VO对象
    /** 文档ID */
    @Excel(name = "File ID")
    private Long fileID;

    /** 操作人(管理人员 ，二期由系统默认操作) */
    @Excel(name = "Operator")
    private String operateEmail;

    /** 借阅人GPN/LPN/员工号 */
    @Excel(name = "Borrower ID")
    private String borrowerGPN;

    /** 借阅人邮箱 */
    @Excel(name = "Borrower Email")
    private String borrowerEmail;

    /** 借阅日期 */
    @Excel(name = "Borrowing Date", width = 30, dateFormat = "yyyy-MM-dd")
    private LocalDateTime loanDate;

    /** 归还日期 */
//    @Excel(name = "归还日期", width = 30, dateFormat = "yyyy-MM-dd")
    private LocalDateTime returnDate;


    /** 借阅到期时间，开始时间+默认借阅周期（例如默认30天） */
    @Excel(name = "Borrowing Due Date", width = 30, dateFormat = "yyyy-MM-dd")
    private LocalDateTime dueDate;


    @Excel(name = "Overdue Status")
    private boolean isExpire=false;

    /** 邮件发送标识，用于记录是否发送邮件 */
//    @Excel(name = "邮件发送标识，用于记录是否发送邮件")
    private String emailMark;

    /** 归还状态，用于操作文档归还 */
    @Excel(name = "Return Status")
    private String returnStatus;

    /** 转手标志 默认0未转手， 1转手 */
//    @Excel(name = "转手标志 默认0未转手， 1转手")
    private Long transferMark;

    /** 转手记录，顺序记录之前的借阅人邮箱，用户文件转手用 */
//    @Excel(name = "转手记录，顺序记录之前的借阅人邮箱，用户文件转手用")
    private String transferHistory;

    /** 客户组名称 */
    @Excel(name = "Group")
    private String clientGroupName;


    /** 客户公司名称 */
    @Excel(name = "Client Name")
    private String clientCompanyName;


    /** 文件类型 */
    @Excel(name = "File Type")
    private String fileType;

    /** 财年结束时间 */
    @Excel(name = "Y.E.D.", width = 30, dateFormat = "yyyy-MM-dd")
    private LocalDateTime yearEndDate;

    /** 档案内容 */
    @Excel(name = "File DESC")
    private String fileDescription;

    /** 文件所属地区 */
    private String areaLocation;
}
