package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel
public class PerpayInvoiceAccountParamVO {

    @ApiModelProperty(value = "单个对账时传递预付发票明细行id,自动对账时传预付发票id")
    @NotNull(message = "发票id不能为空")
    private Long id;

    @ApiModelProperty(value = "业务订单货位行 , 单个对账需要,自动对账不需要传递")
    private List<PerpayInvoiceAccountDetailParamVO>  perpayInvoiceAccountDetailParamVOS;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<PerpayInvoiceAccountDetailParamVO> getPerpayInvoiceAccountDetailParamVOS() {
        return perpayInvoiceAccountDetailParamVOS;
    }

    public void setPerpayInvoiceAccountDetailParamVOS(List<PerpayInvoiceAccountDetailParamVO> perpayInvoiceAccountDetailParamVOS) {
        this.perpayInvoiceAccountDetailParamVOS = perpayInvoiceAccountDetailParamVOS;
    }
}
