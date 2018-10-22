package com.rograndec.feijiayun.chain.common.constant;

/**
 * 专管药品
 * @ClassName: ChargeDrugs
 * @Description: TODO(描述该类做什么)
 * @author sunteng
 * @version 1.0
 * @date 2017年9月8日
 */
public enum ChargeDrugs {

	SPECIAL_DRUGS_CONTAINING_EPHEDRINE(0, "含特殊药品复方制剂-含麻黄碱类复方制剂"),
	SPECIAL_DRUGS_CODEINE_COMPOUND_ORAL_SOLUTION(1, "含特殊药品复方制剂-含可待因复方口服溶液"),
	SPECIAL_DRUGS_DIPHENOXYLATE_TABLETS(2, "含特殊药品复方制剂-复方地芬诺酯片"),
	SPECIAL_DRUGS_LIQUORICE_SLICE(3, "含特殊药品复方制剂-复方甘草片"),
	ANABOLIC_STERIODS(4, "蛋白同化制剂"),
	PEPTIDE_HORMONE(5, "肽类激素");
	private int code;
	private String name;

	ChargeDrugs(int code, String name) {
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
		for (ChargeDrugs c : ChargeDrugs.values()) {
			if (c.getCode() == code) {
				return c.getName();
			}
		}
		return null;
	}

	public static int getCode(String name) {
		for (ChargeDrugs c : ChargeDrugs.values()) {
			if (c.getName().equals(name)) {
				return c.getCode();
			}
		}
		return -1;
	}
}
