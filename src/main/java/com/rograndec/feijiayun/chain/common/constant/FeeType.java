package com.rograndec.feijiayun.chain.common.constant;

/**
 * 费用类型
 * @ClassName: FeeType
 * @Description: TODO(描述该类做什么)
 * @author sunteng
 * @version 1.0
 * @date 2017年9月8日
 */
public enum FeeType {
//费别（0-自费；1-公费；2-医保；3-农合；4-其它）
   SELF_PAYING(0,"自费"),
	PUBLICLY_PAYING(1,"公费"),
	MEDICAL_INSURANCE(2,"医保"),
	RURAL_COOPERATIVE(3,"农合"),
	OTHER(4,"其它")
	;
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

	FeeType(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String getName(int code) {
		for (FeeType c : FeeType.values()) {
			if (c.getCode() == code) {
				return c.getName();
			}
		}
		return null;
	}

	public static int getCode(String name) {
		for (FeeType c : FeeType.values()) {
			if (c.getName().equals(name)) {
				return c.getCode();
			}
		}
		return -1;
	}
}