package com.rograndec.feijiayun.chain.business.purchase.receive.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PurchaseReceiveReturnVO implements Serializable {

    /**
     * 收货日期
     */
    @ApiModelProperty(required = true, value = "收货日期")
    private Date receiveDate;

    /**
     * 基础单据ID
     */
    @ApiModelProperty(required = true, value = "基础单据ID")
    private Long baseOrderId;

    /**
     * 收货人员ID
     */
    @ApiModelProperty(required = true, value = "收货人员ID")
    private Long receiverId;

    /**
     * 收货人2ID
     */
    @ApiModelProperty(required = true, value = "收货人2ID")
    private Long secondReceiverId;

    /**
     * 数量合计
     */
    @ApiModelProperty(required = true, value = "数量合计")
    private BigDecimal quantityTotal;

    /**
     * 品种数量
     */
    @ApiModelProperty(required = true, value = "品种数量")
    private Integer varietiesQuantity;

    /**
     * 备注
     */
    @ApiModelProperty(required = true, value = "备注")
    private String remark;

    /**
     * saas_purchase_receive
     */
    private static final long serialVersionUID = 1L;

    /**
     * 收货日期
     * @return receive_date 收货日期
     */
    public Date getReceiveDate() {
        return receiveDate;
    }

    /**
     * 收货日期
     * @param receiveDate 收货日期
     */
    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    /**
     * 基础单据ID
     * @return base_order_id 基础单据ID
     */
    public Long getBaseOrderId() {
        return baseOrderId;
    }

    /**
     * 基础单据ID
     * @param baseOrderId 基础单据ID
     */
    public void setBaseOrderId(Long baseOrderId) {
        this.baseOrderId = baseOrderId;
    }

    /**
     * 收货人员ID
     * @return receiver_id 收货人员ID
     */
    public Long getReceiverId() {
        return receiverId;
    }

    /**
     * 收货人员ID
     * @param receiverId 收货人员ID
     */
    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    /**
     * 收货人2ID
     * @return second_receiver_id 收货人2ID
     */
    public Long getSecondReceiverId() {
        return secondReceiverId;
    }

    /**
     * 收货人2ID
     * @param secondReceiverId 收货人2ID
     */
    public void setSecondReceiverId(Long secondReceiverId) {
        this.secondReceiverId = secondReceiverId;
    }

    /**
     * 数量合计
     * @return quantity_total 数量合计
     */
    public BigDecimal getQuantityTotal() {
        return quantityTotal;
    }

    /**
     * 数量合计
     * @param quantityTotal 数量合计
     */
    public void setQuantityTotal(BigDecimal quantityTotal) {
        this.quantityTotal = quantityTotal;
    }

    /**
     * 品种数量
     * @return varieties_quantity 品种数量
     */
    public Integer getVarietiesQuantity() {
        return varietiesQuantity;
    }

    /**
     * 品种数量
     * @param varietiesQuantity 品种数量
     */
    public void setVarietiesQuantity(Integer varietiesQuantity) {
        this.varietiesQuantity = varietiesQuantity;
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
        sb.append(", receiveDate=").append(receiveDate);
        sb.append(", baseOrderId=").append(baseOrderId);
        sb.append(", receiverId=").append(receiverId);
        sb.append(", secondReceiverId=").append(secondReceiverId);
        sb.append(", quantityTotal=").append(quantityTotal);
        sb.append(", varietiesQuantity=").append(varietiesQuantity);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}