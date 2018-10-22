package com.rograndec.feijiayun.chain.inf.pos.sale.constant;


public enum InvFlag {

	NORMAL(0,"正常"),
	IN_INVENTORY(1,"盘点中"),
	HAS_HANDLING(2,"已处理");
	
	private Integer code;
    private String name;

    private InvFlag(Integer code, String name) {
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
		for (InvFlag c : InvFlag.values()) {
			if (c.getCode() == code) {
				return c.getName();
			}
		}
		return null;
	}
}
