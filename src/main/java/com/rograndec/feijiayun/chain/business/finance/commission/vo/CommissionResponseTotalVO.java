package com.rograndec.feijiayun.chain.business.finance.commission.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 
 * saas_sale_royalty_total
 * 
 * 
 * @author zhaiwei
 * 
 * 2017-09-23
 */
@ApiModel
public class CommissionResponseTotalVO implements Serializable {


    /**
     * 应提金额合计
     */
    @ApiModelProperty(value = "应提金额合计")
    private BigDecimal amountTotal = BigDecimal.ZERO;


    /**
     * 实提金额合计
     */
    @ApiModelProperty(value = "实提金额合计")
    private BigDecimal realAmountTotal = BigDecimal.ZERO;

    /**
     * 差异金额合计
     */
    @ApiModelProperty(value = "差异金额合计")
    private BigDecimal diffAmountTotal = BigDecimal.ZERO;

    @ApiModelProperty(value = "列表集合")
    private List<NoSaleCommissionResponseTotalVO> totalVOS = Collections.emptyList();


    public static void setTotal(CommissionResponseTotalVO commissionResponseTotalVO,
                                                                            List<NoSaleCommissionResponseTotalVO> detailVOS
    ) throws ParseException {


        /**
         * 应提金额
         */
        BigDecimal amountTotal = detailVOS.stream().filter(Objects::nonNull)
                .filter(c -> null != c.getAmountTotal()).map(NoSaleCommissionResponseTotalVO::getAmountTotal).reduce(BigDecimal.ZERO,BigDecimal::add);
        commissionResponseTotalVO.setAmountTotal(amountTotal);


        BigDecimal royaltyAmount = detailVOS.stream().filter(Objects::nonNull)
                .filter(c -> null != c.getRealAmountTotal()).map(NoSaleCommissionResponseTotalVO::getRealAmountTotal).reduce(BigDecimal.ZERO,BigDecimal::add);

        commissionResponseTotalVO.setRealAmountTotal(royaltyAmount);

        BigDecimal diffAmountTotal = detailVOS.stream().filter(Objects::nonNull)
                .filter(c -> null != c.getDiffAmountTotal()).map(NoSaleCommissionResponseTotalVO::getDiffAmountTotal).reduce(BigDecimal.ZERO,BigDecimal::add);
        commissionResponseTotalVO.setDiffAmountTotal(diffAmountTotal);

    }


    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    public BigDecimal getRealAmountTotal() {
        return realAmountTotal;
    }

    public void setRealAmountTotal(BigDecimal realAmountTotal) {
        this.realAmountTotal = realAmountTotal;
    }

    public BigDecimal getDiffAmountTotal() {
        return diffAmountTotal;
    }

    public void setDiffAmountTotal(BigDecimal diffAmountTotal) {
        this.diffAmountTotal = diffAmountTotal;
    }

    public List<NoSaleCommissionResponseTotalVO> getTotalVOS() {
        return totalVOS;
    }

    public void setTotalVOS(List<NoSaleCommissionResponseTotalVO> totalVOS) {
        this.totalVOS = totalVOS;
    }
}