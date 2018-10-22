package com.rograndec.feijiayun.chain.common.constant;

/**
 * 特殊管理药品
 * @ClassName: SpecialDrugsAll
 * @Description: TODO(描述该类做什么)
 * @author sunteng
 * @version 1.0
 * @date 2017年9月8日
 */
public enum SpecialDrugsAll {
	// 精神药品,麻醉药品,医疗用毒性药品,放射性药品
	SPIRIT_DRUGS(0, "精神药品"),
	NARCOTIC(1, "麻醉药品"),
	MEDICAL_POISON(2, "医疗用毒性药品"),
	RADIOPHARMACEUTICAL(3, "放射性药品");
	private int code;
	private String name;

	SpecialDrugsAll(int code, String name) {
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
		for (SpecialDrugsAll c : SpecialDrugsAll.values()) {
			if (c.getCode() == code) {
				return c.getName();
			}
		}
		return null;
	}

	public static int getCode(String name) {
		for (SpecialDrugsAll c : SpecialDrugsAll.values()) {
			if (c.getName().equals(name)) {
				return c.getCode();
			}
		}
		return -1;
	}
}
