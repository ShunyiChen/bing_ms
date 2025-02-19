package com.ruoyi.filingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileReturnDto {
    //批量归还文件专用Dto
    @NotBlank
    private String staffId;
    private List<Long> fileIdlist =new ArrayList<>();
}
