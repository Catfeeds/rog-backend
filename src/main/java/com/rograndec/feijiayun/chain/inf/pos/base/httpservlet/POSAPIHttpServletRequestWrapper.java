package com.rograndec.feijiayun.chain.inf.pos.base.httpservlet;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StreamUtils;



/**
 * 原因：
　　1. 一个InputStream对象在被读取完成后，将无法被再次读取，始终返回-1；
　　2. InputStream并没有实现reset方法（可以重置首次读取的位置），无法实现重置操作；
 * @ClassName: POSAPIHttpServletRequestWrapper   
 * @Description: POS自定义HttpServletRequest
 * @author yuting.li
 * @version 1.0 
 * @date 2017年10月9日 下午6:59:15
 */
public class POSAPIHttpServletRequestWrapper extends HttpServletRequestWrapper{
	
	private static Logger logger = LoggerFactory.getLogger(POSAPIHttpServletRequestWrapper.class);
	
	private byte[] requestBody; // 报文  

	public POSAPIHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		//缓存请求body  
        try {  
        	requestBody = StreamUtils.copyToByteArray(request.getInputStream());  
        } catch (IOException e) {  
        	logger.error("http复制request.getInputStream()错误", e);
        } 
	}
	
	/** 
     * 重写 getInputStream() 
     */  
    @Override  
    public ServletInputStream getInputStream() throws IOException {  
        if(requestBody == null){  
            requestBody= new byte[0];  
        }  
        final ByteArrayInputStream bais = new ByteArrayInputStream(requestBody);    
        return new ServletInputStream() {    
            @Override    
            public int read() throws IOException {    
                return bais.read();    
            }

			@Override
			public boolean isFinished() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isReady() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void setReadListener(ReadListener readListener) {
				// TODO Auto-generated method stub
				
			}    
        };    
    }  
  
    /** 
     * 重写 getReader() 
     */  
    @Override  
    public BufferedReader getReader() throws IOException {  
        return new BufferedReader(new InputStreamReader(getInputStream(),"UTF-8"));    
    } 
	

}
