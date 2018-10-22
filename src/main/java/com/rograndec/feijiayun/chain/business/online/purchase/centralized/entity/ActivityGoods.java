package com.rograndec.feijiayun.chain.business.online.purchase.centralized.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_activity_goods
 * 
 * 
 * @author Asze
 * 
 * 2017-11-21
 */
public class ActivityGoods implements Serializable {
    /**
     * 
     */
    @ApiModelProperty(value = "")
    private Integer id;

    /**
     * 活动id
     */
    @ApiModelProperty(value = "活动id")
    private Integer activityId;

    /**
     * 商品id
     */
    @ApiModelProperty(value = "商品id")
    private String goodsId;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    /**
     * 拼音码
     */
    @ApiModelProperty(value = "拼音码")
    private String pinyinCode;

    /**
     * 规格
     */
    @ApiModelProperty(value = "规格")
    private String specification;

    /**
     * 单位
     */
    @ApiModelProperty(value = "单位")
    private String unitName;

    /**
     * 生产厂家
     */
    @ApiModelProperty(value = "生产厂家")
    private String manufacturer;

    /**
     * 零售价
     */
    @ApiModelProperty(value = "零售价")
    private BigDecimal retailPrice;

    /**
     * 集采价
     */
    @ApiModelProperty(value = "集采价")
    private BigDecimal centralPurchasingPrice;

    /**
     * 库存数量
     */
    @ApiModelProperty(value = "库存数量")
    private Integer inventoryQuantity;

    /**
     * 商品图片路径
     */
    @ApiModelProperty(value = "商品图片路径")
    private String pictureAddress;

    /**
     * 批准文号
     */
    @ApiModelProperty(value = "批准文号")
    private String approvalNumber;

    /**
     * 专属标签
     */
    @ApiModelProperty(value = "专属标签")
    private String label;

    /**
     * 标签描述
     */
    @ApiModelProperty(value = "标签描述")
    private String labelDescription;

    /**
     * 原采购价格
     */
    @ApiModelProperty(value = "原采购价格")
    private BigDecimal oldPurchasingPrice;

    /**
     * 一级分类编号
     */
    @ApiModelProperty(value = "一级分类编号")
    private Integer gcId1;

    /**
     * 二级分类编号
     */
    @ApiModelProperty(value = "二级分类编号")
    private Integer gcId2;

    /**
     * 一级分类名称
     */
    @ApiModelProperty(value = "一级分类名称")
    private String gcName1;

    /**
     * 二级分类名称
     */
    @ApiModelProperty(value = "二级分类名称")
    private String gcName2;

    /**
     * 剂型ID
     */
    @ApiModelProperty(value = "剂型ID")
    private Integer dosageId;

    /**
     * 剂型名称
     */
    @ApiModelProperty(value = "剂型名称")
    private String dosageName;

    /**
     * otc类型（1：处方药 2：甲类非处方药 3：乙类非处方药 4：其他）
     */
    @ApiModelProperty(value = "otc类型（1：处方药 2：甲类非处方药 3：乙类非处方药 4：其他）")
    private Integer otcType;

    /**
     * 品牌
     */
    @ApiModelProperty(value = "品牌")
    private String brands;

    /**
     * 子品牌
     */
    @ApiModelProperty(value = "子品牌")
    private String subBrand;

    /**
     * 是否医保（0：非 1：是 2:未采集到相关信息）
     */
    @ApiModelProperty(value = "是否医保（0：非 1：是 2:未采集到相关信息）")
    private Integer medicalInsurance;

    /**
     * 是否国产 0 进口 1 国产
     */
    @ApiModelProperty(value = "是否国产 0 进口 1 国产")
    private Integer domesticImport;

    /**
     * 限购数量
     */
    @ApiModelProperty(value = "限购数量")
    private Integer restrictedQuantity;

    /**
     * 排序等级
     */
    @ApiModelProperty(value = "排序等级")
    private Integer level;

    /**
     * 秒杀价
     */
    @ApiModelProperty(value = "秒杀价")
    private BigDecimal policyPrice;

    /**
     * 成本价
     */
    @ApiModelProperty(value = "成本价")
    private BigDecimal costPrice;

    /**
     * 是否拆包销售(0否,1是) 
     */
    @ApiModelProperty(value = "是否拆包销售(0否,1是) ")
    private Integer canSplit;

    /**
     * 中包装数量
     */
    @ApiModelProperty(value = "中包装数量")
    private Integer middlePackage;

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
     * saas_activity_goods
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     * @return id 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 活动id
     * @return activity_id 活动id
     */
    public Integer getActivityId() {
        return activityId;
    }

    /**
     * 活动id
     * @param activityId 活动id
     */
    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    /**
     * 商品id
     * @return goods_id 商品id
     */
    public String getGoodsId() {
        return goodsId;
    }

    /**
     * 商品id
     * @param goodsId 商品id
     */
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId == null ? null : goodsId.trim();
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
     * 拼音码
     * @return pinyin_code 拼音码
     */
    public String getPinyinCode() {
        return pinyinCode;
    }

    /**
     * 拼音码
     * @param pinyinCode 拼音码
     */
    public void setPinyinCode(String pinyinCode) {
        this.pinyinCode = pinyinCode == null ? null : pinyinCode.trim();
    }

    /**
     * 规格
     * @return specification 规格
     */
    public String getSpecification() {
        return specification;
    }

    /**
     * 规格
     * @param specification 规格
     */
    public void setSpecification(String specification) {
        this.specification = specification == null ? null : specification.trim();
    }

    /**
     * 单位
     * @return unit_name 单位
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     * 单位
     * @param unitName 单位
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName == null ? null : unitName.trim();
    }

    /**
     * 生产厂家
     * @return manufacturer 生产厂家
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * 生产厂家
     * @param manufacturer 生产厂家
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer == null ? null : manufacturer.trim();
    }

    /**
     * 零售价
     * @return retail_price 零售价
     */
    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    /**
     * 零售价
     * @param retailPrice 零售价
     */
    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    /**
     * 集采价
     * @return central_purchasing_price 集采价
     */
    public BigDecimal getCentralPurchasingPrice() {
        return centralPurchasingPrice;
    }

    /**
     * 集采价
     * @param centralPurchasingPrice 集采价
     */
    public void setCentralPurchasingPrice(BigDecimal centralPurchasingPrice) {
        this.centralPurchasingPrice = centralPurchasingPrice;
    }

    /**
     * 库存数量
     * @return inventory_quantity 库存数量
     */
    public Integer getInventoryQuantity() {
        return inventoryQuantity;
    }

    /**
     * 库存数量
     * @param inventoryQuantity 库存数量
     */
    public void setInventoryQuantity(Integer inventoryQuantity) {
        this.inventoryQuantity = inventoryQuantity;
    }

    /**
     * 商品图片路径
     * @return picture_address 商品图片路径
     */
    public String getPictureAddress() {
        return pictureAddress;
    }

    /**
     * 商品图片路径
     * @param pictureAddress 商品图片路径
     */
    public void setPictureAddress(String pictureAddress) {
        this.pictureAddress = pictureAddress == null ? null : pictureAddress.trim();
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
     * 专属标签
     * @return label 专属标签
     */
    public String getLabel() {
        return label;
    }

    /**
     * 专属标签
     * @param label 专属标签
     */
    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    /**
     * 标签描述
     * @return label_description 标签描述
     */
    public String getLabelDescription() {
        return labelDescription;
    }

    /**
     * 标签描述
     * @param labelDescription 标签描述
     */
    public void setLabelDescription(String labelDescription) {
        this.labelDescription = labelDescription == null ? null : labelDescription.trim();
    }

    /**
     * 原采购价格
     * @return old_purchasing_price 原采购价格
     */
    public BigDecimal getOldPurchasingPrice() {
        return oldPurchasingPrice;
    }

    /**
     * 原采购价格
     * @param oldPurchasingPrice 原采购价格
     */
    public void setOldPurchasingPrice(BigDecimal oldPurchasingPrice) {
        this.oldPurchasingPrice = oldPurchasingPrice;
    }

    /**
     * 一级分类编号
     * @return gc_id1 一级分类编号
     */
    public Integer getGcId1() {
        return gcId1;
    }

    /**
     * 一级分类编号
     * @param gcId1 一级分类编号
     */
    public void setGcId1(Integer gcId1) {
        this.gcId1 = gcId1;
    }

    /**
     * 二级分类编号
     * @return gc_id2 二级分类编号
     */
    public Integer getGcId2() {
        return gcId2;
    }

    /**
     * 二级分类编号
     * @param gcId2 二级分类编号
     */
    public void setGcId2(Integer gcId2) {
        this.gcId2 = gcId2;
    }

    /**
     * 一级分类名称
     * @return gc_name1 一级分类名称
     */
    public String getGcName1() {
        return gcName1;
    }

    /**
     * 一级分类名称
     * @param gcName1 一级分类名称
     */
    public void setGcName1(String gcName1) {
        this.gcName1 = gcName1 == null ? null : gcName1.trim();
    }

    /**
     * 二级分类名称
     * @return gc_name2 二级分类名称
     */
    public String getGcName2() {
        return gcName2;
    }

    /**
     * 二级分类名称
     * @param gcName2 二级分类名称
     */
    public void setGcName2(String gcName2) {
        this.gcName2 = gcName2 == null ? null : gcName2.trim();
    }

    /**
     * 剂型ID
     * @return dosage_id 剂型ID
     */
    public Integer getDosageId() {
        return dosageId;
    }

    /**
     * 剂型ID
     * @param dosageId 剂型ID
     */
    public void setDosageId(Integer dosageId) {
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
     * otc类型（1：处方药 2：甲类非处方药 3：乙类非处方药 4：其他）
     * @return otc_type otc类型（1：处方药 2：甲类非处方药 3：乙类非处方药 4：其他）
     */
    public Integer getOtcType() {
        return otcType;
    }

    /**
     * otc类型（1：处方药 2：甲类非处方药 3：乙类非处方药 4：其他）
     * @param otcType otc类型（1：处方药 2：甲类非处方药 3：乙类非处方药 4：其他）
     */
    public void setOtcType(Integer otcType) {
        this.otcType = otcType;
    }

    /**
     * 品牌
     * @return brands 品牌
     */
    public String getBrands() {
        return brands;
    }

    /**
     * 品牌
     * @param brands 品牌
     */
    public void setBrands(String brands) {
        this.brands = brands == null ? null : brands.trim();
    }

    /**
     * 子品牌
     * @return sub_brand 子品牌
     */
    public String getSubBrand() {
        return subBrand;
    }

    /**
     * 子品牌
     * @param subBrand 子品牌
     */
    public void setSubBrand(String subBrand) {
        this.subBrand = subBrand == null ? null : subBrand.trim();
    }

    /**
     * 是否医保（0：非 1：是 2:未采集到相关信息）
     * @return medical_insurance 是否医保（0：非 1：是 2:未采集到相关信息）
     */
    public Integer getMedicalInsurance() {
        return medicalInsurance;
    }

    /**
     * 是否医保（0：非 1：是 2:未采集到相关信息）
     * @param medicalInsurance 是否医保（0：非 1：是 2:未采集到相关信息）
     */
    public void setMedicalInsurance(Integer medicalInsurance) {
        this.medicalInsurance = medicalInsurance;
    }

    /**
     * 是否国产 0 进口 1 国产
     * @return domestic_import 是否国产 0 进口 1 国产
     */
    public Integer getDomesticImport() {
        return domesticImport;
    }

    /**
     * 是否国产 0 进口 1 国产
     * @param domesticImport 是否国产 0 进口 1 国产
     */
    public void setDomesticImport(Integer domesticImport) {
        this.domesticImport = domesticImport;
    }

    /**
     * 限购数量
     * @return restricted_quantity 限购数量
     */
    public Integer getRestrictedQuantity() {
        return restrictedQuantity;
    }

    /**
     * 限购数量
     * @param restrictedQuantity 限购数量
     */
    public void setRestrictedQuantity(Integer restrictedQuantity) {
        this.restrictedQuantity = restrictedQuantity;
    }

    /**
     * 排序等级
     * @return level 排序等级
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 排序等级
     * @param level 排序等级
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 秒杀价
     * @return policy_price 秒杀价
     */
    public BigDecimal getPolicyPrice() {
        return policyPrice;
    }

    /**
     * 秒杀价
     * @param policyPrice 秒杀价
     */
    public void setPolicyPrice(BigDecimal policyPrice) {
        this.policyPrice = policyPrice;
    }

    /**
     * 成本价
     * @return cost_price 成本价
     */
    public BigDecimal getCostPrice() {
        return costPrice;
    }

    /**
     * 成本价
     * @param costPrice 成本价
     */
    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    /**
     * 是否拆包销售(0否,1是) 
     * @return can_split 是否拆包销售(0否,1是) 
     */
    public Integer getCanSplit() {
        return canSplit;
    }

    /**
     * 是否拆包销售(0否,1是) 
     * @param canSplit 是否拆包销售(0否,1是) 
     */
    public void setCanSplit(Integer canSplit) {
        this.canSplit = canSplit;
    }

    /**
     * 中包装数量
     * @return middle_package 中包装数量
     */
    public Integer getMiddlePackage() {
        return middlePackage;
    }

    /**
     * 中包装数量
     * @param middlePackage 中包装数量
     */
    public void setMiddlePackage(Integer middlePackage) {
        this.middlePackage = middlePackage;
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
        sb.append(", activityId=").append(activityId);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", pinyinCode=").append(pinyinCode);
        sb.append(", specification=").append(specification);
        sb.append(", unitName=").append(unitName);
        sb.append(", manufacturer=").append(manufacturer);
        sb.append(", retailPrice=").append(retailPrice);
        sb.append(", centralPurchasingPrice=").append(centralPurchasingPrice);
        sb.append(", inventoryQuantity=").append(inventoryQuantity);
        sb.append(", pictureAddress=").append(pictureAddress);
        sb.append(", approvalNumber=").append(approvalNumber);
        sb.append(", label=").append(label);
        sb.append(", labelDescription=").append(labelDescription);
        sb.append(", oldPurchasingPrice=").append(oldPurchasingPrice);
        sb.append(", gcId1=").append(gcId1);
        sb.append(", gcId2=").append(gcId2);
        sb.append(", gcName1=").append(gcName1);
        sb.append(", gcName2=").append(gcName2);
        sb.append(", dosageId=").append(dosageId);
        sb.append(", dosageName=").append(dosageName);
        sb.append(", otcType=").append(otcType);
        sb.append(", brands=").append(brands);
        sb.append(", subBrand=").append(subBrand);
        sb.append(", medicalInsurance=").append(medicalInsurance);
        sb.append(", domesticImport=").append(domesticImport);
        sb.append(", restrictedQuantity=").append(restrictedQuantity);
        sb.append(", level=").append(level);
        sb.append(", policyPrice=").append(policyPrice);
        sb.append(", costPrice=").append(costPrice);
        sb.append(", canSplit=").append(canSplit);
        sb.append(", middlePackage=").append(middlePackage);
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