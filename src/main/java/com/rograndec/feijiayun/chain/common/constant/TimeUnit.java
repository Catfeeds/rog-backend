package com.rograndec.feijiayun.chain.common.constant;


/**
 * 日期
 * Created by ST on 2017/9/8.
 */
public enum  TimeUnit {

    DAY(0,"日"),
    MONTH(1,"月"),
    YEAR(2,"年");

    private int code;
    private String name;

    private TimeUnit(int code, String name) {
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
        for (TimeUnit c : TimeUnit.values()) {
            if (c.getCode() == code) {
                return c.getName();
            }
        }
        return null;
    }

    public static int getCode(String name) {
        for (TimeUnit c : TimeUnit.values()) {
            if (c.getName().equals(name)) {
                return c.getCode();
            }
        }
        return -1;
    }
}