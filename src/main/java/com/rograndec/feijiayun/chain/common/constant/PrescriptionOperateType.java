package com.rograndec.feijiayun.chain.common.constant;

/**
 * 处方操作类型
 * @ClassName: PrescriptionOperateType
 * @Description: TODO(描述该类做什么)
 * @author sunteng
 * @version 1.0
 * @date 2017年9月8日
 */
public enum PrescriptionOperateType {

	AUDIT(0,"审核"),
	SWAP(1,"调剂"),
	CHECKER(2,"核对"),
	MEDICINE(3,"发药");
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

	PrescriptionOperateType(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String getName(int code) {
		for (PrescriptionOperateType c : PrescriptionOperateType.values()) {
			if (c.getCode() == code) {
				return c.getName();
			}
		}
		return null;
	}

	public static int getCode(String name) {
		for (PrescriptionOperateType c : PrescriptionOperateType.values()) {
			if (c.getName().equals(name)) {
				return c.getCode();
			}
		}
		return -1;
	}
}