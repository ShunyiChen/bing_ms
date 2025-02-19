package com.bing.bing.utils.enums;

public enum DPNRequestStatus {
    APPROVED("Approved"),
    CANCELLED_BY_IT("Cancelled by IT"),
    FORMATTED("Formatted"),
    REJECTED("Rejected"),
    WAITING_FOR_CONFIRMATION("Waiting for Confirmation"),
    FIDS_RECEIVED("FIDS Received"),
    PENDING_LEGAL_REVIEW("Pending Legal Review");

    private final String status;

    DPNRequestStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}