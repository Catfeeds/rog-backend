package com.rograndec.feijiayun.chain.common.constant;

import java.util.Arrays;

/**
 * 价格类型（0-基础价格；1-配货价格；2-零售价格）
 * Created by zhaiwei on 2017/8/31.
 */
public enum PriceType {


    BASE_PRICE(0,"基础价格"),
    DIS_PRICE(1,"配货价格"),
    RETAIL_PRICE(2,"零售价格");

    private int code;
    private String value;

    PriceType(int code, String value) {
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

    public static PriceType getPriceTypeEnum(int code){

        PriceType userTypeEum = Arrays.stream(PriceType.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return userTypeEum;

    }
}
