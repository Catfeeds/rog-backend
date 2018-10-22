package com.rograndec.feijiayun.chain.business.storage.inventory.constant;

import java.util.Arrays;

/**
 * 盘点范围（0-全盘；1-抽盘）
 * Created by zhaiwei on 2017/9/29.
 */
public enum InvRange {

    INVRANGE_A(0,"全盘"),
    INVRANGE_B(1,"抽盘");

    private int code;
    private String value;

    InvRange(int code, String value) {
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

    public static InvRange getEnum(int code){
        InvRange flowUnitType = Arrays.stream(InvRange.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return flowUnitType;

    }

    public static String getValue(int code){

        InvRange flowUnitType = Arrays.stream(InvRange.values())
                .filter(c -> code == (c.code)).findFirst().orElse(null);

        return flowUnitType.getValue();

    }
}
