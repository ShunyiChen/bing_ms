package com.ruoyi.filingsystem.utils.enums;

import lombok.Getter;

/**
 * FilingRoom不同Office的邮件地址
 */
@Getter
public enum EnumOfficeEmail {
//    BJ(1L,"BJFilingRoom@cn.ey.com;"),
//    GZ(2L,"GZFilingRoom@cn.ey.com;"),
//    HK(3L,"HKFilingroom@hk.ey.com;"),
//    SH(4L,"SHFilingRoom@cn.ey.com;"),
//    SZ(5L,"SZFilingRoom@cn.ey.com;");

    BJ(1L,"Simeon.Chen@cn.ey.com;"),
    GZ(2L,"Simeon.Chen@cn.ey.com;"),
    HK(3L,"Simeon.Chen@cn.ey.com;"),
    SH(4L,"Simeon.Chen@cn.ey.com;"),
    SZ(5L,"Simeon.Chen@cn.ey.com;");

    private final Long deptId;
    private final String email;

    EnumOfficeEmail(Long deptId, String email) {
        this.deptId = deptId;
        this.email = email;
    }

    // 根据 deptId 获取 email
    public static String getEmailByDeptId(Long deptId) {
        for (EnumOfficeEmail officeEmail : EnumOfficeEmail.values()) {
            if (officeEmail.getDeptId().equals(deptId)) {
                return officeEmail.getEmail();
            }
        }
        // 返回 null 或抛出异常，视需求而定
        return null; // 或者 throw new IllegalArgumentException("No email found for deptId: " + deptId);
    }
}
