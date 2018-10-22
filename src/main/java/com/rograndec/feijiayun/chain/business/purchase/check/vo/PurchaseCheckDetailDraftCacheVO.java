package com.rograndec.feijiayun.chain.business.purchase.check.vo;

import com.rograndec.feijiayun.chain.business.purchase.check.entity.PurchaseCheckLot;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class PurchaseCheckDetailDraftCacheVO implements Serializable {


    /**
     * 验收日期
     */
    @ApiModelProperty(value = "验收日期", required = true)
    private Date checkDate;

    /**
     * 基础单据明细ID
     */
    @ApiModelProperty(value = "基础单据明细ID", required = true)
    private Long baseOrderDtlId;

    /**
     * 基础单据ID
     */
    @ApiModelProperty(value = "基础单据ID", required = true)
    private Long baseOrderId;

    /**
     * 基础单据类型
     */
    @ApiModelProperty(value = "基础单据类型", required = true)
    private Integer baseOrderType;

    /**
     * 基础单据编码
     */
    @ApiModelProperty(value = "基础单据编码", required = true)
    private String baseOrderCode;

    /**
     * 基础单据日期
     */
    @ApiModelProperty(value = "基础单据日期", required = true)
    private Date baseOrderDate;

    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID", required = true)
    private Long goodsId;

    /**
     * 商品编码
     */
    @ApiModelProperty(value = "商品编码", required = true)
    private String goodsCode;

    /**
     * 条形码
     */
    @ApiModelProperty(value = "条形码", required = true)
    private String barcode;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称", required = true)
    private String goodsName;

    /**
     * 商品通用名称
     */
    @ApiModelProperty(value = "商品通用名称", required = true)
    private String goodsGenericName;

    /**
     * 剂型ID
     */
    @ApiModelProperty(value = "剂型ID", required = true)
    private Long dosageId;

    /**
     * 剂型名称
     */
    @ApiModelProperty(value = "剂型名称", required = true)
    private String dosageName;

    /**
     * 单位ID
     */
    @ApiModelProperty(value = "单位ID", required = true)
    private Long unitId;

    /**
     * 单位名称
     */
    @ApiModelProperty(value = "单位名称", required = true)
    private String unitName;

    /**
     * 商品规格
     */
    @ApiModelProperty(value = "商品规格", required = true)
    private String goodsSpecification;

    /**
     * 生产企业
     */
    @ApiModelProperty(value = "生产企业", required = true)
    private String manufacturer;

    /**
     * 生产企业ID
     */
    @ApiModelProperty(value = "生产企业ID", required = true)
    private Long manufacturerId;

    /**
     * 商品产地
     */
    @ApiModelProperty(value = "商品产地", required = true)
    private String goodsPlace;

    /**
     * 批准文号
     */
    @ApiModelProperty(value = "批准文号", required = true)
    private String approvalNumber;

    /**
     * 收货数量
     */
    @ApiModelProperty(value = "收货数量", required = true)
    private BigDecimal quantity;

    /**
     * 验收合格数量
     */
    @ApiModelProperty(value = "验收合格数量", required = true)
    private BigDecimal qualifiedQuantity;

    /**
     * 验收不合格数量
     */
    @ApiModelProperty(value = "验收不合格数量", required = true)
    private BigDecimal unqualifiedQuantity;

    /**
     * 行号
     */
    @ApiModelProperty(value = "行号", required = true)
    private Integer lineNum;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", required = true)
    private String remark;


    List<PurchaseCheckLotDraftCacheVO> purchaseCheckLotDraftCacheVOS;

    /**
     * saas_purchase_check_detail
     */
    private static final long serialVersionUID = 1L;


    /**
     * 验收日期
     * @return check_date 验收日期
     */
    public Date getCheckDate() {
        return checkDate;
    }

    /**
     * 验收日期
     * @param checkDate 验收日期
     */
    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    /**
     * 基础单据明细ID
     * @return base_order_dtl_id 基础单据明细ID
     */
    public Long getBaseOrderDtlId() {
        return baseOrderDtlId;
    }

    /**
     * 基础单据明细ID
     * @param baseOrderDtlId 基础单据明细ID
     */
    public void setBaseOrderDtlId(Long baseOrderDtlId) {
        this.baseOrderDtlId = baseOrderDtlId;
    }

    /**
     * 基础单据ID
     * @return base_order_id 基础单据ID
     */
    public Long getBaseOrderId() {
        return baseOrderId;
    }

    /**
     * 基础单据ID
     * @param baseOrderId 基础单据ID
     */
    public void setBaseOrderId(Long baseOrderId) {
        this.baseOrderId = baseOrderId;
    }

    /**
     * 基础单据类型
     * @return base_order_type 基础单据类型
     */
    public Integer getBaseOrderType() {
        return baseOrderType;
    }

    /**
     * 基础单据类型
     * @param baseOrderType 基础单据类型
     */
    public void setBaseOrderType(Integer baseOrderType) {
        this.baseOrderType = baseOrderType;
    }

    /**
     * 基础单据编码
     * @return base_order_code 基础单据编码
     */
    public String getBaseOrderCode() {
        return baseOrderCode;
    }

    /**
     * 基础单据编码
     * @param baseOrderCode 基础单据编码
     */
    public void setBaseOrderCode(String baseOrderCode) {
        this.baseOrderCode = baseOrderCode == null ? null : baseOrderCode.trim();
    }

    /**
     * 基础单据日期
     * @return base_order_date 基础单据日期
     */
    public Date getBaseOrderDate() {
        return baseOrderDate;
    }

    /**
     * 基础单据日期
     * @param baseOrderDate 基础单据日期
     */
    public void setBaseOrderDate(Date baseOrderDate) {
        this.baseOrderDate = baseOrderDate;
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
     * 生产企业
     * @return manufacturer 生产企业
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * 生产企业
     * @param manufacturer 生产企业
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer == null ? null : manufacturer.trim();
    }

    /**
     * 生产企业ID
     * @return manufacturer 生产企业ID
     */
    public Long getManufacturerId() {
        return manufacturerId;
    }

    /**
     * 生产企业ID
     * @param manufacturerId 生产企业ID
     */
    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
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
     * 收货数量
     * @return quantity 收货数量
     */
    public BigDecimal getQuantity() {
        return quantity;
    }

    /**
     * 收货数量
     * @param quantity 收货数量
     */
    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    /**
     * 验收合格数量
     * @return qualified_quantity 验收合格数量
     */
    public BigDecimal getQualifiedQuantity() {
        return qualifiedQuantity;
    }

    /**
     * 验收合格数量
     * @param qualifiedQuantity 验收合格数量
     */
    public void setQualifiedQuantity(BigDecimal qualifiedQuantity) {
        this.qualifiedQuantity = qualifiedQuantity;
    }

    /**
     * 验收不合格数量
     * @return unqualified_quantity 验收不合格数量
     */
    public BigDecimal getUnqualifiedQuantity() {
        return unqualifiedQuantity;
    }

    /**
     * 验收不合格数量
     * @param unqualifiedQuantity 验收不合格数量
     */
    public void setUnqualifiedQuantity(BigDecimal unqualifiedQuantity) {
        this.unqualifiedQuantity = unqualifiedQuantity;
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


    public List<PurchaseCheckLotDraftCacheVO> getPurchaseCheckLotDraftCacheVOS() {
        return purchaseCheckLotDraftCacheVOS;
    }

    public void setPurchaseCheckLotDraftCacheVOS(List<PurchaseCheckLotDraftCacheVO> purchaseCheckLotDraftCacheVOS) {
        this.purchaseCheckLotDraftCacheVOS = purchaseCheckLotDraftCacheVOS;
    }

    @Override
    public String toString() {
        return "PurchaseCheckDetailDraftCacheVO[" +
                " checkDate=" + checkDate +
                ", baseOrderDtlId=" + baseOrderDtlId +
                ", baseOrderId=" + baseOrderId +
                ", baseOrderType=" + baseOrderType +
                ", baseOrderCode='" + baseOrderCode + '\'' +
                ", baseOrderDate=" + baseOrderDate +
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
                ", manufacturer='" + manufacturer + '\'' +
                ", manufacturerId=" + manufacturerId +
                ", goodsPlace='" + goodsPlace + '\'' +
                ", approvalNumber='" + approvalNumber + '\'' +
                ", quantity=" + quantity +
                ", qualifiedQuantity=" + qualifiedQuantity +
                ", unqualifiedQuantity=" + unqualifiedQuantity +
                ", lineNum=" + lineNum +
                ", remark='" + remark + '\'' +
                ", purchaseCheckLotDraftCacheVOS=" + purchaseCheckLotDraftCacheVOS +
                ']';
    }
}