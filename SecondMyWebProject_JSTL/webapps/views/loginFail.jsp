<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 실패 화면!!</title>
</head>
<body>
	<c:import url="/views/common/header.jsp" />
	
	<h1>로그인 실패!!</h1>
	
	<h3 style="color : red;">로그인에 실패하였습니다.</h3>
	<p>
		아이디나 비밀번호를 확인해보시고, 만약 이상이 없다면<br>
		관리자(여러분)에게 문의해주세요!
	</p>
	
	<br /><br /><br /><br />
	
	<a href="/myWeb/index.jsp">처음으로 돌아가기</a>
	
	<c:import url="/views/common/footer.jsp" />
</body>
</html>