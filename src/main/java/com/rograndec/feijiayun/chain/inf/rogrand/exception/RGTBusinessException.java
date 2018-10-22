package com.rograndec.feijiayun.chain.inf.rogrand.exception;

/**
 * 功能描述：
 * Created by ST on 2017/11/4 10:22
 */

public class RGTBusinessException extends RuntimeException {

    private String message;
    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public RGTBusinessException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}