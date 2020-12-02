package com.kh.first.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 서블릿 연결 시 자바 파일을 화면(html)에 연결하는 방법
 * 1) web.xml에 서블릿 클래스를 등록하는 방법 
 * 2) @WebServlet("url주소") 행태로 등록하는 방법 ( v ) 
 * ** 둘은 같이 쓸 수 없다!
 * 
 * @author Kim hyung rok 
 */
@WebServlet("/test2.do")
public class TestServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		// 0.
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		
		PrintWriter out = response.getWriter();
		
		// 2. html 내용
		out.println("<html>");
		out.println("<head>");
		out.println("<title>전달결과</title>");
		out.println("</head>");
		out.println("<html>");
		out.println("<head>");
		out.println("<body>");
		out.println("<h1>로그인 전달 내용 확인</h1>");
		
		// 3. HTML에서 전송한 내용을 자바 변수로 받기
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		

		out.println(userId + "님, 로그인 환영합니다.! <br>");

		out.println("비밀번호 : " + userPwd + "(만료까지 7일)");
		
		out.println("</body>");
		out.println("</html>");
		
		// 4. 사용한 output 객체 반환(닫기:close)하기
		
		out.flush();  // 굳이 안해줘도 상관없지만 관용상 함
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
