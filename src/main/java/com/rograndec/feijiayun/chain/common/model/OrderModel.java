package com.rograndec.feijiayun.chain.common.model;

import java.util.List;

import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.vo.OrderDtlVO;
import com.rograndec.feijiayun.chain.common.vo.OrderVO;

/**
 * 
 * @ClassName: OrderModel  
 * @Description: 单据模型
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年8月26日 上午10:38:27  
 *
 */
public class OrderModel {

	private OrderRule orderRule;
	private OrderVO order;
	private List<OrderDtlVO> orderDtlList;
	
	public OrderRule getOrderRule() {
		return orderRule;
	}
	public void setOrderRule(OrderRule orderRule) {
		this.orderRule = orderRule;
	}
	public OrderVO getOrder() {
		return order;
	}
	public void setOrder(OrderVO order) {
		this.order = order;
	}
	public List<OrderDtlVO> getOrderDtlList() {
		return orderDtlList;
	}
	public void setOrderDtlList(List<OrderDtlVO> orderDtlList) {
		this.orderDtlList = orderDtlList;
	}
	
}
