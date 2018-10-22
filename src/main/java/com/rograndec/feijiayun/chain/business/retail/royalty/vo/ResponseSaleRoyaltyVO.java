package com.rograndec.feijiayun.chain.business.retail.royalty.vo;

import com.rograndec.feijiayun.chain.business.retail.royalty.entity.SaleRoyalty;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * saas_sale_royalty
 * 
 * 
 * @author zhaiwei
 * 
 * 2017-09-23
 */
@ApiModel
public class ResponseSaleRoyaltyVO implements Serializable {
    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    private Long id;

    /**
     * 提成日期
     */
    @ApiModelProperty(value = "提成日期")
    private Date royaltyDate;

    /**
     * 提成管理单号
     */
    @ApiModelProperty(value = "提成管理单号")
    private String code;

    /**
     * 提成人员ID
     */
    @ApiModelProperty(value = "提成人员ID")
    private Long royaltyManId;

    /**
     * 提成人员名称
     */
    @ApiModelProperty(value = "提成人员名称")
    private String royaltyManName;

    @ApiModelProperty(value = "门店id")
    private Long enterpriseId;

    @ApiModelProperty(value = "门店code")
    private String enterpriseCode;

    @ApiModelProperty(value = "门店名称")
    private String enterpriseName;

    /**
     * 销售日期从
     */
    @ApiModelProperty(value = "销售日期从")
    private Date saleDateFrom;

    /**
     * 销售日期至
     */
    @ApiModelProperty(value = "销售日期至")
    private Date saleDateTo;


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


    @ApiModelProperty(value = "提成人员,提成信息汇总集合")
    private List<ResponseSaleRoyaltyTotalVO> responseSaleRoyaltyTotalVOS = new ArrayList<>();


    /**
     * 销售金额
     */
    @ApiModelProperty(value = "销售金额")
    private BigDecimal saleAmountTotal;

    /**
     * 成本金额
     */
    @ApiModelProperty(value = "成本金额")
    private BigDecimal costAmountTotal;

    /**
     * 利润金额
     */
    @ApiModelProperty(value = "利润金额")
    private BigDecimal profitTotal;

    /**
     * 应提金额
     */
    @ApiModelProperty(value = "应提金额")
    private BigDecimal royaltyAmountTotal;

    /**
     * 实提金额
     */
    @ApiModelProperty(value = "实提金额")
    private BigDecimal realRoyaltyAmountTotal;

    /**
     * 提成差额
     */
    @ApiModelProperty(value = "提成差额")
    private BigDecimal diffRoyaltyAmountTotal;


    public static void setTotalAmount(List<ResponseSaleRoyaltyTotalVO> responseSaleRoyaltyTotalVOS
    ,ResponseSaleRoyaltyVO responseSaleRoyaltyVO){

        /**
         * 销售金额
         */
        BigDecimal saleAmountTotal = new BigDecimal(0);

        /**
         * 成本金额
         */
        BigDecimal costAmountTotal = new BigDecimal(0);

        /**
         * 利润金额
         */
        BigDecimal profitTotal = new BigDecimal(0);

        /**
         * 应提金额
         */
        BigDecimal royaltyAmountTotal = new BigDecimal(0);

        /**
         * 实提金额
         */
        BigDecimal realRoyaltyAmountTotal = new BigDecimal(0);

        /**
         * 提成差额
         */
        BigDecimal diffRoyaltyAmountTotal = new BigDecimal(0);

        for(ResponseSaleRoyaltyTotalVO vo : responseSaleRoyaltyTotalVOS){
            saleAmountTotal = saleAmountTotal.add(vo.getSaleAmount()).setScale(2,BigDecimal.ROUND_HALF_UP);
            costAmountTotal = costAmountTotal.add(vo.getCostAmount()).setScale(2,BigDecimal.ROUND_HALF_UP);
            profitTotal = profitTotal.add(vo.getProfit()).setScale(2,BigDecimal.ROUND_HALF_UP);
            royaltyAmountTotal = royaltyAmountTotal.add(vo.getRoyaltyAmount()).setScale(2,BigDecimal.ROUND_HALF_UP);
            realRoyaltyAmountTotal = realRoyaltyAmountTotal.add(vo.getRoyaltyAmount()).setScale(2,BigDecimal.ROUND_HALF_UP);
            diffRoyaltyAmountTotal = diffRoyaltyAmountTotal.add(vo.getDiffRoyaltyAmount()).setScale(2,BigDecimal.ROUND_HALF_UP);
        }

        responseSaleRoyaltyVO.setSaleAmountTotal(saleAmountTotal);
        responseSaleRoyaltyVO.setCostAmountTotal(costAmountTotal);
        responseSaleRoyaltyVO.setProfitTotal(profitTotal);
        responseSaleRoyaltyVO.setRoyaltyAmountTotal(royaltyAmountTotal);
        responseSaleRoyaltyVO.setRealRoyaltyAmountTotal(realRoyaltyAmountTotal);
        responseSaleRoyaltyVO.setDiffRoyaltyAmountTotal(diffRoyaltyAmountTotal);
    }

    public static ResponseSaleRoyaltyVO getResponseSaleRoyaltyVO(Enterprise enterprise ,SaleRoyalty saleRoyalty){

        ResponseSaleRoyaltyVO responseSaleRoyaltyVO = new ResponseSaleRoyaltyVO();
        /**
         * 主键id
         */
        responseSaleRoyaltyVO.setId(saleRoyalty.getId());

        /**
         * 提成日期
         */
        responseSaleRoyaltyVO.setRoyaltyDate(saleRoyalty.getRoyaltyDate());

        /**
         * 提成管理单号
         */
        responseSaleRoyaltyVO.setCode(saleRoyalty.getCode());

        /**
         * 提成人员ID
         */
        responseSaleRoyaltyVO.setRoyaltyManId(saleRoyalty.getRoyaltyManId());

        /**
         * 提成人员名称
         */
        responseSaleRoyaltyVO.setRoyaltyManName(saleRoyalty.getRoyaltyManName());

        /**
         * 门店id
         */
        responseSaleRoyaltyVO.setEnterpriseId(saleRoyalty.getEnterpriseId());

        /**
         * 门店code
         */
        responseSaleRoyaltyVO.setEnterpriseCode(enterprise.getCode());

        /**
         * 门店名称
         */
        responseSaleRoyaltyVO.setEnterpriseName(enterprise.getName());

        /**
         * 销售日期从
         */
        responseSaleRoyaltyVO.setSaleDateFrom(saleRoyalty.getSaleDateFrom());

        /**
         * 销售日期至
         */
        responseSaleRoyaltyVO.setSaleDateTo(saleRoyalty.getSaleDateTo());

        return responseSaleRoyaltyVO;

    }

    public static void setAmount(
            List<ResponseSaleRoyaltyTotalVO> responseSaleRoyaltyTotalVOS
            ,ResponseSaleRoyaltyVO responseSaleRoyaltyVO
    ){
        /**
         * 应提金额
         */
        BigDecimal royaltyAmount = new BigDecimal(0);

        /**
         * 实提金额
         */
        BigDecimal realRoyaltyAmount = new BigDecimal(0);

        /**
         * 提成差额
         */
        BigDecimal diffRoyaltyAmount = new BigDecimal(0);

        for(ResponseSaleRoyaltyTotalVO vo : responseSaleRoyaltyTotalVOS){
            royaltyAmount = royaltyAmount.add(vo.getRoyaltyAmount()).setScale(2,BigDecimal.ROUND_HALF_UP);
            realRoyaltyAmount = realRoyaltyAmount.add(vo.getRealRoyaltyAmount()).setScale(2,BigDecimal.ROUND_HALF_UP);
            diffRoyaltyAmount = diffRoyaltyAmount.add(vo.getDiffRoyaltyAmount()).setScale(2,BigDecimal.ROUND_HALF_UP);
        }

        responseSaleRoyaltyVO.setRoyaltyAmount(royaltyAmount);
        responseSaleRoyaltyVO.setRealRoyaltyAmount(realRoyaltyAmount);
        responseSaleRoyaltyVO.setDiffRoyaltyAmount(diffRoyaltyAmount);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getRoyaltyDate() {
        return royaltyDate;
    }

    public void setRoyaltyDate(Date royaltyDate) {
        this.royaltyDate = royaltyDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getRoyaltyManId() {
        return royaltyManId;
    }

    public void setRoyaltyManId(Long royaltyManId) {
        this.royaltyManId = royaltyManId;
    }

    public Date getSaleDateFrom() {
        return saleDateFrom;
    }

    public void setSaleDateFrom(Date saleDateFrom) {
        this.saleDateFrom = saleDateFrom;
    }

    public Date getSaleDateTo() {
        return saleDateTo;
    }

    public void setSaleDateTo(Date saleDateTo) {
        this.saleDateTo = saleDateTo;
    }

    public String getRoyaltyManName() {
        return royaltyManName;
    }

    public void setRoyaltyManName(String royaltyManName) {
        this.royaltyManName = royaltyManName;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public List<ResponseSaleRoyaltyTotalVO> getResponseSaleRoyaltyTotalVOS() {
        return responseSaleRoyaltyTotalVOS;
    }

    public void setResponseSaleRoyaltyTotalVOS(List<ResponseSaleRoyaltyTotalVO> responseSaleRoyaltyTotalVOS) {
        this.responseSaleRoyaltyTotalVOS = responseSaleRoyaltyTotalVOS;
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

    public BigDecimal getSaleAmountTotal() {
        return saleAmountTotal;
    }

    public void setSaleAmountTotal(BigDecimal saleAmountTotal) {
        this.saleAmountTotal = saleAmountTotal;
    }

    public BigDecimal getCostAmountTotal() {
        return costAmountTotal;
    }

    public void setCostAmountTotal(BigDecimal costAmountTotal) {
        this.costAmountTotal = costAmountTotal;
    }

    public BigDecimal getProfitTotal() {
        return profitTotal;
    }

    public void setProfitTotal(BigDecimal profitTotal) {
        this.profitTotal = profitTotal;
    }

    public BigDecimal getRoyaltyAmountTotal() {
        return royaltyAmountTotal;
    }

    public void setRoyaltyAmountTotal(BigDecimal royaltyAmountTotal) {
        this.royaltyAmountTotal = royaltyAmountTotal;
    }

    public BigDecimal getRealRoyaltyAmountTotal() {
        return realRoyaltyAmountTotal;
    }

    public void setRealRoyaltyAmountTotal(BigDecimal realRoyaltyAmountTotal) {
        this.realRoyaltyAmountTotal = realRoyaltyAmountTotal;
    }

    public BigDecimal getDiffRoyaltyAmountTotal() {
        return diffRoyaltyAmountTotal;
    }

    public void setDiffRoyaltyAmountTotal(BigDecimal diffRoyaltyAmountTotal) {
        this.diffRoyaltyAmountTotal = diffRoyaltyAmountTotal;
    }
}