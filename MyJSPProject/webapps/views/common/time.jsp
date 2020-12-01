<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.*" %>
<%
	// 스크립트릿 태그
	Date current = new Date();
	String myDate = String.format("%tY-%tm-%td %tA",
			          current, current, current, current);
%>
<span style="color : hotpink;"><%= myDate %></span>
 