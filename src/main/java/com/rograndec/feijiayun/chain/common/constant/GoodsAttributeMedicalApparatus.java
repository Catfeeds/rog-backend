package com.rograndec.feijiayun.chain.common.constant;

/**
 * 商品属性 -- 医疗器械
 * @ClassName: EconomyType
 * @Description:
 * @author sunteng
 * @version 1.0
 * @date 2017年9月8日
 */
public enum GoodsAttributeMedicalApparatus {

	MEDICAL_APPARATUS_A(0, "一类医疗器械"),
	MEDICAL_APPARATUS_B(1, "二类医疗器械"),
	MEDICAL_APPARATUS_C(2, "三类医疗器械");
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

	GoodsAttributeMedicalApparatus(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String getName(int code) {
		for (GoodsAttributeMedicalApparatus c : GoodsAttributeMedicalApparatus.values()) {
			if (c.getCode() == code) {
				return c.getName();
			}
		}
		return null;
	}

	public static int getCode(String name) {
		for (GoodsAttributeMedicalApparatus c : GoodsAttributeMedicalApparatus.values()) {
			if (c.getName().equals(name)) {
				return c.getCode();
			}
		}
		return -1;
	}
}