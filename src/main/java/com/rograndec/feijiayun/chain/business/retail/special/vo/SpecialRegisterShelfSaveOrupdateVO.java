package com.rograndec.feijiayun.chain.business.retail.special.vo;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;

 /**
 * 
 * @ClassName: SpecialRegisterShelfSaveOrupdateVO
 * @Description:  零售管理-专管登记单货位明细-Rest接口
 * @author zhengbin.jin
 * @version 1.0 
 * @date 2017-09-22 16:34:35
 */
@ApiModel(value = "SpecialRegisterShelfSaveOrupdateVO", description = "零售管理-专管登记单货位明细")
public class SpecialRegisterShelfSaveOrupdateVO implements Serializable {
	
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
 }