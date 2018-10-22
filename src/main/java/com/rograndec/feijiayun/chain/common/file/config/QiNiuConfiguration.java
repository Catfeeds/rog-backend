package com.rograndec.feijiayun.chain.common.file.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.qiniu.util.Auth;

/**
 * 
 * QiNiuConfiguration
 * @author qun.liu
 *
 */
@Configuration
public class QiNiuConfiguration {
	
	@Value("${qiniu.accessKey}")
	private String qiniuAccessKey;

	@Value("${qiniu.secretKey}")
	private String qiniuSecretKey;

	@Bean
	Auth quth() {
		return Auth.create(qiniuAccessKey, qiniuSecretKey);
	}

}
