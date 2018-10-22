package com.rograndec.feijiayun.chain.common.constant;

//非处方药类别
public enum GoodsAttributeDrugsOTCType {

	OTC_TYPE_A(0,"甲类"),
	OTC_TYPE_B(1,"乙类")
	;
	private int code;
	private String name;
	GoodsAttributeDrugsOTCType(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static String getName(int code) {
		for (GoodsAttributeDrugsOTCType c : GoodsAttributeDrugsOTCType.values()) {
			if (c.getCode() == code) {
				return c.getName();
			}
		}
		return null;
	}

	public static int getCode(String name) {
		for (GoodsAttributeDrugsOTCType c : GoodsAttributeDrugsOTCType.values()) {
			if (c.getName().equals(name)) {
				return c.getCode();
			}
		}
		return -1;
	}
}
