package com.kh.jsp.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MemberLogoutServlet
 */
@WebServlet("/logout.me")
public class MemberLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberLogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 이미 연결된 세션 만료시키기
		// .getSession() : 세션 정보 가져오는 메소드
		// 					만약, 기존에 세션 정보가 없었으면 
		//					새로 만들어서라도 가져오는 기능을 수행한다.
		// .getSession(false) : 있으면 가져오고, 없으면 null을 가져옴.
		
		HttpSession session = request.getSession(false);
		
		if( session != null) {
			System.out.println(" 세션 ID : " + session.getId());
			System.out.println(" -- 세션을 종료합니다. -- ");
			session.invalidate();	// 기존에 연결된 세션 무효화 처리(만료처리)
		}
		
		response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
