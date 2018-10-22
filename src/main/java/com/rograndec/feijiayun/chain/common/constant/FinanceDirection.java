
package com.rograndec.feijiayun.chain.common.constant;

/**
 * 财务-方向
 */
public enum FinanceDirection {

	DEBIT(0,"借"),
	CREDIT(1,"贷"),
	BALANCE(2,"平");

	private int direction;
    private String desc;

    FinanceDirection(int direction, String desc){
    	this.direction = direction;
    	this.desc = desc;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public static String getDesc(int direction) {
        for (FinanceDirection c : FinanceDirection.values()) {
            if (c.getDirection() == direction) {
                return c.getDesc();
            }  
        }  
        return null;  
    }

}
