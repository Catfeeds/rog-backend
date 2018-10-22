package com.rograndec.feijiayun.chain.business.basic.warehouse.exception;


/**
 * Created by ST on 2017/9/5.
 */
public class WarehouseException extends BaseException {

    /**
     * 值校验异常
     */
    public static final String VALUE_CHECK = "111112";

    public WarehouseException(String code, String message) {
        super(code, message);
    }



}