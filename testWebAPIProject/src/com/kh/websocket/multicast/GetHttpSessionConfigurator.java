package com.kh.websocket.multicast;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

/*
 * Configurator 란
 * 사용자 연결을 위한 websocket 객체
 * 생성할 때 기본적으로 설정할 정보들을 작성하는 클래스
 * 
 * Configurator 클래스를 상속 받을 경우
 * modifyHandshake() 를 통해
 * WebSocket 객체의 Session을 
 * HttpSession과 연결해줄 수 있다.
 * 
 */

public class GetHttpSessionConfigurator extends Configurator {

	@Override
	public void modifyHandshake(ServerEndpointConfig sec,
			                    HandshakeRequest request,
			                    HandshakeResponse response) {
		// config에서 기존 설정을 가져 올 때
		// request : HttpServletRequest 와 같은 역할을 수행하는 객체
		// response : HttpServletResponse와 같은 역할을 수행하는 객체
		
		String chat_id
		  = (String)(
				(HttpSession)request.getHttpSession())
		  			.getAttribute("chat_id"); 
		
		sec.getUserProperties().put("chat_id", chat_id);
		
		System.out.println("config : " + chat_id);
	}	
}







