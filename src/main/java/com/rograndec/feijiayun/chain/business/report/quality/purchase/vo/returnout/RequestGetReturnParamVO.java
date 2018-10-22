package com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.returnout;


import io.swagger.annotations.ApiModelProperty;

/**
 * 功能描述：
 * Created by ST on 2017/10/23 13:57
 */

public class RequestGetReturnParamVO extends RequestGetCommonGoodsParamVO{

    /**
     * 购进退出单号
     */
    @ApiModelProperty(value = "购进退出单号")
    private String code;

    @ApiModelProperty(value = "退货人员名称")
    private String returnManName;

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

    /**
     * 退货类型（0-质量问题退货；1-非质量问题退货）
     */
    @ApiModelProperty(value = "退货类型（0-质量问题退货；1-非质量问题退货）")
    private Integer returnType;

    @ApiModelProperty(value = "状态（81-待出库；82-出库中；91-待开票）")
    private Integer status;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getReturnManName() {
        return returnManName;
    }

    public void setReturnManName(String returnManName) {
        this.returnManName = returnManName;
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

    public Integer getReturnType() {
        return returnType;
    }

    public void setReturnType(Integer returnType) {
        this.returnType = returnType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}