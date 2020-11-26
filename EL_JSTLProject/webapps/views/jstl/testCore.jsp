<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>테스트 core 라이브러리</h1>
	<h3>c:set - 변수 선언</h3>
	<c:set var="num1" value="100"/>
	<c:set var="num2" value="200"/>
	<c:set var="result" value="${num1 + num2 }" scope="session"/>
	
	<p>
		num1 : ${num1}<br>
		num2 : ${num2}<br>
		result : ${result}
	</p>
	
	<hr />
	
	<c:set var="colorArr">
		red, orange, yellow, palegreen, blue, navy, violet 
	</c:set>
	
	<p>
		문자열 확인 : ${ colorArr }
	</p>
	
	<h3>c:remove - 생성한 객체 삭제하기</h3>
	<c:remove var="result" scope="session" />
	
	<p>
		result 재확인 : ${result}
	</p>
	
	<hr />
	
	<h3>c:out - 화면 출력 태그</h3>
	일반 출력 : <c:out value="<h2>일반 출력</h2>" /> <br />
	태그로 내용 출력 : <c:out value="<h2>태그로 출력</h2>" escapeXml="false"/> <br />
	전달받은 값 출력 : <c:out value="${param.data }" default="받은 값 없음"/>
	
	
	<hr />
	
	<h2>JStL - 조건문 </h2>
	<h3>c:if - 조건문</h3>
	<p>if 하나밖에 없음</p>
	<c:if test="${num1 > num2 }">
		<p>num1 이  더 큽니다.</p>
	</c:if>
	<c:if test="${ num1 < num2 }">
		<p>num2 가 더 큽니다.</p>
	</c:if>
	
	<h3>c:choose - 자바의 switch/if문과 비슷 </h3>
	<c:set var ="memberType" value="A" />
	<c:choose>
		<c:when test="${ memberType eq 'A' }">
			고객님 환영합니다.
		</c:when>
		<c:when test="${memberType eq 'B' }">
			오셨습니까 ?
		</c:when>
		<c:otherwise>
			일해라 도비!
		</c:otherwise>
	</c:choose>
	
	<hr />
	
	<h3>c:forEach - 자바의 반복문</h3>
	
	<h4>일반 반복문</h4>
	<c:forEach var="i" begin="1" end="10" step="2">
		반복확인 : ${ i } <br />
	</c:forEach>
	
	<% for(int i = 1; i < 11; i++) {%>
		반복확인 <%= i %> <br />
	<% } %>
	<!-- 문자열을 ',' 기준으로 나누어 자동 반복한다. -->
	<h4>문자열을 활용한 반복문</h4>
	<c:forEach var="color" items="${colorArr}">
		<p style="background : ${ color }"> 반복 내용 입니다.</p>
	</c:forEach>
	
	<br />
	
	<h3>배열과 ArrayList</h3>
	<%
		java.util.ArrayList<String> movies = new java.util.ArrayList<>();
	
		movies.add("13일의 금요일");
		movies.add("나홀로 집에");
		movies.add("에일리언");
		movies.add("해리포터시리즈");
		movies.add("반지의제왕시리즈");
		movies.add("집으로");
		movies.add("사운드오브뮤직");
		
	%>
	<ul>
		<c:forEach items="<%= movies %>" var="title" varStatus="status">
			<li><!--   0부터 반복                                              1부터 반복-->
				번호 : ${ status.index } / 순번 : ${ status.count } / 제목 : ${title }
			</li>
		</c:forEach>
	</ul>
	
	<hr />
	
	<h3>c:url - 다른 jsp나 서블릿 주소를 저장하는 태그</h3>
	<!-- 
	<a href="views/common/errorPage.jsp">이동하기</a> -->
	
	<c:url var="testURL" value="test.do">
		<c:param name="no" value="100"/>
	</c:url>
	<!-- no라는 이름의 value 100을 testURL로 데리고 감  -->
	<a href="${ testURL }">이동하기</a>
	
	<hr />
	
	<h3>c:import : jsp-include의 역할</h3>
	<%-- <%@ include file="..." --%>
	<c:import url="import.jsp">
		<c:param name="test" value="check" />
	
	</c:import>
	
	<hr />
	
	<h3>c:catch - 자바의 try-catch</h3>
	<c:set var="num3" value="null" />
	<c:set var="num3" value="null" />
	<c:catch var="e">
		<!-- try 문 -->
		num3 / num4 : ${num3/ num4 }
	</c:catch>
	<c:if test="${ ! empty e }"> <!-- e!=null -->
		에러 메시지 : ${e.message }
	</c:if>
	
	<h3>c:redirect - response.sendRedirect() 와 동일</h3>
	<c:set var="check" value="1"/>
	
	<c:if test="${check==1 }">
		<c:redirect url="../../index.html"/>
	</c:if>
	
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
</body>
</html>