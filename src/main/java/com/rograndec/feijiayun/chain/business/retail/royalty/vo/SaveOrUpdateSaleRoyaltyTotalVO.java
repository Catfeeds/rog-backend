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
 * saas_sale_royalty_total
 * 
 * 
 * @author zhaiwei
 * 
 * 2017-09-23
 */
@ApiModel
public class SaveOrUpdateSaleRoyaltyTotalVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;


    /**
     * 营业员ID
     */
    @ApiModelProperty(value = "营业员ID" ,required = true)
    @NotNull(message = "营业员ID不能为空")
    private Long clerkId;


    /**
     * 销售金额
     */
    @ApiModelProperty(value = "销售金额" ,required = true)
    @NotNull(message = "销售金额不能为空")
    private BigDecimal saleAmount;

    /**
     * 成本金额
     */
    @ApiModelProperty(value = "成本金额" ,required = true)
    @NotNull(message = "成本金额不能为空")
    private BigDecimal costAmount;

    /**
     * 利润金额
     */
    @ApiModelProperty(value = "利润金额" ,required = true)
    @NotNull(message = "利润金额不能为空")
    private BigDecimal profit;

    /**
     * 应提金额
     */
    @ApiModelProperty(value = "应提金额" ,required = true)
    @NotNull(message = "应提金额不能为空")
    private BigDecimal royaltyAmount;

    /**
     * 实提金额
     */
    @ApiModelProperty(value = "实提金额" ,required = true)
    @NotNull(message = "实提金额不能为空")
    private BigDecimal realRoyaltyAmoun;

    @ApiModelProperty(value = "人员提成明细集合")
    private List<SaveOrUpdateSaleRoyaltyDetailVO> saveOrUpdateSaleRoyaltyDetailVOS;

    public static List<Long> getClerkIds(List<SaveOrUpdateSaleRoyaltyTotalVO> totalVOs){
        List<Long> ids = new ArrayList<>();

        for(SaveOrUpdateSaleRoyaltyTotalVO vo : totalVOs){
            ids.add(vo.getClerkId());
        }

        return ids;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClerkId() {
        return clerkId;
    }

    public void setClerkId(Long clerkId) {
        this.clerkId = clerkId;
    }

    public BigDecimal getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(BigDecimal saleAmount) {
        this.saleAmount = saleAmount;
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

    public BigDecimal getRoyaltyAmount() {
        return royaltyAmount;
    }

    public void setRoyaltyAmount(BigDecimal royaltyAmount) {
        this.royaltyAmount = royaltyAmount;
    }

    public BigDecimal getRealRoyaltyAmoun() {
        return realRoyaltyAmoun;
    }

    public void setRealRoyaltyAmoun(BigDecimal realRoyaltyAmoun) {
        this.realRoyaltyAmoun = realRoyaltyAmoun;
    }

    public List<SaveOrUpdateSaleRoyaltyDetailVO> getSaveOrUpdateSaleRoyaltyDetailVOS() {
        return saveOrUpdateSaleRoyaltyDetailVOS;
    }

    public void setSaveOrUpdateSaleRoyaltyDetailVOS(List<SaveOrUpdateSaleRoyaltyDetailVO> saveOrUpdateSaleRoyaltyDetailVOS) {
        this.saveOrUpdateSaleRoyaltyDetailVOS = saveOrUpdateSaleRoyaltyDetailVOS;
    }
}