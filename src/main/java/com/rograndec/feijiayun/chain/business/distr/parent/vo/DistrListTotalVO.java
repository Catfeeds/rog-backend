package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xingjian.lan@rograndec.com <br/>
 * 生成日期：2017/10/9 <br/>
 * 描述：配货单列表 + 合计
 */
public class DistrListTotalVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 合计金额
     */
    @ApiModelProperty(value = "合计金额")
    private BigDecimal totalSumRealAmout = new BigDecimal(0.00);

    /**
     * 不含税合计金额
     */
    @ApiModelProperty(value = "不含税合计金额")
    private BigDecimal totalSumNotaxRealAmount = new BigDecimal(0.00);

    /**
     * 合计税额
     */
    @ApiModelProperty(value = "合计税额")
    private BigDecimal totalSumTaxAmount = new BigDecimal(0.00);

    List<DistrListVO> distrListVOList;

    public BigDecimal getTotalSumRealAmout() {
        return totalSumRealAmout;
    }

    public void setTotalSumRealAmout(BigDecimal totalSumRealAmout) {
        this.totalSumRealAmout = totalSumRealAmout;
    }

    public BigDecimal getTotalSumNotaxRealAmount() {
        return totalSumNotaxRealAmount;
    }

    public void setTotalSumNotaxRealAmount(BigDecimal totalSumNotaxRealAmount) {
        this.totalSumNotaxRealAmount = totalSumNotaxRealAmount;
    }

    public BigDecimal getTotalSumTaxAmount() {
        return totalSumTaxAmount;
    }

    public void setTotalSumTaxAmount(BigDecimal totalSumTaxAmount) {
        this.totalSumTaxAmount = totalSumTaxAmount;
    }

    public List<DistrListVO> getDistrListVOList() {
        return distrListVOList;
    }

    public void setDistrListVOList(List<DistrListVO> distrListVOList) {
        this.distrListVOList = distrListVOList;
    }
}
