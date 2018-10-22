package com.rograndec.feijiayun.chain.inf.mph;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.rograndec.feijiayun.chain.utils.http.HttpClientUtil;

@Component
public class GetSaasOrderDetail {

private final static Logger logger = LoggerFactory.getLogger(GetSaasOrderDetail.class);
	
	@Value("${mph.impl.url}")
	private String mphImplUrl;
	
	/**
	 * @Description: TODO(获取订单明细数据)
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年11月20日 下午2:54:18 
	 * @param rgtUserId 融贯通用户ID
	 * @param rgtEnterpriseId 融贯通企业ID
	 * @param orderId 订单ID 
	 * @param orderCode 订单编码
	 * @return 
	 * @return String
	 */
	public String GetSaasOrderPageByParam(Long rgtUserId, Long rgtEnterpriseId, Long orderId, String orderCode){
		
		JSONObject orderMethod = new JSONObject();
		orderMethod.put("uId", rgtUserId);
		orderMethod.put("eId", rgtEnterpriseId);
		orderMethod.put("orderId", orderId);
		orderMethod.put("orderCode", orderCode);
		StringBuffer buf = new StringBuffer();
		buf.append("dataJson=").append(orderMethod.toString());

		logger.info("will send saas/orderDetail.json to mph:" + orderMethod);
		String mphResult = "";
		try {
			mphResult = HttpClientUtil.sendPostRequest(mphImplUrl + "saas/orderDetail.json?", buf.toString(), true);
		} catch (Exception e) {
			logger.error("调用获取MPH订单明细数据接口失败！", e);
			logger.error("调用获取MPH订单明细数据接口失败！请求数据："+buf.toString());
			logger.error("调用获取MPH订单明细数据接口失败！返回数据："+mphResult);
			e.printStackTrace();
		}
		logger.info("get result saas/orderDetail.json from mph:" + mphResult);
		
		return mphResult;
	}
}
