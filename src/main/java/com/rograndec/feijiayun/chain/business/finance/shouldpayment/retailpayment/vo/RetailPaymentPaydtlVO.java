package com.rograndec.feijiayun.chain.business.finance.shouldpayment.retailpayment.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * saas_retail_payment_paydtl
 * 
 * 
 * @author lizhongyi
 * 
 * 2018-01-12
 */
public class RetailPaymentPaydtlVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 缴款明细ID
     */
    @ApiModelProperty(value = "缴款明细ID")
    private Long dtlId;

    /**
     * 缴款清单ID
     */
    @ApiModelProperty(value = "缴款清单ID")
    private Long itemId;

    /**
     * 缴款单ID
     */
    @ApiModelProperty(value = "缴款单ID")
    private Long docId;

    /**
     * 支付方式ID
     */
    @ApiModelProperty(value = "支付方式ID")
    private Long payTypeId;

    /**
     * 支付方式名称
     */
    @ApiModelProperty(value = "支付方式名称")
    private String payTypeName;

    /**
     * 支付金额
     */
    @ApiModelProperty(value = "支付金额")
    private BigDecimal payAmount;

    /**
     * 状态（0-待缴款；1-已缴款；2-已冲销）
     */
    @ApiModelProperty(value = "状态（0-待缴款；1-已缴款；2-已冲销）")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * saas_retail_payment_paydtl
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     * @return id 主键ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键ID
     * @param id 主键ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 缴款明细ID
     * @return dtl_id 缴款明细ID
     */
    public Long getDtlId() {
        return dtlId;
    }

    /**
     * 缴款明细ID
     * @param dtlId 缴款明细ID
     */
    public void setDtlId(Long dtlId) {
        this.dtlId = dtlId;
    }

    /**
     * 缴款清单ID
     * @return item_id 缴款清单ID
     */
    public Long getItemId() {
        return itemId;
    }

    /**
     * 缴款清单ID
     * @param itemId 缴款清单ID
     */
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    /**
     * 缴款单ID
     * @return doc_id 缴款单ID
     */
    public Long getDocId() {
        return docId;
    }

    /**
     * 缴款单ID
     * @param docId 缴款单ID
     */
    public void setDocId(Long docId) {
        this.docId = docId;
    }

    /**
     * 支付方式ID
     * @return pay_type_id 支付方式ID
     */
    public Long getPayTypeId() {
        return payTypeId;
    }

    /**
     * 支付方式ID
     * @param payTypeId 支付方式ID
     */
    public void setPayTypeId(Long payTypeId) {
        this.payTypeId = payTypeId;
    }

    /**
     * 支付方式名称
     * @return pay_type_name 支付方式名称
     */
    public String getPayTypeName() {
        return payTypeName;
    }

    /**
     * 支付方式名称
     * @param payTypeName 支付方式名称
     */
    public void setPayTypeName(String payTypeName) {
        this.payTypeName = payTypeName == null ? null : payTypeName.trim();
    }

    /**
     * 支付金额
     * @return pay_amount 支付金额
     */
    public BigDecimal getPayAmount() {
        return payAmount;
    }

    /**
     * 支付金额
     * @param payAmount 支付金额
     */
    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    /**
     * 状态（0-待缴款；1-已缴款；2-已冲销）
     * @return status 状态（0-待缴款；1-已缴款；2-已冲销）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态（0-待缴款；1-已缴款；2-已冲销）
     * @param status 状态（0-待缴款；1-已缴款；2-已冲销）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 备注
     * @return remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", dtlId=").append(dtlId);
        sb.append(", itemId=").append(itemId);
        sb.append(", docId=").append(docId);
        sb.append(", payTypeId=").append(payTypeId);
        sb.append(", payTypeName=").append(payTypeName);
        sb.append(", payAmount=").append(payAmount);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}