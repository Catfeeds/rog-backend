package com.rograndec.feijiayun.chain.business.retail.pos.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

 /**
 * 
 * @ClassName: PosPayeeAuthVO
 * @Description:  零售管理-POS管理-款员权限
 * @author yuting.li
 * @version 1.0 
 * @date 2017-09-19 14:52:35
 */
@ApiModel(value = "PosPayeeAuthVO", description = "零售管理-POS管理-款员权限")
public class PosPayeeAuthVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * 主键ID
     */
	@ApiModelProperty(required = true, value = "主键ID")
	private Long id;
	
	/**
     * 款员表主键ID
     */
	@ApiModelProperty(required = true, value = "款员表主键ID")
	private Long payKeyId;
	
	
	/**
     * 收款人员ID
     */
	@ApiModelProperty(required = true, value = "收款人员ID")
	private Long payeeId;
	
	/**
     * 收款人编码
     */
	@ApiModelProperty(required = true, value = "收款人编码")
	private String payeeCode;
	
	/**
     * 收款人名称
     */
	@ApiModelProperty(required = false, value = "收款人名称")
	private String payeeName;
	
	/**
     * 开单收款（0-否；1-是）
     */
	@ApiModelProperty(required = false, value = "开单收款（0-否；1-是）")
	private Integer billingPayment;
	
	/**
     * 款员交班（0-否；1-是）
     */
	@ApiModelProperty(required = false, value = "款员交班（0-否；1-是）")
	private Integer payeeShift;
	
	/**
     * 报表查询（0-否；1-是）
     */
	@ApiModelProperty(required = false, value = "报表查询（0-否；1-是）")
	private Integer reportQuery;
	
	/**
     * 预售开票（0-否；1-是）
     */
	@ApiModelProperty(required = false, value = "预售开票（0-否；1-是）")
	private Integer advanceBooking;
	
	/**
     * 增加会员（0-否；1-是）
     */
	@ApiModelProperty(required = false, value = "增加会员（0-否；1-是）")
	private Integer addMember;
	
	/**
     * 收款练习（0-否；1-是）
     */
	@ApiModelProperty(required = false, value = "收款练习（0-否；1-是）")
	private Integer collectionPractice;
	
	/**
     * 操作日志（0-否；1-是）
     */
	@ApiModelProperty(required = false, value = "操作日志（0-否；1-是）")
	private Integer operationLog;
	
	/**
     * 系统设置（0-否；1-是）
     */
	@ApiModelProperty(required = false, value = "系统设置（0-否；1-是）")
	private Integer systemSet;
	
	/**
     * 中药模式（0-否；1-是）
     */
	@ApiModelProperty(required = false, value = "中药模式（0-否；1-是）")
	private Integer chMedicineMode;
	
	/**
     * 补单模式（0-否；1-是）
     */
	@ApiModelProperty(required = false, value = "补单模式（0-否；1-是）")
	private Integer patchMode;
	
	/**
     * 销售退货（0-否；1-是）
     */
	@ApiModelProperty(required = false, value = "销售退货（0-否；1-是）")
	private Integer saleReturn;
	
	/**
     * 查看应缴金额（0-否；1-是）
     */
	@ApiModelProperty(required = false, value = "查看应缴金额（0-否；1-是）")
	private Integer queryPayableAmount;
	
	/**
     * 修改单价和金额
     */
	@ApiModelProperty(required = false, value = "修改单价和金额")
	private Integer modifyPriceAmount;
	
	/**
     * 修改行折扣（0-否；1-是）
     */
	@ApiModelProperty(required = false, value = "修改行折扣（0-否；1-是）")
	private Integer modifyLineDiscount;
	
	/**
     * 整单折扣
     */
	@ApiModelProperty(required = false, value = "整单折扣")
	private Integer wholeDiscount;
	
	/**
     * 抹零（0-否；1-是）
     */
	@ApiModelProperty(required = false, value = "抹零（0-否；1-是）")
	private Integer maling;
	
	/**
     * 状态（0-禁用；1-启用）
     */
	@ApiModelProperty(required = true, value = "状态（0-禁用；1-启用）")
	private Integer status;

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
	 * 款员表主键ID
	 */
	public void setPayKeyId(Long payKeyId) {
		this.payKeyId = payKeyId;
	}
	
	/**
	 * 款员表主键ID
	 */
	public Long getPayKeyId() {
		return payKeyId;
	}
	
	
	/**
	 * 收款人员ID
	 */
	public void setPayeeId(Long payeeId) {
		this.payeeId = payeeId;
	}
	
	/**
	 * 收款人员ID
	 */
	public Long getPayeeId() {
		return payeeId;
	}
	
	/**
	 * 收款人编码
	 */
	public void setPayeeCode(String payeeCode) {
		this.payeeCode = payeeCode;
	}
	
	/**
	 * 收款人编码
	 */
	public String getPayeeCode() {
		return payeeCode;
	}
	
	/**
	 * 收款人名称
	 */
	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}
	
	/**
	 * 收款人名称
	 */
	public String getPayeeName() {
		return payeeName;
	}
	
	/**
	 * 开单收款（0-否；1-是）
	 */
	public void setBillingPayment(Integer billingPayment) {
		this.billingPayment = billingPayment;
	}
	
	/**
	 * 开单收款（0-否；1-是）
	 */
	public Integer getBillingPayment() {
		return billingPayment;
	}
	
	/**
	 * 款员交班（0-否；1-是）
	 */
	public void setPayeeShift(Integer payeeShift) {
		this.payeeShift = payeeShift;
	}
	
	/**
	 * 款员交班（0-否；1-是）
	 */
	public Integer getPayeeShift() {
		return payeeShift;
	}
	
	/**
	 * 报表查询（0-否；1-是）
	 */
	public void setReportQuery(Integer reportQuery) {
		this.reportQuery = reportQuery;
	}
	
	/**
	 * 报表查询（0-否；1-是）
	 */
	public Integer getReportQuery() {
		return reportQuery;
	}
	
	/**
	 * 预售开票（0-否；1-是）
	 */
	public void setAdvanceBooking(Integer advanceBooking) {
		this.advanceBooking = advanceBooking;
	}
	
	/**
	 * 预售开票（0-否；1-是）
	 */
	public Integer getAdvanceBooking() {
		return advanceBooking;
	}
	
	/**
	 * 增加会员（0-否；1-是）
	 */
	public void setAddMember(Integer addMember) {
		this.addMember = addMember;
	}
	
	/**
	 * 增加会员（0-否；1-是）
	 */
	public Integer getAddMember() {
		return addMember;
	}
	
	/**
	 * 收款练习（0-否；1-是）
	 */
	public void setCollectionPractice(Integer collectionPractice) {
		this.collectionPractice = collectionPractice;
	}
	
	/**
	 * 收款练习（0-否；1-是）
	 */
	public Integer getCollectionPractice() {
		return collectionPractice;
	}
	
	/**
	 * 操作日志（0-否；1-是）
	 */
	public void setOperationLog(Integer operationLog) {
		this.operationLog = operationLog;
	}
	
	/**
	 * 操作日志（0-否；1-是）
	 */
	public Integer getOperationLog() {
		return operationLog;
	}
	
	/**
	 * 系统设置（0-否；1-是）
	 */
	public void setSystemSet(Integer systemSet) {
		this.systemSet = systemSet;
	}
	
	/**
	 * 系统设置（0-否；1-是）
	 */
	public Integer getSystemSet() {
		return systemSet;
	}
	
	/**
	 * 中药模式（0-否；1-是）
	 */
	public void setChMedicineMode(Integer chMedicineMode) {
		this.chMedicineMode = chMedicineMode;
	}
	
	/**
	 * 中药模式（0-否；1-是）
	 */
	public Integer getChMedicineMode() {
		return chMedicineMode;
	}
	
	/**
	 * 补单模式（0-否；1-是）
	 */
	public void setPatchMode(Integer patchMode) {
		this.patchMode = patchMode;
	}
	
	/**
	 * 补单模式（0-否；1-是）
	 */
	public Integer getPatchMode() {
		return patchMode;
	}
	
	/**
	 * 销售退货（0-否；1-是）
	 */
	public void setSaleReturn(Integer saleReturn) {
		this.saleReturn = saleReturn;
	}
	
	/**
	 * 销售退货（0-否；1-是）
	 */
	public Integer getSaleReturn() {
		return saleReturn;
	}
	
	/**
	 * 查看应缴金额（0-否；1-是）
	 */
	public void setQueryPayableAmount(Integer queryPayableAmount) {
		this.queryPayableAmount = queryPayableAmount;
	}
	
	/**
	 * 查看应缴金额（0-否；1-是）
	 */
	public Integer getQueryPayableAmount() {
		return queryPayableAmount;
	}
	
	/**
	 * 修改单价和金额
	 */
	public void setModifyPriceAmount(Integer modifyPriceAmount) {
		this.modifyPriceAmount = modifyPriceAmount;
	}
	
	/**
	 * 修改单价和金额
	 */
	public Integer getModifyPriceAmount() {
		return modifyPriceAmount;
	}
	
	/**
	 * 修改行折扣（0-否；1-是）
	 */
	public void setModifyLineDiscount(Integer modifyLineDiscount) {
		this.modifyLineDiscount = modifyLineDiscount;
	}
	
	/**
	 * 修改行折扣（0-否；1-是）
	 */
	public Integer getModifyLineDiscount() {
		return modifyLineDiscount;
	}
	
	/**
	 * 整单折扣
	 */
	public void setWholeDiscount(Integer wholeDiscount) {
		this.wholeDiscount = wholeDiscount;
	}
	
	/**
	 * 整单折扣
	 */
	public Integer getWholeDiscount() {
		return wholeDiscount;
	}
	
	/**
	 * 抹零（0-否；1-是）
	 */
	public void setMaling(Integer maling) {
		this.maling = maling;
	}
	
	/**
	 * 抹零（0-否；1-是）
	 */
	public Integer getMaling() {
		return maling;
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
	

}