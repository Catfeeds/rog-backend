package com.rograndec.feijiayun.chain.business.basic.user.constant;

import java.util.Arrays;

/**
 * 用户code规则（0-部门+4位流水码；1-4位流水码；2-自定义编码）
 * Created by zhaiwei on 2017/8/30.
 */
public enum UserCoderRule {

    SYSTEM_SET_DEPT_SERIAL_NUM(0,"部门+4位流水码"),
    SYSTEM_SET_SERIAL_NUM(1,"4位流水码"),
    CUSTOM_NUM(2,"自定义编码");

    private int code;
    private String value;

    UserCoderRule(int code, String value) {
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

    public static UserCoderRule getUserCoderRule4Code(int code){

        UserCoderRule userTypeEum = Arrays.stream(UserCoderRule.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return userTypeEum;

    }

}
