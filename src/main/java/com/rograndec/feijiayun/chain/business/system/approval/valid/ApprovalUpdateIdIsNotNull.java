package com.rograndec.feijiayun.chain.business.system.approval.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * Created by zhaiwei on 2017/8/26.
 * 检测修改审批时,id是否为空
 */
@Documented
@Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ApprovalUpdateIdIsNotNullValidator.class)
public @interface ApprovalUpdateIdIsNotNull {
    //默认错误消息
    String message() default "修改时,id不能为空";

    //分组
    Class<?>[] groups() default { };

    //负载
    Class<? extends Payload>[] payload() default { };

    //指定多个时使用
    @Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        ApprovalUpdateIdIsNotNull[] value();
    }
}
