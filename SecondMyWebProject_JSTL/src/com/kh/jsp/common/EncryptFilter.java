package com.kh.jsp.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class EncryptFilter
 */
// memberInsert.do, login.do, memberUpdate.do를 통해서만 들어왔을때 이것을 실행하곘다라는 의미!
@WebFilter({"/login.me", "/mInsert.me" , "/mUpdate.me"})
public class EncryptFilter implements Filter {

    /**
     * Default constructor. 
     */
    public EncryptFilter() {
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
		// 서블릿에 도착하기 전 수행 할 코드
		
		// 사용자의 요청 정보(request) 받기
		HttpServletRequest req = (HttpServletRequest)request;
		
		// 비밀번호 확인을 위한 원본 값 별도 저장 
		request.setAttribute("originPwd", req.getParameter("userPwd"));
		
		// 비밀번호를 암호화 포장(Wrap)할 래퍼 클래스 생성하기 
		EncryptWrapper ew = new EncryptWrapper(req);
		
		chain.doFilter(ew, response);
		
		// 서블릿 처리 후 수행 할 코드
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
