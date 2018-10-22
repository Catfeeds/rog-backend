package com.rograndec.feijiayun.chain.common.constant;

/**
 * 商品属性 -- 食物
 * @ClassName: EconomyType
 * @Description:
 * @author sunteng
 * @version 1.0
 * @date 2017年9月8日
 */
public enum GoodsAttributeFood {
	FOOD(0, "食品"),
	FOOD_HEALTH(1, "保健食品"),
	FOOD_INFANT(2, "婴幼儿配方食品"),
	FSMP(3, "特殊医学用途配方食品");
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

	GoodsAttributeFood(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String getName(int code) {
		for (GoodsAttributeFood c : FOOD.values()) {
			if (c.getCode() == code) {
				return c.getName();
			}
		}
		return null;
	}

	public static int getCode(String name) {
		for (GoodsAttributeFood c : FOOD.values()) {
			if (c.getName().equals(name)) {
				return c.getCode();
			}
		}
		return -1;
	}
}