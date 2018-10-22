package com.rograndec.feijiayun.chain.common.constant;

/**
 * 商品属性--化妆品
 * @Description:
 * @author sunteng
 * @version 1.0
 * @date 2017年9月8日
 */
public enum GoodsAttributeCosmetics2Enum {
	//非特殊用途化妆品,
	// 特殊用途化妆品-育发,
	// 特殊用途化妆品-染发,
	// 特殊用途化妆品-烫发,
	// 特殊用途化妆品-脱毛,
	// 特殊用途化妆品-美乳,
	// 特殊用途化妆品-健美,
	// 特殊用途化妆品-除臭,
	// 特殊用途化妆品-祛斑,
	// 特殊用途化妆品-防晒
	COSMETICS_NO_SPECIAL_USE(0,"非特殊用途化妆品"),
	COSMETICS_SPECIAL_A(1,"特殊用途化妆品-育发"),
	COSMETICS_SPECIAL_B(2,"特殊用途化妆品-染发"),
	COSMETICS_SPECIAL_C(3,"特殊用途化妆品-烫发"),
	COSMETICS_SPECIAL_D(4,"特殊用途化妆品-脱毛"),
	COSMETICS_SPECIAL_E(1,"特殊用途化妆品-美乳"),
	COSMETICS_SPECIAL_F(1,"特殊用途化妆品-健美"),
	COSMETICS_SPECIAL_G(1,"特殊用途化妆品-除臭"),
	COSMETICS_SPECIAL_H(1,"特殊用途化妆品-祛斑"),
	COSMETICS_SPECIAL_I(1,"特殊用途化妆品-防晒")
	;
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

	GoodsAttributeCosmetics2Enum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String getName(int code) {
		for (GoodsAttributeCosmetics2Enum c : GoodsAttributeCosmetics2Enum.values()) {
			if (c.getCode() == code) {
				return c.getName();
			}
		}
		return null;
	}

	public static int getCode(String name) {
		for (GoodsAttributeCosmetics2Enum c : GoodsAttributeCosmetics2Enum.values()) {
			if (c.getName().equals(name)) {
				return c.getCode();
			}
		}
		return -1;
	}
}