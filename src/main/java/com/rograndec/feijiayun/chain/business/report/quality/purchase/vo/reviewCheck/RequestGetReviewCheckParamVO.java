package com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.reviewCheck;


import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.returnout.RequestGetCommonGoodsParamVO;
import io.swagger.annotations.ApiModelProperty;


/**
 * 功能描述：
 * Created by ST on 2017/10/23 13:57
 */

public class RequestGetReviewCheckParamVO extends RequestGetCommonGoodsParamVO{

    @ApiModelProperty(value = "复查单号")
    private String code;

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

    @ApiModelProperty(value = "复查人员")
    private String secondCheckerName;

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

    public String getSecondCheckerName() {
        return secondCheckerName;
    }

    public void setSecondCheckerName(String secondCheckerName) {
        this.secondCheckerName = secondCheckerName;
    }
}