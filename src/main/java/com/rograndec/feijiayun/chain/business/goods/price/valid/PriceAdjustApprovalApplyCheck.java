package com.rograndec.feijiayun.chain.business.goods.price.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * 修改价格调整时校验 审批开始 ,审批失败时才可以修改价格调整
 */
@Documented
@Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PriceAdjustApprovalApplyCheckValidator.class)
public @interface PriceAdjustApprovalApplyCheck {
    //默认错误消息
    String message() default "校验失败";

    //分组
    Class<?>[] groups() default { };

    //负载
    Class<? extends Payload>[] payload() default { };

    //指定多个时使用
    @Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        PriceAdjustApprovalApplyCheck[] value();
    }
}
