package com.rograndec.feijiayun.chain.common.constant;

/**
 * 是否首营品种
 * @ClassName: MedicalFlag   
 * @Description: TODO(描述该类做什么)
 * @author liuqun
 * @version 1.0 
 * @date 2017年8月29日 上午11:35:48
 */
public enum FirstGoods {

	FIRST_GOODS_NO(0,"否"),
	FIRST_GOODS_YES(1,"是");

	private int code;
    private String name;

    private FirstGoods(int code, String name) {
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
        for (FirstGoods c : FirstGoods.values()) {
            if (c.getCode() == code) {  
                return c.getName();  
            }  
        }  
        return null;  
    }

	public static int getCode(String name) {
		for (FirstGoods c : FirstGoods.values()) {
			if (name.equals(c.getName())) {
				return c.getCode();
			}
		}
		return -1;
	}
	
}
