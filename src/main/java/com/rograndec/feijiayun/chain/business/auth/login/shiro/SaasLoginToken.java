package com.rograndec.feijiayun.chain.business.auth.login.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 
 * @ClassName: SaasLoginToken   
 * @Description: 区分登录来源
 * @author yuting.li
 * @version 1.0 
 * @date 2017年10月31日 上午11:47:13
 */
public class SaasLoginToken extends UsernamePasswordToken{
	
	
	
	/**
	 * @Description:
	 * author yuting.li
	 * @date 2017年10月31日 上午11:47:20 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
	// 登录来源
	private String loginSrc;
	
	public SaasLoginToken(final String username, final String password, String loginSrc) {
		super(username, password);
		this.loginSrc = loginSrc;
	}

	public String getLoginSrc() {
		return loginSrc;
	}

	public void setLoginSrc(String loginSrc) {
		this.loginSrc = loginSrc;
	}

}
