package com.ruoyi.filingsystem.dto;

import com.ruoyi.common.core.web.domain.BaseEntity;
import com.ruoyi.filingsystem.domain.FilingBoxProfile;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class AddBoxBorrowRecordsDTO extends BaseEntity {
    @NotBlank(message = "StaffId cannot be blank")
    @Size(min = 0, max = 50, message = "StaffId length cannot exceed 50 characters")
    private String staffId;
    @NotBlank(message = "StaffName cannot be blank")
    @Size(min = 0, max = 100, message = "StaffName length cannot exceed 100 characters")
    private String staffName;
    @NotBlank(message = "StaffEmail cannot be blank")
    @Size(min = 0, max = 100, message = "StaffEmail length cannot exceed 100 characters")
    private String staffEmail;
    private int status;
    @NotBlank(message = "Charge code cannot be blank")
    @Size(min = 0, max = 100, message = "Charge code length cannot exceed 100 characters")
    private String chargeCode;
    @NotEmpty(message = "BoxProfile List cannot be empty")
    private List<FilingBoxProfile> list;
}
