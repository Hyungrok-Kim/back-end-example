<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*" %>
<%
	ArrayList<String> roomList
	  = (ArrayList<String>) application.getAttribute("roomList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>멀티캐스트</title>
<script src="<%=request.getContextPath()%>/resources/js/jquery-3.5.1.min.js"></script>
<style>
	.createRoom {
		display:none;
	}
</style>
</head>
<body>
	<h1>멀티캐스트(Multicast)란? </h1>
	<h3>하나의 네트워크(연결 구성 그룹)안의 여러 사용자 중<br>
	       특정 그룹에 속한 사용자끼리 통신하는(소통) 1:다 의 통신 방식</h3>
    <p>Ex) 단체 카톡, 디스코드 톡, 게임 톡, 스카이프 등 </p>
    
    <br>
    
    <form action="/test/multiChatRoom.do" method="post">
    	<h3>참여할 채팅방 :
    	    <select id="chatRoom" name="chatRoom">
				<% for(String room : roomList) { %>
				<option value="<%= room %>"><%=room %></option>
				<% } %>
				<option value="newRoom">새로 개설하기</option>
			</select>
    	</h3>
    	
    	<h3>
    		사용할 닉네임 : <input type="text" name="chat_id" id="chat_id" />
    	</h3>
    	
    	<!-- 새로운 방 개설을 위한 코드 작성 -->
    	<h3 class="createRoom">
    		방 제목 : <input type="text"
    		               name="roomName"
    		               id="roomName" />
    	</h3>
    	
    	<button id="startBtn">참여하기</button> <br>
    	    
    </form>
    
    <script>
    	$('#chatRoom').on('change', function(){
    		if($(this).val() == "newRoom"){
    			$('#startBtn').text("개설하기");
    			
    			$('.createRoom').each(function(index, value){
    				$(this).css('display', 'block');
    			});
    		} else {
    			$('#startBtn').text("참여하기");
    			
    			$('.createRoom').each(function(){
    				$(this).css('display', 'none');
    			});
    		}
    	});
    </script>
    
    <br><br><br><br>
    <br><br><br><br>	
</body>
</html>











