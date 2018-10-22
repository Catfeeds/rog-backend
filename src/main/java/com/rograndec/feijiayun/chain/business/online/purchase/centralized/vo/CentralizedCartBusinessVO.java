package com.rograndec.feijiayun.chain.business.online.purchase.centralized.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//购物车商业公司
public class CentralizedCartBusinessVO implements Serializable{
	
	private static final long serialVersionUID = -1196713322360627977L;
	//商业公司标准库Id
	@ApiModelProperty(value = "商业公司标准库Id")
	private Long mphSupplierId;
	//商业公司名称
	@ApiModelProperty(value = "商业公司名称")
	private String mphSupplierName;
	//起配金额
	@ApiModelProperty(value = "起配金额")
	private BigDecimal matchingAmount;
	//开票金额
	@ApiModelProperty(value = "开票金额")
	private BigDecimal billingAmount = new BigDecimal(0.00);
	//是否选中 默认false
	@ApiModelProperty(value = "是否选中 默认false")
	private Boolean checked = false;
	//商品集合
	@ApiModelProperty(value = "商品集合")
	private List<CentralizedCartGoodVO> goodsList = new ArrayList<CentralizedCartGoodVO>();
	public String getMphSupplierName() {
		return mphSupplierName;
	}
	public void setMphSupplierName(String mphSupplierName) {
		this.mphSupplierName = mphSupplierName;
	}
	public Long getMphSupplierId() {
		return mphSupplierId;
	}
	public void setMphSupplierId(Long mphSupplierId) {
		this.mphSupplierId = mphSupplierId;
	}
	public BigDecimal getMatchingAmount() {
		return matchingAmount;
	}
	public void setMatchingAmount(BigDecimal matchingAmount) {
		this.matchingAmount = matchingAmount;
	}
	public BigDecimal getBillingAmount() {
		return billingAmount;
	}
	public void setBillingAmount(BigDecimal billingAmount) {
		this.billingAmount = billingAmount;
	}
	public List<CentralizedCartGoodVO> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(List<CentralizedCartGoodVO> goodsList) {
		this.goodsList = goodsList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
}
