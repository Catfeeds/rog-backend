package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInReturnOut;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInReturnOutDetail;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnNoticeDetail;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class DistrReturnNoticeDtlVO implements Serializable{


    @ApiModelProperty(value = "单号")
    private String noticeCode;
    /**
     * 基础单据明细ID
     */
    @ApiModelProperty(value = "基础单据明细ID---用于上游单据保存使用")
    private Long baseOrderDtlId;

    /**
     * 基础单据ID
     */
    @ApiModelProperty(value = "基础单据ID---用于上游单据保存使用")
    private Long baseOrderId;

    /**
     * 基础单据类型
     */
    @ApiModelProperty(value = "基础单据类型---用于上游单据保存使用")
    private Integer baseOrderType;

    /**
     * 基础单据编码
     */
    @ApiModelProperty(value = "基础单据编码---用于上游单据保存使用")
    private String baseOrderCode;

    /**
     * 基础单据日期
     */
    @ApiModelProperty(value = "基础单据日期---用于上游单据保存使用")
    private Date baseOrderDate;
    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID")
    private Long goodsId;

    /**
     * 商品编码
     */
    @ApiModelProperty(value = "商品编码")
    private String goodsCode;

    /**
     * 商品通用名称
     */
    @ApiModelProperty(value = "商品通用名称")
    private String goodsGenericName;

    /**
     * 剂型ID
     */
    @ApiModelProperty(value = "剂型ID")
    private Long dosageId;

    /**
     * 剂型名称
     */
    @ApiModelProperty(value = "剂型名称")
    private String dosageName;

    /**
     * 商品规格
     */
    @ApiModelProperty(value = "商品规格")
    private String goodsSpecification;

    /**
     * 生产厂商ID
     */
    @ApiModelProperty(value = "生产厂商ID")
    private Long manufacturerId;

    /**
     * 生产厂商
     */
    @ApiModelProperty(value = "生产厂商")
    private String manufacturer;

    /**
     * 单位ID
     */
    @ApiModelProperty(value = "单位ID")
    private Long unitId;

    /**
     * 单位名称
     */
    @ApiModelProperty(value = "单位名称")
    private String unitName;

    /**
     * 商品产地
     */
    @ApiModelProperty(value = "商品产地")
    private String goodsPlace;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private BigDecimal quantity;

    /**
     * 单价
     */
    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;

    /**
     * 行折扣（%）
     */
    @ApiModelProperty(value = "行折扣（%）")
    private BigDecimal lineDiscount;

    /**
     * 金额（整单优惠前金额）
     */
    @ApiModelProperty(value = "金额（整单优惠前金额）")
    private BigDecimal amount;

    /**
     * 税率ID
     */
    @ApiModelProperty(value = "税率ID")
    private Long taxRateId;

    /**
     * 税率
     */
    @ApiModelProperty(value = "税率")
    private BigDecimal taxRate;

    /**
     * 不含税实际单价
     */
    @ApiModelProperty(value = "不含税实际单价")
    private BigDecimal notaxRealPrice;

    /**
     * 不含税实际金额
     */
    @ApiModelProperty(value = "不含税实际金额")
    private BigDecimal notaxRealAmount;

    /**
     * 税额
     */
    @ApiModelProperty(value = "税额")
    private BigDecimal taxAmount;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 整单折扣（%）
     */
    @ApiModelProperty(value = "整单折扣（%）---用于上游单据保存使用")
    private BigDecimal wholeDiscount;

    /**
     * 行优惠（整单优惠分摊到行的金额）
     */
    @ApiModelProperty(value = "行优惠（整单优惠分摊到行的金额）---用于上游单据保存使用")
    private BigDecimal lineDiscountAmount;

    /**
     * 实际单价（实际金额/数量）
     */
    @ApiModelProperty(value = "实际单价（实际金额/数量）---用于上游单据保存使用")
    private BigDecimal realPrice;

    /**
     * 实际金额
     */
    @ApiModelProperty(value = "实际金额---用于上游单据保存使用")
    private BigDecimal realAmount;

    /**
     * 未清数量
     */
    @ApiModelProperty(value = "未清数量---用于上游单据保存使用")
    private BigDecimal unclearQuantity;

    /**
     * 已清数量
     */
    @ApiModelProperty(value = "已清数量---用于上游单据保存使用")
    private BigDecimal clearQuantity;

    /**
     * 行号
     */
    @ApiModelProperty(value = "行号---用于上游单据保存使用")
    private Integer lineNum;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态---用于上游单据保存使用")
    private Integer status;


    public static List<DistrReturnNoticeDtlVO> getDistrReturnNoticeDtlVOs(List<DistrInReturnOutDetail> distrInReturnOutDetails,DistrInReturnOut distrInReturnOut){

       /* Map<Long,List<DistrInReturnOutDetail>> map = new HashMap<>();

        for(DistrInReturnOutDetail di : distrInReturnOutDetails){

            List<DistrInReturnOutDetail> dis = map.get(di.getGoodsId());
            if(CollectionUtils.isEmpty(dis)){
                dis = new ArrayList<>();
                dis.add(di);
                map.put(di.getGoodsId(),dis);
            }else {
                dis.add(di);
                map.put(di.getGoodsId(),dis);
            }

        }

        List<DistrInReturnOutDetail> newDistrInReturnOutDetails = new ArrayList<>();

        for(Map.Entry<Long,List<DistrInReturnOutDetail>> entry : map.entrySet()){
            List<DistrInReturnOutDetail> value = entry.getValue();
            if(!CollectionUtils.isEmpty(value)){
                DistrInReturnOutDetail distrInReturnOutDetail = value.get(0);
                DistrInReturnOutDetail newDir = DeepCloneObject.cloneObject(distrInReturnOutDetail);

                for(DistrInReturnOutDetail d: value){
                    if(!d.getId().equals(newDir.getId())){
                        newDir.setQuantity(d.getQuantity().add(newDir.getQuantity()).setScale(2,BigDecimal.ROUND_HALF_UP));

                        newDir.setUnitPrice(d.getUnitPrice().add(newDir.getUnitPrice()));

                        newDir.setAmount(d.getAmount().add(newDir.getAmount()));

                        newDir.setNotaxRealPrice(d.getNotaxRealPrice().add(newDir.getNotaxRealPrice()));

                        newDir.setNotaxRealAmount(d.getNotaxRealAmount().add(newDir.getNotaxRealAmount()));

                        newDir.setTaxAmount(d.getTaxAmount().add(newDir.getTaxAmount()));

                        newDir.setLineDiscountAmount(d.getLineDiscountAmount().add(newDir.getLineDiscountAmount()));

                        newDir.setRealPrice(d.getRealPrice().add(newDir.getRealPrice()));

                        newDir.setRealAmount(d.getRealAmount().add(newDir.getRealAmount()));

                        newDir.setUnclearQuantity(d.getUnclearQuantity().add(newDir.getUnclearQuantity()));

                        newDir.setClearQuantity(d.getClearQuantity().add(newDir.getClearQuantity()));
                        newDir.setLineDiscount(d.getLineDiscount().add(newDir.getLineDiscount()));
                    }

                }

                BigDecimal amountTotal = distrInReturnOut.getAmountTotal();
                BigDecimal wholeDiscount = newDir.getWholeDiscount();
                BigDecimal amount = newDir.getAmount();
                BigDecimal lineRoundOffByLineAmountAndWholeAmountTotal = CalculateComponent.getLineRoundOffByLineAmountAndWholeAmountTotal(
                        wholeDiscount, amount, amountTotal

                );
                newDir.setLineDiscount(lineRoundOffByLineAmountAndWholeAmountTotal);
                newDir.setId(null);
                newDistrInReturnOutDetails.add(newDir);

            }


        }*/


        List<DistrReturnNoticeDtlVO> collect = distrInReturnOutDetails.stream()
                .map(distrInReturnOutDetail -> getDistrReturnNoticeDtlVO(distrInReturnOutDetail)).collect(Collectors.toList());

        return collect;
    }

    public static DistrReturnNoticeDtlVO getDistrReturnNoticeDtlVO(DistrInReturnOutDetail distrInReturnOutDetail){

        DistrReturnNoticeDtlVO distrReturnNoticeDtlVO = new DistrReturnNoticeDtlVO();

        distrReturnNoticeDtlVO.setBaseOrderDtlId(distrInReturnOutDetail.getId());
        distrReturnNoticeDtlVO.setGoodsId(distrInReturnOutDetail.getGoodsId());
        distrReturnNoticeDtlVO.setQuantity(distrInReturnOutDetail.getQuantity());
        distrReturnNoticeDtlVO.setUnitPrice(distrInReturnOutDetail.getUnitPrice());
        distrReturnNoticeDtlVO.setLineDiscount(distrInReturnOutDetail.getLineDiscount());
        distrReturnNoticeDtlVO.setAmount(distrInReturnOutDetail.getAmount());
        distrReturnNoticeDtlVO.setLineDiscountAmount(distrInReturnOutDetail.getLineDiscountAmount());
        distrReturnNoticeDtlVO.setRealPrice(distrInReturnOutDetail.getRealPrice());
        distrReturnNoticeDtlVO.setRealAmount(distrInReturnOutDetail.getRealAmount());
        distrReturnNoticeDtlVO.setTaxRateId(distrInReturnOutDetail.getTaxRateId());
        distrReturnNoticeDtlVO.setNotaxRealPrice(distrInReturnOutDetail.getNotaxRealPrice());
        distrReturnNoticeDtlVO.setNotaxRealAmount(distrInReturnOutDetail.getNotaxRealAmount());
        distrReturnNoticeDtlVO.setTaxAmount(distrInReturnOutDetail.getTaxAmount());
        distrReturnNoticeDtlVO.setUnclearQuantity(distrInReturnOutDetail.getUnclearQuantity());
        distrReturnNoticeDtlVO.setLineNum(distrInReturnOutDetail.getLineNum());
        distrReturnNoticeDtlVO.setRemark(distrInReturnOutDetail.getRemark());

        return distrReturnNoticeDtlVO;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsGenericName() {
        return goodsGenericName;
    }

    public void setGoodsGenericName(String goodsGenericName) {
        this.goodsGenericName = goodsGenericName;
    }

    public Long getDosageId() {
        return dosageId;
    }

    public void setDosageId(Long dosageId) {
        this.dosageId = dosageId;
    }

    public String getDosageName() {
        return dosageName;
    }

    public void setDosageName(String dosageName) {
        this.dosageName = dosageName;
    }

    public String getGoodsSpecification() {
        return goodsSpecification;
    }

    public void setGoodsSpecification(String goodsSpecification) {
        this.goodsSpecification = goodsSpecification;
    }

    public Long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getTaxRateId() {
        return taxRateId;
    }

    public void setTaxRateId(Long taxRateId) {
        this.taxRateId = taxRateId;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getNotaxRealPrice() {
        return notaxRealPrice;
    }

    public void setNotaxRealPrice(BigDecimal notaxRealPrice) {
        this.notaxRealPrice = notaxRealPrice;
    }

    public BigDecimal getNotaxRealAmount() {
        return notaxRealAmount;
    }

    public void setNotaxRealAmount(BigDecimal notaxRealAmount) {
        this.notaxRealAmount = notaxRealAmount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getGoodsPlace() {
        return goodsPlace;
    }

    public void setGoodsPlace(String goodsPlace) {
        this.goodsPlace = goodsPlace;
    }

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

    public Integer getBaseOrderType() {
        return baseOrderType;
    }

    public void setBaseOrderType(Integer baseOrderType) {
        this.baseOrderType = baseOrderType;
    }

    public String getBaseOrderCode() {
        return baseOrderCode;
    }

    public void setBaseOrderCode(String baseOrderCode) {
        this.baseOrderCode = baseOrderCode;
    }

    public Date getBaseOrderDate() {
        return baseOrderDate;
    }

    public void setBaseOrderDate(Date baseOrderDate) {
        this.baseOrderDate = baseOrderDate;
    }

    public BigDecimal getWholeDiscount() {
        return wholeDiscount;
    }

    public void setWholeDiscount(BigDecimal wholeDiscount) {
        this.wholeDiscount = wholeDiscount;
    }

    public BigDecimal getLineDiscountAmount() {
        return lineDiscountAmount;
    }

    public void setLineDiscountAmount(BigDecimal lineDiscountAmount) {
        this.lineDiscountAmount = lineDiscountAmount;
    }

    public BigDecimal getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(BigDecimal realPrice) {
        this.realPrice = realPrice;
    }

    public BigDecimal getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }

    public BigDecimal getUnclearQuantity() {
        return unclearQuantity;
    }

    public void setUnclearQuantity(BigDecimal unclearQuantity) {
        this.unclearQuantity = unclearQuantity;
    }

    public BigDecimal getClearQuantity() {
        return clearQuantity;
    }

    public void setClearQuantity(BigDecimal clearQuantity) {
        this.clearQuantity = clearQuantity;
    }

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getNoticeCode() {
        return noticeCode;
    }

    public void setNoticeCode(String noticeCode) {
        this.noticeCode = noticeCode;
    }

    @Override
    public String toString() {
        return "DistrReturnNoticeDtlVO{" +
                "goodsId=" + goodsId +
                ", goodsCode='" + goodsCode + '\'' +
                ", goodsGenericName='" + goodsGenericName + '\'' +
                ", dosageId=" + dosageId +
                ", dosageName='" + dosageName + '\'' +
                ", goodsSpecification='" + goodsSpecification + '\'' +
                ", manufacturerId=" + manufacturerId +
                ", manufacturer='" + manufacturer + '\'' +
                ", unitId=" + unitId +
                ", unitName='" + unitName + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", lineDiscount=" + lineDiscount +
                ", amount=" + amount +
                ", taxRateId=" + taxRateId +
                ", taxRate=" + taxRate +
                ", notaxRealPrice=" + notaxRealPrice +
                ", notaxRealAmount=" + notaxRealAmount +
                ", taxAmount=" + taxAmount +
                ", remark='" + remark + '\'' +
                '}';
    }

    public static DistrReturnNoticeDtlVO convertToVO(DistrReturnNoticeDetail d) {
        DistrReturnNoticeDtlVO vo = new DistrReturnNoticeDtlVO();
        vo.setGoodsId(d.getGoodsId());
        vo.setGoodsCode(d.getGoodsCode());
        vo.setGoodsGenericName(d.getGoodsGenericName());
        vo.setDosageId(d.getDosageId());
        vo.setDosageName(d.getDosageName());
        vo.setGoodsSpecification(d.getGoodsSpecification());
        vo.setManufacturerId(d.getManufacturerId());
        vo.setManufacturer(d.getManufacturer());
        vo.setUnitId(d.getUnitId());
        vo.setUnitName(d.getUnitName());
        vo.setQuantity(d.getQuantity());
        vo.setUnitPrice(d.getUnitPrice());
        vo.setLineDiscount(d.getLineDiscount());
        vo.setAmount(d.getAmount());
        vo.setTaxRateId(d.getTaxRateId());
        vo.setTaxRate(d.getTaxRate());
        vo.setNotaxRealPrice(d.getNotaxRealPrice());
        vo.setNotaxRealAmount(d.getNotaxRealAmount());
        vo.setTaxAmount(d.getTaxAmount());
        vo.setRemark(d.getRemark());
        vo.setGoodsPlace(d.getGoodsPlace());
        vo.setLineNum(d.getLineNum());
        vo.setNoticeCode(d.getNoticeCode());
        return vo;
    }
}
