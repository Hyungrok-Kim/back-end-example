package com.kh.jsp.board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.jsp.board.model.service.BoardService;
import com.kh.jsp.board.model.vo.Board;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class BoardDelete
 */
@WebServlet("/bDelete.bo")
public class BoardDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 전송받을 파일 최대 크기 
		int maxSize = 1024 * 1024 * 10;
		
		// 2. 멀티파트 확인 
		if(! ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("error-msg", "multipart 전송 아님 !");
			
			request.getRequestDispatcher("views/common/errorPage.jsp")
				   .forward(request, response);
			
		}
		
		// 3. 저장 폴더 (후에 삭제 담당 폴더로 구현 확장이 가능함) 
		String root = request.getServletContext().getRealPath("/"); // ServletContext는 webapps의 위치를 나타냄
		String savePath = root + "resources/boardUploadFiles";
		
		// 4. 멀티파트 객체 생성
		MultipartRequest mre = new MultipartRequest(request, savePath, maxSize, 
													"UTF-8", new DefaultFileRenamePolicy() );
		
		int bno = Integer.parseInt(mre.getParameter("bno"));
		
		BoardService bs = new BoardService();
		
		Board b = bs.selectOne(bno);
		
		// 삭제할 게시글의 첨부파일까지 삭제 
		if( b.getBoardfile() != null ) {
			new File(b.getBoardfile()).delete();
		}
		
		int result = bs.deleteBoard(bno);
		
		if(result > 0) {
			
			response.sendRedirect("selectList.bo");
		
		}else {
			
			request.setAttribute("error-msg", "삭제 실패 !");
			
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			
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
