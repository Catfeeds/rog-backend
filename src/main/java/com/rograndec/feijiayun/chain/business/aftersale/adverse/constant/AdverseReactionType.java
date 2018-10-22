package com.rograndec.feijiayun.chain.business.aftersale.adverse.constant;

import java.util.Arrays;

/**
 * 既往药品不良反应（0-无；1-有；1-不详）
 */
public enum AdverseReactionType {


    NOTHING(0,"无"),
    HAVE(1,"有"),
    Unknown(2,"不详");
    private int code;
    private String value;

    AdverseReactionType(int code, String value) {
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

    public static AdverseReactionType getEnum(int code){

        AdverseReactionType obj = Arrays.stream(AdverseReactionType.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return obj;

    }

    public static String getValue(int code){

        AdverseReactionType obj = Arrays.stream(AdverseReactionType.values())
                .filter(c -> code == (c.code)).findFirst().orElse(null);

        return obj.getValue();

    }
}
