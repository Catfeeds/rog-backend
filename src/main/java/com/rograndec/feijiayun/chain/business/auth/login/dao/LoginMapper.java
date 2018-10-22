package com.rograndec.feijiayun.chain.business.auth.login.dao;

import com.rograndec.feijiayun.chain.common.vo.UserVO;

public interface LoginMapper {

	UserVO findUserByLoginAccount(String loginAccount);
	
	/**
	 * 
	 * @Description: POS 登录验证是否为款员
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月31日 上午10:57:35 
	 * @param loginAccount
	 * @return 
	 * @return UserVO
	 */
	UserVO findUserByPOSLoginAccount(String loginAccount);

}
