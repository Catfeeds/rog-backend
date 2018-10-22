package com.rograndec.feijiayun.chain.business.retail.royalty.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zhaiwei on 2017/9/27.
 */
public class ResponseSaleTotalVO implements Serializable {

    private List<ResponseSaleRoyaltyVO> responseSaleRoyaltyVOS;

    /**
     * 应提金额
     */
    @ApiModelProperty(value = "应提金额")
    private BigDecimal royaltyAmount = new BigDecimal(0);

    /**
     * 实提金额
     */
    @ApiModelProperty(value = "实提金额")
    private BigDecimal realRoyaltyAmount = new BigDecimal(0);

    /**
     * 提成差额
     */
    @ApiModelProperty(value = "提成差额")
    private BigDecimal diffRoyaltyAmount = new BigDecimal(0);

    public List<ResponseSaleRoyaltyVO> getResponseSaleRoyaltyVOS() {
        return responseSaleRoyaltyVOS;
    }

    public void setResponseSaleRoyaltyVOS(List<ResponseSaleRoyaltyVO> responseSaleRoyaltyVOS) {
        this.responseSaleRoyaltyVOS = responseSaleRoyaltyVOS;
    }

    public BigDecimal getRoyaltyAmount() {
        return royaltyAmount;
    }

    public void setRoyaltyAmount(BigDecimal royaltyAmount) {
        this.royaltyAmount = royaltyAmount;
    }

    public BigDecimal getRealRoyaltyAmount() {
        return realRoyaltyAmount;
    }

    public void setRealRoyaltyAmount(BigDecimal realRoyaltyAmount) {
        this.realRoyaltyAmount = realRoyaltyAmount;
    }

    public BigDecimal getDiffRoyaltyAmount() {
        return diffRoyaltyAmount;
    }

    public void setDiffRoyaltyAmount(BigDecimal diffRoyaltyAmount) {
        this.diffRoyaltyAmount = diffRoyaltyAmount;
    }
}
