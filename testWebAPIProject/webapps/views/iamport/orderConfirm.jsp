<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int price = Integer.parseInt(request.getParameter("quan"))
			* Integer.parseInt(request.getParameter("price"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제 완료</title>
</head>
<body>
	<h1>결제 완료 : <%= request.getParameter("item") %></h1>
	<p>
		구매 갯수 : <%= request.getParameter("quan") %> <br>
		총 구매금액 : <%= price %> <br>
	</p>
</body>
</html>