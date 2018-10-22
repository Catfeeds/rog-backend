package com.rograndec.feijiayun.chain.config;

import com.rograndec.feijiayun.chain.filter.InitFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.servlet.Filter;

@Configuration
public class FilterConfiguration {

	@Bean
	@Order(1)
	public Filter getInitFilter(){
		return new InitFilter();
	}

}
