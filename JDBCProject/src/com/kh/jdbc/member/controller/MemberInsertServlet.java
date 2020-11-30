package com.kh.jdbc.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.jdbc.member.model.service.MemberService;
import com.kh.jdbc.member.model.vo.Member;

/**
 * Servlet implementation class MemberInsertServlet
 */
@WebServlet("/memberInsert.do")
public class MemberInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원 가입 컨트롤러 (view <--> Java) (view와 Java를 이어주는 문지기 )
		
		// 1. 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 2. memberJoin.jsp에서 작성한 정보 받아오기
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String userName = request.getParameter("userName");
		String gender = request.getParameter("gender");
		int age = Integer.parseInt(request.getParameter("age"));
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		
		// String[] hobby = request.getParameterValues("hobby"); // hobby라는 이름의 문자열 배열로 옴
		// 배열을 문자열로 만들기
		// why? 결국 들어갈 곳은 DB인데, DB에는 배열은 존재하지 않는다. 
		
		String hobby = String.join(", ",request.getParameterValues("hobby"));
		
		// 3. 위의 정보를 한번에 묶어서 전달할 VO 생성하기 
		
		Member joinMember = new Member(userId, userPwd, userName, gender,
								age, email, phone, address, hobby);
		// 생성자를 만들면 setter 필요없이 쫙 넣을수가 있다. Member() 안에
		
		//joinMember.setUserId(userId);
		//joinMember.setUserPwd(userPwd);
		//joinMember.setUserName(userName);
		//joinMember.setGender(gender);
		//joinMember.setAge(age);
		//joinMember.setEmail(email);
		//joinMember.setPhone(phone);
		//joinMember.setAddress(address);
		
		// JSP에서 들어온 정보 확인해보기 
		System.out.println("가입한 회원 정보 : " + joinMember);
		
		// 4. 회원 가입 서비스 실행하기 
		MemberService ms = new MemberService();
		
		int result = ms.insertMember(joinMember);
		
		if ( result > 0) {
			// 회원 가입 성공 !
			System.out.println("서블릿 : 회원가입 성공 확인 !");
			response.sendRedirect("index.jsp");
		} else {
			System.out.println("서블릿 : 회원 가입 실패 !");
			
			// 에러 페이지 전송
			
			RequestDispatcher view 
							= request.getRequestDispatcher("views/common/errorPage.jsp");
			
			request.setAttribute("error-msg", "회원 가입 실패");
			
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
