package com.rograndec.feijiayun.chain.business.storage.goodshandle.vo;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;

 /**
 * 
 * @ClassName: GoodsHandleShelfSaveOrupdateVO
 * @Description:  储存管理-商品处理-药品处理货位明细-Rest接口
 * @author zhengbin.jin
 * @version 1.0 
 * @date 2017-09-27 17:28:01
 */
@ApiModel(value = "GoodsHandleShelfSaveOrupdateVO", description = "储存管理-商品处理-药品处理货位明细")
public class GoodsHandleShelfSaveOrupdateVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * 批号ID
     */
	@NotNull(message="批号ID不能为空!")
	@ApiModelProperty(required = true, value = "批号ID")
	private Long lotId;
	
	/**
     * 货位ID
     */
	@NotNull(message="货位ID不能为空!")
	@ApiModelProperty(required = true, value = "货位ID")
	private Long shelfId;
	
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

	 @ApiModelProperty(value = "锁定单ID")
	 private Long lockId;

	 @ApiModelProperty(value = "锁定单明细ID")
	 private Long lockDtlId;

	 @ApiModelProperty(value = "锁定单货位明细ID")
	 private Long lockShelfId;

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

	 public Long getLockId() {
		 return lockId;
	 }

	 public void setLockId(Long lockId) {
		 this.lockId = lockId;
	 }

	 public Long getLockDtlId() {
		 return lockDtlId;
	 }

	 public void setLockDtlId(Long lockDtlId) {
		 this.lockDtlId = lockDtlId;
	 }

	 public Long getLockShelfId() {
		 return lockShelfId;
	 }

	 public void setLockShelfId(Long lockShelfId) {
		 this.lockShelfId = lockShelfId;
	 }
 }