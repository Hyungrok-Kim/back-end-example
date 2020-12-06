<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>웹 소켓 응용</title>
<script src="<%=request.getContextPath()%>/resources/js/jquery-3.5.1.min.js"></script>
</head>
<body>
<h2><a href="javascript:$('.input-container:eq(0)').show().find('#userId').focus();">Hello WebSocket</a></h2>
	<div class="input-container">
		<input type="text" id="userId" placeholder="접속아이디" /> <button onclick="goChat();">접속</button>
	</div>		
<script>
function goChat(){
	
	var userId = $("#userId").val().trim();
	if(userId.length!=0)
		location.href = "<%=request.getContextPath()%>/chat/chatRoom.chat?userId="+userId;
}
</script>
</body>
</html>