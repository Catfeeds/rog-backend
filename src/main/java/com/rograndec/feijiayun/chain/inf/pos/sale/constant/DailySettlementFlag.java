package com.rograndec.feijiayun.chain.inf.pos.sale.constant;


public enum DailySettlementFlag {

	NO(0,"否"),
	YES(1,"是");
	
	private Integer code;
    private String name;

    private DailySettlementFlag(Integer code, String name) {
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
		for (DailySettlementFlag c : DailySettlementFlag.values()) {
			if (c.getCode() == code) {
				return c.getName();
			}
		}
		return null;
	}
}
