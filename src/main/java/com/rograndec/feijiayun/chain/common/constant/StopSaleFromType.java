package com.rograndec.feijiayun.chain.common.constant;

import java.util.Arrays;

/**
 * 停售通知单 来源
 * Created by dudy on 2017/9/29.
 */
public enum  StopSaleFromType {


    COMPETENT_UNIT_NOTICE(0,"主管单位通知"),
    DISPLAY_CHECK(1,"陈列检查"),
    MAINTANCE(2,"药品养护"),
    GOODS_LOCK(3,"商品锁定");



    private int code;
    private String value;

    StopSaleFromType(int code, String value) {
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

    public static StopSaleFromType getStopSaleFromTypeEnum(int code){

        StopSaleFromType stopSaleFromType = Arrays.stream(StopSaleFromType.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return stopSaleFromType;

    }
}
