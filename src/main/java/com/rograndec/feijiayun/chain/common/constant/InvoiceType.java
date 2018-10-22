package com.rograndec.feijiayun.chain.common.constant;

import java.util.Arrays;

public enum InvoiceType {
	COMMERCIAL_INVOICE(0,"普通发票"),
	VAT_INVOICE(1,"增值税发票"), ;
	
	private int code;
    private String name;
    
    private InvoiceType(int code, String name) {
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
	
	public static String getName(Integer code) {  
		InvoiceType t = Arrays.asList(InvoiceType.values()).stream().
				filter(f -> code == f.getCode()).findAny().orElse(null);
        return t.getName();  
    }  
	
	public static void main(String[] args) {
		System.out.println(getName(1));
	}
}
