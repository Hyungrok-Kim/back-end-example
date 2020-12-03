package com.kh.jsp.thumb.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.jsp.boardComment.model.service.BoardCommentService;
import com.kh.jsp.boardComment.model.vo.BoardComment;
import com.kh.jsp.thumb.model.service.ThumbnailService;

/**
 * Servlet implementation class ThumbnailSelectOne
 */
@WebServlet("/selectOne.tn")
public class ThumbnailSelectOne extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThumbnailSelectOne() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		// 만약 DB로부터 가져올 객체가 여러 개일 경우 
		// (썸네일(BOARD)과 첨부파일(ATTACHMENT) 가져오는 상황 - DB로 따지면 JOIN하는 상황) 
		// 2가지 방법으로 처리할 수 있다. 
		// 1. 두 객체를 모두 저장할 수 있는 통합형 VO를 만드는 방법 
		//	  장점 : - 한 번 객체를 만들어 두면 사용하기가 편리하다.
		//	  단점 : - 객체 자체의 크기가 커져 메모리 사용률이 올라 
		//	  		서비스가 느려질 수 있다는 단점.
		
		// 2. Map 객체를 활용하여 여러 클래스를 key와 value로 각각 묶어서 
		// 	    가져오는 방법 
		//	  - 여러 객체가 각각의 VO 형태를 유지하여 언제든지 필요한 
		//		개별 VO들을 상황에 따라 묶어 사용할 수 있다. 
		//		ex) "thumb" : Thumbnail / "att" : Attachment 으로 저장하면 getThumb, getAtt 등으로 선택해서 
		//		사용 가능.
		//		-단점 : key로 객체를 간접 접근하기 때문에 , 실제 객체의 내용이 변경되어도 
		//		이를 바로 확인하기 어렵다.
		
		HashMap<String, Object> thumb 
			= new ThumbnailService().selectOne(bno);
		
		ArrayList<BoardComment> clist = new BoardCommentService().selectList(bno);
		
		
		String page = "";
		
		if(thumb != null && thumb.get("thumbnail") != null) {
			request.setAttribute("thumbnail", thumb.get("thumbnail"));
			request.setAttribute("fileList", thumb.get("attachment"));
			request.setAttribute("clist", clist);
			
			page = "views/thumbnail/thumbnailDetail.jsp";
			
		} else {
			request.setAttribute("exception", new Exception("게시글 상세 조회 실패!"));
			
			request.setAttribute("error-msg", "게시글 상세 조회 실패!!");
			
			page = "views/common/errorPage.jsp";
			
		}
		
		request.getRequestDispatcher(page).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
