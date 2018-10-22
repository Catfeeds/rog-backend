package com.rograndec.feijiayun.chain.business.system.approval.exception;


public class ApprovalFlowBizException extends RuntimeException {

	private static final long serialVersionUID = -160485734664530479L;

	/**
	 * 审批流程不能删除
	 */
	public static final Integer NOT_DELETE = 10001;
	/**
	 * 不能新建流程
	 */
	public static final Integer NOT_ADD = 10002;

	/**
	 * 发起机构或者发起机构类型不匹配
	 */
	public static final Integer NOT_MATCH_START_ORG_TYPE = 10003;

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

	public ApprovalFlowBizException(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public ApprovalFlowBizException(String message, int code, String message1) {
		super(message);
		this.code = code;
		this.message = message1;
	}

	public ApprovalFlowBizException(String message, Throwable cause, int code, String message1) {
		super(message, cause);
		this.code = code;
		this.message = message1;
	}

	public ApprovalFlowBizException(Throwable cause, int code, String message) {
		super(cause);
		this.code = code;
		this.message = message;
	}

	public ApprovalFlowBizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code, String message1) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.code = code;
		this.message = message1;
	}

	public ApprovalFlowBizException(int code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public ApprovalFlowBizException(String message, int code, String message1, Object data) {
		super(message);
		this.code = code;
		this.message = message1;
		this.data = data;
	}

	public ApprovalFlowBizException(String message, Throwable cause, int code, String message1, Object data) {
		super(message, cause);
		this.code = code;
		this.message = message1;
		this.data = data;
	}

	public ApprovalFlowBizException(Throwable cause, int code, String message, Object data) {
		super(cause);
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public ApprovalFlowBizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code, String message1, Object data) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.code = code;
		this.message = message1;
		this.data = data;
	}

	public ApprovalFlowBizException(int code) {
		this.code = code;
	}

	public ApprovalFlowBizException(String message, int code) {
		super(message);
		this.code = code;
	}

	public ApprovalFlowBizException(String message, Throwable cause, int code) {
		super(message, cause);
		this.code = code;
	}

	public ApprovalFlowBizException(Throwable cause, int code) {
		super(cause);
		this.code = code;
	}

	public ApprovalFlowBizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.code = code;
	}
}
