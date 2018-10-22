package com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_finance_receivable_detail
 * 
 * 
 * @author lizhongyi
 * 
 * 2018-01-04
 */
public class FinanceReceivableDetail implements Serializable {
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
     * 收款总单ID
     */
    @ApiModelProperty(value = "收款总单ID")
    private Long receivableId;

    /**
     * 单据ID
     */
    @ApiModelProperty(value = "单据ID")
    private Long baseOrderId;

    /**
     * 单据编码
     */
    @ApiModelProperty(value = "单据编码")
    private String baseOrderCode;

    /**
     * 单据类型
     */
    @ApiModelProperty(value = "单据类型")
    private Integer baseOrderType;

    /**
     * 单据日期
     */
    @ApiModelProperty(value = "单据日期")
    private Date baseOrderDate;

    /**
     * 金额
     */
    @ApiModelProperty(value = "金额")
    private BigDecimal amount;

    /**
     * 已清金额合计
     */
    @ApiModelProperty(value = "已清金额合计")
    private BigDecimal clearAmount;

    /**
     * 未清金额合计
     */
    @ApiModelProperty(value = "未清金额合计")
    private BigDecimal unclearAmount;

    /**
     * 本次应收金额
     */
    @ApiModelProperty(value = "本次应收金额")
    private BigDecimal receivableAmount;

    /**
     * 本次优惠金额
     */
    @ApiModelProperty(value = "本次优惠金额")
    private BigDecimal discountAmount;

    /**
     * 本次实收金额
     */
    @ApiModelProperty(value = "本次实收金额")
    private BigDecimal realAmount;

    /**
     * 未清余额
     */
    @ApiModelProperty(value = "未清余额")
    private BigDecimal unclearBalance;

    /**
     * 行号
     */
    @ApiModelProperty(value = "行号")
    private Integer lineNum;

    /**
     * 状态（0-待收款；1-已完成；2-已冲销）
     */
    @ApiModelProperty(value = "状态（0-待收款；1-已完成；2-已冲销）")
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
     * saas_finance_receivable_detail
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
     * 收款总单ID
     * @return receivable_id 收款总单ID
     */
    public Long getReceivableId() {
        return receivableId;
    }

    /**
     * 收款总单ID
     * @param receivableId 收款总单ID
     */
    public void setReceivableId(Long receivableId) {
        this.receivableId = receivableId;
    }

    /**
     * 单据ID
     * @return base_order_id 单据ID
     */
    public Long getBaseOrderId() {
        return baseOrderId;
    }

    /**
     * 单据ID
     * @param baseOrderId 单据ID
     */
    public void setBaseOrderId(Long baseOrderId) {
        this.baseOrderId = baseOrderId;
    }

    /**
     * 单据编码
     * @return base_order_code 单据编码
     */
    public String getBaseOrderCode() {
        return baseOrderCode;
    }

    /**
     * 单据编码
     * @param baseOrderCode 单据编码
     */
    public void setBaseOrderCode(String baseOrderCode) {
        this.baseOrderCode = baseOrderCode == null ? null : baseOrderCode.trim();
    }

    /**
     * 单据类型
     * @return base_order_type 单据类型
     */
    public Integer getBaseOrderType() {
        return baseOrderType;
    }

    /**
     * 单据类型
     * @param baseOrderType 单据类型
     */
    public void setBaseOrderType(Integer baseOrderType) {
        this.baseOrderType = baseOrderType;
    }

    /**
     * 单据日期
     * @return base_order_date 单据日期
     */
    public Date getBaseOrderDate() {
        return baseOrderDate;
    }

    /**
     * 单据日期
     * @param baseOrderDate 单据日期
     */
    public void setBaseOrderDate(Date baseOrderDate) {
        this.baseOrderDate = baseOrderDate;
    }

    /**
     * 金额
     * @return amount 金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 金额
     * @param amount 金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 已清金额合计
     * @return clear_amount 已清金额合计
     */
    public BigDecimal getClearAmount() {
        return clearAmount;
    }

    /**
     * 已清金额合计
     * @param clearAmount 已清金额合计
     */
    public void setClearAmount(BigDecimal clearAmount) {
        this.clearAmount = clearAmount;
    }

    /**
     * 未清金额合计
     * @return unclear_amount 未清金额合计
     */
    public BigDecimal getUnclearAmount() {
        return unclearAmount;
    }

    /**
     * 未清金额合计
     * @param unclearAmount 未清金额合计
     */
    public void setUnclearAmount(BigDecimal unclearAmount) {
        this.unclearAmount = unclearAmount;
    }

    /**
     * 本次应收金额
     * @return receivable_amount 本次应收金额
     */
    public BigDecimal getReceivableAmount() {
        return receivableAmount;
    }

    /**
     * 本次应收金额
     * @param receivableAmount 本次应收金额
     */
    public void setReceivableAmount(BigDecimal receivableAmount) {
        this.receivableAmount = receivableAmount;
    }

    /**
     * 本次优惠金额
     * @return discount_amount 本次优惠金额
     */
    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    /**
     * 本次优惠金额
     * @param discountAmount 本次优惠金额
     */
    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    /**
     * 本次实收金额
     * @return real_amount 本次实收金额
     */
    public BigDecimal getRealAmount() {
        return realAmount;
    }

    /**
     * 本次实收金额
     * @param realAmount 本次实收金额
     */
    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }

    /**
     * 未清余额
     * @return unclear_balance 未清余额
     */
    public BigDecimal getUnclearBalance() {
        return unclearBalance;
    }

    /**
     * 未清余额
     * @param unclearBalance 未清余额
     */
    public void setUnclearBalance(BigDecimal unclearBalance) {
        this.unclearBalance = unclearBalance;
    }

    /**
     * 行号
     * @return line_num 行号
     */
    public Integer getLineNum() {
        return lineNum;
    }

    /**
     * 行号
     * @param lineNum 行号
     */
    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }

    /**
     * 状态（0-待收款；1-已完成；2-已冲销）
     * @return status 状态（0-待收款；1-已完成；2-已冲销）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态（0-待收款；1-已完成；2-已冲销）
     * @param status 状态（0-待收款；1-已完成；2-已冲销）
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
        sb.append(", receivableId=").append(receivableId);
        sb.append(", baseOrderId=").append(baseOrderId);
        sb.append(", baseOrderCode=").append(baseOrderCode);
        sb.append(", baseOrderType=").append(baseOrderType);
        sb.append(", baseOrderDate=").append(baseOrderDate);
        sb.append(", amount=").append(amount);
        sb.append(", clearAmount=").append(clearAmount);
        sb.append(", unclearAmount=").append(unclearAmount);
        sb.append(", receivableAmount=").append(receivableAmount);
        sb.append(", discountAmount=").append(discountAmount);
        sb.append(", realAmount=").append(realAmount);
        sb.append(", unclearBalance=").append(unclearBalance);
        sb.append(", lineNum=").append(lineNum);
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