package com.rograndec.feijiayun.chain.business.report.retail.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@ApiModel(value = "GoodsCategorySaleVo", description = "门店-销售报表-商品分类销售")
public class GoodsCategorySaleVo implements Serializable {
	@ApiModelProperty(value = "分类id")
	private Long id;
	@ApiModelProperty(value = "父分类id")
	private Long parentId;
	@ApiModelProperty(value = "分类名称")
	private String name;
	@ApiModelProperty(value = "总额")
	private BigDecimal realAmountTotal;
	@ApiModelProperty(value = "不含税总额")
	private BigDecimal notaxRealAmountTotal;
	@ApiModelProperty(value = "税额")
	private BigDecimal taxAmountTotal;
	@ApiModelProperty(value = "子分类")
	List<GoodsCategorySaleVo> children;

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

	public List<GoodsCategorySaleVo> getChildren() {
		return children;
	}

	public void setChildren(List<GoodsCategorySaleVo> children) {
		this.children = children;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getRealAmountTotal() {
		if (this.children == null || this.children.isEmpty()) {
			return realAmountTotal;
		} else {
			BigDecimal realAmountTotal = BigDecimal.ZERO;
			for (GoodsCategorySaleVo child : this.children) {
				realAmountTotal = realAmountTotal.add(child.getRealAmountTotal());
			}
			return realAmountTotal;
		}
	}

	public void setRealAmountTotal(BigDecimal realAmountTotal) {
		this.realAmountTotal = realAmountTotal;
	}

	public BigDecimal getNotaxRealAmountTotal() {
		if (this.children == null || this.children.isEmpty()) {
			return notaxRealAmountTotal;
		} else {
			BigDecimal notaxRealAmountTotal = BigDecimal.ZERO;
			for (GoodsCategorySaleVo child : this.children) {
				notaxRealAmountTotal = notaxRealAmountTotal.add(child.getNotaxRealAmountTotal());
			}
			return notaxRealAmountTotal;
		}
	}

	public void setNotaxRealAmountTotal(BigDecimal notaxRealAmountTotal) {
		this.notaxRealAmountTotal = notaxRealAmountTotal;
	}

	public BigDecimal getTaxAmountTotal() {
		if (this.children == null || this.children.isEmpty()) {
			return taxAmountTotal;
		} else {
			BigDecimal taxAmountTotal = BigDecimal.ZERO;
			for (GoodsCategorySaleVo child : this.children) {
				taxAmountTotal = taxAmountTotal.add(child.getTaxAmountTotal());
			}
			return taxAmountTotal;
		}
	}

	public void setTaxAmountTotal(BigDecimal taxAmountTotal) {
		this.taxAmountTotal = taxAmountTotal;
	}
}
