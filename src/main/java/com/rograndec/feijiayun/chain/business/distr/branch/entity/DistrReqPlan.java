package com.rograndec.feijiayun.chain.business.distr.branch.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
 
/**
 * 
 * saas_distr_req_plan
 * 
 * 
 * @author kexinhao
 * 
 * 2017-10-07
 */
public class DistrReqPlan implements Serializable {
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
     * 单据类型（5401）
     */
    @ApiModelProperty(value = "单据类型（5401）")
    private Integer orderType;

    /**
     * 要货计划单号
     */
    @ApiModelProperty(value = "要货计划单号")
    private String code;

    /**
     * 计划日期
     */
    @ApiModelProperty(value = "计划日期")
    private Date planDate;

    /**
     * 配货单位ID
     */
    @ApiModelProperty(value = "配货单位ID")
    private Long distrUnitId;

    /**
     * 配货单位编码
     */
    @ApiModelProperty(value = "配货单位编码")
    private String distrUnitCode;

    /**
     * 配货单位名称
     */
    @ApiModelProperty(value = "配货单位名称")
    private String distrUnitName;

    /**
     * 计划人员ID
     */
    @ApiModelProperty(value = "计划人员ID")
    private Long plannerId;

    /**
     * 计划人员编码
     */
    @ApiModelProperty(value = "计划人员编码")
    private String plannerCode;

    /**
     * 计划人员名称
     */
    @ApiModelProperty(value = "计划人员名称")
    private String plannerName;


    @ApiModelProperty(value = "要货类型（0-总部配送；3-分店间调剂）")
    private Integer requestType;

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
	 * 出库单位ID
	 */
	@ApiModelProperty(value = "出库单位ID")
	private Long outboundUnitId;

	/**
	 * 出库位单位编码
	 */
	@ApiModelProperty(value = "出库位单位编码 ")
	private String outboundUnitCode;

	/**
	 * 出库单位名称
	 */
	@ApiModelProperty(value = "出库单位名称")
	private String outboundUnitName;

    /**
     * saas_distr_req_plan
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
     * 单据类型（5401）
     * @return order_type 单据类型（5401）
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 单据类型（5401）
     * @param orderType 单据类型（5401）
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 要货计划单号
     * @return code 要货计划单号
     */
    public String getCode() {
        return code;
    }

    /**
     * 要货计划单号
     * @param code 要货计划单号
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 计划日期
     * @return plan_date 计划日期
     */
    public Date getPlanDate() {
        return planDate;
    }

    /**
     * 计划日期
     * @param planDate 计划日期
     */
    public void setPlanDate(Date planDate) {
        this.planDate = planDate;
    }

    /**
     * 配货单位ID
     * @return distr_unit_id 配货单位ID
     */
    public Long getDistrUnitId() {
        return distrUnitId;
    }

    /**
     * 配货单位ID
     * @param distrUnitId 配货单位ID
     */
    public void setDistrUnitId(Long distrUnitId) {
        this.distrUnitId = distrUnitId;
    }

    /**
     * 配货单位编码
     * @return distr_unit_code 配货单位编码
     */
    public String getDistrUnitCode() {
        return distrUnitCode;
    }

    /**
     * 配货单位编码
     * @param distrUnitCode 配货单位编码
     */
    public void setDistrUnitCode(String distrUnitCode) {
        this.distrUnitCode = distrUnitCode == null ? null : distrUnitCode.trim();
    }

    /**
     * 配货单位名称
     * @return distr_unit_name 配货单位名称
     */
    public String getDistrUnitName() {
        return distrUnitName;
    }

    /**
     * 配货单位名称
     * @param distrUnitName 配货单位名称
     */
    public void setDistrUnitName(String distrUnitName) {
        this.distrUnitName = distrUnitName == null ? null : distrUnitName.trim();
    }

    /**
     * 计划人员ID
     * @return planner_id 计划人员ID
     */
    public Long getPlannerId() {
        return plannerId;
    }

    /**
     * 计划人员ID
     * @param plannerId 计划人员ID
     */
    public void setPlannerId(Long plannerId) {
        this.plannerId = plannerId;
    }

    /**
     * 计划人员编码
     * @return planner_code 计划人员编码
     */
    public String getPlannerCode() {
        return plannerCode;
    }

    /**
     * 计划人员编码
     * @param plannerCode 计划人员编码
     */
    public void setPlannerCode(String plannerCode) {
        this.plannerCode = plannerCode == null ? null : plannerCode.trim();
    }

    /**
     * 计划人员名称
     * @return planner_name 计划人员名称
     */
    public String getPlannerName() {
        return plannerName;
    }

    /**
     * 计划人员名称
     * @param plannerName 计划人员名称
     */
    public void setPlannerName(String plannerName) {
        this.plannerName = plannerName == null ? null : plannerName.trim();
    }

    /**
     * 要货类型（0-总部配送；1-分店间调剂）
     * @return request_type 要货类型（0-总部配送；1-分店间调剂）
     */
    public Integer getRequestType() {
        return requestType;
    }

    /**
     * 要货类型（0-总部配送；1-分店间调剂）
     * @param requestType 要货类型（0-总部配送；1-分店间调剂）
     */
    public void setRequestType(Integer requestType) {
        this.requestType = requestType;
    }

    /**
     * 数量合计
     * @return quantity_total 数量合计
     */
    public BigDecimal getQuantityTotal() {
        return quantityTotal;
    }

    /**
     * 数量合计
     * @param quantityTotal 数量合计
     */
    public void setQuantityTotal(BigDecimal quantityTotal) {
        this.quantityTotal = quantityTotal;
    }

    /**
     * 品种数量
     * @return varieties_quantity 品种数量
     */
    public Integer getVarietiesQuantity() {
        return varietiesQuantity;
    }

    /**
     * 品种数量
     * @param varietiesQuantity 品种数量
     */
    public void setVarietiesQuantity(Integer varietiesQuantity) {
        this.varietiesQuantity = varietiesQuantity;
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

	public Long getOutboundUnitId() {
		return outboundUnitId;
	}

	public void setOutboundUnitId(Long outboundUnitId) {
		this.outboundUnitId = outboundUnitId;
	}

	public String getOutboundUnitCode() {
		return outboundUnitCode;
	}

	public void setOutboundUnitCode(String outboundUnitCode) {
		this.outboundUnitCode = outboundUnitCode;
	}

	public String getOutboundUnitName() {
		return outboundUnitName;
	}

	public void setOutboundUnitName(String outboundUnitName) {
		this.outboundUnitName = outboundUnitName;
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
        sb.append(", planDate=").append(planDate);
        sb.append(", distrUnitId=").append(distrUnitId);
        sb.append(", distrUnitCode=").append(distrUnitCode);
        sb.append(", distrUnitName=").append(distrUnitName);
        sb.append(", plannerId=").append(plannerId);
        sb.append(", plannerCode=").append(plannerCode);
        sb.append(", plannerName=").append(plannerName);
        sb.append(", requestType=").append(requestType);
        sb.append(", quantityTotal=").append(quantityTotal);
        sb.append(", varietiesQuantity=").append(varietiesQuantity);
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