package com.ruoyi.filingsystem.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 用于记录邮件发送记录的对象 filing_email_send_record
 * 
 * @author ruoyi
 * @date 2024-03-25
 */
public class FilingEmailSendRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 发送记录ID，主键自增加 */
    private Long recordID;

    /** 模板ID，外键关联邮件模板表 */
    @Excel(name = "模板ID，外键关联邮件模板表")
    private Long templateID;

    /** 发件人邮箱 */
    @Excel(name = "发件人邮箱")
    private String senderEmail;

    /** 收件人邮箱 */
    @Excel(name = "收件人邮箱")
    private String recipientEmail;

    /** 抄送邮箱 */
    @Excel(name = "抄送邮箱")
    private String ccEmail;

    /** 发送时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发送时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date sendTime;

    /** 发送状态（成功、失败等） */
    @Excel(name = "发送状态", readConverterExp = "成=功、失败等")
    private String status;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    public void setRecordID(Long recordID) 
    {
        this.recordID = recordID;
    }

    public Long getRecordID() 
    {
        return recordID;
    }
    public void setTemplateID(Long templateID) 
    {
        this.templateID = templateID;
    }

    public Long getTemplateID() 
    {
        return templateID;
    }
    public void setSenderEmail(String senderEmail) 
    {
        this.senderEmail = senderEmail;
    }

    public String getSenderEmail() 
    {
        return senderEmail;
    }
    public void setRecipientEmail(String recipientEmail) 
    {
        this.recipientEmail = recipientEmail;
    }

    public String getRecipientEmail() 
    {
        return recipientEmail;
    }
    public void setCcEmail(String ccEmail) 
    {
        this.ccEmail = ccEmail;
    }

    public String getCcEmail() 
    {
        return ccEmail;
    }
    public void setSendTime(Date sendTime) 
    {
        this.sendTime = sendTime;
    }

    public Date getSendTime() 
    {
        return sendTime;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }

    public String getRemarks() 
    {
        return remarks;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("recordID", getRecordID())
            .append("templateID", getTemplateID())
            .append("senderEmail", getSenderEmail())
            .append("recipientEmail", getRecipientEmail())
            .append("ccEmail", getCcEmail())
            .append("sendTime", getSendTime())
            .append("status", getStatus())
            .append("remarks", getRemarks())
            .toString();
    }
}
