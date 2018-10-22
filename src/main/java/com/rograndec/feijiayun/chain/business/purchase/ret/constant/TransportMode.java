package com.rograndec.feijiayun.chain.business.purchase.ret.constant;

import java.util.Arrays;

/**
 * 运输方式（0-陆运；1-空运；2-海运）
 * Created by zhaiwei on 2017/9/20.
 */
public enum TransportMode {

    LAND(0,"陆运"),
    AIR(1,"空运"),
    SEA(2,"海运");

    private int code;
    private String value;

    TransportMode(int code, String value) {
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

    public static TransportMode getName(int code){

        TransportMode userTypeEum = Arrays.stream(TransportMode.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return userTypeEum;

    }

}
