package com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.reviewCheck;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * 功能描述：
 * Created by ST on 2017/10/23 15:03
 */

public class PurchaseReviewCheckTotalVO {

    /**
     * 数量合计
     */
    @ApiModelProperty(value = "复查数量合计")
    private BigDecimal reviewQuantityTotal;

    private List<PurchaseReviewCheckGoodsReportVO> reviewCheckGoodsReportVOS;


    public BigDecimal getReviewQuantityTotal() {
        return reviewQuantityTotal;
    }

    public void setReviewQuantityTotal(BigDecimal reviewQuantityTotal) {
        this.reviewQuantityTotal = reviewQuantityTotal;
    }

    public List<PurchaseReviewCheckGoodsReportVO> getReviewCheckGoodsReportVOS() {
        return reviewCheckGoodsReportVOS;
    }

    public void setReviewCheckGoodsReportVOS(List<PurchaseReviewCheckGoodsReportVO> reviewCheckGoodsReportVOS) {
        this.reviewCheckGoodsReportVOS = reviewCheckGoodsReportVOS;
    }
}