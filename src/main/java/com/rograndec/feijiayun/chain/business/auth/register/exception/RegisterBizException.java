package com.rograndec.feijiayun.chain.business.auth.register.exception;

import com.rograndec.feijiayun.chain.exception.BusinessException;

/**
 * Created by zhaiwei on 2017/9/6.
 */
public class RegisterBizException extends BusinessException {

    /**
     * 值校验异常
     */
    public static final String VALUE_CHECK = "333333";

    public RegisterBizException(String code, String message) {
        super(code, message);
    }
}