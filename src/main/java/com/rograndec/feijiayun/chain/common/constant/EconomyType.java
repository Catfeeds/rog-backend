package com.rograndec.feijiayun.chain.common.constant;

/**
 * 经济类型常量
 * @ClassName: EconomyType   
 * @Description: TODO(描述该类做什么)
 * @author liuqun
 * @version 1.0 
 * @date 2017年8月22日 下午4:14:21
 */
public enum EconomyType {
	
	STATE_OWNED_ECONOMY(0, "国有经济"),
	COLLECTIVE_ECONOMY(1, "集体经济"),
	PRIVATE_ECONOMY(2, "私营经济"),
	INDIVIDUAL_ECONOMY(3, "个体经济"),
	ASSOCIATED_ECONOMY(4, "联营经济"),
	JOINT_STOCK_ECONOMY(5, "股份制"),
	FOREIGN_INVESTMENT(6, "外商投资"),
	HONGKONG_MACAO_TAIWAN_INVESTMENT(7, "港澳台投资"),
	OTHER_ECONOMY(8, "其它经济类");
	
	private int code;
    private String name;
    
	private EconomyType(int code, String name) {
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
        for (EconomyType c : EconomyType.values()) {  
            if (c.getCode() == code) {  
                return c.getName();  
            }  
        }  
        return null;  
    }  
    
}
