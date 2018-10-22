package com.rograndec.feijiayun.chain.business.basic.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by ST on 2017/8/30.
 */
@ApiModel
public class UserPwdVO {

    @ApiModelProperty(value = "用户id",required = false)
    private Long userId;
    
    @ApiModelProperty(value = "原始密码",required = true)
    @NotNull(message = "原始密码不能为空")
    @Size(min = 6,max = 20,message = "原始密码长度不对")
    private String password;
    
    @ApiModelProperty(value = "新密码",required = true)
    @NotNull(message = "新密码不能为空")
    @Size(min = 6,max = 20,message = "新密码长度不对")
    private String newPassword;
    
    @ApiModelProperty(value = "确认新密码",required = true)
    @NotNull(message = "确认新密码不能为空")
    @Size(min = 6,max = 20,message = "确认新密码长度不对")
    private String affirmPassword;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getAffirmPassword() {
        return affirmPassword;
    }

    public void setAffirmPassword(String affirmPassword) {
        this.affirmPassword = affirmPassword;
    }
}