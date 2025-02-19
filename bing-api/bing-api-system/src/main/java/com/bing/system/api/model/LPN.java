package com.bing.system.api.model;

import java.io.Serializable;

public class LPN implements Serializable {
    private String lpn;

    public LPN(String lpn) {
        this.lpn = lpn;
    }

    public String getLpn() {
        return lpn;
    }

    public void setLpn(String lpn) {
        this.lpn = lpn;
    }
}
