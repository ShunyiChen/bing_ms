package com.ruoyi.filingsystem.service.impl;

import java.util.List;

import com.ruoyi.filingsystem.domain.FilingEmailTemplate;
import com.ruoyi.filingsystem.mapper.FilingEmailTemplateMapper;
import com.ruoyi.filingsystem.service.IFilingEmailTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 邮件模板的_管理人员操作Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-25
 */
@Service
public class FilingEmailTemplateServiceImpl implements IFilingEmailTemplateService
{
    @Autowired
    private FilingEmailTemplateMapper filingEmailTemplateMapper;

    /**
     * 查询邮件模板的_管理人员操作
     * 
     * @param templateID 邮件模板的_管理人员操作主键
     * @return 邮件模板的_管理人员操作
     */
    @Override
    public FilingEmailTemplate selectFilingEmailTemplateByTemplateID(Long templateID)
    {
        return filingEmailTemplateMapper.selectFilingEmailTemplateByTemplateID(templateID);
    }

    /**
     * 查询邮件模板的_管理人员操作列表
     * 
     * @param filingEmailTemplate 邮件模板的_管理人员操作
     * @return 邮件模板的_管理人员操作
     */
    @Override
    public List<FilingEmailTemplate> selectFilingEmailTemplateList(FilingEmailTemplate filingEmailTemplate)
    {
        return filingEmailTemplateMapper.selectFilingEmailTemplateList(filingEmailTemplate);
    }

    /**
     * 新增邮件模板的_管理人员操作
     * 
     * @param filingEmailTemplate 邮件模板的_管理人员操作
     * @return 结果
     */
    @Override
    public int insertFilingEmailTemplate(FilingEmailTemplate filingEmailTemplate)
    {
        return filingEmailTemplateMapper.insertFilingEmailTemplate(filingEmailTemplate);
    }

    /**
     * 修改邮件模板的_管理人员操作
     * 
     * @param filingEmailTemplate 邮件模板的_管理人员操作
     * @return 结果
     */
    @Override
    public int updateFilingEmailTemplate(FilingEmailTemplate filingEmailTemplate)
    {
        return filingEmailTemplateMapper.updateFilingEmailTemplate(filingEmailTemplate);
    }

    /**
     * 批量删除邮件模板的_管理人员操作
     * 
     * @param templateIDs 需要删除的邮件模板的_管理人员操作主键
     * @return 结果
     */
    @Override
    public int deleteFilingEmailTemplateByTemplateIDs(Long[] templateIDs)
    {
        return filingEmailTemplateMapper.deleteFilingEmailTemplateByTemplateIDs(templateIDs);
    }

    /**
     * 删除邮件模板的_管理人员操作信息
     * 
     * @param templateID 邮件模板的_管理人员操作主键
     * @return 结果
     */
    @Override
    public int deleteFilingEmailTemplateByTemplateID(Long templateID)
    {
        return filingEmailTemplateMapper.deleteFilingEmailTemplateByTemplateID(templateID);
    }
}
