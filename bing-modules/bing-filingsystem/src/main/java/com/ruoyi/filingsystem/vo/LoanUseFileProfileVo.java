package com.ruoyi.filingsystem.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanUseFileProfileVo {


    private Long fileID;

    /** 老系统文件的id */

    private Long oldSystemfileID;

    /** 老系统文件标识0老文件，1新文件  老系统指外部供应商的系统 */

    private Long oldSystemFileMark;

    /** 档案内容 */
    private String fileDescription;

    /** 客户组名称 */

    private String clientGroupName;
    /** 客户编号 */

    private String clientNumber;


    /** 客户公司名称 */

    private String clientCompanyName;



    /** 文件类型 */

    private String fileType;


    /** 财年结束时间 */

    private LocalDateTime yearEndDate;


    /** 是否可借阅   true 可借阅，false 不可借阅 */
    private Boolean available = false;





}
