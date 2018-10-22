package com.rograndec.feijiayun.chain.business.purchase.retout.vo;


import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * 功能描述：
 * Created by ST on 2017/9/14 19:34
 */

public class ResponsePurchaseReturnOutAllVO {

    /**
     * 金额合计（金额合计）
     */
    @ApiModelProperty(value = "金额合计（金额合计）整个表单的合计")
    private BigDecimal amountTotalAll;

    /**
     * 不含税金额合计
     */
    @ApiModelProperty(value = "不含税金额合计整个表单的合计")
    private BigDecimal notaxRealAmountTotalAll;

    /**
     * 税额合计
     */
    @ApiModelProperty(value = "税额合计整个表单的合计\"")
    private BigDecimal taxAmountTotalAll;

    public BigDecimal getAmountTotalAll() {
        return amountTotalAll;
    }

    public void setAmountTotalAll(BigDecimal amountTotalAll) {
        this.amountTotalAll = amountTotalAll;
    }

    public BigDecimal getNotaxRealAmountTotalAll() {
        return notaxRealAmountTotalAll;
    }

    public void setNotaxRealAmountTotalAll(BigDecimal notaxRealAmountTotalAll) {
        this.notaxRealAmountTotalAll = notaxRealAmountTotalAll;
    }

    public BigDecimal getTaxAmountTotalAll() {
        return taxAmountTotalAll;
    }

    public void setTaxAmountTotalAll(BigDecimal taxAmountTotalAll) {
        this.taxAmountTotalAll = taxAmountTotalAll;
    }

    public List<ResponsePurchaseReturnOutVO> getPurchaseReturnOutVOList() {
        return purchaseReturnOutVOList;
    }

    public void setPurchaseReturnOutVOList(List<ResponsePurchaseReturnOutVO> purchaseReturnOutVOList) {
        this.purchaseReturnOutVOList = purchaseReturnOutVOList;
    }

    List<ResponsePurchaseReturnOutVO> purchaseReturnOutVOList;
}