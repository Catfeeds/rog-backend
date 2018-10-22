package com.rograndec.feijiayun.chain.common.constant;

/**
 * 精神药品
 * @ClassName: SpiritDrugs
 * @Description: TODO(描述该类做什么)
 * @author sunteng
 * @version 1.0
 * @date 2017年9月8日
 */
public enum SpiritDrugs {
	// 精神药品
	SPIRIT_DRUGS(0, "精神药品");
	private int code;
	private String name;

	SpiritDrugs(int code, String name) {
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
		for (SpiritDrugs c : SpiritDrugs.values()) {
			if (c.getCode() == code) {
				return c.getName();
			}
		}
		return null;
	}

	public static int getCode(String name) {
		for (SpiritDrugs c : SpiritDrugs.values()) {
			if (c.getName().equals(name)) {
				return c.getCode();
			}
		}
		return -1;
	}
}
