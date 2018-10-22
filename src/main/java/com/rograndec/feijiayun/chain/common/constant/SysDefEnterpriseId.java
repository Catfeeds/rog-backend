package com.rograndec.feijiayun.chain.common.constant;

/**
 * 默认全局公用企业id
 */
public enum SysDefEnterpriseId {

	DEF(1L, "默认全局公用企业id");

	private Long code;
    private String name;

	private SysDefEnterpriseId(Long code, String name) {
		this.code = code;
		this.name = name;
	}
	public Long getCode() {
		return code;
	}
	public void setType(Long code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public static String getName(int code) {  
        for (SysDefEnterpriseId c : SysDefEnterpriseId.values()) {
            if (c.getCode() == code) {  
                return c.getName();  
            }  
        }  
        return null;  
    }

	public static Long getCode(String name) {
		for (SysDefEnterpriseId c : SysDefEnterpriseId.values()) {
			if (c.getName().equals(name)) {
				return c.getCode();
			}
		}
		return -1L;
	}
}
