package com.rograndec.feijiayun.chain.business.storage.goodshandle.vo;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;

 /**
 * 
 * @ClassName: GoodsLockShelfSaveOrupdateVO
 * @Description:  储存管理-商品处理-药品锁定货位明细-Rest接口
 * @author zhengbin.jin
 * @version 1.0 
 * @date 2017-09-27 17:27:10
 */
@ApiModel(value = "GoodsLockShelfSaveOrupdateVO", description = "储存管理-商品处理-药品锁定货位明细")
public class GoodsLockShelfSaveOrupdateVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
     * 货位ID
     */
	@NotNull(message="货位ID不能为空!")
	@ApiModelProperty(required = true, value = "货位ID")
	private Long shelfId;
	/**
     * 批次ID
     */
	@NotNull(message="批次ID不能为空!")
	@ApiModelProperty(required = true, value = "批次ID")
	private Long lotId;

	/**
     * 数量
     */
	@NotNull(message="数量不能为空!")
	@ApiModelProperty(required = true, value = "数量")
	private BigDecimal quantity;
	
	/**
     * 行号
     */
	@NotNull(message="行号不能为空!")
	@ApiModelProperty(required = true, value = "行号")
	private Integer lineNum;
	

	/**
     * 备注
     */
	@ApiModelProperty(required = false, value = "备注")
	private String remark;

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

	 public String getRemark() {
		 return remark;
	 }

	 public void setRemark(String remark) {
		 this.remark = remark;
	 }
 }