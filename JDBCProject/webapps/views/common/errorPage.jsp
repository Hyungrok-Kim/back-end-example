<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>에러페이지</title>
</head>
<body>
	<%@ include file="/views/common/header.jsp" %>
	
	<h1>ERROR 발생 !</h1>
	
	<p>
			<%= (String)(request.getAttribute("error-msg")) %>
	</p>
	<p>
		관리자에게 문의하세요.
	</p>
	<%@ include file="/views/common/footer.jsp" %>
</body>
</html>