package com.rograndec.feijiayun.chain.common.constant;

import java.util.Arrays;

/**
 * Created by dudy on 2017/9/29.
 */
public enum StartSaleFromType {


    COMPETENT_UNIT_NOTICE(0,"主管单位通知"),
    GOODS_HANDLE(1,"药品处理");
    private int code;
    private String value;

    StartSaleFromType(int code, String value) {
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

    public static StartSaleFromType getStartSaleFromTypeEnum(int code){

        StartSaleFromType startSaleFromType = Arrays.stream(StartSaleFromType.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return startSaleFromType;

    }
}
