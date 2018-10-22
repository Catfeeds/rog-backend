package com.rograndec.feijiayun.chain.business.member.storedamount.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_stored_amount_record
 * 
 * 
 * @author dudy
 * 
 * 2017-09-19
 */
public class StoredAmount implements Serializable {
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
     * 变动类型（0-充值；1-扣款；2-转账）
     */
    @ApiModelProperty(value = "变动类型（0-充值；1-扣款；2-转账 3-转入 4-开卡）")
    private Integer changeType;
    
    /**
     * 基础单据id
     */
    @ApiModelProperty(value = "基础单据id")
    private Long baseOrderId;

    /**
     * 基础单据类型
     */
    @ApiModelProperty(value = "基础单据类型")
    private Integer baseOrderType;
    
    /**
     * 基础单据编码
     */
    @ApiModelProperty(value = "基础单据编码")
    private String baseOrderCode;
    
    /**
     * 基础单据日期
     */
    @ApiModelProperty(value = "基础单据日期")
    private Date baseOrderDate;

    /**
     * 会员ID
     */
    @ApiModelProperty(value = "会员ID")
    private Long memberId;

    /**
     * 原会员卡号
     */
    @ApiModelProperty(value = "原会员卡号")
    private String cardCode;

    /**
     * 新会员ID
     */
    @ApiModelProperty(value = "新会员ID")
    private Long transferMemberId;

    /**
     * 新会员卡号
     */
    @ApiModelProperty(value = "新会员卡号")
    private String transferCardCode;

    /**
     * 原累积储值
     */
    @ApiModelProperty(value = "原累积储值")
    private BigDecimal totalStoredAmount;

    /**
     * 原当前储值
     */
    @ApiModelProperty(value = "原当前储值")
    private BigDecimal currentStoredAmount;

    /**
     * 变动储值
     */
    @ApiModelProperty(value = "变动储值")
    private BigDecimal changeStoredAmount;

    /**
     * 新累积储值
     */
    @ApiModelProperty(value = "新累积储值")
    private BigDecimal newTotalStoredAmount;

    /**
     * 新当前储值
     */
    @ApiModelProperty(value = "新当前储值")
    private BigDecimal newCurrentStoredAmount;

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
     * saas_stored_amount_record
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
     * 变动类型（0-充值；1-扣款；2-转账）
     * @return change_type 变动类型（0-充值；1-扣款；2-转账）
     */
    public Integer getChangeType() {
        return changeType;
    }

    /**
     * 变动类型（0-充值；1-扣款；2-转账）
     * @param changeType 变动类型（0-充值；1-扣款；2-转账）
     */
    public void setChangeType(Integer changeType) {
        this.changeType = changeType;
    }

    /**
     * 会员ID
     * @return member_id 会员ID
     */
    public Long getMemberId() {
        return memberId;
    }

    /**
     * 会员ID
     * @param memberId 会员ID
     */
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    /**
     * 原会员卡号
     * @return card_code 原会员卡号
     */
    public String getCardCode() {
        return cardCode;
    }

    /**
     * 原会员卡号
     * @param cardCode 原会员卡号
     */
    public void setCardCode(String cardCode) {
        this.cardCode = cardCode == null ? null : cardCode.trim();
    }

    public Long getTransferMemberId() {
        return transferMemberId;
    }

    public void setTransferMemberId(Long transferMemberId) {
        this.transferMemberId = transferMemberId;
    }

    public String getTransferCardCode() {
        return transferCardCode;
    }

    public void setTransferCardCode(String transferCardCode) {
        this.transferCardCode = transferCardCode;
    }

    /**
     * 原累积储值
     * @return total_stored_amount 原累积储值
     */
    public BigDecimal getTotalStoredAmount() {
        return totalStoredAmount;
    }

    /**
     * 原累积储值
     * @param totalStoredAmount 原累积储值
     */
    public void setTotalStoredAmount(BigDecimal totalStoredAmount) {
        this.totalStoredAmount = totalStoredAmount;
    }

    /**
     * 原当前储值
     * @return current_stored_amount 原当前储值
     */
    public BigDecimal getCurrentStoredAmount() {
        return currentStoredAmount;
    }

    /**
     * 原当前储值
     * @param currentStoredAmount 原当前储值
     */
    public void setCurrentStoredAmount(BigDecimal currentStoredAmount) {
        this.currentStoredAmount = currentStoredAmount;
    }

    /**
     * 变动储值
     * @return change_stored_amount 变动储值
     */
    public BigDecimal getChangeStoredAmount() {
        return changeStoredAmount;
    }

    /**
     * 变动储值
     * @param changeStoredAmount 变动储值
     */
    public void setChangeStoredAmount(BigDecimal changeStoredAmount) {
        this.changeStoredAmount = changeStoredAmount;
    }

    /**
     * 新累积储值
     * @return new_total_stored_amount 新累积储值
     */
    public BigDecimal getNewTotalStoredAmount() {
        return newTotalStoredAmount;
    }

    /**
     * 新累积储值
     * @param newTotalStoredAmount 新累积储值
     */
    public void setNewTotalStoredAmount(BigDecimal newTotalStoredAmount) {
        this.newTotalStoredAmount = newTotalStoredAmount;
    }

    /**
     * 新当前储值
     * @return new_current_stored_amount 新当前储值
     */
    public BigDecimal getNewCurrentStoredAmount() {
        return newCurrentStoredAmount;
    }

    /**
     * 新当前储值
     * @param newCurrentStoredAmount 新当前储值
     */
    public void setNewCurrentStoredAmount(BigDecimal newCurrentStoredAmount) {
        this.newCurrentStoredAmount = newCurrentStoredAmount;
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
    
    public Long getBaseOrderId() {
		return baseOrderId;
	}

	public void setBaseOrderId(Long baseOrderId) {
		this.baseOrderId = baseOrderId;
	}

	public Integer getBaseOrderType() {
		return baseOrderType;
	}

	public void setBaseOrderType(Integer baseOrderType) {
		this.baseOrderType = baseOrderType;
	}

	public String getBaseOrderCode() {
		return baseOrderCode;
	}

	public void setBaseOrderCode(String baseOrderCode) {
		this.baseOrderCode = baseOrderCode;
	}

	public Date getBaseOrderDate() {
		return baseOrderDate;
	}

	public void setBaseOrderDate(Date baseOrderDate) {
		this.baseOrderDate = baseOrderDate;
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
        sb.append(", changeType=").append(changeType);
        sb.append(", memberId=").append(memberId);
        sb.append(", cardCode=").append(cardCode);
        sb.append(", newMemberId=").append(transferMemberId);
        sb.append(", newCardCode=").append(transferCardCode);
        sb.append(", totalStoredAmount=").append(totalStoredAmount);
        sb.append(", currentStoredAmount=").append(currentStoredAmount);
        sb.append(", changeStoredAmount=").append(changeStoredAmount);
        sb.append(", newTotalStoredAmount=").append(newTotalStoredAmount);
        sb.append(", newCurrentStoredAmount=").append(newCurrentStoredAmount);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", createrId=").append(createrId);
        sb.append(", createrCode=").append(createrCode);
        sb.append(", createrName=").append(createrName);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}