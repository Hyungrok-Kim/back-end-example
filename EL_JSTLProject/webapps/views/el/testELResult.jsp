<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	request.setCharacterEncoding("UTF-8");
%>
<html>
<head>
<meta charset="UTF-8">
<title>EL 결과 페이지</title>
</head>
<body>
	<h1>EL 사용 결과</h1>
	<%-- <div style="border: 3px solid <%= request.getParameter("color")%>">
		<h1> 이름 : <%= request.getParameter("userName") %> </h1>
		<h1> 나이 : <%= request.getParameter("age") %></h1>
		<h1> 성별 : <%= request.getParameter("gender") %></h1>
		<h1> 선호색상 : <%= request.getParameter("color") %></h1>
	</div> --%>
	
	<div style="border : 3px solid ${param.color}">
		<h1> 이름 : ${param.userName }</h1>
		<h1> 나이 : ${param.age}</h1>
		<h1> 성별 : ${param.gender}</h1>
		<h1> 선호색상 : ${param.color}</h1>
	</div>
</body>
</html>