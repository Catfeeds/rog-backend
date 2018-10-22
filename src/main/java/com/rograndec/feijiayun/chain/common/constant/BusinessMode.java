package com.rograndec.feijiayun.chain.common.constant;

/**
 * 经营方式
 * @ClassName: BusinessMode
 * @Description: TODO(描述该类做什么)
 * @author liuqun
 * @version 1.0 
 * @date 2017年8月22日 下午4:14:21
 */
public enum BusinessMode {


	BusinessMode_A(0, "个人独自经营"),
	BusinessMode_B(1, "合伙经营"),
	BusinessMode_C(2, "有限责任公司"),
	BusinessMode_D(3, "股份有限责任公司"),
	;

	private int code;
    private String name;

	private BusinessMode(int code, String name) {
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
        for (BusinessMode c : BusinessMode.values()) {
            if (c.getCode() == code) {  
                return c.getName();  
            }  
        }  
        return null;  
    }  
    
}
