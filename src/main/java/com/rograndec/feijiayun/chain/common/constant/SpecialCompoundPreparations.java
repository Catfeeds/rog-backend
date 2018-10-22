package com.rograndec.feijiayun.chain.common.constant;

/**
 * 含特殊药品复方制剂
 * @ClassName: SpecialCompoundPreparations
 * @author sunteng
 * @version 1.0
 * @date 2017年9月8日
 */
public enum SpecialCompoundPreparations {

	CONTAINING_EPHEDRINE(0, "含特殊药品复方制剂"),
	ANABOLIC_STERIODS(1, "蛋白同化制剂"),
	PEPTIDE_HORMONE(2, "肽类激素");
	private int code;
	private String name;

	SpecialCompoundPreparations(int code, String name) {
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
		for (SpecialCompoundPreparations c : SpecialCompoundPreparations.values()) {
			if (c.getCode() == code) {
				return c.getName();
			}
		}
		return null;
	}

	public static int getCode(String name) {
		for (SpecialCompoundPreparations c : SpecialCompoundPreparations.values()) {
			if (c.getName().equals(name)) {
				return c.getCode();
			}
		}
		return -1;
	}
}
