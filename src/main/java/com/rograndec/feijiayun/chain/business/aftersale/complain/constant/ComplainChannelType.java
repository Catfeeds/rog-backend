package com.rograndec.feijiayun.chain.business.aftersale.complain.constant;

import java.util.Arrays;

/**
 * 投诉渠道（0-电话；1-邮箱；2-现场）
 */
public enum ComplainChannelType {

    TELEPHONE(0,"电话"),
    EMAIL(1,"邮箱"),
    SCENE(2,"现场");

    private int code;
    private String value;

    ComplainChannelType(int code, String value) {
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

    public static ComplainChannelType getEnum(int code){

        ComplainChannelType obj = Arrays.stream(ComplainChannelType.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return obj;

    }

    public static String getValue(int code){

        ComplainChannelType obj = Arrays.stream(ComplainChannelType.values())
                .filter(c -> code == (c.code)).findFirst().orElse(null);

        return obj.getValue();

    }
}
