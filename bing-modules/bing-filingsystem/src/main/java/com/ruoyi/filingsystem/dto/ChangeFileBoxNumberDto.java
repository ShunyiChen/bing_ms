package com.ruoyi.filingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeFileBoxNumberDto {
//批量改变文件的Box number 使用的Dto
    private List<Long> fileIDList =new ArrayList<>();
    private String boxNumber ;
}
