package com.ruoyi.filingsystem.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 用于记录文档操作历史的_管理人员查询用，自动录入的对象 filing_file_operation_record
 * 
 * @author ruoyi
 * @date 2024-03-25
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilingFileOperationRecord extends BaseEntity
{
    private List<String> fileIDs;
    private String editor;
    private String action;
}
