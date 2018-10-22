package com.rograndec.feijiayun.chain.inf.mph;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rograndec.feijiayun.chain.inf.mph.vo.OrderDetail;
import com.rograndec.feijiayun.chain.utils.http.HttpClientUtil;

@Component
public class AcceptSaasOrder {

	private final static Logger logger = LoggerFactory.getLogger(AcceptSaasOrder.class);
	
	@Value("${mph.impl.url}")
	private String mphUrl;
	
	/**
	 * @Description: TODO(调用mph确认收货接口)
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年11月20日 下午3:18:01 
	 * @param rgtUserId 融贯通userId
	 * @param rgtEnterpriseId 融贯通企业ID
	 * @param orderId 订单ID
	 * @param mphSupplierId mph供应商ID
	 * @param orderCode 订单编码
	 * @param orderDetail 订单明细
	 * @param enterpriseId 企业ID
	 * @return 
	 * @return String
	 */
	public String acceptSaasOrder(Long rgtUserId, Long rgtEnterpriseId, Long orderId, 
			Long mphSupplierId, String orderCode, List<OrderDetail> orderDetail, Long enterpriseId){
		
		JSONObject orderMethod = new JSONObject();
		orderMethod.put("uId", rgtUserId);
		orderMethod.put("eId", rgtEnterpriseId);
		orderMethod.put("orderId", orderId);
		orderMethod.put("oSellerId", mphSupplierId);//供应商id;
		orderMethod.put("orderCode", orderCode);
		
		JSONArray odJsonArray = new JSONArray();
		JSONObject json_object = null;
		if(orderDetail != null){
			for (OrderDetail object : orderDetail) {
				json_object = new JSONObject();
				json_object.put("odId", object.getOrderDetailId());
				json_object.put("gId", object.getMphGoodsId());
				json_object.put("receiveQuantity", object.getReceiveQuantity());
				odJsonArray.add(json_object);
			}
		}
		
		orderMethod.put("orderDetail", odJsonArray);
		
		StringBuffer buf = new StringBuffer();
		buf.append("dataJson=").append(orderMethod.toString());
		
		String mphResult = "";
		logger.info("will send saas/AcceptOrder.json order info to mph:" + buf);
		try {
			mphResult = HttpClientUtil.sendPostRequest(mphUrl + "saas/AcceptOrder.json?", buf.toString(), true);
		} catch (Exception e) {
			logger.error("调用mph确认收货接口失败！", e);
			logger.error("调用mph确认收货接口失败！请求数据："+buf.toString());
			logger.error("调用mph确认收货接口失败！返回数据："+mphResult);
			e.printStackTrace();
		}
		logger.info("get saas/AcceptOrder.json result str from mph:" + mphResult);
		
		return mphResult;
	}

	
}
