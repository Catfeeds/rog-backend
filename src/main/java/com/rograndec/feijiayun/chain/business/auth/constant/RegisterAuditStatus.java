package com.rograndec.feijiayun.chain.business.auth.constant;

/**
 *
 审核结果（0-待审核；1-同意；2-拒绝)
 *
 */
public enum RegisterAuditStatus {
	DETAIL_STATUS_WAIT("待审核", 0),
	DETAIL_STATUS_PASS("同意", 1),
	DETAIL_STATUS_UNPASS("拒绝", 2);

	private String name;
	private int value;

	RegisterAuditStatus(String name, int value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public int getValue() {
		return value;
	}

}
