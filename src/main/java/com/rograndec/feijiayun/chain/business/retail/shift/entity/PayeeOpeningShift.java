package com.rograndec.feijiayun.chain.business.retail.shift.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_payee_opening_shift
 * 
 * 
 * @author Asze
 * 
 * 2017-09-19
 */
public class PayeeOpeningShift implements Serializable {
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
     * 开班人员账号
     */
    @ApiModelProperty(value = "开班人员账号")
    private String openingAccount;

    /**
     * 收款人员ID
     */
    @ApiModelProperty(value = "收款人员ID")
    private Long payeeId;

    /**
     * 收款人编码
     */
    @ApiModelProperty(value = "收款人编码")
    private String payeeCode;

    /**
     * 收款人名称
     */
    @ApiModelProperty(value = "收款人名称")
    private String payeeName;

    /**
     * 开班时间
     */
    @ApiModelProperty(value = "开班时间")
    private Date openingTime;

    /**
     * 交班时间
     */
    @ApiModelProperty(value = "交班时间")
    private Date shiftTime;

    /**
     * 销售笔数
     */
    @ApiModelProperty(value = "销售笔数")
    private Integer salePens;

    /**
     * 销售金额
     */
    @ApiModelProperty(value = "销售金额")
    private BigDecimal saleAmount;

    /**
     * 退货笔数
     */
    @ApiModelProperty(value = "退货笔数")
    private Integer returnPens;

    /**
     * 退货金额
     */
    @ApiModelProperty(value = "退货金额")
    private BigDecimal returnAmount;

    /**
     * 接收备用金
     */
    @ApiModelProperty(value = "接收备用金")
    private BigDecimal acceptSpareMoney;

    /**
     * 应缴现金
     */
    @ApiModelProperty(value = "应缴现金")
    private BigDecimal payableCash;

    /**
     * 上缴现金
     */
    @ApiModelProperty(value = "上缴现金")
    private BigDecimal cash;

    /**
     * 下放备用金
     */
    @ApiModelProperty(value = "下放备用金")
    private BigDecimal sendSpareMoney;

    /**
     * 日结标识（0-未日结；1-已日结）
     */
    @ApiModelProperty(value = "日结标识（0-未日结；1-已日结）")
    private Integer dailySettleFlag;

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
     * 缴款标识（0-未缴款；1-已缴款）
     */
    @ApiModelProperty(value = "缴款标识（0-未缴款；1-已缴款）")
    private Integer paymentFlag;

    /**
     * saas_payee_opening_shift
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
     * 开班人员账号
     * @return opening_account 开班人员账号
     */
    public String getOpeningAccount() {
        return openingAccount;
    }

    /**
     * 开班人员账号
     * @param openingAccount 开班人员账号
     */
    public void setOpeningAccount(String openingAccount) {
        this.openingAccount = openingAccount == null ? null : openingAccount.trim();
    }

    /**
     * 收款人员ID
     * @return payee_id 收款人员ID
     */
    public Long getPayeeId() {
        return payeeId;
    }

    /**
     * 收款人员ID
     * @param payeeId 收款人员ID
     */
    public void setPayeeId(Long payeeId) {
        this.payeeId = payeeId;
    }

    /**
     * 收款人编码
     * @return payee_code 收款人编码
     */
    public String getPayeeCode() {
        return payeeCode;
    }

    /**
     * 收款人编码
     * @param payeeCode 收款人编码
     */
    public void setPayeeCode(String payeeCode) {
        this.payeeCode = payeeCode == null ? null : payeeCode.trim();
    }

    /**
     * 收款人名称
     * @return payee_name 收款人名称
     */
    public String getPayeeName() {
        return payeeName;
    }

    /**
     * 收款人名称
     * @param payeeName 收款人名称
     */
    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName == null ? null : payeeName.trim();
    }

    /**
     * 开班时间
     * @return opening_time 开班时间
     */
    public Date getOpeningTime() {
        return openingTime;
    }

    /**
     * 开班时间
     * @param openingTime 开班时间
     */
    public void setOpeningTime(Date openingTime) {
        this.openingTime = openingTime;
    }

    /**
     * 交班时间
     * @return shift_time 交班时间
     */
    public Date getShiftTime() {
        return shiftTime;
    }

    /**
     * 交班时间
     * @param shiftTime 交班时间
     */
    public void setShiftTime(Date shiftTime) {
        this.shiftTime = shiftTime;
    }

    /**
     * 销售笔数
     * @return sale_pens 销售笔数
     */
    public Integer getSalePens() {
        return salePens;
    }

    /**
     * 销售笔数
     * @param salePens 销售笔数
     */
    public void setSalePens(Integer salePens) {
        this.salePens = salePens;
    }

    /**
     * 销售金额
     * @return sale_amount 销售金额
     */
    public BigDecimal getSaleAmount() {
        return saleAmount;
    }

    /**
     * 销售金额
     * @param saleAmount 销售金额
     */
    public void setSaleAmount(BigDecimal saleAmount) {
        this.saleAmount = saleAmount;
    }

    /**
     * 退货笔数
     * @return return_pens 退货笔数
     */
    public Integer getReturnPens() {
        return returnPens;
    }

    /**
     * 退货笔数
     * @param returnPens 退货笔数
     */
    public void setReturnPens(Integer returnPens) {
        this.returnPens = returnPens;
    }

    /**
     * 退货金额
     * @return return_amount 退货金额
     */
    public BigDecimal getReturnAmount() {
        return returnAmount;
    }

    /**
     * 退货金额
     * @param returnAmount 退货金额
     */
    public void setReturnAmount(BigDecimal returnAmount) {
        this.returnAmount = returnAmount;
    }

    /**
     * 接收备用金
     * @return accept_spare_money 接收备用金
     */
    public BigDecimal getAcceptSpareMoney() {
        return acceptSpareMoney;
    }

    /**
     * 接收备用金
     * @param acceptSpareMoney 接收备用金
     */
    public void setAcceptSpareMoney(BigDecimal acceptSpareMoney) {
        this.acceptSpareMoney = acceptSpareMoney;
    }

    /**
     * 应缴现金
     * @return payable_cash 应缴现金
     */
    public BigDecimal getPayableCash() {
        return payableCash;
    }

    /**
     * 应缴现金
     * @param payableCash 应缴现金
     */
    public void setPayableCash(BigDecimal payableCash) {
        this.payableCash = payableCash;
    }

    /**
     * 上缴现金
     * @return cash 上缴现金
     */
    public BigDecimal getCash() {
        return cash;
    }

    /**
     * 上缴现金
     * @param cash 上缴现金
     */
    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    /**
     * 下放备用金
     * @return send_spare_money 下放备用金
     */
    public BigDecimal getSendSpareMoney() {
        return sendSpareMoney;
    }

    /**
     * 下放备用金
     * @param sendSpareMoney 下放备用金
     */
    public void setSendSpareMoney(BigDecimal sendSpareMoney) {
        this.sendSpareMoney = sendSpareMoney;
    }

    /**
     * 日结标识（0-未日结；1-已日结）
     * @return daily_settle_flag 日结标识（0-未日结；1-已日结）
     */
    public Integer getDailySettleFlag() {
        return dailySettleFlag;
    }

    /**
     * 日结标识（0-未日结；1-已日结）
     * @param dailySettleFlag 日结标识（0-未日结；1-已日结）
     */
    public void setDailySettleFlag(Integer dailySettleFlag) {
        this.dailySettleFlag = dailySettleFlag;
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

    public Integer getPaymentFlag() {
		return paymentFlag;
	}

	public void setPaymentFlag(Integer paymentFlag) {
		this.paymentFlag = paymentFlag;
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
        sb.append(", openingAccount=").append(openingAccount);
        sb.append(", payeeId=").append(payeeId);
        sb.append(", payeeCode=").append(payeeCode);
        sb.append(", payeeName=").append(payeeName);
        sb.append(", openingTime=").append(openingTime);
        sb.append(", shiftTime=").append(shiftTime);
        sb.append(", salePens=").append(salePens);
        sb.append(", saleAmount=").append(saleAmount);
        sb.append(", returnPens=").append(returnPens);
        sb.append(", returnAmount=").append(returnAmount);
        sb.append(", acceptSpareMoney=").append(acceptSpareMoney);
        sb.append(", payableCash=").append(payableCash);
        sb.append(", cash=").append(cash);
        sb.append(", sendSpareMoney=").append(sendSpareMoney);
        sb.append(", dailySettleFlag=").append(dailySettleFlag);
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
        sb.append(", paymentFlag=").append(paymentFlag);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}