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
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/memberUpdate.do")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 인코딩 : EncodingFilter가 대신 해줌  HTML에서 서블릿으로 이동할때 필터가 먼저 실행되서 
		// 2. 회원 정보 수정용 데이터 받아오기 
		String userPwd = request.getParameter("userPwd");
		String gender = request.getParameter("gender");
		int age = Integer.parseInt(request.getParameter("age"));
	    String email = request.getParameter("email");
		String phone= request.getParameter("phone");
		String address= request.getParameter("address");
		String hobby= String.join(", ", request.getParameterValues("hobby"));
		
		// 3. 해당 회원의 원본 정보(로그인 시 가져온 정보) 꺼내오기
		HttpSession session = request.getSession(false);
		
		Member m = (Member)session.getAttribute("member");
		
		System.out.println("[서블릿] 원본 정보 : " + m);
		
		// 변경할 회원정보를 setter로 처리하기 
		
		m.setUserPwd(userPwd);
		m.setGender(gender);
		m.setAge(age);
		m.setEmail(email);
		m.setPhone(phone);
		m.setAddress(address);
		m.setHobby(hobby);
		
		System.out.println("[서블릿] 변경 후 정보 :" + m);
		
		// 5. 회원 서비스 찾아가기
		MemberService ms = new MemberService();
		
		int result = ms.updateMember(m);
		
		if( result > 0) {
			//  회원 정보 수정 완료!
			System.out.println("[서블릿] 회원 정보 수정 완료!");
			
			session.invalidate(); // 강제 로그아웃 시키고 다시 재 로그인 하게끔 
			
			response.sendRedirect("views/loginForm.jsp");
			
		} else {
			// 회원 정보 수정 실패!
			
			System.out.println("[서블릿] 회원 정보 수정 실패!");
			
			request.setAttribute("error-msg", "회원 정보 수정 실패!");
			
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			
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
