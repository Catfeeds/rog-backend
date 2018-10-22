package com.rograndec.feijiayun.chain.business.finance.commission.vo;

import com.rograndec.feijiayun.chain.business.finance.commission.model.SaleCommissionCalcAmortizationModel;
import com.rograndec.feijiayun.chain.business.finance.commission.model.SaleCommissionRange;
import com.rograndec.feijiayun.chain.business.finance.commission.model.SaleCommissionRule;
import com.rograndec.feijiayun.chain.business.goods.sale.entity.CommissionStrategy;
import com.rograndec.feijiayun.chain.business.goods.sale.entity.CommissionStrategyDetail;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

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
public class NoSaleCommissionDetailResponseVO implements Serializable {

    @ApiModelProperty(value = "主键ID")
    private Long id;

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
     * 应提金额
     */
    @ApiModelProperty(value = "应提金额")
    private BigDecimal commissionAmount=BigDecimal.ZERO;

    /**
     * 金额（整单优惠前金额）
     */
    @ApiModelProperty(value = "销售总额 金额（整单优惠前金额）")
    private BigDecimal amount=BigDecimal.ZERO;

    @ApiModelProperty(value = "成本总额")
    private BigDecimal costAmount=BigDecimal.ZERO;

    /**
     * 利润总额
     */
    @ApiModelProperty(value = "利润总额")
    private BigDecimal profit=BigDecimal.ZERO;

    /**
     * 利润率
     */
    @ApiModelProperty(value = "利润率")
    private BigDecimal profitRate=BigDecimal.ZERO;


    /**
     * 策率ID
     */
    @ApiModelProperty(value = "提成策略ID")
    private Long strategyId;

    /**
     * 策略名称
     */
    @ApiModelProperty(value = "提成策略名称")
    private String commissionStrategy;

    /**
     * 门店id
     */
    @ApiModelProperty(value = "门店id")
    private Long enterpriseId;

    @ApiModelProperty(value = "门店名称")
    private String enterpriseName;

    @ApiModelProperty(value = "门店编码")
    private String enterpriseCode;

    /**
     * 行号
     */
    @ApiModelProperty(value = "行号")
    private Integer lineNum;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "销售单明细id集合")
    private List<Long> saleDtlIds = new ArrayList<>();


    public static List<Long> getGoodsIds(List<NoSaleCommissionDetailResponseVO> responseNoSaleRoyaltyDetailVOS){
        List<Long> collect = responseNoSaleRoyaltyDetailVOS.stream().map(NoSaleCommissionDetailResponseVO::getGoodsId).collect(Collectors.toList());

        return collect;
    }


    public static List<Long> getSlDtlIds(List<NoSaleCommissionDetailResponseVO> responseNoSaleRoyaltyDetailVOS){

        Set<Long> idSets = new HashSet<>();
        for(NoSaleCommissionDetailResponseVO vo : responseNoSaleRoyaltyDetailVOS){
            idSets.addAll(vo.getSaleDtlIds());
        }

        List<Long> collect = idSets.stream().collect(Collectors.toList());

        return collect;
    }

    public static List<NoSaleCommissionDetailResponseVO> generateNoSaleCommissionDetailResponseVOs(List<NoSaleCommissionDetailResponseVO> noSaleCommissionDetailResponseVOS){

        Map<Long,List<NoSaleCommissionDetailResponseVO>> map = new HashMap<>();

        for(NoSaleCommissionDetailResponseVO nscdr : noSaleCommissionDetailResponseVOS){

            List<NoSaleCommissionDetailResponseVO> ns = map.get(nscdr.getGoodsId());
            if(CollectionUtils.isEmpty(ns)){
                ns = new ArrayList<>();
                ns.add(nscdr);
                map.put(nscdr.getGoodsId(),ns);
            }else {
                ns.add(nscdr);
            }

        }

        List<NoSaleCommissionDetailResponseVO> ns = new ArrayList<>();

        for(Map.Entry<Long,List<NoSaleCommissionDetailResponseVO>> entry : map.entrySet()){

            List<NoSaleCommissionDetailResponseVO> value = entry.getValue();

            NoSaleCommissionDetailResponseVO noSaleCommissionDetailResponseVO = new NoSaleCommissionDetailResponseVO();

            if(!CollectionUtils.isEmpty(value)){
                NoSaleCommissionDetailResponseVO n = value.get(0);

                ns.add(n);

                   /**-- sum(a.quantity) quantity,  -- 数量
              -- sum(a.real_amount) as realAmount, -- 销售总额*/

                // 数量
                BigDecimal quantity = value.stream().filter(Objects::nonNull)
                        .filter(c -> null != c.getQuantity()).map(NoSaleCommissionDetailResponseVO::getQuantity).reduce(BigDecimal.ZERO,BigDecimal::add);
                n.setQuantity(quantity);

                // 销售总额
                BigDecimal amount = value.stream().filter(Objects::nonNull)
                        .filter(c -> null != c.getAmount()).map(NoSaleCommissionDetailResponseVO::getAmount).reduce(BigDecimal.ZERO,BigDecimal::add);
                n.setAmount(amount);

                /**
                 *  -- sum(e.cost_amount) as costAmount, -- 成本总额
                 -- (sum(a.real_amount) - sum(e.cost_amount)) as profit, -- 利润总额
                 -- (sum(a.real_amount) - sum(e.cost_amount)) / sum(a.real_amount) as profitRate, -- 利润率
                 */

                // 成本总额
                BigDecimal costAmount = value.stream().filter(Objects::nonNull)
                        .filter(c -> null != c.getCostAmount()).map(NoSaleCommissionDetailResponseVO::getCostAmount).reduce(BigDecimal.ZERO,BigDecimal::add);
                n.setCostAmount(costAmount);

                // 利润总额
                BigDecimal profit = amount.subtract(costAmount);
                n.setProfit(profit);

                //利润率
                BigDecimal profitRate = profit.divide(amount,4, BigDecimal.ROUND_HALF_UP);
                n.setProfitRate(profitRate);

                List<Long> saleDtlIds = value.stream().map(v -> {
                    return v.getSaleDtlId();
                }).collect(Collectors.toList());
                n.setSaleDtlIds(saleDtlIds);

                /**
                 * 商品ID
                 */
                noSaleCommissionDetailResponseVO.setGoodsId(n.getGoodsId());

                /**
                 * 商品编码
                 */
                noSaleCommissionDetailResponseVO.setGoodsCode(n.getGoodsCode());
                /**
                 * 商品通用名称
                 */
                noSaleCommissionDetailResponseVO.setGoodsGenericName(n.getGoodsGenericName());
                /**
                 * 剂型ID
                 */
                noSaleCommissionDetailResponseVO.setDosageId(n.getDosageId());

                /**
                 * 剂型名称
                 */
                noSaleCommissionDetailResponseVO.setDosageName(n.getDosageName());

                /**
                 * 单位ID
                 */
                noSaleCommissionDetailResponseVO.setUnitId(n.getUnitId());

                /**
                 * 单位名称
                 */
                noSaleCommissionDetailResponseVO.setUnitName(n.getUnitName());

                /**
                 * 商品规格
                 */
                noSaleCommissionDetailResponseVO.setGoodsSpecification(n.getGoodsSpecification());

                /**
                 * 生产厂商ID
                 */
                noSaleCommissionDetailResponseVO.setManufacturerId(n.getManufacturerId());

                /**
                 * 生产厂商
                 */
                noSaleCommissionDetailResponseVO.setManufacturer(n.getManufacturer());

                /**
                 * 总数量
                 */
                noSaleCommissionDetailResponseVO.setQuantity(n.getQuantity());

                /**
                 * 单价
                 */
                noSaleCommissionDetailResponseVO.setUnitPrice(n.getUnitPrice());

                /**
                 * 营业员ID
                 */
                noSaleCommissionDetailResponseVO.setClerkId(n.getClerkId());

                /**
                 * 营业员编码
                 */
                noSaleCommissionDetailResponseVO.setClerkCode(n.getClerkCode());

                /**
                 * 营业员名称
                 */
                noSaleCommissionDetailResponseVO.setClerkName(n.getClerkName());

                /**
                 * 提成金额
                 */
                noSaleCommissionDetailResponseVO.setCommissionAmount(n.getCommissionAmount());

                /**
                 * 金额（整单优惠前金额）
                 */
                noSaleCommissionDetailResponseVO.setAmount(n.getAmount());

                noSaleCommissionDetailResponseVO.setCostAmount(n.getCostAmount());

                /**
                 * 利润总额
                 */
                noSaleCommissionDetailResponseVO.setProfit(n.getProfit());

                /**
                 * 利润率
                 */
                noSaleCommissionDetailResponseVO.setProfitRate(n.getProfitRate());


                /**
                 * 策率ID
                 */
                noSaleCommissionDetailResponseVO.setStrategyId(n.getStrategyId());

                /**
                 * 策略名称
                 */
                noSaleCommissionDetailResponseVO.setCommissionStrategy(n.getCommissionStrategy());
            }

        }

        return ns;

    }

    public static List<Long> getStrategyIds(List<NoSaleCommissionDetailResponseVO> saleRoyaltyDetailVOS){

        List<Long> ids = new ArrayList<>();
        for(NoSaleCommissionDetailResponseVO saleRoyaltyDetailVO : saleRoyaltyDetailVOS){
            ids.add(saleRoyaltyDetailVO.getStrategyId());
        }

        return ids;

    }

    public static void setRoyaltyAmount4Calc(
            List<CommissionStrategyDetail> commissionStrategyDetails
            ,List<CommissionStrategy> commissionStrategies
            ,List<NoSaleCommissionDetailResponseVO> detailVOS
    ){

        if(CollectionUtils.isEmpty(commissionStrategies)){
            return;
        }

        if(CollectionUtils.isEmpty(commissionStrategyDetails)){
            return;
        }

        List<SaleCommissionRule> saleCommissionRules = new ArrayList<>();

        for (CommissionStrategy royaltyStrategy : commissionStrategies) {

            SaleCommissionRule saleCommissionRule = new SaleCommissionRule();

            saleCommissionRules.add(saleCommissionRule);

            saleCommissionRule.setId(royaltyStrategy.getId());
            /**
             * 编码
             */
            saleCommissionRule.setCode(royaltyStrategy.getCode());

            /**
             * 名称
             */
            saleCommissionRule.setName(royaltyStrategy.getName());

            /**
             * 方法（0-按销售数量提成；1-按销售金额提成；2-按利润额提成；3-按利润率提成）
             */
            saleCommissionRule.setMethod(royaltyStrategy.getMethod());

            /**
             * 方式（0-按比例；1-按金额）
             */
            saleCommissionRule.setMode(royaltyStrategy.getMode());

            /**
             * 0-全额提成；1-盈余提成
             */
            saleCommissionRule.setRange(royaltyStrategy.getRange());

            /**
             * 状态（0-禁用；1-启用）
             */
            saleCommissionRule.setStatus(royaltyStrategy.getStatus());

            for(CommissionStrategyDetail commissionStrategyDetail : commissionStrategyDetails){

                if(commissionStrategyDetail.getSetId().equals(royaltyStrategy.getId())){

                    SaleCommissionRange saleCommissionRange = new SaleCommissionRange();

                    saleCommissionRange.setId(commissionStrategyDetail.getId());
                    saleCommissionRange.setSetId(commissionStrategyDetail.getSetId());

                    /**
                     * 提成基数 开始
                     */
                    saleCommissionRange.setStart(commissionStrategyDetail.getBase());

                    saleCommissionRange.setEnd(commissionStrategyDetail.getBaseTo());

                    /**
                     * 提成比例/金额
                     */
                    saleCommissionRange.setRatio(commissionStrategyDetail.getRatio());

                    saleCommissionRule.getSaleCommissionRanges().add(saleCommissionRange);

                }

            }

        }

        for(NoSaleCommissionDetailResponseVO detailVO : detailVOS){

            for(SaleCommissionRule saleCommissionRule : saleCommissionRules){

                if(detailVO.getStrategyId().equals(saleCommissionRule.getId())){

                    SaleCommissionCalcAmortizationModel saleCommissionCalcAmortizationModel = new SaleCommissionCalcAmortizationModel(
                            detailVO.getProfit()
                            , detailVO.getProfitRate()
                            , detailVO.getAmount()
                            , detailVO.getQuantity()
                            ,saleCommissionRule
                    );

                    BigDecimal royaltyAmount = SaleCommissionCalcAmortizationModel.calcAmortization(saleCommissionCalcAmortizationModel);

                    detailVO.setCommissionAmount(royaltyAmount);
                }
            }

        }


    }


    /**
     * 获取提成金额
     * @param responseNoSaleRoyaltyDetailVOS
     * @return
     */
    public static BigDecimal getRoyaltyAmount(List<NoSaleCommissionDetailResponseVO> responseNoSaleRoyaltyDetailVOS){
        BigDecimal saleAmount = new BigDecimal(0);

        for(NoSaleCommissionDetailResponseVO vo : responseNoSaleRoyaltyDetailVOS){
            saleAmount = saleAmount.add(vo.getCommissionAmount());
        }
        return saleAmount;
    }


    /**
     * 获取利润金额
     * @param responseNoSaleRoyaltyDetailVOS
     * @return
     */
    public static BigDecimal getProfitAmount(List<NoSaleCommissionDetailResponseVO> responseNoSaleRoyaltyDetailVOS){
        BigDecimal saleAmount = new BigDecimal(0);

        for(NoSaleCommissionDetailResponseVO vo : responseNoSaleRoyaltyDetailVOS){
            saleAmount = saleAmount.add(vo.getProfit());
        }
        return saleAmount;
    }

    /**
     * 获取成本总额
     * @param responseNoSaleRoyaltyDetailVOS
     * @return
     */
    public static BigDecimal getCostAmount(List<NoSaleCommissionDetailResponseVO> responseNoSaleRoyaltyDetailVOS){
        BigDecimal saleAmount = new BigDecimal(0);

        for(NoSaleCommissionDetailResponseVO vo : responseNoSaleRoyaltyDetailVOS){
            saleAmount = saleAmount.add(vo.getCostAmount());
        }
        return saleAmount;
    }

    /**
     * 获取销售总额
     * @param responseNoSaleRoyaltyDetailVOS
     * @return
     */
    public static BigDecimal getSaleAmount(List<NoSaleCommissionDetailResponseVO> responseNoSaleRoyaltyDetailVOS){
        BigDecimal saleAmount = new BigDecimal(0);

        for(NoSaleCommissionDetailResponseVO vo : responseNoSaleRoyaltyDetailVOS){
            saleAmount = saleAmount.add(vo.getAmount());
        }
        return saleAmount;
    }

    public static BigDecimal getSumQuantity(List<NoSaleCommissionDetailResponseVO> responseNoSaleRoyaltyDetailVOS){

        BigDecimal sumQuantity = responseNoSaleRoyaltyDetailVOS.stream().filter(Objects::nonNull)
                .filter(c -> null != c.getQuantity()).map(NoSaleCommissionDetailResponseVO::getQuantity).reduce(BigDecimal.ZERO,BigDecimal::add);

        return sumQuantity;

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

    public BigDecimal getCommissionAmount() {
        return commissionAmount;
    }

    public void setCommissionAmount(BigDecimal commissionAmount) {
        this.commissionAmount = commissionAmount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCommissionStrategy() {
        return commissionStrategy;
    }

    public void setCommissionStrategy(String commissionStrategy) {
        this.commissionStrategy = commissionStrategy;
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

    public List<Long> getSaleDtlIds() {
        return saleDtlIds;
    }

    public void setSaleDtlIds(List<Long> saleDtlIds) {
        this.saleDtlIds = saleDtlIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}