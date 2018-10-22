package com.rograndec.feijiayun.chain.business.retail.pricing.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;


public class SelectGoodsMemberPrice implements Serializable {
    /**
	 * @Description: TODO(描述funcion功能)
	 * author yuting.li
	 * @date 2017年9月25日 下午8:04:43 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID")
    private Long goodsId;
    @ApiModelProperty(value="零售单价")
    private BigDecimal retailPrice;
    @ApiModelProperty(value="会员单价")
    private BigDecimal memberPrice;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

	public BigDecimal getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(BigDecimal retailPrice) {
		this.retailPrice = retailPrice;
	}

	public BigDecimal getMemberPrice() {
		return memberPrice;
	}

	public void setMemberPrice(BigDecimal memberPrice) {
		this.memberPrice = memberPrice;
	}

    
	
    
    
}