package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ExportDistrReturnCheckDtlVO implements Serializable {

    /**
     * 配进验收品种明细ID
     */
    @ApiModelProperty(value = "配进验收品种明细ID", required = true)
    private String dtlId;

    /**
     * 商品编码
     */
    @ApiModelProperty(value = "商品编码", required = true)
    private String goodsCode;

    /**
     * 通用名称
     */
    @ApiModelProperty(value = "通用名称", required = true)
    private String goodsGenericName;

    /**
     * 剂型
     */
    @ApiModelProperty(value = "剂型", required = true)
    private String dosageName;

    /**
     * 规格
     */
    @ApiModelProperty(value = "规格", required = true)
    private String goodsSpecification;

    /**
     * 生产厂商
     */
    @ApiModelProperty(value = "生产厂商", required = true)
    private String manufacturer;

    /**
     * 单位
     */
    @ApiModelProperty(value = "单位", required = true)
    private String unitName;

    /**
     * 产地
     */
    @ApiModelProperty(value = "产地", required = true)
    private String goodsPlace;

    /**
     * 收货数量
     */
    @ApiModelProperty(value = "收货数量", required = true)
    private BigDecimal receiveQuantityDtl;

    /**
     * 验收合格数量
     */
    @ApiModelProperty(value = "验收合格数量", required = true)
    private BigDecimal qualifiedQuantityDtl;


    /**
     * 验收不合格数量
     */
    @ApiModelProperty(value = "验收不合格数量", required = true)
    private BigDecimal unqualifiedQuantityDtl;

    /**
     * 批号
     */
    @ApiModelProperty(value = "批号", required = true)
    private String lotNumber;

    /**
     * 生产日期
     */
    @ApiModelProperty(value = "生产日期", required = true)
    private Date productDate;

    /**
     * 有效期
     */
    @ApiModelProperty(value = "有效期", required = true)
    private Date validDate;

    /**
     * 检验项目
     */
    @ApiModelProperty(value = "检验项目", required = true)
    private String checkProjectIds;

    /**
     * 收货数量
     */
    @ApiModelProperty(value = "收货数量", required = true)
    private BigDecimal receiveQuantity;

    /**
     * 抽样数量
     */
    @ApiModelProperty(value = "抽样数量", required = true)
    private BigDecimal samplingQuantity;

    /**
     * 验收合格数量
     */
    @ApiModelProperty(value = "验收合格数量", required = true)
    private BigDecimal qualifiedQuantity;

    /**
     * 验收结论
     */
    @ApiModelProperty(value = "验收结论", required = true)
    private String conclusionIds;

    /**
     * 验收不合格数量
     */
    @ApiModelProperty(value = "验收不合格数量", required = true)
    private BigDecimal unqualifiedQuantity;

    /**
     * 不合格原因
     */
    @ApiModelProperty(value = "不合格原因", required = true)
    private String unqualifiedReasonIds;

    /**
     * 处置措施
     */
    @ApiModelProperty(value = "处置措施", required = true)
    private String measuresIds;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", required = true)
    private String remark;

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

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getGoodsPlace() {
        return goodsPlace;
    }

    public void setGoodsPlace(String goodsPlace) {
        this.goodsPlace = goodsPlace;
    }

    public BigDecimal getReceiveQuantityDtl() {
        return receiveQuantityDtl;
    }

    public void setReceiveQuantityDtl(BigDecimal receiveQuantityDtl) {
        this.receiveQuantityDtl = receiveQuantityDtl;
    }

    public BigDecimal getQualifiedQuantityDtl() {
        return qualifiedQuantityDtl;
    }

    public void setQualifiedQuantityDtl(BigDecimal qualifiedQuantityDtl) {
        this.qualifiedQuantityDtl = qualifiedQuantityDtl;
    }

    public BigDecimal getUnqualifiedQuantityDtl() {
        return unqualifiedQuantityDtl;
    }

    public void setUnqualifiedQuantityDtl(BigDecimal unqualifiedQuantityDtl) {
        this.unqualifiedQuantityDtl = unqualifiedQuantityDtl;
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

    public String getCheckProjectIds() {
        return checkProjectIds;
    }

    public void setCheckProjectIds(String checkProjectIds) {
        this.checkProjectIds = checkProjectIds;
    }

    public BigDecimal getReceiveQuantity() {
        return receiveQuantity;
    }

    public void setReceiveQuantity(BigDecimal receiveQuantity) {
        this.receiveQuantity = receiveQuantity;
    }

    public BigDecimal getSamplingQuantity() {
        return samplingQuantity;
    }

    public void setSamplingQuantity(BigDecimal samplingQuantity) {
        this.samplingQuantity = samplingQuantity;
    }

    public BigDecimal getQualifiedQuantity() {
        return qualifiedQuantity;
    }

    public void setQualifiedQuantity(BigDecimal qualifiedQuantity) {
        this.qualifiedQuantity = qualifiedQuantity;
    }

    public String getConclusionIds() {
        return conclusionIds;
    }

    public void setConclusionIds(String conclusionIds) {
        this.conclusionIds = conclusionIds;
    }

    public BigDecimal getUnqualifiedQuantity() {
        return unqualifiedQuantity;
    }

    public void setUnqualifiedQuantity(BigDecimal unqualifiedQuantity) {
        this.unqualifiedQuantity = unqualifiedQuantity;
    }

    public String getUnqualifiedReasonIds() {
        return unqualifiedReasonIds;
    }

    public void setUnqualifiedReasonIds(String unqualifiedReasonIds) {
        this.unqualifiedReasonIds = unqualifiedReasonIds;
    }

    public String getMeasuresIds() {
        return measuresIds;
    }

    public void setMeasuresIds(String measuresIds) {
        this.measuresIds = measuresIds;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDtlId() {
        return dtlId;
    }

    public void setDtlId(String dtlId) {
        this.dtlId = dtlId;
    }

    @Override
    public String toString() {
        return "ExportDistrReturnCheckDtlVO[" +
                "goodsCode='" + goodsCode + '\'' +
                ", dtlId='" + dtlId + '\'' +
                ", goodsGenericName='" + goodsGenericName + '\'' +
                ", dosageName='" + dosageName + '\'' +
                ", goodsSpecification='" + goodsSpecification + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", unitName='" + unitName + '\'' +
                ", goodsPlace='" + goodsPlace + '\'' +
                ", receiveQuantityDtl=" + receiveQuantityDtl +
                ", qualifiedQuantityDtl=" + qualifiedQuantityDtl +
                ", unqualifiedQuantityDtl=" + unqualifiedQuantityDtl +
                ", lotNumber='" + lotNumber + '\'' +
                ", productDate=" + productDate +
                ", validDate=" + validDate +
                ", checkProjectIds='" + checkProjectIds + '\'' +
                ", receiveQuantity=" + receiveQuantity +
                ", samplingQuantity=" + samplingQuantity +
                ", qualifiedQuantity=" + qualifiedQuantity +
                ", conclusionIds='" + conclusionIds + '\'' +
                ", unqualifiedQuantity=" + unqualifiedQuantity +
                ", unqualifiedReasonIds='" + unqualifiedReasonIds + '\'' +
                ", measuresIds='" + measuresIds + '\'' +
                ", remark='" + remark + '\'' +
                ']';
    }
}
