package com.rograndec.feijiayun.chain.common.constant;

import java.util.Arrays;

/**
 *考核方式（0-开卷；1-闭卷）
 */
public enum ExamineMethod {

    OPENBOOK(0,"开卷"),
    CLOSEBOOK(1,"闭卷");

    private int code;
    private String value;

    ExamineMethod(int code, String value) {
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

    public static ExamineMethod getEnum(int code){

        ExamineMethod obj = Arrays.stream(ExamineMethod.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return obj;

    }

    public static String getValue(int code){

        ExamineMethod obj = Arrays.stream(ExamineMethod.values())
                .filter(c -> code == (c.code)).findFirst().orElse(null);

        return obj.getValue();

    }
}
