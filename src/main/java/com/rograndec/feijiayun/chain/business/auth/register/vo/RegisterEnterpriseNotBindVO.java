package com.rograndec.feijiayun.chain.business.auth.register.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

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
public class RegisterEnterpriseNotBindVO implements Serializable {

    @ApiModelProperty(value = "saas用户id",required = true)
    private Long userId;
    @ApiModelProperty(value = "saas用户name",required = true)
    private String userName;
    @ApiModelProperty(value = "融贯通用户id",required = true)
    private Long rgtUserId;
    @ApiModelProperty(value = "saas企业id",required = true)
    private Long enterpriseId;
    @ApiModelProperty(value = "saas企业name",required = true)
    private String enterpriseName;
    @ApiModelProperty(value = "登录账号",required = true)
    private String loginAccount;
    @ApiModelProperty(value = "融贯通企业id",required = true)
    private Long rgtEnterpriseId;
    @ApiModelProperty(value = "saas企业创建时间",required = true)
    private Date enterpriseCreateTime;
    @ApiModelProperty(value = "saas用户创建时间",required = true)
    private Date userCreateTime;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getRgtUserId() {
        return rgtUserId;
    }

    public void setRgtUserId(Long rgtUserId) {
        this.rgtUserId = rgtUserId;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }

    public Long getRgtEnterpriseId() {
        return rgtEnterpriseId;
    }

    public void setRgtEnterpriseId(Long rgtEnterpriseId) {
        this.rgtEnterpriseId = rgtEnterpriseId;
    }

    public Date getEnterpriseCreateTime() {
        return enterpriseCreateTime;
    }

    public void setEnterpriseCreateTime(Date enterpriseCreateTime) {
        this.enterpriseCreateTime = enterpriseCreateTime;
    }

    public Date getUserCreateTime() {
        return userCreateTime;
    }

    public void setUserCreateTime(Date userCreateTime) {
        this.userCreateTime = userCreateTime;
    }
}