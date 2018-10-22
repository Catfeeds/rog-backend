package com.rograndec.feijiayun.chain.common.valid.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 安全校验注解（固定长度校验）
 * 可校验collection固定大小，字符串长度
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ValidLength {
	int value() ;
}
