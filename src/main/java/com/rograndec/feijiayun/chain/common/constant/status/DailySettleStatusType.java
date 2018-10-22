package com.rograndec.feijiayun.chain.common.constant.status;

import java.util.Arrays;

public enum DailySettleStatusType {
    WILLPAY(0,"待缴款"),
    PAYED(1,"已缴款"),
    CHARGEGAINSTED(2,"已冲销");

    private int code;
    private String value;

    DailySettleStatusType(int code, String value) {
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

    public static DailySettleStatusType getEnum(int code){

        DailySettleStatusType dailySettleStatusType = Arrays.stream(DailySettleStatusType.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return dailySettleStatusType;

    }

    public static String getValue(int code){

        DailySettleStatusType dailySettleStatusType = Arrays.stream(DailySettleStatusType.values())
                .filter(c -> code == (c.code)).findFirst().orElse(null);

        return dailySettleStatusType.getValue();

    }


}
