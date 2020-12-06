package com.kh.ws.filter;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;

import com.kh.ws.chat.HelloWebSocket;

/**
 * Servlet Filter implementation class DuplicateIdCheckFilter
 */
@WebFilter("/chat/chatRoom.chat")
public class DuplicateIdCheckFilter implements Filter {

    /**
     * Default constructor. 
     */
    public DuplicateIdCheckFilter() {
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
		Map<String, Session> clients = HelloWebSocket.clients;
		Set<String> userSet = clients.keySet();
		
		//사용자 요청아이디를 HttpServletRequest에서 가져온다.
		String userId = ((HttpServletRequest)request).getParameter("userId");
		
		//현재 사용자 목록에서 존재여부 확인
		boolean isUsable = !userSet.contains(userId);
		System.out.printf("[%s]사용가능여부@DuplicateIdCheckFilter=%s", userId, isUsable);
		
		if(userId == null || !isUsable){
			request.setAttribute("msg", "사용할 수 없는 아이디입니다.");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/views/common/msg.jsp")
				   .forward(request, response);
			return;
		}
		
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
