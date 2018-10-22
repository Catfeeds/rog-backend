package com.rograndec.feijiayun.chain.business.finance.commission.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * saas_sale_commission
 * 
 * 
 * @author lizhongyi
 * 
 * 2018-01-05
 */
public class SaleCommissionSaveVO implements Serializable {
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
     * 单据类型（6243）
     */
    @ApiModelProperty(value = "单据类型（6243）")
    private Integer orderType;

    /**
     * 单据编码
     */
    @ApiModelProperty(value = "单据编码")
    private String code;

    /**
     * 提成日期
     */
    @ApiModelProperty(value = "提成日期")
    private Date commissionDate;

    /**
     * 提成人员ID
     */
    @ApiModelProperty(value = "提成人员ID")
    private Long commissionManId;

    /**
     * 提成人员编码
     */
    @ApiModelProperty(value = "提成人员编码")
    private String commissionManCode;

    /**
     * 提成人员名称
     */
    @ApiModelProperty(value = "提成人员名称")
    private String commissionManName;

    /**
     * 营业人员ID
     */
    @ApiModelProperty(value = "营业人员ID")
    private Long clerkId;

    /**
     * 营业人员编码
     */
    @ApiModelProperty(value = "营业人员编码")
    private String clerkCode;

    /**
     * 营业人员名称
     */
    @ApiModelProperty(value = "营业人员名称")
    private String clerkName;

    /**
     * 销售日期从
     */
    @ApiModelProperty(value = "销售日期从")
    private Date saleDateFrom;

    /**
     * 销售日期至
     */
    @ApiModelProperty(value = "销售日期至")
    private Date saleDateTo;

    /**
     * 应提金额合计
     */
    @ApiModelProperty(value = "应提金额合计")
    private BigDecimal amountTotal;

    /**
     * 实提金额合计
     */
    @ApiModelProperty(value = "实提金额合计")
    private BigDecimal realAmountTotal;

    /**
     * 差异金额合计
     */
    @ApiModelProperty(value = "差异金额合计")
    private BigDecimal diffAmountTotal;

    /**
     * 状态（1-已完成；2-已冲销）
     */
    @ApiModelProperty(value = "状态（1-已完成；2-已冲销）")
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
     * saas_sale_commission
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
     * 单据类型（6243）
     * @return order_type 单据类型（6243）
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 单据类型（6243）
     * @param orderType 单据类型（6243）
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 单据编码
     * @return code 单据编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 单据编码
     * @param code 单据编码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 提成日期
     * @return commission_date 提成日期
     */
    public Date getCommissionDate() {
        return commissionDate;
    }

    /**
     * 提成日期
     * @param commissionDate 提成日期
     */
    public void setCommissionDate(Date commissionDate) {
        this.commissionDate = commissionDate;
    }

    /**
     * 提成人员ID
     * @return commission_man_id 提成人员ID
     */
    public Long getCommissionManId() {
        return commissionManId;
    }

    /**
     * 提成人员ID
     * @param commissionManId 提成人员ID
     */
    public void setCommissionManId(Long commissionManId) {
        this.commissionManId = commissionManId;
    }

    /**
     * 提成人员编码
     * @return commission_man_code 提成人员编码
     */
    public String getCommissionManCode() {
        return commissionManCode;
    }

    /**
     * 提成人员编码
     * @param commissionManCode 提成人员编码
     */
    public void setCommissionManCode(String commissionManCode) {
        this.commissionManCode = commissionManCode == null ? null : commissionManCode.trim();
    }

    /**
     * 提成人员名称
     * @return commission_man_name 提成人员名称
     */
    public String getCommissionManName() {
        return commissionManName;
    }

    /**
     * 提成人员名称
     * @param commissionManName 提成人员名称
     */
    public void setCommissionManName(String commissionManName) {
        this.commissionManName = commissionManName == null ? null : commissionManName.trim();
    }

    /**
     * 营业人员ID
     * @return clerk_id 营业人员ID
     */
    public Long getClerkId() {
        return clerkId;
    }

    /**
     * 营业人员ID
     * @param clerkId 营业人员ID
     */
    public void setClerkId(Long clerkId) {
        this.clerkId = clerkId;
    }

    /**
     * 营业人员编码
     * @return clerk_code 营业人员编码
     */
    public String getClerkCode() {
        return clerkCode;
    }

    /**
     * 营业人员编码
     * @param clerkCode 营业人员编码
     */
    public void setClerkCode(String clerkCode) {
        this.clerkCode = clerkCode == null ? null : clerkCode.trim();
    }

    /**
     * 营业人员名称
     * @return clerk_name 营业人员名称
     */
    public String getClerkName() {
        return clerkName;
    }

    /**
     * 营业人员名称
     * @param clerkName 营业人员名称
     */
    public void setClerkName(String clerkName) {
        this.clerkName = clerkName == null ? null : clerkName.trim();
    }

    /**
     * 销售日期从
     * @return sale_date_from 销售日期从
     */
    public Date getSaleDateFrom() {
        return saleDateFrom;
    }

    /**
     * 销售日期从
     * @param saleDateFrom 销售日期从
     */
    public void setSaleDateFrom(Date saleDateFrom) {
        this.saleDateFrom = saleDateFrom;
    }

    /**
     * 销售日期至
     * @return sale_date_to 销售日期至
     */
    public Date getSaleDateTo() {
        return saleDateTo;
    }

    /**
     * 销售日期至
     * @param saleDateTo 销售日期至
     */
    public void setSaleDateTo(Date saleDateTo) {
        this.saleDateTo = saleDateTo;
    }

    /**
     * 应提金额合计
     * @return amount_total 应提金额合计
     */
    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    /**
     * 应提金额合计
     * @param amountTotal 应提金额合计
     */
    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    /**
     * 实提金额合计
     * @return real_amount_total 实提金额合计
     */
    public BigDecimal getRealAmountTotal() {
        return realAmountTotal;
    }

    /**
     * 实提金额合计
     * @param realAmountTotal 实提金额合计
     */
    public void setRealAmountTotal(BigDecimal realAmountTotal) {
        this.realAmountTotal = realAmountTotal;
    }

    /**
     * 差异金额合计
     * @return diff_amount_total 差异金额合计
     */
    public BigDecimal getDiffAmountTotal() {
        return diffAmountTotal;
    }

    /**
     * 差异金额合计
     * @param diffAmountTotal 差异金额合计
     */
    public void setDiffAmountTotal(BigDecimal diffAmountTotal) {
        this.diffAmountTotal = diffAmountTotal;
    }

    /**
     * 状态（1-已完成；2-已冲销）
     * @return status 状态（1-已完成；2-已冲销）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态（1-已完成；2-已冲销）
     * @param status 状态（1-已完成；2-已冲销）
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
        sb.append(", code=").append(code);
        sb.append(", commissionDate=").append(commissionDate);
        sb.append(", commissionManId=").append(commissionManId);
        sb.append(", commissionManCode=").append(commissionManCode);
        sb.append(", commissionManName=").append(commissionManName);
        sb.append(", clerkId=").append(clerkId);
        sb.append(", clerkCode=").append(clerkCode);
        sb.append(", clerkName=").append(clerkName);
        sb.append(", saleDateFrom=").append(saleDateFrom);
        sb.append(", saleDateTo=").append(saleDateTo);
        sb.append(", amountTotal=").append(amountTotal);
        sb.append(", realAmountTotal=").append(realAmountTotal);
        sb.append(", diffAmountTotal=").append(diffAmountTotal);
        sb.append(", status=").append(status);
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