package com.rograndec.feijiayun.chain.common.constant;

import java.util.Arrays;

public enum SaleType {

	//个人独自经营；1-合伙经营；2-有限责任公司；3-股份有限责任公司
	
	INDIVIDUALS(0,"个人独自经营"),
	PARTNERSHIP(1,"合伙经营"),
	Limited_liability_company(2,"有限责任公司"),
	LIMITED_LIABILITY_COMPANY(3,"股份有限责任公司");
	
	private int code;
    private String name;

    private SaleType(int code, String name) {
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
