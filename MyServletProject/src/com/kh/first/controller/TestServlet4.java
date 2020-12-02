package com.kh.first.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet4
 */
@WebServlet("/test4.do")
public class TestServlet4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet4() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 만약 전달받은 데이터가
		// ID가 user01이고 , 비밀번호가 pass01이 아니라면 
		// 에러페이지로 이동시키고, 맞다면 test2.do로 이동시키기 
		
		// 0. 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF8");
		
		// 1. HTML에서 전달된 userId와 userPwd 받아오기 
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		if(userId.equals("user01") && userPwd.equals("pass01")) {
			// 로그인 성공 서블릿으로 이동
			
			RequestDispatcher view = request.getRequestDispatcher("/test2.do");
			
			view.forward(request, response);
			
		}else {
			// 로그인 실패 서블릿으로 이동 
			// 만약 응답해야 할 페이지가 서블릿에서 처리한 내용과 
			// 관련이 없을 경우, 혹은 요청한 정보를 사용하지 않을 경우 
			// 다른 서블릿과 연결하는 방법
			
			response.sendRedirect("test4Ack.do");
			
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
