package com.rograndec.feijiayun.chain.common.constant;

public enum InType {
    //0-获赠；1-报溢；2-领用退回；3-其它
    GET(0,"获赠"),
    REPORT(1,"报溢"),
    BACK(2,"领用退回"),
    OTHER(3,"其他");

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

    InType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(int code) {
        for (GoodsNature c : GoodsNature.values()) {
            if (c.getCode() == code) {
                return c.getName();
            }
        }
        return null;
    }

    public static int getCode(String name) {
        for (GoodsNature c : GoodsNature.values()) {
            if (c.getName().equals(name)) {
                return c.getCode();
            }
        }
        return -1;
    }
}
