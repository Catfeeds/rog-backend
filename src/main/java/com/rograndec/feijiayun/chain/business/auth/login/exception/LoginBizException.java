package com.rograndec.feijiayun.chain.business.auth.login.exception;

/**
 * Created by zhaiwei on 2017/9/6.
 */

public class LoginBizException extends RuntimeException {

    /**
     * 值校验异常
     */
    public static final String VALUE_CHECK = "0000011111";

    private int code;

    private String message;

    private Object data;
    public LoginBizException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public LoginBizException(String code, String message) {

        this.code = Integer.parseInt(code);
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}