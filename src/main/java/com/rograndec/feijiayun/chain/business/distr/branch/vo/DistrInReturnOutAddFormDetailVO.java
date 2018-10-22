package com.rograndec.feijiayun.chain.business.distr.branch.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author dongdong.zhang
 * 2017-10-12
 */
@ApiModel
public class DistrInReturnOutAddFormDetailVO implements Serializable {

	/**
	 * 配进入库明细ID
	 */
	@ApiModelProperty(value = "调用配进入库时用（直接新增的商品不需要），配进入库明细ID，（要货计划生成的单据新增修改必填）")
	private Long baseOrderDtlId;


	@ApiModelProperty(value = "商品id")
	private Long goodsId;

	/**
	 * 数量
	 */
	@ApiModelProperty(value = "数量",required = true)
	private BigDecimal quantity;

	/**
	 * 单价
	 */
	@ApiModelProperty(value = "单价",required = true)
	private BigDecimal unitPrice;

	/**
	 * 行折扣（%）
	 */
	@ApiModelProperty(value = "行折扣（%）",required = true)
	private BigDecimal lineDiscount;

	/**
	 * 税率ID
	 */
	@ApiModelProperty(value = "税率ID",required = true)
	private Long taxRateId;
	/**
	 * 批号ID
	 */
	@ApiModelProperty(value = "批号ID",required = true)
	private Long lotId;

	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注")
	private String remark;


	/**
	 * 行号
	 */
	@ApiModelProperty(value = "行号",required = true)
	private Integer lineNum;

	@ApiModelProperty(value = "货位删除id集合")
	private List<Long> deleteShelfIds = new ArrayList<>();


	@ApiModelProperty(value = "购进退出出库单货位明细信息")
	private List<DistrInReturnOutAddFormShelfVO> distrInReturnOutAddFormShelfVOS;

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getLineDiscount() {
		return lineDiscount;
	}

	public void setLineDiscount(BigDecimal lineDiscount) {
		this.lineDiscount = lineDiscount;
	}

	public Long getTaxRateId() {
		return taxRateId;
	}

	public void setTaxRateId(Long taxRateId) {
		this.taxRateId = taxRateId;
	}

	public Long getLotId() {
		return lotId;
	}

	public void setLotId(Long lotId) {
		this.lotId = lotId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getLineNum() {
		return lineNum;
	}

	public void setLineNum(Integer lineNum) {
		this.lineNum = lineNum;
	}

	public List<Long> getDeleteShelfIds() {
		return deleteShelfIds;
	}

	public void setDeleteShelfIds(List<Long> deleteShelfIds) {
		this.deleteShelfIds = deleteShelfIds;
	}

	public List<DistrInReturnOutAddFormShelfVO> getDistrInReturnOutAddFormShelfVOS() {
		return distrInReturnOutAddFormShelfVOS;
	}

	public void setDistrInReturnOutAddFormShelfVOS(List<DistrInReturnOutAddFormShelfVO> distrInReturnOutAddFormShelfVOS) {
		this.distrInReturnOutAddFormShelfVOS = distrInReturnOutAddFormShelfVOS;
	}

	public Long getBaseOrderDtlId() {
		return baseOrderDtlId;
	}

	public void setBaseOrderDtlId(Long baseOrderDtlId) {
		this.baseOrderDtlId = baseOrderDtlId;
	}
}