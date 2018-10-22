package com.rograndec.feijiayun.chain.inf.pos.sale.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class POSReturnSaleDetailVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author liuqun
	 * @date 2017年10月8日 下午1:25:19 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * 基础单据明细ID
     */
    @ApiModelProperty(value = "在线退货时使用，基础单据货位明细ID(包含销售单明细货位ID)")
    private Long baseOrderShelfId;
    
    /**
     * 基础单据ID
     */
   /* @ApiModelProperty(value = "离线退货时使用，基础单据编码(单品退货时，包含销售单，一次只能退一单)")
    private String baseOrderCode;*/
    
    /**
     * 营业员ID
     */
    @ApiModelProperty(value = "营业员ID")
    private Long clerkId;
    
    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID")
    private Long goodsId;
    
    /**
     * 单剂数量
     */
    @ApiModelProperty(value = "单剂数量")
    private BigDecimal singleDose;
    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private BigDecimal quantity;

    /**
     * 单价
     */
    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;

    /**
     * 行折扣（%）
     */
    @ApiModelProperty(value = "行折扣（%）")
    private BigDecimal lineDiscount;
    
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    
    /**
     * 批号ID
     */
    @ApiModelProperty(value = "批号ID")
    private Long lotId;
    
    /**
     * 货位ID
     */
    @ApiModelProperty(value = "货位ID")
    private Long shelfId;
    
    /**
     * 行号
     */
    @ApiModelProperty(value = "行号")
    private Integer lineNum;
    
    /**
     * 行号
     */
    @ApiModelProperty(value = "原单据中行号")
    private Integer oldLineNum;
    
    
    @ApiModelProperty(value = "扣行积分")
    private BigDecimal thisIntegral;
    

	public Long getBaseOrderShelfId() {
		return baseOrderShelfId;
	}

	public void setBaseOrderShelfId(Long baseOrderShelfId) {
		this.baseOrderShelfId = baseOrderShelfId;
	}

	public Long getClerkId() {
		return clerkId;
	}


	public void setClerkId(Long clerkId) {
		this.clerkId = clerkId;
	}


	public Long getGoodsId() {
		return goodsId;
	}


	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}


	public BigDecimal getSingleDose() {
		return singleDose;
	}


	public void setSingleDose(BigDecimal singleDose) {
		this.singleDose = singleDose;
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


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public Long getLotId() {
		return lotId;
	}


	public void setLotId(Long lotId) {
		this.lotId = lotId;
	}


	public Long getShelfId() {
		return shelfId;
	}


	public void setShelfId(Long shelfId) {
		this.shelfId = shelfId;
	}

	public Integer getLineNum() {
		return lineNum;
	}

	public void setLineNum(Integer lineNum) {
		this.lineNum = lineNum;
	}

	public Integer getOldLineNum() {
		return oldLineNum;
	}

	public void setOldLineNum(Integer oldLineNum) {
		this.oldLineNum = oldLineNum;
	}

	/*public String getBaseOrderCode() {
		return baseOrderCode;
	}

	public void setBaseOrderCode(String baseOrderCode) {
		this.baseOrderCode = baseOrderCode;
	}*/

	public BigDecimal getThisIntegral() {
		return thisIntegral;
	}

	public void setThisIntegral(BigDecimal thisIntegral) {
		this.thisIntegral = thisIntegral;
	}
	
}
