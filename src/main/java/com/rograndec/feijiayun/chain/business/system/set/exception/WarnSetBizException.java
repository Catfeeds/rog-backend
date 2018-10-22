package com.rograndec.feijiayun.chain.business.system.set.exception;


import com.rograndec.feijiayun.chain.business.basic.warehouse.exception.BaseException;

/**
 * Created by ST on 2017/9/5.
 */
public class WarnSetBizException extends BaseException {

    /**
     * 值校验异常
     */
    public static final String VALUE_CHECK = "211112";

    public WarnSetBizException(String code, String message) {
        super(code, message);
    }



}