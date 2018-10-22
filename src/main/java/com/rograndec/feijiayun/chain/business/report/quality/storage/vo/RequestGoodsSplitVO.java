package com.rograndec.feijiayun.chain.business.report.quality.storage.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class RequestGoodsSplitVO implements Serializable{
    /**
     * 品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）
     */
    @ApiModelProperty(value="品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）")
    private Integer businessVariety;

    @ApiModelProperty(value = "快速搜索的条件（商品编码/条形码/商品名称/通用名称/批准文号）")
    private String param;

    @ApiModelProperty(value = "当前页---作为导出参数时不需要给我传",required = true)
    private Integer pageNo;

    @ApiModelProperty(value = "每页显示数据---作为导出参数时不需要给我传",required = true)
    private Integer pageSize;

    @ApiModelProperty(value = "组织机构类型")
    private Integer chainType;

    @ApiModelProperty(value = "组织机构编码")
    private String enterpriseCode;

    @ApiModelProperty(value = "组织机构名称")
    private String enterpriseName;

    @ApiModelProperty(value = "分拆起始日期")
    private Date startTime;

    @ApiModelProperty(value = "分拆终止日期")
    private Date endTime;

    @ApiModelProperty(value = "分拆单号")
    private String code;

    @ApiModelProperty(value = "分拆人员")
    private String splitManName;

    @ApiModelProperty(value = "复核人员")
    private String auditManName;

    @ApiModelProperty(value = "流通监管码")
    private String flowCode;

    @ApiModelProperty(value = "状态")
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
     * 注册商标
     */
    @ApiModelProperty(value="注册商标")
    private String registeredTrademark;

    /**
     * 品牌
     */
    @ApiModelProperty(value="品牌")
    private String brand;

    /**
     * 商品属性（0-成药；1-中药材；2-中药饮片）
     */
    @ApiModelProperty(value="商品属性（0-成药；1-中药材；2-中药饮片）-----前端做的时候按照商品信息一样的三级联动接口变化")
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
     * 特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）
     */
    @ApiModelProperty(value="特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）")
    private Integer specialDrug;

    /**
     * 专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）
     *
     */
    @ApiModelProperty(value="专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）")
    private Integer inChargeDrug;

    @ApiModelProperty(value = "按某一列排序(splitDate或者code)")
    private String order;

    @ApiModelProperty(value = "排序方式（升序：asc,降序：desc）")
    private String sort;

    /**
     * 精神药品分类（0-一类；1-二类）
     */
    @ApiModelProperty(value="精神药品分类（0-一类；1-二类）")
    private Integer spiritDrugType;

    /**
     * 含特殊药品复方制剂类型（0-含可卡因复方口服液；1-含麻黄碱类复方制剂；2-复方地芬诺酯片；3-复方甘草片）
     */
    @ApiModelProperty(value="含特殊药品复方制剂类型（0-含可卡因复方口服液；1-含麻黄碱类复方制剂；2-复方地芬诺酯片；3-复方甘草片）")
    private Integer formulationType;

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

    public Integer getChainType() {
        return chainType;
    }

    public void setChainType(Integer chainType) {
        this.chainType = chainType;
    }

    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSplitManName() {
        return splitManName;
    }

    public void setSplitManName(String splitManName) {
        this.splitManName = splitManName;
    }

    public String getAuditManName() {
        return auditManName;
    }

    public void setAuditManName(String auditManName) {
        this.auditManName = auditManName;
    }

    public String getFlowCode() {
        return flowCode;
    }

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
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

    public Integer getInChargeDrug() {
        return inChargeDrug;
    }

    public void setInChargeDrug(Integer inChargeDrug) {
        this.inChargeDrug = inChargeDrug;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Integer getSpiritDrugType() {
        return spiritDrugType;
    }

    public void setSpiritDrugType(Integer spiritDrugType) {
        this.spiritDrugType = spiritDrugType;
    }

    public Integer getFormulationType() {
        return formulationType;
    }

    public void setFormulationType(Integer formulationType) {
        this.formulationType = formulationType;
    }
}
