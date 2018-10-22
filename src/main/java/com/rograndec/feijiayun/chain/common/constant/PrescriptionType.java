package com.rograndec.feijiayun.chain.common.constant;

/**
 * 处方类型
 * @ClassName: PrescriptionOperateType
 * @Description: TODO(描述该类做什么)
 * @author sunteng
 * @version 1.0
 * @date 2017年9月8日
 */
public enum PrescriptionType {

	ORDINARY(0,"普通"),
	EMERGENCY(1,"急诊"),
	PEDIATRICS(2,"儿科"),
	ANESTHETIC(3,"麻"),
	SPIRIT_A(4,"精一"),
	SPIRIT_B(5,"精二"),
	TCM(6,"中药");
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

	PrescriptionType(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String getName(int code) {
		for (PrescriptionType c : PrescriptionType.values()) {
			if (c.getCode() == code) {
				return c.getName();
			}
		}
		return null;
	}

	public static int getCode(String name) {
		for (PrescriptionType c : PrescriptionType.values()) {
			if (c.getName().equals(name)) {
				return c.getCode();
			}
		}
		return -1;
	}
}