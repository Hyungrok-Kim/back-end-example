package com.kh.first.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet3
 */
@WebServlet("/test3.do")
public class TestServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet3() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0. 문자셋 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 만약 자바에서 처리해야 할 데이터 처리 코드가 너무 길거나
	    // 혹은 특정 역할(기능)을 나누어 처리하고자 할 경우
	    // 서블릿에서 바로 HTML로 넘어가지 않고
        // 서블릿끼리 데이터 전달도 가능하다.
		
		// 1. view(HTML)에서 전달한 데이터(파라미터:매개변수) 받아오기
		String color = request.getParameter("colorPicker");
		
		System.out.println("선택한 색상: " + color);
		
		// 2. 전달 데이터에 따른 결과를
		// 	     동적으로 구성하기
		String feeling = null;
		
		switch(color) {
		case "red" : 
			feeling="정열적인 하루의 업무를 위한 색상!";
			break;
		case "orange" :
			feeling="만약 억압받는 업무 환경이라면 당근을 흔드세요";
			break;
		case "yellow" :
			feeling="노란 얼굴을 하신다면 병원 가보셔야 합니다.";
			break;
		case "green" :
			feeling="초록색은 그린입니다.";
			break;
		case "blue" : 
			feeling="뭔가 심상치 않은 일이 생긴거야...";
			break;
		case "purple" :
			feeling="보라색 맛이 났어!";
			break;
			
		}
		
		System.out.println("결과 확인 : " + feeling);
		
		// 응답해야 할 HTML 페이지가 이미 만들어진 페이지가 아닌 
		// 매번 입력값에 따라 바뀌는 페이지라면 여러 서블릿으로 
		// 쪼개어 역할을 분담할 수도 있다!
		
		RequestDispatcher view = request.getRequestDispatcher("/test3Ack.do");
		
		// 전달하고자 하는 데이터를 다음 서블릿에게 
		// 이어 전달하기 위해 request 객체에 값 담기 
		request.setAttribute("feeling", feeling);  // 앞은 찾아올 name , 뒤는 실제 값

		// 준비된 서블릿 출발 !
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
