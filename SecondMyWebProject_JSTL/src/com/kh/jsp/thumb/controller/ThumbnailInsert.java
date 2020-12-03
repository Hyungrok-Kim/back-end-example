package com.kh.jsp.thumb.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.jsp.thumb.common.MyRenamePolicy;
import com.kh.jsp.thumb.model.service.ThumbnailService;
import com.kh.jsp.thumb.model.vo.Attachment;
import com.kh.jsp.thumb.model.vo.Thumbnail;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class ThumbnailInsert
 */
@WebServlet("/insert.tn")
public class ThumbnailInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThumbnailInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 전달 받을 파일의 크기 설정 
		int maxSize = 1024 * 1024 * 10; // 10MB
		
		// 2. 멀티파트 전달 확인 
		if(! ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("exception", new Exception("사진 게시글 등록 오류"));
			request.setAttribute("error-msg", "멀티파트 형식이 아니다.");
			
			request.getRequestDispatcher("views/common/errorPage.jsp")
													.forward(request, response);
		}
		
		// 3. 저장할 경로 설정
		String root = request.getServletContext().getRealPath("/resources");
		String savePath = root + "/thumbnailUploadFiles/";
		
		// 4. 멀티파트 객체 준비 
		MultipartRequest mre = new MultipartRequest(request, savePath, maxSize, "UTF-8", 
									new MyRenamePolicy());
		
		// 다중 파일 업로드 처리하기 
		// 원본 파일 명과 변경된 파일 명 담기 (개발자는 헷갈리지않게 원본 파일 명도 같이)
		ArrayList<String> originNames = new ArrayList<>();
		ArrayList<String> changeNames = new ArrayList<>();
		
		// 화면에서 전달한 파일 이름 가져오기 
		Enumeration<String> files = mre.getFileNames();
		
		while(files.hasMoreElements()) {
			// 가져온 파일 하나씩 꺼내오기
			String tagName = files.nextElement();
			
			originNames.add(mre.getOriginalFileName(tagName));
			changeNames.add(mre.getFilesystemName(tagName));
			
			/*
			System.out.println("tagName : " + tagName);
			System.out.println(originNames);
			System.out.println(changeNames);
			*/
		}
		
		// ------------여기까지 문제 없었음 (중간 점검) ------------------- //
		// 썸네일 게시글 저장하기 
		Thumbnail t = new Thumbnail();
		t.setBtitle(mre.getParameter("title"));
		t.setBcontent(mre.getParameter("content"));
		t.setBwriter(mre.getParameter("userId"));
		
		// Attachment 객체를 생성 후 파일 정보를 저장 하기 
		ArrayList<Attachment> list = new ArrayList<>();
		
		for(int i = originNames.size() - 1; i >= 0; i--) {
			// 기존에 역순으로 가져온 파일들을 올바른 순서로 재정렬
			Attachment at = new Attachment();
			
			at.setFilepath(savePath);
			at.setOriginname(originNames.get(i));
			at.setChangename(changeNames.get(i));
			
			list.add(at);
		}
		System.out.println("list : " + list);
		
		ThumbnailService ts = new ThumbnailService();
		
		int result = ts.insertThumbnail(t, list);
		
		if( result > 0) {
			response.sendRedirect("selectList.tn");
		} else {
			request.setAttribute("exception", new Exception("사진 추가 에러 !"));
			request.setAttribute("error-msg", "게시글 저장 실패 ");
		
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
