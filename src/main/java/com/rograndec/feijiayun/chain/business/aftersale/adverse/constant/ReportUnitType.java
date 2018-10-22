package com.rograndec.feijiayun.chain.business.aftersale.adverse.constant;

import java.util.Arrays;

/**
 * 报告单位类型（0-医疗机构；1-生产企业；2-经营企业；3-个人；4-其它）
 */
public enum ReportUnitType {

    MEDICAL(0,"医疗机构"),
    PRODUCTION(1,"生产企业"),
    MANAGEMENT(2,"经营企业"),
    PERSON(3,"个人"),
    OTHER(4,"其他");

    private int code;
    private String value;

    ReportUnitType(int code, String value) {
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

    public static ReportUnitType getEnum(int code){

        ReportUnitType obj = Arrays.stream(ReportUnitType.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return obj;

    }

    public static String getValue(int code){

        ReportUnitType obj = Arrays.stream(ReportUnitType.values())
                .filter(c -> code == (c.code)).findFirst().orElse(null);

        return obj.getValue();

    }
}
