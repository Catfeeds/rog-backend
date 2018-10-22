package com.rograndec.feijiayun.chain.business.auth.register.vo;

import com.rograndec.feijiayun.chain.business.auth.register.entity.Register;
import io.swagger.annotations.ApiModelProperty;

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
public class CheckRegisterVO implements Serializable {

    /**
     * 账号是否可用(存在并且没有和融贯通关联则表示可用,不存在表示可用,存在但是和融贯通关联则表示不可用)
     */
    @ApiModelProperty(value = "账号是否可用(" +
            "存在并且没有和融贯通关联则表示可用," +
            "不存在表示可用," +
            "存在但是和融贯通关联则表示不可用," +
            "存在并且企业id为0时可用)")
    private boolean enable;

    @ApiModelProperty(value = "存在并且没有和融贯通关联时 会返回注册的信息")
    private Register register;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Register getRegister() {
        return register;
    }

    public void setRegister(Register register) {
        this.register = register;
    }
}