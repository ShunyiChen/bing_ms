package com.bing.bing.dto;

import lombok.Data;

@Data
public class BulkApprovalReturnDTO {
    private int approvedNum;
    private int pendingLegalReviewNum;
    private int updates;
}
