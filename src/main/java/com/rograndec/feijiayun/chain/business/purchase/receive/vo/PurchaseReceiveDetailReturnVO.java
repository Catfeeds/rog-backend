package com.rograndec.feijiayun.chain.business.purchase.receive.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PurchaseReceiveDetailReturnVO implements Serializable {

    /**
     * 基础单据明细ID
     */
    @ApiModelProperty(required = true, value = "基础单据明细ID")
    private Long baseOrderDtlId;

    /**
     * 基础单据ID
     */
    @ApiModelProperty(required = true, value = "基础单据ID")
    private Long baseOrderId;

    /**
     * 商品ID
     */
    @ApiModelProperty(required = true, value = "商品ID")
    private Long goodsId;

    /**
     * 订单数量
     */
    @ApiModelProperty(required = true, value = "订单数量")
    private BigDecimal orderQuantity;

    /**
     * 到货数量
     */
    @ApiModelProperty(required = true, value = "到货数量")
    private BigDecimal arrivalQuantity;

    /**
     * 收货数量
     */
    @ApiModelProperty(required = true, value = "收货数量")
    private BigDecimal receiveQuantity;

    /**
     * 拒收数量
     */
    @ApiModelProperty(required = true, value = "拒收数量")
    private BigDecimal refuseQuantity;

    /**
     * 拒收原因ID集合，多个用逗号分隔
     */
    @ApiModelProperty(required = true, value = "拒收原因ID集合，多个用逗号分隔")
    private String refuseReasonIds;

    /**
     * 未清数量
     */
    @ApiModelProperty(required = true, value = "未清数量")
    private BigDecimal unclearQuantity;

    /**
     * 已清数量
     */
    @ApiModelProperty(required = true, value = "已清数量")
    private BigDecimal clearQuantity;

    /**
     * 行号
     */
    @ApiModelProperty(required = true, value = "行号")
    private Integer lineNum;

    /**
     * 备注
     */
    @ApiModelProperty(required = true, value = "备注")
    private String remark;

    /**
     * saas_purchase_receive_detail
     */
    private static final long serialVersionUID = 1L;

    /**
     * 基础单据明细ID
     * @return base_order_dtl_id 基础单据明细ID
     */
    public Long getBaseOrderDtlId() {
        return baseOrderDtlId;
    }

    /**
     * 基础单据明细ID
     * @param baseOrderDtlId 基础单据明细ID
     */
    public void setBaseOrderDtlId(Long baseOrderDtlId) {
        this.baseOrderDtlId = baseOrderDtlId;
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
     * 订单数量
     * @return order_quantity 订单数量
     */
    public BigDecimal getOrderQuantity() {
        return orderQuantity;
    }

    /**
     * 订单数量
     * @param orderQuantity 订单数量
     */
    public void setOrderQuantity(BigDecimal orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    /**
     * 到货数量
     * @return arrival_quantity 到货数量
     */
    public BigDecimal getArrivalQuantity() {
        return arrivalQuantity;
    }

    /**
     * 到货数量
     * @param arrivalQuantity 到货数量
     */
    public void setArrivalQuantity(BigDecimal arrivalQuantity) {
        this.arrivalQuantity = arrivalQuantity;
    }

    /**
     * 收货数量
     * @return receive_quantity 收货数量
     */
    public BigDecimal getReceiveQuantity() {
        return receiveQuantity;
    }

    /**
     * 收货数量
     * @param receiveQuantity 收货数量
     */
    public void setReceiveQuantity(BigDecimal receiveQuantity) {
        this.receiveQuantity = receiveQuantity;
    }

    /**
     * 拒收数量
     * @return refuse_quantity 拒收数量
     */
    public BigDecimal getRefuseQuantity() {
        return refuseQuantity;
    }

    /**
     * 拒收数量
     * @param refuseQuantity 拒收数量
     */
    public void setRefuseQuantity(BigDecimal refuseQuantity) {
        this.refuseQuantity = refuseQuantity;
    }

    /**
     * 拒收原因ID集合，多个用逗号分隔
     * @return refuse_reason_ids 拒收原因ID集合，多个用逗号分隔
     */
    public String getRefuseReasonIds() {
        return refuseReasonIds;
    }

    /**
     * 拒收原因ID集合，多个用逗号分隔
     * @param refuseReasonIds 拒收原因ID集合，多个用逗号分隔
     */
    public void setRefuseReasonIds(String refuseReasonIds) {
        this.refuseReasonIds = refuseReasonIds == null ? null : refuseReasonIds.trim();
    }

    /**
     * 未清数量
     * @return unclear_quantity 未清数量
     */
    public BigDecimal getUnclearQuantity() {
        return unclearQuantity;
    }

    /**
     * 未清数量
     * @param unclearQuantity 未清数量
     */
    public void setUnclearQuantity(BigDecimal unclearQuantity) {
        this.unclearQuantity = unclearQuantity;
    }

    /**
     * 已清数量
     * @return clear_quantity 已清数量
     */
    public BigDecimal getClearQuantity() {
        return clearQuantity;
    }

    /**
     * 已清数量
     * @param clearQuantity 已清数量
     */
    public void setClearQuantity(BigDecimal clearQuantity) {
        this.clearQuantity = clearQuantity;
    }

    /**
     * 行号
     * @return line_num 行号
     */
    public Integer getLineNum() {
        return lineNum;
    }

    /**
     * 行号
     * @param lineNum 行号
     */
    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
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

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
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
        sb.append(", baseOrderDtlId=").append(baseOrderDtlId);
        sb.append(", baseOrderId=").append(baseOrderId);
        sb.append(", orderQuantity=").append(orderQuantity);
        sb.append(", arrivalQuantity=").append(arrivalQuantity);
        sb.append(", receiveQuantity=").append(receiveQuantity);
        sb.append(", refuseQuantity=").append(refuseQuantity);
        sb.append(", refuseReasonIds=").append(refuseReasonIds);
        sb.append(", unclearQuantity=").append(unclearQuantity);
        sb.append(", clearQuantity=").append(clearQuantity);
        sb.append(", lineNum=").append(lineNum);
        sb.append(", remark=").append(remark);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}