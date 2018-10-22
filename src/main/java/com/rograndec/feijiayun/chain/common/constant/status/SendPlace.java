package com.rograndec.feijiayun.chain.common.constant.status;

import com.rograndec.feijiayun.chain.common.constant.GoodsNature;

public enum SendPlace {
    //送达地址（0-配送中心仓库；1-门店仓库）

    DISTRIBUTION_CENTER_WAREHOUSE(0,"配送中心仓库"),
    STOREHOUSE(1,"门店仓库");


    private int code;
    private String name;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    SendPlace(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(int code) {
        for (SendPlace c : SendPlace.values()) {
            if (c.getCode() == code) {
                return c.getName();
            }
        }
        return null;
    }

    public static int getCode(String name) {
        for (SendPlace c : SendPlace.values()) {
            if (c.getName().equals(name)) {
                return c.getCode();
            }
        }
        return -1;
    }
}
