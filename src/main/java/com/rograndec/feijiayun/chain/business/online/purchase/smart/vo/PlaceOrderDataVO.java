package com.rograndec.feijiayun.chain.business.online.purchase.smart.vo;

import java.io.Serializable;

public class PlaceOrderDataVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author liuqun
	 * @date 2017年11月20日 下午8:34:52 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = -4607165466254809919L;

	private String url;
	
	private Long code;
	
	private String callbackUrl;
	
	private String params;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
