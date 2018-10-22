package com.rograndec.feijiayun.chain.common.constant;

/**
 * 含特殊药品复方制剂
 * @ClassName: SpecialCompoundPreparationsType
 * @Description: TODO(描述该类做什么)
 * @author sunteng
 * @version 1.0
 * @date 2017年9月8日
 */
public enum SpecialCompoundPreparationsType {

	CONTAINING_EPHEDRINE(1, "含麻黄碱类复方制剂"),
	CODEINE_COMPOUND_ORAL_SOLUTION(0, "含可卡因复方口服液"),
	DIPHENOXYLATE_TABLETS(2, "复方地芬诺酯片"),
	LIQUORICE_SLICE(3, "复方甘草片");
	private int code;
	private String name;

	SpecialCompoundPreparationsType(int code, String name) {
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
		for (SpecialCompoundPreparationsType c : SpecialCompoundPreparationsType.values()) {
			if (c.getCode() == code) {
				return c.getName();
			}
		}
		return null;
	}

	public static int getCode(String name) {
		for (SpecialCompoundPreparationsType c : SpecialCompoundPreparationsType.values()) {
			if (c.getName().equals(name)) {
				return c.getCode();
			}
		}
		return -1;
	}
}
