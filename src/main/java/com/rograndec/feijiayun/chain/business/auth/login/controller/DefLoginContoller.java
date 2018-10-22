package com.rograndec.feijiayun.chain.business.auth.login.controller;

import com.rograndec.feijiayun.chain.business.auth.constant.SessionKey;
import com.rograndec.feijiayun.chain.business.auth.login.service.LoginService;
import com.rograndec.feijiayun.chain.business.auth.login.vo.LoginVO;
import com.rograndec.feijiayun.chain.business.auth.login.vo.UserMenusVO;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.constant.LoginSrc;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**
 *
 * @ClassName: LoginContoller
 * @Description: 系统登录控制器
 * @author zhongyi.li zhongyi.li@rograndec.com
 * @date 2017年8月23日 下午3:59:15
 *
 */

@Profile({"dev","test"})
@Api(value = "defLogin",description = "登录管理")
@RestController
@RequestMapping("defLogin/")
@Validated
public class DefLoginContoller {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/loginUser", method = RequestMethod.GET)
    public Result<UserMenusVO> loginUser(
            @ApiParam(name="loginVO", value="登录信息" , required=true)
            @RequestParam(value = "userName")
                    String userName
            ,  HttpServletRequest request) {

        UserVO userByLoginAccount = loginService.findUserByLoginAccount(userName);

        LoginVO loginVO = new LoginVO();
        loginVO.setLoginAccount(userByLoginAccount.getLoginAccount());
        loginVO.setPassword(userByLoginAccount.getPassword());
        loginVO.setLoginSrc(LoginSrc.CHAIN_CLIENT.getValue());

        UserMenusVO login = loginService.login(userByLoginAccount, loginVO, request);
        request.getSession().setAttribute(SessionKey.USER.getCode(),userByLoginAccount);


       /* System.out.println(request.getSession().getId());
        System.out.println(request.getSession().getMaxInactiveInterval());*/
        Result<UserMenusVO> result = new Result<>();

        return result;
    }




}
