package com.rograndec.feijiayun.chain.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
@ApiModel(value = "SelectThreeBean", description = "下拉框id-code-name")
public class SelectThreeBean implements Serializable{

    /**
     * serialVersionUID : <功能描述>
     */
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "下拉框id")
    private  Long id;

    @ApiModelProperty(value = "下拉框银行账号")
    private  String account;

    @ApiModelProperty(value = "下拉框name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
