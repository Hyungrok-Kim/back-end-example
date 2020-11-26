package com.kh.el.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class TestELServlet
 */
@WebServlet("/testEL.do")
public class TestELServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestELServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		
		// request 영역
		request.setAttribute("req", "Request 영역입니다.");
		request.setAttribute("lunchMenu" , "곱창");
		
		// session 영역
		HttpSession session = request.getSession();
		session.setAttribute("ses", "Session 영역입니다.");
		session.setAttribute("lunchMenu", "막국수");
		
		// application 영역 - 서버가 종료되지 않는 한 계속 유지되는 영역
		ServletContext application = request.getServletContext();
		application.setAttribute("app", "Application 영역입니다.");
		application.setAttribute("lunchMenu", "순두부찌개");
		
		request.getRequestDispatcher("views/el/testScope.jsp").forward(request,response);
		
		
		
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
