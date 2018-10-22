package com.rograndec.feijiayun.chain.common.constant;

/**
 * 配货价格类型常量
 * @ClassName: DistrPriceType   
 * @Description: 
 * @author liuqun
 * @version 1.0 
 * @date 2017年8月24日 下午5:26:16
 */
public enum DistrPriceType {
	PRICE_LIST(0, "价格清单"),
	COST_PRICE(1, "成本价"),
	COST_PLUS(2, "成本加成");
	
	private Integer code;
    private String name;
	private DistrPriceType(Integer code, String name) {
		this.code = code;
		this.name = name;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public static String getName(int code) {  
        for (DistrPriceType c : DistrPriceType.values()) {  
            if (c.getCode() == code) {  
                return c.getName();  
            }  
        }  
        return null;  
    }
    
    
}
