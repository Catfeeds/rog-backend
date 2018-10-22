package com.rograndec.feijiayun.chain.business.auth.constant;

import java.util.Arrays;

/**
 * session key
 */
public enum SessionKey {


    USER("user","session user key"),
	POSLOGINUSER("posLoginUser","session posLoginUser key");//pos用户登录信息
    private String code;
    private String value;

    SessionKey(String code, String value) {
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

    public static SessionKey getEnum(String code){

        SessionKey distrInNoticeType = Arrays.stream(SessionKey.values())
                .filter(c -> code.equals(c.code) ).findFirst().orElse(null);

        return distrInNoticeType;

    }

    public static String getValue(String code){

        SessionKey distrInNoticeType = Arrays.stream(SessionKey.values())
                .filter(c -> code.equals(c.code)).findFirst().orElse(null);

        return distrInNoticeType.getValue();

    }
}
