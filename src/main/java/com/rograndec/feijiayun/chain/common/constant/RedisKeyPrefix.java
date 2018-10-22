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
 * redis Prefix key enum
 */
public enum RedisKeyPrefix {

	PURCHASE_RETURNOUT(0,"PurchaseReturnOut:"),
	DRAFT_CACHE(3,"DraftCache:"),
	/**
	 * 配进退出出库,生成的配进退出头单key
	 */
	DISTR_IN_RETURN_OUT_TO_RETURN(1,"DistrInReturnOut_return:"),

	/**
	 * 配进退出出库,生成的配进退出明细单key
	 */
	DISTR_IN_RETURN_OUT_TO_RETURN_DETAIL(2,"DistrInReturnOut_returnDetail:");

	private int code;
    private String name;

    private RedisKeyPrefix(int code, String name) {
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
        for (RedisKeyPrefix c : RedisKeyPrefix.values()) {
            if (c.getCode() == code) {  
                return c.getName();  
            }  
        }  
        return null;  
    }

}
