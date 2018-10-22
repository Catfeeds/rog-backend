package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * saas_distr_return_in_detail
 * 
 * 
 * @author zhaiwei
 * 
 * 2017-10-08
 */
public class DistrReturnInDetailExcelVO implements Serializable {


    /**
     * 主键ID
     */
    @ApiModelProperty(value = "购后退回品种信息id")
    private Long id;

    /**
     * 配后退回入库单ID
     */
    @ApiModelProperty(value = "配后退回入库单ID")
    private Long returnInId;

    /**
     * 配后退回验收明细单ID
     */
    @ApiModelProperty(value = "配后退回验收明细单ID")
    private Long checkDtId;

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
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String goodsName;

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
     * 单价
     */
    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;


    /**
     * 金额（整单优惠前金额）
     */
    @ApiModelProperty(value = "金额（整单优惠前金额）")
    private BigDecimal amount;

    /**
     * 行折扣（%）
     */
    @ApiModelProperty(value = "行折扣（%）")
    private BigDecimal lineDiscount;

    /**
     * 实际金额
     */
    @ApiModelProperty(value = "实际金额")
    private BigDecimal realAmount;

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
     * 货位质量状态描述
     */
    @ApiModelProperty(value = "货位质量状态描述")
    private String shelfStatusDesc;
    /**
     * 批号
     */
    @ApiModelProperty(value = "批号")
    private String lotNumber;

    /**
     * 生产日期
     */
    @ApiModelProperty(value = "生产日期")
    private Date productDate;

    /**
     * 有效期
     */
    @ApiModelProperty(value = "有效期")
    private Date validDate;

    /**
     * 货位名称
     */
    @ApiModelProperty(value = "货位名称")
    private String shelfName;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private BigDecimal quantity;


    public static List<DistrReturnInDetailExcelVO> getDistrReturnInDetailPageVO(DistrReturnInDetailPageVO distrReturnInDetail) {

        List<DistrReturnInDetailExcelVO> list = new ArrayList<>();
        List<DistrReturnInShelfPageVO> distrReturnInShelfFormVOS = distrReturnInDetail.getDistrReturnInShelfFormVOS();

        for(DistrReturnInShelfPageVO shlefVo : distrReturnInShelfFormVOS){
            DistrReturnInDetailExcelVO distrReturnInDetailPageVO = new DistrReturnInDetailExcelVO();

            /**
             * 商品编码
             */
            distrReturnInDetailPageVO.setGoodsCode(distrReturnInDetail.getGoodsCode());

            /**
             * 商品名称
             */
            distrReturnInDetailPageVO.setGoodsName(distrReturnInDetail.getGoodsName());

            /**
             * 商品通用名称
             */
            distrReturnInDetailPageVO.setGoodsGenericName(distrReturnInDetail.getGoodsGenericName());

            /**
             * 剂型ID
             */
            distrReturnInDetailPageVO.setDosageId(distrReturnInDetail.getDosageId());

            /**
             * 剂型名称
             */
            distrReturnInDetailPageVO.setDosageName(distrReturnInDetail.getDosageName());

            /**
             * 商品规格
             */
            distrReturnInDetailPageVO.setGoodsSpecification(distrReturnInDetail.getGoodsSpecification());

            /**
             * 生产厂商ID
             */
            distrReturnInDetailPageVO.setManufacturerId(distrReturnInDetail.getManufacturerId());

            /**
             * 生产厂商
             */
            distrReturnInDetailPageVO.setManufacturer(distrReturnInDetail.getManufacturer());

            /**
             * 单位ID
             */
            distrReturnInDetailPageVO.setUnitId(distrReturnInDetail.getUnitId());

            /**
             * 单位名称
             */
            distrReturnInDetailPageVO.setUnitName(distrReturnInDetail.getUnitName());

            /**
             * 数量
             */
            distrReturnInDetailPageVO.setQuantity(shlefVo.getQuantity());

            /**
             * 单价
             */
            distrReturnInDetailPageVO.setUnitPrice(distrReturnInDetail.getUnitPrice());
            distrReturnInDetailPageVO.setAmount(distrReturnInDetail.getAmount());

            /**
             * 行折扣（%）
             */
            distrReturnInDetailPageVO.setLineDiscount(distrReturnInDetail.getLineDiscount());

            /**
             * 实际金额
             */
            distrReturnInDetailPageVO.setRealAmount(distrReturnInDetail.getRealAmount());

            /**
             * 不含税实际单价
             */
            distrReturnInDetailPageVO.setNotaxRealPrice(distrReturnInDetail.getNotaxRealPrice());


            /**
             * 不含税实际金额
             */
            distrReturnInDetailPageVO.setNotaxRealAmount(distrReturnInDetail.getNotaxRealAmount());

            /**
             * 税率ID
             */
            distrReturnInDetailPageVO.setTaxRateId(distrReturnInDetail.getTaxRateId());

            /**
             * 税率
             */
            distrReturnInDetailPageVO.setTaxRate(distrReturnInDetail.getTaxRate());
            distrReturnInDetailPageVO.setTaxAmount(distrReturnInDetail.getTaxAmount());

            /**
             * 备注
             */
            distrReturnInDetailPageVO.setRemark(distrReturnInDetail.getRemark());



            /**
             * 货位质量状态描述
             */
            distrReturnInDetailPageVO.setShelfStatusDesc(shlefVo.getShelfStatusDesc());
            /**
             * 批号
             */
            distrReturnInDetailPageVO.setLotNumber(shlefVo.getLotNumber());

            /**
             * 生产日期
             */
            distrReturnInDetailPageVO.setProductDate(shlefVo.getProductDate());

            /**
             * 有效期
             */
            distrReturnInDetailPageVO.setValidDate(shlefVo.getValidDate());


            /**
             * 货位名称
             */
            distrReturnInDetailPageVO.setShelfName(shlefVo.getShelfName());

            list.add(distrReturnInDetailPageVO);
        }


        return list;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReturnInId() {
        return returnInId;
    }

    public void setReturnInId(Long returnInId) {
        this.returnInId = returnInId;
    }

    public Long getCheckDtId() {
        return checkDtId;
    }

    public void setCheckDtId(Long checkDtId) {
        this.checkDtId = checkDtId;
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

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
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

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getLineDiscount() {
        return lineDiscount;
    }

    public void setLineDiscount(BigDecimal lineDiscount) {
        this.lineDiscount = lineDiscount;
    }

    public BigDecimal getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
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

    public String getShelfStatusDesc() {
        return shelfStatusDesc;
    }

    public void setShelfStatusDesc(String shelfStatusDesc) {
        this.shelfStatusDesc = shelfStatusDesc;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public Date getProductDate() {
        return productDate;
    }

    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public String getShelfName() {
        return shelfName;
    }

    public void setShelfName(String shelfName) {
        this.shelfName = shelfName;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }
}