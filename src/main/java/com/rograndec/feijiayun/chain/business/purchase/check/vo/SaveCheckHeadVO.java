package com.rograndec.feijiayun.chain.business.purchase.check.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zeshi.sun on 2017/9/19.
 */
public class SaveCheckHeadVO implements Serializable {

    /**
     * 调用草稿返回的redisID
     */
    @ApiModelProperty(value = "调用草稿返回的redisID", required = true)
    private String redisKeyValue;

    /**
     * 验收日期
     */
    @ApiModelProperty(value = "验收日期", required = true)
    private Date checkDate;

    /**
     * 基础单据ID
     */
    @ApiModelProperty(value = "基础单据ID", required = true)
    private Long baseOrderId;


    /**
     * 供货单位ID
     */
    @ApiModelProperty(value = "供货单位ID", required = true)
    private Long supplierId;


    /**
     * 验收人员ID
     */
    @ApiModelProperty(value = "验收人员ID", required = true)
    private Long checkerId;


    /**
     * 验收人员2ID
     */
    @ApiModelProperty(value = "验收人员2ID", required = true)
    private Long secondCheckerId;



    /**
     * 数量合计
     */
    @ApiModelProperty(value = "数量合计", required = true)
    private BigDecimal quantityTotal;

    /**
     * 品种数量
     */
    @ApiModelProperty(value = "品种数量", required = true)
    private Integer varietiesQuantity;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", required = true)
    private String remark;

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public Long getBaseOrderId() {
        return baseOrderId;
    }

    public void setBaseOrderId(Long baseOrderId) {
        this.baseOrderId = baseOrderId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getCheckerId() {
        return checkerId;
    }

    public void setCheckerId(Long checkerId) {
        this.checkerId = checkerId;
    }

    public Long getSecondCheckerId() {
        return secondCheckerId;
    }

    public void setSecondCheckerId(Long secondCheckerId) {
        this.secondCheckerId = secondCheckerId;
    }

    public BigDecimal getQuantityTotal() {
        return quantityTotal;
    }

    public void setQuantityTotal(BigDecimal quantityTotal) {
        this.quantityTotal = quantityTotal;
    }

    public Integer getVarietiesQuantity() {
        return varietiesQuantity;
    }

    public void setVarietiesQuantity(Integer varietiesQuantity) {
        this.varietiesQuantity = varietiesQuantity;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRedisKeyValue() {
        return redisKeyValue;
    }

    public void setRedisKeyValue(String redisKeyValue) {
        this.redisKeyValue = redisKeyValue;
    }

    @Override
    public String toString() {
        return "SaveCheckHeadVO[" +
                "checkDate=" + checkDate +
                ", redisKeyValue=" + redisKeyValue +
                ", baseOrderId=" + baseOrderId +
                ", supplierId=" + supplierId +
                ", checkerId=" + checkerId +
                ", secondCheckerId=" + secondCheckerId +
                ", quantityTotal=" + quantityTotal +
                ", varietiesQuantity=" + varietiesQuantity +
                ", remark='" + remark + '\'' +
                ']';
    }
}
