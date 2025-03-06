package com.bing.bing.domain;

import java.util.Date;

import com.bing.common.core.annotation.Excel;
import com.bing.common.core.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 【病案记录】对象 bing_patient_record
 * 
 * @author Simeon
 * @date 2025-02-25
 */
@Data
public class BingPatientRecord extends BaseEntity
{
    /** ID */
    private Long id;

    /** 机构代码 */
    @Excel(name = "机构代码")
    private String institutionCode;

    /** 机构名称 */
    @Excel(name = "机构名称")
    private String institutionName;

    /** 病案号 */
    @Excel(name = "病案号")
    private String medicalRecordNumber;

    /** 住院次数 */
    @Excel(name = "住院次数")
    private Long hospitalizationCount;

    /** 入院日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入院日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date admissionDate;

    /** 出院日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出院日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dischargeDate;

    /** 性名 */
    @Excel(name = "性名")
    private String patientName;

    /** 性别 */
    @Excel(name = "性别")
    private String gender;

    /** 出生日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthDate;

    /** 年龄 */
    @Excel(name = "年龄")
    private Long age;

    /** 离院方式 */
    @Excel(name = "离院方式")
    private Long dischargeMethod;

    /** 病案类型 0-西医 1-中医 */
    @Excel(name = "病案类型 0-西医 1-中医")
    private Long type;

    @Excel(name = "病案状态 New (新建)/Submitted (已提交)/Modified (有改动)")
    private String status;

    /** pageNum(非字段) 页数 */
    private Integer pageNum;
    /** pageSize(非字段) 每页数量 */
    private Integer pageSize;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date admissionDateFrom;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date admissionDateTo;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dischargeDateFrom;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dischargeDateTo;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDateFrom;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDateTo;
}
