package com.rograndec.feijiayun.chain.business.auth.login.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import com.rograndec.feijiayun.chain.inf.rogrand.service.RGTService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.rograndec.feijiayun.chain.business.auth.constant.Menu;
import com.rograndec.feijiayun.chain.business.auth.login.dao.LoginLogMapper;
import com.rograndec.feijiayun.chain.business.auth.login.dao.LoginMapper;
import com.rograndec.feijiayun.chain.business.auth.login.exception.LoginBizException;
import com.rograndec.feijiayun.chain.business.auth.login.service.LoginService;
import com.rograndec.feijiayun.chain.business.auth.login.vo.LoginVO;
import com.rograndec.feijiayun.chain.business.auth.login.vo.UserMenusVO;
import com.rograndec.feijiayun.chain.business.auth.register.entity.LoginLog;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserRoleMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.UserRole;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.set.dao.SysActionMapper;
import com.rograndec.feijiayun.chain.business.system.set.dao.SysRoleActionMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.SysRoleAction;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.CommonComponent;
import com.rograndec.feijiayun.chain.common.vo.BaseTreeVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.http.NetworkUtils;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginMapper loginMapper;

	@Autowired
	private LoginLogMapper loginLogMapper;

	@Autowired
	private SysActionMapper sysActionMapper;

	@Autowired
	private SysRoleActionMapper sysRoleActionMapper;

	@Autowired
	private EnterpriseMapper enterpriseMapper;

	@Autowired
	private UserRoleMapper userRoleMapper;

	@Autowired
	private CommonComponent commonComponent;


	@Override
	public UserVO findUserByLoginAccount(String loginAccount) {
		UserVO userVO = loginMapper.findUserByLoginAccount(loginAccount);

		return userVO;
	}


	@Override
	/**
	 * 登录操作
	 * @param loginVO
	 * @param request
	 */
	@Transactional( rollbackFor = Exception.class)
	public UserMenusVO login(UserVO userVO,LoginVO loginVO, HttpServletRequest request){

		Integer loginCount = loginLogMapper.selectLoginCount(userVO.getLoginAccount());

		String ip = NetworkUtils.getIp(request);
		saveLoginLog(
				userVO
				,ip
				,loginCount
				,loginVO.getLoginSrc()
		);
		List<BaseTreeVO> baseTreeVOS = commonComponent.cascadeQuerySetAction(userVO,true);

		UserMenusVO userMenusVO = UserMenusVO.getUserMenusVO(userVO);

		userMenusVO.setMenus(baseTreeVOS);

		return userMenusVO;
	}

	public void saveLoginLog(UserVO userByLoginAccount,String ip,Integer loginCount,String loginSrc){

		LoginLog loginLog = LoginLog.getLoginLog(userByLoginAccount, ip, loginCount, loginSrc);

		loginLogMapper.insertSelective(loginLog);
	}

	@Override
	/**
	 * 登出逻辑
	 * @param userVO
	 */
	public void loginOut(UserVO userVO){

		if(null != userVO){

			LoginLog loginLog = loginLogMapper.selectByDescAccount(userVO.getLoginAccount());
            if(null == loginLog) {
                throw new LoginBizException(SysCode.FORBIDDEN.getCode(),"注销用户不存在");
            }

			LoginLog newLoginLog = new LoginLog();
			newLoginLog.setId(loginLog.getId());
			newLoginLog.setLoginOutTime(new Date());

			Subject subject = SecurityUtils.getSubject();
			subject.logout();

			loginLogMapper.updateByPrimaryKeySelective(newLoginLog);
		}

	}



	@Override
	public UserVO findUserByPOSLoginAccount(String loginAccount) {
		return loginMapper.findUserByPOSLoginAccount(loginAccount);
	}

}
