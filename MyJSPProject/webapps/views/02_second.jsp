<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Date" %>

<%@ page import="java.util.*, java.text.SimpleDateFormat"%>
    
    
<%--
	지시자 태그는 크게 page, include, taglib 구분자로 나뉘어 진다.
	그 중, page 태그는 해당 jsp 페이지에서 사용하는 설정들을 담당하는데,
	선언의 중복, 충돌을 막기 위해 단 한 번만 선언할 수 있다. 
	단, page를 통해 import를 선언 시에는 별도로 추가 선언이 가능하다.
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자바 라이브러리 사용하기</title>
</head>
<body>
	<h1>자바 라이브러리 사용하기</h1>
	<%
		Date now = new Date(); // Date를 import해야되는데 그 역할을 담당하는게 page 태그
		
		String date = String.format("%tF", now);
		String today = String.format("%tY년 %tm월 %td일", now, now, now);
		String time = String.format("%tp %tT", now, now);
		
		
				/*
				
				날짜  타입  format  ||               설명
				------------------------------------------------
				%tF				    날짜를 yyyy-mm-dd 형식으로 포맷
				%tT					날짜의 시각을 HH:MM:SS 형식으로 포맷.
				------------------------------------------------------
				%tY					4자리 년도만 출력 
				%ty					2자리 년도
				%tB					월의 이름(January, February, March...)
				%tm					월을 01,02,03 ~12 로 출력
				%td					일수를 1~31 로 출력
				%te					%td 와 같음.
				%tA					요일명 출력
				---------------------------------------------------
				%tp					오전, 오후를 출력
				%tk					시간을 0~23 으로 출력.
				%tl					시간을 1~12 로 출력.
				%tM					분을 00 ~59 로 출력.
				%tS					초를 00 ~ 59 로 출력.
				-----------------------------------------------------
				%tZ					타임존을 출력 (한국은 KST)
				---------------------------------------------------
				*/
	%>
	
	<ul>
		<li>오늘 날짜 : <%= date %></li>
		<li>현재 시간 : <%= time %></li>
		<li>나만의 형식 : <%= today %></li>
	</ul>
</body>
</html>