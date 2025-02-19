package com.ruoyi.filingsystem.utils.enums;

public enum EnumStartFileID {
    Beijing("BJ", "401400000000"),
    Shanghai("SH", "403300000000"),
    Shenzhen("SZ", "405600000000"),
    Guangzhou("GZ", "407500000000"),
    Hongkong("HK", "852000000000");

    private String name;
    private String startFileID;

    EnumStartFileID(String name, String startFileID) {
        this.name = name;
        this.startFileID = startFileID;
    }

    public static String getStartID(String name) {
        for (EnumStartFileID e : EnumStartFileID.values()) {
            if (e.name.equals(name)) {
                return e.startFileID;
            }
        }
        throw new IllegalArgumentException("Invalid name: " + name);
    }
}
