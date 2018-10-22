package com.rograndec.feijiayun.chain.common.constant;

import java.util.Arrays;

/**
 * 培训内容（0-法律法规；1-药品专业知识及技能；2-质量管理制度；3-职责及岗位操作规程）
 */
public enum TrainContent {

    LAW(0,"法律法规"),
    DRUGKNOWLEDGE(1,"药品专业知识及技能"),
    QUALITYASSURANCE(2,"质量管理制度"),
    POSTOPERATION(3,"职责及岗位操作规程");

    private int code;
    private String value;

    TrainContent(int code, String value) {
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

    public static TrainContent getEnum(int code){

        TrainContent obj = Arrays.stream(TrainContent.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return obj;

    }

    public static String getValue(int code){

        TrainContent obj = Arrays.stream(TrainContent.values())
                .filter(c -> code == (c.code)).findFirst().orElse(null);

        return obj.getValue();

    }
}
