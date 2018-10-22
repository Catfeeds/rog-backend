package com.rograndec.feijiayun.chain.business.finance.receivableinvoice.receivablevoucher.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class SaveReveivableVoucherVO implements Serializable {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID(更新时必传字段)")
    private Long id;

    /**
     * 应收贷项凭证缓存RedisKeyValue
     */
    @ApiModelProperty(value = "应收贷项凭证缓存RedisKeyValue(调用缓存时必填字段)")
    private String redisKeyValue;

    /**
     * 单据编码
     */
    @ApiModelProperty(value = "单据编码")
    private String code;

    /**
     * 开票日期
     */
    @ApiModelProperty(value = "过账日期(新增必传)")
    private Date postDate;

    /**
     * 开票人员ID
     */
    @ApiModelProperty(value = "过账人员ID(新增必传)")
    private Long postManId;

    /**
     * 开票人员编码
     */
    @ApiModelProperty(value = "过账人员编码")
    private String postManCode;

    /**
     * 开票人员名称
     */
    @ApiModelProperty(value = "过账人员名称")
    private String postManName;

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
     * 状态（0-待付款；1-部分付款；2-已付款；3-已冲销）
     */
    @ApiModelProperty(value = "状态（0-待付款；1-部分付款；2-已付款；3-已冲销）")
    private Integer status;

    List<SaveReceivableVoucherDetailVO> saveReceivableVoucherDetailVO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRedisKeyValue() {
        return redisKeyValue;
    }

    public void setRedisKeyValue(String redisKeyValue) {
        this.redisKeyValue = redisKeyValue;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public Long getPostManId() {
        return postManId;
    }

    public void setPostManId(Long postManId) {
        this.postManId = postManId;
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

    public String getUpdateReason() {
        return updateReason;
    }

    public void setUpdateReason(String updateReason) {
        this.updateReason = updateReason;
    }

    public List<SaveReceivableVoucherDetailVO> getSaveReceivableVoucherDetailVO() {
        return saveReceivableVoucherDetailVO;
    }

    public void setSaveReceivableVoucherDetailVO(List<SaveReceivableVoucherDetailVO> saveReceivableVoucherDetailVO) {
        this.saveReceivableVoucherDetailVO = saveReceivableVoucherDetailVO;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getQuantityTotal() {
        return quantityTotal;
    }

    public void setQuantityTotal(BigDecimal quantityTotal) {
        this.quantityTotal = quantityTotal;
    }

    @Override
    public String toString() {
        return "SaveReveivableVoucherVO[" +
                "id=" + id +
                ", redisKeyValue='" + redisKeyValue + '\'' +
                ", code='" + code + '\'' +
                ", postDate=" + postDate +
                ", postManId=" + postManId +
                ", postManCode='" + postManCode + '\'' +
                ", postManName='" + postManName + '\'' +
                ", purchaseUnitId=" + purchaseUnitId +
                ", purchaseUnitCode='" + purchaseUnitCode + '\'' +
                ", purchaseUnitName='" + purchaseUnitName + '\'' +
                ", remark='" + remark + '\'' +
                ", varietiesQuantity=" + varietiesQuantity +
                ", amountTotal=" + amountTotal +
                ", notaxAmountTotal=" + notaxAmountTotal +
                ", taxAmountTotal=" + taxAmountTotal +
                ", updateReason='" + updateReason + '\'' +
                ", status='" + status + '\'' +
                ", quantityTotal='" + quantityTotal + '\'' +
                ", saveReceivableVoucherDetailVO=" + saveReceivableVoucherDetailVO +
                ']';
    }
}
