package com.rograndec.feijiayun.chain.business.purchase.ret.constant;

/**
 * 结算方式（0-零售缴款；1-应收账款）
 * @ClassName: MedicalFlag   
 * @Description: TODO(描述该类做什么)
 * @author liuqun
 * @version 1.0 
 * @date 2017年8月29日 上午11:35:48
 */
public enum Settlement4RetMode {
	RETAIL_CONTRIBUTION(0,"现结"),
	ACCOUNTS_RECEIVABLE(1,"账期");

	private int code;
    private String name;

    private Settlement4RetMode(int code, String name) {
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
        for (Settlement4RetMode c : Settlement4RetMode.values()) {
            if (c.getCode() == code) {  
                return c.getName();  
            }  
        }  
        return null;  
    }
}
