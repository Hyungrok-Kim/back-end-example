package com.kh.jdbc.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.jdbc.member.model.service.MemberService;
import com.kh.jdbc.member.model.vo.Member;

/**
 * Controller : 화면과 Java를 이어주는 클래스의 역할
 * HTML에서 작성한 내용을 자바 서비스 기능 클래스에게 전달하고,
 * 그 결과를 다시 HTML(화면)로 전달하는 역할 
 */
@WebServlet("/login.do")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 1. 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 2. 정보 받아오기 
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String originPwd = (String)request.getAttribute("originPwd");
		
		System.out.println("원본 비번 : " + originPwd);
		
		System.out.println("서블릿 : " + userId + "/" + userPwd);
		
		Member m = new Member(userId, userPwd);
		
		// 3. 회원 정보를 처리하는 서비스 기능의
		//	    클래스에 전달하고 그 결과 확인하기 
		MemberService ms = new MemberService();
		
		m = ms.selectMember(m);
		
		if(m != null) {
			// 로그인 성공!
			
			// 세션에 로그인 정보 저장
			HttpSession session = request.getSession();
			session.setAttribute("member", m);
			
			response.sendRedirect("index.jsp");
			
			// request.setAttribute("member", m);
			/*
			 * 
			RequestDispatcher view = 
						request.getRequestDispatcher("views/loginSuccess.jsp");
			
			view.forward(request, response);
			*/
		} else {
			// 로그인 실패!
			request.setAttribute("fail-msg", "회원 로그인 실패");
			
			RequestDispatcher view =
						request.getRequestDispatcher("views/loginFail.jsp");
			
			view.forward(request, response);
			
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
