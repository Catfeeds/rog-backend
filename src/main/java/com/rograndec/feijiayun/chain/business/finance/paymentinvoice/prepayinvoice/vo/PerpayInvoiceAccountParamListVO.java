package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel
public class PerpayInvoiceAccountParamListVO {

    @ApiModelProperty(value = "对账集合")
    private List<PerpayInvoiceAccountParamVO> perpayInvoiceAccountParamVOs;

    public List<PerpayInvoiceAccountParamVO> getPerpayInvoiceAccountParamVOs() {
        return perpayInvoiceAccountParamVOs;
    }

    public void setPerpayInvoiceAccountParamVOs(List<PerpayInvoiceAccountParamVO> perpayInvoiceAccountParamVOs) {
        this.perpayInvoiceAccountParamVOs = perpayInvoiceAccountParamVOs;
    }
}
