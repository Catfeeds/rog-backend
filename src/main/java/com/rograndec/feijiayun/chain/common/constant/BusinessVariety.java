package com.rograndec.feijiayun.chain.common.constant;

/**
 * 经营品种
 * @ClassName: EconomyType   
 * @Description: TODO(描述该类做什么)
 * @author liuqun
 * @version 1.0 
 * @date 2017年8月22日 下午4:14:21
 */
public enum BusinessVariety {

	DRUGS(0, "药品"),
	MEDICAL_APPARATUS(1, "医疗器械"),
	FOODS(2, "食品"),
	COSMETICS(3, "化妆品"),
	OTHERS(4, "其它");
	
	private int code;
    private String name;
    
	private BusinessVariety(int code, String name) {
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
        for (BusinessVariety c : BusinessVariety.values()) {  
            if (c.getCode() == code) {  
                return c.getName();  
            }  
        }  
        return null;  
    }

	public static int getCode(String name) {
		for (BusinessVariety c : BusinessVariety.values()) {
			if (c.getName().equals(name)) {
				return c.getCode();
			}
		}
		return -1;
	}
}
