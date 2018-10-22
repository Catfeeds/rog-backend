package com.rograndec.feijiayun.chain.common.constant;

/**
 * 经营方式常量
 * @ClassName: ModeOperation   
 * @Description: TODO(描述该类做什么)
 * @author liuqun
 * @version 1.0 
 * @date 2017年8月22日 下午4:29:22
 */
public enum ManagementMode {

	PURCHASE_SALE(0, "购销"),
	PURCHASE_SALE_IN_TIME(1, "实销实结");

	private int code;
    private String name;

	private ManagementMode(int code, String name) {
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
        for (ManagementMode c : ManagementMode.values()) {
            if (c.getCode() == code) {  
                return c.getName();  
            }  
        }  
        return null;  
    }
	public static int getCode(String name) {
		for (ManagementMode c : ManagementMode.values()) {
			if (c.getName().equals(name)) {
				return c.getCode();
			}
		}
		return -1;
	}
}
