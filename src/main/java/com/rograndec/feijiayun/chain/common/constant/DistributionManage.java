package com.rograndec.feijiayun.chain.common.constant;

/**
 * 启用、禁用状态
 * @author lizhongyi
 *
 */
public enum DistributionManage {
	DISTRIBUTIONMANAGE_A(0,"禁止直购"),
	DISTRIBUTIONMANAGE_B(1,"允许直购");

	private int status;
    private String name;

    private DistributionManage(int status, String name) {
		this.status = status;
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static String getName(int status) {          
		for (DistributionManage es : DistributionManage.values()) {
			if (es.getStatus() == status) {                  
				return es.getName();              
			}          
		}          
		return null;      
	}  

}