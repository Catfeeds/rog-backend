package com.rograndec.feijiayun.chain.common.constant;

/**
 * 功能描述：
 * Created by ST on 2017/11/4 20:07
 */
public enum OpeningInventoryStatus {
    DISABLE(0,"禁用"),
    ENABLE(1,"启用"), ;

    private int code;
    private String name;

    OpeningInventoryStatus(int code, String name) {
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
        for (OpeningInventoryStatus c : OpeningInventoryStatus.values()) {
            if (c.getCode() == code) {
                return c.getName();
            }
        }
        return null;
    }
}
