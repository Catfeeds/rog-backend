package com.rograndec.feijiayun.chain.business.report.finance.account.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhangyu
 * @create 2018-01-11
 */
@ApiModel(value = "PendingInvoicingVO", description = "财务管理-应付/应收账款-应付/应收待开票单据打印显示")
public class PendingInvoicingPrintVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "企业名称")
    private String enterpriseName;

    @ApiModelProperty(value = "应付/应收待开票单据")
    private List<PendingInvoicingVO> pendingInvoicingVOS;

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public List<PendingInvoicingVO> getPendingInvoicingVOS() {
        return pendingInvoicingVOS;
    }

    public void setPendingInvoicingVOS(List<PendingInvoicingVO> pendingInvoicingVOS) {
        this.pendingInvoicingVOS = pendingInvoicingVOS;
    }
}
