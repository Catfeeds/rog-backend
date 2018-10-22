package com.rograndec.feijiayun.chain.exception;

/**
 * 
 * @ClassName: BusinessException  
 * @Description: 自定义业务异常
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年8月30日 下午12:00:16  
 *
 */
public class BusinessException extends RuntimeException {

	/**  
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)  
	 */  
	private static final long serialVersionUID = 7427463161728567827L;

	private String errorCode;
	
	public BusinessException(){
		super();
	}
	
	public BusinessException(String message){
		super(message);
	}
	
	public BusinessException(String message, Throwable cause){
		super(message, cause);
	}
	
	public BusinessException(String errorCode, String message){
		super(message);
		this.setErrorCode(errorCode);
	}
	
	public BusinessException(String errorCode, String message, Throwable cause){
		super(message, cause);
		this.setErrorCode(errorCode);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
}
