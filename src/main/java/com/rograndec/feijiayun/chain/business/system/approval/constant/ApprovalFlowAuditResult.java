package com.rograndec.feijiayun.chain.business.system.approval.constant;

import java.util.Arrays;

/**
 * 
 * @ClassName: ApprovalFlowStatus  
 * @Description: TODO(审批流程 审批结果（0-拒绝；1-同意；2-待审核）)  
 * @author jianhui.zhao jianhui.zhao@rograndec.com  
 * @date 2017年8月25日 上午10:45:18  
 *
 */
public enum ApprovalFlowAuditResult {
	DETAIL_RESULT_UNPASS("拒绝", 0),
	DETAIL_RESULT_PASS("同意", 1),
	DETAIL_RESULT_WAIT("待审核", 2);
	
	private String name;
	private int value;
	
	private ApprovalFlowAuditResult(String name, int value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public int getValue() {
		return value;
	}

	public static ApprovalFlowAuditResult getApprovalFlowAuditResult(int code){

		ApprovalFlowAuditResult approvalFlowAuditResult = Arrays.stream(ApprovalFlowAuditResult.values())
				.filter(c -> code == c.value).findFirst().orElse(null);

		return approvalFlowAuditResult;

	}
}
