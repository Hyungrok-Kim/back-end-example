package com.kh.ws.chat;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import com.google.gson.Gson;

/**
 * Servlet implementation class SendDMServlet
 */
@WebServlet("/chat/sendDM.chat")
public class SendDMServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.encoding
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		
		//2.parameter handling
		String dm = request.getParameter("dm");
		Map<String, Object> dmMap = new Gson().fromJson(dm, Map.class);
		System.out.println("dm@servlet="+dmMap);
		
		
		//3.business logic: 해당 사용자를 찾아서 메세지 전송
		String result = "";
		Map<String, Session> clients = HelloWebSocket.clients;
		Set<String> userList = clients.keySet();
		
		if(userList.contains(dmMap.get("receiver"))) {
			Session dmReceiver = clients.get(dmMap.get("receiver"));
			dmReceiver.getBasicRemote().sendText(dm);
			result = "DM을 성공적으로 전송했습니다.";
		}
		else {
			result = "해당 사용자가 현재 접속중이 아닙니다.";
		}
		
		//4.결과 전송
		Map<String, String> resultMap = new HashMap<>();
		resultMap.put("result", result);
		new Gson().toJson(resultMap, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
