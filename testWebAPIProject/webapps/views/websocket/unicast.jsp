<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유니캐스트</title>
<script src="/test/resources/js/jquery-3.5.1.min.js"></script>
<style>
 #messageWindow {
 	background:LightSkyBlue;
 	height:300px;
 	overflow:auto;
 }
 .chat_content {
 	background: rgb(255, 255, 102);
 	padding: 10px;
 	border-radius: 10px;
 	display: inline-block;
 	position: relative;
 	margin: 10px;
 	float: right;
 	clear: both;
 }
 
 .chat_content:after{
 	content: '';
	position: absolute;
	right: 0;
	top: 50%;
	width: 0;
	height: 0;
	border: 20px solid transparent;
	border-left-color: rgb(255, 255, 102);
	border-right: 0;
	border-top: 0;
	margin-top: -3.5px;
	margin-right: -10px;
 }
 
 .other-side {
 	background: white;
 	float:left;
 	clear:both;
 }
 
 .other-side:after{
 	content: '';
	position: absolute;
	left: 0;
	top: 50%;
	width: 0;
	height: 0;
	border: 20px solid transparent;
	border-right-color: white;
	border-left: 0;
	border-top: 0;
	margin-top: -3.5px;
	margin-left: -10px;
 }
</style>
</head>
<body>
	<h1>Unicast 란?</h1>
	<h3>각 사용자 간 1:1로 통신하는 방식</h3>
	<p>Ex) 개인 카톡, 전화 통화, 문자 메시지 등</p>
	
	나의 ID : <input type="text" id="chat_id" size="10"/> <br>
	상대방 ID : <input type="text" id="recvUser" size="10"/>
	&nbsp; 
	<button type="button" id="startBtn">채팅하기</button> <br>
	
	<!-- 채팅창 구현 부 -->
	
	<div style="display:none;" id="chatbox">
		<fieldset>
			<div id="messageWindow"></div> <br>
			<input type="text" id="inputMessage" onkeyup="enterKey();" />
			<input type="submit" value="보내기" onclick="send();"/>
			<button type="button" id="endBtn">나가기</button>
		</fieldset>
	</div>
	
	<script>
		$('#startBtn').click(function(){
			$('#chatbox').css('display','block');
			$(this).css('display', 'none');
			connection();
		});
		
		$('#endBtn').click(function(){
			$('#chatbox').css('display','none');
			$('#startBtn').css('display', 'inline');
			
			websocket.send($('#chat_id').val()
					+"|님이 채팅방을 퇴장하였습니다.");
			
			websocket.close();
		});
	
		// 상대방과 연결을 위한 웹 소켓 객체 생성하기
		
		/*
			        웹 소켓 동작 메소드
			1. open : 웹 소켓 객체를 생성 시에 동작하여
			          서버와 사용자를 연결해주는 메소드

			2. send : 서버에 특정 메시지를 전송하는 메소드
			
			3. message : 서버에서 데이터를 전달받는 메소드
			
			4. error : 서버에 데이터 전달, 
			                     혹은 데이터를 전달 받는 도중 에러가 발생할 
			                    경우 동작할 메소드

			5. close : 사용자가 서버로부터 연결을 끊을 경우
			                      동작하는 메소드
		
			***
			웹소켓 객체 생성 시 new WebSocket(서버 주소)하면
			웹소켓 객체가 생성되면서 바로 자동으로 연결된다.
			(open이 자동으로 실행된다.)
		*/
	
		// 채팅 창 내용 부분 변수
		var $textarea = $('#messageWindow');
		
		// 채팅 서버 변수
		var websocket = null;
		
		// 내가 보낼 문자열을 담은 input태그
		var $inputMessage = $('#inputMessage');
		
		function connection(){
			// 연결 시작 메소드
			
			websocket = new WebSocket('ws://localhost:8088'+
					'<%= request.getContextPath()%>/unicast');
		
			websocket.onopen = function(event){
				
				$textarea.html("<p class='chat_content'>"
						+$('#chat_id').val()+"님이 입장하셨습니다.</p><br>");
				
				// 웹 소켓을 통해 만든 채팅 서버에 참여한 내용을 메시지로 전달
				// 내가 보낼 때는 send / 받을 때는 message
				
				websocket.send($('#chat_id').val() + '|님이 입장하셨습니다.');				
			};
			
			// 서버로부터 메시지를 전달 받을 때 동작하는 메소드
			websocket.onmessage = function(event){
				onMessage(event);
			}
			
			// 서버에서 에러가 발생했을 때 동작할 메소드
			websocket.onerror = function(event) {
				onError(event);
			}
			
			// 서버와 연결이 종료될 경우 동작하는 메소드
			websocket.onclose = function(event) {
				// onClose(event);
			}
			
		};
		
		// 엔터키 입력 시 채팅 내용 전송
		function enterKey(){
			if(window.event.keyCode == 13) send();
		}
		
		// 서버로 메시지를 전달하는 메소드
		function send(){
			if($inputMessage.val() == ""){
				// 전달할 내용이 비어있는 경우
				alert("메시지를 입력해주세요.");
			} else {
				// 전달할 내용이 있을 경우
				$textarea.html(
						$textarea.html()
						+"<p class='chat_content'>나 : "
						+ $inputMessage.val() + "</p><br>");
				
				websocket.send($('#chat_id').val() 
						+ "|" + $inputMessage.val()); // "홍길동|안녕하세요"
				
				$inputMessage.val("");
			}
			
			$textarea.scrollTop($textarea.height());
		}
	
		// 서버로 부터 메시지를 받을 때 수행할 메소드
		function onMessage(event) {
			var message = event.data.split("|");
			
			// 보낸사람 ID
			var sender = message[0];
			
			// 전달한 내용
			var content = message[1];
			
			if(content == "" || !sender.match($('#recvUser').val())){
				// 전달할 글이 없거나, 내가 1:1 통신하려는 사람이 아닐 경우
				// 아무 내용도 실행하지 않는다.
			} else {
				// 전달 받은 내용이 있을 경우
				
				$textarea.html(
						$textarea.html() 
						+"<p class='chat_content other-side'>"
						+sender + ":" + content
						+"</p><br>");
				
				$textarea.scrollTop($textarea.height());
			}
		}
		
		function onError(event){
			alert(event.data);
		}
		
		function onClose(event){
			alert(event);
		}
	</script>
</body>
</html>












