<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String failMsg = (String)request.getAttribute("fail-msg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 실패 ㅠㅜ</title>
</head>
<body>
	
	<%@ include file="/views/common/header.jsp" %>
	
	<section style="padding : 10px;">
	
		<br><br>
		
		<h1 style="color:red;">로그인 실패!</h1>
		
		<p>
			실패 사유 : <%= failMsg %> <br>
			관리자에게 연락하세요.
		</p>
		
		<br><br>
		
		<a href="/jdbc/index.jsp">첫 페이지로 돌아가기</a>
	
	</section>
	
	
	<%@ include file="/views/common/footer.jsp" %>
	
</body>
</html>