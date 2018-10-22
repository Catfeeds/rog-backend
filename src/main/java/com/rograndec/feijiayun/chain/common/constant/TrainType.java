package com.rograndec.feijiayun.chain.common.constant;

import java.util.Arrays;

/**
 * 培训类型（0-岗前培训；1-继续培训）
 */
public enum TrainType {

    PREJOBTRAINING(0,"岗前培训"),
    CONTINUETRAINING(1,"继续培训");

    private int code;
    private String value;

    TrainType(int code, String value) {
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

    public static TrainType getEnum(int code){

        TrainType obj = Arrays.stream(TrainType.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return obj;

    }

    public static String getValue(int code){

        TrainType obj = Arrays.stream(TrainType.values())
                .filter(c -> code == (c.code)).findFirst().orElse(null);

        return obj.getValue();

    }
}
