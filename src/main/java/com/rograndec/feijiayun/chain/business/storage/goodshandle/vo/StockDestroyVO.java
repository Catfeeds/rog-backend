package com.rograndec.feijiayun.chain.business.storage.goodshandle.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品列表
 * @author 孙帮祥
 * */
import io.swagger.annotations.ApiModelProperty;

public class StockDestroyVO implements Serializable{
	@ApiModelProperty(value = "货位ID")
	private Long shelfId;
	@ApiModelProperty(value = "货位名称")
	private String shelfName;
	@ApiModelProperty(value = "数量")
	private String quantity;
	/**
	 * :0-合格品区,1-待处理区,2-不合格品区
	 * */
	@ApiModelProperty(value = "质量状况描述")
	private String shelfStatusDesc;
	@ApiModelProperty(value = "批号ID")
	private String lotId;
	@ApiModelProperty(value = "批号")
	private String lotNumber;
	@ApiModelProperty(value = "生产日期")
	private Date productDate;
	@ApiModelProperty(value = "有效期")
	private Date validDate;
	@ApiModelProperty(value = "库存可用数量")
	private BigDecimal usableQuantity;

	@ApiModelProperty(value = "锁定数量")
	private BigDecimal lockQuantity;

	public Long getShelfId() {
		return shelfId;
	}
	public void setShelfId(Long shelfId) {
		this.shelfId = shelfId;
	}
	public String getShelfName() {
		return shelfName;
	}
	public void setShelfName(String shelfName) {
		this.shelfName = shelfName;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getShelfStatusDesc() {
		return shelfStatusDesc;
	}
	public void setShelfStatusDesc(String shelfStatusDesc) {
		this.shelfStatusDesc = shelfStatusDesc;
	}
	public String getLotNumber() {
		return lotNumber;
	}
	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}
	public Date getProductDate() {
		return productDate;
	}
	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}
	public Date getValidDate() {
		return validDate;
	}
	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}
	public BigDecimal getUsableQuantity() {
		return usableQuantity;
	}
	public void setUsableQuantity(BigDecimal usableQuantity) {
		this.usableQuantity = usableQuantity;
	}
	public String getLotId() {
		return lotId;
	}
	public void setLotId(String lotId) {
		this.lotId = lotId;
	}


	public BigDecimal getLockQuantity() {
		return lockQuantity;
	}

	public void setLockQuantity(BigDecimal lockQuantity) {
		this.lockQuantity = lockQuantity;
	}
}
