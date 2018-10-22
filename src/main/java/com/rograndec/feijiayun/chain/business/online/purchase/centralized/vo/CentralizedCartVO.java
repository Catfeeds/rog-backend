package com.rograndec.feijiayun.chain.business.online.purchase.centralized.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//购物车
public class CentralizedCartVO implements Serializable{
	
	private static final long serialVersionUID = -467805545810944732L;
	//商品总数量
	@ApiModelProperty(value = "商品总数量")
	private Integer selectQuantity = new Integer(0);
	// 当前应付总价格
	@ApiModelProperty(value = "当前应付总价格")
	private BigDecimal totalAmount = new BigDecimal(0.00); 
	
	//外层key存储供应商ID,里层key存储商品ID，便于查找
	private Map<Long, Map<Long, Long>> map = new HashMap<Long, Map<Long, Long>>();

	@ApiModelProperty(value = "集采标志")
	private Integer style = 1;
	
	//商业公司集合
	@ApiModelProperty(value = "商业公司集合")
	private List<CentralizedCartBusinessVO> supplierList = new ArrayList<CentralizedCartBusinessVO>();

	public Integer getSelectQuantity() {
		return selectQuantity;
	}

	public void setSelectQuantity(Integer selectQuantity) {
		this.selectQuantity = selectQuantity;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<CentralizedCartBusinessVO> getSupplierList() {
		return supplierList;
	}

	public void setSupplierList(List<CentralizedCartBusinessVO> supplierList) {
		this.supplierList = supplierList;
	}

	public Map<Long, Map<Long, Long>> getMap() {
		return map;
	}

	public void setMap(Map<Long, Map<Long, Long>> map) {
		this.map = map;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getStyle() {
		return style;
	}

	public void setStyle(Integer style) {
		this.style = style;
	}
}
