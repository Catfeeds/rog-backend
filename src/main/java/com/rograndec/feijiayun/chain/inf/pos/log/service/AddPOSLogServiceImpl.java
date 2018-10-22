package com.rograndec.feijiayun.chain.inf.pos.log.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.google.gson.GsonBuilder;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.inf.pos.base.entity.POSHeaders;
import com.rograndec.feijiayun.chain.inf.pos.log.dao.POSClientLogMapper;
import com.rograndec.feijiayun.chain.inf.pos.log.entity.POSClientLog;
import com.rograndec.feijiayun.chain.inf.pos.log.entity.POSCountDataLog;
import com.rograndec.feijiayun.chain.utils.date.DateStyle;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.mq.core.MQTemplate;

/**
 * 
 * @ClassName: AddPOSLogServiceImpl   
 * @Description: POS 记录日志
 * @author yuting.li
 * @version 1.0 
 * @date 2017年10月8日 下午7:00:42
 */
@Service
public class AddPOSLogServiceImpl {
	
	private static Logger logger = LoggerFactory.getLogger(AddPOSLogServiceImpl.class);
	
	private ExecutorService fixedThread = Executors.newFixedThreadPool(2);
	
	@Autowired
    private MQTemplate mqTemplate;
	
	
	@SuppressWarnings("unused")
	@Autowired
	private POSClientLogMapper pOSClientLogMapper;
	
	public void savePosLog(HttpServletRequest request) throws Exception {
		
		POSHeaders posHeaders = getHeaders(request);
		if(null != posHeaders) {
			try {
				POSClientLog posLog = new POSClientLog();
				posLog.setClientType(0);
				posLog.setPcMac(posHeaders.getMac());
				posLog.setEnterpriseId(posHeaders.getEnterpriseid() == null ? 0 : Long.valueOf(posHeaders.getEnterpriseid()));
				posLog.setContent(setContent(request));
				posLog.setLogDate(new Date());
				posLog.setUploadDate(new Date());
				/*fixedThread.execute(()->{
					pOSClientLogMapper.insertSelective(posLog);
				});*/
				fixedThread.submit(()->{
					mqTemplate.sendOneWay("saaspos:chain", posLog);
				});
			} catch (Exception e) {
				logger.error("POS记录日志错误", e);
			}
		}
	}
	
	public void saveCountDataLog(Map<String, Object> param,UserVO userVO,Long count) {
		try {
			Long enterpriseId = userVO.getEnterpriseId();
			String userName = userVO.getUserName();
			String loginAccount = userVO.getLoginAccount();
			String countParam = param != null ? JSON.toJSONString(param) : "参数为空";
			String countData = countData(param, count);
			String logDate = DateUtils.DateToString(new Date(), DateStyle.YYYY_MM_DD_HH_MM_SS);
			POSCountDataLog posCountDataLog = new POSCountDataLog(enterpriseId, userName, loginAccount, countParam, countData, logDate);
			fixedThread.submit(()->{
				mqTemplate.sendOneWay("saaspos_countdata:chain", posCountDataLog);
			});
		} catch (Exception e) {
			logger.error("POS记录同步总数日志错误", e);
		}
	}
	
	private String countData(Map<String, Object> param,Long count) {
		JSONObject countData = new JSONObject();
		countData.put("tableName", param.get("tableName"));
		countData.put("countData", count);
		return countData.toJSONString();
	}
	
	@PreDestroy
	public void destory() {
		try {
			int waitTime = 500;  
			Thread.sleep(waitTime);
			fixedThread.shutdown();
			fixedThread.awaitTermination(waitTime, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			logger.error("POS记录日志线程关闭失败", e);
		}
	}  
	
	public POSHeaders getHeaders(HttpServletRequest request) {
		Enumeration<String> header = request.getHeaderNames();
		Map<String, String> hmap = Maps.newHashMap();
		while (header.hasMoreElements()) {
			String key = header.nextElement();
			hmap.put(key, request.getHeader(key));
		}
		JSON hjson = (JSON) JSON.toJSON(hmap);
		POSHeaders posHeaders = JSON.toJavaObject(hjson, POSHeaders.class);
		return posHeaders;
	}
	
	private String getParam(HttpServletRequest request) throws IOException {
		String line = null;
		BufferedReader reader = request.getReader();
		StringBuilder content = new StringBuilder();  
		while((line = reader.readLine()) != null) {
			content.append(line);  
		}
		reader.close();
		return content.toString();
	}
	
	private String setContent(HttpServletRequest request) throws Exception {
		POSHeaders posHeaders = getHeaders(request);
		String body = getParam(request);
		Map<String, Object> map = Maps.newHashMap();
		map.put("head", posHeaders);
		map.put("body", body.contains("{") ? JSON.parse(body) : body);
		map.put("uri", request.getRequestURI());
		return new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(map);
	}
}
