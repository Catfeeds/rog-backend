package com.rograndec.feijiayun.chain.inf.pos.login.controller;

import com.rograndec.feijiayun.chain.business.auth.constant.SessionKey;
import com.rograndec.feijiayun.chain.business.auth.login.exception.LoginBizException;
import com.rograndec.feijiayun.chain.business.auth.login.service.LoginService;
import com.rograndec.feijiayun.chain.business.auth.login.shiro.SaasLoginToken;
import com.rograndec.feijiayun.chain.business.auth.login.vo.LoginVO;
import com.rograndec.feijiayun.chain.business.basic.user.service.UserManagerService;
import com.rograndec.feijiayun.chain.business.basic.user.vo.UserPwdVO;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.LoginSrc;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.inf.pos.login.vo.POSLoginUserVO;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * 
 * @ClassName: POSLoginContoller   
 * @Description: POS登录
 * @author yuting.li
 * @version 1.0 
 * @date 2017年9月28日 下午5:54:48
 */
@Api(value="POSLoginContoller",description = "POS客户端登录")
@RestController
@RequestMapping("/inf/pos/login")
public class POSLoginContoller {
	
	private static Logger logger = LoggerFactory.getLogger(POSLoginContoller.class);
	
	@Autowired
    private LoginService loginService;
	
	@Autowired
    private UserManagerService userManagerService;
	
	@ApiOperation(value = "POS登录", notes = "POS登录 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    public Result<POSLoginUserVO> login(@RequestBody @Valid LoginVO loginVO,HttpServletRequest request) {
		Result<POSLoginUserVO> result = new Result<>();
        try {
        	if(!LoginSrc.POS_CLIENT.getValue().equals(loginVO.getLoginSrc())) {
        		throw new BusinessException("POS客户端来源不对!");
        	}
        	SaasLoginToken usernamePasswordToken = new SaasLoginToken(loginVO.getLoginAccount(),loginVO.getPassword(),LoginSrc.POS_CLIENT.getValue());
			Subject subject = SecurityUtils.getSubject();
			subject.login(usernamePasswordToken);
			UserVO userByLoginAccount =(UserVO) subject.getPrincipal();
    		POSLoginUserVO posLoginUser = new POSLoginUserVO();
    		CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(userByLoginAccount, posLoginUser);
    		request.getSession().setAttribute(SessionKey.POSLOGINUSER.getCode(),userByLoginAccount);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,posLoginUser);
            
		}catch(UnknownAccountException uae){
            result.setBizCodeFallInfo(SysCode.FORBIDDEN,"验证未通过,未知账户");
        }catch(IncorrectCredentialsException ice){
            result.setBizCodeFallInfo(SysCode.FORBIDDEN,"密码不正确");
        }catch(LockedAccountException lae){
            result.setBizCodeFallInfo(SysCode.FORBIDDEN,"账户已锁定");
        }catch(ExcessiveAttemptsException eae){
            result.setBizCodeFallInfo(SysCode.FORBIDDEN,"用户名或密码错误次数过多");
        }catch(AuthenticationException ae){
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            result.setBizCodeFallInfo(SysCode.FORBIDDEN, null == ae.getCause() ? "登录用户名密码不正确" : ae.getCause().getMessage());
        }catch(LoginBizException e) {
        	if(StringUtils.isNotBlank(e.getMessage())) {
        		result.setBizCodeFallInfo(SysCode.FORBIDDEN,e.getMessage());
        	} else {
        		result.setBizCodeFallInfo(SysCode.FAIL);
        	}
			return result;
		}
        return result;
    }
    
    
	@ApiOperation(value = "POS注销", notes = "POS注销 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    @RequestMapping(value = "/logOut", method = RequestMethod.POST)
    public Result<String> logOut(HttpSession session,@ApiParam(name="loginAccount", value="用户账号" , required=true)@RequestBody String loginAccount) {
		Result<String> result = new Result<>();
		UserVO userVO = new UserVO();
		if(StringUtils.isNoneBlank(loginAccount)) {
			userVO.setLoginAccount(loginAccount);
			loginService.loginOut(userVO);
			session.removeAttribute(SessionKey.POSLOGINUSER.getCode());
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,"POS用户注销成功");
		} else {
			result.setBizCodeSuccessInfo(SysCode.FAIL_WITH_TIPS,"注销的用户账号不能为空");
		}
        return result;
    }
	
	
	@ApiOperation(value = "POS修改密码", notes = "POS修改密码 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    @RequestMapping(value = "/setPassword", method = RequestMethod.POST)
    public Result<Object> setPassword(HttpSession session,@RequestBody  @Valid UserPwdVO userPwdVO) {
		Result<Object> result = new Result<>();
		try {
	        UserVO userVO = (UserVO) session.getAttribute("posLoginUser");
	        Long userId = userVO.getUserId();
	        userPwdVO.setUserId(userId);
	        //userPwdVO.setPassword(PasswordUtils.MD5Password(userPwdVO.getPassword()));
	        int count = userManagerService.getUserCountByUIDPwd(userPwdVO);
	        if(count ==  0){
	            //密码错误
	            result.setBizCodeFallInfo(SysCode.FAIL,"密码错误");
	            return result;
	        }
	        String newPassword = userPwdVO.getNewPassword();
	        String affirmPassword = userPwdVO.getAffirmPassword();
	        if(StringUtils.isBlank(newPassword) || !newPassword.equals(affirmPassword)){
	            //密码不一致
	            result.setBizCodeFallInfo(SysCode.FAIL,"密码不一致");
	            return result;
	        }
	        //userPwdVO.setNewPassword(PasswordUtils.MD5Password(userPwdVO.getNewPassword()));
	        userManagerService.updatePwd(userPwdVO,userVO);
	        result.setBizCodeSuccessInfo(SysCode.SUCCESS,"修改成功");
		} catch (Exception e) {
			logger.error("POS修改密码失败:", e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
    }
}
