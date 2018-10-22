package com.rograndec.feijiayun.chain.business.auth.constant;

import java.util.Arrays;

/**
 * 验证码类型
 */
public enum VerificationCode {


    SCODE("scode","验证码");
    private String code;
    private String value;

    VerificationCode(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static VerificationCode getEnum(String code){

        VerificationCode distrInNoticeType = Arrays.stream(VerificationCode.values())
                .filter(c -> code.equals(c.code)).findFirst().orElse(null);

        return distrInNoticeType;

    }

    public static String getValue(String code){

        VerificationCode distrInNoticeType = Arrays.stream(VerificationCode.values())
                .filter(c -> code.equals (c.code)).findFirst().orElse(null);

        return distrInNoticeType.getValue();

    }
}
