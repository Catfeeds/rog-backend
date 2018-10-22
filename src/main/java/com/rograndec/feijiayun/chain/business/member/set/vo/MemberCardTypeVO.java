package com.rograndec.feijiayun.chain.business.member.set.vo;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "MemberCardTypeVO", description = "会员卡类型对象")
public class MemberCardTypeVO {

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


	@ApiModelProperty(value="是否可删除")
	private Boolean deleteFlag = true;

	@ApiModelProperty(value="状态是否可以编辑")
	private Boolean updateFlag = true;

	/**
	 * 是否所有者
	 */
	@ApiModelProperty(value = "是否所有者，0否1是(控制可编辑)")
	private Integer isOwner;


	public Boolean getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Boolean getUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(Boolean updateFlag) {
		this.updateFlag = updateFlag;
	}

	public Integer getSysType() {
		return sysType;
	}

	public void setSysType(Integer sysType) {
		this.sysType = sysType;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getLevelId() {
		return levelId;
	}

	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public Integer getPriceStrategy() {
		return priceStrategy;
	}

	public void setPriceStrategy(Integer priceStrategy) {
		this.priceStrategy = priceStrategy;
	}

	public BigDecimal getDiscountStrategy() {
		return discountStrategy;
	}

	public void setDiscountStrategy(BigDecimal discountStrategy) {
		this.discountStrategy = discountStrategy;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getIntegral() {
		return integral;
	}

	public void setIntegral(BigDecimal integral) {
		this.integral = integral;
	}

	public Integer getUseStorer() {
		return useStorer;
	}

	public void setUseStorer(Integer useStorer) {
		this.useStorer = useStorer;
	}

	public String getStorerIds() {
		return storerIds;
	}

	public void setStorerIds(String storerIds) {
		this.storerIds = storerIds;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Integer getIsOwner() {
		return isOwner;
	}

	public void setIsOwner(Integer isOwner) {
		this.isOwner = isOwner;
	}

	@Override
	public String toString() {
		return "MemberCardTypeVO [id=" + id + ", code=" + code + ", name=" + name + ", type=" + type + ", levelId=" + levelId
				+ ", levelName=" + levelName + ", priceStrategy=" + priceStrategy + ", discountStrategy=" + discountStrategy + ", amount="
				+ amount + ", integral=" + integral + ", useStorer=" + useStorer + ", storerIds=" + storerIds + ", status=" + status
				+ ", remark=" + remark + "]";
	}

}
