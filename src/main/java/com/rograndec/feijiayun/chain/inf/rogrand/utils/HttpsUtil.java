package com.rograndec.feijiayun.chain.inf.rogrand.utils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

public class HttpsUtil
{
	static final String url = "https://api-test-rgec.rograndec.com/biz/v4.2.6/addEnterpriseAndPic.html?dataJson=";
	
	/**
	 * 方法一
	 * @param clientBuilder
	 */
	public static void configureHttpClient(HttpClientBuilder clientBuilder) 
	{
		try
		{
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy()
			{
				// 信任所有
				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException 
				{
					return true;
				}
			}).build();
	        
	        clientBuilder.setSSLContext(sslContext);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 方法二
	 * @param clientBuilder
	 */
	public static void configureHttpClient2(HttpClientBuilder clientBuilder) 
	{
		try
		{
			SSLContext ctx = SSLContext.getInstance("TLS");  
	        X509TrustManager tm = new X509TrustManager()
	        {  
	                public void checkClientTrusted(X509Certificate[] chain,  String authType) throws CertificateException 
	                {
	                	
	                }  
	                public void checkServerTrusted(X509Certificate[] chain,  String authType) throws CertificateException 
	                {
	                	
	                }  
	                public X509Certificate[] getAcceptedIssuers() 
	                {  
	                    return null;  
	                }  
	        };  
	        ctx.init(null, new TrustManager[]{tm}, null);  
	        
	        clientBuilder.setSSLContext(ctx);
	        
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)throws Exception
	{
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		
		configureHttpClient2(httpClientBuilder);
		
		CloseableHttpClient httpClient = httpClientBuilder.build();
		
		HttpPost post = new HttpPost(url);
		post.setHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded");
		String sendData = "dataJson={\"body\":{\"eProvince\":\"110000\",\"eAddress\":\"fuhhv\",\"uid\":\"9024749\",\"eStatus\":\"2\",\"eName\":\"fhhhhjj\",\"eType\":7,\"eParentId\":0,\"eRegion\":\"110101\",\"password\":\"qeewwwee\",\"eContactor\":\"thick\",\"eMobile\":\"13655875558\",\"eCity\":\"110100\",\"eLicenseNo\":\"ryuuiiioo\",\"epList\":[{\"epPic\":\"2017\\/10\\/17\\/mph_71c2f8fe4c274800a630759a3d0d5bc5_watermark.jpg\",\"epType\":13}],\"username\":\"yuyyyyhh\"},\"head\":{\"method\":\"112003\",\"serialNumber\":\"12032114314473700001\",\"version\":\"1\",\"sysVersion\":\"iPhoneOS9.2\",\"appVersion\":\"5.3.13\",\"imei\":\"74FD10EE-5427-455A-8D07-6C57A88674DF\",\"terminalstate\":12,\"appType\":2,\"appSys\":1,\"appId\":8,\"appSecret\":\"175906d1a85aeff2eeb5ad7732f6ba98\",\"sourceCode\":\"wdyy-app\"},\"mac\":\"D89AF733\"}";

		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		for (String str : sendData.split("&")) {
			formParams.add(new BasicNameValuePair(str.substring(0, str.indexOf("=")),
					str.substring(str.indexOf("=") + 1)));
		}
		post.setEntity(new StringEntity(
				URLEncodedUtils.format(formParams,"UTF-8" )));
		
		CloseableHttpResponse resp = httpClient.execute(post);
		
		HttpEntity httpEntity = resp.getEntity();  
        
        System.out.println(EntityUtils.toString(httpEntity));
		
		httpClient.close();
	}

}
