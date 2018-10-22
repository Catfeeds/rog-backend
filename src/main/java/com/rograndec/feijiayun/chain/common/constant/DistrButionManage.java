package com.rograndec.feijiayun.chain.common.constant;

/**
 * 配送管理 要货管理（0-总部控制；1-自主控制）
 */
public enum DistrButionManage {


	DISABLE(0, "禁止直购"),
	ENABLE(1, "允许直购");

	private int code;
    private String name;

	private DistrButionManage(int code, String name) {
		this.code = code;
		this.name = name;
	}
	public int getCode() {
		return code;
	}
	public void setType(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public static String getName(int code) {  
        for (DistrButionManage c : DistrButionManage.values()) {
            if (c.getCode() == code) {  
                return c.getName();  
            }  
        }  
        return null;  
    }  
    
}
