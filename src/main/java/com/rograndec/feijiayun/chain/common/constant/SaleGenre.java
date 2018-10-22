package com.rograndec.feijiayun.chain.common.constant;


public enum SaleGenre {

	SALE(0,"销售"),
	SALERETURN(1,"销退");
	
	private Integer code;
    private String name;

    private SaleGenre(Integer code, String name) {
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
		for (SaleGenre c : SaleGenre.values()) {
			if (c.getCode() == code) {
				return c.getName();
			}
		}
		return null;
	}
}
