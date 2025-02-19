package com.ruoyi.filingsystem.utils.enums;

public enum EnumSendEmailOption {

    LoanFile("借阅文件通知", 1), ReturnFile("归还文件通知邮件", 2), expireTime("文件到期通知邮件", 3),;
    // 成员变量
    private String Option;
    private int index;
    // 构造方法
    private EnumSendEmailOption(String Option, int index) {
        this.Option = Option;
        this.index = index;
    }
    //覆盖方法
    @Override
    public String toString() {
        return this.index+"_"+this.Option;
    }



}
