package com.rograndec.feijiayun.chain.business.keytable.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class InOutRecord implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 企业ID
     */
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
    private Long parentId;

    /**
     * 方向（0-入库；1-出库；2-货位移动）
     */
    private Integer direction;

    /**
     * 业务单据ID
     */
    private Long orderId;

    /**
     * 业务单据编码
     */
    private String orderCode;

    /**
     * 单据类型
     */
    private Integer orderType;

    /**
     * 单据日期
     */
    private Date orderDate;

    /**
     * 
     */
    private Long companyId;

    /**
     * 单位名称
     */
    private String companyCode;

    /**
     * 
     */
    private String companyName;

    /**
     * 数量合计
     */
    private BigDecimal quantityTotal;

    /**
     * 品种数量
     */
    private Integer varietiesQuantity;

    /**
     * 金额合计（整单优惠前的金额合计）
     */
    private BigDecimal amountTotal;

    /**
     * 整单折扣（%）
     */
    private BigDecimal wholeDiscount;

    /**
     * 整单优惠金额
     */
    private BigDecimal wholeDiscountAmount;

    /**
     * 实际金额合计
     */
    private BigDecimal realAmountTotal;

    /**
     * 不含税金额合计
     */
    private BigDecimal notaxRealAmountTotal;

    /**
     * 税额合计
     */
    private BigDecimal taxAmountTotal;
    
    /**
     * 成本金额合计
     */
    private BigDecimal costAmountTotal;
    
    /**
     * 不含税成本金额合计
     */
    private BigDecimal notaxCostAmountTotal;

    /**
     * 利润合计
     */
    private BigDecimal profitTotal;

    /**
     * 不含税利润合计
     */
    private BigDecimal notaxProfitTotal;

    /**
     * 利润率
     */
    private BigDecimal profitRate;

    /**
     * 不含税利润率
     */
    private BigDecimal notaxProfitRate;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建人ID
     */
    private Long createrId;

    /**
     * 创建人编码
     */
    private String createrCode;

    /**
     * 创建人名称
     */
    private String createrName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后修改人ID
     */
    private Long modifierId;

    /**
     * 最后修改人编码
     */
    private String modifierCode;

    /**
     * 最后修改人名称
     */
    private String modifierName;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * saas_in_out_record
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
     * 方向（0-入库；1-出库）
     * @return direction 方向（0-入库；1-出库）
     */
    public Integer getDirection() {
        return direction;
    }

    /**
     * 方向（0-入库；1-出库）
     * @param direction 方向（0-入库；1-出库）
     */
    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    /**
     * 业务单据ID
     * @return order_id 业务单据ID
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * 业务单据ID
     * @param orderId 业务单据ID
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * 业务单据编码
     * @return order_code 业务单据编码
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * 业务单据编码
     * @param orderCode 业务单据编码
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    /**
     * 单据类型
     * @return order_type 单据类型
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 单据类型
     * @param orderType 单据类型
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 单据日期
     * @return order_date 单据日期
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * 单据日期
     * @param orderDate 单据日期
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * 
     * @return company_id 
     */
    public Long getCompanyId() {
        return companyId;
    }

    /**
     * 
     * @param companyId 
     */
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    /**
     * 单位名称
     * @return company_code 单位名称
     */
    public String getCompanyCode() {
        return companyCode;
    }

    /**
     * 单位名称
     * @param companyCode 单位名称
     */
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode == null ? null : companyCode.trim();
    }

    /**
     * 
     * @return company_name 
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 
     * @param companyName 
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    /**
     * 数量合计
     * @return quantity_total 数量合计
     */
    public BigDecimal getQuantityTotal() {
        return quantityTotal;
    }

    /**
     * 数量合计
     * @param quantityTotal 数量合计
     */
    public void setQuantityTotal(BigDecimal quantityTotal) {
        this.quantityTotal = quantityTotal;
    }

    /**
     * 品种数量
     * @return varieties_quantity 品种数量
     */
    public Integer getVarietiesQuantity() {
        return varietiesQuantity;
    }

    /**
     * 品种数量
     * @param varietiesQuantity 品种数量
     */
    public void setVarietiesQuantity(Integer varietiesQuantity) {
        this.varietiesQuantity = varietiesQuantity;
    }

    /**
     * 金额合计（整单优惠前的金额合计）
     * @return amount_total 金额合计（整单优惠前的金额合计）
     */
    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    /**
     * 金额合计（整单优惠前的金额合计）
     * @param amountTotal 金额合计（整单优惠前的金额合计）
     */
    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    /**
     * 整单折扣（%）
     * @return whole_discount 整单折扣（%）
     */
    public BigDecimal getWholeDiscount() {
        return wholeDiscount;
    }

    /**
     * 整单折扣（%）
     * @param wholeDiscount 整单折扣（%）
     */
    public void setWholeDiscount(BigDecimal wholeDiscount) {
        this.wholeDiscount = wholeDiscount;
    }

    /**
     * 整单优惠金额
     * @return whole_discount_amount 整单优惠金额
     */
    public BigDecimal getWholeDiscountAmount() {
        return wholeDiscountAmount;
    }

    /**
     * 整单优惠金额
     * @param wholeDiscountAmount 整单优惠金额
     */
    public void setWholeDiscountAmount(BigDecimal wholeDiscountAmount) {
        this.wholeDiscountAmount = wholeDiscountAmount;
    }

    /**
     * 实际金额合计
     * @return real_amount_total 实际金额合计
     */
    public BigDecimal getRealAmountTotal() {
        return realAmountTotal;
    }

    /**
     * 实际金额合计
     * @param realAmountTotal 实际金额合计
     */
    public void setRealAmountTotal(BigDecimal realAmountTotal) {
        this.realAmountTotal = realAmountTotal;
    }

    /**
     * 不含税金额合计
     * @return notax_real_amount_total 不含税金额合计
     */
    public BigDecimal getNotaxRealAmountTotal() {
        return notaxRealAmountTotal;
    }

    /**
     * 不含税金额合计
     * @param notaxRealAmountTotal 不含税金额合计
     */
    public void setNotaxRealAmountTotal(BigDecimal notaxRealAmountTotal) {
        this.notaxRealAmountTotal = notaxRealAmountTotal;
    }

    /**
     * 税额合计
     * @return tax_amount_total 税额合计
     */
    public BigDecimal getTaxAmountTotal() {
        return taxAmountTotal;
    }

    /**
     * 税额合计
     * @param taxAmountTotal 税额合计
     */
    public void setTaxAmountTotal(BigDecimal taxAmountTotal) {
        this.taxAmountTotal = taxAmountTotal;
    }

    /**
     * 成本金额合计
     * @return cost_amount_total 成本金额合计
     */
    public BigDecimal getCostAmountTotal() {
		return costAmountTotal;
	}

    /**
     * 成本金额合计
     * @param costAmountTotal 成本金额合计
     */
	public void setCostAmountTotal(BigDecimal costAmountTotal) {
		this.costAmountTotal = costAmountTotal;
	}

	/**
     * 不含税成本金额合计
     * @return notax_cost_amount_total 不含税成本金额合计
     */
	public BigDecimal getNotaxCostAmountTotal() {
		return notaxCostAmountTotal;
	}

	/**
     * 不含税成本金额合计
     * @param notaxCostAmountTotal 不含税成本金额合计
     */
	public void setNotaxCostAmountTotal(BigDecimal notaxCostAmountTotal) {
		this.notaxCostAmountTotal = notaxCostAmountTotal;
	}

	/**
     * 利润合计
     * @return profit_total 利润合计
     */
    public BigDecimal getProfitTotal() {
        return profitTotal;
    }

    /**
     * 利润合计
     * @param profitTotal 利润合计
     */
    public void setProfitTotal(BigDecimal profitTotal) {
        this.profitTotal = profitTotal;
    }

    /**
     * 不含税利润合计
     * @return notax_profit_total 不含税利润合计
     */
    public BigDecimal getNotaxProfitTotal() {
        return notaxProfitTotal;
    }

    /**
     * 不含税利润合计
     * @param notaxProfitTotal 不含税利润合计
     */
    public void setNotaxProfitTotal(BigDecimal notaxProfitTotal) {
        this.notaxProfitTotal = notaxProfitTotal;
    }

    /**
     * 利润率
     * @return profit_rate 利润率
     */
    public BigDecimal getProfitRate() {
        return profitRate;
    }

    /**
     * 利润率
     * @param profitRate 利润率
     */
    public void setProfitRate(BigDecimal profitRate) {
        this.profitRate = profitRate;
    }

    /**
     * 不含税利润率
     * @return notax_profit_rate 不含税利润率
     */
    public BigDecimal getNotaxProfitRate() {
        return notaxProfitRate;
    }

    /**
     * 不含税利润率
     * @param notaxProfitRate 不含税利润率
     */
    public void setNotaxProfitRate(BigDecimal notaxProfitRate) {
        this.notaxProfitRate = notaxProfitRate;
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
        sb.append(", direction=").append(direction);
        sb.append(", orderId=").append(orderId);
        sb.append(", orderCode=").append(orderCode);
        sb.append(", orderType=").append(orderType);
        sb.append(", orderDate=").append(orderDate);
        sb.append(", companyId=").append(companyId);
        sb.append(", companyCode=").append(companyCode);
        sb.append(", companyName=").append(companyName);
        sb.append(", quantityTotal=").append(quantityTotal);
        sb.append(", varietiesQuantity=").append(varietiesQuantity);
        sb.append(", amountTotal=").append(amountTotal);
        sb.append(", wholeDiscount=").append(wholeDiscount);
        sb.append(", wholeDiscountAmount=").append(wholeDiscountAmount);
        sb.append(", realAmountTotal=").append(realAmountTotal);
        sb.append(", notaxRealAmountTotal=").append(notaxRealAmountTotal);
        sb.append(", taxAmountTotal=").append(taxAmountTotal);
        sb.append(", costAmountTotal=").append(costAmountTotal);
        sb.append(", notaxCostAmountTotal=").append(notaxCostAmountTotal);
        sb.append(", profitTotal=").append(profitTotal);
        sb.append(", notaxProfitTotal=").append(notaxProfitTotal);
        sb.append(", profitRate=").append(profitRate);
        sb.append(", notaxProfitRate=").append(notaxProfitRate);
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