package com.rograndec.feijiayun.chain.business.storage.inventory.constant;

import java.util.Arrays;

/**
 * 登记方法
 * 登记方法登记方法(0-按账面登记；1-按实物登记)
 * Created by zhaiwei on 2017/9/29.
 */
public enum RegisterType {

    BOOK_REGISTER(0,"按账面登记"),
    PHYSICAL_REGISTER(1,"按实物登记");

    private int code;
    private String value;

    RegisterType(int code, String value) {
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

    public static RegisterType getEnum(int code){
        RegisterType flowUnitType = Arrays.stream(RegisterType.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return flowUnitType;

    }

    public static String getValue(int code){

        RegisterType flowUnitType = Arrays.stream(RegisterType.values())
                .filter(c -> code == (c.code)).findFirst().orElse(null);

        return flowUnitType.getValue();

    }
}
