package com.rograndec.feijiayun.chain.common.constant;

public enum Nature {

	Wholesale_enterprise(0,"批发企业"),
	manufacturer(1,"生产企业");
	
	private int code;
    private String name;

    private Nature(int code, String name) {
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

	public static String getName(int code) {
		for (Nature c : Nature.values()) {
			if (c.getCode() == code) {
				return c.getName();
			}
		}
		return null;
	}
}
