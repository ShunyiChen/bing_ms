package com.ruoyi.filingsystem.service;

import com.ruoyi.filingsystem.domain.FilingEmailTemplate;

import java.util.List;

/**
 * 邮件模板的_管理人员操作Service接口
 * 
 * @author ruoyi
 * @date 2024-03-25
 */
public interface IFilingEmailTemplateService
{
    /**
     * 查询邮件模板的_管理人员操作
     * 
     * @param templateID 邮件模板的_管理人员操作主键
     * @return 邮件模板的_管理人员操作
     */
    public FilingEmailTemplate selectFilingEmailTemplateByTemplateID(Long templateID);

    /**
     * 查询邮件模板的_管理人员操作列表
     * 
     * @param filingEmailTemplate 邮件模板的_管理人员操作
     * @return 邮件模板的_管理人员操作集合
     */
    public List<FilingEmailTemplate> selectFilingEmailTemplateList(FilingEmailTemplate filingEmailTemplate);

    /**
     * 新增邮件模板的_管理人员操作
     * 
     * @param filingEmailTemplate 邮件模板的_管理人员操作
     * @return 结果
     */
    public int insertFilingEmailTemplate(FilingEmailTemplate filingEmailTemplate);

    /**
     * 修改邮件模板的_管理人员操作
     * 
     * @param filingEmailTemplate 邮件模板的_管理人员操作
     * @return 结果
     */
    public int updateFilingEmailTemplate(FilingEmailTemplate filingEmailTemplate);

    /**
     * 批量删除邮件模板的_管理人员操作
     * 
     * @param templateIDs 需要删除的邮件模板的_管理人员操作主键集合
     * @return 结果
     */
    public int deleteFilingEmailTemplateByTemplateIDs(Long[] templateIDs);

    /**
     * 删除邮件模板的_管理人员操作信息
     * 
     * @param templateID 邮件模板的_管理人员操作主键
     * @return 结果
     */
    public int deleteFilingEmailTemplateByTemplateID(Long templateID);
}
