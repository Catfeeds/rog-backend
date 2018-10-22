package com.rograndec.feijiayun.chain.business.purchase.ret.constant;

import java.util.Arrays;

/**
 * 退货类型（0-质量问题退货；1-非质量问题退货）
 * Created by zhaiwei on 2017/9/20.
 */
public enum ReturnType {

    QUALITY(0,"质量问题退货"),
    NOT_QUALITY(1,"非质量问题退货");

    private int code;
    private String value;

    ReturnType(int code, String value) {
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

    public static ReturnType getValue(int code){

        ReturnType userTypeEum = Arrays.stream(ReturnType.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return userTypeEum;

    }

}
