package com.rograndec.feijiayun.chain.business.report.retail.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
@ApiModel(value = "RequestGoodsCategorySaleVo", description = "门店-销售报表-商品分类销售")
public class RequestGoodsCategorySaleVo implements Serializable {
	@ApiModelProperty(value = "1-按日结日期,2-按销售日期")
	private Integer type;
	@ApiModelProperty(value = "开始日期")
	private String startDate;
	@ApiModelProperty(value = "结束日期")
	private String endDate;

	@ApiModelProperty(value = "门店类型（1-自营店；2-加盟店）")
	private Integer chainType;
	@ApiModelProperty(value = "门店编码")
	private String code;
	@ApiModelProperty(value = "门店名称")
	private String name;



	private Long id;
	private Long enterpriseId;
	private Long eid;
	private Integer parent;
	private Long parentId;

	public Long getEid() {
		return eid;
	}

	public void setEid(Long eid) {
		this.eid = eid;
	}

	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	public Integer getChainType() {
		return chainType;
	}

	public void setChainType(Integer chainType) {
		this.chainType = chainType;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
}
