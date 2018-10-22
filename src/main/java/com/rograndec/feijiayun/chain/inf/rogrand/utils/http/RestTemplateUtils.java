package com.rograndec.feijiayun.chain.inf.rogrand.utils.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述：
 * Created by ST on 2017/10/31 14:15
 */

public class RestTemplateUtils {

    public static JSONObject post(String url,String param){
        //设置header
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-Type", "application/json; charset=UTF-8");

        //设置参数
        HttpEntity<String> httpEntity = new HttpEntity<String>(param, httpHeaders);

		//执行请求
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> resp = restTemplate.exchange(url, HttpMethod.POST,httpEntity, String.class);

		//获取返回的header
		List<String> val = resp.getHeaders().get("Set-Cookie");
//		System.out.println(val);

		//获得返回值
		String body = resp.getBody();
		return  JSON.parseObject(body);
    }


	public static JSONObject post2Rgt(String url,String param){

		JSONObject jsonObject = post(url,param);
		if(jsonObject == null){
			throw new BusinessException("111111","融贯通接口返回异常");
		}
		return jsonObject.getJSONObject("body");
	}


    public static void main(String[] args) {
////        String url = "http://192.168.20.3:9999/basic/warehouse/getWarehouseArea";
////        RequestEntity requestEntity = new RequestEntity();
////        RequestHeadEntity head = new RequestHeadEntity();
////        head.setAppId("appid");
////        head.setMethod("GET");
////        requestEntity.setBody("body");
////        requestEntity.setHead(head);
////        requestEntity.setMac("mac");
////        String s = JSON.toJSONString(requestEntity);
////        System.out.println(s);
////
////        Map<String,Object> map = new HashMap<>();
////        map.put("status",0);
////        map.put("warehouseId",42);
////        post(url, JSON.toJSONString(map));
//
//
//        String  param = "";
//        RequestEntity requestEntity = new RequestEntity();
//        RequestHeadEntity head = new RequestHeadEntity();
//        head.setAppId("");
//        head.setMethod("");
//        head.setAppSecret("");
//        head.setSerialNumber("");
//        head.setTokenId("");
//        head.setVerison("1");
//        head.setAppKey("dba0de982cee11e5b7a3848f69dd5ff1");
//
//        requestEntity.setBody(new ArrayList());//接口参数
//        requestEntity.setHead(head);//
//        String mac = EncryptUtil.encrypt(JSON.toJSONString(head), param);
//        requestEntity.setMac("6bf8b4f5cc8c23fde6b4b7c0771b6224");
//        String paramStr = JSON.toJSONString(requestEntity);
//
//        System.out.println(paramStr);
//        String url = "http://api.mypharma.com/supplier/fuzzyQuery";
//        Map<String,String> map = new HashMap<>();
//        map.put("dataJson","{body:[{}],head:{'appKey':'dba0de982cee11e5b7a3848f69dd5ff1','version':1},'mac':'6bf8b4f5cc8c23fde6b4b7c0771b6224'}");
//        JSONObject object = post(url,paramStr);
//        System.out.println(object);

		Map<String,String> map = new HashMap<>();
		map.put("1","q");
		String dd = JSON.toJSONString(map);
		System.out.println(dd);

		JSONObject jsonObject = JSONObject.parseObject(dd);
		JSONObject o = jsonObject.getJSONObject("1");
		System.out.println(o);


	}

}