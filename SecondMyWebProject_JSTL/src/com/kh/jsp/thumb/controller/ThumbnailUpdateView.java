package com.kh.jsp.thumb.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.jsp.thumb.model.service.ThumbnailService;

/**
 * Servlet implementation class ThumbnailUpdateView
 */
@WebServlet("/updateView.tn")
public class ThumbnailUpdateView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThumbnailUpdateView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		HashMap<String, Object> hmap = new ThumbnailService().getUpdateView(bno);
	
		String page = "";
		
		if(hmap != null && hmap.get("thumbnail") != null) {
			request.setAttribute("thumbnail", hmap.get("thumbnail"));
			request.setAttribute("fileList", hmap.get("attachment"));
			
			page = "views/thumbnail/thumbnailUpdateView.jsp";
			
		}else {
			request.setAttribute("exception", new Exception("실패!"));
			request.setAttribute("error-msg", "실패 !!");
			
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
