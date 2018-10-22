package com.rograndec.feijiayun.chain.inf.mph.vo;

import java.io.Serializable;

public class OrderDetail implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author liuqun
	 * @date 2017年11月20日 下午3:20:30 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 3847616977002275233L;
	
	//订单明细ID
	private Long orderDetailId;
	
	//mph商品ID
	private Long mphGoodsId;
	
	//收货数量
	private Double receiveQuantity;

	public Long getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Long orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public Long getMphGoodsId() {
		return mphGoodsId;
	}

	public void setMphGoodsId(Long mphGoodsId) {
		this.mphGoodsId = mphGoodsId;
	}

	public Double getReceiveQuantity() {
		return receiveQuantity;
	}

	public void setReceiveQuantity(Double receiveQuantity) {
		this.receiveQuantity = receiveQuantity;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
