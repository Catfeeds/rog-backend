package com.rograndec.feijiayun.chain.business.aftersale.adverse.constant;


import java.util.Arrays;

/**
 * 报告类型
 * 0-新的；1-严重；2-一般
 */
public enum ReportType {

    NEW(0,"新的"),
    SERIOUS(1,"严重"),
    COMMONLY(2,"一般");
    private int code;
    private String value;

    ReportType(int code, String value) {
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

    public static ReportType getEnum(int code){

        ReportType obj = Arrays.stream(ReportType.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return obj;

    }

    public static String getValue(int code){

        ReportType obj = Arrays.stream(ReportType.values())
                .filter(c -> code == (c.code)).findFirst().orElse(null);

        return obj.getValue();

    }

}


