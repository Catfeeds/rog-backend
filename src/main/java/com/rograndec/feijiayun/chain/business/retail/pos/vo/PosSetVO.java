package com.rograndec.feijiayun.chain.business.retail.pos.vo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

 /**
 * 
 * @ClassName: PosSetVO
 * @Description:  零售管理-POS管理-系统设置-Rest接口
 * @author yuting.li
 * @version 1.0 
 * @date 2017-09-18 17:31:49
 */
@ApiModel(value = "PosSetVO", description = "零售管理-POS管理-系统设置")
public class PosSetVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * 主键ID
     */
	@ApiModelProperty(required = true, value = "主键ID,修改时需要传入,新增时不需要")
	private Long id;
	
	/**
     * 企业ID
     */
	@ApiModelProperty(required = false, value = "企业ID", hidden = true)
	private Long enterpriseId;
	
	/**
     * 各门店使用统一的设置（0-关闭；1-开启）
     */
	@ApiModelProperty(required = false, value = "各门店使用统一的设置（0-关闭；1-开启）")
	private Integer unifiedFlag;
	
	/**
     * 处方药销售登记（0-否；1-是）
     */
	@ApiModelProperty(required = false, value = "处方药销售登记（0-否；1-是）")
	private Integer prescriptionSaleRegister;
	
	/**
     * 特殊药销售登记（0-否；1-是）
     */
	@ApiModelProperty(required = false, value = "特殊药销售登记（0-否；1-是）")
	private Integer specialSaleRegister;
	
	/**
     * 近效期商品提示（0-否；1-是）
     */
	@ApiModelProperty(required = false, value = "近效期商品提示（0-否；1-是）")
	private Integer nearPeriodSaleTips;
	
	/**
     * 配伍禁忌商品销售提示（0-否；1-是）
     */
	@ApiModelProperty(required = false, value = "配伍禁忌商品销售提示（0-否；1-是）")
	private Integer incompatibilitySaleRegister;
	
	/**
     * 常规模式销售中药饮片（0-禁止；1-允许）
     */
	@ApiModelProperty(required = false, value = "常规模式销售中药饮片（0-禁止；1-允许）")
	private Integer chMedicineLimit;
	
	/**
     * 售价小于成本价提示（0-否；1-是）
     */
	@ApiModelProperty(required = false, value = "售价小于成本价提示（0-否；1-是）")
	private Integer lowPriceTips;
	
	/**
     * 售价等于零提示（0-否；1-是）
     */
	@ApiModelProperty(required = false, value = "售价等于零提示（0-否；1-是）")
	private Integer zeroPriceTips;
	
	/**
     * 营业人员强制登记（0-否；1-是）
     */
	@ApiModelProperty(required = false, value = "营业人员强制登记（0-否；1-是）")
	private Integer clerkForceRegister;
	
	/**
     * 商品超量销售（0-否；1-是）
     */
	@ApiModelProperty(required = false, value = "商品超量销售（0-否；1-是）")
	private Integer excessiveSale;
	
	/**
     * 近效期N天内商品销售（0-禁止；1-允许）
     */
	@ApiModelProperty(required = false, value = "近效期N天内商品销售（0-禁止；1-允许）")
	private Integer nearPeriodSale;
	
	/**
     * 近效期天数
     */
	@NotNull(message="近效期天数不能为空，必须为数字！")
	@ApiModelProperty(required = false, value = "近效期天数")
	private Integer nearPeriodSaleDays;
	
	/**
     * 中药饮片N种以上处方登记（0-关闭；1-开启）
     */
	@ApiModelProperty(required = false, value = "中药饮片N种以上处方登记（0-关闭；1-开启）")
	private Integer chPrescriptionRegister;
	
	/**
     * 中药饮片品种数
     */
	@NotNull(message="中药饮片品种数不能为空，必须为数字！")
	@ApiModelProperty(required = false, value = "中药饮片品种数")
	private Integer chMedicineQty;
	
	/**
     * 名称列显示活动标识（0-否；1-是）
     */
	@ApiModelProperty(required = false, value = "名称列显示活动标识（0-否；1-是）")
	private Integer showActivityLogo;
	
	/**
     * 自动抹零（0-否；1-是）
     */
	@ApiModelProperty(required = false, value = "自动抹零（0-否；1-是）")
	private Integer autoMaling;
	
	/**
     * 自动抹零类型（0-抹零到角；1-抹零到元）
     */
	@ApiModelProperty(required = false, value = "自动抹零类型（0-抹零到角；1-抹零到元）")
	private Integer autoMalingType;
	
	/**
     * 小票样式（0-常规；1-标准；2-精简；3-自定义1）
     */
	@ApiModelProperty(required = false, value = "小票样式（0-常规；1-标准；2-精简；3-自定义1）")
	private Integer smallTicketStyle;
	
	/**
     * 打印份数，默认为1
     */
	@NotNull(message="打印份数不能为空，必须为数字！")
	@ApiModelProperty(required = false, value = "打印份数，默认为1")
	private Integer printCopiess;
	
	/**
     * 状态（0-禁用；1-启用）
     */
	@ApiModelProperty(required = false, value = "状态（0-禁用；1-启用）",hidden = true)
	private Integer status;
	
	/**
     * 备注
     */
	@ApiModelProperty(required = false, value = "备注", hidden = true)
	private String remark;
	

	/**
	 * 主键ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * 主键ID
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * 企业ID
	 */
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	
	/**
	 * 企业ID
	 */
	public Long getEnterpriseId() {
		return enterpriseId;
	}
	
	/**
	 * 各门店使用统一的设置（0-关闭；1-开启）
	 */
	public void setUnifiedFlag(Integer unifiedFlag) {
		this.unifiedFlag = unifiedFlag;
	}
	
	/**
	 * 各门店使用统一的设置（0-关闭；1-开启）
	 */
	public Integer getUnifiedFlag() {
		return unifiedFlag;
	}
	
	/**
	 * 处方药销售登记（0-否；1-是）
	 */
	public void setPrescriptionSaleRegister(Integer prescriptionSaleRegister) {
		this.prescriptionSaleRegister = prescriptionSaleRegister;
	}
	
	/**
	 * 处方药销售登记（0-否；1-是）
	 */
	public Integer getPrescriptionSaleRegister() {
		return prescriptionSaleRegister;
	}
	
	/**
	 * 特殊药销售登记（0-否；1-是）
	 */
	public void setSpecialSaleRegister(Integer specialSaleRegister) {
		this.specialSaleRegister = specialSaleRegister;
	}
	
	/**
	 * 特殊药销售登记（0-否；1-是）
	 */
	public Integer getSpecialSaleRegister() {
		return specialSaleRegister;
	}
	
	/**
	 * 近效期商品提示（0-否；1-是）
	 */
	public void setNearPeriodSaleTips(Integer nearPeriodSaleTips) {
		this.nearPeriodSaleTips = nearPeriodSaleTips;
	}
	
	/**
	 * 近效期商品提示（0-否；1-是）
	 */
	public Integer getNearPeriodSaleTips() {
		return nearPeriodSaleTips;
	}
	
	/**
	 * 配伍禁忌商品销售提示（0-否；1-是）
	 */
	public void setIncompatibilitySaleRegister(Integer incompatibilitySaleRegister) {
		this.incompatibilitySaleRegister = incompatibilitySaleRegister;
	}
	
	/**
	 * 配伍禁忌商品销售提示（0-否；1-是）
	 */
	public Integer getIncompatibilitySaleRegister() {
		return incompatibilitySaleRegister;
	}
	
	/**
	 * 常规模式销售中药饮片（0-禁止；1-允许）
	 */
	public void setChMedicineLimit(Integer chMedicineLimit) {
		this.chMedicineLimit = chMedicineLimit;
	}
	
	/**
	 * 常规模式销售中药饮片（0-禁止；1-允许）
	 */
	public Integer getChMedicineLimit() {
		return chMedicineLimit;
	}
	
	/**
	 * 售价小于成本价提示（0-否；1-是）
	 */
	public void setLowPriceTips(Integer lowPriceTips) {
		this.lowPriceTips = lowPriceTips;
	}
	
	/**
	 * 售价小于成本价提示（0-否；1-是）
	 */
	public Integer getLowPriceTips() {
		return lowPriceTips;
	}
	
	/**
	 * 售价等于零提示（0-否；1-是）
	 */
	public void setZeroPriceTips(Integer zeroPriceTips) {
		this.zeroPriceTips = zeroPriceTips;
	}
	
	/**
	 * 售价等于零提示（0-否；1-是）
	 */
	public Integer getZeroPriceTips() {
		return zeroPriceTips;
	}
	
	/**
	 * 营业人员强制登记（0-否；1-是）
	 */
	public void setClerkForceRegister(Integer clerkForceRegister) {
		this.clerkForceRegister = clerkForceRegister;
	}
	
	/**
	 * 营业人员强制登记（0-否；1-是）
	 */
	public Integer getClerkForceRegister() {
		return clerkForceRegister;
	}
	
	/**
	 * 商品超量销售（0-否；1-是）
	 */
	public void setExcessiveSale(Integer excessiveSale) {
		this.excessiveSale = excessiveSale;
	}
	
	/**
	 * 商品超量销售（0-否；1-是）
	 */
	public Integer getExcessiveSale() {
		return excessiveSale;
	}
	
	/**
	 * 近效期N天内商品销售（0-禁止；1-允许）
	 */
	public void setNearPeriodSale(Integer nearPeriodSale) {
		this.nearPeriodSale = nearPeriodSale;
	}
	
	/**
	 * 近效期N天内商品销售（0-禁止；1-允许）
	 */
	public Integer getNearPeriodSale() {
		return nearPeriodSale;
	}
	
	/**
	 * 近效期天数
	 */
	public void setNearPeriodSaleDays(Integer nearPeriodSaleDays) {
		this.nearPeriodSaleDays = nearPeriodSaleDays;
	}
	
	/**
	 * 近效期天数
	 */
	public Integer getNearPeriodSaleDays() {
		return nearPeriodSaleDays;
	}
	
	/**
	 * 中药饮片N种以上处方登记（0-关闭；1-开启）
	 */
	public void setChPrescriptionRegister(Integer chPrescriptionRegister) {
		this.chPrescriptionRegister = chPrescriptionRegister;
	}
	
	/**
	 * 中药饮片N种以上处方登记（0-关闭；1-开启）
	 */
	public Integer getChPrescriptionRegister() {
		return chPrescriptionRegister;
	}
	
	/**
	 * 中药饮片品种数
	 */
	public void setChMedicineQty(Integer chMedicineQty) {
		this.chMedicineQty = chMedicineQty;
	}
	
	/**
	 * 中药饮片品种数
	 */
	public Integer getChMedicineQty() {
		return chMedicineQty;
	}
	
	/**
	 * 名称列显示活动标识（0-否；1-是）
	 */
	public void setShowActivityLogo(Integer showActivityLogo) {
		this.showActivityLogo = showActivityLogo;
	}
	
	/**
	 * 名称列显示活动标识（0-否；1-是）
	 */
	public Integer getShowActivityLogo() {
		return showActivityLogo;
	}
	
	/**
	 * 自动抹零（0-否；1-是）
	 */
	public void setAutoMaling(Integer autoMaling) {
		this.autoMaling = autoMaling;
	}
	
	/**
	 * 自动抹零（0-否；1-是）
	 */
	public Integer getAutoMaling() {
		return autoMaling;
	}
	
	/**
	 * 自动抹零类型（0-抹零到角；1-抹零到元）
	 */
	public void setAutoMalingType(Integer autoMalingType) {
		this.autoMalingType = autoMalingType;
	}
	
	/**
	 * 自动抹零类型（0-抹零到角；1-抹零到元）
	 */
	public Integer getAutoMalingType() {
		return autoMalingType;
	}
	
	/**
	 * 小票样式（0-常规；1-标准；2-精简；3-自定义1）
	 */
	public void setSmallTicketStyle(Integer smallTicketStyle) {
		this.smallTicketStyle = smallTicketStyle;
	}
	
	/**
	 * 小票样式（0-常规；1-标准；2-精简；3-自定义1）
	 */
	public Integer getSmallTicketStyle() {
		return smallTicketStyle;
	}
	
	/**
	 * 打印份数，默认为1
	 */
	public void setPrintCopiess(Integer printCopiess) {
		this.printCopiess = printCopiess;
	}
	
	/**
	 * 打印份数，默认为1
	 */
	public Integer getPrintCopiess() {
		return printCopiess;
	}
	
	/**
	 * 状态（0-禁用；1-启用）
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	 * 状态（0-禁用；1-启用）
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	/**
	 * 备注
	 */
	public String getRemark() {
		return remark;
	}
	

}