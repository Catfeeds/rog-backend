package com.rograndec.feijiayun.chain.common.constant;

/**
 * 启用、禁用状态
 * @author lizhongyi
 *
 */
public enum CheckStatus {
	DISABLE(0,"驳回"),
	ENABLE(1,"同意");

	private int status;
    private String name;

    private CheckStatus(int status, String name) {
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
		for (CheckStatus es : CheckStatus.values()) {
			if (es.getStatus() == status) {                  
				return es.getName();              
			}          
		}          
		return null;      
	}  

}