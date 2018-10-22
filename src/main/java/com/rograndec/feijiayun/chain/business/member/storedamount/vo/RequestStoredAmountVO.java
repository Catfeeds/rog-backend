package com.rograndec.feijiayun.chain.business.member.storedamount.vo;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xingjian.lan@rograndec.com <br/>
 * 生成日期：2017/9/22 <br/>
 * 描述：积分-储值管理-储值操作传参
 */
public class RequestStoredAmountVO {

    @ApiModelProperty(value = "会员卡id", required = true)
    private Long id;

    @ApiModelProperty(value = "操作类型（充值0 扣款1 转账2 修改密码3）", required = true)
    private int changeType;

    @ApiModelProperty(value = "储值改变金额", required = true)
    private BigDecimal changeValue;

    @ApiModelProperty(value = "转账卡号（操作类型为2时必填）", required = false)
    private String transferCardCode;

    @ApiModelProperty(value = "输入密码", required = true)
    private String password;

    @ApiModelProperty(value = "密码确认", required = true)
    private String passwordConfirm;

    @ApiModelProperty(value = "原密码---用于修改密码时", required = true)
    private String oldPassword;

    @ApiModelProperty(value = "新密码---用于修改密码时", required = true)
    private String newPassword;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getChangeType() {
        return changeType;
    }

    public void setChangeType(int changeType) {
        this.changeType = changeType;
    }

    public BigDecimal getChangeValue() {
        return changeValue;
    }

    public void setChangeValue(BigDecimal changeValue) {
        this.changeValue = changeValue;
    }

    public String getTransferCardCode() {
        return transferCardCode;
    }

    public void setTransferCardCode(String transferCardCode) {
        this.transferCardCode = transferCardCode;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
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
}