package com.rograndec.feijiayun.chain.business.auth.login.controller;

import com.rograndec.feijiayun.chain.business.auth.constant.SessionKey;
import com.rograndec.feijiayun.chain.business.auth.login.service.LoginService;
import com.rograndec.feijiayun.chain.business.auth.login.shiro.SaasLoginToken;
import com.rograndec.feijiayun.chain.business.auth.login.vo.LoginVO;
import com.rograndec.feijiayun.chain.business.auth.login.vo.UserMenusVO;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.LoginSrc;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


/**
 * 
 * @ClassName: LoginContoller  
 * @Description: 系统登录控制器 
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年8月23日 下午3:59:15  
 *
 */
@Api(value = "login",description = "登录管理")
@RestController
@RequestMapping("login/")
@Validated
public class LoginContoller {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    public Result<UserMenusVO> loginUser(
            @ApiParam(name="loginVO", value="登录信息" , required=true)
            @RequestBody
            @Valid
            LoginVO loginVO
            ,  HttpServletRequest request) {


        Result<UserMenusVO> result = new Result<>();

        try {
        	SaasLoginToken usernamePasswordToken = new SaasLoginToken(loginVO.getLoginAccount(),loginVO.getPassword(),LoginSrc.CHAIN_CLIENT.getValue());
            Subject subject = SecurityUtils.getSubject();
            subject.login(usernamePasswordToken);

            UserVO userByLoginAccount =(UserVO) subject.getPrincipal();

            UserMenusVO login = loginService.login(userByLoginAccount, loginVO, request);

            request.getSession().setAttribute(SessionKey.USER.getCode(),userByLoginAccount);

            result.setBizCodeSuccessInfo(SysCode.SUCCESS,login);
        } catch(UnknownAccountException uae){
            result.setBizCodeFallInfo(SysCode.FAIL,"验证未通过,未知账户");
        }catch(IncorrectCredentialsException ice){
            result.setBizCodeFallInfo(SysCode.FAIL,"密码不正确");
        }catch(LockedAccountException lae){
            result.setBizCodeFallInfo(SysCode.FAIL,"账户已锁定");
        }catch(ExcessiveAttemptsException eae){
            result.setBizCodeFallInfo(SysCode.FAIL,"用户名或密码错误次数过多");
        }catch(AuthenticationException ae){
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(), null == ae.getCause() ? "登录用户名密码不正确" : ae.getCause().getMessage());
        }catch (Exception e){
            result.setBizCodeFallInfo(SysCode.FAIL, null == e.getCause() ? "未知错误,无法登录" : e.getCause().getMessage());

        }

        return result;
    }

    @RequestMapping(value = "/logOut", method = RequestMethod.GET)
    public Result logOut(HttpServletRequest request,@ApiIgnore UserVO userVO) {

        Result result = new Result<>();

        loginService.loginOut(userVO);

        HttpSession session = request.getSession();
        session.removeAttribute(SessionKey.USER.getCode());
        session.setMaxInactiveInterval(1);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }



}
