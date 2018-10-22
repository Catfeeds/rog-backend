package com.rograndec.feijiayun.chain.business.purchase.ret.constant;

import java.util.Arrays;

/**
 * 温控方式（0-冷藏车；1-冷藏箱；2-保温箱）
 * Created by zhaiwei on 2017/9/20.
 */
public enum TempControlMode {

    CHILL_CAR(0,"冷藏车"),
    CHILL_BOX(1,"冷藏箱"),
    INCUBATOR(2,"保温箱");

    private int code;
    private String value;

    TempControlMode(int code, String value) {
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

    public static TempControlMode getName(int code){

        TempControlMode userTypeEum = Arrays.stream(TempControlMode.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return userTypeEum;

    }

}
