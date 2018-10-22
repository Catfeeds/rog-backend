package com.rograndec.feijiayun.chain.business.storage.lot.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_lot_adjust
 * 
 * 
 * @author kexinhao
 * 
 * 2017-09-28
 */
public class LotAdjust implements Serializable {
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
     * 调整人员ID
     */
    @ApiModelProperty(value = "调整人员ID")
    private Long adjustManId;

    /**
     * 调整人员编码
     */
    @ApiModelProperty(value = "调整人员编码")
    private String adjustManCode;

    /**
     * 调整人员名称
     */
    @ApiModelProperty(value = "调整人员名称")
    private String adjustManName;

    /**
     * 调整原因
     */
    @ApiModelProperty(value = "调整原因")
    private String adjustReason;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
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
    @ApiModelProperty(value = "数量合计")
    private BigDecimal quantityTotal;
    @ApiModelProperty(value = "品种数量")
    private Integer varietiesQuantity;
    @ApiModelProperty(value = "金额合计")
    private BigDecimal amountTotal;
    @ApiModelProperty(value = "不含税金额合计")
    private BigDecimal notaxAmountTotal;
    @ApiModelProperty(value = "税额合计")
    private BigDecimal taxAmountTotal;
    /**
     * saas_lot_adjust
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
     * 调整人员ID
     * @return adjust_man_id 调整人员ID
     */
    public Long getAdjustManId() {
        return adjustManId;
    }

    /**
     * 调整人员ID
     * @param adjustManId 调整人员ID
     */
    public void setAdjustManId(Long adjustManId) {
        this.adjustManId = adjustManId;
    }

    /**
     * 调整人员编码
     * @return adjust_man_code 调整人员编码
     */
    public String getAdjustManCode() {
        return adjustManCode;
    }

    /**
     * 调整人员编码
     * @param adjustManCode 调整人员编码
     */
    public void setAdjustManCode(String adjustManCode) {
        this.adjustManCode = adjustManCode == null ? null : adjustManCode.trim();
    }

    /**
     * 调整人员名称
     * @return adjust_man_name 调整人员名称
     */
    public String getAdjustManName() {
        return adjustManName;
    }

    /**
     * 调整人员名称
     * @param adjustManName 调整人员名称
     */
    public void setAdjustManName(String adjustManName) {
        this.adjustManName = adjustManName == null ? null : adjustManName.trim();
    }

    /**
     * 调整原因
     * @return adjust_reason 调整原因
     */
    public String getAdjustReason() {
        return adjustReason;
    }

    /**
     * 调整原因
     * @param adjustReason 调整原因
     */
    public void setAdjustReason(String adjustReason) {
        this.adjustReason = adjustReason == null ? null : adjustReason.trim();
    }

    /**
     * 状态
     * @return status 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态
     * @param status 状态
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

	public BigDecimal getNotaxAmountTotal() {
		return notaxAmountTotal;
	}

	public void setNotaxAmountTotal(BigDecimal notaxAmountTotal) {
		this.notaxAmountTotal = notaxAmountTotal;
	}

	public BigDecimal getTaxAmountTotal() {
		return taxAmountTotal;
	}

	public void setTaxAmountTotal(BigDecimal taxAmountTotal) {
		this.taxAmountTotal = taxAmountTotal;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
        sb.append(", adjustManId=").append(adjustManId);
        sb.append(", adjustManCode=").append(adjustManCode);
        sb.append(", adjustManName=").append(adjustManName);
        sb.append(", adjustReason=").append(adjustReason);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", createrId=").append(createrId);
        sb.append(", createrCode=").append(createrCode);
        sb.append(", createrName=").append(createrName);
        sb.append(", createTime=").append(createTime);
        sb.append(", quantityTotal=").append(quantityTotal);
        sb.append(", varietiesQuantity=").append(varietiesQuantity);
        sb.append(", amountTotal=").append(amountTotal);
        sb.append(", notaxAmountTotal=").append(notaxAmountTotal);
        sb.append(", taxAmountTotal=").append(taxAmountTotal);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}