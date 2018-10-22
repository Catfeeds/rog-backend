package com.rograndec.feijiayun.chain.common.constant;

/**
 * 会员储值管理-变动类型
 * @ClassName: BusinessMode
 * @Description: TODO(描述该类做什么)
 * @author liuqun
 * @version 1.0 
 * @date 2017年8月22日 下午4:14:21
 */
public enum StoredAmountChangeType {


	TOP_UP(0, "充值"),
	DEDUCTIONS(1, "扣款"),
	TRANSFER(2, "转账"),
	INTO(3, "转入"),
	OPEN_CARD(4, "开卡"),
	SALES_PAYMENT(5, "销售付款"),
	SALES_REFUND(6, "销售退款"),
	;

	private int code;
    private String name;

	private StoredAmountChangeType(int code, String name) {
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
        for (StoredAmountChangeType c : StoredAmountChangeType.values()) {
            if (c.getCode() == code) {  
                return c.getName();  
            }  
        }  
        return null;  
    }  
    
}
