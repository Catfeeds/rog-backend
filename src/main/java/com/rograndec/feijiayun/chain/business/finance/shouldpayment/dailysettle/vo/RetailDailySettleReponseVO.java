package com.rograndec.feijiayun.chain.business.finance.shouldpayment.dailysettle.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class RetailDailySettleReponseVO implements Serializable{

    private static final long serialVersionUID = -103107809307874525L;

    @ApiModelProperty(value = "销售笔数总计")
    private Integer salePensTotal = 0;

    @ApiModelProperty(value = "销售金额合计")
    private BigDecimal saleAmountTotal = BigDecimal.ZERO;

    @ApiModelProperty(value = "退货笔数合计")
    private Integer returnPensTotal = 0;

    @ApiModelProperty(value = "退货金额合计")
    private BigDecimal returnAmountTotal = BigDecimal.ZERO;

    @ApiModelProperty(value = "应收金额合计")
    private BigDecimal amountTotal = BigDecimal.ZERO;

    @ApiModelProperty(value = "日结单据集合")
    private List<RetailDailySettleVO> dailySettleVOS;

    public Integer getSalePensTotal() {
        return salePensTotal;
    }

    public void setSalePensTotal(Integer salePensTotal) {
        this.salePensTotal = salePensTotal;
    }

    public BigDecimal getSaleAmountTotal() {
        return saleAmountTotal;
    }

    public void setSaleAmountTotal(BigDecimal saleAmountTotal) {
        this.saleAmountTotal = saleAmountTotal;
    }

    public Integer getReturnPensTotal() {
        return returnPensTotal;
    }

    public void setReturnPensTotal(Integer returnPensTotal) {
        this.returnPensTotal = returnPensTotal;
    }

    public BigDecimal getReturnAmountTotal() {
        return returnAmountTotal;
    }

    public void setReturnAmountTotal(BigDecimal returnAmountTotal) {
        this.returnAmountTotal = returnAmountTotal;
    }

    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    public List<RetailDailySettleVO> getDailySettleVOS() {
        return dailySettleVOS;
    }

    public void setDailySettleVOS(List<RetailDailySettleVO> dailySettleVOS) {
        this.dailySettleVOS = dailySettleVOS;
    }
}
