<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>포맷관련태그</title>
</head>
<body>
	<h1>날짜, 숫자 관련 포맷 태그</h1>
	
	<hr>
	<h3>fmt:formatDate 태그 : 날짜와 시간에 포맷 적용하는 태그</h3>
	value 속성에는 java.util.Date 객체를 사용해야 함
	<fmt:formatDate value="<%=new java.util.Date() %>"/>
	<c:set var="current" value="<%=new java.util.Date() %>" />
	
	<ul>
		<li>오늘 날짜 : <fmt:formatDate value="${current}"/></li>
		<li>현재 시간 : <fmt:formatDate value="${current}" type="time"/></li>
		<li>둘 다 : <fmt:formatDate value="${current}" type="both"/></li>
		<li>[Full] : <fmt:formatDate value="${current}" type="both" dateStyle="full" timeStyle="full"/></li>
		<li>[Long] : <fmt:formatDate value="${current}" type="both" dateStyle="long" timeStyle="long"/></li>
		<li>[Medium] : <fmt:formatDate value="${current}" type="both" dateStyle="medium" timeStyle="medium"/></li>
		<li>[Short] : <fmt:formatDate value="${current}" type="both" dateStyle="short" timeStyle="short"/></li>
		<li>내 패턴 : <fmt:formatDate value="${current}" type="both" pattern="yyyy-MM-dd(E) HH:mm:ss(a)"/></li>
	</ul>
	
	<hr>
	<h3>fmt:setLocale : 지역대 설정하는 태그</h3>
	
	fmt:timeZone : 시간대 설정하는 태그임. setTimeZone 사용해도 됨
	<ul>
		<li><fmt:setLocale value="ko_kr"/>
	한국 현재 시간 : <fmt:formatDate value="<%=new java.util.Date() %>" type="both"/>
		</li>
		<li><fmt:setLocale value="America/New_York"/>
		미국 현재 시간 : <fmt:formatDate value="<%=new java.util.Date() %>" type="both"/>
			
		</li>	
	</ul>
	
	<hr>
	<form action="testEncoding.jsp" method="post">
		전송값 : <input type="text" name="test" />
		<input type="submit" value="전송하기">
	</form>
	
	<hr>
	<h3>언어코드로 지역별 숫자표기 확인하기</h3>
	
	<c:set var="now" value="<%= new java.util.Date() %>"/>
	<h1>한국</h1>
	금액 표기 : <fmt:formatNumber value="1000000" type="currency"/> <br>
	일자 표기 : <fmt:formatDate value="${ now }" type="both"
	                            dateStyle="full" timeStyle="full"/>
	<h1>미국</h1>
	<fmt:setLocale value="en_us"/>
	<!-- 한 번 변경하면 이후 코드는 모두 같은 설정을 적용시킨다 -->
	금액 표기 : <fmt:formatNumber value="1000000" type="currency"/> <br>
	일자 표기 : <fmt:formatDate value="${ now }" type="both"
	                            dateStyle="full" timeStyle="full"/>
	
	<h1>일본</h1>
	<fmt:setLocale value="ja_jp"/>
	<!-- 한 번 변경하면 이후 코드는 모두 같은 설정을 적용시킨다 -->
	금액 표기 : <fmt:formatNumber value="1000000" type="currency"/> <br>
	일자 표기 : <fmt:formatDate value="${ now }" type="both"
	                            dateStyle="full" timeStyle="full"/>
	                            
	<h1>북한</h1>
	<fmt:setLocale value="ko_kp"/>
	<!-- 한 번 변경하면 이후 코드는 모두 같은 설정을 적용시킨다 -->
	금액 표기 : <fmt:formatNumber value="1000000" type="currency"/> <br>
	일자 표기 : <fmt:formatDate value="${ now }" type="both"
	                            dateStyle="full" timeStyle="full"/>
	
	<h3>fmt:formatNumber : 숫자 데이터 포맷</h3>
	일반 숫자 표현 : <fmt:formatNumber value="100200300" groupingUsed="false"/><br /> <!-- groupingUsed="false"는 일반 숫자 형식이 된다. -->
	천단위 숫자 표현 : <fmt:formatNumber value="100200300" groupingUsed="true"/><br /> 
	
	
	<h3>소숫점 데이터 포맷</h3>
	# : <fmt:formatNumber value="12.3" pattern="#.###" /> <br />
	0 : <fmt:formatNumber value="12.3" pattern="0.000" />
	
	<h3>퍼센트 패터 포맷</h3>
	0.20 : <fmt:formatNumber value="0.20" type="percent" /> <br />
	1.20 : <fmt:formatNumber value="1.20" type="percent" /> <br />
	
	<h3>통화기호 포맷</h3>
	12000  : <fmt:formatNumber value="12000" type="currency" currencySymbol="$" />
	
	
	 
</body>
</html>