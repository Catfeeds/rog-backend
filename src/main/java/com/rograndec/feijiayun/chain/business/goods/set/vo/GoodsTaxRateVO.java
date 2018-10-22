package com.rograndec.feijiayun.chain.business.goods.set.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @author dongyang.du
 *
 */
public class GoodsTaxRateVO implements Serializable{

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
     * 企业（总部ID）
     */
    @ApiModelProperty(value=" 企业（总部ID）",required=true)
    private Long enterpriseId;

    /**
     * 编码
     */
    @ApiModelProperty(value=" 编码",required=true)
    private String code;

    /**
     * 税率
     */
    @ApiModelProperty(value=" 税率",required=true)
    private BigDecimal taxRate;


    /**
     * 状态（0-禁用；1-启用）
     */
    @ApiModelProperty(value=" 状态（0-禁用；1-启用）",required=true)
    private Integer status;
    
    /**
     * '0：用户自定义部门；1-系统默认部门'
     */
    @ApiModelProperty(value=" '0：用户自定义部门；1-系统默认部门'",required=true)
    private Integer sysType;

    /**
     * 备注
     */
    @ApiModelProperty(value=" 备注",required=true)
    private String remark;


	@ApiModelProperty(value="是否可删除，状态是否可以修改")
	private Boolean deleteFlag = false;

	@ApiModelProperty(value="是否可编辑")
	private Boolean updateFlag = false;


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

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
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
		return "GoodsTaxRateVO [id=" + id + ", enterpriseId=" + enterpriseId + ", code=" + code + ", taxRate=" + taxRate
				+ ", status=" + status + ", sysType=" + sysType + ", remark=" + remark + "]";
	}



    

}
