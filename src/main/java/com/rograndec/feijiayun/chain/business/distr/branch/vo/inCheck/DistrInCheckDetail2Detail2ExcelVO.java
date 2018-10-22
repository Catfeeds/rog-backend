package com.rograndec.feijiayun.chain.business.distr.branch.vo.inCheck;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * saas_distr_in_check_detail
 * 
 * 
 * @author ST
 * 
 * 2017-10-10
 */
public class DistrInCheckDetail2Detail2ExcelVO implements Serializable {

    private Long id;
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
     * 剂型名称
     */
    @ApiModelProperty(value = "剂型名称")
    private String dosageName;


    /**
     * 单位名称
     */
    @ApiModelProperty(value = "单位名称")
    private String unitName;

    /**
     * 商品规格
     */
    @ApiModelProperty(value = "商品规格")
    private String goodsSpecification;



    /**
     * 生产厂商
     */
    @ApiModelProperty(value = "生产厂商")
    private String manufacturer;

    /**
     * 商品产地
     */
    @ApiModelProperty(value = "商品产地")
    private String goodsPlace;


    /**
     * 收货数量
     */
    @ApiModelProperty(value = "收货数量")
    private BigDecimal receiveQuantity;

    /**
     * 验收合格数量
     */
    @ApiModelProperty(value = "验收合格数量")
    private BigDecimal qualifiedQuantity;

    /**
     * 验收不合格数量
     */
    @ApiModelProperty(value = "验收不合格数量")
    private BigDecimal unqualifiedQuantity;



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
     * 检验项目ID集合，多个用逗号分隔
     */
    @ApiModelProperty(value = "检验项目ID集合，多个用逗号分隔")
    private String checkProjectIds;

    @ApiModelProperty(value = "检验项目Name集合，多个用逗号分隔")
    private String checkProjectNames;

    /**
     * 收货数量
     */
    @ApiModelProperty(value = "收货数量")
    private BigDecimal receiveQuantityLot;

    /**
     * 抽样数量
     */
    @ApiModelProperty(value = "抽样数量")
    private BigDecimal samplingQuantity;


    /**
     * 验收合格数量
     */
    @ApiModelProperty(value = "验收合格数量")
    private BigDecimal qualifiedQuantityLot;

    /**
     * 验收结论ID集合，多个用逗号分隔
     */
    @ApiModelProperty(value = "验收结论ID集合，多个用逗号分隔")
    private String conclusionIds;

    @ApiModelProperty(value = "验收结论Name集合，多个用逗号分隔")
    private String conclusionNames;

    /**
     * 验收不合格数量
     */
    @ApiModelProperty(value = "验收不合格数量")
    private BigDecimal unqualifiedQuantityLot;

    /**
     * 验收不合格原因ID集合，多个用逗号分隔
     */
    @ApiModelProperty(value = "验收不合格原因ID集合，多个用逗号分隔")
    private String unqualifiedReasonIds;

    @ApiModelProperty(value = "验收不合格原因Name集合，多个用逗号分隔")
    private String unqualifiedReasonNames;
    /**
     * 处置措施ID集合，多个用逗号分隔
     */
    @ApiModelProperty(value = "处置措施ID集合，多个用逗号分隔")
    private String measuresIds;

    @ApiModelProperty(value = "处置措施Name集合，多个用逗号分隔")
    private String measuresNames;

    //备注
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

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
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

    public String getGoodsPlace() {
        return goodsPlace;
    }

    public void setGoodsPlace(String goodsPlace) {
        this.goodsPlace = goodsPlace;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getReceiveQuantityLot() {
        return receiveQuantityLot;
    }

    public void setReceiveQuantityLot(BigDecimal receiveQuantityLot) {
        this.receiveQuantityLot = receiveQuantityLot;
    }

    public BigDecimal getQualifiedQuantityLot() {
        return qualifiedQuantityLot;
    }

    public void setQualifiedQuantityLot(BigDecimal qualifiedQuantityLot) {
        this.qualifiedQuantityLot = qualifiedQuantityLot;
    }

    public BigDecimal getUnqualifiedQuantityLot() {
        return unqualifiedQuantityLot;
    }

    public void setUnqualifiedQuantityLot(BigDecimal unqualifiedQuantityLot) {
        this.unqualifiedQuantityLot = unqualifiedQuantityLot;
    }

    public String getCheckProjectNames() {
        return checkProjectNames;
    }

    public void setCheckProjectNames(String checkProjectNames) {
        this.checkProjectNames = checkProjectNames;
    }

    public String getConclusionNames() {
        return conclusionNames;
    }

    public void setConclusionNames(String conclusionNames) {
        this.conclusionNames = conclusionNames;
    }

    public String getMeasuresNames() {
        return measuresNames;
    }

    public void setMeasuresNames(String measuresNames) {
        this.measuresNames = measuresNames;
    }

    public String getUnqualifiedReasonNames() {
        return unqualifiedReasonNames;
    }

    public void setUnqualifiedReasonNames(String unqualifiedReasonNames) {
        this.unqualifiedReasonNames = unqualifiedReasonNames;
    }
}