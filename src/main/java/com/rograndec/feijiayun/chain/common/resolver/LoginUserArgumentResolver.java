package com.rograndec.feijiayun.chain.common.resolver;

import com.rograndec.feijiayun.chain.common.vo.UserVO;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Created by zhaiwei on 2017/7/27.
 */
@Component
//@Configuration
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(UserVO.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        Object user = webRequest.getAttribute("user", RequestAttributes.SCOPE_SESSION);

        return user;
    }

    public LoginUserArgumentResolver() {
    }

}
