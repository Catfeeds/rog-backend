package com.rograndec.feijiayun.chain.business.distr.branch.constant;

import java.util.Arrays;

/**
 * Created by zhaiwei on 2017/10/12.
 */
public enum DistrAuditType {

    REFUSE(0,"驳回"),
    AGREE(1,"同意");
    private int code;
    private String value;

    DistrAuditType(int code, String value) {
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

    public static DistrAuditType getEnum(int code){

        DistrAuditType distrInNoticeType = Arrays.stream(DistrAuditType.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return distrInNoticeType;

    }

    public static String getValue(int code){

        DistrAuditType distrInNoticeType = Arrays.stream(DistrAuditType.values())
                .filter(c -> code == (c.code)).findFirst().orElse(null);

        return distrInNoticeType.getValue();

    }
}
