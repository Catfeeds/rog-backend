package com.rograndec.feijiayun.chain.business.keytable.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Profit implements Serializable {
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
     * 出库单据ID
     */
    private Long outOrderId;

    /**
     * 出库单据编码
     */
    private String outOrderCode;

    /**
     * 出库单据类型
     */
    private Integer outOrderType;

    /**
     * 出库单据日期
     */
    private Date outOrderDate;

    /**
     * 出库单据明细ID
     */
    private Long outOrderDtlId;

    /**
     * 出库单据明细行号
     */
    private Integer outLineNum;

    /**
     * 入库单据ID
     */
    private Long inOrderId;

    /**
     * 入库单据编码
     */
    private String inOrderCode;

    /**
     * 入库单据类型
     */
    private Long inOrderDtlId;

    /**
     * 入库单据日期
     */
    private Integer inOrderType;

    /**
     * 入库单据明细ID
     */
    private Date inOrderDate;

    /**
     * 入库单据明细行号
     */
    private Integer inLineNum;

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
     * 流向单位ID
     */
    private Long flowUnitId;

    /**
     * 流向单位编码
     */
    private String flowUnitCode;

    /**
     * 流向单位名称
     */
    private String flowUnitName;

    /**
     * 数量
     */
    private BigDecimal quantity;

    /**
     * 出库实际单价
     */
    private BigDecimal outRealPrice;

    /**
     * 出库实际金额
     */
    private BigDecimal outRealAmount;

    /**
     * 销项税
     */
    private BigDecimal saleTaxRate;

    /**
     * 出库不含税实际单价
     */
    private BigDecimal outNotaxRealPrice;

    /**
     * 出库不含税实际金额
     */
    private BigDecimal outNotaxRealAmount;

    /**
     * 出库税额
     */
    private BigDecimal outTaxAmount;

    /**
     * 入库实际单价
     */
    private BigDecimal inRealPrice;

    /**
     * 入库实际金额
     */
    private BigDecimal inRealAmount;
    
    /**
     * 进项税
     */
    private BigDecimal purchaseTaxRate;

    /**
     * 入库不含税实际单价
     */
    private BigDecimal inNotaxRealPrice;

    /**
     * 入库不含税实际金额
     */
    private BigDecimal inNotaxRealAmount;

    /**
     * 入库税额
     */
    private BigDecimal inTaxAmount;

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
     * saas_profit
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
     * 出库单据ID
     * @return out_order_id 出库单据ID
     */
    public Long getOutOrderId() {
        return outOrderId;
    }

    /**
     * 出库单据ID
     * @param outOrderId 出库单据ID
     */
    public void setOutOrderId(Long outOrderId) {
        this.outOrderId = outOrderId;
    }

    /**
     * 出库单据编码
     * @return out_order_code 出库单据编码
     */
    public String getOutOrderCode() {
        return outOrderCode;
    }

    /**
     * 出库单据编码
     * @param outOrderCode 出库单据编码
     */
    public void setOutOrderCode(String outOrderCode) {
        this.outOrderCode = outOrderCode == null ? null : outOrderCode.trim();
    }

    /**
     * 出库单据类型
     * @return out_order_type 出库单据类型
     */
    public Integer getOutOrderType() {
        return outOrderType;
    }

    /**
     * 出库单据类型
     * @param outOrderType 出库单据类型
     */
    public void setOutOrderType(Integer outOrderType) {
        this.outOrderType = outOrderType;
    }

    /**
     * 出库单据日期
     * @return out_order_date 出库单据日期
     */
    public Date getOutOrderDate() {
        return outOrderDate;
    }

    /**
     * 出库单据日期
     * @param outOrderDate 出库单据日期
     */
    public void setOutOrderDate(Date outOrderDate) {
        this.outOrderDate = outOrderDate;
    }

    /**
     * 出库单据明细ID
     * @return out_order_dtl_id 出库单据明细ID
     */
    public Long getOutOrderDtlId() {
        return outOrderDtlId;
    }

    /**
     * 出库单据明细ID
     * @param outOrderDtlId 出库单据明细ID
     */
    public void setOutOrderDtlId(Long outOrderDtlId) {
        this.outOrderDtlId = outOrderDtlId;
    }

    /**
     * 出库单据明细行号
     * @return out_line_num 出库单据明细行号
     */
    public Integer getOutLineNum() {
        return outLineNum;
    }

    /**
     * 出库单据明细行号
     * @param outLineNum 出库单据明细行号
     */
    public void setOutLineNum(Integer outLineNum) {
        this.outLineNum = outLineNum;
    }

    /**
     * 入库单据ID
     * @return in_order_id 入库单据ID
     */
    public Long getInOrderId() {
        return inOrderId;
    }

    /**
     * 入库单据ID
     * @param inOrderId 入库单据ID
     */
    public void setInOrderId(Long inOrderId) {
        this.inOrderId = inOrderId;
    }

    /**
     * 入库单据编码
     * @return in_order_code 入库单据编码
     */
    public String getInOrderCode() {
        return inOrderCode;
    }

    /**
     * 入库单据编码
     * @param inOrderCode 入库单据编码
     */
    public void setInOrderCode(String inOrderCode) {
        this.inOrderCode = inOrderCode == null ? null : inOrderCode.trim();
    }

    /**
     * 入库单据类型
     * @return in_order_dtl_id 入库单据类型
     */
    public Long getInOrderDtlId() {
        return inOrderDtlId;
    }

    /**
     * 入库单据类型
     * @param inOrderDtlId 入库单据类型
     */
    public void setInOrderDtlId(Long inOrderDtlId) {
        this.inOrderDtlId = inOrderDtlId;
    }

    /**
     * 入库单据日期
     * @return in_order_type 入库单据日期
     */
    public Integer getInOrderType() {
        return inOrderType;
    }

    /**
     * 入库单据日期
     * @param inOrderType 入库单据日期
     */
    public void setInOrderType(Integer inOrderType) {
        this.inOrderType = inOrderType;
    }

    /**
     * 入库单据明细ID
     * @return in_order_date 入库单据明细ID
     */
    public Date getInOrderDate() {
        return inOrderDate;
    }

    /**
     * 入库单据明细ID
     * @param inOrderDate 入库单据明细ID
     */
    public void setInOrderDate(Date inOrderDate) {
        this.inOrderDate = inOrderDate;
    }

    /**
     * 入库单据明细行号
     * @return in_line_num 入库单据明细行号
     */
    public Integer getInLineNum() {
        return inLineNum;
    }

    /**
     * 入库单据明细行号
     * @param inLineNum 入库单据明细行号
     */
    public void setInLineNum(Integer inLineNum) {
        this.inLineNum = inLineNum;
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
     * 内部批次ID
     * @return batch_id 内部批次ID
     */
    public Long getBatchId() {
		return batchId;
	}
    
    /**
     * 内部批次ID
     * @return batch_id 内部批次ID
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
     * 流向单位ID
     * @return flow_unit_id 流向单位ID
     */
    public Long getFlowUnitId() {
        return flowUnitId;
    }

    /**
     * 流向单位ID
     * @param flowUnitId 流向单位ID
     */
    public void setFlowUnitId(Long flowUnitId) {
        this.flowUnitId = flowUnitId;
    }

    /**
     * 流向单位编码
     * @return flow_unit_code 流向单位编码
     */
    public String getFlowUnitCode() {
        return flowUnitCode;
    }

    /**
     * 流向单位编码
     * @param flowUnitCode 流向单位编码
     */
    public void setFlowUnitCode(String flowUnitCode) {
        this.flowUnitCode = flowUnitCode == null ? null : flowUnitCode.trim();
    }

    /**
     * 流向单位名称
     * @return flow_unit_name 流向单位名称
     */
    public String getFlowUnitName() {
        return flowUnitName;
    }

    /**
     * 流向单位名称
     * @param flowUnitName 流向单位名称
     */
    public void setFlowUnitName(String flowUnitName) {
        this.flowUnitName = flowUnitName == null ? null : flowUnitName.trim();
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
     * 出库实际单价
     * @return out_real_price 出库实际单价
     */
    public BigDecimal getOutRealPrice() {
        return outRealPrice;
    }

    /**
     * 出库实际单价
     * @param outRealPrice 出库实际单价
     */
    public void setOutRealPrice(BigDecimal outRealPrice) {
        this.outRealPrice = outRealPrice;
    }

    /**
     * 出库实际金额
     * @return out_real_amount 出库实际金额
     */
    public BigDecimal getOutRealAmount() {
        return outRealAmount;
    }

    /**
     * 出库实际金额
     * @param outRealAmount 出库实际金额
     */
    public void setOutRealAmount(BigDecimal outRealAmount) {
        this.outRealAmount = outRealAmount;
    }

    /**
     * 销项税
     * @return sale_tax_rate 销项税
     */
    public BigDecimal getSaleTaxRate() {
        return saleTaxRate;
    }

    /**
     * 销项税
     * @param saleTaxRate 销项税
     */
    public void setSaleTaxRate(BigDecimal saleTaxRate) {
        this.saleTaxRate = saleTaxRate;
    }

    /**
     * 出库不含税实际单价
     * @return out_notax_real_price 出库不含税实际单价
     */
    public BigDecimal getOutNotaxRealPrice() {
        return outNotaxRealPrice;
    }

    /**
     * 出库不含税实际单价
     * @param outNotaxRealPrice 出库不含税实际单价
     */
    public void setOutNotaxRealPrice(BigDecimal outNotaxRealPrice) {
        this.outNotaxRealPrice = outNotaxRealPrice;
    }

    /**
     * 出库不含税实际金额
     * @return out_notax_real_amount 出库不含税实际金额
     */
    public BigDecimal getOutNotaxRealAmount() {
        return outNotaxRealAmount;
    }

    /**
     * 出库不含税实际金额
     * @param outNotaxRealAmount 出库不含税实际金额
     */
    public void setOutNotaxRealAmount(BigDecimal outNotaxRealAmount) {
        this.outNotaxRealAmount = outNotaxRealAmount;
    }

    /**
     * 出库税额
     * @return out_tax_amount 出库税额
     */
    public BigDecimal getOutTaxAmount() {
        return outTaxAmount;
    }

    /**
     * 出库税额
     * @param outTaxAmount 出库税额
     */
    public void setOutTaxAmount(BigDecimal outTaxAmount) {
        this.outTaxAmount = outTaxAmount;
    }

    /**
     * 入库实际单价
     * @return in_real_price 入库实际单价
     */
    public BigDecimal getInRealPrice() {
        return inRealPrice;
    }

    /**
     * 入库实际单价
     * @param inRealPrice 入库实际单价
     */
    public void setInRealPrice(BigDecimal inRealPrice) {
        this.inRealPrice = inRealPrice;
    }

    /**
     * 入库实际金额
     * @return in_real_amount 入库实际金额
     */
    public BigDecimal getInRealAmount() {
        return inRealAmount;
    }

    /**
     * 入库实际金额
     * @param inRealAmount 入库实际金额
     */
    public void setInRealAmount(BigDecimal inRealAmount) {
        this.inRealAmount = inRealAmount;
    }
    
    /**
     * 进项税
     * @return purchase_tax_rate 进项税
     */
    public BigDecimal getPurchaseTaxRate() {
        return purchaseTaxRate;
    }

    /**
     * 进项税
     * @param purchaseTaxRate 进项税
     */
    public void setPurchaseTaxRate(BigDecimal purchaseTaxRate) {
        this.purchaseTaxRate = purchaseTaxRate;
    }

    /**
     * 入库不含税实际单价
     * @return in_notax_real_price 入库不含税实际单价
     */
    public BigDecimal getInNotaxRealPrice() {
        return inNotaxRealPrice;
    }

    /**
     * 入库不含税实际单价
     * @param inNotaxRealPrice 入库不含税实际单价
     */
    public void setInNotaxRealPrice(BigDecimal inNotaxRealPrice) {
        this.inNotaxRealPrice = inNotaxRealPrice;
    }

    /**
     * 入库不含税实际金额
     * @return in_notax_real_amount 入库不含税实际金额
     */
    public BigDecimal getInNotaxRealAmount() {
        return inNotaxRealAmount;
    }

    /**
     * 入库不含税实际金额
     * @param inNotaxRealAmount 入库不含税实际金额
     */
    public void setInNotaxRealAmount(BigDecimal inNotaxRealAmount) {
        this.inNotaxRealAmount = inNotaxRealAmount;
    }

    /**
     * 入库税额
     * @return in_tax_amount 入库税额
     */
    public BigDecimal getInTaxAmount() {
        return inTaxAmount;
    }

    /**
     * 入库税额
     * @param inTaxAmount 入库税额
     */
    public void setInTaxAmount(BigDecimal inTaxAmount) {
        this.inTaxAmount = inTaxAmount;
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
        sb.append(", enterpriseId=").append(enterpriseId);
        sb.append(", parentId=").append(parentId);
        sb.append(", outOrderId=").append(outOrderId);
        sb.append(", outOrderCode=").append(outOrderCode);
        sb.append(", outOrderType=").append(outOrderType);
        sb.append(", outOrderDate=").append(outOrderDate);
        sb.append(", outOrderDtlId=").append(outOrderDtlId);
        sb.append(", outLineNum=").append(outLineNum);
        sb.append(", inOrderId=").append(inOrderId);
        sb.append(", inOrderCode=").append(inOrderCode);
        sb.append(", inOrderDtlId=").append(inOrderDtlId);
        sb.append(", inOrderType=").append(inOrderType);
        sb.append(", inOrderDate=").append(inOrderDate);
        sb.append(", inLineNum=").append(inLineNum);
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
        sb.append(", flowUnitId=").append(flowUnitId);
        sb.append(", flowUnitCode=").append(flowUnitCode);
        sb.append(", flowUnitName=").append(flowUnitName);
        sb.append(", quantity=").append(quantity);
        sb.append(", outRealPrice=").append(outRealPrice);
        sb.append(", outRealAmount=").append(outRealAmount);
        sb.append(", saleTaxRate=").append(saleTaxRate);
        sb.append(", outNotaxRealPrice=").append(outNotaxRealPrice);
        sb.append(", outNotaxRealAmount=").append(outNotaxRealAmount);
        sb.append(", outTaxAmount=").append(outTaxAmount);
        sb.append(", inRealPrice=").append(inRealPrice);
        sb.append(", inRealAmount=").append(inRealAmount);
        sb.append(", purchaseTaxRate=").append(purchaseTaxRate);
        sb.append(", inNotaxRealPrice=").append(inNotaxRealPrice);
        sb.append(", inNotaxRealAmount=").append(inNotaxRealAmount);
        sb.append(", inTaxAmount=").append(inTaxAmount);
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