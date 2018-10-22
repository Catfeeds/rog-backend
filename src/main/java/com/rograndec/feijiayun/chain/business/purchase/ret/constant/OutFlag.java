package com.rograndec.feijiayun.chain.business.purchase.ret.constant;

import java.util.Arrays;

/**
 * 出库标志（0-未出库；1-已出库）
 * Created by zhaiwei on 2017/9/20.
 */
public enum OutFlag {

    NOT_OUT(0,"未出库"),
    OUT(1,"已出库");

    private int code;
    private String value;

    OutFlag(int code, String value) {
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

    public static OutFlag getOutFlagT4Code(int code){

        OutFlag userTypeEum = Arrays.stream(OutFlag.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return userTypeEum;

    }

}
