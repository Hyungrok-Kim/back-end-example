package com.kh.first.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet3Ack
 */
@WebServlet("/test3Ack.do")
public class TestServlet3Ack extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet3Ack() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// HTML을 만드는 서블릿 
		// test3.do 서블릿으로부터 받은 정보를 HTML로 바꾸는 역할을 수행
		
		// 0. 인코딩 과정 (test3.do에서 하고 넘어왔으니 생략!)
		
		// 1. HTML 화면 작성
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>오늘의 색상</title>");
		
		out.println("</head>");
		
		out.println("<body>");
		out.println("<h1>오늘의 색상!</h1>");
		out.println("<p>");
		out.println("<h1 style='color : "+ request.getParameter("colorPicker") + "'>");
		out.println("선택한 색상 : " + request.getParameter("colorPicker"));
		out.println("</h1>");
		out.println("색상 평 : " + request.getAttribute("feeling"));
		out.println("</p>");
		out.println("</body>");
		
		out.println("</html>");
		
		
		
		
		
		
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
