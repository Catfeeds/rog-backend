package com.rograndec.feijiayun.chain.common.constant;

/**
 * 标识（0-作废；1-正常）
 * @ClassName: ValidFlag   
 * @Description: TODO(描述该类做什么)
 * @author liuqun
 * @version 1.0 
 * @date 2017年8月29日 上午11:37:49
 */
public enum ValidFlag {

	NULLIFY(0,"作废"),
	NORMAL(1,"正常");
	
	private int code;
    private String name;
    
    private ValidFlag(int code, String name) {
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
        for (ValidFlag c : ValidFlag.values()) {  
            if (c.getCode() == code) {  
                return c.getName();  
            }  
        }  
        return null;  
    }

	public static int getCode(String name) {
		for (ValidFlag c : ValidFlag.values()) {
			if (name.equals(c.getName())) {
				return c.getCode();
			}
		}
		return -1;
	}
}
