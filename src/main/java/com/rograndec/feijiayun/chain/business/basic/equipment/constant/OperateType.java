package com.rograndec.feijiayun.chain.business.basic.equipment.constant;

import java.util.Arrays;

public enum OperateType  {

    //0-检查；1-清洁；2-维护;
    CHECK(0,"检查"),
    CLEAN(1,"清洁"),
    MAINTAIN(2,"维护");

    private int code;
    private String value;

    OperateType(int code, String value) {
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

    public static OperateType getEnum(int code){

        OperateType operateType = Arrays.stream(OperateType.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return operateType;

    }

    public static String getValue(int code){

        OperateType operateType = Arrays.stream(OperateType.values())
                .filter(c -> code == (c.code)).findFirst().orElse(null);

        return operateType.getValue();

    }
}
