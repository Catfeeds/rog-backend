package com.rograndec.feijiayun.chain.business.retail.royalty.vo;

import com.rograndec.feijiayun.chain.business.retail.royalty.entity.SaleRoyaltyTotal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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
public class ResponseSaleRoyaltyTotalVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 提成管理ID
     */
    @ApiModelProperty(value = "提成管理ID")
    private Long royaltyId;

    /**
     * 营业员ID
     */
    @ApiModelProperty(value = "营业员ID")
    private Long clerkId;

    /**
     * 营业员编码
     */
    @ApiModelProperty(value = "营业员编码")
    private String clerkCode;

    /**
     * 营业员名称
     */
    @ApiModelProperty(value = "营业员名称")
    private String clerkName;

    /**
     * 销售金额
     */
    @ApiModelProperty(value = "销售金额")
    private BigDecimal saleAmount;

    /**
     * 成本金额
     */
    @ApiModelProperty(value = "成本金额")
    private BigDecimal costAmount;

    /**
     * 利润金额
     */
    @ApiModelProperty(value = "利润金额")
    private BigDecimal profit;

    /**
     * 应提金额
     */
    @ApiModelProperty(value = "应提金额")
    private BigDecimal royaltyAmount;

    /**
     * 实提金额
     */
    @ApiModelProperty(value = "实提金额")
    private BigDecimal realRoyaltyAmount;

    /**
     * 提成差额
     */
    @ApiModelProperty(value = "提成差额")
    private BigDecimal diffRoyaltyAmount;

    /**
     * 状态（0-禁用；1-启用）
     */
    @ApiModelProperty(value = "状态（0-禁用；1-启用）")
    private Integer status;

    @ApiModelProperty(value = "提成明细集合")
    private List<ResponseSaleRoyaltyDetailVO> responseSaleRoyaltyDetailVOList = new ArrayList<>();





    public static List<ResponseSaleRoyaltyTotalVO> getResponseSaleRoyaltyTotalVOs(
            List<SaleRoyaltyTotal> saleRoyaltyTotals
    ){

        List<ResponseSaleRoyaltyTotalVO> list = new ArrayList<>();

        for(SaleRoyaltyTotal saleRoyaltyTotal : saleRoyaltyTotals){
            list.add(getResponseSaleRoyaltyTotalVO(saleRoyaltyTotal));
        }

        return list;
    }

    public static ResponseSaleRoyaltyTotalVO getResponseSaleRoyaltyTotalVO(SaleRoyaltyTotal saleRoyaltyTotal){

        ResponseSaleRoyaltyTotalVO saleRoyaltyTotalVO = new ResponseSaleRoyaltyTotalVO();

        /**
         * 主键ID
         */
        saleRoyaltyTotalVO.setId(saleRoyaltyTotal.getId());

        /**
         * 提成管理ID
         */
        saleRoyaltyTotalVO.setRoyaltyId(saleRoyaltyTotal.getRoyaltyId());

        /**
         * 营业员ID
         */
        saleRoyaltyTotalVO.setClerkId(saleRoyaltyTotal.getClerkId());

        /**
         * 营业员编码
         */
        saleRoyaltyTotalVO.setClerkCode(saleRoyaltyTotal.getClerkCode());

        /**
         * 营业员名称
         */
        saleRoyaltyTotalVO.setClerkName(saleRoyaltyTotal.getClerkName());

        /**
         * 销售金额
         */
        saleRoyaltyTotalVO.setSaleAmount(saleRoyaltyTotal.getSaleAmount());

        /**
         * 成本金额
         */
        saleRoyaltyTotalVO.setCostAmount(saleRoyaltyTotal.getCostAmount());

        /**
         * 利润金额
         */
        saleRoyaltyTotalVO.setProfit(saleRoyaltyTotal.getProfit());

        /**
         * 应提金额
         */
        saleRoyaltyTotalVO.setRoyaltyAmount(saleRoyaltyTotal.getRoyaltyAmount());

        /**
         * 实提金额
         */
        saleRoyaltyTotalVO.setRealRoyaltyAmount(saleRoyaltyTotal.getRealRoyaltyAmount());

        /**
         * 提成差额
         */
        saleRoyaltyTotalVO.setDiffRoyaltyAmount(saleRoyaltyTotal.getDiffRoyaltyAmount());

        /**
         * 状态（0-禁用；1-启用）
         */
        saleRoyaltyTotalVO.setStatus(saleRoyaltyTotal.getStatus());

        return saleRoyaltyTotalVO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getRoyaltyId() {
        return royaltyId;
    }

    public void setRoyaltyId(Long royaltyId) {
        this.royaltyId = royaltyId;
    }

    public Long getClerkId() {
        return clerkId;
    }

    public void setClerkId(Long clerkId) {
        this.clerkId = clerkId;
    }

    public String getClerkCode() {
        return clerkCode;
    }

    public void setClerkCode(String clerkCode) {
        this.clerkCode = clerkCode;
    }

    public String getClerkName() {
        return clerkName;
    }

    public void setClerkName(String clerkName) {
        this.clerkName = clerkName;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<ResponseSaleRoyaltyDetailVO> getResponseSaleRoyaltyDetailVOList() {
        return responseSaleRoyaltyDetailVOList;
    }

    public void setResponseSaleRoyaltyDetailVOList(List<ResponseSaleRoyaltyDetailVO> responseSaleRoyaltyDetailVOList) {
        this.responseSaleRoyaltyDetailVOList = responseSaleRoyaltyDetailVOList;
    }


}