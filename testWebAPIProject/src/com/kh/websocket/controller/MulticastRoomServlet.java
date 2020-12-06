package com.kh.websocket.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MulticastRoomServlet
 */
@WebServlet("/multiChatRoom.do")
public class MulticastRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
    public MulticastRoomServlet() { }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// doGet(request, response);
		
		request.setCharacterEncoding("UTF-8");
		
		// 참여할 채팅방 이름
		String room = request.getParameter("chatRoom");
		
		// 사용자 아이디(닉네임) 가져오기
		String id = request.getParameter("chat_id");
		
		HttpSession session
		 = request.getSession();
		
		session.setAttribute("chat_id", id);
		
		ServletContext application
		 = request.getServletContext();
		
		ArrayList<String> roomList
		 = (ArrayList<String>)application.getAttribute("roomList");
		
		// 만약에 새로운 채팅방을 개설했다면
		// 해당하는 채팅방으로 현재 방을 설정하고
		// 기존의 채팅방에 새로운 채팅방을 추가한다.
		
		if(room.equals("newRoom")) {
			room = request.getParameter("roomName");
			
			roomList.add(room);
			
			application.setAttribute("roomList", roomList);
		}
		
		// 정상적으로 등록하여 방 리스트가 갱신되었다면
		// 채팅방으로 이동하기
		if(!roomList.isEmpty()) {
			request.setAttribute("room", room);
			
			request.getRequestDispatcher("views/websocket/chatRoom.jsp")
			.forward(request, response);
		}
	}
}






























