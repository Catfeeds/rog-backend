package com.rograndec.feijiayun.chain.business.retail.shift.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by zeshi.sun on 2017/9/22.
 */
public class SelectPosPayeeVO implements Serializable {

    /**
     * 主键ID
     */
    @ApiModelProperty(required = true, value = "主键ID")
    private Long id;

    /**
     * 收款人名称
     */
    @ApiModelProperty(required = false, value = "收款人名称")
    private String payeeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    @Override
    public String toString() {
        return "SelectPosPayeeVO[" +
                "id=" + id +
                ", payeeName='" + payeeName + '\'' +
                ']';
    }
}
