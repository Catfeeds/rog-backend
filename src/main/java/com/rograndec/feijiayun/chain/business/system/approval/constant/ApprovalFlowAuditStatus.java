package com.rograndec.feijiayun.chain.business.system.approval.constant;

/**
 * 
 * @ClassName: ApprovalFlowStatus  
 * @Description: TODO(审批流程 审核状态（0-待审核；1-审核通过；2-审核被驳回,3-取消）)
 * @author jianhui.zhao jianhui.zhao@rograndec.com  
 * @date 2017年8月25日 上午10:45:18  
 *
 */
public enum ApprovalFlowAuditStatus {
	DETAIL_STATUS_WAIT("待审核", 0),
	DETAIL_STATUS_PASS("审核通过", 1),
	DETAIL_STATUS_UNPASS("审核被驳回", 2),
	DETAIL_STATUS_CANCEL("取消",-2);
	
	private String name;
	private int value;
	
	ApprovalFlowAuditStatus(String name, int value) {
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
