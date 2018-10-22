package com.rograndec.feijiayun.chain.business.basic.user.constant;

import java.util.Arrays;

/**
 * 学历和专业类型
 * Created by zhaiwei on 2017/8/28.
 */
public enum EducationMajorTypeEum {

    EDUCATION(0,"education"),
    MAJOR(1,"major");

    private int code;
    private String value;

    EducationMajorTypeEum(int code, String value) {
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

    public static EducationMajorTypeEum getEducationMajorTypeEum4Code(int code){

        EducationMajorTypeEum userTypeEum = Arrays.stream(EducationMajorTypeEum.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return userTypeEum;

    }

    public static EducationMajorTypeEum getEducationMajorTypeEum4Value(String value){

        EducationMajorTypeEum userTypeEum = Arrays.stream(EducationMajorTypeEum.values())
                .filter(c -> value.equals(c.value)).findFirst().orElse(null);

        return userTypeEum;

    }
}
