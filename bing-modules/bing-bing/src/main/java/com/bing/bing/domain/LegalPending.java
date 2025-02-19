package com.bing.bing.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.bing.common.core.annotation.Excel;
import com.bing.common.core.web.domain.BaseEntity;
import com.bing.common.core.xss.Xss;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Pending对象 legal_pending
 * 
 * @author Simeon
 * @date 2024-11-23
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LegalPending extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 审批列表中的名称 */
    @ExcelProperty("Name in Batch Approval List")
    @Excel(name = "审批列表中的名称")
    @Xss(message = "Name must not contain script characters")
    @NotBlank(message = "Name must not be blank")
    @Size(max = 100, message = "Length of Name must not exceed 100 characters")
    private String nameInBatchApprovalList;

    /** gpn */
    @ExcelProperty("GPN")
    @Excel(name = "gpn")
    @Xss(message = "GPN must not contain script characters")
    @NotBlank(message = "GPN must not be blank")
    @Size(max = 30, message = "Length of GPN must not exceed 30 characters")
    private String gpn;

    /** 邮箱 */
    @ExcelProperty("Email Address")
    @Excel(name = "邮箱")
    @Email(message = "Email is not a well-formed email address")
    @Xss(message = "Email must not contain script characters")
    @NotBlank(message = "Email must not be blank")
    @Size(max = 100, message = "Length of Email must not exceed 100 characters")
    private String emailAddress;

    /** 用户名称 */
    @ExcelProperty("User Name")
    @Excel(name = "用户名称")
    @Xss(message = "User Name not contain script characters")
    @NotBlank(message = "User Name must not be blank")
    @Size(max = 50, message = "Length of User Name must not exceed 50 characters")
    private String userName;

    /** lpn */
    @ExcelProperty("LPN")
    @Excel(name = "lpn")
    @Xss(message = "LPN must not contain script characters")
    @NotBlank(message = "LPN must not be blank")
    @Size(max = 20, message = "Length of LPN must not exceed 20 characters")
    private String lpn;

    /**
     * 在Excel中的行号属性
     */
    private int lineNumber;

    private Integer pageNum;
    private Integer pageSize;
}
