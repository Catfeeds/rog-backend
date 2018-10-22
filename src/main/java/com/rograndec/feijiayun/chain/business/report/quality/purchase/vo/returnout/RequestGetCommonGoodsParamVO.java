package com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.returnout;

import com.rograndec.feijiayun.chain.common.vo.RequestReportBaseParamVO;
import io.swagger.annotations.ApiModelProperty;


/**
 * 功能描述：
 * Created by ST on 2017/10/23 13:57
 */

public class RequestGetCommonGoodsParamVO extends RequestReportBaseParamVO {

    @ApiModelProperty("快速搜索的条件（编码/条形码/商品名称/通用名称/批准文号）")
    private String param;


    @ApiModelProperty(value="品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）")
    private Long businessVariety;

    /**
     * 品种类别为0-药品，商品属性为0-成药时，prescription_drug含义为是否为处方药［0：非处方药，1：处方药］
     */
    @ApiModelProperty("品种类别为0-药品，商品属性为0-成药时，prescription_drug含义为是否为处方药［0：非处方药，1：处方药］")
    private Integer goodsAttribute;

    /**
     * 商品属性-是否为处方药，0：非处方药，1：处方药
     */
    @ApiModelProperty("商品属性-是否为处方药，0：非处方药，1：处方药")
    private Integer prescriptionDrug;

    /**
     * 品种类别为3-化妆品，商品属性为1-特殊用途化妆品时，化妆品类别［0-育发；1-染发；2-烫发；3-脱毛；4-美乳；5-健美；6-除臭；7-祛斑；8-防嗮］
     */
    @ApiModelProperty("品种类别为3-化妆品，商品属性为1-特殊用途化妆品时，化妆品类别［0-育发；1-染发；2-烫发；3-脱毛；4-美乳；5-健美；6-除臭；7-祛斑；8-防嗮］")
    private Integer cosmetics;

    @ApiModelProperty("商品属性-品种类别为0-药品，商品属性为0-成药，并且为非处方药时，otc_type\n" +
            "表示非处方药类别［0-甲类；1-乙类］")
    private Integer otcType;


    /**
     * 商品分类ID
     */
    @ApiModelProperty("商品分类ID")
    private Long categoryId;


    /**
     * 国产/进口（0-国产；1-进口）
     */
    @ApiModelProperty("国产/进口（0-国产；1-进口）")
    private Integer domesticImport;


    /**
     * 贮藏区域（0-常温；1-阴凉；2-冷藏；3-冷冻）
     */
    @ApiModelProperty("贮藏温度（0-常温；1-阴凉；2-冷藏；3-冷冻）")
    private Integer storageTemp;

    @ApiModelProperty("贮藏条件名称")
    private String storageConditionName;


    /**
     * 经营范围ID
     */
    @ApiModelProperty("经营范围ID")
    private Long managementScopeId;

    /**
     * 特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）
     */
    @ApiModelProperty("特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）")
    private Integer specialDrug;

    /**
     * 专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）
     */
    @ApiModelProperty("专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）")
    private Integer inChargeDrug;

    @ApiModelProperty("注册商标")
    private String registeredTrademark;

    @ApiModelProperty("品牌")
    private String brand;

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public Long getBusinessVariety() {
        return businessVariety;
    }

    public void setBusinessVariety(Long businessVariety) {
        this.businessVariety = businessVariety;
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

    public String getStorageConditionName() {
        return storageConditionName;
    }

    public void setStorageConditionName(String storageConditionName) {
        this.storageConditionName = storageConditionName;
    }

    public Integer getCosmetics() {
        return cosmetics;
    }

    public void setCosmetics(Integer cosmetics) {
        this.cosmetics = cosmetics;
    }
}