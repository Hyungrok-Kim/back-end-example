<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>에러 페이지</title>
</head>
<body>
	<h1>에러 발생!!</h1>
	<p style="color:red;">
		<%= exception.getMessage() %>
	</p>
	<p>
		에러가 발생했습니다. 관리자에게 문의하세요.
	</p>
</body>
</html>