package com.rograndec.feijiayun.chain.common.constant;

import java.util.Arrays;

public enum SupplierCodeRule {

	GROUPANDSTREAM(0,"供货单位分组+4位流水码"),
	STREAM(1,"4位流水码"),
	SELF(2,"自定义编码");
	
	private int code;
    private String value;

    SupplierCodeRule(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
