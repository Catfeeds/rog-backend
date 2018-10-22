package com.rograndec.feijiayun.chain.business.auth.login.vo;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Validated
public class LoginVO {

    /**
     * 登录账号
     */
    @ApiModelProperty(value = "登录账号" ,required = true)
    @NotNull(message = "登录账号不能为空")
    @Size(min = 5,max = 20,message = "登录账号长度不对")
    private String loginAccount;

    /**
     * 登录密码
     */
    @ApiModelProperty(value = "登录密码")
    @NotNull(message = "登录密码不能为空")
    @Size(min = 6,max = 20,message = "登录密码长度不对")
    private String password;

    @ApiModelProperty(value = "登录来源 FeiJiaYunChainClient : 菲加云连锁客户端 ; SaaSClient-POS : POS客户端",required = true)
    @NotNull(message = "登录来源不能为空")
    @Size(min = 7,max = 100,message = "登录来源长度不对")
    private String loginSrc;

    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginSrc() {
        return loginSrc;
    }

    public void setLoginSrc(String loginSrc) {
        this.loginSrc = loginSrc;
    }
}
