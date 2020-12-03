package com.kh.jsp.notice.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.jsp.common.exception.NoticeException;
import com.kh.jsp.notice.model.service.NoticeService;
import com.kh.jsp.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeInsert
 */
@WebServlet("/nInsert.no")
public class NoticeInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 공지사항의 제목 , 작성자 , 내용
		String ntitle = request.getParameter("title");
		String nwriter = request.getParameter("userId");
		String ncontent = request.getParameter("content");
		
		// 날짜의 경우 화면에서 문자열로 온다.
		// "2020-10-14"
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		
		Date ndate = null;
		
		try {
			ndate = new Date(sdf.parse(request.getParameter("date")).getTime());

		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		Notice n = new Notice();
		
		n.setNtitle(ntitle);
		n.setNcontent(ncontent);
		n.setNwriter(nwriter);
		n.setNdate(ndate);
		
		NoticeService ns = new NoticeService();
		
		try {
			
			ns.insertNotice(n);
			response.sendRedirect("selectList.no");
		
		} catch (NoticeException e) {
		
			e.printStackTrace();
			request.setAttribute("exception", e);
			
			request.setAttribute("error-msg", "공지사항 등록 실패 !");
			
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
