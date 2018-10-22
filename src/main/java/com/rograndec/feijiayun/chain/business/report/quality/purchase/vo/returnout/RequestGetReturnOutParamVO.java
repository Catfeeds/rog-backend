package com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.returnout;


import io.swagger.annotations.ApiModelProperty;

/**
 * 功能描述：
 * Created by ST on 2017/10/23 13:57
 */

public class RequestGetReturnOutParamVO extends RequestGetCommonGoodsParamVO{

    /**
     * 购进退出单号
     */
    @ApiModelProperty(value = "购进退出出库单号")
    private String code;

    @ApiModelProperty(value = "出库人员名称")
    private String outManName;

    /**
     * 供货单位编码
     */
    @ApiModelProperty(value = "供货单位编码")
    private String supplierCode;

    /**
     * 供货单位名称
     */
    @ApiModelProperty(value = "供货单位名称")
    private String supplierName;

    @ApiModelProperty(value = "流通监管码")
    private String flowCode;


    @ApiModelProperty(value = "状态（83-待复核；91-待开票;92-待支付;93-已支付）")
    private Integer status;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOutManName() {
        return outManName;
    }

    public void setOutManName(String outManName) {
        this.outManName = outManName;
    }

    public String getFlowCode() {
        return flowCode;
    }

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
    }
}