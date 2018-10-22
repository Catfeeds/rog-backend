package com.rograndec.feijiayun.chain.business.aftersale.adverse.constant;

import com.rograndec.feijiayun.chain.business.distr.branch.constant.DistrAuditType;

import java.util.Arrays;

/**
 * 是否首次报告
 * 0-首次报告；1-跟踪报告
 */
public enum FirstReportType {


    FIRST(0,"首次报告"),
    TRACK(1,"跟踪报告");
    private int code;
    private String value;

    FirstReportType(int code, String value) {
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

    public static FirstReportType getEnum(int code){

        FirstReportType obj = Arrays.stream(FirstReportType.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return obj;

    }

    public static String getValue(int code){

        FirstReportType obj = Arrays.stream(FirstReportType.values())
                .filter(c -> code == (c.code)).findFirst().orElse(null);

        return obj.getValue();

    }
}
