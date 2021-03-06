package com.rograndec.feijiayun.chain.business.storage.maintance.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * saas_goods_maintance_detail
 * 
 * 
 * @author Asze
 * 
 * 2017-09-26
 */
public class GoodsMaintanceDetailVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 单据（药品养护单）ID
     */
    @ApiModelProperty(value = "单据（药品养护单）ID")
    private Long maintanceId;

    /**
     * 单据（药品养护单）编码
     */
    @ApiModelProperty(value = "单据（药品养护单）编码")
    private String maintanceCode;

    /**
     * 单据（药品养护单）日期
     */
    @ApiModelProperty(value = "单据（药品养护单）日期")
    private Date maintanceDate;

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
     * 条形码
     */
    @ApiModelProperty(value = "条形码")
    private String barcode;

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
     * 商品产地
     */
    @ApiModelProperty(value = "商品产地")
    private String goodsPlace;

    /**
     * 批准文号
     */
    @ApiModelProperty(value = "批准文号")
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
     * 货位ID
     */
    @ApiModelProperty(value = "货位ID")
    private Long shelfId;

    /**
     * 货位名称
     */
    @ApiModelProperty(value = "货位名称")
    private String shelfName;

    /**
     * 货位质量状态描述
     */
    @ApiModelProperty(value = "货位质量状态描述")
    private String shelfStatusDesc;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private BigDecimal quantity;

    /**
     * 养护措施ID集合（取系统设置-养护措施）
     */
    @ApiModelProperty(value = "养护措施ID集合（取系统设置-养护措施）")
    private String measuresIds;

    /**
     * 养护措施
     */
    @ApiModelProperty(value = "养护措施")
    private String measures;

    /**
     * 合格数量
     */
    @ApiModelProperty(value = "合格数量")
    private BigDecimal qualifiedQuantity;

    /**
     * 养护结论ID（取系统设置-验收结论）
     */
    @ApiModelProperty(value = "养护结论ID（取系统设置-验收结论）")
    private Long conclusionId;

    /**
     * 养护结论
     */
    @ApiModelProperty(value = "养护结论")
    private String conclusion;

    /**
     * 不合格数量
     */
    @ApiModelProperty(value = "不合格数量")
    private BigDecimal unqualifiedQuantity;

    /**
     * 问题原因ID集合（取系统设置-不合格原因）
     */
    @ApiModelProperty(value = "问题原因ID集合（取系统设置-不合格原因）")
    private String reasonIds;

    /**
     * 问题原因
     */
    @ApiModelProperty(value = "问题原因")
    private String reason;

    /**
     * 处置措施ID集合（取系统设置-处置措施）
     */
    @ApiModelProperty(value = "处置措施ID集合（取系统设置-处置措施）")
    private String handleMeasuresIds;

    /**
     * 处置措施
     */
    @ApiModelProperty(value = "处置措施")
    private String handleMeasures;

    /**
     * 行号
     */
    @ApiModelProperty(value = "行号")
    private Integer lineNum;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * saas_goods_maintance_detail
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     * @return id 主键ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键ID
     * @param id 主键ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 单据（药品养护单）ID
     * @return maintance_id 单据（药品养护单）ID
     */
    public Long getMaintanceId() {
        return maintanceId;
    }

    /**
     * 单据（药品养护单）ID
     * @param maintanceId 单据（药品养护单）ID
     */
    public void setMaintanceId(Long maintanceId) {
        this.maintanceId = maintanceId;
    }

    /**
     * 单据（药品养护单）编码
     * @return maintance_code 单据（药品养护单）编码
     */
    public String getMaintanceCode() {
        return maintanceCode;
    }

    /**
     * 单据（药品养护单）编码
     * @param maintanceCode 单据（药品养护单）编码
     */
    public void setMaintanceCode(String maintanceCode) {
        this.maintanceCode = maintanceCode == null ? null : maintanceCode.trim();
    }

    /**
     * 单据（药品养护单）日期
     * @return maintance_date 单据（药品养护单）日期
     */
    public Date getMaintanceDate() {
        return maintanceDate;
    }

    /**
     * 单据（药品养护单）日期
     * @param maintanceDate 单据（药品养护单）日期
     */
    public void setMaintanceDate(Date maintanceDate) {
        this.maintanceDate = maintanceDate;
    }

    /**
     * 商品ID
     * @return goods_id 商品ID
     */
    public Long getGoodsId() {
        return goodsId;
    }

    /**
     * 商品ID
     * @param goodsId 商品ID
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 商品编码
     * @return goods_code 商品编码
     */
    public String getGoodsCode() {
        return goodsCode;
    }

    /**
     * 商品编码
     * @param goodsCode 商品编码
     */
    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode == null ? null : goodsCode.trim();
    }

    /**
     * 条形码
     * @return barcode 条形码
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * 条形码
     * @param barcode 条形码
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode == null ? null : barcode.trim();
    }

    /**
     * 商品名称
     * @return goods_name 商品名称
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 商品名称
     * @param goodsName 商品名称
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    /**
     * 商品通用名称
     * @return goods_generic_name 商品通用名称
     */
    public String getGoodsGenericName() {
        return goodsGenericName;
    }

    /**
     * 商品通用名称
     * @param goodsGenericName 商品通用名称
     */
    public void setGoodsGenericName(String goodsGenericName) {
        this.goodsGenericName = goodsGenericName == null ? null : goodsGenericName.trim();
    }

    /**
     * 剂型ID
     * @return dosage_id 剂型ID
     */
    public Long getDosageId() {
        return dosageId;
    }

    /**
     * 剂型ID
     * @param dosageId 剂型ID
     */
    public void setDosageId(Long dosageId) {
        this.dosageId = dosageId;
    }

    /**
     * 剂型名称
     * @return dosage_name 剂型名称
     */
    public String getDosageName() {
        return dosageName;
    }

    /**
     * 剂型名称
     * @param dosageName 剂型名称
     */
    public void setDosageName(String dosageName) {
        this.dosageName = dosageName == null ? null : dosageName.trim();
    }

    /**
     * 单位ID
     * @return unit_id 单位ID
     */
    public Long getUnitId() {
        return unitId;
    }

    /**
     * 单位ID
     * @param unitId 单位ID
     */
    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    /**
     * 单位名称
     * @return unit_name 单位名称
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     * 单位名称
     * @param unitName 单位名称
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName == null ? null : unitName.trim();
    }

    /**
     * 商品规格
     * @return goods_specification 商品规格
     */
    public String getGoodsSpecification() {
        return goodsSpecification;
    }

    /**
     * 商品规格
     * @param goodsSpecification 商品规格
     */
    public void setGoodsSpecification(String goodsSpecification) {
        this.goodsSpecification = goodsSpecification == null ? null : goodsSpecification.trim();
    }

    /**
     * 生产厂商ID
     * @return manufacturer_id 生产厂商ID
     */
    public Long getManufacturerId() {
        return manufacturerId;
    }

    /**
     * 生产厂商ID
     * @param manufacturerId 生产厂商ID
     */
    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    /**
     * 生产厂商
     * @return manufacturer 生产厂商
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * 生产厂商
     * @param manufacturer 生产厂商
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer == null ? null : manufacturer.trim();
    }

    /**
     * 商品产地
     * @return goods_place 商品产地
     */
    public String getGoodsPlace() {
        return goodsPlace;
    }

    /**
     * 商品产地
     * @param goodsPlace 商品产地
     */
    public void setGoodsPlace(String goodsPlace) {
        this.goodsPlace = goodsPlace == null ? null : goodsPlace.trim();
    }

    /**
     * 批准文号
     * @return approval_number 批准文号
     */
    public String getApprovalNumber() {
        return approvalNumber;
    }

    /**
     * 批准文号
     * @param approvalNumber 批准文号
     */
    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber == null ? null : approvalNumber.trim();
    }

    /**
     * 批号ID
     * @return lot_id 批号ID
     */
    public Long getLotId() {
        return lotId;
    }

    /**
     * 批号ID
     * @param lotId 批号ID
     */
    public void setLotId(Long lotId) {
        this.lotId = lotId;
    }

    /**
     * 批号
     * @return lot_number 批号
     */
    public String getLotNumber() {
        return lotNumber;
    }

    /**
     * 批号
     * @param lotNumber 批号
     */
    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber == null ? null : lotNumber.trim();
    }

    /**
     * 生产日期
     * @return product_date 生产日期
     */
    public Date getProductDate() {
        return productDate;
    }

    /**
     * 生产日期
     * @param productDate 生产日期
     */
    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    /**
     * 有效期
     * @return valid_date 有效期
     */
    public Date getValidDate() {
        return validDate;
    }

    /**
     * 有效期
     * @param validDate 有效期
     */
    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    /**
     * 货位ID
     * @return shelf_id 货位ID
     */
    public Long getShelfId() {
        return shelfId;
    }

    /**
     * 货位ID
     * @param shelfId 货位ID
     */
    public void setShelfId(Long shelfId) {
        this.shelfId = shelfId;
    }

    /**
     * 货位名称
     * @return shelf_name 货位名称
     */
    public String getShelfName() {
        return shelfName;
    }

    /**
     * 货位名称
     * @param shelfName 货位名称
     */
    public void setShelfName(String shelfName) {
        this.shelfName = shelfName == null ? null : shelfName.trim();
    }

    /**
     * 数量
     * @return quantity 数量
     */
    public BigDecimal getQuantity() {
        return quantity;
    }

    /**
     * 数量
     * @param quantity 数量
     */
    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    /**
     * 养护措施ID集合（取系统设置-养护措施）
     * @return measures_ids 养护措施ID集合（取系统设置-养护措施）
     */
    public String getMeasuresIds() {
        return measuresIds;
    }

    /**
     * 养护措施ID集合（取系统设置-养护措施）
     * @param measuresIds 养护措施ID集合（取系统设置-养护措施）
     */
    public void setMeasuresIds(String measuresIds) {
        this.measuresIds = measuresIds == null ? null : measuresIds.trim();
    }

    /**
     * 合格数量
     * @return qualified_quantity 合格数量
     */
    public BigDecimal getQualifiedQuantity() {
        return qualifiedQuantity;
    }

    /**
     * 合格数量
     * @param qualifiedQuantity 合格数量
     */
    public void setQualifiedQuantity(BigDecimal qualifiedQuantity) {
        this.qualifiedQuantity = qualifiedQuantity;
    }

    /**
     * 养护结论ID（取系统设置-验收结论）
     * @return conclusion_id 养护结论ID（取系统设置-验收结论）
     */
    public Long getConclusionId() {
        return conclusionId;
    }

    /**
     * 养护结论ID（取系统设置-验收结论）
     * @param conclusionId 养护结论ID（取系统设置-验收结论）
     */
    public void setConclusionId(Long conclusionId) {
        this.conclusionId = conclusionId;
    }

    /**
     * 不合格数量
     * @return unqualified_quantity 不合格数量
     */
    public BigDecimal getUnqualifiedQuantity() {
        return unqualifiedQuantity;
    }

    /**
     * 不合格数量
     * @param unqualifiedQuantity 不合格数量
     */
    public void setUnqualifiedQuantity(BigDecimal unqualifiedQuantity) {
        this.unqualifiedQuantity = unqualifiedQuantity;
    }

    /**
     * 问题原因ID集合（取系统设置-不合格原因）
     * @return reason_ids 问题原因ID集合（取系统设置-不合格原因）
     */
    public String getReasonIds() {
        return reasonIds;
    }

    /**
     * 问题原因ID集合（取系统设置-不合格原因）
     * @param reasonIds 问题原因ID集合（取系统设置-不合格原因）
     */
    public void setReasonIds(String reasonIds) {
        this.reasonIds = reasonIds == null ? null : reasonIds.trim();
    }

    /**
     * 处置措施ID集合（取系统设置-处置措施）
     * @return handle_measures_ids 处置措施ID集合（取系统设置-处置措施）
     */
    public String getHandleMeasuresIds() {
        return handleMeasuresIds;
    }

    /**
     * 处置措施ID集合（取系统设置-处置措施）
     * @param handleMeasuresIds 处置措施ID集合（取系统设置-处置措施）
     */
    public void setHandleMeasuresIds(String handleMeasuresIds) {
        this.handleMeasuresIds = handleMeasuresIds == null ? null : handleMeasuresIds.trim();
    }

    public String getShelfStatusDesc() {
        return shelfStatusDesc;
    }

    public void setShelfStatusDesc(String shelfStatusDesc) {
        this.shelfStatusDesc = shelfStatusDesc;
    }

    /**
     * 行号
     * @return line_num 行号
     */
    public Integer getLineNum() {
        return lineNum;
    }

    /**
     * 行号
     * @param lineNum 行号
     */
    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }

    /**
     * 状态
     * @return status 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态
     * @param status 状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 备注
     * @return remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getMeasures() {
        return measures;
    }

    public void setMeasures(String measures) {
        this.measures = measures;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getHandleMeasures() {
        return handleMeasures;
    }

    public void setHandleMeasures(String handleMeasures) {
        this.handleMeasures = handleMeasures;
    }
/**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        return "GoodsMaintanceDetailVO{" +
                "id=" + id +
                ", maintanceId=" + maintanceId +
                ", maintanceCode='" + maintanceCode + '\'' +
                ", maintanceDate=" + maintanceDate +
                ", goodsId=" + goodsId +
                ", goodsCode='" + goodsCode + '\'' +
                ", barcode='" + barcode + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsGenericName='" + goodsGenericName + '\'' +
                ", dosageId=" + dosageId +
                ", dosageName='" + dosageName + '\'' +
                ", unitId=" + unitId +
                ", unitName='" + unitName + '\'' +
                ", goodsSpecification='" + goodsSpecification + '\'' +
                ", manufacturerId=" + manufacturerId +
                ", manufacturer='" + manufacturer + '\'' +
                ", goodsPlace='" + goodsPlace + '\'' +
                ", approvalNumber='" + approvalNumber + '\'' +
                ", lotId=" + lotId +
                ", lotNumber='" + lotNumber + '\'' +
                ", productDate=" + productDate +
                ", validDate=" + validDate +
                ", shelfId=" + shelfId +
                ", shelfName='" + shelfName + '\'' +
                ", shelfStatusDesc='" + shelfStatusDesc + '\'' +
                ", quantity=" + quantity +
                ", measuresIds='" + measuresIds + '\'' +
                ", measures='" + measures + '\'' +
                ", qualifiedQuantity=" + qualifiedQuantity +
                ", conclusionId=" + conclusionId +
                ", conclusion='" + conclusion + '\'' +
                ", unqualifiedQuantity=" + unqualifiedQuantity +
                ", reasonIds='" + reasonIds + '\'' +
                ", reason='" + reason + '\'' +
                ", handleMeasuresIds='" + handleMeasuresIds + '\'' +
                ", handleMeasures='" + handleMeasures + '\'' +
                ", lineNum=" + lineNum +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                '}';
    }
}