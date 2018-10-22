package com.rograndec.feijiayun.chain.common.constant;

/**
 * 商品属性
 * @ClassName: EconomyType
 * @Description:
 * @author sunteng
 * @version 1.0
 * @date 2017年9月8日
 */
public enum GoodsAttribute2DrugsA {

	PATENTMEDICINE(0, "成药"),
	CHINESE_MEDICINAL_MATERIALS(1, "中药材"),
	CHINESE_MEDICINE_DECOCTION_PIECES(2, "中药饮片");
	private int code;
	private String name;

	GoodsAttribute2DrugsA(int code, String name) {
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
		for (GoodsAttribute2DrugsA c : GoodsAttribute2DrugsA.values()) {
			if (c.getCode() == code) {
				return c.getName();
			}
		}
		return null;
	}

	public static int getCode(String name) {
		for (GoodsAttribute2DrugsA c : GoodsAttribute2DrugsA.values()) {
			if (c.getName().equals(name)) {
				return c.getCode();
			}
		}
		return -1;
	}
}
