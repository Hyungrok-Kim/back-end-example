package com.kh.jsp.notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.jsp.common.exception.NoticeException;
import com.kh.jsp.notice.model.service.NoticeService;
import com.kh.jsp.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeSearch
 */
@WebServlet("/searchNotice.no")
public class NoticeSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 검색 옵션(condition)
		String condition = request.getParameter("con");
		
		// 검색 키워드(keyword)
		String keyword = request.getParameter("keyword");
		
		System.out.println(condition + " : "+ keyword);
	
		ArrayList<Notice> list = new ArrayList<>();
		
		NoticeService ns = new NoticeService();
		
		String page = null;
		
		try {
			list = ns.searchNotice(condition, keyword);
			
			request.setAttribute("list", list);
			
			page = "views/notice/noticeList.jsp";
			
		} catch (NoticeException e) {
			
			request.setAttribute("exception", e);
			request.setAttribute("error-msg", "");
			
			page = "views/common/errorPage.jsp";
			
			e.printStackTrace();
		} finally {
			
			request.getRequestDispatcher(page).forward(request, response);
			
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
