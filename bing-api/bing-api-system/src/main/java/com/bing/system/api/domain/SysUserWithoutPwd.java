package com.bing.system.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SysUserWithoutPwd extends SysUser {

    @JsonIgnore
    protected String password;
}
