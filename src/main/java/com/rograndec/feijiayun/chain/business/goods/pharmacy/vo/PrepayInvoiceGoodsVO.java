package com.rograndec.feijiayun.chain.business.goods.pharmacy.vo;

import com.rograndec.feijiayun.chain.common.component.CalculateComponent;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ApiModel
public class PrepayInvoiceGoodsVO implements Serializable {

    @ApiModelProperty(required = true, value = "商品id")
    private Long goodsId;
    @ApiModelProperty(required = true, value = "标准库ID")
    private Long standardLibraryId;
    @ApiModelProperty(required = true, value = "企业id")
    private Long enterpriseId;
    @ApiModelProperty(required = true, value = "源企业id")
    private Long ownerId;
    @ApiModelProperty(required = true, value = "企业类型")
    private Integer chainType;
    /**
     * 品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）
     */
    @ApiModelProperty(required = true, value = "品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）")
    private Integer businessVariety;
    /**
     * 商品分类ID
     */
    @ApiModelProperty(required = true, value = "商品分类ID")
    private Long categoryId;
    /**
     * 商品编码
     */
    @ApiModelProperty(required = true, value = "商品编码")
    private String goodsCode;

    /**
     * 条形码
     */
    @ApiModelProperty(required = true, value = "条形码")
    private String barcode;

    /**
     * 通用名称
     */
    @ApiModelProperty(required = true, value = "通用名称")
    private String goodsGenericName;


    /**
     * 商品名称
     */
    @ApiModelProperty(required = true, value = "商品名称")
    private String goodsName;

    /**
     * 剂型ID
     */
    @ApiModelProperty(required = true, value = "剂型ID")
    private Long dosageId;

    /**
     * 剂型名称
     */
    @ApiModelProperty(required = true, value = "剂型名称")
    private String dosageName;

    /**
     * 规格
     */
    @ApiModelProperty(required = true, value = "规格")
    private String goodsSpecification;

    /**
     * 库存计量单位ID
     */
    @ApiModelProperty(required = true, value = "库存计量单位ID")
    private Long unitId;
    /**
     * 库存计量单位名称
     */
    @ApiModelProperty(required = true, value = "库存计量单位名称")
    private String unitName;
    /**
     * 生产厂商ID
     */
    @ApiModelProperty(required = true, value = "生产厂商ID")
    private Long manufacturerId;

    /**
     * 生产厂商
     */
    @ApiModelProperty(required = true, value = "生产厂商")
    private String manufacturer;
    /**
     * 产地
     */
    @ApiModelProperty(required = true, value = "产地")
    private String goodsPlace;

    /**
     * 批准文号
     */
    @ApiModelProperty(required = true, value = "批准文号")
    private String approvalNumber;

    /**
     * 有效期至
     */
    @ApiModelProperty(required = true, value = "有效期至")
    private Date validUntil;

    /**
     * 最新一次入库单价
     */
    @ApiModelProperty(required = true, value = "最新一次入库单价")
    private BigDecimal unitPrice = BigDecimal.ZERO;

    /**
     *最新采购税率ID
     */
    @ApiModelProperty(required = true, value = "最新采购税率ID")
    private Long taxRateId = 1L;

    /**
     *最新采购税率
     */
    @ApiModelProperty(required = true, value = "最新采购税率")
    private BigDecimal taxRate = BigDecimal.ZERO;

    /**
     * 不含税实际单价
     */
    @ApiModelProperty(value = "不含税实际单价")
    private BigDecimal notaxRealPrice = BigDecimal.ZERO;

    /**
     * 不含税实际金额
     */
    @ApiModelProperty(value = "不含税实际金额")
    private BigDecimal notaxRealAmount = BigDecimal.ZERO;

    @ApiModelProperty(value = "数量")
    private BigDecimal quantity = BigDecimal.ONE;

    @ApiModelProperty(value = "金额")
    private BigDecimal amount = BigDecimal.ZERO;
    /**
     * 税额
     */
    @ApiModelProperty(value = "税额")
    private BigDecimal taxAmount = BigDecimal.ZERO;


    public static List<Long> getGoodsIds(List<PrepayInvoiceGoodsVO> prepayInvoiceGoodsVOS){
        List<Long> collect = prepayInvoiceGoodsVOS.stream().map(PrepayInvoiceGoodsVO::getGoodsId).collect(Collectors.toList());

        Set<Long> sets = collect.stream().collect(Collectors.toSet());

        return sets.stream().collect(Collectors.toList());
    }

    public static void setNotTax(List<PrepayInvoiceGoodsVO> prepayInvoiceGoodsVOS){

        prepayInvoiceGoodsVOS.forEach(
                prepayInvoiceGoodsVO -> {

                    BigDecimal price = prepayInvoiceGoodsVO.getUnitPrice();
                    prepayInvoiceGoodsVO.setAmount(price);
                    BigDecimal taxRate = prepayInvoiceGoodsVO.getTaxRate();
                    BigDecimal notaxPrice = CalculateComponent.getNotaxAmountByRealAmountAndTaxRate(price, taxRate);

                    prepayInvoiceGoodsVO.setNotaxRealPrice(notaxPrice);
                    prepayInvoiceGoodsVO.setNotaxRealAmount(notaxPrice);

                    /**
                     *  根据实际金额和不含税金额获取税额：金额-不含税金额
                     *  默认数量为1 所以单价就是金额
                     */
                    BigDecimal taxAmount = CalculateComponent.getTaxAmountByRealAmountAndNotaxAmount(price,prepayInvoiceGoodsVO.getNotaxRealAmount());
                    prepayInvoiceGoodsVO.setTaxAmount(taxAmount);
                }
        );

    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getStandardLibraryId() {
        return standardLibraryId;
    }

    public void setStandardLibraryId(Long standardLibraryId) {
        this.standardLibraryId = standardLibraryId;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getChainType() {
        return chainType;
    }

    public void setChainType(Integer chainType) {
        this.chainType = chainType;
    }

    public Integer getBusinessVariety() {
        return businessVariety;
    }

    public void setBusinessVariety(Integer businessVariety) {
        this.businessVariety = businessVariety;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getGoodsGenericName() {
        return goodsGenericName;
    }

    public void setGoodsGenericName(String goodsGenericName) {
        this.goodsGenericName = goodsGenericName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
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

    public String getGoodsPlace() {
        return goodsPlace;
    }

    public void setGoodsPlace(String goodsPlace) {
        this.goodsPlace = goodsPlace;
    }

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
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

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }
}
