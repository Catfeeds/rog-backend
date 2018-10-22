package com.rograndec.feijiayun.chain.business.storage.goodshandle.vo;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;

 /**
 * 
 * @ClassName: GoodsHandleDetailSaveOrupdateVO
 * @Description:  储存管理-商品处理-药品处理明细-Rest接口
 * @author zhengbin.jin
 * @version 1.0 
 * @date 2017-09-27 17:27:49
 */
@ApiModel(value = "GoodsHandleDetailSaveOrupdateVO", description = "储存管理-商品处理-药品处理明细")
public class GoodsHandleDetailSaveOrupdateVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * 商品ID
     */
	@NotNull(message="商品ID不能为空!")
	@ApiModelProperty(required = true, value = "商品ID")
	private Long goodsId;
	
	/**
     * 行号
     */
	@NotNull(message="行号不能为空!")
	@ApiModelProperty(required = true, value = "行号")
	private Integer lineNum;

	 /**
	  * 基础单据明细ID
	  */
	 @ApiModelProperty(value = "基础单据明细ID")
	 private Long baseOrderDtlId;
	
	/**
     * 备注
     */
	@ApiModelProperty(required = false, value = "备注")
	private String remark;

	 @NotNull(message = "货位信息不能为空")
	 @ApiModelProperty(required = true,value = "货位信息")
	 private List<GoodsHandleShelfSaveOrupdateVO> goodsHandleShelfSaveOrupdateVOList;

	 public List<GoodsHandleShelfSaveOrupdateVO> getGoodsHandleShelfSaveOrupdateVOList() {
		 return goodsHandleShelfSaveOrupdateVOList;
	 }

	 public void setGoodsHandleShelfSaveOrupdateVOList(List<GoodsHandleShelfSaveOrupdateVO> goodsHandleShelfSaveOrupdateVOList) {
		 this.goodsHandleShelfSaveOrupdateVOList = goodsHandleShelfSaveOrupdateVOList;
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

	 public Integer getLineNum() {
		 return lineNum;
	 }

	 public void setLineNum(Integer lineNum) {
		 this.lineNum = lineNum;
	 }

	 public String getRemark() {
		 return remark;
	 }

	 public void setRemark(String remark) {
		 this.remark = remark;
	 }
 }