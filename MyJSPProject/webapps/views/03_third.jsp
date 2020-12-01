<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.*" %>
<%!
	// 선언자 태그 
	Date now = new Date();
	String today = String.format("%tY-%tm-%td %tA", now, now, now, now);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>include 사용하기 </title>
</head>
<body>
	<h1>include 사용하기</h1>
	<%--	<h3>현재 시간 : <%= today %></h3> --%>
	<%@ include file="common/time.jsp" %>
	
	<select>
		<option value="red">빨간색</option>
		<option value="blue">파란색</option>
		<option value="green">초록색</option>
	</select>

	
	
</body>
</html>
