package com.rograndec.feijiayun.chain.business.purchase.order.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class UnitPriceVO implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 单价
     */
    private BigDecimal unitPrice;

    /**
     * saas_purchase_order_detail
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     * @return id 主键ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键ID
     * @param id 主键ID
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * 单价
     * @return unit_price 单价
     */
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     * 单价
     * @param unitPrice 单价
     */
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }


    /**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", unitPrice=").append(unitPrice);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}