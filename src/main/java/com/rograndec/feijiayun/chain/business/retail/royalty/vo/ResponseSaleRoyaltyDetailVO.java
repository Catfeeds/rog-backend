package com.rograndec.feijiayun.chain.business.retail.royalty.vo;

import com.rograndec.feijiayun.chain.business.retail.royalty.entity.SaleRoyaltyDetail;
import com.rograndec.feijiayun.chain.business.retail.royalty.vo.transfer.DetailVO;
import com.rograndec.feijiayun.chain.business.retail.saleflow.entity.Sale;
import com.rograndec.feijiayun.chain.business.retail.saleflow.entity.SaleDetail;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
public class ResponseSaleRoyaltyDetailVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 企业ID
     */
    @ApiModelProperty(value = "企业ID")
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
    @ApiModelProperty(value = "上级企业ID")
    private Long parentId;

    /**
     * 提成管理ID
     */
    @ApiModelProperty(value = "提成管理ID")
    private Long royaltyTotalId;

    /**
     * 提成ID
     */
    @ApiModelProperty(value = "提成ID")
    private Long royaltyId;

    /**
     * 基础单据明细ID
     */
    @ApiModelProperty(value = "销售单据明细ID")
    private Long saleDtlId;

    /**
     * 基础单据ID
     */
    @ApiModelProperty(value = "销售单据ID")
    private Long saleId;

    /**
     * 销售模式（0-常规；1-中药）
     */
    @ApiModelProperty(value = "销售模式（0-常规；1-中药）")
    private Integer saleMode;

    /**
     * 销售类型（0-销售；1-销退）
     */
    @ApiModelProperty(value = "销售类型（0-销售；1-销退）")
    private Integer saleType;

    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID")
    private Long goodsId;

    /**
     * 商品编码
     */
    @ApiModelProperty(value = "商品编码")
    private String goodsCode;
    /**
     * 商品通用名称
     */
    @ApiModelProperty(value = "商品通用名称")
    private String goodsGenericName;
    /**
     * 剂型ID
     */
    @ApiModelProperty(value = "剂型ID")
    private Long dosageId;

    /**
     * 剂型名称
     */
    @ApiModelProperty(value = "剂型名称")
    private String dosageName;

    /**
     * 单位ID
     */
    @ApiModelProperty(value = "单位ID")
    private Long unitId;

    /**
     * 单位名称
     */
    @ApiModelProperty(value = "单位名称")
    private String unitName;

    /**
     * 商品规格
     */
    @ApiModelProperty(value = "商品规格")
    private String goodsSpecification;

    /**
     * 生产厂商ID
     */
    @ApiModelProperty(value = "生产厂商ID")
    private Long manufacturerId;

    /**
     * 生产厂商
     */
    @ApiModelProperty(value = "生产厂商")
    private String manufacturer;

    /**
     * 基础单据编码
     */
    @ApiModelProperty(value = "销售单据编码")
    private String saleCode;

    /**
     * 基础单据日期
     */
    @ApiModelProperty(value = "销售单据日期")
    private Date saleDate;

    /**
     * 总数量
     */
    @ApiModelProperty(value = "总数量")
    private BigDecimal quantity;

    /**
     * 单价
     */
    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;

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
     * 提成策略
     */
    @ApiModelProperty(value = "提成策略")
    private String royaltyStrategy;

    /**
     * 提成金额
     */
    @ApiModelProperty(value = "提成金额")
    private BigDecimal royaltyAmount;

    /**
     * 金额（整单优惠前金额）
     */
    @ApiModelProperty(value = "销售总额 金额（整单优惠前金额）")
    private BigDecimal realAmount;

    @ApiModelProperty(value = "成本总额")
    private BigDecimal costAmount;

    /**
     * 利润合计
     */
    @ApiModelProperty(value = "利润合计")
    private BigDecimal profit;

    /**
     * 利润率
     */
    @ApiModelProperty(value = "利润率")
    private BigDecimal profitRate;


//    /**
//     * 策率ID
//     */
//    @ApiModelProperty(value = "策率ID")
//    private Long strategyId;

    /**
     * 策略名称
     */
    @ApiModelProperty(value = "策略名称")
    private String stragegyName;
    /**
     * 状态（0-禁用；1-启用）
     */
    @ApiModelProperty(value = "状态（0-禁用；1-启用）")
    private Integer status;

    public static List<ResponseSaleRoyaltyDetailVO> getResponseSaleRoyaltyDetailVOs(
            List<Sale> sales
            , List<SaleDetail> saleDetails
            , List<SaleRoyaltyDetail> saleRoyaltyDetails
    ){
        List<ResponseSaleRoyaltyDetailVO> saleRoyaltyDetailVOS = new ArrayList<>();
        for(SaleRoyaltyDetail saleRoyaltyDetail : saleRoyaltyDetails){
            DetailVO detailVO = new DetailVO();
            for(Sale sale : sales){
                if(sale.getId().equals(saleRoyaltyDetail.getBaseOrderId())){
                    detailVO.setSale(sale);
                }
            }
            for(SaleDetail saleDetail : saleDetails){
                if(saleDetail.getId().equals(saleRoyaltyDetail.getBaseOrderDtlId())){
                    detailVO.setSaleDetail(saleDetail);
                }
            }
            detailVO.setSaleRoyaltyDetail(saleRoyaltyDetail);

            ResponseSaleRoyaltyDetailVO responseSaleRoyaltyDetailVO = getResponseSaleRoyaltyDetailVO(detailVO);
            saleRoyaltyDetailVOS.add(responseSaleRoyaltyDetailVO);
        }

        return saleRoyaltyDetailVOS;
    }

    public static ResponseSaleRoyaltyDetailVO getResponseSaleRoyaltyDetailVO(
            DetailVO detailVO
    ){

        ResponseSaleRoyaltyDetailVO saleRoyaltyDetailVO = new ResponseSaleRoyaltyDetailVO();

        /**
         * 主键ID
         */
        saleRoyaltyDetailVO.setId(detailVO.getSaleRoyaltyDetail().getId());

        /**
         * 企业ID
         */
        saleRoyaltyDetailVO.setEnterpriseId(detailVO.getSaleRoyaltyDetail().getEnterpriseId());

        /**
         * 上级企业ID
         */
        saleRoyaltyDetailVO.setParentId(detailVO.getSaleRoyaltyDetail().getParentId());

        /**
         * 提成管理ID
         */
        saleRoyaltyDetailVO.setRoyaltyTotalId(detailVO.getSaleRoyaltyDetail().getRoyaltyTotalId());

        /**
         * 提成ID
         */
        saleRoyaltyDetailVO.setRoyaltyId(detailVO.getSaleRoyaltyDetail().getRoyaltyId());

        /**
         * 基础单据明细ID
         */
        saleRoyaltyDetailVO.setSaleDtlId(detailVO.getSaleRoyaltyDetail().getBaseOrderDtlId());

        /**
         * 基础单据ID
         */
        saleRoyaltyDetailVO.setSaleId(detailVO.getSaleRoyaltyDetail().getBaseOrderId());

        /**
         * 销售模式（0-常规；1-中药）
         */
        saleRoyaltyDetailVO.setSaleMode(detailVO.getSale().getSaleMode());

        /**
         * 销售类型（0-销售；1-销退）
         */
        saleRoyaltyDetailVO.setSaleType(detailVO.getSale().getSaleType());

        /**
         * 商品ID
         */
        saleRoyaltyDetailVO.setGoodsId(detailVO.getSaleDetail().getGoodsId());

        /**
         * 商品编码
         */
        saleRoyaltyDetailVO.setGoodsCode(detailVO.getSaleDetail().getGoodsCode());
        /**
         * 商品通用名称
         */
        saleRoyaltyDetailVO.setGoodsGenericName(detailVO.getSaleDetail().getGoodsGenericName());
        /**
         * 剂型ID
         */
        saleRoyaltyDetailVO.setDosageId(detailVO.getSaleDetail().getDosageId());

        /**
         * 剂型名称
         */
        saleRoyaltyDetailVO.setDosageName(detailVO.getSaleDetail().getDosageName());

        /**
         * 单位ID
         */
        saleRoyaltyDetailVO.setUnitId(detailVO.getSaleDetail().getUnitId());

        /**
         * 单位名称
         */
        saleRoyaltyDetailVO.setUnitName(detailVO.getSaleDetail().getUnitName());

        /**
         * 商品规格
         */
        saleRoyaltyDetailVO.setGoodsSpecification(detailVO.getSaleDetail().getGoodsSpecification());

        /**
         * 生产厂商ID
         */
        saleRoyaltyDetailVO.setManufacturerId(detailVO.getSaleDetail().getManufacturerId());

        /**
         * 生产厂商
         */
        saleRoyaltyDetailVO.setManufacturer(detailVO.getSaleDetail().getManufacturer());

        /**
         * 基础单据编码
         */
        saleRoyaltyDetailVO.setSaleCode(detailVO.getSaleRoyaltyDetail().getBaseOrderCode());

        /**
         * 基础单据日期
         */
        saleRoyaltyDetailVO.setSaleDate(detailVO.getSaleRoyaltyDetail().getBaseOrderDate());

        /**
         * 总数量
         */
        saleRoyaltyDetailVO.setQuantity(detailVO.getSaleDetail().getQuantity());

        /**
         * 单价
         */
        saleRoyaltyDetailVO.setUnitPrice(detailVO.getSaleDetail().getUnitPrice());

        /**
         * 营业员ID
         */
        saleRoyaltyDetailVO.setClerkId(detailVO.getSaleRoyaltyDetail().getClerkId());

        /**
         * 营业员编码
         */
        saleRoyaltyDetailVO.setClerkCode(detailVO.getSaleRoyaltyDetail().getClerkCode());

        /**
         * 营业员名称
         */
        saleRoyaltyDetailVO.setClerkName(detailVO.getSaleRoyaltyDetail().getClerkName());

        /**
         * 提成策略
         */
        saleRoyaltyDetailVO.setRoyaltyStrategy(detailVO.getSaleRoyaltyDetail().getRoyaltyStrategy());

        /**
         * 提成金额
         */
        saleRoyaltyDetailVO.setRoyaltyAmount(detailVO.getSaleRoyaltyDetail().getRoyaltyAmount());

        /**
         * 金额（整单优惠前金额）
         */
        saleRoyaltyDetailVO.setRealAmount(detailVO.getSaleDetail().getRealAmount());

        /**
         * 成本总额
         */
        saleRoyaltyDetailVO.setCostAmount(detailVO.getSaleRoyaltyDetail().getCostAmount());

        /**
         * 利润合计
         */
        saleRoyaltyDetailVO.setProfit(detailVO.getSaleRoyaltyDetail().getProfit());

        /**
         * 利润率
         */
        saleRoyaltyDetailVO.setProfitRate(detailVO.getSaleRoyaltyDetail().getProfitRate());

        /**
         * 策略名称
         */
        saleRoyaltyDetailVO.setStragegyName(detailVO.getSaleRoyaltyDetail().getRoyaltyStrategy());
        /**
         * 状态（0-禁用；1-启用）
         */
        saleRoyaltyDetailVO.setStatus(detailVO.getSaleRoyaltyDetail().getStatus());

        return saleRoyaltyDetailVO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getRoyaltyTotalId() {
        return royaltyTotalId;
    }

    public void setRoyaltyTotalId(Long royaltyTotalId) {
        this.royaltyTotalId = royaltyTotalId;
    }

    public Long getRoyaltyId() {
        return royaltyId;
    }

    public void setRoyaltyId(Long royaltyId) {
        this.royaltyId = royaltyId;
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

    public Integer getSaleMode() {
        return saleMode;
    }

    public void setSaleMode(Integer saleMode) {
        this.saleMode = saleMode;
    }

    public Integer getSaleType() {
        return saleType;
    }

    public void setSaleType(Integer saleType) {
        this.saleType = saleType;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsGenericName() {
        return goodsGenericName;
    }

    public void setGoodsGenericName(String goodsGenericName) {
        this.goodsGenericName = goodsGenericName;
    }

    public Long getDosageId() {
        return dosageId;
    }

    public void setDosageId(Long dosageId) {
        this.dosageId = dosageId;
    }

    public String getDosageName() {
        return dosageName;
    }

    public void setDosageName(String dosageName) {
        this.dosageName = dosageName;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getGoodsSpecification() {
        return goodsSpecification;
    }

    public void setGoodsSpecification(String goodsSpecification) {
        this.goodsSpecification = goodsSpecification;
    }

    public Long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getSaleCode() {
        return saleCode;
    }

    public void setSaleCode(String saleCode) {
        this.saleCode = saleCode;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
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

    public BigDecimal getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }

    public BigDecimal getCostAmount() {
        return costAmount;
    }

    public void setCostAmount(BigDecimal costAmount) {
        this.costAmount = costAmount;
    }


    public BigDecimal getProfitRate() {
        return profitRate;
    }

    public void setProfitRate(BigDecimal profitRate) {
        this.profitRate = profitRate;
    }

    public String getStragegyName() {
        return stragegyName;
    }

    public void setStragegyName(String stragegyName) {
        this.stragegyName = stragegyName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }
}