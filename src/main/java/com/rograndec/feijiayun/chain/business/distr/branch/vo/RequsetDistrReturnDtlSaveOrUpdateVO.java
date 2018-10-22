package com.rograndec.feijiayun.chain.business.distr.branch.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author dongdong.zhang
 * 
 * 2017-10-12
 */
@ApiModel
public class  RequsetDistrReturnDtlSaveOrUpdateVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "配进退出单明细行id,修改时需要传递,新增不需要")
    private Long id;
    
    /**
     * 配进入库明细ID
     */
    @ApiModelProperty(value = "调用配进入库时用（直接新增的商品不需要），配进入库明细ID，（要货计划生成的单据新增修改必填）")
    private Long baseOrderDtlId;

    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID" ,required = true)
    private Long goodsId;
    
    /**
     * 商品批号
     */
    @ApiModelProperty(value = "批号id" ,required = true)
    private Long lotId;
    
    /**
     * 单价
     */
    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量" ,required = true)
    private BigDecimal quantity;
    
    /**
     * 行号
     */
    @ApiModelProperty(value = "行号")
    private Integer lineNum;
    
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    
    /**
     * 行折扣（%）
     */
    @ApiModelProperty(value = "折扣" ,required = true)
    private BigDecimal lineDiscount;
    
    /**
     * 税率ID
     */
    @ApiModelProperty(value = "税率ID")
    private Long taxRateId;

    public static List<RequsetDistrReturnDtlSaveOrUpdateVO> getRequsetDistrReturnDtlSaveOrUpdateVOs(List<DistrInReturnOutAddFormDetailVO> distrInReturnOutAddFormDetailVOS){

		return distrInReturnOutAddFormDetailVOS.stream().map(distrInReturnOutAddFormDetailVO -> {

			RequsetDistrReturnDtlSaveOrUpdateVO requsetDistrReturnDtlSaveOrUpdateVO = new RequsetDistrReturnDtlSaveOrUpdateVO();

			/**
			 * 商品ID
			 */
			requsetDistrReturnDtlSaveOrUpdateVO.setGoodsId(distrInReturnOutAddFormDetailVO.getGoodsId());

			/**
			 * 商品批号
			 */
			requsetDistrReturnDtlSaveOrUpdateVO.setLotId(distrInReturnOutAddFormDetailVO.getLotId());

			/**
			 * 单价
			 */
			requsetDistrReturnDtlSaveOrUpdateVO.setUnitPrice(distrInReturnOutAddFormDetailVO.getUnitPrice());

			/**
			 * 数量
			 */
			requsetDistrReturnDtlSaveOrUpdateVO.setQuantity(distrInReturnOutAddFormDetailVO.getQuantity());

			/**
			 * 行号
			 */
			requsetDistrReturnDtlSaveOrUpdateVO.setLineNum(distrInReturnOutAddFormDetailVO.getLineNum());

			/**
			 * 备注
			 */
			requsetDistrReturnDtlSaveOrUpdateVO.setRemark(distrInReturnOutAddFormDetailVO.getRemark());

			/**
			 * 行折扣（%）
			 */
			requsetDistrReturnDtlSaveOrUpdateVO.setLineDiscount(distrInReturnOutAddFormDetailVO.getLineDiscount());

			/**
			 * 税率ID
			 */
			requsetDistrReturnDtlSaveOrUpdateVO.setTaxRateId(distrInReturnOutAddFormDetailVO.getTaxRateId());

			requsetDistrReturnDtlSaveOrUpdateVO.setBaseOrderDtlId(distrInReturnOutAddFormDetailVO.getBaseOrderDtlId());

			return requsetDistrReturnDtlSaveOrUpdateVO;
		}).collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBaseOrderDtlId() {
		return baseOrderDtlId;
	}

	public void setBaseOrderDtlId(Long baseOrderDtlId) {
		this.baseOrderDtlId = baseOrderDtlId;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public Long getLotId() {
		return lotId;
	}

	public void setLotId(Long lotId) {
		this.lotId = lotId;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public Integer getLineNum() {
		return lineNum;
	}

	public void setLineNum(Integer lineNum) {
		this.lineNum = lineNum;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark==null?null:remark;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	
}