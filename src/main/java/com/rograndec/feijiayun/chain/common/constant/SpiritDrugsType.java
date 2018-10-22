package com.rograndec.feijiayun.chain.common.constant;

/**
 * 精神药品类别
 * @ClassName: SpiritDrugsType
 * @Description:
 * @author sunteng
 * @version 1.0
 * @date 2017年9月8日
 */
public enum SpiritDrugsType {
	// 精神药品-一类,精神药品-二类
	SPIRIT_DRUGS_FIRST(0, "一类"),
	SPIRIT_DRUGS_SECOND(1, "二类");
	private int code;
	private String name;

	SpiritDrugsType(int code, String name) {
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
		for (SpiritDrugsType c : SpiritDrugsType.values()) {
			if (c.getCode() == code) {
				return c.getName();
			}
		}
		return null;
	}

	public static int getCode(String name) {
		for (SpiritDrugsType c : SpiritDrugsType.values()) {
			if (c.getName().equals(name)) {
				return c.getCode();
			}
		}
		return -1;
	}
}
