package com.rograndec.feijiayun.chain.common.valid.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 安全校验注解(最小长度校验)
 * 字符串长度和collection大小
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ValidMin {
	int value() ;
}
