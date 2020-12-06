package com.kh.websocket.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MulticastViewServlet
 */
@WebServlet("/multiView.do")
public class MulticastViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 현재 열려있는 채팅방 명칭 리스트
	public static ArrayList<String> roomList;
       
    public MulticastViewServlet() { }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	request.setCharacterEncoding("UTF-8");
    	
    	// 방의 리스트를 서버의 영역에 저장하기 위한 Context 객체 생성
    	// 사용자 모두가 해당 방을 공유하기 위해서...
    	ServletContext application
    	  = request.getServletContext();
    	
    	// 기존의 방 리스트를 받아오기
    	roomList
    	  = (ArrayList<String>)application.getAttribute("roomList");
    	
    	if(roomList == null || roomList.isEmpty()) {
    		
    		roomList = new ArrayList<String>();
    		
    		// 테스트용 방 3개
    		roomList.add("room1");
    		roomList.add("room2");
    		roomList.add("room3");
    		
    		application.setAttribute("roomList", roomList);
    	}
    	
    	if(!roomList.isEmpty()) 
    		response.sendRedirect("views/websocket/multicast.jsp");
    		
	}
}














