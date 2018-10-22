package com.rograndec.feijiayun.chain.business.auth.login.service;

import com.rograndec.feijiayun.chain.business.auth.login.vo.LoginVO;
import com.rograndec.feijiayun.chain.business.auth.login.vo.UserMenusVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {

	UserVO findUserByLoginAccount(String loginAccount);

    /**
     * 登录操作
     * @param loginVO
     * @param request
     */
    UserMenusVO login(UserVO userVO, LoginVO loginVO, HttpServletRequest request);

    /**
     * 登出逻辑
     * @param userVO
     */
    void loginOut(UserVO userVO);
    
    
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
