package com.rograndec.feijiayun.chain.business.system.approval.constant;

/**
 * 
 * @ClassName: ApprovalStartOrg  
 * @Description: TODO(审批流程系统默认标识（0-系统默认；1-非系统默认）)  
 * @author jianhui.zhao jianhui.zhao@rograndec.com  
 * @date 2017年8月23日 下午1:19:38  
 *
 */
public enum ApprovalFlowDefaultFlag {
	FLAG_DEFAULT_YES("系统默认", 0),
	FLAG_DEFAULT_NO("非系统默认", 1);
	
	private String flagName;
	private int value;
	
	private ApprovalFlowDefaultFlag(String flagName, int value) {
		this.flagName = flagName;
		this.value = value;
	}

	public String getFlagName() {
		return flagName;
	}

	public int getValue() {
		return value;
	}


}
