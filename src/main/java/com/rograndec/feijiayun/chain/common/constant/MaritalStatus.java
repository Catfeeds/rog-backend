/**    
 * @Title: ChainType.java  
 * @Package com.rograndec.feijiayun.chain.common.constant  
 * @Description: TODO(用一句话描述该文件做什么)  
 * @author lei.su lei.su@rograndec.com    
 * @date 2017年8月22日 下午8:55:26  
 * @version V1.0    
 */
package com.rograndec.feijiayun.chain.common.constant;

import java.util.Arrays;

/**
 * 婚姻状况（0-未婚；1-已婚）
 * zhaiwei
 */
public enum MaritalStatus {

	UNMARITALD(0,"未婚"),
	MARITALD(1,"已婚");

	private int code;
    private String name;

    private MaritalStatus(int code, String name) {
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

	public static MaritalStatus getMaritalStatus4Code(int code){

		MaritalStatus maritalStatus = Arrays.stream(MaritalStatus.values())
				.filter(c -> code == c.code).findFirst().orElse(null);

		return maritalStatus;

	}
	public static String getName(Integer code) {
		for (MaritalStatus c : MaritalStatus.values()) {
			if (c.getCode() == code) {
				return c.getName();
			}
		}
		return null;
	}
}
