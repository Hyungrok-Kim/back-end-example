<%-- JSP의 주석 태그 --%>

<%-- 지시자 태그 (Directive Tag) <%@ %> --%>
<%-- 현재 JSP 파일의 문자셋 등의 설정을 명시하는 태그  --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- 선언자 태그 (Declaration Tag) --%>
<%-- 현재 JSP에서 사용할 자바 변수 등을 선언하는 역할 --%>
<%!
	// 변수를 미리 선언하여 사용할 값 등록을 할 수 있는 영역
	/* 범위 주석도 가능하다. */
	int num1 = 10;
	int num2 = 100;
	
%>    
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>첫번째 JSP 페이지</title>
</head>
<body>
	<h1>첫번째 JSP 페이지</h1>
	<%-- 주석 태그 (Comments Tag) --%>
	<%-- 
		 JSP가 서블릿으로 변할 때 무시되는 태그
	 --%>
	 <!-- 일반 HTML 태그 -->
	 
	 <%-- 자바 코드 구현 태그 (Scriptlet Tag) --%>
	 <%
	 	// 자바의 일반 소스 코드를 작성하는 영역
	 	int sum = 0;
	 	
	 	for(int i = 1; i < 11; i++){
	 		sum += i;
	 	}
	 %>
	 
	 <%-- 표현 태그 (Expression Tag) --%>
	 <%-- 자바로 생성된 코드의 값을 출력하는 역할 --%>
	 <h4>sum 값 확인 : <%= sum %></h4>
	 <h4>num1 + num2 = <%= num1 + num2 %></h4>
</body>
</html>