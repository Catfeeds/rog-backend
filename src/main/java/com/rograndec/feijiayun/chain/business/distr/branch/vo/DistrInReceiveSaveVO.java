package com.rograndec.feijiayun.chain.business.distr.branch.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
 * saas_distr_in_receive
 * 
 * 
 * @author Asze
 * 
 * 2017-10-09
 */
public class DistrInReceiveSaveVO implements Serializable {

    /**
     * 配进收货日期
     */
    @ApiModelProperty(value = "配进收货日期")
    private Date receiveDate;

    /**
     * 基础单据ID
     */
    @ApiModelProperty(value = "基础单据ID")
    private Long baseOrderId;

    /**
     * 收货人员ID
     */
    @ApiModelProperty(value = "收货人员ID")
    private Long receiverId;

    /**
     * 第二收货人员ID
     */
    @ApiModelProperty(value = "第二收货人员ID")
    private Long secondReceiverId;

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
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 细单集合
     */
    @ApiModelProperty(value = "细单集合")
    private List<DistrInReceiveDetailSaveVO> distrInReceiveDetailSaveVOS;

    /**
     * saas_distr_in_receive
     */
    private static final long serialVersionUID = 1L;

    /**
     * 配进收货日期
     * @return receive_date 配进收货日期
     */
    public Date getReceiveDate() {
        return receiveDate;
    }

    /**
     * 配进收货日期
     * @param receiveDate 配进收货日期
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
     * 第二收货人员ID
     * @return second_receiver_id 第二收货人员ID
     */
    public Long getSecondReceiverId() {
        return secondReceiverId;
    }

    /**
     * 第二收货人员ID
     * @param secondReceiverId 第二收货人员ID
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

    public List<DistrInReceiveDetailSaveVO> getDistrInReceiveDetailSaveVOS() {
        return distrInReceiveDetailSaveVOS;
    }

    public void setDistrInReceiveDetailSaveVOS(List<DistrInReceiveDetailSaveVO> distrInReceiveDetailSaveVOS) {
        this.distrInReceiveDetailSaveVOS = distrInReceiveDetailSaveVOS;
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
        sb.append(", distrInReceiveDetailSaveVOS=").append(distrInReceiveDetailSaveVOS);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}