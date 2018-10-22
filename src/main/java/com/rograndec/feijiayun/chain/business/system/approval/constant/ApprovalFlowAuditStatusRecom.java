package com.rograndec.feijiayun.chain.business.system.approval.constant;

import java.util.Arrays;

/**
 * 
 * @ClassName: ApprovalFlowStatus  
 * @Description: TODO(审批流程 复合状态（0-待审核；1-已完成；2-审核被驳回 -2:取消 -1:审核中）)
 * @author jianhui.zhao jianhui.zhao@rograndec.com  
 * @date 2017年8月25日 上午10:45:18  
 *
 */
public enum ApprovalFlowAuditStatusRecom {
	ACTION_STATUS_RECOM_WAIT("待审核", 0),
	ACTION_STATUS_RECOM_PASS("已完成", 1),
	ACTION_STATUS_RECOM_UNPASS("审核被驳回", 2),
	ACTION_STATUS_RECOM_CANCEL("取消",-2),
	ACTION_STATUS_RECOM_IN("审核中",-1);

	private String name;
	private int value;
	
	private ApprovalFlowAuditStatusRecom(String name, int value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public int getValue() {
		return value;
	}


	public static ApprovalFlowAuditStatusRecom getApprovalFlowAuditStatusRecomEnum(int code){

		ApprovalFlowAuditStatusRecom approvalFlowAuditResult = Arrays.stream(ApprovalFlowAuditStatusRecom.values())
				.filter(c -> code == c.value).findFirst().orElse(null);

		return approvalFlowAuditResult;

	}
}
