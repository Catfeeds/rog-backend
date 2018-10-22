package com.rograndec.feijiayun.chain.common.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * aop拦截
 * Created by zhaiwei on 2017/6/15.
 */
@Aspect
@Component
@Order(-5)
public class ExceptionLogAspect {

    private Logger log =  LoggerFactory.getLogger(ExceptionLogAspect.class);

    /**
     * 异常通知 用于拦截service层记录异常日志
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "execution(public * com.rograndec.feijiayun.chain.business.*.service..*.*(..))", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {

        //获取用户请求方法的参数并序列化为JSON格式字符串
        String params = "";
        try {

            if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
                for (int i = 0; i < joinPoint.getArgs().length; i++) {
                    ObjectMapper mapper = new ObjectMapper();
                    params += mapper.writeValueAsString(joinPoint.getArgs()[i] )+ ";";
                }
            }
              /*========控制台输出=========*/
            System.out.println("=====异常通知开始=====");
            System.out.println("异常代码:" + e.getClass().getName());
            System.out.println("异常信息:" + e.getMessage());
            System.out.println("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            System.out.println("方法描述:" + getServiceMthodDescription(joinPoint));
            System.out.println("请求参数:" + params);
            //保存数据库
            System.out.println("=====异常通知结束=====");
        } catch (Exception ex) {
            //记录本地异常日志
            log.error("==异常通知异常==");
            log.error("异常信息:{}", ex.getMessage());
        }
         /*==========记录本地异常日志==========*/
        log.error("异常方法:{}异常代码:{}异常信息:{}参数:{}", joinPoint.getTarget().getClass().getName() + joinPoint.getSignature().getName(), e.getClass().getName(), e.getMessage(), params);

    }

    /**
     * 获取注解中对方法的描述信息 用于service层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static String getServiceMthodDescription(JoinPoint joinPoint)
            throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        StringBuffer sb = new StringBuffer();
        sb.append("targetName:"+targetName).append("methodName:"+methodName).append("arguments:");
        for(Object o : arguments){
            sb.append(o.toString()).append(",");
        }
        return sb.toString();
    }
}
