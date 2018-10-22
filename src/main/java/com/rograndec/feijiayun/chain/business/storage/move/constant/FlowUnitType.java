package com.rograndec.feijiayun.chain.business.storage.move.constant;

import java.util.Arrays;

/**
 * 流向单位
 * 0-部门；1-总部；2-
 * Created by zhaiwei on 2017/9/29.
 */
public enum FlowUnitType {

    DEPT(0,"部门"),
    HEADQUARTERS(1,"总部"),
    STORES(2,"门店"),
    SUPPLIER(3,"供货单位");

    private int code;
    private String value;

    FlowUnitType(int code, String value) {
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

    public static FlowUnitType getEnum(int code){

        FlowUnitType flowUnitType = Arrays.stream(FlowUnitType.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return flowUnitType;

    }

    public static String getValue(int code){

        FlowUnitType flowUnitType = Arrays.stream(FlowUnitType.values())
                .filter(c -> code == (c.code)).findFirst().orElse(null);

        return flowUnitType.getValue();

    }
}
