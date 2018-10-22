package com.rograndec.feijiayun.chain.business.storage.goodshandle.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

 /**
 * 
 * @ClassName: GoodsHandleSaveOrupdateVO
 * @Description:  储存管理-商品处理-药品处理-Rest接口
 * @author zhengbin.jin
 * @version 1.0 
 * @date 2017-09-27 17:27:38
 */
@ApiModel(value = "GoodsHandleSaveOrupdateVO", description = "储存管理-商品处理-药品处理")
public class GoodsHandleSaveOrupdateVO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	 /**
	  * 锁定日期
	  */
	 @NotNull(message = "锁定日期不能为空!")
	 @ApiModelProperty(required = true, value = "锁定日期")
	 private String handleDateStr;

	 /**
	  * 锁定人员ID
	  */
	 @NotNull(message = "锁定人员ID不能为空!")
	 @ApiModelProperty(required = true, value = "锁定人员ID")
	 private Long handleManId;

	 /**
	  * 锁定原因（0-疑似质量问题；1-疑似伪劣商品；2-药品养护问题商品；3-陈列检查问题商品）
	  */
	 @NotNull(message = "处理结果（0-解除锁定；1-移动到不合格品货位）不能为空!")
	 @ApiModelProperty(required = true, value = "处理结果（0-解除锁定；1-移动到不合格品货位）")
	 private Integer handleResult;

	 /**
	  * 备注
	  */
	 @ApiModelProperty(required = false, value = "备注")
	 private String remark;
	 /*
     * 商品列表
     */
	 @NotNull(message = "商品列表不能为空")
	 @ApiModelProperty(required = true,value = "商品列表")
	 List<GoodsHandleDetailSaveOrupdateVO> goodsHandleDetailSaveOrupdateVOList;

	 /**
	  * 基础单据ID
	  */
	 @ApiModelProperty(value = "基础单据ID")
	 private Long baseOrderId;


	 /**
	  * 基础单据类型
	  */
	 @ApiModelProperty(value = "基础单据类型")
	 private Integer baseOrderType;

	 /**
	  * 基础单据编码
	  */
	 @ApiModelProperty(value = "基础单据编码")
	 private String baseOrderCode;

	 /**
	  * 基础单据日期
	  */
	 @ApiModelProperty(value = "基础单据日期")
	 private Date baseOrderDate;

	 public Integer getBaseOrderType() {
		 return baseOrderType;
	 }

	 public void setBaseOrderType(Integer baseOrderType) {
		 this.baseOrderType = baseOrderType;
	 }

	 public String getBaseOrderCode() {
		 return baseOrderCode;
	 }

	 public void setBaseOrderCode(String baseOrderCode) {
		 this.baseOrderCode = baseOrderCode;
	 }

	 public Date getBaseOrderDate() {
		 return baseOrderDate;
	 }

	 public void setBaseOrderDate(Date baseOrderDate) {
		 this.baseOrderDate = baseOrderDate;
	 }

	 public String getHandleDateStr() {
		 return handleDateStr;
	 }

	 public void setHandleDateStr(String handleDateStr) {
		 this.handleDateStr = handleDateStr;
	 }

	 public Long getHandleManId() {
		 return handleManId;
	 }

	 public void setHandleManId(Long handleManId) {
		 this.handleManId = handleManId;
	 }

	 public Integer getHandleResult() {
		 return handleResult;
	 }

	 public void setHandleResult(Integer handleResult) {
		 this.handleResult = handleResult;
	 }

	 public String getRemark() {
		 return remark;
	 }

	 public void setRemark(String remark) {
		 this.remark = remark;
	 }

	 public List<GoodsHandleDetailSaveOrupdateVO> getGoodsHandleDetailSaveOrupdateVOList() {
		 return goodsHandleDetailSaveOrupdateVOList;
	 }

	 public void setGoodsHandleDetailSaveOrupdateVOList(List<GoodsHandleDetailSaveOrupdateVO> goodsHandleDetailSaveOrupdateVOList) {
		 this.goodsHandleDetailSaveOrupdateVOList = goodsHandleDetailSaveOrupdateVOList;
	 }

	 public Long getBaseOrderId() {
		 return baseOrderId;
	 }

	 public void setBaseOrderId(Long baseOrderId) {
		 this.baseOrderId = baseOrderId;
	 }
 }