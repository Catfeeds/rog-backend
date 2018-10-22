package com.rograndec.feijiayun.chain.common.constant;

/**
 * 功能描述：货区作业区域
 * Created by ST on 2017/9/16 17:16
 */

public enum CargoAreaWorkingArea {

    UALIFIED_AREA(0,"合格区"),
    PENDING_AREA(1,"待处理"),
    UNQUALIFIED_AREA(2,"不合格区");

    private int code;
    private String name;

    private CargoAreaWorkingArea(int code, String name) {
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
        for (CargoAreaWorkingArea c : CargoAreaWorkingArea.values()) {
            if (c.getCode() == code) {
                return c.getName();
            }
        }
        return null;
    }

    public static int getCode(String name) {
        for (CargoAreaWorkingArea c : CargoAreaWorkingArea.values()) {
            if (name.equals(c.getName())) {
                return c.getCode();
            }
        }
        return -1;
    }
}