package com.rograndec.feijiayun.chain.common.constant;


public enum SaleMode {

	CONVENTIONAL(0,"常规"),
	CHINESE_MEDICINE (1,"中药");
	
	private Integer code;
    private String name;

    private SaleMode(Integer code, String name) {
		this.code = code;
		this.name = name;
	}
	public Integer getCode() {
		return code;
	}
	public void setType(Integer code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public static String getName(Integer code) {
		for (SaleMode c : SaleMode.values()) {
			if (c.getCode() == code) {
				return c.getName();
			}
		}
		return null;
	}
}
