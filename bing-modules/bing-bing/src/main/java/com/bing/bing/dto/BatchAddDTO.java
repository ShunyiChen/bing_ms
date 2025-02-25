package com.bing.bing.dto;

import com.bing.bing.domain.BingPatientRecord;
import lombok.Data;

import java.util.List;

@Data
public class BatchAddDTO {
    private List<BingPatientRecord> data;
}
