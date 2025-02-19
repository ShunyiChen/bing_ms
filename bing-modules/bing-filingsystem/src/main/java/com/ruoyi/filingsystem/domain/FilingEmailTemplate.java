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
 * 邮件模板的_管理人员操作对象 filing_email_template
 * 
 * @author ruoyi
 * @date 2024-03-25
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilingEmailTemplate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 模板ID，主键自增加 */
    private Long templateID;

    /** 模板名称 */
    @Excel(name = "模板名称")
    private String templateName;

    /** 邮件主题 */
    @Excel(name = "邮件主题")
    private String subject;

    /** 邮件正文 */
    @Excel(name = "邮件正文头部")
    private String bodyStart;

    /** 邮件正文 */
    @Excel(name = "邮件正文尾部")
    private String bodyEnd;

    /** 模板创建日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "模板创建日期", width = 30, dateFormat = "yyyy-MM-dd")
    private LocalDateTime createdDate;

    /** 模板最后修改日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "模板最后修改日期", width = 30, dateFormat = "yyyy-MM-dd")
    private LocalDateTime lastModifiedDate;
//
//    public void setTemplateID(Long templateID)
//    {
//        this.templateID = templateID;
//    }
//
//    public Long getTemplateID()
//    {
//        return templateID;
//    }
//    public void setTemplateName(String templateName)
//    {
//        this.templateName = templateName;
//    }
//
//    public String getTemplateName()
//    {
//        return templateName;
//    }
//    public void setSubject(String subject)
//    {
//        this.subject = subject;
//    }
//
//    public String getSubject()
//    {
//        return subject;
//    }
//    public void setBody(String body)
//    {
//        this.body = body;
//    }
//
//    public String getBody()
//    {
//        return body;
//    }
//    public void setCreatedDate(Date createdDate)
//    {
//        this.createdDate = createdDate;
//    }
//
//    public Date getCreatedDate()
//    {
//        return createdDate;
//    }
//    public void setLastModifiedDate(Date lastModifiedDate)
//    {
//        this.lastModifiedDate = lastModifiedDate;
//    }
//
//    public Date getLastModifiedDate()
//    {
//        return lastModifiedDate;
//    }
//
//    @Override
//    public String toString() {
//        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
//            .append("templateID", getTemplateID())
//            .append("templateName", getTemplateName())
//            .append("subject", getSubject())
//            .append("body", getBody())
//            .append("createdDate", getCreatedDate())
//            .append("lastModifiedDate", getLastModifiedDate())
//            .toString();
//    }
}
