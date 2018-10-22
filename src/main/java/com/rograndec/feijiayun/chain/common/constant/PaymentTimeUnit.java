package com.rograndec.feijiayun.chain.common.constant;

/**
 * 付款时间单位（0-每月；1-每周）
 * @ClassName: PaymentTimeUnit   
 * @Description: TODO(描述该类做什么)
 * @author liuqun
 * @version 1.0 
 * @date 2017年8月29日 下午2:27:41
 */
public enum PaymentTimeUnit {

	PER_MONTH(0,"每月"),
	PER_WEEK(1,"每周");
	
	private int code;
    private String name;
    
    private PaymentTimeUnit(int code, String name) {
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
        for (PaymentTimeUnit c : PaymentTimeUnit.values()) {  
            if (c.getCode() == code) {  
                return c.getName();  
            }  
        }  
        return null;  
    }
	
}
