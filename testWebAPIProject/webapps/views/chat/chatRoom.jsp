<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String userId = request.getParameter("userId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채팅방 만들기 ver.2</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-3.5.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

<script>
var host = location.host;//localhost이거나, 서버컴퓨터 ip주소와 포트번호를 가져옴.
var ws = new WebSocket('ws://'+ host +'<%=request.getContextPath()%>/chat/helloWebSocket');

//웹 소켓을 통해 연결이 이루어 질 때 동작할 메소드
ws.onopen = function(e){
	console.log("open!");
};

// 서버로부터 메시지를 전달 받을 때 동작하는 메소드
ws.onmessage = function(e){
 	console.log("message!:"+e.data);
 	var o = JSON.parse(e.data);
 	
 	//dm 메세지인 경우
 	if(o.type == 'dm'){
 		alert(o.sender+" : "+o.msg);
 	}
 	//일반 메세지인 경우
 	else {
	 	var html = '<li class="list-group-item"><span class="badge badge-dark">'+o.sender+'</span> '+o.msg+'</li>';
	 	$("#chat-container ul").append(html); 		
 	}
 	
 	//스크롤처리
 	$('#msg-container').scrollTop($("#msg-container").prop('scrollHeight'));
 	
}

// 서버에서 에러가 발생할 경우 동작할 메소드
ws.onerror = function(event){
	console.log("error!");
}
// 서버와의 연결이 종료될 경우 동작하는 메소드
ws.onclose = function(event){
	console.log("close!");
}

$(function(){
	
	//일반 메세지 보내기
	$("#send").click(function(){
		var o = {
				type:"message",
				msg: $("#msg").val(),
				sender: "<%=userId%>",
				time: Date.now()
		}
		var s = JSON.stringify(o);
		ws.send(s);
		
		$("#msg").val('').focus();
	});
	
	//엔터키 눌렀을때 전송하기
	$("#msg").keyup(function(e){
		if(e.key == 'Enter'){
			$("#send").trigger('click');
		}
	});
	
	//접속자 명단 확인하기
	$("#btn-userList").click(function(){
		$.ajax({
			url: "<%=request.getContextPath()%>/chat/userList.chat",
			dataType: "json",
			success: function(data){
				var len = data.length;
				alert(data+" ("+len+"명)");
			}
		});
	});
	
	//dm전송을 위한 접속자 명단 가져오기
	$("#dm-client").focus(function(){
		$.ajax({
			url: "<%=request.getContextPath()%>/chat/userList.chat",
			dataType: "json",
			success: function(data){
				console.log(data);
				$("#dm-client").html('<option value="" disabled>접속자 목록</option>');				
				
				for(var i in data){
					$("#dm-client").append('<option value="'+data[i]+'">'+data[i]+'</option>');i
				}
			}
		});
	});
	
	//dm전송
	$("#dm-send").click(function(){
		if($("#dm-msg").val().trim().length == 0 ||
		   $("#dm-client option:selected").val() == ""){
			console.log("dm is not gonna sent!");
			return;
		}
		
		
		var dm = {
				type:"dm",
				msg: $("#dm-msg").val(),
				sender: "<%=userId%>",
				receiver: $("#dm-client option:selected").val(),
				time: Date.now()
		}
		
		$.ajax({
			url: "<%=request.getContextPath()%>/chat/sendDM.chat",
			data: {dm: JSON.stringify(dm)},
			dataType: "json",
			success: function(data){
				console.log(data.result);
			}, 
			complete: function(){
				//dm input태그 초기화
				$("#dm-msg").val('');
			}
		});
	});	
});
</script>
<style>
#chat-container{
	width: 600px;
	margin: 0 auto;
	padding: 10px;
}
#msg-container {	
	height: 600px;
	overflow-y: scroll;
}
#btn-userList{
	margin: 10px 100px;
}
#dm-container{
	margin: 50px 0 100px;
}
</style>
</head>
<body>
	<div class="jumbotron jumbotron-fluid">
	  <div class="container">
		<h1 class="display-4">Websocket Chatroom</h1>
		<p class="lead">This is a modified jumbotron that occupies the entire horizontal space of its parent.</p>
	  </div>
	</div>
	
	<button type="button" class="btn btn-success btn-small" id="btn-userList">현재사용자확인</button>
	
	<section id="chat-container">	
		<div id="msg-container">
			<ul class="list-group list-group-flush">
			  <!-- <li class="list-group-item">Cras justo odio</li>
			  <li class="list-group-item">Dapibus ac facilisis in</li>
			  <li class="list-group-item">Morbi leo risus</li>
			  <li class="list-group-item">Porta ac consectetur ac</li>
			  <li class="list-group-item">Vestibulum at eros</li> -->
			</ul>
		</div>
		
		<div class="input-group mb-3" >
		  <input type="text" class="form-control" id="msg">
		  <div class="input-group-append">
			<button class="btn btn-outline-secondary" type="button" id="send">Send</button>
		  </div>
		</div>
		
		<hr style="margin:30px 0" />

		<!-- dm관련 섹션 -->		
		<div id="dm-container" class="input-group mb-3">
			<div class="input-group-prepend">
			  <label class="input-group-text" for="dm-client">DM</label>
			</div>
			<select class="custom-select" id="dm-client">
				<option value="" disabled selected>접속자 목록</option>
			</select>
		</div>
		<div class="input-group mb-3">
			<input type="text" class="form-control" id="dm-msg">
			<div class="input-group-append">
				<button class="btn btn-outline-secondary" type="button"
					id="dm-send">DM-Send</button>
			</div>
		</div>
		<br><br><br><br>
	</section>
</body>
</html>