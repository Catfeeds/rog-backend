package com.rograndec.feijiayun.chain.filter;

import com.rograndec.feijiayun.chain.inf.pos.base.httpservlet.POSAPIHttpServletRequestWrapper;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebFilter(filterName="initFilter",urlPatterns="/*")
public class InitFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// TODO Auto-generated method stub
		//System.out.println("!!!!!!!!!!!!!!!!!---执行自定义过滤器InitFilter---!!!!!!!!!!!!!!!!!!");

		/*HttpSession session = request.getSession();*/
		String uri = request.getRequestURI();

		/*Object loginUser = session.getAttribute(SessionKey.USER.getCode());

		String regex = "/login/";
		Pattern p = Pattern.compile(regex);
		Matcher matcher = p.matcher(uri);
		String regexDef = "/defLogin/";
		Pattern pDef = Pattern.compile(regexDef);
		Matcher defMatcher = pDef.matcher(uri);
		if(null == loginUser && !matcher.find() && !defMatcher.find()){
			throw new LoginBizException(SysCode.FORBIDDEN.getCode(),"会话过期,请重新登录!!!");
		}*/

		/*if(null == loginUser){
			response.setHeader("session-status", "timeout");//在响应头设置session状态
			throw new LoginBizException(SysCode.FORBIDDEN.getCode(),"会话过期,请重新登录!!!");
		}*/

		// 处理POS接口过滤处理
		ServletRequest requestWrapper = null;
		if(matcherUrl("/inf/pos/",uri)){
	        if(request instanceof HttpServletRequest) {    
	            requestWrapper = new POSAPIHttpServletRequestWrapper((HttpServletRequest) request);
	        }    
		}
		logger.debug(uri);
		if(requestWrapper == null) {    
			doFilter(request, response, filterChain); 
        } else {    
        	System.out.println("----------------------------POS过滤器接口处理-------------------------");
        	doFilter(requestWrapper, response, filterChain); 
        }


	}


	private boolean matcherUrl(String regex,String uri){
		Pattern p = Pattern.compile(regex);
		Matcher matcher = p.matcher(uri);
		return matcher.find();
	}




}
