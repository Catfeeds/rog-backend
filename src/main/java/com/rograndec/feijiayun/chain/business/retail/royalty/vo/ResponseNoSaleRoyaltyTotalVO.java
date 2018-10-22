package com.rograndec.feijiayun.chain.business.retail.royalty.vo;

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
public class ResponseNoSaleRoyaltyTotalVO implements Serializable {

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


    @ApiModelProperty(value = "提成明细集合")
    private List<ResponseNoSaleRoyaltyDetailVO> responseNoSaleRoyaltyDetailVOS = new ArrayList<>();


    public static ResponseNoSaleRoyaltyTotalVO getResponseNoSaleRoyaltyTotalVO(
            List<ResponseNoSaleRoyaltyDetailVO> detailVOS
    ){

        ResponseNoSaleRoyaltyTotalVO totalVO = new ResponseNoSaleRoyaltyTotalVO();
        ResponseNoSaleRoyaltyDetailVO fristDetailVO = detailVOS.get(0);

        /**
         * 门店id
         */
        totalVO.setEnterpriseId(fristDetailVO.getEnterpriseId());
        totalVO.setEnterpriseName(fristDetailVO.getEnterpriseName());
        totalVO.setEnterpriseCode(fristDetailVO.getEnterpriseCode());

        /**
         * 营业员ID
         */
        totalVO.setClerkId(fristDetailVO.getClerkId());

        /**
         * 营业员编码
         */
        totalVO.setClerkCode(fristDetailVO.getClerkCode());

        /**
         * 营业员名称
         */
        totalVO.setClerkName(fristDetailVO.getClerkName());

//        BigDecimal saleAmount = new BigDecimal(0);
//        BigDecimal costAmount = new BigDecimal(0);
//        BigDecimal profit = new BigDecimal(0);
//        BigDecimal royaltyAmount = new BigDecimal(0);
//
//        for(ResponseNoSaleRoyaltyDetailVO vo : detailVOS){
//            saleAmount = saleAmount.add(vo.getRealAmount()).setScale(2,BigDecimal.ROUND_HALF_UP);
//            costAmount = costAmount.add(vo.getCostAmount());
//            profit = profit.add(vo.getPorfitAmount());
//            royaltyAmount = royaltyAmount.add(vo.getRoyaltyAmount());
//        }

        /**
         * 销售金额
         */
        BigDecimal saleAmount = ResponseNoSaleRoyaltyDetailVO.getSaleAmount(detailVOS);
        totalVO.setSaleAmount(saleAmount);

        /**
         * 成本金额
         */
        BigDecimal costAmount = ResponseNoSaleRoyaltyDetailVO.getCostAmount(detailVOS);
        totalVO.setCostAmount(costAmount);

        /**
         * 利润金额
         */
        BigDecimal profitAmount = ResponseNoSaleRoyaltyDetailVO.getProfitAmount(detailVOS);
        totalVO.setProfit(profitAmount);

        /**
         * 应提金额
         */
        BigDecimal royaltyAmount = ResponseNoSaleRoyaltyDetailVO.getRoyaltyAmount(detailVOS);
        totalVO.setRoyaltyAmount(royaltyAmount);

        totalVO.setResponseNoSaleRoyaltyDetailVOS(detailVOS);
//        Map<Long,List<RoyaltyStrategyDetail>> royaltyStrategyDetailMap = new HashMap<>();
//        for(RoyaltyStrategyDetail royaltyStrategyDetail : royaltyStrategyDetails){
//            List<RoyaltyStrategyDetail> rsd = royaltyStrategyDetailMap.get(royaltyStrategyDetail.getSetId());
//            if(rsd == null ){
//                rsd = new ArrayList<>();
//                rsd.add(royaltyStrategyDetail);
//                royaltyStrategyDetailMap.put(royaltyStrategyDetail.getSetId(),rsd);
//            }else {
//                rsd.add(royaltyStrategyDetail);
//            }
//
//        }
//        for(ResponseNoSaleRoyaltyDetailVO detailVO : detailVOS){
//            for(RoyaltyStrategy royaltyStrategy : royaltyStrategies){
//                if(detailVO.getStrategyId().equals(royaltyStrategy.getId())){
//                    CalcAmortizationModel calcAmortizationModel = new CalcAmortizationModel(
//                            detailVO.getPorfitAmount()
//                            , detailVO.getProfitRate()
//                            , detailVO.getRealAmount()
//                            , detailVO.getQuantity()
//                            , royaltyStrategy
//                            , royaltyStrategyDetailMap.get(royaltyStrategy.getId())
//                    );
//
//                    BigDecimal royaltyAmount = calcAmortizationModel.calcAmortization(
//                            calcAmortizationModel
//                    );
//
//                }
//            }
//        }

        return totalVO;
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


    public List<ResponseNoSaleRoyaltyDetailVO> getResponseNoSaleRoyaltyDetailVOS() {
        return responseNoSaleRoyaltyDetailVOS;
    }

    public void setResponseNoSaleRoyaltyDetailVOS(List<ResponseNoSaleRoyaltyDetailVO> responseNoSaleRoyaltyDetailVOS) {
        this.responseNoSaleRoyaltyDetailVOS = responseNoSaleRoyaltyDetailVOS;
    }

    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
    }
}