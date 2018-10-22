package com.rograndec.feijiayun.chain.business.basic.supplier.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class SupplierBasicVO implements Serializable{

	@ApiModelProperty(value = "主键ID")
    private Long id;

	@ApiModelProperty(value = "分组ID")
    private Long groupId;
	
	/**
     * 企业（总部）ID
     */
	@ApiModelProperty(value = "企业（总部）ID")
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
	 * 所属机构类型
	 */
	@ApiModelProperty(value = "所属机构类型")
	private Integer ownerChainType;
	
	/**
     * 所属机构类型名称
     */
	@ApiModelProperty(value = "所属机构类型名称")
    private String ownerChainTypeName;

    /**
     * 名称
     */
	@ApiModelProperty(value = "所属机构编码")
    private String ownerCode;
	
	/**
     * 名称
     */
	@ApiModelProperty(value = "所属机构名称")
    private String ownerName;

    /**
     * 性质（0-批发企业；1-生产企业）
     */
	@ApiModelProperty(value = "性质（0-批发企业；1-生产企业）")
    private String nature;
	
	/**
     * 企业负责人ID
     */
	@ApiModelProperty(value = "企业负责人ID")
    private Long businessManId;

    /**
     * 企业负责人编码
     */
	@ApiModelProperty(value = "企业负责人编码")
    private String businessManCode;

    /**
     * 企业负责人名称
     */
	@ApiModelProperty(value = "企业负责人名称")
    private String businessManName;
	
	 /**
     * 公司电话
     */
	@ApiModelProperty(value = "公司电话")
    private String tel;

    /**
     * 公司传真
     */
	@ApiModelProperty(value = "公司传真")
    private String fax;
	
	/**
     * 公司地址
     */
	@ApiModelProperty(value = "公司地址")
    private String companyAddress;
	
	/**
     * 状态
     */
	@ApiModelProperty(value = "状态")
    private Integer status;

	/**
	 * 审核状态
	 */
	@ApiModelProperty(value = "审批状态（0-待审核；1-审核通过；2-审核拒绝；-2-审核撤回；-1-审核中）tips:只有在0和-1的状态时，不可以修改")
	private Integer approveStatus;

	/**
	 * 是否所有者
	 */
	@ApiModelProperty(value = "是否所有者，0否1是(控制可编辑)")
	private Integer isOwner;
	/**
	 *  所属企业id
	 */
	@ApiModelProperty(value = "所属企业id")
	private Long ownerId;

	/**
	 * 药店类型（0-总部；1-自营店；2-加盟店）
	 */
	@ApiModelProperty(value = "药店类型（0-总部；1-自营店；2-加盟店）")
	private Integer chainType;
	
	/**
	 * 0 - 否  1- 是
	 */
	@ApiModelProperty(value = "0 - 否  1- 是")
	private Integer franchisedStoreFlag;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
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

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public Long getBusinessManId() {
		return businessManId;
	}

	public void setBusinessManId(Long businessManId) {
		this.businessManId = businessManId;
	}

	public String getBusinessManCode() {
		return businessManCode;
	}

	public void setBusinessManCode(String businessManCode) {
		this.businessManCode = businessManCode;
	}

	public String getBusinessManName() {
		return businessManName;
	}

	public void setBusinessManName(String businessManName) {
		this.businessManName = businessManName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	
	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Integer getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(Integer approveStatus) {
		this.approveStatus = approveStatus;
	}

	public Integer getIsOwner() {
		return isOwner;
	}

	public void setIsOwner(Integer isOwner) {
		this.isOwner = isOwner;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		return "SupplierBasicVO [id=" + id + ", enterpriseId=" + enterpriseId + ", code="
				+ code + ", name=" + name + ", nature=" + nature + ", businessManId=" + businessManId
				+ ", businessManCode=" + businessManCode + ", businessManName=" + businessManName + ", tel=" + tel
				+ ", fax=" + fax + ", companyAddress=" + companyAddress + ", status=" + status + "]";
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public Integer getChainType() {
		return chainType;
	}

	public void setChainType(Integer chainType) {
		this.chainType = chainType;
	}

	public Integer getFranchisedStoreFlag() {
		return franchisedStoreFlag;
	}

	public void setFranchisedStoreFlag(Integer franchisedStoreFlag) {
		this.franchisedStoreFlag = franchisedStoreFlag;
	}

	public Integer getOwnerChainType() {
		return ownerChainType;
	}

	public void setOwnerChainType(Integer ownerChainType) {
		this.ownerChainType = ownerChainType;
	}

	public String getOwnerChainTypeName() {
		return ownerChainTypeName;
	}

	public void setOwnerChainTypeName(String ownerChainTypeName) {
		this.ownerChainTypeName = ownerChainTypeName;
	}

	public String getOwnerCode() {
		return ownerCode;
	}

	public void setOwnerCode(String ownerCode) {
		this.ownerCode = ownerCode;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	
	
	
}
