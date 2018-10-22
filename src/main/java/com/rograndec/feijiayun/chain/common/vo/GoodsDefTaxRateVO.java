package com.rograndec.feijiayun.chain.common.vo;

import java.math.BigDecimal;

/**
 *
 * @ClassName: GOodsDefTaxRateVO
 * @Description: 商品默认税率
 * @author zhongyi.li zhongyi.li@rograndec.com
 * @date 2017年10月12日 下午8:40:22
 *
 */
public class GoodsDefTaxRateVO {

    private int taxRateType;
    private Long taxRateId;
    private BigDecimal taxRate;

    public void setTaxRateType(int taxRateType) {
        this.taxRateType = taxRateType;
    }

    public int getTaxRateType() {
        return taxRateType;
    }

    public void setTaxRateId(Long taxRateId) {
        this.taxRateId = taxRateId;
    }

    public Long getTaxRateId() {
        return taxRateId;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

}
