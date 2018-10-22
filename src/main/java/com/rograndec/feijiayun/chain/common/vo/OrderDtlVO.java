package com.rograndec.feijiayun.chain.common.vo;

import com.rograndec.feijiayun.chain.business.keytable.entity.BatchNumber;
import com.rograndec.feijiayun.chain.business.keytable.entity.Cost;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @ClassName: OrderDtlVO  
 * @Description: 单据明细对象
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年8月26日 上午10:44:07  
 *
 */
public class OrderDtlVO {
	
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
     * 单据类型
     */
    private Integer orderType;

	/**
     * 单据日期
     */
    private Date orderDate;

	/**
	 * 业务单据明细ID
	 */
	private Long orderDtlId;

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
     * 生产日期
     */
    private Date productDate;

	/**
     * 有效期至
     */
    private Date validUntil;

	/**
     * 批准文号
     */
    private String approvalNumber;

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
	 * 临时存储入库内部批次
	 */
	private List<BatchNumber> batchNumberList = new ArrayList<BatchNumber>();

    /**
     * 临时存储出库单出库成本列表
     */
    private List<Cost> outCostList = new ArrayList<Cost>();
    
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

	public Long getOrderDtlId() {
		return orderDtlId;
	}

	public void setOrderDtlId(Long orderDtlId) {
		this.orderDtlId = orderDtlId;
	}

	public Integer getLineNum() {
		return lineNum;
	}

	public void setLineNum(Integer lineNum) {
		this.lineNum = lineNum;
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

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Long getLotId() {
		return lotId;
	}

	public void setLotId(Long lotId) {
		this.lotId = lotId;
	}

	public String getLotNum() {
		return lotNum;
	}

	public void setLotNum(String lotNum) {
		this.lotNum = lotNum;
	}
	
	public Date getProductDate() {
		return productDate;
	}

	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}

	public Date getValidUntil() {
		return validUntil;
	}

	public void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
	}

	public String getApprovalNumber() {
		return approvalNumber;
	}

	public void setApprovalNumber(String approvalNumber) {
		this.approvalNumber = approvalNumber;
	}

	public Long getShelfId() {
		return shelfId;
	}

	public void setShelfId(Long shelfId) {
		this.shelfId = shelfId;
	}

	public String getShelfName() {
		return shelfName;
	}

	public void setShelfName(String shelfName) {
		this.shelfName = shelfName;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getStorageQuantity() {
		return storageQuantity;
	}

	public void setStorageQuantity(BigDecimal storageQuantity) {
		this.storageQuantity = storageQuantity;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getLineDiscount() {
		return lineDiscount;
	}

	public void setLineDiscount(BigDecimal lineDiscount) {
		this.lineDiscount = lineDiscount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getWholeDiscount() {
		return wholeDiscount;
	}

	public void setWholeDiscount(BigDecimal wholeDiscount) {
		this.wholeDiscount = wholeDiscount;
	}

	public BigDecimal getLineDiscountAmount() {
		return lineDiscountAmount;
	}

	public void setLineDiscountAmount(BigDecimal lineDiscountAmount) {
		this.lineDiscountAmount = lineDiscountAmount;
	}

	public BigDecimal getRealPrice() {
		return realPrice;
	}

	public void setRealPrice(BigDecimal realPrice) {
		this.realPrice = realPrice;
	}

	public BigDecimal getRealAmount() {
		return realAmount;
	}

	public void setRealAmount(BigDecimal realAmount) {
		this.realAmount = realAmount;
	}
	
	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public BigDecimal getNotaxRealPrice() {
		return notaxRealPrice;
	}

	public void setNotaxRealPrice(BigDecimal notaxRealPrice) {
		this.notaxRealPrice = notaxRealPrice;
	}

	public BigDecimal getNotaxRealAmount() {
		return notaxRealAmount;
	}

	public void setNotaxRealAmount(BigDecimal notaxRealAmount) {
		this.notaxRealAmount = notaxRealAmount;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public BigDecimal getCostAmount() {
		return costAmount;
	}

	public void setCostAmount(BigDecimal costAmount) {
		this.costAmount = costAmount;
	}

	public BigDecimal getNotaxCostAmount() {
		return notaxCostAmount;
	}

	public void setNotaxCostAmount(BigDecimal notaxCostAmount) {
		this.notaxCostAmount = notaxCostAmount;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public BigDecimal getNotaxProfit() {
		return notaxProfit;
	}

	public void setNotaxProfit(BigDecimal notaxProfit) {
		this.notaxProfit = notaxProfit;
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

	public List<BatchNumber> getBatchNumberList() {
		return batchNumberList;
	}

	public void setBatchNumberList(List<BatchNumber> batchNumberList) {
		this.batchNumberList = batchNumberList;
	}

	public List<Cost> getOutCostList() {
		return outCostList;
	}

	public void setOutCostList(List<Cost> outCostList) {
		this.outCostList = outCostList;
	}

	@Override
	public String toString() {
		return "OrderDtlVO{" +
				"enterpriseId=" + enterpriseId +
				", parentId=" + parentId +
				", direction=" + direction +
				", orderId=" + orderId +
				", orderCode='" + orderCode + '\'' +
				", orderType=" + orderType +
				", orderDate=" + orderDate +
				", orderDtlId=" + orderDtlId +
				", lineNum=" + lineNum +
				", companyId=" + companyId +
				", companyCode='" + companyCode + '\'' +
				", companyName='" + companyName + '\'' +
				", goodsId=" + goodsId +
				", goodsCode='" + goodsCode + '\'' +
				", goodsName='" + goodsName + '\'' +
				", lotId=" + lotId +
				", lotNum='" + lotNum + '\'' +
				", productDate=" + productDate +
				", validUntil=" + validUntil +
				", approvalNumber='" + approvalNumber + '\'' +
				", shelfId=" + shelfId +
				", shelfName='" + shelfName + '\'' +
				", quantity=" + quantity +
				", storageQuantity=" + storageQuantity +
				", unitPrice=" + unitPrice +
				", lineDiscount=" + lineDiscount +
				", amount=" + amount +
				", wholeDiscount=" + wholeDiscount +
				", lineDiscountAmount=" + lineDiscountAmount +
				", realPrice=" + realPrice +
				", realAmount=" + realAmount +
				", taxRate=" + taxRate +
				", notaxRealPrice=" + notaxRealPrice +
				", notaxRealAmount=" + notaxRealAmount +
				", taxAmount=" + taxAmount +
				", costAmount=" + costAmount +
				", notaxCostAmount=" + notaxCostAmount +
				", profit=" + profit +
				", notaxProfit=" + notaxProfit +
				", profitRate=" + profitRate +
				", notaxProfitRate=" + notaxProfitRate +
				", remark='" + remark + '\'' +
				", createrId=" + createrId +
				", createrCode='" + createrCode + '\'' +
				", createrName='" + createrName + '\'' +
				", createTime=" + createTime +
				", modifierId=" + modifierId +
				", modifierCode='" + modifierCode + '\'' +
				", modifierName='" + modifierName + '\'' +
				", updateTime=" + updateTime +
				", batchNumberList=" + batchNumberList +
				", outCostList=" + outCostList +
				'}';
	}
}
