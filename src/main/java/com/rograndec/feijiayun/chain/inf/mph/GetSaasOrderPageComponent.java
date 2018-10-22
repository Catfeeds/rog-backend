package com.rograndec.feijiayun.chain.inf.mph;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.rograndec.feijiayun.chain.utils.http.HttpClientUtil;

@Component
public class GetSaasOrderPageComponent {

private final static Logger logger = LoggerFactory.getLogger(GetSaasOrderPageComponent.class);
	
	@Value("${mph.impl.url}")
	private String mphImplUrl;
	
	public String getMphUrl() {
		return mphImplUrl;
	}
	
	/**
	 * @Description: TODO(分页获取Mph中Saas订单)
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年11月20日 下午2:54:18 
	 * @param rgtUserId 融贯通用户ID
	 * @param rgtEnterpriseId 融贯通企业ID
	 * @param pageNo  
	 * @param pageSize
	 * @param search 页面搜索框
	 * @return 
	 * @return String
	 */
	public String getSaasOrderPageByParam(Long rgtUserId, Long rgtEnterpriseId, Integer pageNo, Integer pageSize,
			String search){
		
		JSONObject orderMethod = new JSONObject();
		orderMethod.put("uId", rgtUserId);
		orderMethod.put("eId", rgtEnterpriseId);
		orderMethod.put("pageNo", pageNo);
		orderMethod.put("pageSize", pageSize);
		orderMethod.put("search", search);
		StringBuffer buf = new StringBuffer();
		buf.append("dataJson=").append(orderMethod.toString());
		String sendData = buf.toString();

		logger.info("will send order info to mph:" + orderMethod);
		String mphResult = "";
		try {
			mphResult = HttpClientUtil.sendPostRequest(mphImplUrl + "saas/pageOrder.json?", sendData, true);
		} catch (Exception e) {
			logger.error("调用分页获取Mph中Saas订单接口失败！", e);
			logger.error("调用分页获取Mph中Saas订单接口失败！请求数据："+sendData);
			logger.error("调用分页获取Mph中Saas订单接口失败！返回数据："+mphResult);
			e.printStackTrace();
		}
		logger.info("get result str from mph:" + mphResult);
		
		return mphResult;
	}
}
