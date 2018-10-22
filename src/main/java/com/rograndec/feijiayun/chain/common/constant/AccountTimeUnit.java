package com.rograndec.feijiayun.chain.common.constant;

public enum AccountTimeUnit {
    PER_MONTH(0,"每月"),
    PER_WEEK(1,"每周");

    private int code;
    private String name;

    private AccountTimeUnit(int code, String name) {
        this.code = code;
        this.name = name;
    }
    public int getCode() {
        return code;
    }
    public void setType(int code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public static String getName(int code) {
        for (AccountTimeUnit c : AccountTimeUnit.values()) {
            if (c.getCode() == code) {
                return c.getName();
            }
        }
        return null;
    }
}
