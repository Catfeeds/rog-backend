package com.rograndec.feijiayun.chain.inf.pos.base.Interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 
 * @ClassName: InfPosApiConfig   
 * @Description: POS 注册拦截器
 * @author yuting.li
 * @version 1.0 
 * @date 2017年10月7日 下午1:38:31
 */
@Configuration
public class InfPosApiConfig extends WebMvcConfigurerAdapter{
	
	@Autowired
	private InfPosApiInterceptor infPosApiInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(infPosApiInterceptor).addPathPatterns("/inf/pos/**");
	}
	
}
