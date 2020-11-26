<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	// 페이지 영역 확인 객체 생성
	pageContext.setAttribute("page", "Page 영역입니다.");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL 영역 확인</title>
</head>
<body>
	<h1>EL 영역 확인하기</h1>
	<hr />
	
	<h3>page 영역 : ${pageScope.page}</h3>
	<h3>request 영역 : ${requestScope.req}</h3>
	<h3>session 영역 : ${sessionScope.ses}</h3>
	<h3>application 영역 : ${applicationScope.app }</h3>
	
	<hr />
	
	<h1>scope 선언 없이 단순 호출하기</h1>
	
	<p>
		만약 scope(ex: requestScope) 선언 없이 단순히 객체를 호출한다면, <br />	
		page &gt; request &gt; session &gt; application 순으로 <br />
		값을 자동으로 찾아간다. <br />
		** param은 제외!
		
	
	</p>
	
	<h3> 오늘 점심 메뉴 : ${ lunchMenu }</h3> <!-- 단순 호출 가능 -->
	<span style="color:red">
		** 객체의 이름이 겹치면 직접 scope를 명시해야 한다.
	</span>
	
	
</body>
</html>