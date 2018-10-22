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
 * @ClassName: LoginSrc
 * @Description: 登录来源枚举
 * @author zhongyi.li@rograndec.com
 * @date 2017年10月22日 下午18:55:26
 *
 */
public enum LoginSrc {


	CHAIN_CLIENT(0, "FeiJiaYunChainClient"),// 菲加云连锁客户端
	POS_CLIENT(1, "SaaSClient-POS");// POS客户端

	private int id;
	private String value;

	LoginSrc(int id, String value){
		this.id = id;
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}


}
