package com.rograndec.feijiayun.chain.business.distr.branch.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * saas_distr_in_receive_detail
 * 
 * 
 * @author Asze
 * 
 * 2017-10-09
 */
public class DistrInReceiveDetailSaveVO implements Serializable {

    /**
     * 基础单据明细ID
     */
    @ApiModelProperty(value = "基础单据明细ID")
    private Long baseOrderDtlId;

    /**
     * 基础单据ID
     */
    @ApiModelProperty(value = "基础单据ID")
    private Long baseOrderId;

    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID")
    private Long goodsId;

    /**
     * 到货数量
     */
    @ApiModelProperty(value = "到货数量")
    private BigDecimal arrivalQuantity;

    /**
     * 收货数量
     */
    @ApiModelProperty(value = "收货数量")
    private BigDecimal receiveQuantity;

    /**
     * 拒收数量
     */
    @ApiModelProperty(value = "拒收数量")
    private BigDecimal refuseQuantity;

    /**
     * 拒收原因ID集合，多个用逗号分隔
     */
    @ApiModelProperty(value = "拒收原因ID集合，多个用逗号分隔")
    private String refuseReasonIds;

    /**
     * 未清数量
     */
    @ApiModelProperty(value = "未清数量")
    private BigDecimal unclearQuantity;

    /**
     * 已清数量
     */
    @ApiModelProperty(value = "已清数量")
    private BigDecimal clearQuantity;

    /**
     * 行号
     */
    @ApiModelProperty(value = "行号")
    private Integer lineNum;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * saas_distr_in_receive_detail
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
     * 商品ID
     * @return goods_id 商品ID
     */
    public Long getGoodsId() {
        return goodsId;
    }

    /**
     * 商品ID
     * @param goodsId 商品ID
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
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

    /**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        return "DistrInReceiveDetailVO{" +
                ", baseOrderDtlId=" + baseOrderDtlId +
                ", baseOrderId=" + baseOrderId +
                ", goodsId=" + goodsId +
                ", arrivalQuantity=" + arrivalQuantity +
                ", receiveQuantity=" + receiveQuantity +
                ", refuseQuantity=" + refuseQuantity +
                ", refuseReasonIds='" + refuseReasonIds + '\'' +
                ", unclearQuantity=" + unclearQuantity +
                ", clearQuantity=" + clearQuantity +
                ", lineNum=" + lineNum +
                ", remark='" + remark + '\'' +
                '}';
    }
}