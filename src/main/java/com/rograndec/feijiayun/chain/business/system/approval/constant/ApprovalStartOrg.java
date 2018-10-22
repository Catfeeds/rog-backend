package com.rograndec.feijiayun.chain.business.system.approval.constant;

import java.util.Arrays;

/**
 * 
 * @ClassName: ApprovalStartOrg  
 * @Description: TODO(审批发起机构枚举类，0-总部；1-全部分店；2-仅自营店；3-仅加盟店；4-指定发起机构)  
 * @author jianhui.zhao jianhui.zhao@rograndec.com  
 * @date 2017年8月22日 下午4:19:38  
 *
 */
public enum ApprovalStartOrg {
	ZB("总部", 0),
	QBFD("全部分店", 1),
	JZYD("仅自营店", 2),
	JJMD("仅加盟店", 3),
	ZDFQJG("指定发起机构", 4);
	
	private String orgName;
	private int orgFlag;
	
	private ApprovalStartOrg(String orgName, int orgFlag) {
		this.orgName = orgName;
		this.orgFlag = orgFlag;
	}

	public String getOrgName() {
		return orgName;
	}

	public int getOrgFlag() {
		return orgFlag;
	}


	public static ApprovalStartOrg getApprovalStartOrgEnum(int start){

		ApprovalStartOrg approvalStartOrg = Arrays.stream(ApprovalStartOrg.values()).map(sa -> {

			return sa;
		}).filter(sa -> sa.getOrgFlag() == start).findFirst().orElse(null);

		return approvalStartOrg;
	}

}
