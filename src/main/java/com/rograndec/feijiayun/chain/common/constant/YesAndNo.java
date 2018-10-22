/**    
 * @Title: ChainType.java  
 * @Package com.rograndec.feijiayun.chain.common.constant  
 * @Description: TODO(用一句话描述该文件做什么)  
 * @author lei.su lei.su@rograndec.com    
 * @date 2017年8月22日 下午8:55:26  
 * @version V1.0    
 */
package com.rograndec.feijiayun.chain.common.constant;


/**
 * （0-否；1-是；）
 * sunteng
 */
public enum YesAndNo {

	NO(0,"否"),
	YES(1,"是");

	private int code;
    private String name;

    private YesAndNo(int code, String name) {
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
		for (YesAndNo c : YesAndNo.values()) {
			if (c.getCode() == code) {
				return c.getName();
			}
		}
		return null;
	}

	public static int getCode(String name) {
		for (YesAndNo c : YesAndNo.values()) {
			if (name.equals(c.getName())) {
				return c.getCode();
			}
		}
		return -1;
	}
}
