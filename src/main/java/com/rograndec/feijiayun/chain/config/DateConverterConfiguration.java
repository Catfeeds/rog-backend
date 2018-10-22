package com.rograndec.feijiayun.chain.config;

import com.rograndec.feijiayun.chain.utils.date.DateConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import java.util.HashSet;
import java.util.Set;

/**
 * 前后台时间交互转换工具
 * Created by ST on 2017/8/25.
 */
@Configuration
public class DateConverterConfiguration {
    private Logger logger = LoggerFactory.getLogger(DateConverterConfiguration.class);

    @Bean
    public ConversionService createConversionService() {
        ConversionServiceFactoryBean factoryBean = new ConversionServiceFactoryBean();
        //FormattingConversionServiceFactoryBean factoryBean = new FormattingConversionServiceFactoryBean();
        Set<Converter> converters = new HashSet<>();
        converters.add(createDateConverter());
        factoryBean.setConverters(converters);
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }

    @Bean
    public DateConverter createDateConverter() {
        return new DateConverter();
    }


}