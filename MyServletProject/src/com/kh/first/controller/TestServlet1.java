package com.kh.first.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet1
 */
public class TestServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0. 화면의 인코딩 방식
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 1. 화면 HTML 작성
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		
		out.println("<head>");
		
		out.println("<title>서블릿이란?</title>");
		
	
		
		out.println("</head>");
	
		out.println("<body>");
		out.println("<h1>서블릿이란?</h1>");
		out.println("<p>");
		out.println("서블릿은 웹 애플리케이션으로 보여질 코드 , 웹 문서 내용을 <br>");
		out.println("자바 언어로 구현하는 기술이다");
		out.println("</p>");
		out.println("</body>");
		
		out.println("</html>");
		
		out.flush();
		out.close();
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
