package com.rograndec.feijiayun.chain.common.constant;
/**
 * 预收发票状态
 * @author czm
 *
 */
public enum AdvanceInvoiceStatus {
	WAIT_RECEIVE(0,"待收款"),
	PART_RECEIVE(1,"部分收款"),
	FINISH_RECEIVE(2,"已收款"),
	CANCEL_RECEIVE(3,"已冲销");
	private int code;
	private String name;

	private AdvanceInvoiceStatus(int code, String name) {
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
		for (AdvanceInvoiceStatus c : AdvanceInvoiceStatus.values()) {
			if (c.getCode() == code) {
				return c.getName();
			}
		}
		return null;
	}
}
