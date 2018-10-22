package com.rograndec.feijiayun.chain.business.storage.lot.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_lot_adjust_detail
 * 
 * 
 * @author kexinhao
 * 
 * 2017-09-28
 */
public class LotAdjustDetail implements Serializable {
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
     * 单据类型（5321）
     */
    @ApiModelProperty(value = "单据类型（5321）")
    private Integer orderType;

    /**
     * 调整日期
     */
    @ApiModelProperty(value = "调整日期")
    private Date adjustDate;

    /**
     * 调整单号
     */
    @ApiModelProperty(value = "调整单号")
    private String code;

    /**
     * 单据（批号调整单）ID
     */
    @ApiModelProperty(value = "单据（批号调整单）ID")
    private Long adjustId;

    /**
     * 单据（批号调整单）编码
     */
    @ApiModelProperty(value = "单据（批号调整单）编码")
    private String adjustCode;

    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID")
    private Long goodsId;

    /**
     * 商品编码
     */
    @ApiModelProperty(value = "商品编码")
    private String goodsCode;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    /**
     * 批号ID
     */
    @ApiModelProperty(value = "批号ID")
    private Long lotId;
    
    private Long newLotId;
    /**
     * 批号
     */
    @ApiModelProperty(value = "批号")
    private String lotNum;

    /**
     * 生产日期
     */
    @ApiModelProperty(value = "生产日期")
    private Date productDate;

    /**
     * 有效期至
     */
    @ApiModelProperty(value = "有效期至")
    private Date validUntil;

    /**
     * 新批号
     */
    @ApiModelProperty(value = "新批号")
    private String newLotNum;

    /**
     * 新生产日期
     */
    @ApiModelProperty(value = "新生产日期")
    private Date newProductDate;

    /**
     * 新有效期至
     */
    @ApiModelProperty(value = "新有效期至")
    private Date newValidUntil;

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
     * 新增字段
     * */
    @ApiModelProperty(value = "数量")
    private BigDecimal quantity;
    
    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;
    
    @ApiModelProperty(value = "金额")
    private BigDecimal amount;
    
    @ApiModelProperty(value = "税率ID")
    private Long taxRateId;
    
    @ApiModelProperty(value = "税率")
    private BigDecimal taxRate;
    
    @ApiModelProperty(value = "不含税单价")
    private BigDecimal notaxPrice;
    
    @ApiModelProperty(value = "不含税金额")
    private BigDecimal notaxAmount;
    
    @ApiModelProperty(value = "税额")
    private BigDecimal taxAmount;

    /**
     * saas_lot_adjust_detail
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
     * 单据类型（5321）
     * @return order_type 单据类型（5321）
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 单据类型（5321）
     * @param orderType 单据类型（5321）
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 调整日期
     * @return adjust_date 调整日期
     */
    public Date getAdjustDate() {
        return adjustDate;
    }

    /**
     * 调整日期
     * @param adjustDate 调整日期
     */
    public void setAdjustDate(Date adjustDate) {
        this.adjustDate = adjustDate;
    }

    /**
     * 调整单号
     * @return code 调整单号
     */
    public String getCode() {
        return code;
    }

    /**
     * 调整单号
     * @param code 调整单号
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 单据（批号调整单）ID
     * @return adjust_id 单据（批号调整单）ID
     */
    public Long getAdjustId() {
        return adjustId;
    }

    /**
     * 单据（批号调整单）ID
     * @param adjustId 单据（批号调整单）ID
     */
    public void setAdjustId(Long adjustId) {
        this.adjustId = adjustId;
    }

    /**
     * 单据（批号调整单）编码
     * @return adjust_code 单据（批号调整单）编码
     */
    public String getAdjustCode() {
        return adjustCode;
    }

    /**
     * 单据（批号调整单）编码
     * @param adjustCode 单据（批号调整单）编码
     */
    public void setAdjustCode(String adjustCode) {
        this.adjustCode = adjustCode == null ? null : adjustCode.trim();
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
     * 生产日期
     * @return product_date 生产日期
     */
    public Date getProductDate() {
        return productDate;
    }

    /**
     * 生产日期
     * @param productDate 生产日期
     */
    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    /**
     * 有效期至
     * @return valid_until 有效期至
     */
    public Date getValidUntil() {
        return validUntil;
    }

    /**
     * 有效期至
     * @param validUntil 有效期至
     */
    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }


    /**
     * 新批号
     * @return new_lot_num 新批号
     */
    public String getNewLotNum() {
        return newLotNum;
    }

    /**
     * 新批号
     * @param newLotNum 新批号
     */
    public void setNewLotNum(String newLotNum) {
        this.newLotNum = newLotNum == null ? null : newLotNum.trim();
    }

    /**
     * 新生产日期
     * @return new_product_date 新生产日期
     */
    public Date getNewProductDate() {
        return newProductDate;
    }

    /**
     * 新生产日期
     * @param newProductDate 新生产日期
     */
    public void setNewProductDate(Date newProductDate) {
        this.newProductDate = newProductDate;
    }

    /**
     * 新有效期至
     * @return new_valid_until 新有效期至
     */
    public Date getNewValidUntil() {
        return newValidUntil;
    }

    /**
     * 新有效期至
     * @param newValidUntil 新有效期至
     */
    public void setNewValidUntil(Date newValidUntil) {
        this.newValidUntil = newValidUntil;
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

    public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Long getTaxRateId() {
		return taxRateId;
	}

	public void setTaxRateId(Long taxRateId) {
		this.taxRateId = taxRateId;
	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public BigDecimal getNotaxPrice() {
		return notaxPrice;
	}

	public void setNotaxPrice(BigDecimal notaxPrice) {
		this.notaxPrice = notaxPrice;
	}

	public BigDecimal getNotaxAmount() {
		return notaxAmount;
	}

	public void setNotaxAmount(BigDecimal notaxAmount) {
		this.notaxAmount = notaxAmount;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getNewLotId() {
		return newLotId;
	}

	public void setNewLotId(Long newLotId) {
		this.newLotId = newLotId;
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
        sb.append(", orderType=").append(orderType);
        sb.append(", adjustDate=").append(adjustDate);
        sb.append(", code=").append(code);
        sb.append(", adjustId=").append(adjustId);
        sb.append(", adjustCode=").append(adjustCode);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", goodsCode=").append(goodsCode);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", lotId=").append(lotId);
        sb.append(", lotNum=").append(lotNum);
        sb.append(", productDate=").append(productDate);
        sb.append(", validUntil=").append(validUntil);
        sb.append(", newLotNum=").append(newLotNum);
        sb.append(", newProductDate=").append(newProductDate);
        sb.append(", newValidUntil=").append(newValidUntil);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", createrId=").append(createrId);
        sb.append(", createrCode=").append(createrCode);
        sb.append(", createrName=").append(createrName);
        sb.append(", createTime=").append(createTime);
        sb.append(", quantity=").append(quantity);
        sb.append(", unitPrice=").append(unitPrice);
        sb.append(", amount=").append(amount);
        sb.append(", taxRateId=").append(taxRateId);
        sb.append(", taxRate=").append(taxRate);
        sb.append(", notaxPrice=").append(notaxPrice);
        sb.append(", notaxAmount=").append(notaxAmount);
        sb.append(", taxAmount=").append(taxAmount);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}