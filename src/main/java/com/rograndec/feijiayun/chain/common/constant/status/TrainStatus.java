package com.rograndec.feijiayun.chain.common.constant.status;


import java.util.Arrays;

/**
 * 培训计划状态（0-待培训；1-已培训）
 */
public enum TrainStatus {

    WAITTRAINING(0,"待培训"),
    ALREADYTRAINED(1,"已培训");

    private int code;
    private String value;

    TrainStatus(int code, String value) {
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

    public static TrainStatus getEnum(int code){

        TrainStatus obj = Arrays.stream(TrainStatus.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return obj;

    }

    public static String getValue(int code){

        TrainStatus obj = Arrays.stream(TrainStatus.values())
                .filter(c -> code == (c.code)).findFirst().orElse(null);

        return obj.getValue();

    }
}
