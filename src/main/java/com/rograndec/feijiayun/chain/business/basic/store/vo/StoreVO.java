package com.rograndec.feijiayun.chain.business.basic.store.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "StoreVO", description = "门店基本信息对象")
public class StoreVO implements Serializable{

	/**
	 * @Description:
	 * author liuqun
	 * @date 2017年8月28日 下午3:43:32 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * ID
     */
	@ApiModelProperty(required = true, value = "门店ID")
    private Long id;
	
	/**
     * 编码
     */
	@ApiModelProperty(required = true, value = "编码")
    private String code;
	
	/**
     * 企业名称
     */
	@ApiModelProperty(required = true, value = "名称")
    private String name;
	
	/**
     * 门店类型（0-总部；1-自营店；2-加盟店）
     */
	@ApiModelProperty(required = true, value = "门店类型（0-总部；1-自营店；2-加盟店）")
    private Integer chainType;
	
	/**
     * 门店类型名称
     */
	@ApiModelProperty(required = true, value = "门店类型名称")
    private String chainTypeName;
	
	/**
     * 企业负责人名称
     */
	@ApiModelProperty(required = false, value = "企业负责人名称")
    private String businessManName;
	
	/**
     * 公司电话
     */
	@ApiModelProperty(required = false, value = "公司电话")
    private String tel;

    /**
     * 公司传真
     */
	@ApiModelProperty(required = false, value = "公司传真")
    private String fax;
	
	/**
     * 公司地址
     */
	@ApiModelProperty(required = false, value = "公司地址")
    private String companyAddress;
	
	/**
     * 状态（0-禁用；1-启用）
     */
	@ApiModelProperty(required = true, value = "状态（0-禁用；1-启用）")
    private Integer status;

	/**
	 * 审核状态（0-待审核；1-审核通过；2-审核拒绝；-2-审核撤回；-1-审核中）
	 */
	@ApiModelProperty(required = true, value = " 审核状态（0-待审核；1-审核通过；2-审核拒绝；-2-审核撤回；-1-审核中）")
	private Integer approveStatus;

	@ApiModelProperty(required = true, value = " 审核状态描述")
	private String approveStatusDesc;

	/**
     * 状态名称
     */
	@ApiModelProperty(required = true, value = "状态名称")
    private String statusName;

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
	
	public Integer getChainType() {
		return chainType;
	}

	public void setChainType(Integer chainType) {
		this.chainType = chainType;
	}

	public String getChainTypeName() {
		return chainTypeName;
	}

	public void setChainTypeName(String chainTypeName) {
		this.chainTypeName = chainTypeName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	@Override
	public String toString() {
		return "StoreVo [id=" + id + ", code=" + code + ", name=" + name
				+ ", chainType=" + chainType + ", chainTypeName="
				+ chainTypeName + ", businessManName=" + businessManName
				+ ", tel=" + tel + ", fax=" + fax + ", companyAddress="
				+ companyAddress + ", status=" + status + "]";
	}

	public Integer getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(Integer approveStatus) {
		this.approveStatus = approveStatus;
	}

	public String getApproveStatusDesc() {
		return approveStatusDesc;
	}

	public void setApproveStatusDesc(String approveStatusDesc) {
		this.approveStatusDesc = approveStatusDesc;
	}
}
