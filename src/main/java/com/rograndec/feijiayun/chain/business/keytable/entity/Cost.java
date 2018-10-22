package com.rograndec.feijiayun.chain.business.keytable.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Cost implements Serializable {
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
     * 业务单据ID
     */
    private Long orderId;

    /**
     * 业务单据编码
     */
    private String orderCode;

    /**
     * 业务单据类型
     */
    private Integer orderType;

    /**
     * 业务单据日期O
     */
    private Date orderDate;

    /**
     * 业务单据明细ID
     */
    private Long orderDtlId;

    /**
     * 业务单据明细行号
     */
    private Integer lineNum;

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
     * 批次ID
     */
    private Long batchId;
    
    /**
     * 内部批次号
     */
    private String batchNum;

    /**
     * 来源单位ID
     */
    private Long srcUnitId;

    /**
     * 来源单位编码
     */
    private String srcUnitCode;

    /**
     * 来源单位名称
     */
    private String srcUnitName;

    /**
     * 数量
     */
    private BigDecimal quantity;

    /**
     * 可用数量
     */
    private BigDecimal usableQuantity;

    /**
     * 实际单价
     */
    private BigDecimal realPrice;

    /**
     * 实际金额
     */
    private BigDecimal realAmount;

    /**
     * 可用实际金额
     */
    private BigDecimal usableRealAmout;
    
    /**
     * 税率
     */
    private BigDecimal taxRate;

    /**
     * 不含税实际单价
     */
    private BigDecimal notaxRealPrice;

    /**
     * 不含税实际金额
     */
    private BigDecimal notaxRealAmount;

    /**
     * 不含税实际可用金额
     */
    private BigDecimal notaxUsableRealAmout;

    /**
     * 税额
     */
    private BigDecimal taxAmount;

    /**
     * 可用税额
     */
    private BigDecimal usableTaxAmount;

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
     * 暂存出库明细单成本出库数量
     */
    private BigDecimal tmpOutQuantity;

    /**
     * saas_cost
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
     * 业务单据类型
     * @return order_type 业务单据类型
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 业务单据类型
     * @param orderType 业务单据类型
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 业务单据日期
     * @return order_date 业务单据日期
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * 业务单据日期
     * @param orderDate 业务单据日期
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
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
     * 业务单据明细行号
     * @return line_num 业务单据明细行号
     */
    public Integer getLineNum() {
        return lineNum;
    }

    /**
     * 业务单据明细行号
     * @param lineNum 业务单据明细行号
     */
    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
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
     * @param lotId 批号ID
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
     * 批次ID
     * @return batch_id 批次ID
     */
    public Long getBatchId() {
		return batchId;
	}
    
    /**
     * 内部批次ID
     * @param batchId 内部批次ID
     */
    public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}

    /**
     * 内部批次号
     * @return batch_num 内部批次号
     */
    public String getBatchNum() {
        return batchNum;
    }

    /**
     * 内部批次号
     * @param batchNum 内部批次号
     */
    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum == null ? null : batchNum.trim();
    }

    /**
     * 来源单位ID
     * @return src_unit_id 来源单位ID
     */
    public Long getSrcUnitId() {
        return srcUnitId;
    }

    /**
     * 来源单位ID
     * @param srcUnitId 来源单位ID
     */
    public void setSrcUnitId(Long srcUnitId) {
        this.srcUnitId = srcUnitId;
    }

    /**
     * 来源单位编码
     * @return src_unit_code 来源单位编码
     */
    public String getSrcUnitCode() {
        return srcUnitCode;
    }

    /**
     * 来源单位编码
     * @param srcUnitCode 来源单位编码
     */
    public void setSrcUnitCode(String srcUnitCode) {
        this.srcUnitCode = srcUnitCode == null ? null : srcUnitCode.trim();
    }

    /**
     * 来源单位名称
     * @return src_unit_name 来源单位名称
     */
    public String getSrcUnitName() {
        return srcUnitName;
    }

    /**
     * 来源单位名称
     * @param srcUnitName 来源单位名称
     */
    public void setSrcUnitName(String srcUnitName) {
        this.srcUnitName = srcUnitName == null ? null : srcUnitName.trim();
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
     * 可用数量
     * @return usable_quantity 可用数量
     */
    public BigDecimal getUsableQuantity() {
        return usableQuantity;
    }

    /**
     * 可用数量
     * @param usableQuantity 可用数量
     */
    public void setUsableQuantity(BigDecimal usableQuantity) {
        this.usableQuantity = usableQuantity;
    }

    /**
     * 实际单价
     * @return real_price 实际单价
     */
    public BigDecimal getRealPrice() {
        return realPrice;
    }

    /**
     * 实际单价
     * @param realPrice 实际单价
     */
    public void setRealPrice(BigDecimal realPrice) {
        this.realPrice = realPrice;
    }

    /**
     * 实际金额
     * @return real_amount 实际金额
     */
    public BigDecimal getRealAmount() {
        return realAmount;
    }

    /**
     * 实际金额
     * @param realAmount 实际金额
     */
    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }

    /**
     * 可用实际金额
     * @return usable_real_amout 可用实际金额
     */
    public BigDecimal getUsableRealAmout() {
        return usableRealAmout;
    }

    /**
     * 可用实际金额
     * @param usableRealAmout 可用实际金额
     */
    public void setUsableRealAmout(BigDecimal usableRealAmout) {
        this.usableRealAmout = usableRealAmout;
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
     * 不含税实际单价
     * @return notax_real_price 不含税实际单价
     */
    public BigDecimal getNotaxRealPrice() {
        return notaxRealPrice;
    }

    /**
     * 不含税实际单价
     * @param notaxRealPrice 不含税实际单价
     */
    public void setNotaxRealPrice(BigDecimal notaxRealPrice) {
        this.notaxRealPrice = notaxRealPrice;
    }

    /**
     * 不含税实际金额
     * @return notax_real_amount 不含税实际金额
     */
    public BigDecimal getNotaxRealAmount() {
        return notaxRealAmount;
    }

    /**
     * 不含税实际金额
     * @param notaxRealAmount 不含税实际金额
     */
    public void setNotaxRealAmount(BigDecimal notaxRealAmount) {
        this.notaxRealAmount = notaxRealAmount;
    }

    /**
     * 不含税实际可用金额
     * @return notax_usable_real_amout 不含税实际可用金额
     */
    public BigDecimal getNotaxUsableRealAmout() {
        return notaxUsableRealAmout;
    }

    /**
     * 不含税实际可用金额
     * @param notaxUsableRealAmout 不含税实际可用金额
     */
    public void setNotaxUsableRealAmout(BigDecimal notaxUsableRealAmout) {
        this.notaxUsableRealAmout = notaxUsableRealAmout;
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
     * 可用税额
     * @return usable_tax_amount 可用税额
     */
    public BigDecimal getUsableTaxAmount() {
        return usableTaxAmount;
    }

    /**
     * 可用税额
     * @param usableTaxAmount 可用税额
     */
    public void setUsableTaxAmount(BigDecimal usableTaxAmount) {
        this.usableTaxAmount = usableTaxAmount;
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
    
    public BigDecimal getTmpOutQuantity() {
		return tmpOutQuantity;
	}

	public void setTmpOutQuantity(BigDecimal tmpOutQuantity) {
		this.tmpOutQuantity = tmpOutQuantity;
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
        sb.append(", orderId=").append(orderId);
        sb.append(", orderCode=").append(orderCode);
        sb.append(", orderType=").append(orderType);
        sb.append(", orderDate=").append(orderDate);
        sb.append(", orderDtlId=").append(orderDtlId);
        sb.append(", lineNum=").append(lineNum);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", goodsCode=").append(goodsCode);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", lotId=").append(lotId);
        sb.append(", lotNum=").append(lotNum);
        sb.append(", batchId=").append(batchId);
        sb.append(", batchNum=").append(batchNum);
        sb.append(", srcUnitId=").append(srcUnitId);
        sb.append(", srcUnitCode=").append(srcUnitCode);
        sb.append(", srcUnitName=").append(srcUnitName);
        sb.append(", quantity=").append(quantity);
        sb.append(", usableQuantity=").append(usableQuantity);
        sb.append(", realPrice=").append(realPrice);
        sb.append(", realAmount=").append(realAmount);
        sb.append(", usableRealAmout=").append(usableRealAmout);
        sb.append(", taxRate=").append(taxRate);
        sb.append(", notaxRealPrice=").append(notaxRealPrice);
        sb.append(", notaxRealAmount=").append(notaxRealAmount);
        sb.append(", notaxUsableRealAmout=").append(notaxUsableRealAmout);
        sb.append(", taxAmount=").append(taxAmount);
        sb.append(", usableTaxAmount=").append(usableTaxAmount);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cost cost = (Cost) o;

        return id != null ? id.equals(cost.id) : cost.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}