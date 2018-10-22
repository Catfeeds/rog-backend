/**    
 * @Title: ChainType.java  
 * @Package com.rograndec.feijiayun.chain.common.constant  
 * @Description: TODO(用一句话描述该文件做什么)  
 * @author lei.su lei.su@rograndec.com    
 * @date 2017年8月22日 下午8:55:26  
 * @version V1.0    
 */
package com.rograndec.feijiayun.chain.common.constant;

/**
 * @ClassName: ChainType  
 * @Description: TODO(这里用一句话描述这个类的作用)  
 * @author lei.su lei.su@rograndec.com  
 * @date 2017年8月22日 下午8:55:26  
 *    
 */
public enum DefRoleCode {

	lEAL_MAN("010101","法定代表人"),
	BUSINESS_MAN("010201","企业负责人"),
	QUALITY_OFFICER("030101","质量负责人");

	private String code;
    private String name;

    private DefRoleCode(String code, String name) {
		this.code = code;
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setType(String code) {
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
        for (DefRoleCode c : DefRoleCode.values()) {
            if (c.getCode().equals(code)) {
                return c.getName();  
            }  
        }  
        return null;  
    }



}
