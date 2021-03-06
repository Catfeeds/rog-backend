package com.rograndec.feijiayun.chain.common.constant;
/**
 * 付款账期单位（0-天；1-月）
 * @ClassName: PaymentPeriodUnit   
 * @Description: TODO(描述该类做什么)
 * @author liuqun
 * @version 1.0 
 * @date 2017年8月29日 下午2:14:36
 */
public enum PaymentPeriodUnit {

	DAY(0,"天"),
	MONTH(1,"月");
	
	private int code;
    private String name;
    
    private PaymentPeriodUnit(int code, String name) {
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
        for (PaymentPeriodUnit c : PaymentPeriodUnit.values()) {  
            if (c.getCode() == code) {  
                return c.getName();  
            }  
        }  
        return null;  
    }
	
}
