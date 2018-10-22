package com.rograndec.feijiayun.chain.common.constant;

import java.util.Arrays;

/**
 * 培训方式（0-远程培训；1-现场培训）
 */
public enum TrainMethod {

    LONGRANGE(0,"远程培训"),
    SCENE(1,"现场培训");

    private int code;
    private String value;

    TrainMethod(int code, String value) {
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

    public static TrainMethod getEnum(int code){

        TrainMethod obj = Arrays.stream(TrainMethod.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return obj;

    }

    public static String getValue(int code){

        TrainMethod obj = Arrays.stream(TrainMethod.values())
                .filter(c -> code == (c.code)).findFirst().orElse(null);

        return obj.getValue();

    }
}
