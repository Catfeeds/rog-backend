package com.rograndec.feijiayun.chain.business.basic.user.constant;

import com.rograndec.feijiayun.chain.common.constant.ValidFlag;

import java.util.Arrays;

/**
 * Created by zhaiwei on 2017/8/28.
 */
public enum UserStatusEum {

    DEPARTURE(0,"离职"),
    ON_JOB(1,"在职");


    private int code;
    private String value;

    UserStatusEum(int code, String value) {
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

    public static UserStatusEum getUserStatusEum4Code(int code){

        UserStatusEum userTypeEum = Arrays.stream(UserStatusEum.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return userTypeEum;

    }

    public static UserStatusEum getUserStatusEum4Value(String value){

        UserStatusEum userTypeEum = Arrays.stream(UserStatusEum.values())
                .filter(c -> value.equals(c.value)).findFirst().orElse(null);

        return userTypeEum;

    }
    public static String getValue(Integer code){
        for (UserStatusEum c : UserStatusEum.values()) {
            if (c.getCode() == code) {
                return c.getValue();
            }
        }
        return null;
    }
}
