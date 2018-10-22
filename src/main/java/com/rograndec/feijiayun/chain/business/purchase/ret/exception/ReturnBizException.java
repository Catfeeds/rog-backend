package com.rograndec.feijiayun.chain.business.purchase.ret.exception;


public class ReturnBizException extends RuntimeException {

	private static final long serialVersionUID = -160485734664530479L;

	/**
	 * 值校验异常
	 */
	public static final Integer VALUE_CHECK = 80001;

	/**
	 * 数据不存在
	 */
	public static final Integer NOT_DATA = 80005;

	private int code;

	private String message;

	private Object data;

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

	public ReturnBizException(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public ReturnBizException(String message, int code, String message1) {
		super(message);
		this.code = code;
		this.message = message1;
	}

	public ReturnBizException(String message, Throwable cause, int code, String message1) {
		super(message, cause);
		this.code = code;
		this.message = message1;
	}

	public ReturnBizException(Throwable cause, int code, String message) {
		super(cause);
		this.code = code;
		this.message = message;
	}

	public ReturnBizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code, String message1) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.code = code;
		this.message = message1;
	}

	public ReturnBizException(int code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public ReturnBizException(String message, int code, String message1, Object data) {
		super(message);
		this.code = code;
		this.message = message1;
		this.data = data;
	}

	public ReturnBizException(String message, Throwable cause, int code, String message1, Object data) {
		super(message, cause);
		this.code = code;
		this.message = message1;
		this.data = data;
	}

	public ReturnBizException(Throwable cause, int code, String message, Object data) {
		super(cause);
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public ReturnBizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code, String message1, Object data) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.code = code;
		this.message = message1;
		this.data = data;
	}

	public ReturnBizException(int code) {
		this.code = code;
	}

	public ReturnBizException(String message, int code) {
		super(message);
		this.code = code;
	}

	public ReturnBizException(String message, Throwable cause, int code) {
		super(message, cause);
		this.code = code;
	}

	public ReturnBizException(Throwable cause, int code) {
		super(cause);
		this.code = code;
	}

	public ReturnBizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.code = code;
	}
}
