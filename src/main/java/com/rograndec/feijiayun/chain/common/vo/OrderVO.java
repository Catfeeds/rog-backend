package com.rograndec.feijiayun.chain.common.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @ClassName: OrderVO  
 * @Description: 单据对象
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年8月26日 上午10:44:07  
 *
 */
public class OrderVO {

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
     * 单位ID
     */
    private Long companyId;

    /**
     * 单位编码
     */
    private String companyCode;

    /**
     * 单位名称
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

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Integer getDirection() {
		return direction;
	}

	public void setDirection(Integer direction) {
		this.direction = direction;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public BigDecimal getQuantityTotal() {
		return quantityTotal;
	}

	public void setQuantityTotal(BigDecimal quantityTotal) {
		this.quantityTotal = quantityTotal;
	}

	public Integer getVarietiesQuantity() {
		return varietiesQuantity;
	}

	public void setVarietiesQuantity(Integer varietiesQuantity) {
		this.varietiesQuantity = varietiesQuantity;
	}

	public BigDecimal getAmountTotal() {
		return amountTotal;
	}

	public void setAmountTotal(BigDecimal amountTotal) {
		this.amountTotal = amountTotal;
	}

	public BigDecimal getWholeDiscount() {
		return wholeDiscount;
	}

	public void setWholeDiscount(BigDecimal wholeDiscount) {
		this.wholeDiscount = wholeDiscount;
	}

	public BigDecimal getWholeDiscountAmount() {
		return wholeDiscountAmount;
	}

	public void setWholeDiscountAmount(BigDecimal wholeDiscountAmount) {
		this.wholeDiscountAmount = wholeDiscountAmount;
	}

	public BigDecimal getRealAmountTotal() {
		return realAmountTotal;
	}

	public void setRealAmountTotal(BigDecimal realAmountTotal) {
		this.realAmountTotal = realAmountTotal;
	}

	public BigDecimal getNotaxRealAmountTotal() {
		return notaxRealAmountTotal;
	}

	public void setNotaxRealAmountTotal(BigDecimal notaxRealAmountTotal) {
		this.notaxRealAmountTotal = notaxRealAmountTotal;
	}

	public BigDecimal getTaxAmountTotal() {
		return taxAmountTotal;
	}

	public void setTaxAmountTotal(BigDecimal taxAmountTotal) {
		this.taxAmountTotal = taxAmountTotal;
	}

	public BigDecimal getCostAmountTotal() {
		return costAmountTotal;
	}

	public void setCostAmountTotal(BigDecimal costAmountTotal) {
		this.costAmountTotal = costAmountTotal;
	}

	public BigDecimal getNotaxCostAmountTotal() {
		return notaxCostAmountTotal;
	}

	public void setNotaxCostAmountTotal(BigDecimal notaxCostAmountTotal) {
		this.notaxCostAmountTotal = notaxCostAmountTotal;
	}

	public BigDecimal getProfitTotal() {
		return profitTotal;
	}

	public void setProfitTotal(BigDecimal profitTotal) {
		this.profitTotal = profitTotal;
	}

	public BigDecimal getNotaxProfitTotal() {
		return notaxProfitTotal;
	}

	public void setNotaxProfitTotal(BigDecimal notaxProfitTotal) {
		this.notaxProfitTotal = notaxProfitTotal;
	}

	public BigDecimal getProfitRate() {
		return profitRate;
	}

	public void setProfitRate(BigDecimal profitRate) {
		this.profitRate = profitRate;
	}

	public BigDecimal getNotaxProfitRate() {
		return notaxProfitRate;
	}

	public void setNotaxProfitRate(BigDecimal notaxProfitRate) {
		this.notaxProfitRate = notaxProfitRate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getCreaterId() {
		return createrId;
	}

	public void setCreaterId(Long createrId) {
		this.createrId = createrId;
	}

	public String getCreaterCode() {
		return createrCode;
	}

	public void setCreaterCode(String createrCode) {
		this.createrCode = createrCode;
	}

	public String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getModifierId() {
		return modifierId;
	}

	public void setModifierId(Long modifierId) {
		this.modifierId = modifierId;
	}

	public String getModifierCode() {
		return modifierCode;
	}

	public void setModifierCode(String modifierCode) {
		this.modifierCode = modifierCode;
	}

	public String getModifierName() {
		return modifierName;
	}

	public void setModifierName(String modifierName) {
		this.modifierName = modifierName;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "OrderVO{" +
				"orderId=" + orderId +
				", orderCode='" + orderCode + '\'' +
				", orderType=" + orderType +
				", orderDate=" + orderDate +
				'}';
	}

}
