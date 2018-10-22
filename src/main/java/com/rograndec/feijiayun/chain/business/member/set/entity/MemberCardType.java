package com.rograndec.feijiayun.chain.business.member.set.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_member_card_type
 * 
 * 
 * @author dudy
 * 
 * 2017-09-19
 */
public class MemberCardType implements Serializable {
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
     * 编码
     */
    @ApiModelProperty(value = "编码")
    private String code;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 类别（0-积分+储值；1-仅积分；2-仅储值）
     */
    @ApiModelProperty(value = "类别（0-积分+储值；1-仅积分；2-仅储值）")
    private Integer type;

    /**
     * 级别ID
     */
    @ApiModelProperty(value = "级别ID")
    private Long levelId;

    /**
     * 级别名称
     */
    @ApiModelProperty(value = "级别名称")
    private String levelName;

    /**
     * 价格策略（0-零售价；1-会员价）
     */
    @ApiModelProperty(value = "价格策略（0-零售价；1-会员价）")
    private Integer priceStrategy;

    /**
     * 价格策略
     */
    @ApiModelProperty(value = "价格策略")
    private BigDecimal discountStrategy;

    /**
     * 积分策略金额
     */
    @ApiModelProperty(value = "积分策略金额")
    private BigDecimal amount;

    /**
     * 积分策略积分
     */
    @ApiModelProperty(value = "积分策略积分")
    private BigDecimal integral;

    /**
     * 使用门店（0-全部；1-自营店；2-加盟店；3-指定门店）
     */
    @ApiModelProperty(value = "使用门店（0-全部；1-自营店；2-加盟店；3-指定门店）")
    private Integer useStorer;

    /**
     * 指定门店ID集合
     */
    @ApiModelProperty(value = "指定门店ID集合")
    private String storerIds;

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

    @ApiModelProperty(value = "0：用户自定义部门；1-系统默认部门")
    private Integer sysType;

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
     * saas_member_card_type
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
     * 编码
     * @return code 编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 编码
     * @param code 编码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 名称
     * @return name 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 名称
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 类别（0-积分+储值；1-仅积分；2-仅储值）
     * @return type 类别（0-积分+储值；1-仅积分；2-仅储值）
     */
    public Integer getType() {
        return type;
    }

    /**
     * 类别（0-积分+储值；1-仅积分；2-仅储值）
     * @param type 类别（0-积分+储值；1-仅积分；2-仅储值）
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 级别ID
     * @return level_id 级别ID
     */
    public Long getLevelId() {
        return levelId;
    }

    /**
     * 级别ID
     * @param levelId 级别ID
     */
    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    /**
     * 级别名称
     * @return level_name 级别名称
     */
    public String getLevelName() {
        return levelName;
    }

    /**
     * 级别名称
     * @param levelName 级别名称
     */
    public void setLevelName(String levelName) {
        this.levelName = levelName == null ? null : levelName.trim();
    }

    /**
     * 价格策略（0-零售价；1-会员价）
     * @return price_strategy 价格策略（0-零售价；1-会员价）
     */
    public Integer getPriceStrategy() {
        return priceStrategy;
    }

    /**
     * 价格策略（0-零售价；1-会员价）
     * @param priceStrategy 价格策略（0-零售价；1-会员价）
     */
    public void setPriceStrategy(Integer priceStrategy) {
        this.priceStrategy = priceStrategy;
    }

    /**
     * 价格策略
     * @return discount_strategy 价格策略
     */
    public BigDecimal getDiscountStrategy() {
        return discountStrategy;
    }

    /**
     * 价格策略
     * @param discountStrategy 价格策略
     */
    public void setDiscountStrategy(BigDecimal discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    /**
     * 积分策略金额
     * @return amount 积分策略金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 积分策略金额
     * @param amount 积分策略金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 积分策略积分
     * @return integral 积分策略积分
     */
    public BigDecimal getIntegral() {
        return integral;
    }

    /**
     * 积分策略积分
     * @param integral 积分策略积分
     */
    public void setIntegral(BigDecimal integral) {
        this.integral = integral;
    }

    /**
     * 使用门店（0-全部；1-自营店；2-加盟店；3-指定门店）
     * @return use_storer 使用门店（0-全部；1-自营店；2-加盟店；3-指定门店）
     */
    public Integer getUseStorer() {
        return useStorer;
    }

    /**
     * 使用门店（0-全部；1-自营店；2-加盟店；3-指定门店）
     * @param useStorer 使用门店（0-全部；1-自营店；2-加盟店；3-指定门店）
     */
    public void setUseStorer(Integer useStorer) {
        this.useStorer = useStorer;
    }

    /**
     * 指定门店ID集合
     * @return storer_ids 指定门店ID集合
     */
    public String getStorerIds() {
        return storerIds;
    }

    /**
     * 指定门店ID集合
     * @param storerIds 指定门店ID集合
     */
    public void setStorerIds(String storerIds) {
        this.storerIds = storerIds == null ? null : storerIds.trim();
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

    public Integer getSysType() {
        return sysType;
    }

    public void setSysType(Integer sysType) {
        this.sysType = sysType;
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
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", type=").append(type);
        sb.append(", levelId=").append(levelId);
        sb.append(", levelName=").append(levelName);
        sb.append(", priceStrategy=").append(priceStrategy);
        sb.append(", discountStrategy=").append(discountStrategy);
        sb.append(", amount=").append(amount);
        sb.append(", integral=").append(integral);
        sb.append(", useStorer=").append(useStorer);
        sb.append(", storerIds=").append(storerIds);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", sysType=").append(sysType);

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