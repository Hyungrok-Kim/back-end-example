package com.kh.jsp.common;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * @WebFilter 는 특정 url이나 서블릿을 거쳐갈 때 
 * 해당하는 필터를 동작 시켜라 라는 뜻.
 * ex) /login.do : 로그인 서블릿을 실행 때만 수행하는 필터 
 * ex2) /* : 모든 서블릿을 실행할 때 수행하는 필터 
 */
@WebFilter("/*")
public class EncodingFilter implements Filter {

    /**
     * Default constructor. 
     */
    public EncodingFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 서블릿 수행 전
		request.setCharacterEncoding("UTF-8");
		
		chain.doFilter(request, response);
	
		// 서블릿 수행 후
		
	
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
