package com.rograndec.feijiayun.chain.common.aop;

import com.rograndec.feijiayun.chain.business.auth.constant.SessionKey;
import com.rograndec.feijiayun.chain.business.auth.login.exception.LoginBizException;
import com.rograndec.feijiayun.chain.common.SysCode;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * aop拦截
 * Created by zhaiwei on 2017/6/15.
 */
@Aspect
@Component
@Order(-4)
public class SessionTimeOutAspect {

    private Logger log =  LoggerFactory.getLogger(SessionTimeOutAspect.class);

    @Pointcut("execution(* com.rograndec.feijiayun.chain.business.*.controller..*(..)) || @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    /*@Pointcut("@within(org.springframework.web.bind.annotation.RestController) || @within(org.springframework.stereotype.Controller)")*/
    public void sessionTimeOut(){}


    @Before("sessionTimeOut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();


        // 记录下请求内容
        log.debug("URL : " + request.getRequestURL().toString());
        log.debug("HTTP_METHOD : " + request.getMethod());
        log.debug("IP : " + request.getRemoteAddr());
        log.debug("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.debug("ARGS : " + Arrays.toString(joinPoint.getArgs()));


        Object loginUser = null;


        String uri = request.getRequestURI();

        String regex = "/login/";
        Pattern p = Pattern.compile(regex);
        Matcher matcher = p.matcher(uri);
        if(matcher.find()){
            return;
        }

        String regexDef = "/defLogin/";
        Pattern pDef = Pattern.compile(regexDef);
        Matcher defMatcher = pDef.matcher(uri);
        if(defMatcher.find()){
            return;
        }


        String regexError = "/error";
        Pattern pError= Pattern.compile(regexError);
        Matcher defError = pError.matcher(uri);
        if(defError.find()){
            return;
        }

        //*api/swagger-resources/configuration/ui*/

        String swaggerError = "/swagger";
        Pattern swaggerPattern= Pattern.compile(swaggerError);
        Matcher defswagger = swaggerPattern.matcher(uri);
        if(defswagger.find()){
            return;
        }

       /* /api-docs*/

        String apiDocsError = "/api-docs";
        Pattern apiDocsPattern= Pattern.compile(apiDocsError);
        Matcher apiDocsswagger = apiDocsPattern.matcher(uri);
        if(apiDocsswagger.find()){
            return;
        }


        // POS接口
        if(matcherUrl("/inf/pos/",uri)){
            return;
        }
        
        // 文件上传服务接口
        if(matcherUrl("/file",uri)){
            return;
        }
        
        //闪贷接口--判断是否saas用户
        if(matcherUrl("/fast/loan/isSaasUser", uri)){
        	return;
        }
        
        //线上采购--删除Saas购物车数据
        if(matcherUrl("/online/purchase/deleteCartByMph", uri)){
        	return;
        }
        
        // 地区
        if(matcherUrl("/location",uri)){
            return;
        }

		/**
		 * 注册
		 */
		if (matcherUrl("/register", uri)) {
			return;
		}

		/**
		 * 获取服务器名称
		 */
		if (matcherUrl("/getHostName", uri)) {
			return;
		}

        for(int i = 0 ; i < 10 ; i ++){
            log.debug("第"+i+"次获取登录用户{}",request.getSession().getId());
            loginUser = request.getSession().getAttribute(SessionKey.USER.getCode());
            if(null != loginUser){
                break;
            }else {
                log.debug("第"+i+"次获取登录用户,依然为空{}",request.getSession().getId());
            }
        }

        if(null == loginUser){
            throw new LoginBizException(SysCode.FORBIDDEN.getCode(),"会话过期,请重新登录!!!");
        }

    }

    private boolean matcherUrl(String regex,String uri){
		Pattern p = Pattern.compile(regex);
		Matcher matcher = p.matcher(uri);
		return matcher.find();
	}
}
