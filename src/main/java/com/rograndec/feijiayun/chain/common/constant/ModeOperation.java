package com.rograndec.feijiayun.chain.common.constant;

/**
 * 经营方式常量
 * @ClassName: ModeOperation   
 * @Description: TODO(描述该类做什么)
 * @author liuqun
 * @version 1.0 
 * @date 2017年8月22日 下午4:29:22
 */
public enum ModeOperation {
	
	PERSONAL_BUSINESS(0, "个人独自经营"),
	PARTNERSHIP_BUSINESS(1, "合伙经营"),
	LIMITED_LIABILITY_COMPANY(2, "有限责任公司"),
	SHARE_LIMITED_LIABILITY_COMPANY(3, "股份有限责任公司");
	
	private int code;
    private String name;
    
	private ModeOperation(int code, String name) {
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
        for (ModeOperation c : ModeOperation.values()) {  
            if (c.getCode() == code) {  
                return c.getName();  
            }  
        }  
        return null;  
    }  
}
