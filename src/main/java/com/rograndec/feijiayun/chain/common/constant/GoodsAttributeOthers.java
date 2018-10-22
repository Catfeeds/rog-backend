package com.rograndec.feijiayun.chain.common.constant;

/**
 * 商品属性---其他
 * @ClassName: EconomyType
 * @Description:
 * @author sunteng
 * @version 1.0
 * @date 2017年9月8日
 */
public enum GoodsAttributeOthers {

	OTHERS_SERVICE(1, "服务"),
	OTHERS_PREMIUM(0, "赠品"),
	OTHERS_DRUG_ELIMINATION(2, "消毒品");
	private int code;
	private String name;

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

	GoodsAttributeOthers(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String getName(int code) {
		for (GoodsAttributeOthers c : GoodsAttributeOthers.values()) {
			if (c.getCode() == code) {
				return c.getName();
			}
		}
		return null;
	}

	public static int getCode(String name) {
		for (GoodsAttributeOthers c : GoodsAttributeOthers.values()) {
			if (c.getName().equals(name)) {
				return c.getCode();
			}
		}
		return -1;
	}
}