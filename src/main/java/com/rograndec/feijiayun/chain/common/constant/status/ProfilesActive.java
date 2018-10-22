package com.rograndec.feijiayun.chain.common.constant.status;

/**
 * 系统的环境配置标识
 */
public enum ProfilesActive {


	PRD_ALI(0, "prd_ali"),
	TEST(1, "test"),
	DEV(2, "dev")
	;

	private int code;
    private String name;

	ProfilesActive(int code, String name) {
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
        for (ProfilesActive c : ProfilesActive.values()) {
            if (c.getCode() == code) {  
                return c.getName();  
            }  
        }  
        return null;  
    }  
    
}
