package com.ruoyi.filingsystem.utils.enums;

public enum EnumFileState {
    OFFICE("0", "OFFICE"),  //---文件表 用
    WAREHOUSE("1", "WAREHOUSE"),  //---文件表 用
    DESTROYED("2", "DESTROYED"), //  ---文件表 用  ,二期 销毁盒子也在用
    LOSE("3", "LOSE"); //  ---文件表 用
    private EnumFileState(String code, String state) {
        this.code = code;
        this.state = state;
    }

    /**
     * 代码
     */
    private String code;

    /**
     * 名称
     */
    private String state;

    public String getCode() {
        return code;
    }

    public String getState() {
        return state;
    }
}
