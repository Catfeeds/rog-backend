package com.rograndec.feijiayun.chain.business.aftersale.adverse.constant;

import java.util.Arrays;

/**
 * 0-痊愈；1-好转；2-未好转；3-不详；4-有后遗症；5-死亡
 */
public enum AdverseReactionResultType {


    RECOVERY(0,"痊愈"),
    BETTER(1,"好转"),
    NOBETTER(2,"未好转"),
    UNKNOWN(3,"不详"),
    SEQUELAE(4,"有后遗症"),
    DEAD(5,"死亡");


    private int code;
    private String value;

    AdverseReactionResultType(int code, String value) {
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

    public static AdverseReactionResultType getEnum(int code){

        AdverseReactionResultType obj = Arrays.stream(AdverseReactionResultType.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return obj;

    }

    public static String getValue(int code){

        AdverseReactionResultType obj = Arrays.stream(AdverseReactionResultType.values())
                .filter(c -> code == (c.code)).findFirst().orElse(null);

        return obj.getValue();

    }
}
