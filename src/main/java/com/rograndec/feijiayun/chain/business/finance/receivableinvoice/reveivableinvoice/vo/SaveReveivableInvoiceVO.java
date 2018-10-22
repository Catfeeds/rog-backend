package com.rograndec.feijiayun.chain.business.finance.receivableinvoice.reveivableinvoice.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class SaveReveivableInvoiceVO implements Serializable {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID(更新时必传字段)")
    private Long id;

    /**
     * 应收发票缓存RedisKeyValue
     */
    @ApiModelProperty(value = "应收发票缓存RedisKeyValue(调用缓存时必填字段)")
    private String redisKeyValue;

    /**
     * 单据编码
     */
    @ApiModelProperty(value = "单据编码")
    private String code;

    /**
     * 开票日期
     */
    @ApiModelProperty(value = "开票日期(新增必传)")
    private Date billDate;

    /**
     * 开票人员ID
     */
    @ApiModelProperty(value = "开票人员ID(新增必传)")
    private Long billManId;

    /**
     * 开票人员编码
     */
    @ApiModelProperty(value = "开票人员编码")
    private String billManCode;

    /**
     * 开票人员名称
     */
    @ApiModelProperty(value = "开票人员名称")
    private String billManName;

    /**
     * 购货单位ID
     */
    @ApiModelProperty(value = "购货单位ID(新增必传)")
    private Long purchaseUnitId;

    /**
     * 购货单位编码
     */
    @ApiModelProperty(value = "购货单位编码")
    private String purchaseUnitCode;

    /**
     * 购货单位名称
     */
    @ApiModelProperty(value = "购货单位名称")
    private String purchaseUnitName;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 数量合计
     */
    @ApiModelProperty(value = "数量合计")
    private BigDecimal quantityTotal;

    /**
     * 品种数量
     */
    @ApiModelProperty(value = "品种数量")
    private Integer varietiesQuantity;

    /**
     * 金额合计
     */
    @ApiModelProperty(value = "金额合计")
    private BigDecimal amountTotal;

    /**
     * 不含税金额合计
     */
    @ApiModelProperty(value = "不含税金额合计")
    private BigDecimal notaxAmountTotal;

    /**
     * 税额合计
     */
    @ApiModelProperty(value = "税额合计")
    private BigDecimal taxAmountTotal;

    /**
     * 修改原因
     */
    @ApiModelProperty(value = "修改原因")
    private String updateReason;

    /**
     * 状态（0-待收款；1-部分收款；2-已收款；3-已冲销）
     */
    @ApiModelProperty(value = "状态（0-待收款；1-部分收款；2-已收款；3-已冲销）")
    private Integer status;

    List<SaveReceivableInvoiceDetailVO> saveReceivableInvoiceDetailVO;

    SaveReceivableInvoiceInfoVO saveReceivableInvoiceInfoVO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public Long getBillManId() {
        return billManId;
    }

    public void setBillManId(Long billManId) {
        this.billManId = billManId;
    }

    public String getBillManCode() {
        return billManCode;
    }

    public void setBillManCode(String billManCode) {
        this.billManCode = billManCode;
    }

    public String getBillManName() {
        return billManName;
    }

    public void setBillManName(String billManName) {
        this.billManName = billManName;
    }

    public Long getPurchaseUnitId() {
        return purchaseUnitId;
    }

    public void setPurchaseUnitId(Long purchaseUnitId) {
        this.purchaseUnitId = purchaseUnitId;
    }

    public String getPurchaseUnitCode() {
        return purchaseUnitCode;
    }

    public void setPurchaseUnitCode(String purchaseUnitCode) {
        this.purchaseUnitCode = purchaseUnitCode;
    }

    public String getPurchaseUnitName() {
        return purchaseUnitName;
    }

    public void setPurchaseUnitName(String purchaseUnitName) {
        this.purchaseUnitName = purchaseUnitName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<SaveReceivableInvoiceDetailVO> getSaveReceivableInvoiceDetailVO() {
        return saveReceivableInvoiceDetailVO;
    }

    public void setSaveReceivableInvoiceDetailVO(List<SaveReceivableInvoiceDetailVO> saveReceivableInvoiceDetailVO) {
        this.saveReceivableInvoiceDetailVO = saveReceivableInvoiceDetailVO;
    }

    public SaveReceivableInvoiceInfoVO getSaveReceivableInvoiceInfoVO() {
        return saveReceivableInvoiceInfoVO;
    }

    public void setSaveReceivableInvoiceInfoVO(SaveReceivableInvoiceInfoVO saveReceivableInvoiceInfoVO) {
        this.saveReceivableInvoiceInfoVO = saveReceivableInvoiceInfoVO;
    }

    public Integer getVarietiesQuantity() {
        return varietiesQuantity;
    }

    public void setVarietiesQuantity(Integer varietiesQuantity) {
        this.varietiesQuantity = varietiesQuantity;
    }

    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    public BigDecimal getNotaxAmountTotal() {
        return notaxAmountTotal;
    }

    public void setNotaxAmountTotal(BigDecimal notaxAmountTotal) {
        this.notaxAmountTotal = notaxAmountTotal;
    }

    public BigDecimal getTaxAmountTotal() {
        return taxAmountTotal;
    }

    public void setTaxAmountTotal(BigDecimal taxAmountTotal) {
        this.taxAmountTotal = taxAmountTotal;
    }

    public String getRedisKeyValue() {
        return redisKeyValue;
    }

    public void setRedisKeyValue(String redisKeyValue) {
        this.redisKeyValue = redisKeyValue;
    }

    public String getUpdateReason() {
        return updateReason;
    }

    public void setUpdateReason(String updateReason) {
        this.updateReason = updateReason;
    }

    public BigDecimal getQuantityTotal() {
        return quantityTotal;
    }

    public void setQuantityTotal(BigDecimal quantityTotal) {
        this.quantityTotal = quantityTotal;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SaveReveivableInvoiceVO[" +
                "id=" + id +
                ", redisKeyValue='" + redisKeyValue + '\'' +
                ", code='" + code + '\'' +
                ", billDate=" + billDate +
                ", billManId=" + billManId +
                ", billManCode='" + billManCode + '\'' +
                ", billManName='" + billManName + '\'' +
                ", purchaseUnitId=" + purchaseUnitId +
                ", purchaseUnitCode='" + purchaseUnitCode + '\'' +
                ", purchaseUnitName='" + purchaseUnitName + '\'' +
                ", remark='" + remark + '\'' +
                ", varietiesQuantity=" + varietiesQuantity +
                ", amountTotal=" + amountTotal +
                ", notaxAmountTotal=" + notaxAmountTotal +
                ", quantityTotal=" + quantityTotal +
                ", taxAmountTotal=" + taxAmountTotal +
                ", updateReason=" + updateReason +
                ", status=" + status +
                ", saveReceivableInvoiceDetailVO=" + saveReceivableInvoiceDetailVO +
                ", saveReceivableInvoiceInfoVO=" + saveReceivableInvoiceInfoVO +
                ']';
    }
}
