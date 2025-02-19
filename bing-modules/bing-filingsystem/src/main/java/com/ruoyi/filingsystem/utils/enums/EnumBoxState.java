package com.ruoyi.filingsystem.utils.enums;

public enum EnumBoxState {
    IN("0", "IN"),  //---文件表 用
    OUT("1", "OUT"),  //---文件表 用
    DESTROYED("2", "DESTROYED"), //  ---文件表 用  ,二期 销毁盒子也在用
    PERMANENT_OUT("3", "PERM OUT"); //  ---文件表 用
    private EnumBoxState(String code, String state) {
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
