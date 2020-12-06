package com.kh.ws.chat;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

/**
 * Configurator 란 사용자 연결을 위한 websocket 객체를 생성할 때 기본적으로 설정할 정보들을 작성하는 클래스
 * 
 * Configurator 클래스를 상속 받을 경우, modifyHandshake() 메소드를 오버라이드함. HttpSession에 접근할 수
 * 있음
 * 
 * 1. HandshakeRequest : HttpServletRequest 역할. 하지만, casting불가함
 *  2. HandshakeResponse : HttpServletResponse 역할.
 * 
 */
public class HelloWebSocketConfigurator extends Configurator {

	@Override
	public void modifyHandshake(ServerEndpointConfig config, 
							    HandshakeRequest request, 
							    HandshakeResponse response) {

		String userId = (String) ((HttpSession) request.getHttpSession()).getAttribute("userId");
		config.getUserProperties().put("userId", userId);

		System.out.println("config : userId=" + config.getUserProperties().get("userId"));
	}

}
