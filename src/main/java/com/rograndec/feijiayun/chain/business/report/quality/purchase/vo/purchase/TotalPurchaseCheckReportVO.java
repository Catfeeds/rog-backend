package com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

public class TotalPurchaseCheckReportVO<T> {

    /**
     * 单据集合
     */
    @ApiModelProperty("单据集合")
    private List<T> data;

    /**
     * 收货数量
     */
    @ApiModelProperty(value = "收货数量")
    private BigDecimal receiveQuantityAll;

    /**
     * 抽检数量
     */
    @ApiModelProperty(value = "抽检数量")
    private BigDecimal samplingQuantityAll;

    /**
     * 验收合格数量
     */
    @ApiModelProperty(value = "验收合格数量")
    private BigDecimal qualifiedQuantityAll;

    /**
     * 验收不合格数量
     */
    @ApiModelProperty(value = "验收不合格数量")
    private BigDecimal unqualifiedQuantityAll;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public BigDecimal getReceiveQuantityAll() {
        return receiveQuantityAll;
    }

    public void setReceiveQuantityAll(BigDecimal receiveQuantityAll) {
        this.receiveQuantityAll = receiveQuantityAll;
    }

    public BigDecimal getSamplingQuantityAll() {
        return samplingQuantityAll;
    }

    public void setSamplingQuantityAll(BigDecimal samplingQuantityAll) {
        this.samplingQuantityAll = samplingQuantityAll;
    }

    public BigDecimal getQualifiedQuantityAll() {
        return qualifiedQuantityAll;
    }

    public void setQualifiedQuantityAll(BigDecimal qualifiedQuantityAll) {
        this.qualifiedQuantityAll = qualifiedQuantityAll;
    }

    public BigDecimal getUnqualifiedQuantityAll() {
        return unqualifiedQuantityAll;
    }

    public void setUnqualifiedQuantityAll(BigDecimal unqualifiedQuantityAll) {
        this.unqualifiedQuantityAll = unqualifiedQuantityAll;
    }

    @Override
    public String toString() {
        return "TotalPurchaseCheckReportVO[" +
                "data=" + data +
                ", receiveQuantityAll=" + receiveQuantityAll +
                ", samplingQuantityAll=" + samplingQuantityAll +
                ", qualifiedQuantityAll=" + qualifiedQuantityAll +
                ", unqualifiedQuantityAll=" + unqualifiedQuantityAll +
                ']';
    }
}
