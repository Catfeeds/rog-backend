package com.rograndec.feijiayun.chain.common.constant;

/**
 * 是否医保药店
 * @ClassName: MedicalFlag   
 * @Description: TODO(描述该类做什么)
 * @author liuqun
 * @version 1.0 
 * @date 2017年8月29日 上午11:35:48
 */
public enum MedicalFlag {

	MEDICAL_NO(0,"否"),
	MEDICAL_YES(1,"是");
	
	private int code;
    private String name;
    
    private MedicalFlag(int code, String name) {
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
        for (MedicalFlag c : MedicalFlag.values()) {  
            if (c.getCode() == code) {  
                return c.getName();  
            }  
        }  
        return null;  
    }

	public static int getCode(String name) {
		for (MedicalFlag c : MedicalFlag.values()) {
			if (name.equals(c.getName())) {
				return c.getCode();
			}
		}
		return -1;
	}
	
}
