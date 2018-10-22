package com.rograndec.feijiayun.chain.business.purchase.check.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zeshi.sun on 2017/9/19.
 */
public class SaveCheckDetailVO implements Serializable {

    /**
     * 基础单据明细ID
     */
    @ApiModelProperty(value = "基础单据明细ID", required = true)
    private Long baseOrderDtlId;

    /**
     * 基础单据ID
     */
    @ApiModelProperty(value = "基础单据ID", required = true)
    private Long baseOrderId;


    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID", required = true)
    private Long goodsId;


    /**
     * 收货数量
     */
    @ApiModelProperty(value = "收货数量", required = true)
    private BigDecimal quantity;

    /**
     * 验收合格数量
     */
    @ApiModelProperty(value = "验收合格数量", required = true)
    private BigDecimal qualifiedQuantity;

    /**
     * 验收不合格数量
     */
    @ApiModelProperty(value = "验收不合格数量", required = true)
    private BigDecimal unqualifiedQuantity;

//    /**
//     * 未清数量
//     */
//    @ApiModelProperty(value = "未清数量", required = true)
//    private BigDecimal unclearQuantity;
//
//    /**
//     * 已清数量
//     */
//    @ApiModelProperty(value = "已清数量", required = true)
//    private BigDecimal clearQuantity;

    /**
     * 行号
     */
    @ApiModelProperty(value = "行号", required = true)
    private Integer lineNum;


    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", required = true)
    private String remark;



    private List<SaveCheckDetailTwoVO> saveCheckDetailTwoVO;

    public Long getBaseOrderDtlId() {
        return baseOrderDtlId;
    }

    public void setBaseOrderDtlId(Long baseOrderDtlId) {
        this.baseOrderDtlId = baseOrderDtlId;
    }

    public Long getBaseOrderId() {
        return baseOrderId;
    }

    public void setBaseOrderId(Long baseOrderId) {
        this.baseOrderId = baseOrderId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getQualifiedQuantity() {
        return qualifiedQuantity;
    }

    public void setQualifiedQuantity(BigDecimal qualifiedQuantity) {
        this.qualifiedQuantity = qualifiedQuantity;
    }

    public BigDecimal getUnqualifiedQuantity() {
        return unqualifiedQuantity;
    }

    public void setUnqualifiedQuantity(BigDecimal unqualifiedQuantity) {
        this.unqualifiedQuantity = unqualifiedQuantity;
    }

//    public BigDecimal getUnclearQuantity() {
//        return unclearQuantity;
//    }
//
//    public void setUnclearQuantity(BigDecimal unclearQuantity) {
//        this.unclearQuantity = unclearQuantity;
//    }
//
//    public BigDecimal getClearQuantity() {
//        return clearQuantity;
//    }
//
//    public void setClearQuantity(BigDecimal clearQuantity) {
//        this.clearQuantity = clearQuantity;
//    }

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<SaveCheckDetailTwoVO> getSaveCheckDetailTwoVO() {
        return saveCheckDetailTwoVO;
    }

    public void setSaveCheckDetailTwoVO(List<SaveCheckDetailTwoVO> saveCheckDetailTwoVO) {
        this.saveCheckDetailTwoVO = saveCheckDetailTwoVO;
    }

    @Override
    public String toString() {
        return "SaveCheckDetailVO[" +
                "baseOrderDtlId=" + baseOrderDtlId +
                ", baseOrderId=" + baseOrderId +
                ", goodsId=" + goodsId +
                ", quantity=" + quantity +
                ", qualifiedQuantity=" + qualifiedQuantity +
                ", unqualifiedQuantity=" + unqualifiedQuantity +
//                ", unclearQuantity=" + unclearQuantity +
//                ", clearQuantity=" + clearQuantity +
                ", lineNum=" + lineNum +
                ", remark='" + remark + '\'' +
                ", saveCheckDetailTwoVO=" + saveCheckDetailTwoVO +
                ']';
    }
}
