package com.rograndec.feijiayun.chain.common.constant;

/**
 * 启用、禁用状态
 * @author lizhongyi
 *
 */
public enum InventoryStatus {
	NORMAL(0,"正常"),
	IN_INVENTORY(1,"盘点中"), ;
	
	private int status;
    private String name;
    
    private InventoryStatus(int status, String name) {
		this.status = status;
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static String getName(Integer status) {          
		for (InventoryStatus es : InventoryStatus.values()) {              
			if (es.getStatus() == status) {                  
				return es.getName();              
			}          
		}          
		return null;      
	}  

}