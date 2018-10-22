package com.rograndec.feijiayun.chain.common.constant.warehouse;

/**
 * 功能描述：
 * Created by ST on 2017/12/13 12:03
 */
public enum WarehouseAreaType {

    NORMAL_TEMPERATURE(0,"常温"),
    COOL(1,"阴凉"),
    COLD(2,"冷藏"),
    FREEZE(3,"冷冻");

    private int code;
    private String name;

    private WarehouseAreaType(int code, String name) {
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


}
