package com.rograndec.feijiayun.chain.business.retail.pos.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_pos_set
 * 
 * 
 * @author liyut
 * 
 * 2017-09-18
 */
public class PosSet implements Serializable {
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
     * 各门店使用统一的设置（0-关闭；1-开启）
     */
    @ApiModelProperty(value = "各门店使用统一的设置（0-关闭；1-开启）")
    private Integer unifiedFlag;

    /**
     * 处方药销售登记（0-否；1-是）
     */
    @ApiModelProperty(value = "处方药销售登记（0-否；1-是）")
    private Integer prescriptionSaleRegister;

    /**
     * 特殊药销售登记（0-否；1-是）
     */
    @ApiModelProperty(value = "特殊药销售登记（0-否；1-是）")
    private Integer specialSaleRegister;

    /**
     * 近效期商品提示（0-否；1-是）
     */
    @ApiModelProperty(value = "近效期商品提示（0-否；1-是）")
    private Integer nearPeriodSaleTips;

    /**
     * 配伍禁忌商品销售提示（0-否；1-是）
     */
    @ApiModelProperty(value = "配伍禁忌商品销售提示（0-否；1-是）")
    private Integer incompatibilitySaleRegister;

    /**
     * 常规模式销售中药饮片（0-禁止；1-允许）
     */
    @ApiModelProperty(value = "常规模式销售中药饮片（0-禁止；1-允许）")
    private Integer chMedicineLimit;

    /**
     * 售价小于成本价提示（0-否；1-是）
     */
    @ApiModelProperty(value = "售价小于成本价提示（0-否；1-是）")
    private Integer lowPriceTips;

    /**
     * 售价等于零提示（0-否；1-是）
     */
    @ApiModelProperty(value = "售价等于零提示（0-否；1-是）")
    private Integer zeroPriceTips;

    /**
     * 营业人员强制登记（0-否；1-是）
     */
    @ApiModelProperty(value = "营业人员强制登记（0-否；1-是）")
    private Integer clerkForceRegister;

    /**
     * 商品超量销售（0-否；1-是）
     */
    @ApiModelProperty(value = "商品超量销售（0-否；1-是）")
    private Integer excessiveSale;

    /**
     * 近效期N天内商品销售（0-禁止；1-允许）
     */
    @ApiModelProperty(value = "近效期N天内商品销售（0-禁止；1-允许）")
    private Integer nearPeriodSale;

    /**
     * 近效期天数
     */
    @ApiModelProperty(value = "近效期天数")
    private Integer nearPeriodSaleDays;

    /**
     * 中药饮片N种以上处方登记（0-关闭；1-开启）
     */
    @ApiModelProperty(value = "中药饮片N种以上处方登记（0-关闭；1-开启）")
    private Integer chPrescriptionRegister;

    /**
     * 中药饮片品种数
     */
    @ApiModelProperty(value = "中药饮片品种数")
    private Integer chMedicineQty;

    /**
     * 名称列显示活动标识（0-否；1-是）
     */
    @ApiModelProperty(value = "名称列显示活动标识（0-否；1-是）")
    private Integer showActivityLogo;

    /**
     * 自动抹零（0-否；1-是）
     */
    @ApiModelProperty(value = "自动抹零（0-否；1-是）")
    private Integer autoMaling;

    /**
     * 自动抹零类型（0-抹零到角；1-抹零到元）
     */
    @ApiModelProperty(value = "自动抹零类型（0-抹零到角；1-抹零到元）")
    private Integer autoMalingType;

    /**
     * 小票样式（0-常规；1-标准；2-精简；3-自定义1）
     */
    @ApiModelProperty(value = "小票样式（0-常规；1-标准；2-精简；3-自定义1）")
    private Integer smallTicketStyle;

    /**
     * 打印份数，默认为1
     */
    @ApiModelProperty(value = "打印份数，默认为1")
    private Integer printCopiess;

    /**
     * 状态（0-禁用；1-启用）
     */
    @ApiModelProperty(value = "状态（0-禁用；1-启用）")
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
     * saas_pos_set
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
     * 各门店使用统一的设置（0-关闭；1-开启）
     * @return unified_flag 各门店使用统一的设置（0-关闭；1-开启）
     */
    public Integer getUnifiedFlag() {
        return unifiedFlag;
    }

    /**
     * 各门店使用统一的设置（0-关闭；1-开启）
     * @param unifiedFlag 各门店使用统一的设置（0-关闭；1-开启）
     */
    public void setUnifiedFlag(Integer unifiedFlag) {
        this.unifiedFlag = unifiedFlag;
    }

    /**
     * 处方药销售登记（0-否；1-是）
     * @return prescription_sale_register 处方药销售登记（0-否；1-是）
     */
    public Integer getPrescriptionSaleRegister() {
        return prescriptionSaleRegister;
    }

    /**
     * 处方药销售登记（0-否；1-是）
     * @param prescriptionSaleRegister 处方药销售登记（0-否；1-是）
     */
    public void setPrescriptionSaleRegister(Integer prescriptionSaleRegister) {
        this.prescriptionSaleRegister = prescriptionSaleRegister;
    }

    /**
     * 特殊药销售登记（0-否；1-是）
     * @return special_sale_register 特殊药销售登记（0-否；1-是）
     */
    public Integer getSpecialSaleRegister() {
        return specialSaleRegister;
    }

    /**
     * 特殊药销售登记（0-否；1-是）
     * @param specialSaleRegister 特殊药销售登记（0-否；1-是）
     */
    public void setSpecialSaleRegister(Integer specialSaleRegister) {
        this.specialSaleRegister = specialSaleRegister;
    }

    /**
     * 近效期商品提示（0-否；1-是）
     * @return near_period_sale_tips 近效期商品提示（0-否；1-是）
     */
    public Integer getNearPeriodSaleTips() {
        return nearPeriodSaleTips;
    }

    /**
     * 近效期商品提示（0-否；1-是）
     * @param nearPeriodSaleTips 近效期商品提示（0-否；1-是）
     */
    public void setNearPeriodSaleTips(Integer nearPeriodSaleTips) {
        this.nearPeriodSaleTips = nearPeriodSaleTips;
    }

    /**
     * 配伍禁忌商品销售提示（0-否；1-是）
     * @return incompatibility_sale_register 配伍禁忌商品销售提示（0-否；1-是）
     */
    public Integer getIncompatibilitySaleRegister() {
        return incompatibilitySaleRegister;
    }

    /**
     * 配伍禁忌商品销售提示（0-否；1-是）
     * @param incompatibilitySaleRegister 配伍禁忌商品销售提示（0-否；1-是）
     */
    public void setIncompatibilitySaleRegister(Integer incompatibilitySaleRegister) {
        this.incompatibilitySaleRegister = incompatibilitySaleRegister;
    }

    /**
     * 常规模式销售中药饮片（0-禁止；1-允许）
     * @return ch_medicine_limit 常规模式销售中药饮片（0-禁止；1-允许）
     */
    public Integer getChMedicineLimit() {
        return chMedicineLimit;
    }

    /**
     * 常规模式销售中药饮片（0-禁止；1-允许）
     * @param chMedicineLimit 常规模式销售中药饮片（0-禁止；1-允许）
     */
    public void setChMedicineLimit(Integer chMedicineLimit) {
        this.chMedicineLimit = chMedicineLimit;
    }

    /**
     * 售价小于成本价提示（0-否；1-是）
     * @return low_price_tips 售价小于成本价提示（0-否；1-是）
     */
    public Integer getLowPriceTips() {
        return lowPriceTips;
    }

    /**
     * 售价小于成本价提示（0-否；1-是）
     * @param lowPriceTips 售价小于成本价提示（0-否；1-是）
     */
    public void setLowPriceTips(Integer lowPriceTips) {
        this.lowPriceTips = lowPriceTips;
    }

    /**
     * 售价等于零提示（0-否；1-是）
     * @return zero_price_tips 售价等于零提示（0-否；1-是）
     */
    public Integer getZeroPriceTips() {
        return zeroPriceTips;
    }

    /**
     * 售价等于零提示（0-否；1-是）
     * @param zeroPriceTips 售价等于零提示（0-否；1-是）
     */
    public void setZeroPriceTips(Integer zeroPriceTips) {
        this.zeroPriceTips = zeroPriceTips;
    }

    /**
     * 营业人员强制登记（0-否；1-是）
     * @return clerk_force_register 营业人员强制登记（0-否；1-是）
     */
    public Integer getClerkForceRegister() {
        return clerkForceRegister;
    }

    /**
     * 营业人员强制登记（0-否；1-是）
     * @param clerkForceRegister 营业人员强制登记（0-否；1-是）
     */
    public void setClerkForceRegister(Integer clerkForceRegister) {
        this.clerkForceRegister = clerkForceRegister;
    }

    /**
     * 商品超量销售（0-否；1-是）
     * @return excessive_sale 商品超量销售（0-否；1-是）
     */
    public Integer getExcessiveSale() {
        return excessiveSale;
    }

    /**
     * 商品超量销售（0-否；1-是）
     * @param excessiveSale 商品超量销售（0-否；1-是）
     */
    public void setExcessiveSale(Integer excessiveSale) {
        this.excessiveSale = excessiveSale;
    }

    /**
     * 近效期N天内商品销售（0-禁止；1-允许）
     * @return near_period_sale 近效期N天内商品销售（0-禁止；1-允许）
     */
    public Integer getNearPeriodSale() {
        return nearPeriodSale;
    }

    /**
     * 近效期N天内商品销售（0-禁止；1-允许）
     * @param nearPeriodSale 近效期N天内商品销售（0-禁止；1-允许）
     */
    public void setNearPeriodSale(Integer nearPeriodSale) {
        this.nearPeriodSale = nearPeriodSale;
    }

    /**
     * 近效期天数
     * @return near_period_sale_days 近效期天数
     */
    public Integer getNearPeriodSaleDays() {
        return nearPeriodSaleDays;
    }

    /**
     * 近效期天数
     * @param nearPeriodSaleDays 近效期天数
     */
    public void setNearPeriodSaleDays(Integer nearPeriodSaleDays) {
        this.nearPeriodSaleDays = nearPeriodSaleDays;
    }

    /**
     * 中药饮片N种以上处方登记（0-关闭；1-开启）
     * @return ch_prescription_register 中药饮片N种以上处方登记（0-关闭；1-开启）
     */
    public Integer getChPrescriptionRegister() {
        return chPrescriptionRegister;
    }

    /**
     * 中药饮片N种以上处方登记（0-关闭；1-开启）
     * @param chPrescriptionRegister 中药饮片N种以上处方登记（0-关闭；1-开启）
     */
    public void setChPrescriptionRegister(Integer chPrescriptionRegister) {
        this.chPrescriptionRegister = chPrescriptionRegister;
    }

    /**
     * 中药饮片品种数
     * @return ch_medicine_qty 中药饮片品种数
     */
    public Integer getChMedicineQty() {
        return chMedicineQty;
    }

    /**
     * 中药饮片品种数
     * @param chMedicineQty 中药饮片品种数
     */
    public void setChMedicineQty(Integer chMedicineQty) {
        this.chMedicineQty = chMedicineQty;
    }

    /**
     * 名称列显示活动标识（0-否；1-是）
     * @return show_activity_logo 名称列显示活动标识（0-否；1-是）
     */
    public Integer getShowActivityLogo() {
        return showActivityLogo;
    }

    /**
     * 名称列显示活动标识（0-否；1-是）
     * @param showActivityLogo 名称列显示活动标识（0-否；1-是）
     */
    public void setShowActivityLogo(Integer showActivityLogo) {
        this.showActivityLogo = showActivityLogo;
    }

    /**
     * 自动抹零（0-否；1-是）
     * @return auto_maling 自动抹零（0-否；1-是）
     */
    public Integer getAutoMaling() {
        return autoMaling;
    }

    /**
     * 自动抹零（0-否；1-是）
     * @param autoMaling 自动抹零（0-否；1-是）
     */
    public void setAutoMaling(Integer autoMaling) {
        this.autoMaling = autoMaling;
    }

    /**
     * 自动抹零类型（0-抹零到角；1-抹零到元）
     * @return auto_maling_type 自动抹零类型（0-抹零到角；1-抹零到元）
     */
    public Integer getAutoMalingType() {
        return autoMalingType;
    }

    /**
     * 自动抹零类型（0-抹零到角；1-抹零到元）
     * @param autoMalingType 自动抹零类型（0-抹零到角；1-抹零到元）
     */
    public void setAutoMalingType(Integer autoMalingType) {
        this.autoMalingType = autoMalingType;
    }

    /**
     * 小票样式（0-常规；1-标准；2-精简；3-自定义1）
     * @return small_ticket_style 小票样式（0-常规；1-标准；2-精简；3-自定义1）
     */
    public Integer getSmallTicketStyle() {
        return smallTicketStyle;
    }

    /**
     * 小票样式（0-常规；1-标准；2-精简；3-自定义1）
     * @param smallTicketStyle 小票样式（0-常规；1-标准；2-精简；3-自定义1）
     */
    public void setSmallTicketStyle(Integer smallTicketStyle) {
        this.smallTicketStyle = smallTicketStyle;
    }

    /**
     * 打印份数，默认为1
     * @return print_copiess 打印份数，默认为1
     */
    public Integer getPrintCopiess() {
        return printCopiess;
    }

    /**
     * 打印份数，默认为1
     * @param printCopiess 打印份数，默认为1
     */
    public void setPrintCopiess(Integer printCopiess) {
        this.printCopiess = printCopiess;
    }

    /**
     * 状态（0-禁用；1-启用）
     * @return status 状态（0-禁用；1-启用）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态（0-禁用；1-启用）
     * @param status 状态（0-禁用；1-启用）
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
        sb.append(", unifiedFlag=").append(unifiedFlag);
        sb.append(", prescriptionSaleRegister=").append(prescriptionSaleRegister);
        sb.append(", specialSaleRegister=").append(specialSaleRegister);
        sb.append(", nearPeriodSaleTips=").append(nearPeriodSaleTips);
        sb.append(", incompatibilitySaleRegister=").append(incompatibilitySaleRegister);
        sb.append(", chMedicineLimit=").append(chMedicineLimit);
        sb.append(", lowPriceTips=").append(lowPriceTips);
        sb.append(", zeroPriceTips=").append(zeroPriceTips);
        sb.append(", clerkForceRegister=").append(clerkForceRegister);
        sb.append(", excessiveSale=").append(excessiveSale);
        sb.append(", nearPeriodSale=").append(nearPeriodSale);
        sb.append(", nearPeriodSaleDays=").append(nearPeriodSaleDays);
        sb.append(", chPrescriptionRegister=").append(chPrescriptionRegister);
        sb.append(", chMedicineQty=").append(chMedicineQty);
        sb.append(", showActivityLogo=").append(showActivityLogo);
        sb.append(", autoMaling=").append(autoMaling);
        sb.append(", autoMalingType=").append(autoMalingType);
        sb.append(", smallTicketStyle=").append(smallTicketStyle);
        sb.append(", printCopiess=").append(printCopiess);
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