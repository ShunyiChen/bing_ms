package com.ruoyi.filingsystem.utils.enums;

public enum EnumFileOperationState {
    ORIGINAL("Original"),
    EDIT("Edit"),
    RECOVER("Recover"),
    DESTROY("Destroy");

    private final String state;

    EnumFileOperationState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}