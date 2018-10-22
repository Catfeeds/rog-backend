package com.rograndec.feijiayun.chain.common.constant;

public enum AdvanceInvoiceType {
	TYPE_0(0,"增值税发票"),
	TYPE_1(1,"普通发票"),
	TYPE_2(2,"票据");
	private int code;
	private String name;

	private AdvanceInvoiceType(int code, String name) {
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
		if (code == null) {
			return null;
		}
		for (AdvanceInvoiceType c : AdvanceInvoiceType.values()) {
			if (c.getCode() == code) {
				return c.getName();
			}
		}
		return null;
	}
}
