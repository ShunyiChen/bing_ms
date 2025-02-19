package com.ruoyi.filingsystem.utils.enums;

public enum EnumBorrowState {

    YES(0),
    NO(1);

    private final int state;

    EnumBorrowState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }
}