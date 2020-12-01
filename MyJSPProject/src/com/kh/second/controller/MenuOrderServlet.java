package com.kh.second.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MenuOrderServlet
 */
@WebServlet("/menuOrder.do")
public class MenuOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0. 인코딩
		response.setCharacterEncoding("UTF-8");
		
		// 1. 전달받은 데이터 확인 
		String menu = request.getParameter("menuSelect");
		
		System.out.println("선택한 메뉴 확인 : " + menu);
		
		// 2. 비즈니스 로직 작성 (업무 로직 : 기능 관련 소스코드 작성)
		
		int price = 0;
		
		switch(menu) {
		case "스테이크" : 
			price = 40000;
			break;
		case "시리얼" : 
			price = 2000;
			break;
		case "샐러드" :
			price = 9500;
			break;
		case "게란후라이" : 
			price = 4000;
			break;
		case "시그니처버거" : 
			price = 12000;
			break;
		}
		
		// 3. 비즈니스 로직 실행 결과 담기
		request.setAttribute("menu", menu);
		request.setAttribute("price", price);
		
		// 4. forward (결과를 받아 전달보내야 하기 때문에) : 작성 내용을 jsp에게 전송
		RequestDispatcher view = request.getRequestDispatcher("views/menuResult.jsp"); // views 앞에 /를 붙여서 /views/menuResult.jsp도 가능! views앞의 /는 절대경로! webapps를 뜻함
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
