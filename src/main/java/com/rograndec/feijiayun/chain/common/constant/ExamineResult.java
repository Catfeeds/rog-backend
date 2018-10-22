package com.rograndec.feijiayun.chain.common.constant;

import java.util.Arrays;

/**
 * 考核结果（0-不合格；1-合格）
 */
public enum ExamineResult {

    UNQUALIFIED(0,"不合格"),
    QUALIFIED(1,"合格");

    private int code;
    private String value;

    ExamineResult(int code, String value) {
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

    public static ExamineResult getEnum(int code){

        ExamineResult obj = Arrays.stream(ExamineResult.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return obj;

    }

    public static String getValue(int code){

        ExamineResult obj = Arrays.stream(ExamineResult.values())
                .filter(c -> code == (c.code)).findFirst().orElse(null);

        return obj.getValue();

    }
}
