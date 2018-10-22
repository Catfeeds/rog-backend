package com.rograndec.feijiayun.chain.common.constant;

public enum GoodsManageStatus {
	WAIT_ON_SHELVES(0,"待上架"),
	ALREADY_ON_SHELVES(1,"已上架"),
	ON_SALE(2,"出售中"),
	SOLD_OUT(3,"已售罄"),
	ALREADY_OFF_SHELVES(4,"已下架"),;
	
	private int code;
    private String name;
    
    private GoodsManageStatus(int code, String name) {
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
	
	public static String getName(Integer code) {
		if(code == null){
			return null;
		}
        for (GoodsManageStatus c : GoodsManageStatus.values()) {  
            if (c.getCode() == code) {  
                return c.getName();  
            }  
        }  
        return null;  
    }  
}
