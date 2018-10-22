package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnNotice;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnNoticeDetail;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * saas_distr_return_receive
 * 
 * 
 * @author Asze
 * 
 * 2017-10-08
 */
public class DistrReturnReceiveSaveVO implements Serializable {

    /**
     * 配后退回收货日期
     */
    @ApiModelProperty(value = "配后退回收货日期")
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
    private List<DistrReturnReceiveDetailSaveVO> distrReturnReceiveDetailSaveVOS;

    public static DistrReturnReceiveSaveVO getDistrReturnReceiveSaveVO(DistrReturnNoticeMapVO distrReturnNoticeMapVO){

        DistrReturnNotice distrReturnNotice = distrReturnNoticeMapVO.getDistrReturnNotice();

        DistrReturnReceiveSaveVO distrReturnReceiveSaveVO = new DistrReturnReceiveSaveVO();

        /**
         * 配后退回收货日期
         */
        distrReturnReceiveSaveVO.setReceiveDate(distrReturnNotice.getNoticeDate());

        /**
         * 基础单据ID
         */
        distrReturnReceiveSaveVO.setBaseOrderId(distrReturnNotice.getId());

        /**
         * 收货人员ID
         */
        distrReturnReceiveSaveVO.setReceiverId(distrReturnNoticeMapVO.getReceiverId());

        /**
         * 第二收货人员ID
         */
        distrReturnReceiveSaveVO.setSecondReceiverId(distrReturnNoticeMapVO.getSecondReceiverId());

        /**
         * 数量合计
         */
        distrReturnReceiveSaveVO.setQuantityTotal(distrReturnNotice.getQuantityTotal());

        /**
         * 品种数量
         */
        distrReturnReceiveSaveVO.setVarietiesQuantity(distrReturnNotice.getVarietiesQuantity());

        /**
         * 备注
         */
        distrReturnReceiveSaveVO.setRemark(distrReturnNotice.getRemark());

        List<DistrReturnNoticeDetail> distrReturnNoticeDetails = distrReturnNoticeMapVO.getDistrReturnNoticeDetails();

/*
        List<DistrReturnReceiveDetailSaveVO> distrReturnReceiveDetailSaveVOS
*/

        List<DistrReturnReceiveDetailSaveVO> collect = distrReturnNoticeDetails.stream().map(distrReturnNoticeDetail -> {

            DistrReturnReceiveDetailSaveVO distrReturnReceiveDetailSaveVO = new DistrReturnReceiveDetailSaveVO();

            /**
             * 基础单据明细ID
             */
            distrReturnReceiveDetailSaveVO.setBaseOrderDtlId(distrReturnNoticeDetail.getId());

            /**
             * 基础单据ID
             */
            distrReturnReceiveDetailSaveVO.setBaseOrderId(distrReturnNotice.getId());

            /**
             * 商品ID
             */
            distrReturnReceiveDetailSaveVO.setGoodsId(distrReturnNoticeDetail.getGoodsId());

            /**
             * 到货数量
             */
            distrReturnReceiveDetailSaveVO.setArrivalQuantity(distrReturnNoticeDetail.getQuantity());

            /**
             * 收货数量
             */
            distrReturnReceiveDetailSaveVO.setReceiveQuantity(distrReturnNoticeDetail.getQuantity());

            /**
             * 拒收数量
             */
            distrReturnReceiveDetailSaveVO.setRefuseQuantity(BigDecimal.ZERO);

            /**
             * 行号
             */
            distrReturnReceiveDetailSaveVO.setLineNum(distrReturnNoticeDetail.getLineNum());

            /**
             * 备注
             */
            distrReturnReceiveDetailSaveVO.setRemark(distrReturnNoticeDetail.getRemark());

            return distrReturnReceiveDetailSaveVO;

        }).collect(Collectors.toList());

        distrReturnReceiveSaveVO.setDistrReturnReceiveDetailSaveVOS(collect);

        return distrReturnReceiveSaveVO;
    }

    /**
     * saas_distr_return_receive
     */

    /**
     * 配后退回收货日期
     * @return receive_date 配后退回收货日期
     */
    public Date getReceiveDate() {
        return receiveDate;
    }

    /**
     * 配后退回收货日期
     * @param receiveDate 配后退回收货日期
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

    public List<DistrReturnReceiveDetailSaveVO> getDistrReturnReceiveDetailSaveVOS() {
        return distrReturnReceiveDetailSaveVOS;
    }

    public void setDistrReturnReceiveDetailSaveVOS(List<DistrReturnReceiveDetailSaveVO> distrReturnReceiveDetailSaveVOS) {
        this.distrReturnReceiveDetailSaveVOS = distrReturnReceiveDetailSaveVOS;
    }

    /**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        return "DistrReturnReceiveSaveVO{" +
                "receiveDate=" + receiveDate +
                ", baseOrderId=" + baseOrderId +
                ", receiverId=" + receiverId +
                ", secondReceiverId=" + secondReceiverId +
                ", quantityTotal=" + quantityTotal +
                ", varietiesQuantity=" + varietiesQuantity +
                ", remark='" + remark + '\'' +
                ", distrReturnReceiveDetailSaveVOS=" + distrReturnReceiveDetailSaveVOS +
                '}';
    }
}