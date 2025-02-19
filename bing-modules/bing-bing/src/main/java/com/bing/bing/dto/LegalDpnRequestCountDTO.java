package com.bing.bing.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 获取DPN Request各个状态的计数
 *
 * @author Simeon
 * @date 2024-11-23
 */
@Data
public class LegalDpnRequestCountDTO implements Serializable {
    private int totalCount;
    private int approvedCount;
    private int cancelledByITCount;
    private int formattedCount;
    private int rejectedCount;
    private int waitingForConfirmationCount;
    private int pendingLegalReviewCount;
}
