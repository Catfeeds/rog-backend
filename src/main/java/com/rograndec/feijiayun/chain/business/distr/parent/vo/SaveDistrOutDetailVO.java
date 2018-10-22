package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnInDetail;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnInShelf;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrSendDetail;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.PickDetail;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SaveDistrOutDetailVO implements Serializable {

    @ApiModelProperty(required = true, value = "调用单据的单据明细行ID")
    private Long callDataDtlId;

    @ApiModelProperty(value = "货品ID", required = true)
    private Long goodsId;

    @ApiModelProperty(value = "数量", required = true)
    private BigDecimal quantity;

    @ApiModelProperty(value = "单价", required = true)
    private BigDecimal unitPrice;

    @ApiModelProperty(value = "折扣", required = true)
    private BigDecimal lineDiscount;

    @ApiModelProperty(value = "税率ID", required = true)
    private Long taxRateId;

    @ApiModelProperty(value = "税率",required = true)
    private BigDecimal taxRate;

    @ApiModelProperty(value = "行号", required = true)
    private Integer lineNum;

    @ApiModelProperty(value = "备注", required = true)
    private String remark;

    @ApiModelProperty(value = "整单折扣（%）", required = true)
    private BigDecimal wholeDiscount;

    List<SaveDistrOutShelfVO> saveDistrOutShelfVOList;


    public static List<SaveDistrOutDetailVO> getSaveDistrOutDetailVOs(DistrReturnInVO distrReturnInVO){

        List<DistrReturnInDetail> distrReturnInDetails = distrReturnInVO.getDistrReturnInDetails();
        List<DistrReturnInShelf> distrReturnInShelfs = distrReturnInVO.getDistrReturnInShelfs();
        Map<String, Long> goodsLotNumMap = distrReturnInVO.getGoodsLotNumMap();

        List<SaveDistrOutDetailVO> saveDistrOutDetailVOS = new ArrayList<>();

        int i = 0;
        for(DistrReturnInDetail distrReturnInDetail : distrReturnInDetails){

            i++;
            SaveDistrOutDetailVO saveDistrOutDetailVO = new SaveDistrOutDetailVO();

            saveDistrOutDetailVO.setGoodsId(distrReturnInDetail.getGoodsId());
            saveDistrOutDetailVO.setCallDataDtlId(distrReturnInDetail.getId());

            saveDistrOutDetailVO.setQuantity(distrReturnInDetail.getQuantity());

            saveDistrOutDetailVO.setUnitPrice(distrReturnInDetail.getUnitPrice());

            saveDistrOutDetailVO.setLineDiscount(distrReturnInDetail.getLineDiscount());

            saveDistrOutDetailVO.setTaxRateId(distrReturnInDetail.getTaxRateId());

            saveDistrOutDetailVO.setTaxRate(distrReturnInDetail.getTaxRate());

            saveDistrOutDetailVO.setLineNum(i);

            saveDistrOutDetailVO.setWholeDiscount(distrReturnInDetail.getWholeDiscount());

            List<SaveDistrOutShelfVO> saveDistrOutShelfVO = SaveDistrOutShelfVO.getSaveDistrOutShelfVO(distrReturnInDetail, distrReturnInShelfs,goodsLotNumMap);
            saveDistrOutDetailVO.setSaveDistrOutShelfVOList(saveDistrOutShelfVO);

            saveDistrOutDetailVOS.add(saveDistrOutDetailVO);
        }

        return saveDistrOutDetailVOS;
    }

    public static List<SaveDistrOutDetailVO> getSaveDistrOutDetailVOs(List<PickDetail> pickDetails,List<DistrSendDetail> distrSendDetailList){

        List<SaveDistrOutDetailVO> saveDistrOutDetailVOS = new ArrayList<>();

        int i = 0;
        for(DistrSendDetail distrSendDetail : distrSendDetailList){

            i++;
            SaveDistrOutDetailVO saveDistrOutDetailVO = new SaveDistrOutDetailVO();

            saveDistrOutDetailVO.setGoodsId(distrSendDetail.getGoodsId());

            saveDistrOutDetailVO.setQuantity(distrSendDetail.getQuantity());

            saveDistrOutDetailVO.setUnitPrice(distrSendDetail.getUnitPrice());

            saveDistrOutDetailVO.setLineDiscount(distrSendDetail.getLineDiscount());

            saveDistrOutDetailVO.setTaxRateId(distrSendDetail.getTaxRateId());

            saveDistrOutDetailVO.setTaxRate(distrSendDetail.getTaxRate());

            saveDistrOutDetailVO.setLineNum(i);

            saveDistrOutDetailVO.setWholeDiscount(distrSendDetail.getWholeDiscount());

            for(PickDetail pickDetail : pickDetails){

                if(pickDetail.getGoodsId().equals(distrSendDetail.getGoodsId())){
                    List<SaveDistrOutShelfVO> saveDistrOutShelfVO = SaveDistrOutShelfVO.getSaveDistrOutShelfVO(pickDetail, distrSendDetail);
                    saveDistrOutDetailVO.setSaveDistrOutShelfVOList(saveDistrOutShelfVO);
                }

            }

            saveDistrOutDetailVOS.add(saveDistrOutDetailVO);
        }

        return saveDistrOutDetailVOS;
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

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getLineDiscount() {
        return lineDiscount;
    }

    public void setLineDiscount(BigDecimal lineDiscount) {
        this.lineDiscount = lineDiscount;
    }

    public Long getTaxRateId() {
        return taxRateId;
    }

    public void setTaxRateId(Long taxRateId) {
        this.taxRateId = taxRateId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getWholeDiscount() {
        return wholeDiscount;
    }

    public void setWholeDiscount(BigDecimal wholeDiscount) {
        this.wholeDiscount = wholeDiscount;
    }

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public List<SaveDistrOutShelfVO> getSaveDistrOutShelfVOList() {
        return saveDistrOutShelfVOList;
    }

    public void setSaveDistrOutShelfVOList(List<SaveDistrOutShelfVO> saveDistrOutShelfVOList) {
        this.saveDistrOutShelfVOList = saveDistrOutShelfVOList;
    }

    public Long getCallDataDtlId() {
        return callDataDtlId;
    }

    public void setCallDataDtlId(Long callDataDtlId) {
        this.callDataDtlId = callDataDtlId;
    }

    @Override
    public String toString() {
        return "SaveDistrOutDetailVO[" +
                "goodsId=" + goodsId +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", lineDiscount=" + lineDiscount +
                ", taxRateId=" + taxRateId +
                ", taxRate='" + taxRate + '\'' +
                ", remark='" + remark + '\'' +
                ", lineNum='" + lineNum + '\'' +
                ", wholeDiscount=" + wholeDiscount +
                ", saveDistrOutShelfVOList=" + saveDistrOutShelfVOList +
                ']';
    }
}
