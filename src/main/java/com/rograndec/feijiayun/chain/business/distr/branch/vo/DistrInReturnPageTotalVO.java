package com.rograndec.feijiayun.chain.business.distr.branch.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * saas_distr_in_return_out
 * 
 * 
 * @author zhaiwei
 * 
 * 2017-10-10
 */
public class DistrInReturnPageTotalVO implements Serializable {

    /**
     * 实际金额合计
     */
    @ApiModelProperty(value = "实际金额合计")
    private BigDecimal realAmountTotal;

    /**
     * 不含税金额合计
     */
    @ApiModelProperty(value = "不含税金额合计")
    private BigDecimal notaxRealAmountTotal;

    /**
     * 税额合计
     */
    @ApiModelProperty(value = "税额合计")
    private BigDecimal taxAmountTotal;

    private List<ResponseDistrInReturnVO> distrInVOS = new ArrayList<>();

    public BigDecimal getRealAmountTotal() {
        return realAmountTotal;
    }

    public void setRealAmountTotal(BigDecimal realAmountTotal) {
        this.realAmountTotal = realAmountTotal;
    }

    public BigDecimal getNotaxRealAmountTotal() {
        return notaxRealAmountTotal;
    }

    public void setNotaxRealAmountTotal(BigDecimal notaxRealAmountTotal) {
        this.notaxRealAmountTotal = notaxRealAmountTotal;
    }

    public BigDecimal getTaxAmountTotal() {
        return taxAmountTotal;
    }

    public void setTaxAmountTotal(BigDecimal taxAmountTotal) {
        this.taxAmountTotal = taxAmountTotal;
    }

    public List<ResponseDistrInReturnVO> getDistrInVOS() {
        return distrInVOS;
    }

    public void setDistrInVOS(List<ResponseDistrInReturnVO> distrInVOS) {
        this.distrInVOS = distrInVOS;
    }
}