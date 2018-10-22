package com.rograndec.feijiayun.chain.business.member.integralexchange.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_integral_exchange
 * 
 * 
 * @author dudy
 * 
 * 2017-09-23
 */
public class IntegralExchange implements Serializable {
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
     * 积分兑换（6211）
     */
    @ApiModelProperty(value = "积分兑换（6211）")
    private Integer orderType;

    /**
     * 积分兑换单号
     */
    @ApiModelProperty(value = "积分兑换单号")
    private String code;

    /**
     * 兑换日期
     */
    @ApiModelProperty(value = "兑换日期")
    private Date exchangeDate;

    /**
     * 兑换人员ID
     */
    @ApiModelProperty(value = "兑换人员ID")
    private Long exchangeManId;

    /**
     * 兑换人员编码
     */
    @ApiModelProperty(value = "兑换人员编码")
    private String exchangeManCode;

    /**
     * 兑换人员名称
     */
    @ApiModelProperty(value = "兑换人员名称")
    private String exchangeManName;

    /**
     * 会员ID
     */
    @ApiModelProperty(value = "会员ID")
    private Long memberId;

    /**
     * 会员卡号
     */
    @ApiModelProperty(value = "会员卡号")
    private String memberCardCode;

    /**
     * 当前积分
     */
    @ApiModelProperty(value = "当前积分")
    private BigDecimal currentIntegral;

    /**
     * 扣除积分
     */
    @ApiModelProperty(value = "扣除积分")
    private BigDecimal deductIntegral;

    /**
     * 剩余积分
     */
    @ApiModelProperty(value = "剩余积分")
    private BigDecimal remainderIntegral;


    /**
     * 数量合计
     */
    @ApiModelProperty(value = "数量合计")
    private BigDecimal quantityTotal;

    /**
     * 品种数量
     */
    @ApiModelProperty(value = "品种数量")
    private Integer varietiesQuantity;

    /**
     * 金额（合计）
     */
    @ApiModelProperty(value = "金额（合计）")
    private BigDecimal amountTotal;

    /**
     * 无税金额（合计）
     */
    @ApiModelProperty(value = "无税金额（合计）")
    private BigDecimal notaxAmountTotal;

    /**
     * 税额（合计）
     */
    @ApiModelProperty(value = "税额（合计）")
    private BigDecimal taxAmountTotal;

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
     * saas_integral_exchange
     */
    private static final long serialVersionUID = 1L;


    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
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
     * 积分兑换单号
     * @return code 积分兑换单号
     */
    public String getCode() {
        return code;
    }

    /**
     * 积分兑换单号
     * @param code 积分兑换单号
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 兑换日期
     * @return exchange_date 兑换日期
     */
    public Date getExchangeDate() {
        return exchangeDate;
    }

    /**
     * 兑换日期
     * @param exchangeDate 兑换日期
     */
    public void setExchangeDate(Date exchangeDate) {
        this.exchangeDate = exchangeDate;
    }

    /**
     * 兑换人员ID
     * @return exchange_man_id 兑换人员ID
     */
    public Long getExchangeManId() {
        return exchangeManId;
    }

    /**
     * 兑换人员ID
     * @param exchangeManId 兑换人员ID
     */
    public void setExchangeManId(Long exchangeManId) {
        this.exchangeManId = exchangeManId;
    }

    /**
     * 兑换人员编码
     * @return exchange_man_code 兑换人员编码
     */
    public String getExchangeManCode() {
        return exchangeManCode;
    }

    /**
     * 兑换人员编码
     * @param exchangeManCode 兑换人员编码
     */
    public void setExchangeManCode(String exchangeManCode) {
        this.exchangeManCode = exchangeManCode == null ? null : exchangeManCode.trim();
    }

    /**
     * 兑换人员名称
     * @return exchange_man_name 兑换人员名称
     */
    public String getExchangeManName() {
        return exchangeManName;
    }

    /**
     * 兑换人员名称
     * @param exchangeManName 兑换人员名称
     */
    public void setExchangeManName(String exchangeManName) {
        this.exchangeManName = exchangeManName == null ? null : exchangeManName.trim();
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
     * 会员卡号
     * @return member_card_code 会员卡号
     */
    public String getMemberCardCode() {
        return memberCardCode;
    }

    /**
     * 会员卡号
     * @param memberCardCode 会员卡号
     */
    public void setMemberCardCode(String memberCardCode) {
        this.memberCardCode = memberCardCode == null ? null : memberCardCode.trim();
    }

    /**
     * 当前积分
     * @return current_integral 当前积分
     */
    public BigDecimal getCurrentIntegral() {
        return currentIntegral;
    }

    /**
     * 当前积分
     * @param currentIntegral 当前积分
     */
    public void setCurrentIntegral(BigDecimal currentIntegral) {
        this.currentIntegral = currentIntegral;
    }

    /**
     * 扣除积分
     * @return deduct_integral 扣除积分
     */
    public BigDecimal getDeductIntegral() {
        return deductIntegral;
    }

    /**
     * 扣除积分
     * @param deductIntegral 扣除积分
     */
    public void setDeductIntegral(BigDecimal deductIntegral) {
        this.deductIntegral = deductIntegral;
    }

    /**
     * 剩余积分
     * @return remainder_integral 剩余积分
     */
    public BigDecimal getRemainderIntegral() {
        return remainderIntegral;
    }

    /**
     * 剩余积分
     * @param remainderIntegral 剩余积分
     */
    public void setRemainderIntegral(BigDecimal remainderIntegral) {
        this.remainderIntegral = remainderIntegral;
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
     * @mbg.generated
     */
    @Override
    public String toString() {
        return "IntegralExchange{" +
                "id=" + id +
                ", enterpriseId=" + enterpriseId +
                ", parentId=" + parentId +
                ", orderType=" + orderType +
                ", code='" + code + '\'' +
                ", exchangeDate=" + exchangeDate +
                ", exchangeManId=" + exchangeManId +
                ", exchangeManCode='" + exchangeManCode + '\'' +
                ", exchangeManName='" + exchangeManName + '\'' +
                ", memberId=" + memberId +
                ", memberCardCode='" + memberCardCode + '\'' +
                ", currentIntegral=" + currentIntegral +
                ", deductIntegral=" + deductIntegral +
                ", remainderIntegral=" + remainderIntegral +
                ", quantityTotal=" + quantityTotal +
                ", varietiesQuantity=" + varietiesQuantity +
                ", amountTotal=" + amountTotal +
                ", notaxAmountTotal=" + notaxAmountTotal +
                ", taxAmountTotal=" + taxAmountTotal +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                ", createrId=" + createrId +
                ", createrCode='" + createrCode + '\'' +
                ", createrName='" + createrName + '\'' +
                ", createTime=" + createTime +
                ", modifierId=" + modifierId +
                ", modifierCode='" + modifierCode + '\'' +
                ", modifierName='" + modifierName + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}