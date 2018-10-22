package com.rograndec.feijiayun.chain.common.component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Component
public class RestTemplateComponent {

    @Autowired
    private RestTemplate restTemplate;


    public  JSONObject post(String url, String param){
        //设置header
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");

        //设置参数
        HttpEntity<String> httpEntity = new HttpEntity<String>(param, httpHeaders);

        //执行请求
        ResponseEntity<String> resp = restTemplate.exchange(url, HttpMethod.GET,httpEntity, String.class);

        //获取返回的header
        List<String> val = resp.getHeaders().get("Set-Cookie");
//		System.out.println(val);

        //获得返回值
        String body = resp.getBody();
        return  JSON.parseObject(body);
    }


    /**
     * GET请求
     * @param url
     * @param param
     * @return
     */
    public  JSONObject get(String url, String param){
        System.out.println(url);
        System.out.println(param);
        String result = restTemplate.getForObject(url, String.class, param);
        JSONObject resp = JSONObject.parseObject(result);
        return resp;
    }
    public  ResponseEntity<String> post(String url,String param,Map<String,String> headerMap){
        //设置header
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");

        headerMap.forEach((o1, o2) -> httpHeaders.set(o1, o2));

        //设置参数
        HttpEntity<String> httpEntity = new HttpEntity<String>(param, httpHeaders);

        //执行请求
        ResponseEntity<String> resp = restTemplate.exchange(url, HttpMethod.POST,httpEntity, String.class);


        return  resp;
    }


    public  JSONObject post2Rgt(String url,String param){

        JSONObject jsonObject =  post(url,param);
        if(jsonObject == null){
            throw new BusinessException("111111","融贯通接口返回异常");
        }
        return jsonObject.getJSONObject("body");
    }
}
