package com.rograndec.feijiayun.chain.common.constant;

/**
 * 操作方式 新增 修改
 * @ClassName: MedicalFlag   
 * @Description: TODO(描述该类做什么)
 * @author liuqun
 * @version 1.0 
 * @date 2017年8月29日 上午11:35:48
 */
public enum OperationTypeEnum {

	ADD(0,"新增"),
	UPDATE(1,"修改");

	private int code;
    private String name;

	OperationTypeEnum(int code, String name) {
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
        for (OperationTypeEnum c : OperationTypeEnum.values()) {
            if (c.getCode() == code) {  
                return c.getName();  
            }  
        }  
        return null;  
    }  
	
}
