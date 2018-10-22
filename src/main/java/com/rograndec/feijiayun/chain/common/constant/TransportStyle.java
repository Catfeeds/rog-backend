package com.rograndec.feijiayun.chain.common.constant;
/**
 * 
 * @ClassName: TransportStyle  
 * @Description: 运输方式 
 * @author lei.su lei.su@rograndec.com  
 * @date 2017年8月31日 上午11:18:21  
 *
 */
public enum TransportStyle {

	LAND_TRANSPORT(0,"陆运"),
	SKY_WAREHOUSE(1,"空运"),
	SEA_WAREHOUSE(2,"海运");
	
	private int code;
    private String name;
    
    private TransportStyle(int code, String name) {
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
		for (TransportStyle c : TransportStyle.values()) {
			if (c.getCode() == code) {
				return c.getName();
			}
		}
		return null;
	}
	public static int getCode(String name) {
		for (TransportStyle c : TransportStyle.values()) {
			if (c.getName().equals(name)) {
				return c.getCode();
			}
		}
		return -1;
	}
}
