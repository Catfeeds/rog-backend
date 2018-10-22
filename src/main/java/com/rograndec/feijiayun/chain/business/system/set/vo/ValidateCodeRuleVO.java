package com.rograndec.feijiayun.chain.business.system.set.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class ValidateCodeRuleVO implements Serializable{

    @ApiModelProperty(value = "供货单位编码是否可编辑 0-不可编辑 1-可以编辑")
    private Integer editSupplierCodeRule;

    @ApiModelProperty(value = "商品信息编码是否可编辑 0-不可编辑 1-可以编辑")
    private Integer editGoodsCodeRule;

    @ApiModelProperty(value = "员工信息编码是否可编辑 0-不可编辑 1-可以编辑")
    private Integer editUserCodeRule;

    public Integer getEditSupplierCodeRule() {
        return editSupplierCodeRule;
    }

    public void setEditSupplierCodeRule(Integer editSupplierCodeRule) {
        this.editSupplierCodeRule = editSupplierCodeRule;
    }

    public Integer getEditGoodsCodeRule() {
        return editGoodsCodeRule;
    }

    public void setEditGoodsCodeRule(Integer editGoodsCodeRule) {
        this.editGoodsCodeRule = editGoodsCodeRule;
    }

    public Integer getEditUserCodeRule() {
        return editUserCodeRule;
    }

    public void setEditUserCodeRule(Integer editUserCodeRule) {
        this.editUserCodeRule = editUserCodeRule;
    }
}
