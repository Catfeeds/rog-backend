package com.rograndec.feijiayun.chain.inf.pos.base.Interceptor;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.rograndec.feijiayun.chain.business.auth.constant.SessionKey;
import com.rograndec.feijiayun.chain.business.auth.login.service.LoginService;
import com.rograndec.feijiayun.chain.common.constant.LoginSrc;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.inf.pos.base.entity.POSHeaders;
import com.rograndec.feijiayun.chain.inf.pos.log.service.AddPOSLogServiceImpl;
import com.rograndec.feijiayun.chain.utils.date.DateStyle;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;

/**
 * 
 * @ClassName: InfPosApiInterceptor   
 * @Description: POS接口请求访问拦截器处理
 * @author yuting.li
 * @version 1.0 
 * @date 2017年10月6日 下午5:12:23
 */
@Service("infPosApiInterceptor")
public class InfPosApiInterceptor extends HandlerInterceptorAdapter{
	
	private static final Logger logger = LoggerFactory.getLogger(InfPosApiInterceptor.class);
	
	@Autowired
	private AddPOSLogServiceImpl addPOSLogServiceImpl;
	
	@Autowired
	private LoginService loginService;

	
	/**
	 * This implementation always returns {@code true}.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		//logger.debug("获取拦截方法："+method.getName());
		//判断是否登录处理
		HttpSession session = request.getSession(true);
		
		POSHeaders headers = addPOSLogServiceImpl.getHeaders(request);
		//判断客户端User-Agent
		String fromClient = headers.getFromclient(); //LoginSrc.POS_CLIENT.getValue();
		if(StringUtils.isNotBlank(fromClient) && LoginSrc.POS_CLIENT.getValue().equals(fromClient)) {
			//记录POS请求日志
			if(!"heartbeat".contains(method.getName())) {// 心跳接口不记录日志
				addPOSLogServiceImpl.savePosLog(request);
				// 拦截处理请求头数据，组装session信息
				String enterpriseId = headers.getEnterpriseid();
				String loginAccount = headers.getLoginaccount();
				if(!"loginUser".contains(method.getName())) {
					if(StringUtils.isBlank(enterpriseId) || StringUtils.isBlank(loginAccount)) {
						throw new BusinessException("请设置请求头：loginaccount or enterpriseid");
					}
					if (null == getLoginUser(session)) {
						UserVO posLoginUser = loginService.findUserByPOSLoginAccount(loginAccount);
						if(null == posLoginUser) {
							throw new BusinessException("用户账号不存在！");
						}
						if(!posLoginUser.getEnterpriseId().toString().equals(enterpriseId)) {
							throw new BusinessException(posLoginUser.getEnterpriseName()+"企业没有用户账号："+loginAccount);
						}
						session.setAttribute(SessionKey.POSLOGINUSER.getCode(), posLoginUser);
					}
				}
			}
		}else {
			throw new BusinessException("请设置请求头：fromclient");
		}
		
		logger.debug("接口："+method.getName()+" |POS登录session用户信息："+getLoginUser(session));
		// 测试
		//setPosLoginUser(session);
		//返回服务器时间
		response.setHeader("serverdate", DateUtils.DateToString(new Date(), DateStyle.YYYY_MM_DD_HH_MM_SS));
		return super.preHandle(request, response, handlerMethod);
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void postHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		
	}
	
	
	/**
	 * 得到登录用户对象
	 *
	 * @param session
	 *            会话对象
	 * @return User
	 */
	private UserVO getLoginUser(HttpSession session) {
		if (session == null)
			return null;
		Object obj = session.getAttribute(SessionKey.POSLOGINUSER.getCode());
		return obj != null && obj instanceof UserVO ? (UserVO) obj : null;
	}
	
	// 测试
//	private void setPosLoginUser(HttpSession session) {
//		UserVO user = new UserVO();
//		user.setUserId(1L);
//		user.setEnterpriseId(1L);
//		user.setEnterpriseCode("RGRANDEC");
//		user.setEnterpriseName("融贯医药");
//		user.setParentId(0L);
//		user.setUserCode("0001");
//		user.setUserName("POS-接口测试人员（总部）");
//		user.setLoginAccount("admin");
//		user.setPassword("123456");
//		user.setChainType(0);
//		session.setAttribute(SessionKey.POSLOGINUSER.getCode(), user);
//	}
	
}
