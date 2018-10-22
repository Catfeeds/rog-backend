package com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class ReceivePageVO implements Serializable{

    /**
     * 报表类别(0-收货 1拒收)
     */
    @ApiModelProperty(value="报表类别(0-收货 1拒收)")
    private Integer reportType;
    /**
     * 品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）
     */
    @ApiModelProperty(value="品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）")
    private Integer businessVariety;

    /**
     * 商品搜索参数
     */
    @ApiModelProperty(value = "商品搜索参数")
    private String param;

    /**
     * 收货开始日期
     */
    @ApiModelProperty("收货(拒收)开始日期")
    private Date startDate;

    /**
     * 收货结束日期
     */
    @ApiModelProperty("收货(拒收)结束日期")
    private Date endDate;

    /**
     * 供货单位编码
     */
    @ApiModelProperty(value = "供货单位编码")
    private String supplierCode;

    /**
     * 供货单位名称
     */
    @ApiModelProperty(value = "供货单位名称")
    private String supplierName;

    /**
     * 收货单号
     */
    @ApiModelProperty(value = "收货单号(拒收单号)")
    private String code;

    /**
     * 收货人员
     */
    @ApiModelProperty(value = "收货人员(拒收单的拒收人员)")
    private String receiverName;

    /**
     * 第二收货人员
     */
    @ApiModelProperty(value = "第二收货人员")
    private String secondReceiverName;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态 (33, 已完成, 61 已收货)")
    private Integer status;

    /**
     * 商品分类ID
     */
    @ApiModelProperty(value="商品分类ID")
    private Long categoryId;

    /**
     * 国产/进口（0-国产；1-进口）
     */
    @ApiModelProperty(value="国产/进口（0-国产；1-进口）")
    private Integer domesticImport;

    /**
     * 贮藏温度（0-常温；1-阴凉；2-冷藏；3-冷冻）
     */
    @ApiModelProperty(value=" 贮藏温度（0-常温；1-阴凉；2-冷藏；3-冷冻）")
    private Integer storageTemp;

    /**
     * 贮藏条件
     */
    @ApiModelProperty(value="贮藏条件")
    private String storageConditionName;

    /**
     * 商品属性（0-成药；1-中药材；2-中药饮片）
     */
    @ApiModelProperty(value="商品属性（0-成药；1-中药材；2-中药饮片）")
    private Integer goodsAttribute;

    /**
     * 商品属性-是否为处方药，0：非处方药，1：处方药
     */
    @ApiModelProperty(value="商品属性-是否为处方药，0：非处方药，1：处方药")
    private Integer prescriptionDrug;

    /**
     * 非处方药类别：0-甲类；1-乙类；
     */
    @ApiModelProperty(value="非处方药类别：0-甲类；1-乙类；")
    private Integer otcType;

    /**
     * 经营范围ID
     */
    @ApiModelProperty(value="经营范围ID")
    private Long managementScopeId;

    /**
     *
     * 特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）
     */
    @ApiModelProperty(value="特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）")
    private Integer specialDrug;

    /**
     * 精神药品分类（0-一类；1-二类）
     */
    @ApiModelProperty(value="精神药品分类（0-一类；1-二类）")
    private Integer spiritDrugType;

    /**
     * 专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）
     */
    @ApiModelProperty(value="专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）")
    private Integer inChargeDrug;

    /**
     *  含特殊药品复方制剂类型（0-含可卡因复方口服液；1-含麻黄碱类复方制剂；2-复方地芬诺酯片；3-复方甘草片）
     */
    @ApiModelProperty(value="含特殊药品复方制剂类型（0-含可卡因复方口服液；1-含麻黄碱类复方制剂；2-复方地芬诺酯片；3-复方甘草片）")
    private Integer formulationType;

    /**
     * 注册商标
     */
    @ApiModelProperty(value = "注册商标")
    private String registeredTrademark;

    /**
     * 品牌
     */
    @ApiModelProperty(value = "品牌")
    private String brand;

    /**
     * 待排序字段
     */
    @ApiModelProperty(value = "待排序字段")
    private String orderName;

    /**
     * 排序方式
     */
    @ApiModelProperty(value = "排序方式")
    private String orderType;

    /**
     * 页码
     */
    @ApiModelProperty(value = "页码", required = true)
    private Integer pageNo;

    /**
     * 页容量
     */
    @ApiModelProperty(value = "页容量", required = true)
    private Integer pageSize;

    public Integer getBusinessVariety() {
        return businessVariety;
    }

    public void setBusinessVariety(Integer businessVariety) {
        this.businessVariety = businessVariety;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
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

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSecondReceiverName() {
        return secondReceiverName;
    }

    public void setSecondReceiverName(String secondReceiverName) {
        this.secondReceiverName = secondReceiverName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getDomesticImport() {
        return domesticImport;
    }

    public void setDomesticImport(Integer domesticImport) {
        this.domesticImport = domesticImport;
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

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public Integer getReportType() {
        return reportType;
    }

    public void setReportType(Integer reportType) {
        this.reportType = reportType;
    }

    public Integer getGoodsAttribute() {
        return goodsAttribute;
    }

    public void setGoodsAttribute(Integer goodsAttribute) {
        this.goodsAttribute = goodsAttribute;
    }

    public Integer getPrescriptionDrug() {
        return prescriptionDrug;
    }

    public void setPrescriptionDrug(Integer prescriptionDrug) {
        this.prescriptionDrug = prescriptionDrug;
    }

    public Integer getOtcType() {
        return otcType;
    }

    public void setOtcType(Integer otcType) {
        this.otcType = otcType;
    }

    public Long getManagementScopeId() {
        return managementScopeId;
    }

    public void setManagementScopeId(Long managementScopeId) {
        this.managementScopeId = managementScopeId;
    }

    public Integer getSpecialDrug() {
        return specialDrug;
    }

    public void setSpecialDrug(Integer specialDrug) {
        this.specialDrug = specialDrug;
    }

    public Integer getSpiritDrugType() {
        return spiritDrugType;
    }

    public void setSpiritDrugType(Integer spiritDrugType) {
        this.spiritDrugType = spiritDrugType;
    }

    public Integer getInChargeDrug() {
        return inChargeDrug;
    }

    public void setInChargeDrug(Integer inChargeDrug) {
        this.inChargeDrug = inChargeDrug;
    }

    public Integer getFormulationType() {
        return formulationType;
    }

    public void setFormulationType(Integer formulationType) {
        this.formulationType = formulationType;
    }

    public String getRegisteredTrademark() {
        return registeredTrademark;
    }

    public void setRegisteredTrademark(String registeredTrademark) {
        this.registeredTrademark = registeredTrademark;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "ReceiveMoneyPageVO{" +
                "reportType=" + reportType +
                ", businessVariety=" + businessVariety +
                ", param='" + param + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", supplierCode='" + supplierCode + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", code='" + code + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", secondReceiverName='" + secondReceiverName + '\'' +
                ", status=" + status +
                ", categoryId=" + categoryId +
                ", domesticImport=" + domesticImport +
                ", storageTemp=" + storageTemp +
                ", storageConditionName='" + storageConditionName + '\'' +
                ", goodsAttribute=" + goodsAttribute +
                ", prescriptionDrug=" + prescriptionDrug +
                ", otcType=" + otcType +
                ", managementScopeId=" + managementScopeId +
                ", specialDrug=" + specialDrug +
                ", spiritDrugType=" + spiritDrugType +
                ", inChargeDrug=" + inChargeDrug +
                ", formulationType=" + formulationType +
                ", orderName='" + orderName + '\'' +
                ", orderType='" + orderType + '\'' +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", registeredTrademark=" + registeredTrademark +
                ", brand=" + brand +
                '}';
    }
}
