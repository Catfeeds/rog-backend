package com.rograndec.feijiayun.chain.business.basic.supplier.exception;


public class SupplierBizException extends RuntimeException {

	private static final long serialVersionUID = -160485734664530479L;

	/**
	 * 值校验异常
	 */
	public static final Integer VALUE_CHECK = 30001;

	/**
	 * 删除失败
	 */
	public static final Integer DELETE_FAIL = 30002;

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

	public SupplierBizException(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public SupplierBizException(String message, int code, String message1) {
		super(message);
		this.code = code;
		this.message = message1;
	}

	public SupplierBizException(String message, Throwable cause, int code, String message1) {
		super(message, cause);
		this.code = code;
		this.message = message1;
	}

	public SupplierBizException(Throwable cause, int code, String message) {
		super(cause);
		this.code = code;
		this.message = message;
	}

	public SupplierBizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code, String message1) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.code = code;
		this.message = message1;
	}

	public SupplierBizException(int code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public SupplierBizException(String message, int code, String message1, Object data) {
		super(message);
		this.code = code;
		this.message = message1;
		this.data = data;
	}

	public SupplierBizException(String message, Throwable cause, int code, String message1, Object data) {
		super(message, cause);
		this.code = code;
		this.message = message1;
		this.data = data;
	}

	public SupplierBizException(Throwable cause, int code, String message, Object data) {
		super(cause);
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public SupplierBizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code, String message1, Object data) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.code = code;
		this.message = message1;
		this.data = data;
	}

	public SupplierBizException(int code) {
		this.code = code;
	}

	public SupplierBizException(String message, int code) {
		super(message);
		this.code = code;
	}

	public SupplierBizException(String message, Throwable cause, int code) {
		super(message, cause);
		this.code = code;
	}

	public SupplierBizException(Throwable cause, int code) {
		super(cause);
		this.code = code;
	}

	public SupplierBizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.code = code;
	}
}
