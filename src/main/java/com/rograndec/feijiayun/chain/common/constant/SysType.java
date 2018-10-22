package com.rograndec.feijiayun.chain.common.constant;

import java.util.Arrays;

/**
 * 创建类型 创建类型 0：用户自定义；1-系统默认
 * Created by zhaiwei on 2017/8/31.
 */
public enum SysType {


    CUSTOMIZE(0,"用户自定义"),
    SYSTEM(1,"系统默认");

    private int code;
    private String value;

    SysType(int code, String value) {
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

    public static SysType getSysTypeEnum(int code){

        SysType userTypeEum = Arrays.stream(SysType.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return userTypeEum;

    }
}
