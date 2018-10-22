package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import com.rograndec.feijiayun.chain.business.distr.parent.entity.*;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class SaveDistrOutShelfVO implements Serializable {

    @ApiModelProperty(value = "货品ID", required = true)
    private Long goodsId;

    @ApiModelProperty(value = "批号ID", required = true)
    private Long lotId;

    @ApiModelProperty(value = "批号", required = true)
    private String lotNum;

    @ApiModelProperty(value = "货位ID", required = true)
    private Long shelfId;

    @ApiModelProperty(value = "货位状态", required = true)
    private String shelfStatusDesc;

    @ApiModelProperty(value = "货位", required = true)
    private String shelfName;

    @ApiModelProperty(value = "数量", required = true)
    private BigDecimal quantity;

    @ApiModelProperty(value = "行号", required = true)
    private Integer lineNum;

    @ApiModelProperty(value = "单价", required = true)
    private BigDecimal unitPrice;

    @ApiModelProperty(value = "折扣", required = true)
    private BigDecimal lineDiscount;

    @ApiModelProperty(value = "税率ID", required = true)
    private Long taxRateId;

    @ApiModelProperty(value = "税率",required = true)
    private BigDecimal taxRate;

    @ApiModelProperty(value = "整单折扣（%）", required = true)
    private BigDecimal wholeDiscount;

    @ApiModelProperty(value = "备注", required = true)
    private String remark;

    private Date validDate;

    private Date validUntil;

    private Date productDate;

    public static List<SaveDistrOutShelfVO> getSaveDistrOutShelfVO(DistrReturnInDetail distrReturnInDetail, List<DistrReturnInShelf> distrReturnInShelves, Map<String, Long> goodsLotNumMap){


        List<SaveDistrOutShelfVO> saveDistrOutShelfVOS = new ArrayList<>();

        int i = 0;
        for(DistrReturnInShelf distrReturnInShelf : distrReturnInShelves){

            if(distrReturnInShelf.getDtlId().equals(distrReturnInDetail.getId())){
                i++;

                SaveDistrOutShelfVO saveDistrOutShelfVO = new SaveDistrOutShelfVO();

                saveDistrOutShelfVO.setGoodsId(distrReturnInShelf.getGoodsId());

                String key = distrReturnInShelf.getGoodsId()+"_"+distrReturnInShelf.getLotNumber();
                Long lotId = goodsLotNumMap.get(key);

                saveDistrOutShelfVO.setLotId(lotId);

                saveDistrOutShelfVO.setLotNum(distrReturnInShelf.getLotNumber());

                saveDistrOutShelfVO.setShelfId(distrReturnInShelf.getShelfId());

                saveDistrOutShelfVO.setShelfStatusDesc(distrReturnInShelf.getShelfStatusDesc());

                saveDistrOutShelfVO.setShelfName(distrReturnInShelf.getShelfName());

                saveDistrOutShelfVO.setQuantity(distrReturnInShelf.getQuantity());

                saveDistrOutShelfVO.setLineNum(i);

                saveDistrOutShelfVO.setUnitPrice(distrReturnInShelf.getUnitPrice());

                saveDistrOutShelfVO.setLineDiscount(distrReturnInShelf.getLineDiscount());

                saveDistrOutShelfVO.setTaxRateId(distrReturnInShelf.getTaxRateId());

                saveDistrOutShelfVO.setTaxRate(distrReturnInShelf.getTaxRate());

                saveDistrOutShelfVO.setWholeDiscount(distrReturnInShelf.getWholeDiscount());
                saveDistrOutShelfVO.setValidDate(distrReturnInShelf.getValidDate());

                saveDistrOutShelfVO.setValidUntil(distrReturnInShelf.getValidDate());
                saveDistrOutShelfVO.setProductDate(distrReturnInShelf.getProductDate());

                saveDistrOutShelfVOS.add(saveDistrOutShelfVO);
            }

        }

        return saveDistrOutShelfVOS;

    }

    public static List<SaveDistrOutShelfVO> getSaveDistrOutShelfVO(PickDetail pickDetail,DistrSendDetail distrSendDetail){

        List<PickShelf> pickShelves = pickDetail.getPickShelves();

        List<SaveDistrOutShelfVO> saveDistrOutShelfVOS = new ArrayList<>();

        int i = 0;
        for(PickShelf pickShelf : pickShelves){

            i++;

            SaveDistrOutShelfVO saveDistrOutShelfVO = new SaveDistrOutShelfVO();

            saveDistrOutShelfVO.setGoodsId(pickShelf.getGoodsId());

            saveDistrOutShelfVO.setLotId(pickShelf.getLotId());

            saveDistrOutShelfVO.setLotNum(pickShelf.getLotNumber());

            saveDistrOutShelfVO.setShelfId(pickShelf.getShelfId());

            saveDistrOutShelfVO.setShelfStatusDesc(pickShelf.getShelfStatusDesc());

            saveDistrOutShelfVO.setShelfName(pickShelf.getShelfName());

            saveDistrOutShelfVO.setQuantity(pickShelf.getQuantity());

            saveDistrOutShelfVO.setLineNum(i);

            saveDistrOutShelfVO.setUnitPrice(distrSendDetail.getUnitPrice());

            saveDistrOutShelfVO.setLineDiscount(distrSendDetail.getLineDiscount());

            saveDistrOutShelfVO.setTaxRateId(distrSendDetail.getTaxRateId());

            saveDistrOutShelfVO.setTaxRate(distrSendDetail.getTaxRate());

            saveDistrOutShelfVO.setWholeDiscount(distrSendDetail.getWholeDiscount());
            saveDistrOutShelfVO.setValidDate(pickShelf.getValidDate());

            saveDistrOutShelfVO.setValidUntil(pickShelf.getValidDate());
            saveDistrOutShelfVO.setProductDate(pickShelf.getProductDate());

            saveDistrOutShelfVOS.add(saveDistrOutShelfVO);
        }

        return saveDistrOutShelfVOS;

    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getLotId() {
        return lotId;
    }

    public void setLotId(Long lotId) {
        this.lotId = lotId;
    }

    public Long getShelfId() {
        return shelfId;
    }

    public void setShelfId(Long shelfId) {
        this.shelfId = shelfId;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
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

    public BigDecimal getWholeDiscount() {
        return wholeDiscount;
    }

    public void setWholeDiscount(BigDecimal wholeDiscount) {
        this.wholeDiscount = wholeDiscount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getShelfStatusDesc() {
        return shelfStatusDesc;
    }

    public void setShelfStatusDesc(String shelfStatusDesc) {
        this.shelfStatusDesc = shelfStatusDesc;
    }

    public String getShelfName() {
        return shelfName;
    }

    public void setShelfName(String shelfName) {
        this.shelfName = shelfName;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public String getLotNum() {
        return lotNum;
    }

    public void setLotNum(String lotNum) {
        this.lotNum = lotNum;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

    public Date getProductDate() {
        return productDate;
    }

    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    @Override
    public String toString() {
        return "SaveDistrOutShelfVO[" +
                "goodsId=" + goodsId +
                ", lotId=" + lotId +
                ", lotNum='" + lotNum + '\'' +
                ", shelfId=" + shelfId +
                ", shelfStatusDesc='" + shelfStatusDesc + '\'' +
                ", shelfName='" + shelfName + '\'' +
                ", quantity=" + quantity +
                ", lineNum=" + lineNum +
                ", unitPrice=" + unitPrice +
                ", lineDiscount=" + lineDiscount +
                ", taxRateId=" + taxRateId +
                ", taxRate=" + taxRate +
                ", wholeDiscount=" + wholeDiscount +
                ", remark='" + remark + '\'' +
                ", validDate=" + validDate +
                ", validUntil=" + validUntil +
                ", productDate=" + productDate +
                ']';
    }
}
