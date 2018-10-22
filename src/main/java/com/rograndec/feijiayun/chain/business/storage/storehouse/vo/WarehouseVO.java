package com.rograndec.feijiayun.chain.business.storage.storehouse.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by DD on 2017/9/26.
 */
public class WarehouseVO {

	/**
	 * 仓库的id
	 */
	 @ApiModelProperty(value = "仓库的id")
	private Long warehouseId;
	/**
	 * 仓库的名称
	 */
	 @ApiModelProperty(value = "仓库的名称")
	private String warehouseName;
	public Long getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	
	
	

}