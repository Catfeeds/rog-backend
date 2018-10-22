package com.rograndec.feijiayun.chain.business.storage.move.vo;

import com.rograndec.feijiayun.chain.business.storage.move.entity.ShelfMove;
import com.rograndec.feijiayun.chain.business.storage.move.entity.ShelfMoveDetail;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ShelfMoveDetailVO implements Serializable{

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
    private Date productDate;

    /**
     * 有效期
     */
    @ApiModelProperty(value = "有效期")
    private Date validDate;

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
     * 可用数量
     */
    @ApiModelProperty(value = "可用数量")
    private BigDecimal usableQuantity;

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

    /**
     * 商品处理单号
     */
    @ApiModelProperty(value = "商品处理单号")
    private String handleCode;

    /**
     * 商品处理单号ID
     */
    @ApiModelProperty(value = "商品处理单号ID")
    private Long handleId;



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

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
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

    public BigDecimal getUsableQuantity() {
        return usableQuantity;
    }

    public void setUsableQuantity(BigDecimal usableQuantity) {
        this.usableQuantity = usableQuantity;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
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

    public Long getDosageId() {
        return dosageId;
    }

    public void setDosageId(Long dosageId) {
        this.dosageId = dosageId;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public Long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
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

    public Long getSrcShelfId() {
        return srcShelfId;
    }

    public void setSrcShelfId(Long srcShelfId) {
        this.srcShelfId = srcShelfId;
    }

    public Long getTargetShelfId() {
        return targetShelfId;
    }

    public void setTargetShelfId(Long targetShelfId) {
        this.targetShelfId = targetShelfId;
    }

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getHandleCode() {
        return handleCode;
    }

    public void setHandleCode(String handleCode) {
        this.handleCode = handleCode;
    }

    public Long getHandleId() {
        return handleId;
    }

    public void setHandleId(Long handleId) {
        this.handleId = handleId;
    }

    @Override
    public String toString() {
        return "ShelfMoveDetailVO{" +
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
                ", productDate=" + productDate +
                ", validDate=" + validDate +
                ", srcShelfId=" + srcShelfId +
                ", srcShelfName='" + srcShelfName + '\'' +
                ", srcShelfStatusDesc='" + srcShelfStatusDesc + '\'' +
                ", targetShelfId=" + targetShelfId +
                ", targetShelfName='" + targetShelfName + '\'' +
                ", targetShelfStatusDesc='" + targetShelfStatusDesc + '\'' +
                ", lineNum=" + lineNum +
                ", remark='" + remark + '\'' +
                '}';
    }

    public static ShelfMoveDetailVO convertToVO(ShelfMoveDetail detail) {
        ShelfMoveDetailVO shelfMoveDetailVO = new ShelfMoveDetailVO();
        shelfMoveDetailVO.setGoodsCode(detail.getGoodsCode());
        shelfMoveDetailVO.setGoodsGenericName(detail.getGoodsGenericName());
        shelfMoveDetailVO.setDosageName(detail.getDosageName());
        shelfMoveDetailVO.setGoodsSpecification(detail.getGoodsSpecification());
        shelfMoveDetailVO.setUnitName(detail.getUnitName());
        shelfMoveDetailVO.setManufacturer(detail.getManufacturer());
        shelfMoveDetailVO.setGoodsPlace(detail.getGoodsPlace());
        shelfMoveDetailVO.setLotNumber(detail.getLotNumber());
        shelfMoveDetailVO.setProductDate(detail.getProductDate());
        shelfMoveDetailVO.setValidDate(detail.getValidDate());
        shelfMoveDetailVO.setSrcShelfName(detail.getSrcShelfName());
        shelfMoveDetailVO.setSrcShelfStatusDesc(detail.getSrcShelfStatusDesc());
        shelfMoveDetailVO.setTargetShelfName(detail.getTargetShelfName());
        shelfMoveDetailVO.setTargetShelfStatusDesc(detail.getTargetShelfStatusDesc());
        shelfMoveDetailVO.setQuantity(detail.getQuantity());
        shelfMoveDetailVO.setLineNum(detail.getLineNum());
        shelfMoveDetailVO.setRemark(detail.getRemark());
        return shelfMoveDetailVO;
    }
}
