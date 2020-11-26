<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문자열 관련 JSTL 태그</title>
</head>
<body>
	<h1>문자열 관련 JSTL 태그</h1>
	<p>
		문자열 관련 fn 태그는 el 안에서 사용한다.
	</p>
	
	<c:set  var="str" value="I am your father." />
	
	<p>
		str : ${str}
	</p>
	
	<p>
		모두 대문자로 변경 : ${ fn:toUpperCase(str) } <br>
		모두 소문자로 변경 : ${ fn:toLowerCase(str) } <br />
		father의 위치 : ${fn:indexOf(str,"father") } <br />
		father -> mother : ${ fn:replace(str,"father","mother") } <br />
		문자열의 갯수 : ${ fn:length(str) } 개 <br />
		특정 문자 분리하기(am) : ${ fn:substring(str, 2, 4)}
	</p>
	
	<hr />
	
	<h3>split과 join</h3>
	<p>
		split : 주어진 문자열을 특정 구분자 기준으로 쪼개는 함수 <br />
		join : 여러 배열을 특정 구분자로 합치는 함수
	</p>
	<c:set var="animals" value="꼬꼬,초롱이,초복,중복,말복,양념,후라이" />
	
	<c:set var="splitAnimals" value="${ fn:split(animals,',') }" />
	
	<c:set var="joinAnimals" value="${ fn:join(splitAnimals, '-') }"></c:set>
	
	<p>
		원문 :  ${animals} <br />
		split 후 : ${splitAnimals[0] } <br />
		join 후 : ${joinAnimals }
		
	</p>
</body>
</html>