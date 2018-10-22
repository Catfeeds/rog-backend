package com.rograndec.feijiayun.chain.business.basic.warehouse.exception;


import javax.validation.ValidationException;

/**
 * Created by ST on 2017/9/5.
 */
public class BaseException extends ValidationException {

    private String code;

    private String message;

    private Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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

    public BaseException(String message) {
        super(message);
    }
    public BaseException(String code,String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}