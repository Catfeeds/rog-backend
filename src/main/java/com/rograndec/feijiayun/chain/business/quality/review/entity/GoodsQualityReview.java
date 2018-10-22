package com.rograndec.feijiayun.chain.business.quality.review.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_goods_quality_review
 * 
 * 
 * @author dudy
 * 
 * 2017-10-21
 */
public class GoodsQualityReview implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 企业ID
     */
    @ApiModelProperty(value = "企业ID")
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
    @ApiModelProperty(value = "上级企业ID")
    private Long parentId;

    /**
     * 评审编号
     */
    @ApiModelProperty(value = "评审编号")
    private String reviewCode;

    /**
     * 评审日期
     */
    @ApiModelProperty(value = "评审日期")
    private Date reviewDate;

    /**
     * 评审日期从
     */
    @ApiModelProperty(value = "评审日期从")
    private Date startDate;

    /**
     * 评审日期至
     */
    @ApiModelProperty(value = "评审日期至")
    private Date endDate;

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
     * 产地
     */
    @ApiModelProperty(value = "产地")
    private String goodsPlace;

    /**
     * 批准文号
     */
    @ApiModelProperty(value = "批准文号")
    private String approvalNumber;

    /**
     * 有效期至
     */
    @ApiModelProperty(value = "有效期至")
    private Date validUntil;

    /**
     * 国产/进口（0-国产；1-进口）
     */
    @ApiModelProperty(value = "国产/进口（0-国产；1-进口）")
    private String domesticImportDesc;

    /**
     * 经营范围名称
     */
    @ApiModelProperty(value = "经营范围名称")
    private String managementScopeName;

    /**
     * 特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）
     */
    @ApiModelProperty(value = "特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）")
    private String specialDrugDesc;

    /**
     * 专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）
     */
    @ApiModelProperty(value = "专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）")
    private String inChargeDrugDesc;

    /**
     * 限购数量
     */
    @ApiModelProperty(value = "限购数量")
    private BigDecimal limitQuantity;

    /**
     * 贮藏温度（0-常温；1-阴凉；2-冷藏；3-冷冻）
     */
    @ApiModelProperty(value = "贮藏温度（0-常温；1-阴凉；2-冷藏；3-冷冻）")
    private String storageTempDesc;

    /**
     * 贮藏条件名称
     */
    @ApiModelProperty(value = "贮藏条件名称")
    private String storageConditionName;

    /**
     * 保质期描述
     */
    @ApiModelProperty(value = "保质期描述")
    private String qualityPeriodDesc;

    /**
     * 商品属性描述
     */
    @ApiModelProperty(value = "商品属性描述")
    private String goodsAttributeDesc;

    /**
     * 供货单位ID集合
     */
    @ApiModelProperty(value = "供货单位ID集合")
    private String supplierIds;

    /**
     * 供货单位名称集合
     */
    @ApiModelProperty(value = "供货单位名称集合")
    private String supplierNames;

    /**
     * 订单笔数
     */
    @ApiModelProperty(value = "订单笔数")
    private Integer orderCount;

    /**
     * 订单数量
     */
    @ApiModelProperty(value = "订单数量")
    private BigDecimal orderQuanlity;

    /**
     * 到货笔数
     */
    @ApiModelProperty(value = "到货笔数")
    private Integer arrivalCount;

    /**
     * 收货数量
     */
    @ApiModelProperty(value = "收货数量")
    private BigDecimal receiveQuanlity;

    /**
     * 拒收数量
     */
    @ApiModelProperty(value = "拒收数量")
    private BigDecimal rejectQuanlity;

    /**
     * 收货合格率
     */
    @ApiModelProperty(value = "收货合格率")
    private BigDecimal receiveQualifiedRate;

    /**
     * 验收批次（批号个数）
     */
    @ApiModelProperty(value = "验收批次（批号个数）")
    private Integer checkBatch;

    /**
     * 验收合格数量
     */
    @ApiModelProperty(value = "验收合格数量")
    private BigDecimal checkQualifiedQuantity;

    /**
     * 验收不合格数量
     */
    @ApiModelProperty(value = "验收不合格数量")
    private BigDecimal checkUnqualifiedQuantity;

    /**
     * 验收合格率
     */
    @ApiModelProperty(value = "验收合格率")
    private BigDecimal checkQualifiedRate;

    /**
     * 购进退出笔数
     */
    @ApiModelProperty(value = "购进退出笔数")
    private Integer purchaseReturnCount;

    /**
     * 购进退出批次（批号个数）
     */
    @ApiModelProperty(value = "购进退出批次（批号个数）")
    private Integer purchaseReturnBatch;

    /**
     * 购进退出数量
     */
    @ApiModelProperty(value = "购进退出数量")
    private BigDecimal purchaseReturnQuantity;

    /**
     * 销售笔数
     */
    @ApiModelProperty(value = "销售笔数")
    private Integer saleCount;

    /**
     * 销售批次（批号个数）
     */
    @ApiModelProperty(value = "销售批次（批号个数）")
    private Integer saleBatch;

    /**
     * 销售数量
     */
    @ApiModelProperty(value = "销售数量")
    private BigDecimal saleQuantity;

    /**
     * 销后退回笔数
     */
    @ApiModelProperty(value = "销后退回笔数")
    private Integer saleReturnCount;

    /**
     * 销后退回批次（批号个数）
     */
    @ApiModelProperty(value = "销后退回批次（批号个数）")
    private Integer saleReturnBatch;

    /**
     * 销后退回数量
     */
    @ApiModelProperty(value = "销后退回数量")
    private BigDecimal saleReturnQuantity;

    /**
     * 销毁笔数
     */
    @ApiModelProperty(value = "销毁笔数")
    private Integer destroyCount;

    /**
     * 销毁批次（批号个数）
     */
    @ApiModelProperty(value = "销毁批次（批号个数）")
    private Integer destroyBatch;

    /**
     * 销毁数量
     */
    @ApiModelProperty(value = "销毁数量")
    private BigDecimal destroyQuantity;

    /**
     * 投诉笔数
     */
    @ApiModelProperty(value = "投诉笔数")
    private Integer complainCount;

    /**
     * 投诉批次
     */
    @ApiModelProperty(value = "投诉批次")
    private Integer complainBatch;

    /**
     * 不良反应笔数
     */
    @ApiModelProperty(value = "不良反应笔数")
    private Integer adverseReactionCount;

    /**
     * 不良反应批次
     */
    @ApiModelProperty(value = "不良反应批次")
    private Integer adverseReactionBatch;

    /**
     * 召回数量
     */
    @ApiModelProperty(value = "召回数量")
    private BigDecimal recallQuantity;

    /**
     * 召回批次
     */
    @ApiModelProperty(value = "召回批次")
    private Integer recallBatch;

    /**
     * 追回数量
     */
    @ApiModelProperty(value = "追回数量")
    private Integer recoverQuantity;

    /**
     * 追回批次
     */
    @ApiModelProperty(value = "追回批次")
    private Integer recoverBatch;

    /**
     * 业务部门评审意见
     */
    @ApiModelProperty(value = "业务部门评审意见")
    private String businessOpinion;

    /**
     * 业务部门评审时间
     */
    @ApiModelProperty(value = "业务部门评审时间")
    private Date businessReviewTime;

    /**
     * 业务部门评审人员
     */
    @ApiModelProperty(value = "业务部门评审人员")
    private String businessReviewer;

    /**
     * 质量部门评审意见
     */
    @ApiModelProperty(value = "质量部门评审意见")
    private String qualityOpinion;

    /**
     * 质量部门评审时间
     */
    @ApiModelProperty(value = "质量部门评审时间")
    private Date qualityReviewTime;

    /**
     * 质量部门评审人员
     */
    @ApiModelProperty(value = "质量部门评审人员")
    private String qualityReviewer;

    /**
     * 总经理评审意见
     */
    @ApiModelProperty(value = "总经理评审意见")
    private String managerOpinion;

    /**
     * 总经理评审时间
     */
    @ApiModelProperty(value = "总经理评审时间")
    private Date managerReviewTime;

    /**
     * 总经理评审人员
     */
    @ApiModelProperty(value = "总经理评审人员")
    private String managerReviewer;

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
     * 创建人ID
     */
    @ApiModelProperty(value = "创建人ID")
    private Long createrId;

    /**
     * 创建人编码
     */
    @ApiModelProperty(value = "创建人编码")
    private String createrCode;

    /**
     * 创建人名称
     */
    @ApiModelProperty(value = "创建人名称")
    private String createrName;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 最后修改人ID
     */
    @ApiModelProperty(value = "最后修改人ID")
    private Long modifierId;

    /**
     * 最后修改人编码
     */
    @ApiModelProperty(value = "最后修改人编码")
    private String modifierCode;

    /**
     * 最后修改人名称
     */
    @ApiModelProperty(value = "最后修改人名称")
    private String modifierName;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * saas_goods_quality_review
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
     * 企业ID
     * @return enterprise_id 企业ID
     */
    public Long getEnterpriseId() {
        return enterpriseId;
    }

    /**
     * 企业ID
     * @param enterpriseId 企业ID
     */
    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    /**
     * 上级企业ID
     * @return parent_id 上级企业ID
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 上级企业ID
     * @param parentId 上级企业ID
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 评审编号
     * @return review_code 评审编号
     */
    public String getReviewCode() {
        return reviewCode;
    }

    /**
     * 评审编号
     * @param reviewCode 评审编号
     */
    public void setReviewCode(String reviewCode) {
        this.reviewCode = reviewCode == null ? null : reviewCode.trim();
    }

    /**
     * 评审日期
     * @return review_date 评审日期
     */
    public Date getReviewDate() {
        return reviewDate;
    }

    /**
     * 评审日期
     * @param reviewDate 评审日期
     */
    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    /**
     * 评审日期从
     * @return start_date 评审日期从
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 评审日期从
     * @param startDate 评审日期从
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 评审日期至
     * @return end_date 评审日期至
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 评审日期至
     * @param endDate 评审日期至
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
     * 产地
     * @return goods_place 产地
     */
    public String getGoodsPlace() {
        return goodsPlace;
    }

    /**
     * 产地
     * @param goodsPlace 产地
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
     * 有效期至
     * @return valid_until 有效期至
     */
    public Date getValidUntil() {
        return validUntil;
    }

    /**
     * 有效期至
     * @param validUntil 有效期至
     */
    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

    /**
     * 国产/进口（0-国产；1-进口）
     * @return domestic_import_desc 国产/进口（0-国产；1-进口）
     */
    public String getDomesticImportDesc() {
        return domesticImportDesc;
    }

    /**
     * 国产/进口（0-国产；1-进口）
     * @param domesticImportDesc 国产/进口（0-国产；1-进口）
     */
    public void setDomesticImportDesc(String domesticImportDesc) {
        this.domesticImportDesc = domesticImportDesc == null ? null : domesticImportDesc.trim();
    }

    /**
     * 经营范围名称
     * @return management_scope_name 经营范围名称
     */
    public String getManagementScopeName() {
        return managementScopeName;
    }

    /**
     * 经营范围名称
     * @param managementScopeName 经营范围名称
     */
    public void setManagementScopeName(String managementScopeName) {
        this.managementScopeName = managementScopeName == null ? null : managementScopeName.trim();
    }

    /**
     * 特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）
     * @return special_drug_desc 特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）
     */
    public String getSpecialDrugDesc() {
        return specialDrugDesc;
    }

    /**
     * 特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）
     * @param specialDrugDesc 特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）
     */
    public void setSpecialDrugDesc(String specialDrugDesc) {
        this.specialDrugDesc = specialDrugDesc == null ? null : specialDrugDesc.trim();
    }

    /**
     * 专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）
     * @return in_charge_drug_desc 专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）
     */
    public String getInChargeDrugDesc() {
        return inChargeDrugDesc;
    }

    /**
     * 专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）
     * @param inChargeDrugDesc 专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）
     */
    public void setInChargeDrugDesc(String inChargeDrugDesc) {
        this.inChargeDrugDesc = inChargeDrugDesc == null ? null : inChargeDrugDesc.trim();
    }

    /**
     * 限购数量
     * @return limit_quantity 限购数量
     */
    public BigDecimal getLimitQuantity() {
        return limitQuantity;
    }

    /**
     * 限购数量
     * @param limitQuantity 限购数量
     */
    public void setLimitQuantity(BigDecimal limitQuantity) {
        this.limitQuantity = limitQuantity;
    }

    /**
     * 贮藏温度（0-常温；1-阴凉；2-冷藏；3-冷冻）
     * @return storage_temp_desc 贮藏温度（0-常温；1-阴凉；2-冷藏；3-冷冻）
     */
    public String getStorageTempDesc() {
        return storageTempDesc;
    }

    /**
     * 贮藏温度（0-常温；1-阴凉；2-冷藏；3-冷冻）
     * @param storageTempDesc 贮藏温度（0-常温；1-阴凉；2-冷藏；3-冷冻）
     */
    public void setStorageTempDesc(String storageTempDesc) {
        this.storageTempDesc = storageTempDesc == null ? null : storageTempDesc.trim();
    }

    /**
     * 贮藏条件名称
     * @return storage_condition_name 贮藏条件名称
     */
    public String getStorageConditionName() {
        return storageConditionName;
    }

    /**
     * 贮藏条件名称
     * @param storageConditionName 贮藏条件名称
     */
    public void setStorageConditionName(String storageConditionName) {
        this.storageConditionName = storageConditionName == null ? null : storageConditionName.trim();
    }

    /**
     * 保质期描述
     * @return quality_period_desc 保质期描述
     */
    public String getQualityPeriodDesc() {
        return qualityPeriodDesc;
    }

    /**
     * 保质期描述
     * @param qualityPeriodDesc 保质期描述
     */
    public void setQualityPeriodDesc(String qualityPeriodDesc) {
        this.qualityPeriodDesc = qualityPeriodDesc;
    }

    /**
     * 商品属性描述
     * @return goods_attribute_desc 商品属性描述
     */
    public String getGoodsAttributeDesc() {
        return goodsAttributeDesc;
    }

    /**
     * 商品属性描述
     * @param goodsAttributeDesc 商品属性描述
     */
    public void setGoodsAttributeDesc(String goodsAttributeDesc) {
        this.goodsAttributeDesc = goodsAttributeDesc == null ? null : goodsAttributeDesc.trim();
    }

    /**
     * 供货单位ID集合
     * @return supplier_ids 供货单位ID集合
     */
    public String getSupplierIds() {
        return supplierIds;
    }

    /**
     * 供货单位ID集合
     * @param supplierIds 供货单位ID集合
     */
    public void setSupplierIds(String supplierIds) {
        this.supplierIds = supplierIds == null ? null : supplierIds.trim();
    }

    /**
     * 供货单位名称集合
     * @return supplier_names 供货单位名称集合
     */
    public String getSupplierNames() {
        return supplierNames;
    }

    /**
     * 供货单位名称集合
     * @param supplierNames 供货单位名称集合
     */
    public void setSupplierNames(String supplierNames) {
        this.supplierNames = supplierNames == null ? null : supplierNames.trim();
    }

    /**
     * 订单笔数
     * @return order_count 订单笔数
     */
    public Integer getOrderCount() {
        return orderCount;
    }

    /**
     * 订单笔数
     * @param orderCount 订单笔数
     */
    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    /**
     * 订单数量
     * @return order_quanlity 订单数量
     */
    public BigDecimal getOrderQuanlity() {
        return orderQuanlity;
    }

    /**
     * 订单数量
     * @param orderQuanlity 订单数量
     */
    public void setOrderQuanlity(BigDecimal orderQuanlity) {
        this.orderQuanlity = orderQuanlity;
    }

    /**
     * 到货笔数
     * @return arrival_count 到货笔数
     */
    public Integer getArrivalCount() {
        return arrivalCount;
    }

    /**
     * 到货笔数
     * @param arrivalCount 到货笔数
     */
    public void setArrivalCount(Integer arrivalCount) {
        this.arrivalCount = arrivalCount;
    }

    /**
     * 收货数量
     * @return receive_quanlity 收货数量
     */
    public BigDecimal getReceiveQuanlity() {
        return receiveQuanlity;
    }

    /**
     * 收货数量
     * @param receiveQuanlity 收货数量
     */
    public void setReceiveQuanlity(BigDecimal receiveQuanlity) {
        this.receiveQuanlity = receiveQuanlity;
    }

    /**
     * 拒收数量
     * @return reject_quanlity 拒收数量
     */
    public BigDecimal getRejectQuanlity() {
        return rejectQuanlity;
    }

    /**
     * 拒收数量
     * @param rejectQuanlity 拒收数量
     */
    public void setRejectQuanlity(BigDecimal rejectQuanlity) {
        this.rejectQuanlity = rejectQuanlity;
    }

    /**
     * 收货合格率
     * @return receive_qualified_rate 收货合格率
     */
    public BigDecimal getReceiveQualifiedRate() {
        return receiveQualifiedRate;
    }

    /**
     * 收货合格率
     * @param receiveQualifiedRate 收货合格率
     */
    public void setReceiveQualifiedRate(BigDecimal receiveQualifiedRate) {
        this.receiveQualifiedRate = receiveQualifiedRate;
    }

    /**
     * 验收批次（批号个数）
     * @return check_batch 验收批次（批号个数）
     */
    public Integer getCheckBatch() {
        return checkBatch;
    }

    /**
     * 验收批次（批号个数）
     * @param checkBatch 验收批次（批号个数）
     */
    public void setCheckBatch(Integer checkBatch) {
        this.checkBatch = checkBatch;
    }

    /**
     * 验收合格数量
     * @return check_qualified_quantity 验收合格数量
     */
    public BigDecimal getCheckQualifiedQuantity() {
        return checkQualifiedQuantity;
    }

    /**
     * 验收合格数量
     * @param checkQualifiedQuantity 验收合格数量
     */
    public void setCheckQualifiedQuantity(BigDecimal checkQualifiedQuantity) {
        this.checkQualifiedQuantity = checkQualifiedQuantity;
    }

    /**
     * 验收不合格数量
     * @return check_unqualified_quantity 验收不合格数量
     */
    public BigDecimal getCheckUnqualifiedQuantity() {
        return checkUnqualifiedQuantity;
    }

    /**
     * 验收不合格数量
     * @param checkUnqualifiedQuantity 验收不合格数量
     */
    public void setCheckUnqualifiedQuantity(BigDecimal checkUnqualifiedQuantity) {
        this.checkUnqualifiedQuantity = checkUnqualifiedQuantity;
    }

    /**
     * 验收合格率
     * @return check_qualified_rate 验收合格率
     */
    public BigDecimal getCheckQualifiedRate() {
        return checkQualifiedRate;
    }

    /**
     * 验收合格率
     * @param checkQualifiedRate 验收合格率
     */
    public void setCheckQualifiedRate(BigDecimal checkQualifiedRate) {
        this.checkQualifiedRate = checkQualifiedRate;
    }

    /**
     * 购进退出笔数
     * @return purchase_return_count 购进退出笔数
     */
    public Integer getPurchaseReturnCount() {
        return purchaseReturnCount;
    }

    /**
     * 购进退出笔数
     * @param purchaseReturnCount 购进退出笔数
     */
    public void setPurchaseReturnCount(Integer purchaseReturnCount) {
        this.purchaseReturnCount = purchaseReturnCount;
    }

    /**
     * 购进退出批次（批号个数）
     * @return purchase_return_batch 购进退出批次（批号个数）
     */
    public Integer getPurchaseReturnBatch() {
        return purchaseReturnBatch;
    }

    /**
     * 购进退出批次（批号个数）
     * @param purchaseReturnBatch 购进退出批次（批号个数）
     */
    public void setPurchaseReturnBatch(Integer purchaseReturnBatch) {
        this.purchaseReturnBatch = purchaseReturnBatch;
    }

    /**
     * 购进退出数量
     * @return purchase_return_quantity 购进退出数量
     */
    public BigDecimal getPurchaseReturnQuantity() {
        return purchaseReturnQuantity;
    }

    /**
     * 购进退出数量
     * @param purchaseReturnQuantity 购进退出数量
     */
    public void setPurchaseReturnQuantity(BigDecimal purchaseReturnQuantity) {
        this.purchaseReturnQuantity = purchaseReturnQuantity;
    }

    /**
     * 销售笔数
     * @return sale_count 销售笔数
     */
    public Integer getSaleCount() {
        return saleCount;
    }

    /**
     * 销售笔数
     * @param saleCount 销售笔数
     */
    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }

    /**
     * 销售批次（批号个数）
     * @return sale_batch 销售批次（批号个数）
     */
    public Integer getSaleBatch() {
        return saleBatch;
    }

    /**
     * 销售批次（批号个数）
     * @param saleBatch 销售批次（批号个数）
     */
    public void setSaleBatch(Integer saleBatch) {
        this.saleBatch = saleBatch;
    }

    /**
     * 销售数量
     * @return sale_quantity 销售数量
     */
    public BigDecimal getSaleQuantity() {
        return saleQuantity;
    }

    /**
     * 销售数量
     * @param saleQuantity 销售数量
     */
    public void setSaleQuantity(BigDecimal saleQuantity) {
        this.saleQuantity = saleQuantity;
    }

    /**
     * 销后退回笔数
     * @return sale_return_count 销后退回笔数
     */
    public Integer getSaleReturnCount() {
        return saleReturnCount;
    }

    /**
     * 销后退回笔数
     * @param saleReturnCount 销后退回笔数
     */
    public void setSaleReturnCount(Integer saleReturnCount) {
        this.saleReturnCount = saleReturnCount;
    }

    /**
     * 销后退回批次（批号个数）
     * @return sale_return_batch 销后退回批次（批号个数）
     */
    public Integer getSaleReturnBatch() {
        return saleReturnBatch;
    }

    /**
     * 销后退回批次（批号个数）
     * @param saleReturnBatch 销后退回批次（批号个数）
     */
    public void setSaleReturnBatch(Integer saleReturnBatch) {
        this.saleReturnBatch = saleReturnBatch;
    }

    /**
     * 销后退回数量
     * @return sale_return_quantity 销后退回数量
     */
    public BigDecimal getSaleReturnQuantity() {
        return saleReturnQuantity;
    }

    /**
     * 销后退回数量
     * @param saleReturnQuantity 销后退回数量
     */
    public void setSaleReturnQuantity(BigDecimal saleReturnQuantity) {
        this.saleReturnQuantity = saleReturnQuantity;
    }

    /**
     * 销毁笔数
     * @return destroy_count 销毁笔数
     */
    public Integer getDestroyCount() {
        return destroyCount;
    }

    /**
     * 销毁笔数
     * @param destroyCount 销毁笔数
     */
    public void setDestroyCount(Integer destroyCount) {
        this.destroyCount = destroyCount;
    }

    /**
     * 销毁批次（批号个数）
     * @return destroy_batch 销毁批次（批号个数）
     */
    public Integer getDestroyBatch() {
        return destroyBatch;
    }

    /**
     * 销毁批次（批号个数）
     * @param destroyBatch 销毁批次（批号个数）
     */
    public void setDestroyBatch(Integer destroyBatch) {
        this.destroyBatch = destroyBatch;
    }

    /**
     * 销毁数量
     * @return destroy_quantity 销毁数量
     */
    public BigDecimal getDestroyQuantity() {
        return destroyQuantity;
    }

    /**
     * 销毁数量
     * @param destroyQuantity 销毁数量
     */
    public void setDestroyQuantity(BigDecimal destroyQuantity) {
        this.destroyQuantity = destroyQuantity;
    }

    /**
     * 投诉笔数
     * @return complain_count 投诉笔数
     */
    public Integer getComplainCount() {
        return complainCount;
    }

    /**
     * 投诉笔数
     * @param complainCount 投诉笔数
     */
    public void setComplainCount(Integer complainCount) {
        this.complainCount = complainCount;
    }

    /**
     * 投诉批次
     * @return complain_batch 投诉批次
     */
    public Integer getComplainBatch() {
        return complainBatch;
    }

    /**
     * 投诉批次
     * @param complainBatch 投诉批次
     */
    public void setComplainBatch(Integer complainBatch) {
        this.complainBatch = complainBatch;
    }

    /**
     * 不良反应笔数
     * @return adverse_reaction_count 不良反应笔数
     */
    public Integer getAdverseReactionCount() {
        return adverseReactionCount;
    }

    /**
     * 不良反应笔数
     * @param adverseReactionCount 不良反应笔数
     */
    public void setAdverseReactionCount(Integer adverseReactionCount) {
        this.adverseReactionCount = adverseReactionCount;
    }

    /**
     * 不良反应批次
     * @return adverse_reaction_batch 不良反应批次
     */
    public Integer getAdverseReactionBatch() {
        return adverseReactionBatch;
    }

    /**
     * 不良反应批次
     * @param adverseReactionBatch 不良反应批次
     */
    public void setAdverseReactionBatch(Integer adverseReactionBatch) {
        this.adverseReactionBatch = adverseReactionBatch;
    }

    /**
     * 召回数量
     * @return recall_quantity 召回数量
     */
    public BigDecimal getRecallQuantity() {
        return recallQuantity;
    }

    /**
     * 召回数量
     * @param recallQuantity 召回数量
     */
    public void setRecallQuantity(BigDecimal recallQuantity) {
        this.recallQuantity = recallQuantity;
    }

    /**
     * 召回批次
     * @return recall_batch 召回批次
     */
    public Integer getRecallBatch() {
        return recallBatch;
    }

    /**
     * 召回批次
     * @param recallBatch 召回批次
     */
    public void setRecallBatch(Integer recallBatch) {
        this.recallBatch = recallBatch;
    }

    /**
     * 追回数量
     * @return recover_quantity 追回数量
     */
    public Integer getRecoverQuantity() {
        return recoverQuantity;
    }

    /**
     * 追回数量
     * @param recoverQuantity 追回数量
     */
    public void setRecoverQuantity(Integer recoverQuantity) {
        this.recoverQuantity = recoverQuantity;
    }

    /**
     * 追回批次
     * @return recover_batch 追回批次
     */
    public Integer getRecoverBatch() {
        return recoverBatch;
    }

    /**
     * 追回批次
     * @param recoverBatch 追回批次
     */
    public void setRecoverBatch(Integer recoverBatch) {
        this.recoverBatch = recoverBatch;
    }

    /**
     * 业务部门评审意见
     * @return business_opinion 业务部门评审意见
     */
    public String getBusinessOpinion() {
        return businessOpinion;
    }

    /**
     * 业务部门评审意见
     * @param businessOpinion 业务部门评审意见
     */
    public void setBusinessOpinion(String businessOpinion) {
        this.businessOpinion = businessOpinion == null ? null : businessOpinion.trim();
    }

    /**
     * 业务部门评审时间
     * @return business_review_time 业务部门评审时间
     */
    public Date getBusinessReviewTime() {
        return businessReviewTime;
    }

    /**
     * 业务部门评审时间
     * @param businessReviewTime 业务部门评审时间
     */
    public void setBusinessReviewTime(Date businessReviewTime) {
        this.businessReviewTime = businessReviewTime;
    }

    /**
     * 业务部门评审人员
     * @return business_reviewer 业务部门评审人员
     */
    public String getBusinessReviewer() {
        return businessReviewer;
    }

    /**
     * 业务部门评审人员
     * @param businessReviewer 业务部门评审人员
     */
    public void setBusinessReviewer(String businessReviewer) {
        this.businessReviewer = businessReviewer == null ? null : businessReviewer.trim();
    }

    /**
     * 质量部门评审意见
     * @return quality_opinion 质量部门评审意见
     */
    public String getQualityOpinion() {
        return qualityOpinion;
    }

    /**
     * 质量部门评审意见
     * @param qualityOpinion 质量部门评审意见
     */
    public void setQualityOpinion(String qualityOpinion) {
        this.qualityOpinion = qualityOpinion == null ? null : qualityOpinion.trim();
    }

    /**
     * 质量部门评审时间
     * @return quality_review_time 质量部门评审时间
     */
    public Date getQualityReviewTime() {
        return qualityReviewTime;
    }

    /**
     * 质量部门评审时间
     * @param qualityReviewTime 质量部门评审时间
     */
    public void setQualityReviewTime(Date qualityReviewTime) {
        this.qualityReviewTime = qualityReviewTime;
    }

    /**
     * 质量部门评审人员
     * @return quality_reviewer 质量部门评审人员
     */
    public String getQualityReviewer() {
        return qualityReviewer;
    }

    /**
     * 质量部门评审人员
     * @param qualityReviewer 质量部门评审人员
     */
    public void setQualityReviewer(String qualityReviewer) {
        this.qualityReviewer = qualityReviewer == null ? null : qualityReviewer.trim();
    }

    /**
     * 总经理评审意见
     * @return manager_opinion 总经理评审意见
     */
    public String getManagerOpinion() {
        return managerOpinion;
    }

    /**
     * 总经理评审意见
     * @param managerOpinion 总经理评审意见
     */
    public void setManagerOpinion(String managerOpinion) {
        this.managerOpinion = managerOpinion == null ? null : managerOpinion.trim();
    }

    /**
     * 总经理评审时间
     * @return manager_review_time 总经理评审时间
     */
    public Date getManagerReviewTime() {
        return managerReviewTime;
    }

    /**
     * 总经理评审时间
     * @param managerReviewTime 总经理评审时间
     */
    public void setManagerReviewTime(Date managerReviewTime) {
        this.managerReviewTime = managerReviewTime;
    }

    /**
     * 总经理评审人员
     * @return manager_reviewer 总经理评审人员
     */
    public String getManagerReviewer() {
        return managerReviewer;
    }

    /**
     * 总经理评审人员
     * @param managerReviewer 总经理评审人员
     */
    public void setManagerReviewer(String managerReviewer) {
        this.managerReviewer = managerReviewer == null ? null : managerReviewer.trim();
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

    /**
     * 创建人ID
     * @return creater_id 创建人ID
     */
    public Long getCreaterId() {
        return createrId;
    }

    /**
     * 创建人ID
     * @param createrId 创建人ID
     */
    public void setCreaterId(Long createrId) {
        this.createrId = createrId;
    }

    /**
     * 创建人编码
     * @return creater_code 创建人编码
     */
    public String getCreaterCode() {
        return createrCode;
    }

    /**
     * 创建人编码
     * @param createrCode 创建人编码
     */
    public void setCreaterCode(String createrCode) {
        this.createrCode = createrCode == null ? null : createrCode.trim();
    }

    /**
     * 创建人名称
     * @return creater_name 创建人名称
     */
    public String getCreaterName() {
        return createrName;
    }

    /**
     * 创建人名称
     * @param createrName 创建人名称
     */
    public void setCreaterName(String createrName) {
        this.createrName = createrName == null ? null : createrName.trim();
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 最后修改人ID
     * @return modifier_id 最后修改人ID
     */
    public Long getModifierId() {
        return modifierId;
    }

    /**
     * 最后修改人ID
     * @param modifierId 最后修改人ID
     */
    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    /**
     * 最后修改人编码
     * @return modifier_code 最后修改人编码
     */
    public String getModifierCode() {
        return modifierCode;
    }

    /**
     * 最后修改人编码
     * @param modifierCode 最后修改人编码
     */
    public void setModifierCode(String modifierCode) {
        this.modifierCode = modifierCode == null ? null : modifierCode.trim();
    }

    /**
     * 最后修改人名称
     * @return modifier_name 最后修改人名称
     */
    public String getModifierName() {
        return modifierName;
    }

    /**
     * 最后修改人名称
     * @param modifierName 最后修改人名称
     */
    public void setModifierName(String modifierName) {
        this.modifierName = modifierName == null ? null : modifierName.trim();
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", enterpriseId=").append(enterpriseId);
        sb.append(", parentId=").append(parentId);
        sb.append(", reviewCode=").append(reviewCode);
        sb.append(", reviewDate=").append(reviewDate);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", goodsCode=").append(goodsCode);
        sb.append(", barcode=").append(barcode);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", goodsGenericName=").append(goodsGenericName);
        sb.append(", dosageId=").append(dosageId);
        sb.append(", dosageName=").append(dosageName);
        sb.append(", unitId=").append(unitId);
        sb.append(", unitName=").append(unitName);
        sb.append(", goodsSpecification=").append(goodsSpecification);
        sb.append(", manufacturerId=").append(manufacturerId);
        sb.append(", manufacturer=").append(manufacturer);
        sb.append(", goodsPlace=").append(goodsPlace);
        sb.append(", approvalNumber=").append(approvalNumber);
        sb.append(", validUntil=").append(validUntil);
        sb.append(", domesticImportDesc=").append(domesticImportDesc);
        sb.append(", managementScopeName=").append(managementScopeName);
        sb.append(", specialDrugDesc=").append(specialDrugDesc);
        sb.append(", inChargeDrugDesc=").append(inChargeDrugDesc);
        sb.append(", limitQuantity=").append(limitQuantity);
        sb.append(", storageTempDesc=").append(storageTempDesc);
        sb.append(", storageConditionName=").append(storageConditionName);
        sb.append(", qualityPeriodDesc=").append(qualityPeriodDesc);
        sb.append(", goodsAttributeDesc=").append(goodsAttributeDesc);
        sb.append(", supplierIds=").append(supplierIds);
        sb.append(", supplierNames=").append(supplierNames);
        sb.append(", orderCount=").append(orderCount);
        sb.append(", orderQuanlity=").append(orderQuanlity);
        sb.append(", arrivalCount=").append(arrivalCount);
        sb.append(", receiveQuanlity=").append(receiveQuanlity);
        sb.append(", rejectQuanlity=").append(rejectQuanlity);
        sb.append(", receiveQualifiedRate=").append(receiveQualifiedRate);
        sb.append(", checkBatch=").append(checkBatch);
        sb.append(", checkQualifiedQuantity=").append(checkQualifiedQuantity);
        sb.append(", checkUnqualifiedQuantity=").append(checkUnqualifiedQuantity);
        sb.append(", checkQualifiedRate=").append(checkQualifiedRate);
        sb.append(", purchaseReturnCount=").append(purchaseReturnCount);
        sb.append(", purchaseReturnBatch=").append(purchaseReturnBatch);
        sb.append(", purchaseReturnQuantity=").append(purchaseReturnQuantity);
        sb.append(", saleCount=").append(saleCount);
        sb.append(", saleBatch=").append(saleBatch);
        sb.append(", saleQuantity=").append(saleQuantity);
        sb.append(", saleReturnCount=").append(saleReturnCount);
        sb.append(", saleReturnBatch=").append(saleReturnBatch);
        sb.append(", saleReturnQuantity=").append(saleReturnQuantity);
        sb.append(", destroyCount=").append(destroyCount);
        sb.append(", destroyBatch=").append(destroyBatch);
        sb.append(", destroyQuantity=").append(destroyQuantity);
        sb.append(", complainCount=").append(complainCount);
        sb.append(", complainBatch=").append(complainBatch);
        sb.append(", adverseReactionCount=").append(adverseReactionCount);
        sb.append(", adverseReactionBatch=").append(adverseReactionBatch);
        sb.append(", recallQuantity=").append(recallQuantity);
        sb.append(", recallBatch=").append(recallBatch);
        sb.append(", recoverQuantity=").append(recoverQuantity);
        sb.append(", recoverBatch=").append(recoverBatch);
        sb.append(", businessOpinion=").append(businessOpinion);
        sb.append(", businessReviewTime=").append(businessReviewTime);
        sb.append(", businessReviewer=").append(businessReviewer);
        sb.append(", qualityOpinion=").append(qualityOpinion);
        sb.append(", qualityReviewTime=").append(qualityReviewTime);
        sb.append(", qualityReviewer=").append(qualityReviewer);
        sb.append(", managerOpinion=").append(managerOpinion);
        sb.append(", managerReviewTime=").append(managerReviewTime);
        sb.append(", managerReviewer=").append(managerReviewer);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", createrId=").append(createrId);
        sb.append(", createrCode=").append(createrCode);
        sb.append(", createrName=").append(createrName);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifierId=").append(modifierId);
        sb.append(", modifierCode=").append(modifierCode);
        sb.append(", modifierName=").append(modifierName);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}