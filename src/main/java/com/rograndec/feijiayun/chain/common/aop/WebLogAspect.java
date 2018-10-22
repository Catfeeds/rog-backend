package com.rograndec.feijiayun.chain.common.aop;

import com.rograndec.feijiayun.chain.business.system.other.entity.SysLog;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.http.HttpClientUtil;
import com.rograndec.mq.core.MQTemplate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ST on 2017/8/26.
 */
@Aspect
@Component
public class WebLogAspect {

    private Logger logger = LoggerFactory.getLogger(WebLogAspect.class);
    @Autowired
    private MQTemplate mqTemplate;

    static ExecutorService executorService = Executors.newFixedThreadPool(2);

    private static final String update = "update";
    private static final String modify = "modify";
    private static final String insert = "insert";
    private static final String save = "save";
    private static final String add = "add";
    private static final String remove = "remove";

    @Pointcut(value = "@annotation(apiOperation)",argNames = "apiOperation")
    public void webLog(ApiOperation apiOperation){

    }

    @Before("webLog(apiOperation)")
    public void doBefore(JoinPoint joinPoint,ApiOperation apiOperation){
        // 接收到请求，记录请求内容
        // 记录下请求内容

        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            String ip = getOutNetIp(request);
            logger.debug("URL : " + request.getRequestURL().toString());
            logger.debug("HTTP_METHOD : " + request.getMethod());
            logger.debug("IP : " + ip);
            logger.debug("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
            logger.debug("ARGS : " + Arrays.toString(joinPoint.getArgs()));
            String method = joinPoint.getSignature().getName();
            // POS心跳接口不记录日志
            if(!"heartbeat".equals(method) && matcherUrl(method)) {
                HttpSession session = request.getSession();
                UserVO userVO = (UserVO) session.getAttribute("user");
                if(null != userVO){
                    SysLog sysLog = new SysLog();
                    sysLog.setActionName(apiOperation.value());
                    sysLog.setAccount(userVO.getLoginAccount());
                    sysLog.setParentId(userVO.getParentId());
                    sysLog.setEnterpriseId(userVO.getEnterpriseId());
                    sysLog.setEmployeeCode(userVO.getUserCode());
                    sysLog.setEmployeeName(userVO.getUserName());
                    sysLog.setEmployeeId(userVO.getUserId());
                    sysLog.setLoginTime(new Date());
                    sysLog.setIp(ip);
                    String ipArea = HttpClientUtil.getIpArea(ip);
                    sysLog.setLoginPlace(ipArea);//// TODO: 2017/8/26 ip地址库待接入   http://ip.taobao.com/service/getIpInfo.php?ip=119.57.68.162
                    Object target = joinPoint.getTarget();
                    Class<?> aClass = target.getClass();

                    try {
                        Api api = aClass.getAnnotation(Api.class);

                        if(null != api){
                            String description = api.description();
                            String value = api.value();
                            sysLog.setClassName(StringUtils.isEmpty(description) ? (StringUtils.isEmpty(value) ? aClass.getSimpleName() : value) : description);
                        }else {
                            sysLog.setClassName(aClass.getSimpleName());
                        }

                    }catch (Exception e){
                        sysLog.setClassName(aClass.getSimpleName());
                    }

                    // mqTemplate.sendOneWay("saas:chain", sysLog);
                    executorService.submit(() -> {
                        mqTemplate.sendOneWay("saas:chain", sysLog);
                    });
                }


              /*  throw new RegisterBizException(RegisterBizException.VALUE_CHECK,"111");*/

            }
        } catch (Exception e) {
            // logger.error(e.getMessage());
        }
    }


//    public String getIpAddr(HttpServletRequest request){
//        String ipAddress = request.getHeader("x-forwarded-for");
//        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
//            ipAddress = request.getHeader("Proxy-Client-IP");
//        }
//        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
//            ipAddress = request.getHeader("WL-Proxy-Client-IP");
//        }
//        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
//            ipAddress = request.getRemoteAddr();
//            if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){
//                //根据网卡取本机配置的IP
//                InetAddress inet=null;
//                try {
//                    inet = InetAddress.getLocalHost();
//                } catch (UnknownHostException e) {
//                    e.printStackTrace();
//                }
//                ipAddress= inet.getHostAddress();
//            }
//        }
//        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
//        if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15
//            if(ipAddress.indexOf(",")>0){
//                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
//            }
//        }
//        return ipAddress;
//    }

    public  String getOutNetIp(HttpServletRequest request) {
        String ip = "";
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
        try {

            ip = request.getHeader("X-Forwarded-For");

            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getHeader("Proxy-Client-IP");
                }
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getHeader("WL-Proxy-Client-IP");
                }
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getHeader("HTTP_CLIENT_IP");
                }
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getHeader("HTTP_X_FORWARDED_FOR");
                }
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getRemoteAddr();
                }
            } else if (ip.length() > 15) {
                String[] ips = ip.split(",");
                for (int index = 0; index < ips.length; index++) {
                    String strIp = (String) ips[index];
                    if (!("unknown".equalsIgnoreCase(strIp))) {
                        ip = strIp;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ip;
    }

//    private String getOutNetIp(HttpServletRequest request) throws Exception {
//
//        if (request == null) {
//            throw (new Exception("getIpAddr method HttpServletRequest Object is null"));
//        }
//        String ipString = request.getHeader("x-forwarded-for");
//        if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {
//            ipString = request.getHeader("Proxy-Client-IP");
//        }
//        if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {
//            ipString = request.getHeader("WL-Proxy-Client-IP");
//        }
//        if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {
//            ipString = request.getRemoteAddr();
//        }
//        logger.info("ipString = [" + ipString + "]");
//        // 多个路由时，取第一个非unknown的ip
//        final String[] arr = ipString.split(",");
//        for (final String str : arr) {
//            if (!"unknown".equalsIgnoreCase(str)) {
//                ipString = str;
//                break;
//            }
//        }
//
//        return ipString;
//    }


    private boolean matcherUrl(String uri){

        return uri.contains(update) || uri.contains(modify)
                || uri.contains(insert)
                || uri.contains(save)
                || uri.contains(add)
                || uri.contains(remove)
                || uri.contains(update);
    }


}