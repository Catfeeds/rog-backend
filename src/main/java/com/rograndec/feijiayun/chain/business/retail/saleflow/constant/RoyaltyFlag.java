package com.rograndec.feijiayun.chain.business.retail.saleflow.constant;

import java.util.Arrays;

/**
 * Created by zhaiwei on 2017/8/28.
 */
public enum RoyaltyFlag {

    NO_ROYALTY(0,"未提成"),
    ROYALTY(1,"已提成");


    private int code;
    private String value;

    RoyaltyFlag(int code, String value) {
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

    public static int getRoyaltyFlagCode(int code){

        RoyaltyFlag userTypeEum = Arrays.stream(RoyaltyFlag.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return userTypeEum.getCode();

    }

    public static String getRoyaltyFlagValue(String value){

        RoyaltyFlag userTypeEum = Arrays.stream(RoyaltyFlag.values())
                .filter(c -> value.equals(c.value)).findFirst().orElse(null);

        return userTypeEum.getValue();

    }
}
