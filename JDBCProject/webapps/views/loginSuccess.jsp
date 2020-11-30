<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.jdbc.member.model.vo.*" %>
<%
	// 로그인 정보 받아오기
	// Member m = (Member)request.getAttribute("member");

	Member m = (Member)session.getAttribute("member");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 성공!!!</title>
</head>
<body>

	<%@ include file="/views/common/header.jsp" %>
	
	<section style="background : lightgreen;">
	
	<h1>로그인 성공 !!!</h1>
	
	<h3>
		<%= m.getUserName() %> 님, 환영합니다!
	</h3>
	<p>
		로그인 회원 정보 : <br>
		<%= m.toString() %>
	</p>
	
	</section>
	
	<%@ include file="/views/common/footer.jsp" %>
	
	
	
	
	

</body>
</html>