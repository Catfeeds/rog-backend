package com.rograndec.feijiayun.chain.business.online.purchase.constant;

/**
 * 
 * @ClassName: OnlineType
 *
 */
public enum OnlineType {
	
	SMART_SOURCING(3, "连锁版-智能采购"),
	CENTRALIZED(4, "连锁版-集采");
	
	private Integer type;
    private String typeName;
    
	OnlineType(Integer type, String typeName){
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

	public static String getName(int code) {
		for (OnlineType c : OnlineType.values()) {
			if (c.getType() == code) {
				return c.getTypeName();
			}
		}
		return null;
	}

	public static int getCode(String name) {
		for (OnlineType c : OnlineType.values()) {
			if (c.getTypeName().equals(name)) {
				return c.getType();
			}
		}
		return -1;
	}


}
