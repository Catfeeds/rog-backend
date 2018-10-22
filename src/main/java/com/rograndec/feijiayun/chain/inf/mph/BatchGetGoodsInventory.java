package com.rograndec.feijiayun.chain.inf.mph;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.rograndec.feijiayun.chain.utils.http.HttpClientUtil;
/**
 * @ClassName: batchGetGoodsInventory   
 * @Description: TODO批量获取商品最新库存
 * @author liuqun
 * @version 1.0 
 * @date 2017年11月4日 上午11:18:27
 */

@Component
public class BatchGetGoodsInventory {

	
	private final static Logger logger = LoggerFactory.getLogger(BatchGetGoodsInventory.class);
	
	@Value("${mph.apirograndec.url}")
	private String mphApiUrl;
	
	public String batchGetGoodsInventoryGid(String uId, List<Long> gIds){
		JSONObject orderMethod = new JSONObject();
		orderMethod.put("uid", uId);
		orderMethod.put("gids", gIds);
		JSONObject bodyMethod = new JSONObject();
		bodyMethod.put("body", orderMethod);
		bodyMethod.put("head", getHead());
		bodyMethod.put("mac", "");
		StringBuffer buf = new StringBuffer();
		buf.append("dataJson=").append(bodyMethod.toString());
		String sendData = buf.toString();
		
		String mphResult = "";
		logger.info("will send getStockNum info to mph:" + sendData);
		try {
			mphResult = HttpClientUtil.sendPostRequest(mphApiUrl + "item/getStockNum?", sendData, true);
		} catch (Exception e) {
			logger.error("调用批量获取商品最新库存接口失败！", e);
			logger.error("调用批量获取商品最新库存接口失败！请求数据："+sendData);
			logger.error("调用批量获取商品最新库存接口失败！返回数据："+mphResult);
			e.printStackTrace();
		}
		logger.info("get getStockNum result str from mph:" + mphResult);
		
//		mphResult = "{\"code\":0,\"msg\":\"错误\",\"result\":{\"stockList\":[{\"gid\":1,\"buyNumber\":20,"
//				+ "\"stockStr\":\"abc\",\"stockType\":\"1\"},{\"gid\":2,\"buyNumber\":20,\"stockStr\":\"def\","
//				+ "\"stockType\":\"2\"}]}}";
		
		return mphResult;
	}

	private Object getHead() {
		JSONObject orderMethod = new JSONObject();
		orderMethod.put("appId", 8);
		orderMethod.put("appSecret", "175906d1a85aeff2eeb5ad7732f6ba98");
		orderMethod.put("appSys", "2");
		orderMethod.put("appType", "2");
		orderMethod.put("appVersion", "5.3.4.01");
		orderMethod.put("imei", "864387022640706");
		orderMethod.put("method", "142005");
		orderMethod.put("serialNumber", "120319181305000011");
		orderMethod.put("sysVersion", "6.0");
		orderMethod.put("terminalstate", "0");
		orderMethod.put("version", "1");
		orderMethod.put("macAddress", "24:09:95:37:07:67");
		orderMethod.put("sourceCode", "wdyy-app");
		return orderMethod;
	}
	
}
