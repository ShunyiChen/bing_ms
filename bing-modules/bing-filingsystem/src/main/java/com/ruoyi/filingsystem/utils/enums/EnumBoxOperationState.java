package com.ruoyi.filingsystem.utils.enums;

public enum EnumBoxOperationState {
    ORIGINAL("Original"),
    EDIT("Edit"),
    UPLOAD_ATTACHMENT("Upload Attachment"),
    DELETE_ATTACHMENT("Delete Attachment"),
    DESTROY("Destroy"),
    PERM_OUT("Perm Out"),
    EDIT_CHARGE("Edit Charge"),;

    private final String state;

    EnumBoxOperationState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}