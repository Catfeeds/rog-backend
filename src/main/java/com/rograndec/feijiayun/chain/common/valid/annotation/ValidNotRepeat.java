package com.rograndec.feijiayun.chain.common.valid.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 安全校验注解(不能为空)
 * collection内重复对象内容校验
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ValidNotRepeat {
	String value() ;//非重复唯一标示
}
