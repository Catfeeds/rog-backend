package com.rograndec.feijiayun.chain.business.online.purchase.smart.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * The persistent class for the saas_smart_sourcing_cart database table.
 * 
 */
public class SmartSourcingCartVO implements Serializable {
	
	/**
	 * @Description: TODO(描述funcion功能)
	 * author liuqun
	 * @date 2017年11月20日 下午7:55:16 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = -298595222807715857L;
	
	//商品已选择总数量
	@ApiModelProperty(value = "商品已选择总数量")
	private Integer selectQuantity = new Integer(0);
	// 当前应付总价格
	@ApiModelProperty(value = "当前应付总价格")
	private BigDecimal totalAmount = new BigDecimal(0.00);

	//0代表智采 1-代表集采
	@ApiModelProperty(value = "0代表智采 1-代表集采")
	private Integer style = 0;

	//全网比价是否可用0-不可用，1-可用
	@ApiModelProperty(value = "全网比价是否可用0-不可用，1-可用")
	private Integer wholeNetworkParity = 1;

	private List<SmartSourcingSupplierVO> supplierList = new ArrayList<>();
	
	//外层key存储供应商ID,里层key存储商品ID，便于查找
	private Map<Long, Map<Long, Long>> map = new HashMap<Long, Map<Long, Long>>();

	public List<SmartSourcingSupplierVO> getSupplierList() {
		return supplierList;
	}

	public void setSupplierList(List<SmartSourcingSupplierVO> supplierList) {
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

	public Integer getStyle() {
		return style;
	}

	public void setStyle(Integer style) {
		this.style = style;
	}

	public Integer getWholeNetworkParity() {
		return wholeNetworkParity;
	}

	public void setWholeNetworkParity(Integer wholeNetworkParity) {
		this.wholeNetworkParity = wholeNetworkParity;
	}
}