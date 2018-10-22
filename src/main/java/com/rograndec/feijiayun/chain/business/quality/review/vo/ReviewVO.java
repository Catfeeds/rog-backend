package com.rograndec.feijiayun.chain.business.quality.review.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ReviewVO  implements Serializable{

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;


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
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    /**
     * 条形码
     */
    @ApiModelProperty(value = "条形码")
    private String barcode;

    /**
     * 通用名称
     */
    @ApiModelProperty(value = "通用名称")
    private String genericName;

    /**
     * 剂型ID
     */
    @ApiModelProperty(value="剂型ID")
    private Long dosageId;

    /**
     * 剂型名称
     */
    @ApiModelProperty(value="剂型名称")
    private String dosageName;

    /**
     * 规格
     */
    @ApiModelProperty(value="规格")
    private String specification;

    /**
     * 库存计量单位ID
     */
    @ApiModelProperty(value=" 库存计量单位ID")
    private Long unitId;

    /**
     * 库存计量单位名称
     */
    @ApiModelProperty(value="库存计量单位名称")
    private String unitName;

    /**
     * 生产厂商
     */
    @ApiModelProperty(value="生产厂商")
    private String manufacturer;

    /**
     * 产地
     */
    @ApiModelProperty(value="产地")
    private String place;

    /**
     * 批准文号
     */
    @ApiModelProperty(value="批准文号")
    private String approvalNumber;

    /**
     * 有效期至
     */
    @ApiModelProperty(value="有效期至")
    private Date validUntil;

    /**
     * 经营范围ID
     */
    @ApiModelProperty(value="经营范围ID")
    private Long managementScopeId;

    /**
     * 经营范围名称
     */
    @ApiModelProperty(value=" 经营范围名称")
    private String managementScopeName;

    /**
     * 国产/进口（0-国产；1-进口）
     */
    @ApiModelProperty(value="国产/进口（0-国产；1-进口）")
    private Integer domesticImport;

    @ApiModelProperty(value="国产/进口描述")
    private String domesticImportName;


    /**
     *
     * 特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）
     */
    @ApiModelProperty(value="特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）")
    private Integer specialDrug;

    @ApiModelProperty(value="特殊管理药品名称")
    private String specialDrugName;


    /**
     * 专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）
     */
    @ApiModelProperty(value="专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）")
    private Integer inChargeDrug;

    @ApiModelProperty(value="专管药品名称")
    private String inChargeDrugName;

    /**
     * 限购数量
     */
    @ApiModelProperty(value="限购数量")
    private BigDecimal limitQuantity;

    /**
     * 贮藏温度（0-常温；1-阴凉；2-冷藏；3-冷冻）
     */
    @ApiModelProperty(value=" 贮藏温度（0-常温；1-阴凉；2-冷藏；3-冷冻）")
    private Integer storageTemp;

    @ApiModelProperty(value=" 贮藏温度")
    private String storageTempName;



    /**
     * 贮藏条件名称
     */
    @ApiModelProperty(value="贮藏条件名称")
    private String storageConditionName;

    /**
     * 保质期
     */
    @ApiModelProperty(value="保质期")
    private Integer qualityPeriod;

    /**
     * 保质期单位（0-日；1-月；2-年）
     */
    @ApiModelProperty(value="保质期单位（0-日；1-月；2-年）")
    private Integer qualityPeriodUnit;

    @ApiModelProperty(value="保质期单位名称")
    private String  qualityPeriodUnitName;

    @ApiModelProperty(value="商品属性描述")
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
    private BigDecimal recoverQuantity;

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


    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
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

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
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

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
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

    public Long getManagementScopeId() {
        return managementScopeId;
    }

    public void setManagementScopeId(Long managementScopeId) {
        this.managementScopeId = managementScopeId;
    }

    public String getManagementScopeName() {
        return managementScopeName;
    }

    public void setManagementScopeName(String managementScopeName) {
        this.managementScopeName = managementScopeName;
    }

    public Integer getDomesticImport() {
        return domesticImport;
    }

    public void setDomesticImport(Integer domesticImport) {
        this.domesticImport = domesticImport;
    }

    public Integer getSpecialDrug() {
        return specialDrug;
    }

    public void setSpecialDrug(Integer specialDrug) {
        this.specialDrug = specialDrug;
    }

    public Integer getInChargeDrug() {
        return inChargeDrug;
    }

    public void setInChargeDrug(Integer inChargeDrug) {
        this.inChargeDrug = inChargeDrug;
    }

    public BigDecimal getLimitQuantity() {
        return limitQuantity;
    }

    public void setLimitQuantity(BigDecimal limitQuantity) {
        this.limitQuantity = limitQuantity;
    }

    public Integer getStorageTemp() {
        return storageTemp;
    }

    public void setStorageTemp(Integer storageTemp) {
        this.storageTemp = storageTemp;
    }


    public String getStorageConditionName() {
        return storageConditionName;
    }

    public void setStorageConditionName(String storageConditionName) {
        this.storageConditionName = storageConditionName;
    }

    public Integer getQualityPeriod() {
        return qualityPeriod;
    }

    public void setQualityPeriod(Integer qualityPeriod) {
        this.qualityPeriod = qualityPeriod;
    }

    public Integer getQualityPeriodUnit() {
        return qualityPeriodUnit;
    }

    public void setQualityPeriodUnit(Integer qualityPeriodUnit) {
        this.qualityPeriodUnit = qualityPeriodUnit;
    }


    public String getDomesticImportName() {
        return domesticImportName;
    }

    public void setDomesticImportName(String domesticImportName) {
        this.domesticImportName = domesticImportName;
    }

    public String getSpecialDrugName() {
        return specialDrugName;
    }

    public void setSpecialDrugName(String specialDrugName) {
        this.specialDrugName = specialDrugName;
    }

    public String getInChargeDrugName() {
        return inChargeDrugName;
    }

    public void setInChargeDrugName(String inChargeDrugName) {
        this.inChargeDrugName = inChargeDrugName;
    }

    public String getStorageTempName() {
        return storageTempName;
    }

    public void setStorageTempName(String storageTempName) {
        this.storageTempName = storageTempName;
    }

    public String getQualityPeriodUnitName() {
        return qualityPeriodUnitName;
    }

    public void setQualityPeriodUnitName(String qualityPeriodUnitName) {
        this.qualityPeriodUnitName = qualityPeriodUnitName;
    }

    public String getGoodsAttributeDesc() {
        return goodsAttributeDesc;
    }

    public void setGoodsAttributeDesc(String goodsAttributeDesc) {
        this.goodsAttributeDesc = goodsAttributeDesc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReviewCode() {
        return reviewCode;
    }

    public void setReviewCode(String reviewCode) {
        this.reviewCode = reviewCode;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    public String getSupplierIds() {
        return supplierIds;
    }

    public void setSupplierIds(String supplierIds) {
        this.supplierIds = supplierIds;
    }

    public String getSupplierNames() {
        return supplierNames;
    }

    public void setSupplierNames(String supplierNames) {
        this.supplierNames = supplierNames;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public BigDecimal getOrderQuanlity() {
        return orderQuanlity;
    }

    public void setOrderQuanlity(BigDecimal orderQuanlity) {
        this.orderQuanlity = orderQuanlity;
    }

    public Integer getArrivalCount() {
        return arrivalCount;
    }

    public void setArrivalCount(Integer arrivalCount) {
        this.arrivalCount = arrivalCount;
    }

    public BigDecimal getReceiveQuanlity() {
        return receiveQuanlity;
    }

    public void setReceiveQuanlity(BigDecimal receiveQuanlity) {
        this.receiveQuanlity = receiveQuanlity;
    }

    public BigDecimal getRejectQuanlity() {
        return rejectQuanlity;
    }

    public void setRejectQuanlity(BigDecimal rejectQuanlity) {
        this.rejectQuanlity = rejectQuanlity;
    }

    public BigDecimal getReceiveQualifiedRate() {
        return receiveQualifiedRate;
    }

    public void setReceiveQualifiedRate(BigDecimal receiveQualifiedRate) {
        this.receiveQualifiedRate = receiveQualifiedRate;
    }

    public Integer getCheckBatch() {
        return checkBatch;
    }

    public void setCheckBatch(Integer checkBatch) {
        this.checkBatch = checkBatch;
    }

    public BigDecimal getCheckQualifiedQuantity() {
        return checkQualifiedQuantity;
    }

    public void setCheckQualifiedQuantity(BigDecimal checkQualifiedQuantity) {
        this.checkQualifiedQuantity = checkQualifiedQuantity;
    }

    public BigDecimal getCheckUnqualifiedQuantity() {
        return checkUnqualifiedQuantity;
    }

    public void setCheckUnqualifiedQuantity(BigDecimal checkUnqualifiedQuantity) {
        this.checkUnqualifiedQuantity = checkUnqualifiedQuantity;
    }

    public BigDecimal getCheckQualifiedRate() {
        return checkQualifiedRate;
    }

    public void setCheckQualifiedRate(BigDecimal checkQualifiedRate) {
        this.checkQualifiedRate = checkQualifiedRate;
    }

    public Integer getPurchaseReturnCount() {
        return purchaseReturnCount;
    }

    public void setPurchaseReturnCount(Integer purchaseReturnCount) {
        this.purchaseReturnCount = purchaseReturnCount;
    }

    public Integer getPurchaseReturnBatch() {
        return purchaseReturnBatch;
    }

    public void setPurchaseReturnBatch(Integer purchaseReturnBatch) {
        this.purchaseReturnBatch = purchaseReturnBatch;
    }

    public BigDecimal getPurchaseReturnQuantity() {
        return purchaseReturnQuantity;
    }

    public void setPurchaseReturnQuantity(BigDecimal purchaseReturnQuantity) {
        this.purchaseReturnQuantity = purchaseReturnQuantity;
    }

    public Integer getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }

    public Integer getSaleBatch() {
        return saleBatch;
    }

    public void setSaleBatch(Integer saleBatch) {
        this.saleBatch = saleBatch;
    }

    public BigDecimal getSaleQuantity() {
        return saleQuantity;
    }

    public void setSaleQuantity(BigDecimal saleQuantity) {
        this.saleQuantity = saleQuantity;
    }

    public Integer getSaleReturnCount() {
        return saleReturnCount;
    }

    public void setSaleReturnCount(Integer saleReturnCount) {
        this.saleReturnCount = saleReturnCount;
    }

    public Integer getSaleReturnBatch() {
        return saleReturnBatch;
    }

    public void setSaleReturnBatch(Integer saleReturnBatch) {
        this.saleReturnBatch = saleReturnBatch;
    }

    public BigDecimal getSaleReturnQuantity() {
        return saleReturnQuantity;
    }

    public void setSaleReturnQuantity(BigDecimal saleReturnQuantity) {
        this.saleReturnQuantity = saleReturnQuantity;
    }

    public Integer getDestroyCount() {
        return destroyCount;
    }

    public void setDestroyCount(Integer destroyCount) {
        this.destroyCount = destroyCount;
    }

    public Integer getDestroyBatch() {
        return destroyBatch;
    }

    public void setDestroyBatch(Integer destroyBatch) {
        this.destroyBatch = destroyBatch;
    }

    public BigDecimal getDestroyQuantity() {
        return destroyQuantity;
    }

    public void setDestroyQuantity(BigDecimal destroyQuantity) {
        this.destroyQuantity = destroyQuantity;
    }

    public Integer getComplainCount() {
        return complainCount;
    }

    public void setComplainCount(Integer complainCount) {
        this.complainCount = complainCount;
    }

    public Integer getComplainBatch() {
        return complainBatch;
    }

    public void setComplainBatch(Integer complainBatch) {
        this.complainBatch = complainBatch;
    }

    public Integer getAdverseReactionCount() {
        return adverseReactionCount;
    }

    public void setAdverseReactionCount(Integer adverseReactionCount) {
        this.adverseReactionCount = adverseReactionCount;
    }

    public Integer getAdverseReactionBatch() {
        return adverseReactionBatch;
    }

    public void setAdverseReactionBatch(Integer adverseReactionBatch) {
        this.adverseReactionBatch = adverseReactionBatch;
    }

    public BigDecimal getRecallQuantity() {
        return recallQuantity;
    }

    public void setRecallQuantity(BigDecimal recallQuantity) {
        this.recallQuantity = recallQuantity;
    }

    public Integer getRecallBatch() {
        return recallBatch;
    }

    public void setRecallBatch(Integer recallBatch) {
        this.recallBatch = recallBatch;
    }

    public BigDecimal getRecoverQuantity() {
        return recoverQuantity;
    }

    public void setRecoverQuantity(BigDecimal recoverQuantity) {
        this.recoverQuantity = recoverQuantity;
    }

    public Integer getRecoverBatch() {
        return recoverBatch;
    }

    public void setRecoverBatch(Integer recoverBatch) {
        this.recoverBatch = recoverBatch;
    }

    public String getBusinessOpinion() {
        return businessOpinion;
    }

    public void setBusinessOpinion(String businessOpinion) {
        this.businessOpinion = businessOpinion;
    }

    public Date getBusinessReviewTime() {
        return businessReviewTime;
    }

    public void setBusinessReviewTime(Date businessReviewTime) {
        this.businessReviewTime = businessReviewTime;
    }

    public String getBusinessReviewer() {
        return businessReviewer;
    }

    public void setBusinessReviewer(String businessReviewer) {
        this.businessReviewer = businessReviewer;
    }

    public String getQualityOpinion() {
        return qualityOpinion;
    }

    public void setQualityOpinion(String qualityOpinion) {
        this.qualityOpinion = qualityOpinion;
    }

    public Date getQualityReviewTime() {
        return qualityReviewTime;
    }

    public void setQualityReviewTime(Date qualityReviewTime) {
        this.qualityReviewTime = qualityReviewTime;
    }

    public String getQualityReviewer() {
        return qualityReviewer;
    }

    public void setQualityReviewer(String qualityReviewer) {
        this.qualityReviewer = qualityReviewer;
    }

    public String getManagerOpinion() {
        return managerOpinion;
    }

    public void setManagerOpinion(String managerOpinion) {
        this.managerOpinion = managerOpinion;
    }

    public Date getManagerReviewTime() {
        return managerReviewTime;
    }

    public void setManagerReviewTime(Date managerReviewTime) {
        this.managerReviewTime = managerReviewTime;
    }

    public String getManagerReviewer() {
        return managerReviewer;
    }

    public void setManagerReviewer(String managerReviewer) {
        this.managerReviewer = managerReviewer;
    }
}
