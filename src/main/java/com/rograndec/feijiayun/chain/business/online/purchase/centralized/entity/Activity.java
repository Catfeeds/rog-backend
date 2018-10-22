package com.rograndec.feijiayun.chain.business.online.purchase.centralized.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_activity
 * 
 * 
 * @author Asze
 * 
 * 2017-11-21
 */
public class Activity implements Serializable {
    /**
     * 
     */
    @ApiModelProperty(value = "")
    private Long id;

    /**
     * 活动名称
     */
    @ApiModelProperty(value = "活动名称")
    private String name;

    /**
     * MPH活动id
     */
    @ApiModelProperty(value = "MPH活动id")
    private Long mphActivityId;

    /**
     * 活动类型ID（1-单品合计满减；2-有买有赠；3：低价换购；4-特价专区；5-限时秒杀；6-未知）
     */
    @ApiModelProperty(value = "活动类型ID（1-单品合计满减；2-有买有赠；3：低价换购；4-特价专区；5-限时秒杀；6-未知）")
    private Integer typeId;

    /**
     * 0-无指定客户；1-有指定客户；2-有指定客户类型；3-未知
     */
    @ApiModelProperty(value = "0-无指定客户；1-有指定客户；2-有指定客户类型；3-未知")
    private String typeName;

    /**
     * Mph供应商ID
     */
    @ApiModelProperty(value = "Mph供应商ID")
    private Long mphSupplierId;

    /**
     * Mph供应商名称
     */
    @ApiModelProperty(value = "Mph供应商名称")
    private String mphSupplierName;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    /**
     * 起配金额
     */
    @ApiModelProperty(value = "起配金额")
    private BigDecimal matchingAmount;

    /**
     * 优先级
     */
    @ApiModelProperty(value = "优先级")
    private Integer priority;

    /**
     * 运输方式
     */
    @ApiModelProperty(value = "运输方式")
    private String transport;

    /**
     * 0-无指定客户；1-有指定客户；2-有指定客户类型；3-未知
     */
    @ApiModelProperty(value = "0-无指定客户；1-有指定客户；2-有指定客户类型；3-未知")
    private Integer memberStatus;

    /**
     * 0-无指定客户；1-有指定客户；2-有指定客户类型；3-未知
     */
    @ApiModelProperty(value = "0-无指定客户；1-有指定客户；2-有指定客户类型；3-未知")
    private String memberStatusName;

    /**
     * Mph供应商商城logo（bucket失效时间）
     */
    @ApiModelProperty(value = "Mph供应商商城logo（bucket失效时间）")
    private String mphSupplierLogo;

    /**
     * 促销活动上传logo,需加前缀//download.mypharma.com/
     */
    @ApiModelProperty(value = "促销活动上传logo,需加前缀//download.mypharma.com/")
    private String activityLogo;

    /**
     * 活动描述
     */
    @ApiModelProperty(value = "活动描述")
    private String bewrite;

    /**
     * 操作状态 0-执行   1-中止
     */
    @ApiModelProperty(value = "操作状态 0-执行   1-中止")
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
     * saas_activity
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     * @return id 
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 活动名称
     * @return name 活动名称
     */
    public String getName() {
        return name;
    }

    /**
     * 活动名称
     * @param name 活动名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * MPH活动id
     * @return mph_activity_id MPH活动id
     */
    public Long getMphActivityId() {
        return mphActivityId;
    }

    /**
     * MPH活动id
     * @param mphActivityId MPH活动id
     */
    public void setMphActivityId(Long mphActivityId) {
        this.mphActivityId = mphActivityId;
    }

    /**
     * 活动类型ID（1-单品合计满减；2-有买有赠；3：低价换购；4-特价专区；5-限时秒杀；6-未知）
     * @return type_id 活动类型ID（1-单品合计满减；2-有买有赠；3：低价换购；4-特价专区；5-限时秒杀；6-未知）
     */
    public Integer getTypeId() {
        return typeId;
    }

    /**
     * 活动类型ID（1-单品合计满减；2-有买有赠；3：低价换购；4-特价专区；5-限时秒杀；6-未知）
     * @param typeId 活动类型ID（1-单品合计满减；2-有买有赠；3：低价换购；4-特价专区；5-限时秒杀；6-未知）
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    /**
     * 0-无指定客户；1-有指定客户；2-有指定客户类型；3-未知
     * @return type_name 0-无指定客户；1-有指定客户；2-有指定客户类型；3-未知
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * 0-无指定客户；1-有指定客户；2-有指定客户类型；3-未知
     * @param typeName 0-无指定客户；1-有指定客户；2-有指定客户类型；3-未知
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    /**
     * Mph供应商ID
     * @return mph_supplier_id Mph供应商ID
     */
    public Long getMphSupplierId() {
        return mphSupplierId;
    }

    /**
     * Mph供应商ID
     * @param mphSupplierId Mph供应商ID
     */
    public void setMphSupplierId(Long mphSupplierId) {
        this.mphSupplierId = mphSupplierId;
    }

    /**
     * Mph供应商名称
     * @return mph_supplier_name Mph供应商名称
     */
    public String getMphSupplierName() {
        return mphSupplierName;
    }

    /**
     * Mph供应商名称
     * @param mphSupplierName Mph供应商名称
     */
    public void setMphSupplierName(String mphSupplierName) {
        this.mphSupplierName = mphSupplierName == null ? null : mphSupplierName.trim();
    }

    /**
     * 开始时间
     * @return start_time 开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 开始时间
     * @param startTime 开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 结束时间
     * @return end_time 结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 结束时间
     * @param endTime 结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 起配金额
     * @return matching_amount 起配金额
     */
    public BigDecimal getMatchingAmount() {
        return matchingAmount;
    }

    /**
     * 起配金额
     * @param matchingAmount 起配金额
     */
    public void setMatchingAmount(BigDecimal matchingAmount) {
        this.matchingAmount = matchingAmount;
    }

    /**
     * 优先级
     * @return priority 优先级
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * 优先级
     * @param priority 优先级
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    /**
     * 运输方式
     * @return transport 运输方式
     */
    public String getTransport() {
        return transport;
    }

    /**
     * 运输方式
     * @param transport 运输方式
     */
    public void setTransport(String transport) {
        this.transport = transport == null ? null : transport.trim();
    }

    /**
     * 0-无指定客户；1-有指定客户；2-有指定客户类型；3-未知
     * @return member_status 0-无指定客户；1-有指定客户；2-有指定客户类型；3-未知
     */
    public Integer getMemberStatus() {
        return memberStatus;
    }

    /**
     * 0-无指定客户；1-有指定客户；2-有指定客户类型；3-未知
     * @param memberStatus 0-无指定客户；1-有指定客户；2-有指定客户类型；3-未知
     */
    public void setMemberStatus(Integer memberStatus) {
        this.memberStatus = memberStatus;
    }

    /**
     * 0-无指定客户；1-有指定客户；2-有指定客户类型；3-未知
     * @return member_status_name 0-无指定客户；1-有指定客户；2-有指定客户类型；3-未知
     */
    public String getMemberStatusName() {
        return memberStatusName;
    }

    /**
     * 0-无指定客户；1-有指定客户；2-有指定客户类型；3-未知
     * @param memberStatusName 0-无指定客户；1-有指定客户；2-有指定客户类型；3-未知
     */
    public void setMemberStatusName(String memberStatusName) {
        this.memberStatusName = memberStatusName == null ? null : memberStatusName.trim();
    }

    /**
     * Mph供应商商城logo（bucket失效时间）
     * @return mph_supplier_logo Mph供应商商城logo（bucket失效时间）
     */
    public String getMphSupplierLogo() {
        return mphSupplierLogo;
    }

    /**
     * Mph供应商商城logo（bucket失效时间）
     * @param mphSupplierLogo Mph供应商商城logo（bucket失效时间）
     */
    public void setMphSupplierLogo(String mphSupplierLogo) {
        this.mphSupplierLogo = mphSupplierLogo == null ? null : mphSupplierLogo.trim();
    }

    /**
     * 促销活动上传logo,需加前缀//download.mypharma.com/
     * @return activity_logo 促销活动上传logo,需加前缀//download.mypharma.com/
     */
    public String getActivityLogo() {
        return activityLogo;
    }

    /**
     * 促销活动上传logo,需加前缀//download.mypharma.com/
     * @param activityLogo 促销活动上传logo,需加前缀//download.mypharma.com/
     */
    public void setActivityLogo(String activityLogo) {
        this.activityLogo = activityLogo == null ? null : activityLogo.trim();
    }

    /**
     * 活动描述
     * @return bewrite 活动描述
     */
    public String getBewrite() {
        return bewrite;
    }

    /**
     * 活动描述
     * @param bewrite 活动描述
     */
    public void setBewrite(String bewrite) {
        this.bewrite = bewrite == null ? null : bewrite.trim();
    }

    /**
     * 操作状态 0-执行   1-中止
     * @return status 操作状态 0-执行   1-中止
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 操作状态 0-执行   1-中止
     * @param status 操作状态 0-执行   1-中止
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
        sb.append(", name=").append(name);
        sb.append(", mphActivityId=").append(mphActivityId);
        sb.append(", typeId=").append(typeId);
        sb.append(", typeName=").append(typeName);
        sb.append(", mphSupplierId=").append(mphSupplierId);
        sb.append(", mphSupplierName=").append(mphSupplierName);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", matchingAmount=").append(matchingAmount);
        sb.append(", priority=").append(priority);
        sb.append(", transport=").append(transport);
        sb.append(", memberStatus=").append(memberStatus);
        sb.append(", memberStatusName=").append(memberStatusName);
        sb.append(", mphSupplierLogo=").append(mphSupplierLogo);
        sb.append(", activityLogo=").append(activityLogo);
        sb.append(", bewrite=").append(bewrite);
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