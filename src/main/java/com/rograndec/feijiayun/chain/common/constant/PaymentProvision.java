package com.rograndec.feijiayun.chain.common.constant;

/**
 * 付款条款
 * @ClassName: PaymentProvision   
 * @Description: TODO(描述该类做什么)
 * @author liuqun
 * @version 1.0 
 * @date 2017年8月29日 上午11:35:22
 */
public enum PaymentProvision {

	CASH_SETTLEMENT(0,"现结"),
	ACCOUNT_PERIOD(1,"账期");
	
	private int code;
    private String name;
    
    private PaymentProvision(int code, String name) {
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
        for (PaymentProvision c : PaymentProvision.values()) {  
            if (c.getCode() == code) {  
                return c.getName();  
            }  
        }  
        return null;  
    }
}
