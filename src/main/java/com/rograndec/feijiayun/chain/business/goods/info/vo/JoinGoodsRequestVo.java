package com.rograndec.feijiayun.chain.business.goods.info.vo;

import java.io.Serializable;

import com.rograndec.feijiayun.chain.common.valid.annotation.ValidNotNull;

import io.swagger.annotations.ApiModelProperty;

public class JoinGoodsRequestVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "页码")
    @ValidNotNull()
    private Integer pageNo;
    @ApiModelProperty(value = "每页显示的记录数")
    @ValidNotNull()
    private Integer pageSize;
    @ApiModelProperty(value = "加盟店id")
    @ValidNotNull()
    private Long enterpriseId;
    @ApiModelProperty(value = "总店id")
    @ValidNotNull()
    private Long parentId;
    /*   @ApiModelProperty(value = "商品编码")
     private String code;
    @ApiModelProperty(value = "条形码")
    private String barcode;
    @ApiModelProperty(value = "检索码（型如“通用名检索码—商品名检索码”）")
    private String pinyinCode;
    @ApiModelProperty(value = "通用名称")
    private String genericName;
    @ApiModelProperty(value = "批准文号")
    private String approvalNumber;
    @ApiModelProperty(value = "生产厂商")
    private String manufacturer;*/
    @ApiModelProperty(value = "通用参数")
    private String commonParam;
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Long getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	/*public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}*/
	public String getCommonParam() {
		return commonParam;
	}
	public void setCommonParam(String commonParam) {
		this.commonParam = commonParam;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
    
}
