package com.rograndec.feijiayun.chain.common.constant;

/**
 *
 * @ClassName: LackHandle
 * @Description: 配货规则
 * @author dongyang.du
 * @date 配送类型
 * 
 */
public enum LackHandle {

	LackHandle_A(0, "生成缺配单"), LackHandle_B(1, "不处理");

	private int code;
	private String name;

	private LackHandle(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setType(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static String getName(Integer code) {
		if (code == null) {
			return null;
		}
		for (LackHandle c : LackHandle.values()) {
			if (c.getCode() == code) {
				return c.getName();
			}
		}
		return null;
	}
}
