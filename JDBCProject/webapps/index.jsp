<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.kh.jdbc.member.model.vo.*" %>
<%
	Member m = (Member)session.getAttribute("member");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JDBC 테스트</title>
</head>
<body>
<%@ include file="views/common/header.jsp" %>
	<section>
	<% if (m != null ) { %>
		<h3> <%= m.getUserName() %> 님, 오늘도 좋은 하루 되세요.</h3>
	<%}  %>
		<h3>
			<a href="views/loginForm.jsp">로그인 / 로그아웃</a>
		</h3>
	<%if( m == null) {%>
		
		<h3>
			<a href="views/member/memberJoin.jsp">회원 가입</a>
		</h3>
	
	<%} else {%>
		<h3>
			<a href="views/member/memberUpdate.jsp">회원 정보 수정</a>
		</h3>
	
	<% } %>
	
		<br /> <br /> <br /> <br /> <br />
		<br /> <br /> <br /> <br /> <br />
	</section>
<%@ include file="views/common/footer.jsp" %>
</body>
</html>


