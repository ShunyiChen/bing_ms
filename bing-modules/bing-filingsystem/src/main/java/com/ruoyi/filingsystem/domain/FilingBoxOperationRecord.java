package com.ruoyi.filingsystem.domain;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 用于记录盒子操作历史的,整箱操作用对象 filing_box_operation_record
 * 
 * @author victor.xiong
 * @date 2024-11-18
 */

@Data
public class FilingBoxOperationRecord extends BaseEntity {
    private Long id;
    private int oldSystemboxMark;
    @Excel(name = "Box Department")
    private String boxDepartment;
    @Excel(name = "Box Number")
    private String boxNumber;
    @Excel(name = "Vendor Number")
    private String vendorNumber;
    @Excel(name = "Vendor Barcode")
    private String vendorBarcode;
    @Excel(name = "Retention Date", width = 30, dateFormat = "yyyy-MM-dd")
    private Date retentionDate;
    @Excel(name = "Charge Code")
    private String chargeCode;
    @Excel(name = "Approver LPN")
    private String approverLPN;
    @Excel(name = "Approver Email")
    private String approverEmail;
    private String supportingDocument;
    @Excel(name = "Requester LPN")
    private String requesterLPN;
    @Excel(name = "Requester Email")
    private String requesterEmail;
    @Excel(name = "Box Status")
    private String boxState;
    @Excel(name = "Location")
    private String areaLocation;
    private int borrowState;
    @Excel(name = "Borrower StaffId")
    private String borrowerStaffId;
    @Excel(name = "Borrower Name")
    private String borrowerName;
    @Excel(name = "Destruction Time", width = 30, dateFormat = "yyyy-MM-dd hh:mm:ss")
    private Date destructionTime;
    @Excel(name = "Destruction Officer")
    private String destructionPeople;
    @Excel(name = "Create Time", width = 30, dateFormat = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;
    @Excel(name = "Update Time", width = 30, dateFormat = "yyyy-MM-dd hh:mm:ss")
    private Date updateTime;
    private Long deptId;
    @Excel(name = "Account Code")
    private String accountCode;
    @Excel(name = "Box Remark")
    private String boxRemark;
    @Excel(name = "Create By")
    private String createBy;
    @Excel(name = "Update By")
    private String updateBy;
    /** 审批文件列表 */
    private List<FilingAttachment> approvalFiles;
    /** 文件明细列表 */
    private List<FilingAttachment> fileDetails;
    private String action;
    private String operator;
    private Date operationTime;
    private Long boxId;
    private String filesJson;//json 附件历史记录
}
