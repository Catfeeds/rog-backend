package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class BranchStockVO implements Serializable {

    /**
     * 企业ID
     */
    @ApiModelProperty(value = "企业ID", required = true)
    private Long enterpriseId;

    /**
     * 门店可用库存
     */
    @ApiModelProperty(value = "门店可用库存", required = true)
    private BigDecimal usableQuantity;

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public BigDecimal getUsableQuantity() {
        return usableQuantity;
    }

    public void setUsableQuantity(BigDecimal usableQuantity) {
        this.usableQuantity = usableQuantity;
    }

    @Override
    public String toString() {
        return "BranchStockVO[" +
                "enterpriseId=" + enterpriseId +
                ", usableQuantity=" + usableQuantity +
                ']';
    }
}
