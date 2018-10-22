package com.rograndec.feijiayun.chain.business.storage.move.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ShelfMoveExcelVO implements Serializable{

    @ApiModelProperty(value = "商品ID")
    private Long goodsId;
    /**
     * 商品编码
     */
    @ApiModelProperty(value = "商品编码")
    private String goodsCode;

    @ApiModelProperty(value="条形码")
    private String barcode;

    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    /**
     * 商品通用名称
     */
    @ApiModelProperty(value = "商品通用名称")
    private String goodsGenericName;

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

    @ApiModelProperty(value="单位ID")
    private Long unitId;

    /**
     * 单位名称
     */
    @ApiModelProperty(value = "单位名称")
    private String unitName;

    @ApiModelProperty(value = "生产厂商ID")
    private Long manufacturerId;

    /**
     * 生产厂商
     */
    @ApiModelProperty(value = "生产厂商")
    private String manufacturer;

    /**
     * 产地
     */
    @ApiModelProperty(value = "产地")
    private String goodsPlace;

    @ApiModelProperty(value="批准文号")
    private String approvalNumber;

    /**
     * 批号ID
     */
    @ApiModelProperty(value = "批号ID")
    private Long lotId;

    /**
     * 批号
     */
    @ApiModelProperty(value = "批号")
    private String lotNumber;

    /**
     * 生产日期
     */
    @ApiModelProperty(value = "生产日期")
    private String productDate;

    /**
     * 有效期
     */
    @ApiModelProperty(value = "有效期")
    private String validDate;

    /**
     * 源货位ID
     */
    @ApiModelProperty(value = "源货位ID")
    private Long srcShelfId;

    /**
     * 源货位名称
     */
    @ApiModelProperty(value = "源货位名称")
    private String srcShelfName;

    /**
     * 源货位质量状况
     */
    @ApiModelProperty(value = "源货位质量状况")
    private String srcShelfStatusDesc;

    /**
     * 目标货位ID
     */
    @ApiModelProperty(value = "目标货位ID")
    private Long targetShelfId;

    /**
     * 目标货位名称
     */
    @ApiModelProperty(value = "目标货位名称")
    private String targetShelfName;

    /**
     * 目标货位质量状况
     */
    @ApiModelProperty(value = "目标货位质量状况")
    private String targetShelfStatusDesc;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private BigDecimal quantity;

    /**
     * 行号
     */
    @ApiModelProperty(value = "行号")
    private Integer lineNum;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

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

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
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

    public Long getLotId() {
        return lotId;
    }

    public void setLotId(Long lotId) {
        this.lotId = lotId;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public String getProductDate() {
        return productDate;
    }

    public void setProductDate(String productDate) {
        this.productDate = productDate;
    }

    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }

    public Long getSrcShelfId() {
        return srcShelfId;
    }

    public void setSrcShelfId(Long srcShelfId) {
        this.srcShelfId = srcShelfId;
    }

    public String getSrcShelfName() {
        return srcShelfName;
    }

    public void setSrcShelfName(String srcShelfName) {
        this.srcShelfName = srcShelfName;
    }

    public String getSrcShelfStatusDesc() {
        return srcShelfStatusDesc;
    }

    public void setSrcShelfStatusDesc(String srcShelfStatusDesc) {
        this.srcShelfStatusDesc = srcShelfStatusDesc;
    }

    public Long getTargetShelfId() {
        return targetShelfId;
    }

    public void setTargetShelfId(Long targetShelfId) {
        this.targetShelfId = targetShelfId;
    }

    public String getTargetShelfName() {
        return targetShelfName;
    }

    public void setTargetShelfName(String targetShelfName) {
        this.targetShelfName = targetShelfName;
    }

    public String getTargetShelfStatusDesc() {
        return targetShelfStatusDesc;
    }

    public void setTargetShelfStatusDesc(String targetShelfStatusDesc) {
        this.targetShelfStatusDesc = targetShelfStatusDesc;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "ShelfMoveExcelVO{" +
                "goodsId=" + goodsId +
                ", goodsCode='" + goodsCode + '\'' +
                ", barcode='" + barcode + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsGenericName='" + goodsGenericName + '\'' +
                ", dosageId=" + dosageId +
                ", dosageName='" + dosageName + '\'' +
                ", goodsSpecification='" + goodsSpecification + '\'' +
                ", unitId=" + unitId +
                ", unitName='" + unitName + '\'' +
                ", manufacturerId=" + manufacturerId +
                ", manufacturer='" + manufacturer + '\'' +
                ", goodsPlace='" + goodsPlace + '\'' +
                ", approvalNumber='" + approvalNumber + '\'' +
                ", lotId=" + lotId +
                ", lotNumber='" + lotNumber + '\'' +
                ", productDate='" + productDate + '\'' +
                ", validDate=" + validDate +
                ", srcShelfId=" + srcShelfId +
                ", srcShelfName='" + srcShelfName + '\'' +
                ", srcShelfStatusDesc='" + srcShelfStatusDesc + '\'' +
                ", targetShelfId=" + targetShelfId +
                ", targetShelfName='" + targetShelfName + '\'' +
                ", targetShelfStatusDesc='" + targetShelfStatusDesc + '\'' +
                ", quantity=" + quantity +
                ", lineNum=" + lineNum +
                ", remark='" + remark + '\'' +
                '}';
    }

    public static ShelfMoveExcelVO convertToVO(ShelfMoveDetailVO s) {
        ShelfMoveExcelVO vo = new ShelfMoveExcelVO();
        vo.setGoodsCode(s.getGoodsCode());
        vo.setGoodsGenericName(s.getGoodsGenericName());
        vo.setDosageName(s.getDosageName());
        vo.setGoodsSpecification(s.getGoodsSpecification());
        vo.setManufacturer(s.getManufacturer());
        vo.setUnitName(s.getUnitName());
        vo.setLotNumber(s.getLotNumber());
        vo.setProductDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(s.getProductDate()));
        vo.setValidDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(s.getValidDate()));
        vo.setSrcShelfName(s.getSrcShelfName());
        vo.setSrcShelfStatusDesc(s.getSrcShelfStatusDesc());
        vo.setTargetShelfName(s.getTargetShelfName());
        vo.setTargetShelfStatusDesc(s.getTargetShelfStatusDesc());
        vo.setQuantity(s.getUsableQuantity());
        vo.setRemark(s.getRemark());
        return vo;
    }
}
