<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 성공 화면!</title>
</head>
<body>
	<%@ include file="/views/common/header.jsp" %>
	
	<h1>로그인 성공!</h1>
	<h3><%= m.getUserName() %>님, 환영합니다!!</h3>
	<p>회원 정보 : <br>
	   <%= m.toString() %>
	</p>
	
	<%@ include file="/views/common/footer.jsp" %>
</body>
</html>








