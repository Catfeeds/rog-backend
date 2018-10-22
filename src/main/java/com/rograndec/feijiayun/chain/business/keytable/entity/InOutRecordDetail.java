package com.rograndec.feijiayun.chain.business.keytable.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class InOutRecordDetail implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 出入库单ID
     */
    private Long inOutId;

    /**
     * 企业ID
     */
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
    private Long parentId;

    /**
     * 方向（0-入库；1-出库）
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
     * 业务单据明细ID
     */
    private Long orderDtlId;

    /**
     * 单据类型
     */
    private Integer orderType;

    /**
     * 单据日期
     */
    private Date orderDate;

    /**
     * 行号
     */
    private Integer lineNum;

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
     * 商品ID
     */
    private Long goodsId;

    /**
     * 商品编码
     */
    private String goodsCode;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 批号ID
     */
    private Long lotId;
    
    /**
     * 批号
     */
    private String lotNum;

    /**
     * 货位ID
     */
    private Long shelfId;

    /**
     * 货位名称
     */
    private String shelfName;

    /**
     * 数量
     */
    private BigDecimal quantity;

    /**
     * 业务发生前商品库存数量
     */
    private BigDecimal storageQuantity;

    /**
     * 单价
     */
    private BigDecimal unitPrice;

    /**
     * 行折扣（%）
     */
    private BigDecimal lineDiscount;

    /**
     * 金额（整单优惠前金额=数量*单价*行折扣）
     */
    private BigDecimal amount;

    /**
     * 整单折扣（%）
     */
    private BigDecimal wholeDiscount;

    /**
     * 行优惠（整单优惠分摊到行的金额）
     */
    private BigDecimal lineDiscountAmount;

    /**
     * 实际单价（实际金额/数量）
     */
    private BigDecimal realPrice;

    /**
     * 实际金额（实际单价*数量）
     */
    private BigDecimal realAmount;

    /**
     * 税率
     */
    private BigDecimal taxRate;

    /**
     * 不含税单价
     */
    private BigDecimal notaxRealPrice;

    /**
     * 不含税金额
     */
    private BigDecimal notaxRealAmount;

    /**
     * 税额
     */
    private BigDecimal taxAmount;
    
    /**
     * 成本金额
     */
    private BigDecimal costAmount;
    
    /**
     * 不含税成本金额
     */
    private BigDecimal notaxCostAmount;

    /**
     * 利润金额
     */
    private BigDecimal profit;

    /**
     * 不含税利润金额
     */
    private BigDecimal notaxProfit;

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
     * saas_in_out_record_detail
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
     * 出入库单ID
     * @return in_out_id 出入库单ID
     */
    public Long getInOutId() {
        return inOutId;
    }

    /**
     * 出入库单ID
     * @param inOutId 出入库单ID
     */
    public void setInOutId(Long inOutId) {
        this.inOutId = inOutId;
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
     * 业务单据明细ID
     * @return order_dtl_id 业务单据明细ID
     */
    public Long getOrderDtlId() {
        return orderDtlId;
    }

    /**
     * 业务单据明细ID
     * @param orderDtlId 业务单据明细ID
     */
    public void setOrderDtlId(Long orderDtlId) {
        this.orderDtlId = orderDtlId;
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
     * 单位ID
     * @return company_id 单位ID
     */
    public Long getCompanyId() {
        return companyId;
    }

    /**
     * 单位ID
     * @param companyId 单位ID
     */
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    /**
     * 单位编码
     * @return company_code 单位编码
     */
    public String getCompanyCode() {
        return companyCode;
    }

    /**
     * 单位编码
     * @param companyCode 单位编码
     */
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode == null ? null : companyCode.trim();
    }

    /**
     * 单位名称
     * @return company_name 单位名称
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 单位名称
     * @param companyName 单位名称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    /**
     * 商品ID
     * @return goods_id 商品ID
     */
    public Long getGoodsId() {
        return goodsId;
    }

    /**
     * 商品ID
     * @param goodsId 商品ID
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 商品编码
     * @return goods_code 商品编码
     */
    public String getGoodsCode() {
        return goodsCode;
    }

    /**
     * 商品编码
     * @param goodsCode 商品编码
     */
    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode == null ? null : goodsCode.trim();
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
     * 批号ID
     * @return lot_id 批号ID
     */
    public Long getLotId() {
		return lotId;
	}
    
    /**
     * 批号ID
     * @return lot_id 批号ID
     */
    public void setLotId(Long lotId) {
		this.lotId = lotId;
	}
    
    /**
     * 批号
     * @return lot_num 批号
     */
    public String getLotNum() {
        return lotNum;
    }

    /**
     * 批号
     * @param lotNum 批号
     */
    public void setLotNum(String lotNum) {
        this.lotNum = lotNum == null ? null : lotNum.trim();
    }

    /**
     * 货位ID
     * @return shelf_id 货位ID
     */
    public Long getShelfId() {
        return shelfId;
    }

    /**
     * 货位ID
     * @param shelfId 货位ID
     */
    public void setShelfId(Long shelfId) {
        this.shelfId = shelfId;
    }

    /**
     * 货位名称
     * @return shelf_name 货位名称
     */
    public String getShelfName() {
        return shelfName;
    }

    /**
     * 货位名称
     * @param shelfName 货位名称
     */
    public void setShelfName(String shelfName) {
        this.shelfName = shelfName == null ? null : shelfName.trim();
    }

    /**
     * 数量
     * @return quantity 数量
     */
    public BigDecimal getQuantity() {
        return quantity;
    }

    /**
     * 数量
     * @param quantity 数量
     */
    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    /**
     * 业务发生前商品库存数量
     * @return storage_quantity 业务发生前商品库存数量
     */
    public BigDecimal getStorageQuantity() {
        return storageQuantity;
    }

    /**
     * 业务发生前商品库存数量
     * @param storageQuantity 业务发生前商品库存数量
     */
    public void setStorageQuantity(BigDecimal storageQuantity) {
        this.storageQuantity = storageQuantity;
    }

    /**
     * 单价
     * @return unit_price 单价
     */
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     * 单价
     * @param unitPrice 单价
     */
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * 行折扣（%）
     * @return line_discount 行折扣（%）
     */
    public BigDecimal getLineDiscount() {
        return lineDiscount;
    }

    /**
     * 行折扣（%）
     * @param lineDiscount 行折扣（%）
     */
    public void setLineDiscount(BigDecimal lineDiscount) {
        this.lineDiscount = lineDiscount;
    }

    /**
     * 金额（整单优惠前金额=数量*单价*行折扣）
     * @return amount 金额（整单优惠前金额=数量*单价*行折扣）
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 金额（整单优惠前金额=数量*单价*行折扣）
     * @param amount 金额（整单优惠前金额=数量*单价*行折扣）
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
     * 行优惠（整单优惠分摊到行的金额）
     * @return line_discount_amount 行优惠（整单优惠分摊到行的金额）
     */
    public BigDecimal getLineDiscountAmount() {
        return lineDiscountAmount;
    }

    /**
     * 行优惠（整单优惠分摊到行的金额）
     * @param lineDiscountAmount 行优惠（整单优惠分摊到行的金额）
     */
    public void setLineDiscountAmount(BigDecimal lineDiscountAmount) {
        this.lineDiscountAmount = lineDiscountAmount;
    }

    /**
     * 实际单价（实际金额/数量）
     * @return real_price 实际单价（实际金额/数量）
     */
    public BigDecimal getRealPrice() {
        return realPrice;
    }

    /**
     * 实际单价（实际金额/数量）
     * @param realPrice 实际单价（实际金额/数量）
     */
    public void setRealPrice(BigDecimal realPrice) {
        this.realPrice = realPrice;
    }

    /**
     * 实际金额（实际单价*数量）
     * @return real_amount 实际金额（实际单价*数量）
     */
    public BigDecimal getRealAmount() {
        return realAmount;
    }

    /**
     * 实际金额（实际单价*数量）
     * @param realAmount 实际金额（实际单价*数量）
     */
    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }

    /**
     * 税率
     * @return tax_rate 税率
     */
    public BigDecimal getTaxRate() {
        return taxRate;
    }

    /**
     * 税率
     * @param taxRate 税率
     */
    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    /**
     * 不含税单价
     * @return notax_real_price 不含税单价
     */
    public BigDecimal getNotaxRealPrice() {
        return notaxRealPrice;
    }

    /**
     * 不含税单价
     * @param notaxRealPrice 不含税单价
     */
    public void setNotaxRealPrice(BigDecimal notaxRealPrice) {
        this.notaxRealPrice = notaxRealPrice;
    }

    /**
     * 不含税金额
     * @return notax_real_amount 不含税金额
     */
    public BigDecimal getNotaxRealAmount() {
        return notaxRealAmount;
    }

    /**
     * 不含税金额
     * @param notaxRealAmount 不含税金额
     */
    public void setNotaxRealAmount(BigDecimal notaxRealAmount) {
        this.notaxRealAmount = notaxRealAmount;
    }

    /**
     * 税额
     * @return tax_amount 税额
     */
    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    /**
     * 税额
     * @param taxAmount 税额
     */
    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    /**
     * 成本金额
     * @return cost_amount 成本金额
     */
    public BigDecimal getCostAmount() {
		return costAmount;
	}

    /**
     * 成本金额
     * @param costAmount 成本金额
     */
	public void setCostAmount(BigDecimal costAmount) {
		this.costAmount = costAmount;
	}

	/**
	 * 不含税成本金额
	 * @return notax_cost_amount 不含税成本金额
	 */
	public BigDecimal getNotaxCostAmount() {
		return notaxCostAmount;
	}
	
	/**
	 * 不含税成本金额
	 * @param notaxCostAmount 不含税成本金额
	 */
	public void setNotaxCostAmount(BigDecimal notaxCostAmount) {
		this.notaxCostAmount = notaxCostAmount;
	}

	/**
     * 利润金额
     * @return profit 利润金额
     */
    public BigDecimal getProfit() {
        return profit;
    }

    /**
     * 利润金额
     * @param profit 利润金额
     */
    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    /**
     * 不含税利润金额
     * @return notax_profit 不含税利润金额
     */
    public BigDecimal getNotaxProfit() {
        return notaxProfit;
    }

    /**
     * 不含税利润金额
     * @param notaxProfit 不含税利润金额
     */
    public void setNotaxProfit(BigDecimal notaxProfit) {
        this.notaxProfit = notaxProfit;
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
        sb.append(", inOutId=").append(inOutId);
        sb.append(", enterpriseId=").append(enterpriseId);
        sb.append(", parentId=").append(parentId);
        sb.append(", direction=").append(direction);
        sb.append(", orderId=").append(orderId);
        sb.append(", orderCode=").append(orderCode);
        sb.append(", orderDtlId=").append(orderDtlId);
        sb.append(", orderType=").append(orderType);
        sb.append(", orderDate=").append(orderDate);
        sb.append(", lineNum=").append(lineNum);
        sb.append(", companyId=").append(companyId);
        sb.append(", companyCode=").append(companyCode);
        sb.append(", companyName=").append(companyName);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", goodsCode=").append(goodsCode);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", lotId=").append(lotId);
        sb.append(", lotNum=").append(lotNum);
        sb.append(", shelfId=").append(shelfId);
        sb.append(", shelfName=").append(shelfName);
        sb.append(", quantity=").append(quantity);
        sb.append(", storageQuantity=").append(storageQuantity);
        sb.append(", unitPrice=").append(unitPrice);
        sb.append(", lineDiscount=").append(lineDiscount);
        sb.append(", amount=").append(amount);
        sb.append(", wholeDiscount=").append(wholeDiscount);
        sb.append(", lineDiscountAmount=").append(lineDiscountAmount);
        sb.append(", realPrice=").append(realPrice);
        sb.append(", realAmount=").append(realAmount);
        sb.append(", taxRate=").append(taxRate);
        sb.append(", notaxRealPrice=").append(notaxRealPrice);
        sb.append(", notaxRealAmount=").append(notaxRealAmount);
        sb.append(", taxAmount=").append(taxAmount);
        sb.append(", costAmount=").append(costAmount);
        sb.append(", notaxCostAmount=").append(notaxCostAmount);
        sb.append(", profit=").append(profit);
        sb.append(", notaxProfit=").append(notaxProfit);
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