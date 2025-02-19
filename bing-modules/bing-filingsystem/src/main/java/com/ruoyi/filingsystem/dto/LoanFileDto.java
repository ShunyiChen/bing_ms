package com.ruoyi.filingsystem.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanFileDto {
    @NotBlank
    private String staffId;
    private List<Long> fileIdlist =new ArrayList<>();
}
