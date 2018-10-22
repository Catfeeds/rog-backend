package com.rograndec.feijiayun.chain.business.auth.register.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 
 * saas_register
 * 
 * 
 * @author zhaiwei
 * 
 * 2017-10-21
 */
@ApiModel
public class RegisterUserVO implements Serializable {

    /**
     * 登录账号
     */
    @ApiModelProperty(value = "登录账号",required = true)
    @NotNull(message = "登录账号不能为空")
    @Size(min = 5,max = 20,message = "登录账号长度不对")
    private String loginAccount;

    /**
     * 用户姓名
     */
    @NotNull(message = "用户姓名不能为空")
    @Size(min = 2,max = 20,message = "用户姓名长度不对")
    @ApiModelProperty(value = "用户姓名",required = true)
    private String userName;

    /**
     * 登录密码
     */
    @NotNull(message = "登录密码不能为空")
    @Size(min = 5,max = 20,message = "登录密码长度不对")
    @ApiModelProperty(value = "登录密码",required = true)
    private String password;

    /**
     * 密码确认
     */
    @NotNull(message = "密码确认,不能为空")
    @Size(min = 5,max = 20,message = "密码确认,不能为空")
    @ApiModelProperty(value = "密码确认",required = true)
    private String passwordConfirm;

    /**
     * 手机号码
     */
    @NotNull(message = "手机号码,不能为空")
    @Size(min = 1,message = "手机号码,不能为空")
    @ApiModelProperty(value = "手机号码",required = true)
    private String mobilePhone;

    /**
     * email
     */
    @NotNull(message = "邮箱,不能为空")
    @Size(min = 1,message = "邮箱,不能为空")
    @ApiModelProperty(value = "邮箱",required = true)
    private String email;

    @ApiModelProperty(value = "注册id",required = true)
    private Long id;

    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}