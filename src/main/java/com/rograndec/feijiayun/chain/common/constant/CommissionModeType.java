package com.rograndec.feijiayun.chain.common.constant;

import java.util.Arrays;

public enum CommissionModeType {

    AMOUNT(0,"金额"),
    RATIO(1,"比例"),
    QUANLITY(2,"数量"),
    PROFITREAT(3,"利润率");

    private int code;
    private String value;

    CommissionModeType(int code, String value) {
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

    public static CommissionModeType getEnum(int code){

        CommissionModeType commissionModeType = Arrays.stream(CommissionModeType.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return commissionModeType;

    }

    public static String getValue(int code){

        CommissionModeType commissionModeType = Arrays.stream(CommissionModeType.values())
                .filter(c -> code == (c.code)).findFirst().orElse(null);

        return commissionModeType.getValue();

    }
}
