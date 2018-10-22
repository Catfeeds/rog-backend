package com.rograndec.feijiayun.chain.business.storage.inventory.constant;

import java.util.Arrays;

/**
 * 盘点方法（0-按货位；1-按商品）
 * Created by zhaiwei on 2017/9/29.
 */
public enum InvType {

    SHELF(0,"按货位"),
    GOODS(1,"按商品");

    private int code;
    private String value;

    InvType(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static InvType getEnum(int code){
        InvType flowUnitType = Arrays.stream(InvType.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return flowUnitType;

    }

    public static String getValue(int code){

        InvType flowUnitType = Arrays.stream(InvType.values())
                .filter(c -> code == (c.code)).findFirst().orElse(null);

        return flowUnitType.getValue();

    }
}
