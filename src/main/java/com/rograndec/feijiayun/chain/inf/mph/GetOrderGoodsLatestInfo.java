package com.rograndec.feijiayun.chain.inf.mph;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.rograndec.feijiayun.chain.utils.http.HttpClientUtil;

/**
 * @ClassName: getOrderGoodsLatestInfo   
 * @Description: TODO根据订单ID获取订单商品最新数据接口，用于再次购买
 * @author liuqun
 * @version 1.0 
 * @date 2017年11月4日 上午11:16:45
 */

@Component
public class GetOrderGoodsLatestInfo {

	private final static Logger logger = LoggerFactory.getLogger(GetOrderGoodsLatestInfo.class);
	
	@Value("${mph.impl.url}")
	private String mphUrl;
	
	public String getOrderGoodsLatestInfoByOid(Long rgtUserId, Long oId){
		StringBuffer buf = new StringBuffer();
		buf.append("uid=").append(rgtUserId).append("&").append("oid=").append(oId);
		String sendData = buf.toString();
		
		String mphResult = "";
		logger.info("will send buyAgain order info to mph:" + sendData);
		try {
			mphResult = HttpClientUtil.sendPostRequest(mphUrl + "saas/buyAgain?", sendData, true);
		} catch (Exception e) {
			logger.error("调用根据订单ID获取订单商品最新数据接口失败！", e);
			logger.error("调用根据订单ID获取订单商品最新数据接口失败！请求数据："+sendData);
			logger.error("调用根据订单ID获取订单商品最新数据接口失败！返回数据："+mphResult);
			e.printStackTrace();
		}
		logger.info("get cancel buyAgain result str from mph:" + mphResult);
		
		return mphResult;
	}
}
