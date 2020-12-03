package com.kh.jsp.board.controller;

import java.io.IOException;
import java.io.File;

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
 * Servlet implementation class BoardUpdate
 */
@WebServlet("/bUpdate.bo")
public class BoardUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		BoardService bs = new BoardService();
		
		// 1. 전송받을 최대 크기 설정
		int maxSize = 1024 * 1024 * 10; // 10MB;
		
		// 2. multipart/form-data 형식 확인
		if(! ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("error-msg", 
					             "multipart로 전송되지 않았습니다.");
			
			request.getRequestDispatcher("views/common/errorPage.jsp")
			       .forward(request, response);
		}
		
		// 3. 저장할 폴더 위치 설정
		String root = request.getServletContext().getRealPath("/");
		String savePath = root + "resources/boardUploadFiles";
		
		// 4. 멀티파트 객체 생성
		MultipartRequest mre = new MultipartRequest(
									request, savePath, maxSize, "UTF-8",
									new DefaultFileRenamePolicy()
				);
		
		// 5-1. 기본 전송값 처리
		int bno = Integer.parseInt(mre.getParameter("bno"));
		String title = mre.getParameter("title");
		String content = mre.getParameter("content");
		
		// 5-2. 파일 전송 후 저장
		String fileName = mre.getFilesystemName("file");
		
		// 5-3. 이전의 게시글 가져오기
		Board b = bs.selectOne(bno);
		
		// 만약 전달한 파일이 있다면 파일 변경,
		// 그렇지 않다면 이전 파일 사용하기
		if(fileName != null && fileName.length() > 0) {

			if(b.getBoardfile() != null) {
				// 만약 이전 파일이 있었다면 삭제
				File originFile = new File(savePath+"/"+b.getBoardfile());
				System.out.println("파일 삭제 확인 : " + originFile.delete());
			}
			
			b.setBoardfile(fileName);
		}
		
		b.setBtitle(title);
		b.setBcontent(content);
		
		// 6. 전달받은 값을 서비스로 넘기기
		int result = new BoardService().updateBoard(b);
		
		if(result > 0) {
			response.sendRedirect("selectList.bo");
		} else {
			request.setAttribute("error-msg", "게시글 수정 실패!");
			request.getRequestDispatcher("views/common/errorPage.jsp")
								.forward(request, response);
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
