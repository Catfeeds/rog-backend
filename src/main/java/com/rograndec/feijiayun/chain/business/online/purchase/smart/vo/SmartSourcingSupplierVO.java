package com.rograndec.feijiayun.chain.business.online.purchase.smart.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the saas_smart_sourcing_cart database table.
 * 
 */
public class SmartSourcingSupplierVO implements Serializable {

	/**
	 * @Description: TODO(描述funcion功能)
	 * author liuqun
	 * @date 2017年11月20日 下午7:46:26 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = -8599219618734490912L;
	

	//mph 供应商id
	@ApiModelProperty(value = "mph 供应商id")
	private Long mphSupplierId;

	//mph 供应商名称
	@ApiModelProperty(value = "mph 供应商名称")
	private String mphSupplierName;
	
	//药店ID
	@ApiModelProperty(value = "药店ID")
	private Long entrepriseId;

	//MPH企业起采金额限制
	@ApiModelProperty(value = "MPH企业起采金额限制")
	private BigDecimal matchingAmount;
	
	//开票金额
	@ApiModelProperty(value = "开票金额")
	private BigDecimal billingAmount = new BigDecimal(0.00);

	//是否选中 默认false
	@ApiModelProperty(value = "是否选中 默认false")
	private Boolean checked = false;
	
	private List<SmartSourcingGoodsVO> goodsList = new ArrayList<SmartSourcingGoodsVO>();

	public List<SmartSourcingGoodsVO> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<SmartSourcingGoodsVO> goodsList) {
		this.goodsList = goodsList;
	}

	public Long getMphSupplierId() {
		return mphSupplierId;
	}

	public void setMphSupplierId(Long mphSupplierId) {
		this.mphSupplierId = mphSupplierId;
	}

	public String getMphSupplierName() {
		return mphSupplierName;
	}

	public void setMphSupplierName(String mphSupplierName) {
		this.mphSupplierName = mphSupplierName;
	}

	public Long getEntrepriseId() {
		return entrepriseId;
	}

	public void setEntrepriseId(Long entrepriseId) {
		this.entrepriseId = entrepriseId;
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

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}