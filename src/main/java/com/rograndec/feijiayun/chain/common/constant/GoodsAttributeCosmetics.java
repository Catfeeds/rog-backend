package com.rograndec.feijiayun.chain.common.constant;

/**
 * 商品属性--化妆品
 * @ClassName: EconomyType
 * @Description:
 * @author sunteng
 * @version 1.0
 * @date 2017年9月8日
 */
public enum GoodsAttributeCosmetics {

	COSMETICS_NO_SPECIAL_USE(0,"非特殊用途化妆品"),
	COSMETICS_SPECIAL_USE(1,"特殊用途化妆品");
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

	GoodsAttributeCosmetics(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String getName(int code) {
		for (GoodsAttributeCosmetics c : GoodsAttributeCosmetics.values()) {
			if (c.getCode() == code) {
				return c.getName();
			}
		}
		return null;
	}

	public static int getCode(String name) {
		for (GoodsAttributeCosmetics c : GoodsAttributeCosmetics.values()) {
			if (c.getName().equals(name)) {
				return c.getCode();
			}
		}
		return -1;
	}
}