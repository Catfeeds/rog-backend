package com.rograndec.feijiayun.chain.business.retail.royalty.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * saas_sale_royalty_detail
 * 
 * 
 * @author zhaiwei
 * 
 * 2017-09-23
 */
@ApiModel
public class SaveOrUpdateSaleRoyaltyDetailVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;


    /**
     * 基础单据明细ID
     */
    @ApiModelProperty(value = "基础单据明细ID" ,required = true)
    @NotNull(message = "基础单据明细ID不能为空")
    private Long saleDtlId;

    /**
     * 基础单据ID
     */
    @ApiModelProperty(value = "基础单据ID" ,required = true)
    @NotNull(message = "基础单据ID不能为空")
    private Long saleId;

    /**
     * 提成策略
     */
    @ApiModelProperty(value = "提成策略" ,required = true)
    @NotNull(message = "提成策略不能为空")
    private String royaltyStrategy;

    /**
     * 提成金额
     */
    @ApiModelProperty(value = "提成金额" ,required = true)
    @NotNull(message = "提成金额不能为空")
    private BigDecimal royaltyAmount;

    @ApiModelProperty(value = "成本总额" ,required = true)
    @NotNull(message = "成本总额不能为空")
    private BigDecimal costAmount;

    /**
     * 利润合计
     */
    @ApiModelProperty(value = "利润合计" ,required = true)
    @NotNull(message = "利润合计不能为空")
    private BigDecimal profit;

    /**
     * 利润率
     */
    @ApiModelProperty(value = "利润率" ,required = true)
    @NotNull(message = "利润率不能为空")
    private BigDecimal profitRate;

    public static List<Long> getSaleDtlIds(List<SaveOrUpdateSaleRoyaltyDetailVO> saveOrUpdateSaleRoyaltyDetailVOS){

        List<Long> ids = new ArrayList<>();

        for(SaveOrUpdateSaleRoyaltyDetailVO vo : saveOrUpdateSaleRoyaltyDetailVOS){
            ids.add(vo.getSaleDtlId());
        }

        return ids;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSaleDtlId() {
        return saleDtlId;
    }

    public void setSaleDtlId(Long saleDtlId) {
        this.saleDtlId = saleDtlId;
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public String getRoyaltyStrategy() {
        return royaltyStrategy;
    }

    public void setRoyaltyStrategy(String royaltyStrategy) {
        this.royaltyStrategy = royaltyStrategy;
    }

    public BigDecimal getRoyaltyAmount() {
        return royaltyAmount;
    }

    public void setRoyaltyAmount(BigDecimal royaltyAmount) {
        this.royaltyAmount = royaltyAmount;
    }

    public BigDecimal getCostAmount() {
        return costAmount;
    }

    public void setCostAmount(BigDecimal costAmount) {
        this.costAmount = costAmount;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public BigDecimal getProfitRate() {
        return profitRate;
    }

    public void setProfitRate(BigDecimal profitRate) {
        this.profitRate = profitRate;
    }
}