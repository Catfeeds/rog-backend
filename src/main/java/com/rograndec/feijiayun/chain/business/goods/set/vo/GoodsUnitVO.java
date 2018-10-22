package com.rograndec.feijiayun.chain.business.goods.set.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author dongyang.du 2017-9-9 10:46:49
 *
 */
public class GoodsUnitVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     *  主键ID
     */
    @ApiModelProperty(value=" 主键ID",required=true)
    private Long id;

    /**
     * 企业（总部）ID
     */
    @ApiModelProperty(value=" 企业（总部）ID",required=true)
    private Long enterpriseId;

    /**
     * 编码
     */
    @ApiModelProperty(value=" 编码",required=true)
    private String code;

    /**
     * 名称
     */
    @ApiModelProperty(value=" 名称",required=true)
    private String name;

    /**
     * 状态（0-禁用；1-启用）
     */
    @ApiModelProperty(value=" 状态（0-禁用；1-启用）",required=true)
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value=" 备注",required=true)
    private String remark;

    /**
     * '0：用户自定义部门；1-系统默认部门'
     */
    @ApiModelProperty(value=" '0：用户自定义部门；1-系统默认部门'",required=true)
    private Integer sysType;

	@ApiModelProperty(value="是否可删除")
    private Boolean deleteFlag = true;

	@ApiModelProperty(value="状态是否可以编辑")
	private Boolean updateFlag = true;



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

	@Override
	public String toString() {
		return "GoodsUnitVO [id=" + id + ", enterpriseId=" + enterpriseId + ", code=" + code + ", name=" + name
				+ ", status=" + status + ", remark=" + remark + ", sysType=" + sysType + "]";
	}


	
    

}
