package com.rograndec.feijiayun.chain.inf.mph;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.rograndec.feijiayun.chain.utils.http.HttpClientUtil;

/**
 * @ClassName: CancelSaasOrder   
 * @Description: TODO(描述该类做什么)
 * @author liuqun
 * @version 1.0 
 * @date 2017年11月20日 下午3:06:35
 */
@Component
public class CancelSaasOrder {

	private final static Logger logger = LoggerFactory.getLogger(CancelSaasOrder.class);
	
	@Value("${mph.impl.url}")
	private String mphUrl;
	
	/**
	 * @Description: TODO(调用取消mph订单接口)
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年11月20日 下午3:06:43 
	 * @param rgtUserId 融贯通Userid
	 * @param orderCode mph订单号
	 * @return 
	 * @return String
	 */
	public String getOrderGoodsLatestInfoByOid(Long rgtUserId, String orderCode){
		StringBuffer buf = new StringBuffer();
		buf.append("userId=").append(rgtUserId).append("&").append("osns=").append(orderCode);
		String sendData = buf.toString();
		
		String mphResult = "";
		logger.info("will send cancelOrder4SaasCP.html order info to mph:" + sendData);
		try {
			mphResult = HttpClientUtil.sendPostRequest(mphUrl + "cancelOrder4SaasCP.html?", sendData, true);
		} catch (Exception e) {
			logger.error("调用取消mph订单接口失败！", e);
			logger.error("调用取消mph订单接口失败！请求数据："+sendData);
			logger.error("调用取消mph订单接口失败！返回数据："+mphResult);
			e.printStackTrace();
		}
		logger.info("get cancelOrder4SaasCP.html result str from mph:" + mphResult);
		
		return mphResult;
	}
}
