package com.rograndec.feijiayun.chain.business.retail.royalty.vo;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zhaiwei on 2017/9/27.
 */
public class ResponseNoTotalVO {

    private List<ResponseNoSaleRoyaltyTotalVO> responseNoSaleRoyaltyTotalVOS;

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

    public static ResponseNoTotalVO getResponseNoTotalVO(List<ResponseNoSaleRoyaltyTotalVO> responseNoSaleRoyaltyTotalVOS){
        ResponseNoTotalVO responseNoTotalVO = new ResponseNoTotalVO();
        BigDecimal saleAmountTotal = new BigDecimal(0);
        BigDecimal costAmountTotal = new BigDecimal(0);
        BigDecimal profitTotal = new BigDecimal(0);
        BigDecimal royaltyAmountTotal = new BigDecimal(0);
        for(ResponseNoSaleRoyaltyTotalVO vo : responseNoSaleRoyaltyTotalVOS){

            saleAmountTotal = saleAmountTotal.add(vo.getSaleAmount()).setScale(2,BigDecimal.ROUND_HALF_UP);
            costAmountTotal = costAmountTotal.add(vo.getCostAmount()).setScale(2,BigDecimal.ROUND_HALF_UP);
            profitTotal = profitTotal.add(vo.getProfit()).setScale(2,BigDecimal.ROUND_HALF_UP);
            royaltyAmountTotal = royaltyAmountTotal.add(vo.getRoyaltyAmount()).setScale(2,BigDecimal.ROUND_HALF_UP);

        }

        responseNoTotalVO.setSaleAmountTotal(saleAmountTotal);
        responseNoTotalVO.setCostAmountTotal(costAmountTotal);
        responseNoTotalVO.setProfitTotal(profitTotal);
        responseNoTotalVO.setRoyaltyAmountTotal(royaltyAmountTotal);
        responseNoTotalVO.setResponseNoSaleRoyaltyTotalVOS(responseNoSaleRoyaltyTotalVOS);

        return responseNoTotalVO;
    }

    public List<ResponseNoSaleRoyaltyTotalVO> getResponseNoSaleRoyaltyTotalVOS() {
        return responseNoSaleRoyaltyTotalVOS;
    }

    public void setResponseNoSaleRoyaltyTotalVOS(List<ResponseNoSaleRoyaltyTotalVO> responseNoSaleRoyaltyTotalVOS) {
        this.responseNoSaleRoyaltyTotalVOS = responseNoSaleRoyaltyTotalVOS;
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
}
