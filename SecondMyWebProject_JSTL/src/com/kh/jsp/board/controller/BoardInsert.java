package com.kh.jsp.board.controller;

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
 * Servlet implementation class BoardInsert
 */
@WebServlet("/bInsert.bo")
public class BoardInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 파일 업로드용 서블릿
		// MultipartRequest
		
		// 1. 전송받은 최대 크기 설정하기 
		// 사이트를 만들때 1:1이 아닌 여러 사람이 사용할 수 있는 서버를 만들어야하는데  
		// 10MB -> 개인이 사용할 수 있는 용량 최대치 -> Byte 단위로 작성해야 함
		// 1024Byte -> 1KB / 1024KB -> 1MB 
		
		int maxSize= 1024 * 1024 * 10;
		
		// 2. multipart/form-data 형식으로 인코딩 되어 왔는지 확인 <- views/boardInsertForm에서 그렇게 보냈음
		if(!ServletFileUpload.isMultipartContent(request)) {
			//에러 페이지 
			request.setAttribute("error-msg", "multipart/form-data형식으로 전송되지않았다.");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			
		}
		
		// 3. 받아온 파일을 저장할 경로 설정하기 
		
		String root = request.getServletContext().getRealPath("/");
		String savePath = root + "resources/boardUploadFiles";
		
		// 4. 위에서 설정한 정보를 바탕으로 MultipartRequest
		/*
		 *  @param request / 속성 변경을 위한 원본 객체
		 *  @param savePath / 파일 저장 경로 
		 *  @param maxSize  / 저장 가능한 파일 최대크기 
		 *  @param encoding / ex) "UTF-8"
		 *  @param FileRenamePolicy / 원본파일 이름을 서버에서 관리하기 편한 이름으로 바꾸는 정책 
		 * */
		MultipartRequest mre = new MultipartRequest(request, savePath, 
													maxSize, "UTF-8",
													new DefaultFileRenamePolicy());
		
		// ** DefaultFileRenamePolicy 는 
		// 	    만약 폴더에 이미 있는 파일의 이름이 또 들어 올 경우
		// 	    해당 이름을 바꿔주는 정책 
		// ex) 새 폴더 --> 새 폴더1 --> 새 폴더2 ...
		
		// -------------설정 끝 -----------//
		
		// 5-1. 파일이 아닌 기본 전송값 처리 
		String title = mre.getParameter("title");
		String content = mre.getParameter("content");
		String userId = mre.getParameter("userId");
		
		// 5-2. 파일 저장 및 정보 처리하기 
		// 		JSP로 부터 전달받은 파일을 먼저 저장하고 
		//		해당 파일의 이름을 따온다.
		
		String filename = mre.getFilesystemName("file");
		
		// 6. 전달받은 값을 서비스로 넘기기 
		Board b = new Board(title, content, userId, filename);
		
		int result = new BoardService().insertBoard(b);
		
		if(result > 0) {
			response.sendRedirect("selectList.bo");
		}else {
			request.setAttribute("error-msg", "게시글 작성 실패 !");
			
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
