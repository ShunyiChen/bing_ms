package com.ruoyi.filingsystem.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 位置信息的对象 filing_location
 * 
 * @author ruoyi
 * @date 2024-03-25
 */
@Data
public class FilingLocation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 办公室位置ID，主键自增加 */
    private Long locationID;

    /** 位置名称，缩写 唯一约束 */
    @Excel(name = "位置名称，缩写 唯一约束")
    private String locationName;

    /** 位置描述 */
    @Excel(name = "位置描述")
    private String description;

    @Excel(name = "编码")
    private String code;

    private Long deptId;

}
