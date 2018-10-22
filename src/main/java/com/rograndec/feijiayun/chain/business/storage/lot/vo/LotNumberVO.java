package com.rograndec.feijiayun.chain.business.storage.lot.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品列表
 * @author 孙帮祥
 * */
import io.swagger.annotations.ApiModelProperty;

public class LotNumberVO implements Serializable{
	@ApiModelProperty(value = "批号ID")
	private Long lotId;
	@ApiModelProperty(value = "批号")
	private String lotNum;
    @ApiModelProperty(value="生产日期")
    private Date productDate;
    @ApiModelProperty(value="有效期")
    private Date validUntil;
	public Long getLotId() {
		return lotId;
	}
	public void setLotId(Long lotId) {
		this.lotId = lotId;
	}
	public String getLotNum() {
		return lotNum;
	}
	public void setLotNum(String lotNum) {
		this.lotNum = lotNum;
	}
	public Date getProductDate() {
		return productDate;
	}
	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}
	public Date getValidUntil() {
		return validUntil;
	}
	public void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
	}


}
