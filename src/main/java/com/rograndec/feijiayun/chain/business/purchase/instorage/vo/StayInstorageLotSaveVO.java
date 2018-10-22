package com.rograndec.feijiayun.chain.business.purchase.instorage.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "StayInstorageLotSaveVO", description = "采购管理-采购入库-验收批号明细保存对象")
public class StayInstorageLotSaveVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(required = true, value = "验收批号明细ID")
    private Long checkLotId;
	
	@ApiModelProperty(required = true, value = "入库类型0-合格品，1不合格品")
	private Integer type;
	
	@ApiModelProperty(required = true, value = "货位ID")
    private Long shelfId;
	
	
	public Long getCheckLotId() {
		return checkLotId;
	}

	public void setCheckLotId(Long checkLotId) {
		this.checkLotId = checkLotId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getShelfId() {
		return shelfId;
	}

	public void setShelfId(Long shelfId) {
		this.shelfId = shelfId;
	}

	
}
