package com.rograndec.feijiayun.chain.common.constant;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 特殊管理药品excel
 * @ClassName: SpecialDrugs
 * @Description: TODO(描述该类做什么)
 * @author sunteng
 * @version 1.0
 * @date 2017年9月8日
 */
public enum SpecialDrugs {
	// 精神药品-一类,精神药品-二类,麻醉药品,医疗用毒性药品,放射性药品
	SPIRIT_DRUGS_FIRST(0, "精神药品-一类"),
	SPIRIT_DRUGS_SECOND(1, "精神药品-二类"),
	NARCOTIC(2, "麻醉药品"),
	MEDICAL_POISON(3, "医疗用毒性药品"),
	RADIOPHARMACEUTICAL(4, "放射性药品");
	private int code;
	private String name;

	SpecialDrugs(int code, String name) {
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


	public static List<Integer> getSpecialDrugsCodes(){
		return Arrays.stream(SpecialDrugs.values()).map(specialDrugs -> specialDrugs.code).collect(Collectors.toList());
	}

	public static String getName(int code) {
		for (SpecialDrugs c : SpecialDrugs.values()) {
			if (c.getCode() == code) {
				return c.getName();
			}
		}
		return null;
	}

	public static int getCode(String name) {
		for (SpecialDrugs c : SpecialDrugs.values()) {
			if (c.getName().equals(name)) {
				return c.getCode();
			}
		}
		return -1;
	}
}
