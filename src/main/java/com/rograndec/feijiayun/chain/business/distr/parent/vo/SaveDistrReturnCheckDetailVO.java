package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class SaveDistrReturnCheckDetailVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 配进收货明细ID
     */
    @ApiModelProperty(value = "配进收货明细ID", required = true)
    private Long distrReturnReceiveDetailId;

    /**
     * 货品ID
     */
    @ApiModelProperty(value = "货品ID")
    private Long goodsId;

    /**
     * 是否为特殊管理药品：（0-是  1-否）
     */
    @ApiModelProperty(value = "是否为特殊管理药品：（0-是  1-否）", required = true)
    private Integer specialDrug;

    /**
     * 收货数量
     */
    @ApiModelProperty(value = "收货数量", required = true)
    private BigDecimal receiveQuantity;

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

    /**
     * 行号
     */
    @ApiModelProperty(value = "行号", required = true)
    private Integer lineNum;

    List<SaveDistrReturnCheckDetailOneVO> saveDistrReturnCheckDetailOneVO;

    public Long getDistrReturnReceiveDetailId() {
        return distrReturnReceiveDetailId;
    }

    public void setDistrReturnReceiveDetailId(Long distrReturnReceiveDetailId) {
        this.distrReturnReceiveDetailId = distrReturnReceiveDetailId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public BigDecimal getReceiveQuantity() {
        return receiveQuantity;
    }

    public void setReceiveQuantity(BigDecimal receiveQuantity) {
        this.receiveQuantity = receiveQuantity;
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

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }

    public Integer getSpecialDrug() {
        return specialDrug;
    }

    public void setSpecialDrug(Integer specialDrug) {
        this.specialDrug = specialDrug;
    }

    public void setUnqualifiedQuantity(BigDecimal unqualifiedQuantity) {
        this.unqualifiedQuantity = unqualifiedQuantity;
    }

    public List<SaveDistrReturnCheckDetailOneVO> getSaveDistrReturnCheckDetailOneVO() {
        return saveDistrReturnCheckDetailOneVO;
    }

    public void setSaveDistrReturnCheckDetailOneVO(List<SaveDistrReturnCheckDetailOneVO> saveDistrReturnCheckDetailOneVO) {
        this.saveDistrReturnCheckDetailOneVO = saveDistrReturnCheckDetailOneVO;
    }

    @Override
    public String toString() {
        return "SaveDistrReturnCheckDetailVO[" +
                "goodsId=" + goodsId +
                ", distrReturnReceiveDetailId=" + distrReturnReceiveDetailId +
                ", receiveQuantity=" + receiveQuantity +
                ", specialDrug=" + specialDrug +
                ", lineNum=" + lineNum +
                ", qualifiedQuantity=" + qualifiedQuantity +
                ", unqualifiedQuantity=" + unqualifiedQuantity +
                ", saveDistrReturnCheckDetailOneVO=" + saveDistrReturnCheckDetailOneVO +
                ']';
    }
}
