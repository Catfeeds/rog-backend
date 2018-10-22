package com.rograndec.feijiayun.chain.business.auth.login.shiro;

import com.rograndec.feijiayun.chain.business.auth.login.exception.LoginBizException;
import com.rograndec.feijiayun.chain.common.SysCode;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * 
 * @ClassName: CredentialsMatcher  
 * @Description: shiro 校验密码
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年8月23日 下午5:13:50  
 *
 */
public class CredentialsMatcher extends SimpleCredentialsMatcher{
	
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		// 获得用户输入的密码:(可以采用加盐(salt)的方式去检验)
		String inPassword = new String(upToken.getPassword());
		// 获得数据库中的密码
		String dbPassword = (String) info.getCredentials();
		// 进行密码的比对
		if(!this.equals(inPassword.toUpperCase(), dbPassword.toUpperCase())){
			throw new LoginBizException(SysCode.FORBIDDEN.getCode(),"密码不正确");
		}
		return true;
	}
	
}
