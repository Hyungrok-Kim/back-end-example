package com.kh.jsp.thumb.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

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
 * Servlet implementation class ThumbnailUpdate
 */
@WebServlet("/update.tn")
public class ThumbnailUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThumbnailUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int maxSize = 1024 * 1024 * 10;
		
		if(!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("error-msg", "멀티파트 형식이 아님");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
		
		String root = request.getServletContext()
							 .getRealPath("/resources");
							 
		String savePath = root + "/thumbnailUploadFiles/";
		
		MultipartRequest mre = new MultipartRequest(request, savePath, 
									maxSize, "UTF-8", new MyRenamePolicy());
		
		int bno = Integer.parseInt(mre.getParameter("bno"));
		
		ThumbnailService ts = new ThumbnailService();
		
		HashMap<String, Object> hmap = ts.selectOne(bno);
		
		ArrayList<String> originFiles = new ArrayList<>();
		ArrayList<String> saveFiles = new ArrayList<>();
		
		// 전송한 파일 정보 가져오기 
		Enumeration<String> files = mre.getFileNames();
		
		while(files.hasMoreElements()) {
			String tagName = files.nextElement();
			
			originFiles.add(mre.getOriginalFileName(tagName)); // 파일의 원본 이름 받기
			saveFiles.add(mre.getFilesystemName(tagName));
			
		}
		
		// 이전 사진 게시글의 내용 변경하기 
		Thumbnail t = (Thumbnail)hmap.get("thumbnail"); // hmap으로 가져오면 변수 최상위인 객체에서 가져오기 때문에 thumbnail으로 다운캐스팅을 시켜줘야함.
		t.setBtitle(mre.getParameter("title"));
		t.setBcontent(mre.getParameter("content"));
		t.setBwriter(mre.getParameter("userId"));
		
		ArrayList<Attachment> list = (ArrayList<Attachment>)hmap.get("attachment");
		
		// 수정이 완료되었을 때 
		// 이전에 저장되었던 파일 삭제 시키기 
		
		for(int i = originFiles.size() - 1 ; i >= 0; i--) {
			int j = originFiles.size() - i - 1;
			
			if(originFiles.get(i) != null) {
				// 새로 파일을 등록했다면, 이전의 파일을 삭제하고 , 새로운 파일로 저장하기 
				new File(savePath + list.get(j).getChangename()).delete();
				
				list.get(j).setFilepath(savePath);
				list.get(j).setOriginname(originFiles.get(i));
				list.get(j).setChangename(saveFiles.get(i));
			}
		}
		
		int result = ts.updateThumbnail(t, list);
		
		if(result > 0) {
			response.sendRedirect("selectList.tn");
		}else {
			request.setAttribute("error-msg", "실패");
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
