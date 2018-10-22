package com.rograndec.feijiayun.chain.common.constant;

/**
 * 供货单位返回的信息
 * @ClassName: PaymentProvision   
 * @Description: TODO(描述该类做什么)
 * @author leisu
 * @version 1.0 
 * @date 2017年8月29日 下午11:35:22
 */
public enum SupplierBackType {

	BASICDETAIL(0,"基本信息"),
	BUSINESS(1,"业务信息"),
	QUALIFICATION(2,"资质信息");
	
	private int code;
    private String name;
    
    private SupplierBackType(int code, String name) {
		this.code = code;
		this.name = name;
	}
	public int getCode() {
		return code;
	}
	public void setType(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
