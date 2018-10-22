package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentvoucher.vo;

import com.rograndec.feijiayun.chain.common.vo.RequestBaseParamVO;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 功能描述：
 * Created by ST on 2018/1/8 20:12
 */

public class RequestVoucherParamVO extends RequestBaseParamVO{

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

    /**
     * 过账人员编码
     */
    @ApiModelProperty(value = "过账人员编码")
    private String postManCode;

    /**
     * 过账人员名称
     */
    @ApiModelProperty(value = "过账人员名称")
    private String postManName;

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
     * 状态（0-待收款；1-部分收款；2-已收款；3-已冲销）
     */
    @ApiModelProperty(value = "状态（0-待收款；1-部分收款；2-已收款；3-已冲销）")
    private Integer status;

    private List<Integer> statusList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPostManCode() {
        return postManCode;
    }

    public void setPostManCode(String postManCode) {
        this.postManCode = postManCode;
    }

    public String getPostManName() {
        return postManName;
    }

    public void setPostManName(String postManName) {
        this.postManName = postManName;
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

    public Integer getCodeOrder() {
        if(codeOrder == null) return 0;
        return codeOrder;
    }

    public void setCodeOrder(Integer codeOrder) {
        this.codeOrder = codeOrder;
    }

    public List<Integer> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<Integer> statusList) {
        this.statusList = statusList;
    }
}