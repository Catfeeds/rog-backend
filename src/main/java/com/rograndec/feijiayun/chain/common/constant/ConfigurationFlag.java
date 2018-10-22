package com.rograndec.feijiayun.chain.common.constant;

/**
 * 配置标示
 * @ClassName: ConfigurationFlag
 * @author liuqun
 * @version 1.0 
 * @date 2017年8月29日 上午11:35:48
 */
public enum ConfigurationFlag {

	CONFIGURATION_YES(0,"必备"),
	CONFIGURATION_NO(1,"可选");

	private int code;
    private String name;

    private ConfigurationFlag(int code, String name) {
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
        for (ConfigurationFlag c : ConfigurationFlag.values()) {
            if (c.getCode() == code) {  
                return c.getName();  
            }  
        }  
        return null;  
    }

	public static int getCode(String name) {
		for (ConfigurationFlag c : ConfigurationFlag.values()) {
			if (name.equals(c.getName())) {
				return c.getCode();
			}
		}
		return -1;
	}
	
}
