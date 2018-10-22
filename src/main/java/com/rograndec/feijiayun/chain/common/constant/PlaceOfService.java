package com.rograndec.feijiayun.chain.common.constant;

/**
 * 
 * @ClassName: PlaceOfService  
 * @Description: 送达地点  
 * @author lei.su lei.su@rograndec.com  
 * @date 2017年8月31日 上午11:07:14  
 *
 */
public enum PlaceOfService {

	CAPITAL_WAREHOUSE(0,"配送中心仓库"),
	STORE_WAREHOUSE(1,"门店仓库");
	
	private int code;
    private String name;
    
    private PlaceOfService(int code, String name) {
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
}
