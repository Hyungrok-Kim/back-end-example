package com.kh.jdbc.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.jdbc.member.model.service.MemberService;
import com.kh.jdbc.member.model.vo.Member;

/**
 * Servlet implementation class MemberDeleteServlet
 */
@WebServlet("/memberDelete.do")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session에 있는 아이디 정보 가져옴 
		
		// 로그인한 회원의 아이디 가져오기 
		HttpSession session = request.getSession(false);
		
		Member m = (Member)session.getAttribute("member");
		
		String userId = m.getUserId();
		
		MemberService ms = new MemberService();
		
		int result = ms.deleteMember(userId);
		
		if (result > 0) {
			System.out.println("[서블릿] 회원 삭제 성공!");
			session.invalidate(); // 탈퇴했으니 자동 로그아웃되게 함.
			
			response.sendRedirect("index.jsp");
		} else {
			System.out.println("[서블릿] 회원 삭제 실패!");
			
			response.sendRedirect("views/common/errorPage.jsp");
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
