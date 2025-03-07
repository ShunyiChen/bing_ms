package com.bing.bing.domain;

import com.bing.common.core.annotation.Excel;
import com.bing.common.core.web.domain.BaseEntity;
import lombok.Data;


/**
 * 【病案材料】对象 bing_files
 * 
 * @author Simeon
 * @date 2025-03-04
 */
@Data
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
}
