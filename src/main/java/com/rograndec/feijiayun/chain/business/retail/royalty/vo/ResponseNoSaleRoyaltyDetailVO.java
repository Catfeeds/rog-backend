package com.rograndec.feijiayun.chain.business.retail.royalty.vo;

import com.rograndec.feijiayun.chain.business.goods.sale.entity.CommissionStrategy;
import com.rograndec.feijiayun.chain.business.goods.sale.entity.CommissionStrategyDetail;
import com.rograndec.feijiayun.chain.business.retail.royalty.vo.transfer.CalcAmortizationModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

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
public class ResponseNoSaleRoyaltyDetailVO implements Serializable {



    /**
     * 基础单据ID
     */
    @ApiModelProperty(value = "销售单据ID")
    private Long saleId;

    /**
     * 基础单据明细ID
     */
    @ApiModelProperty(value = "销售单据明细ID")
    private Long saleDtlId;
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


    /**
     * 策率ID
     */
    @ApiModelProperty(value = "策率ID")
    private Long strategyId;

    /**
     * 策略名称
     */
    @ApiModelProperty(value = "策略名称")
    private String royaltyStrategy;

    /**
     * 门店id
     */
    @ApiModelProperty(value = "门店id")
    private Long enterpriseId;

    @ApiModelProperty(value = "门店名称")
    private String enterpriseName;

    @ApiModelProperty(value = "门店编码")
    private String enterpriseCode;


    public static List<Long> getStrategyIds(List<ResponseNoSaleRoyaltyDetailVO> saleRoyaltyDetailVOS){

        List<Long> ids = new ArrayList<>();
        for(ResponseNoSaleRoyaltyDetailVO saleRoyaltyDetailVO : saleRoyaltyDetailVOS){
            ids.add(saleRoyaltyDetailVO.getStrategyId());
        }

        return ids;

    }

    public static void setRoyaltyAmount4Calc(
            List<CommissionStrategyDetail> royaltyStrategyDetails
            ,List<CommissionStrategy> royaltyStrategies
            ,List<ResponseNoSaleRoyaltyDetailVO> detailVOS
    ){
        Map<Long,List<CommissionStrategyDetail>> royaltyStrategyDetailMap = new HashMap<>();
        for(CommissionStrategyDetail royaltyStrategyDetail : royaltyStrategyDetails){
            List<CommissionStrategyDetail> rsd = royaltyStrategyDetailMap.get(royaltyStrategyDetail.getSetId());
            if(rsd == null ){
                rsd = new ArrayList<>();
                rsd.add(royaltyStrategyDetail);
                royaltyStrategyDetailMap.put(royaltyStrategyDetail.getSetId(),rsd);
            }else {
                rsd.add(royaltyStrategyDetail);
            }

        }
        for(ResponseNoSaleRoyaltyDetailVO detailVO : detailVOS){
            for(CommissionStrategy royaltyStrategy : royaltyStrategies){
                if(detailVO.getStrategyId().equals(royaltyStrategy.getId())){
                    CalcAmortizationModel calcAmortizationModel = new CalcAmortizationModel(
                            detailVO.getProfit()
                            , detailVO.getProfitRate()
                            , detailVO.getRealAmount()
                            , detailVO.getQuantity()
                            , royaltyStrategy
                            , royaltyStrategyDetailMap.get(royaltyStrategy.getId())
                    );

                    BigDecimal royaltyAmount = calcAmortizationModel.calcAmortization(
                            calcAmortizationModel
                    );

                    detailVO.setRoyaltyAmount(royaltyAmount);
                }
            }
        }
    }

    /**
     * 获取提成金额
     * @param responseNoSaleRoyaltyDetailVOS
     * @return
     */
    public static BigDecimal getRoyaltyAmount(List<ResponseNoSaleRoyaltyDetailVO> responseNoSaleRoyaltyDetailVOS){
        BigDecimal saleAmount = new BigDecimal(0);

        for(ResponseNoSaleRoyaltyDetailVO vo : responseNoSaleRoyaltyDetailVOS){
            saleAmount = saleAmount.add(vo.getRoyaltyAmount());
        }
        return saleAmount;
    }


    /**
     * 获取利润金额
     * @param responseNoSaleRoyaltyDetailVOS
     * @return
     */
    public static BigDecimal getProfitAmount(List<ResponseNoSaleRoyaltyDetailVO> responseNoSaleRoyaltyDetailVOS){
        BigDecimal saleAmount = new BigDecimal(0);

        for(ResponseNoSaleRoyaltyDetailVO vo : responseNoSaleRoyaltyDetailVOS){
            saleAmount = saleAmount.add(vo.getProfit());
        }
        return saleAmount;
    }

    /**
     * 获取成本总额
     * @param responseNoSaleRoyaltyDetailVOS
     * @return
     */
    public static BigDecimal getCostAmount(List<ResponseNoSaleRoyaltyDetailVO> responseNoSaleRoyaltyDetailVOS){
        BigDecimal saleAmount = new BigDecimal(0);

        for(ResponseNoSaleRoyaltyDetailVO vo : responseNoSaleRoyaltyDetailVOS){
            saleAmount = saleAmount.add(vo.getCostAmount());
        }
        return saleAmount;
    }

    /**
     * 获取销售总额
     * @param responseNoSaleRoyaltyDetailVOS
     * @return
     */
    public static BigDecimal getSaleAmount(List<ResponseNoSaleRoyaltyDetailVO> responseNoSaleRoyaltyDetailVOS){
        BigDecimal saleAmount = new BigDecimal(0);

        for(ResponseNoSaleRoyaltyDetailVO vo : responseNoSaleRoyaltyDetailVOS){
            saleAmount = saleAmount.add(vo.getRealAmount());
        }
        return saleAmount;
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public Long getSaleDtlId() {
        return saleDtlId;
    }

    public void setSaleDtlId(Long saleDtlId) {
        this.saleDtlId = saleDtlId;
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

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public String getRoyaltyStrategy() {
        return royaltyStrategy;
    }

    public void setRoyaltyStrategy(String royaltyStrategy) {
        this.royaltyStrategy = royaltyStrategy;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
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

    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }
}