package com.rograndec.feijiayun.chain.common.constant;

import java.util.Arrays;

/**
 * 资质类型是否必选类型
 * Created by zhaiwei on 2017/8/31.
 */
public enum UserQualificationTypeMust {


    OPTIONAL(0,"可选"),
    REQUIRED(1,"必选");

    private int code;
    private String value;

    UserQualificationTypeMust(int code, String value) {
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

    public static UserQualificationTypeMust getUserTypeMustEnum(int code){

        UserQualificationTypeMust userTypeEum = Arrays.stream(UserQualificationTypeMust.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return userTypeEum;

    }
}
