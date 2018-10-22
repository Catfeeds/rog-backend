package com.rograndec.feijiayun.chain.business.member.set.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "MemberCardLevelVO", description = "会员卡级别对象")
public class MemberCardLevelVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	 * 成长值（0-累积积分；1-当前积分；2-累积储值）
	 */
	@ApiModelProperty(value = "成长值（0-累积积分；1-当前积分；2-累积储值）")
	private Integer growthValue;

	/**
	 * 累积积分
	 */
	@ApiModelProperty(value = "累积积分")
	private BigDecimal integral;

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

	public static long getSerialVersionUID() {
		return serialVersionUID;
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

	public Integer getGrowthValue() {
		return growthValue;
	}

	public void setGrowthValue(Integer growthValue) {
		this.growthValue = growthValue;
	}

	public BigDecimal getIntegral() {
		return integral;
	}

	public void setIntegral(BigDecimal integral) {
		this.integral = integral;
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


	public Integer getSysType() {
		return sysType;
	}

	public void setSysType(Integer sysType) {
		this.sysType = sysType;
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
}
