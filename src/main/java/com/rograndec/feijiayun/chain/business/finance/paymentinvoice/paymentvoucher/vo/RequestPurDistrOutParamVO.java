package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentvoucher.vo;

import com.rograndec.feijiayun.chain.common.vo.RequestBaseParamVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * 功能描述：
 * Created by ST on 2018/1/8 20:12
 */

public class RequestPurDistrOutParamVO extends RequestBaseParamVO{

    /**
     * 单据编码
     */
    @ApiModelProperty(value = "单据编码")
    private String code;

    /**
     * 单据编码
     */
    @ApiModelProperty(value = "日期排序 0/倒序；1/正序;默认0")
    private Integer codeOrder;


    private Long supplierId;


    private Integer status;

    /**
     * 状态
     */
    @ApiModelProperty(value = "部分开票状态")
    private Integer partBillStatus;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getCodeOrder() {
        if(codeOrder == null) return 0;
        return codeOrder;
    }

    public void setCodeOrder(Integer codeOrder) {
        this.codeOrder = codeOrder;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getPartBillStatus() {
        return partBillStatus;
    }

    public void setPartBillStatus(Integer partBillStatus) {
        this.partBillStatus = partBillStatus;
    }
}