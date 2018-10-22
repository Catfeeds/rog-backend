package com.rograndec.feijiayun.chain.common.constant;

/**
 *
 审核状态（0-全部；1-待审核；2-审批驳回；3-正常;）
 *
 */
public enum Query2ApprovalFlowStatus {

	ALL(0,"全部"),
	ACTION_STATUS_RECOM_WAIT(1,"待审核"),
	ACTION_STATUS_RECOM_UNPASS(2,"审批驳回"),
	ACTION_STATUS_RECOM_PASS(3,"正常"),
	ACTION_STATUS_RECOM_IN(-1,"正常"),
	;

	private Integer type;
	private String typeName;

	private Query2ApprovalFlowStatus(Integer type, String typeName){
		this.type = type;
		this.typeName = typeName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
}
