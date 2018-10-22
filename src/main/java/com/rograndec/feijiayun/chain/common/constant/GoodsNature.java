package com.rograndec.feijiayun.chain.common.constant;

/**
 * 商品性质
 * @ClassName: GoodsNature
 * @Description: TODO(描述该类做什么)
 * @author sunteng
 * @version 1.0
 * @date 2017年9月8日
 */
public enum GoodsNature {

	GOODSNATURE_A(0,"普通商品"),
	GOODSNATURE_B(1,"拆零商品"),
	GOODSNATURE_C(2,"组装商品");
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

	GoodsNature(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String getName(int code) {
		for (GoodsNature c : GoodsNature.values()) {
			if (c.getCode() == code) {
				return c.getName();
			}
		}
		return null;
	}

	public static int getCode(String name) {
		for (GoodsNature c : GoodsNature.values()) {
			if (c.getName().equals(name)) {
				return c.getCode();
			}
		}
		return -1;
	}
}