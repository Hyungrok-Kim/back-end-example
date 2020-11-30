<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.kh.jdbc.member.model.vo.*" %>
    
<%
	Member m = (Member)session.getAttribute("member"); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 화면</title>
</head>
<body>
	<%@ include file="common/header.jsp" %>
	<% if(m == null) {%>
	
	
	<h3>로그인 화면</h3>
	
	<form action="/jdbc/login.do" method="post">
		
		ID : <input type="text" name="userId"> <br>
		PW : <input type="password" name="userPwd" /> <br>
		<input type="submit" value="로그인하기" >
	</form>
	
	<%} else { %>
	
		<h3>로그아웃 화면</h3>
		
		<h3>
			<span style="color:blue"> <%= m.getUserName()%>님, 환영합니다.</span>
		</h3>
		<p>
			만약 로그아웃을 하시려면, <br />
			여기를 클릭해 주세요.
		</p>
		
		<button type="button" id="logoutBtn" onclick="logout();">로그아웃</button>
		
		<script>
			function logout(){
				location.href = '/jdbc/logout.do';
			}
		</script>
	<% } %>
	<br><br><br><br><br>
	<br><br><br><br><br>
	<%@ include file="common/footer.jsp" %>
</body>
</html>






