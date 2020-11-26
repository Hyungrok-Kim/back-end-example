<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL 연습 페이지 </title>
</head>
<body>
	<h1>EL (Expression Language)</h1>
	<h3>
		&lt;%= %&gt;, request.getAttribute("000")으로 실행했던 자바 코드들을 <br />
		더욱 사용이 간편하게 만드는 기술로 JSP.2.0 버전부터 추가되었다.
	</h3>
	
	<hr />
	
	<form action="testELResult.jsp" method="post">
	
		<h1>형록's 설문조사 사이트</h1>
		<h3>
			이름 : <input type="text" name="userName" />
		</h3>
		
		<h3>
			나이 : <input type="number" name="age" />
		</h3>
		<h3>
			성별 : <input type="radio" name="gender" value="M" /> 남성 &nbsp;
				 <input type="radio" name="gender" value="F" /> 여성
		</h3>
		<h3>
			좋아하는 색상 : <select name="color">
							<option value="violet">보라색</option>
							<option value="red">빨간색</option>
							<option value="grey">회색</option>
						</select>
		</h3>
		
		<button type="submit">제출하기</button>
		&nbsp;
		<button type="reset">작성 취소</button>
		
		
		
		
	</form>
</body>
</html>