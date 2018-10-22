//package com.rograndec.feijiayun.chain.common.advice;
//
//import com.rograndec.feijiayun.chain.common.Result;
//import com.rograndec.feijiayun.chain.common.SysCode;
//import org.springframework.core.MethodParameter;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
//import springfox.documentation.swagger.web.SwaggerResource;
//import springfox.documentation.swagger.web.UiConfiguration;
//
//import java.util.Collection;
//
///**
// * Created by zhaiwei on 2017/9/26.
// */
//@ControllerAdvice
//public class ResponseVOAdvice implements ResponseBodyAdvice<Object> {
//
//@Override
//public boolean supports(MethodParameter returnType, Class converterType) {
//    if (returnType.getMethodAnnotation(RequestMapping.class) != null) {
//        return true;
//    }
////    if (returnType.getMethodAnnotation(ResponseBodyEntity.class) != null) {
////        return true;
////    }
//    return false;
//}
//@Override
//public Object beforeBodyWrite(Object body,
//                              MethodParameter returnType,
//                              MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
//                              ServerHttpRequest request,
//                              ServerHttpResponse response) {
//
//            if(body instanceof Result){
//                return body;
//            }if(body instanceof UiConfiguration){
//                return body;
//            }if(body instanceof SwaggerResource){
//                return body;
//            }if(body instanceof Collection){
//                Collection collection = (Collection) body;
//                Object[] objects = collection.toArray();
//                for(Object obj : objects){
//                    if(obj instanceof SwaggerResource){
//                        return body;
//                    }
//                }
//            }else {
//                Result result = new Result();
//                result.setBizCodeSuccessInfo(SysCode.SUCCESS, body);
//                return request;
//            }
//
//            return body;
//        }
//
//}
