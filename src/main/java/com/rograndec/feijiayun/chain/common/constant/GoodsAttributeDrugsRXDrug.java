package com.rograndec.feijiayun.chain.common.constant;

//处方药
public enum GoodsAttributeDrugsRXDrug {
	RX_DRUG(1,"处方药");
	private int code;
	private String name;
	GoodsAttributeDrugsRXDrug(int code, String name) {
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
		for (GoodsAttributeDrugsRXDrug c : GoodsAttributeDrugsRXDrug.values()) {
			if (c.getCode() == code) {
				return c.getName();
			}
		}
		return null;
	}

	public static int getCode(String name) {
		for (GoodsAttributeDrugsRXDrug c : GoodsAttributeDrugsRXDrug.values()) {
			if (c.getName().equals(name)) {
				return c.getCode();
			}
		}
		return -1;
	}
}