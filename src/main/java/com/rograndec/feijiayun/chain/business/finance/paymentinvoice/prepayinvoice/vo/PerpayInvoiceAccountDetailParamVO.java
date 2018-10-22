package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@ApiModel
public class PerpayInvoiceAccountDetailParamVO {


    @ApiModelProperty(value = "业务订单货位行id , 单个对账需要,自动对账不需要传递")
    @NotNull(message = "发票货位行id不能为空")
    private Long orderShelfId;

    @ApiModelProperty(value = "对账数量 , 单个对账需要,自动对账不需要传递")
    private BigDecimal accountQuantity = BigDecimal.ZERO;

    @ApiModelProperty(value = "行号 , 单个对账需要,自动对账不需要传递")
    @NotNull(message = "行号不能为空")
    private Integer lineNum;

    public Long getOrderShelfId() {
        return orderShelfId;
    }

    public void setOrderShelfId(Long orderShelfId) {
        this.orderShelfId = orderShelfId;
    }

    public BigDecimal getAccountQuantity() {
        return accountQuantity;
    }

    public void setAccountQuantity(BigDecimal accountQuantity) {
        this.accountQuantity = accountQuantity;
    }

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }
}
