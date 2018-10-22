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
 * @ClassName: EnterpriseGroupType
 * @Description: TODO(这里用一句话描述这个类的作用)  
 * @author sunteng
 * @date 2017年10月17日 下午8:55:26
 *    
 */
public enum EnterpriseGroupType {

	Selfoperatedshop(0,"自营店"),
	Division(1,"加盟店");

	private int code;
    private String name;

    private EnterpriseGroupType(int code, String name) {
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
        for (EnterpriseGroupType c : EnterpriseGroupType.values()) {
            if (c.getCode() == code) {  
                return c.getName();  
            }  
        }  
        return null;  
    }  
    
}
