package com.rograndec.feijiayun.chain.common.constant;

/**
 * 启用、禁用状态
 * @author lizhongyi
 *
 */
public enum EnableStatus {
	DISABLE(0,"禁用"),
	ENABLE(1,"启用"), ;
	
	private int status;
    private String name;
    
    private EnableStatus(int status, String name) {
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
		for (EnableStatus es : EnableStatus.values()) {              
			if (es.getStatus() == status) {                  
				return es.getName();              
			}          
		}          
		return null;      
	}

	public static int getCode(String name) {
		for (EnableStatus c : EnableStatus.values()) {
			if (name.equals(c.getName())) {
				return c.getStatus();
			}
		}
		return -1;
	}

}