package com.rograndec.feijiayun.chain.business.purchase.check.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zeshi.sun on 2017/9/13.
 */
public class PurchaseCheckVO implements Serializable {


    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID", required = true)
    private Long id;

    /**
     * 验收日期
     */
    @ApiModelProperty(value = "验收日期", required = true)
    private Date checkDate;


    /**
     * 单据（验收单）编号
     */
    @ApiModelProperty(value = "单据（验收单）编号", required = true)
    private String code;

    /**
     * 供货单位编码
     */
    @ApiModelProperty(value = "供货单位编码", required = true)
    private String supplierCode;

    /**
     * 供货单位名称
     */
    @ApiModelProperty(value = "供货单位名称", required = true)
    private String supplierName;

    /**
     * 验收人员名称
     */
    @ApiModelProperty(value = "验收人员名称", required = true)
    private String checkerName;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态", required = true)
    private String status;

    /**
     * 配送类型
     */
    @ApiModelProperty(required = true, value = "配送类型")
    private String carriageModeName;

    /**
     * 配送方式（0-总部配送；1-委托运输；2-自提；3-门店间调剂；4-直调配送）
     */
    @ApiModelProperty(required = true, value = "配送方式（0-总部配送；1-委托运输；2-自提；3-门店间调剂；4-直调配送）")
    private Integer carriageModeId;

    /**
     * 结算单位是否是加盟店（0-否  1-是）
     */
    @ApiModelProperty(required = true, value = "结算单位是否是加盟店（0-否  1-是）")
    private Integer franchisedStoreFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

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

    public String getCheckerName() {
        return checkerName;
    }

    public void setCheckerName(String checkerName) {
        this.checkerName = checkerName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCarriageModeName() {
        return carriageModeName;
    }

    public void setCarriageModeName(String carriageModeName) {
        this.carriageModeName = carriageModeName;
    }

    public Integer getCarriageModeId() {
        return carriageModeId;
    }

    public void setCarriageModeId(Integer carriageModeId) {
        this.carriageModeId = carriageModeId;
    }

    public Integer getFranchisedStoreFlag() {
        return franchisedStoreFlag;
    }

    public void setFranchisedStoreFlag(Integer franchisedStoreFlag) {
        this.franchisedStoreFlag = franchisedStoreFlag;
    }

    @Override
    public String toString() {
        return "PurchaseCheckVO[" +
                "id=" + id +
                ", checkDate=" + checkDate +
                ", code='" + code + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", checkerName=" + checkerName +
                ", status=" + status +
                ", carriageModeName=" + carriageModeName +
                ", carriageModeId=" + carriageModeId +
                ", franchisedStoreFlag=" + franchisedStoreFlag +
                ']';
    }
}
