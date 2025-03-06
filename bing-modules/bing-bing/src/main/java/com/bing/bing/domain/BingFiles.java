package com.bing.bing.domain;

import java.util.Date;

import com.bing.common.core.annotation.Excel;
import com.bing.common.core.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;


/**
 * 【病案材料】对象 bing_files
 * 
 * @author Simeon
 * @date 2025-03-04
 */
public class BingFiles extends BaseEntity
{
    /** 主键ID */
    private Long id;

    /** 病案ID */
    @Excel(name = "病案ID")
    private Long recordId;

    /** 病案分类名称 */
    @Excel(name = "病案分类名称")
    private String recordClassificationName;

    /** 材料分类名称 */
    @Excel(name = "材料分类名称")
    private String classificationName;

    /** 文件名称 */
    @Excel(name = "文件名称")
    private String fileName;

    /** 文件存储路径 */
    @Excel(name = "文件存储路径")
    private String filePath;

    /** 文件记录状态，uploaded/waiting upload */
    @Excel(name = "文件记录状态，uploaded/waiting upload")
    private String status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setRecordId(Long recordId) 
    {
        this.recordId = recordId;
    }

    public Long getRecordId() 
    {
        return recordId;
    }
    public void setClassificationName(String classificationName) 
    {
        this.classificationName = classificationName;
    }

    public String getClassificationName() 
    {
        return classificationName;
    }
    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }

    public String getFileName() 
    {
        return fileName;
    }
    public void setFilePath(String filePath) 
    {
        this.filePath = filePath;
    }

    public String getFilePath() 
    {
        return filePath;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

}
