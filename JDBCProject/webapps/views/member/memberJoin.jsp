<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입 양식</title>
</head>
<body>
	<%@ include file="/views/common/header.jsp" %>
	
	<section style="background:lightgoldenrodyellow; padding: 30px 5px">
		<h1>회원 가입 양식</h1>

		<form action="/jdbc/memberInsert.do" method="post">
			<table>
				<tr>
					<td>아이디 : </td>
					<td><input type="text" name="userId" /></td>
					<td><button type="button" id="dupBtn" disabled>중복 확인</button></td>
				</tr>
				<tr>
					<td>비밀번호 :</td>
					<td><input type="password" name="userPwd" id="userPwd"/></td>
					<td></td>
				</tr>
				<tr>
					<td>비밀번호 확인 :</td>
					<td><input type="password" name="userPwd2" id="userPwd2" /></td>
					<td></td>
				</tr>
				<tr>
					<td>이름 :</td>
					<td><input type="text" name="userName" /></td>
					<td></td>
				</tr>
				<tr>
					<td>성별 :</td>
					<td>
						<input type="radio" name="gender" value="M" />남성
						<input type="radio" name="gender" value="F" />여성
					</td>
					<td></td>
				</tr>
				<tr>
					<td>나이 :</td>
					<td><input type="number" name="age" min="" max="" /></td>
					<td></td>
				</tr>
				<tr>
					<td>이메일 :</td>
					<td><input type="text" name="email" /></td>
					<td></td>
				</tr>
				<tr>
					<td>연락처 :</td>
					<td><input type="text" name="phone"/></td>
					<td></td>
				</tr>
				<tr>
					<td>주소 :</td>
					<td><input type="text" name="address"/></td>
					<td></td>
				</tr>
				<tr>
					<td> 취미 :</td>
					<td>
						<input type="checkbox" name="hobby" value="등산" />등산
						&nbsp; &nbsp;
						<input type="checkbox" name="hobby" value="드라마"/>드라마
						&nbsp; &nbsp;
						<input type="checkbox" name="hobby" value="복습" />복습
						
						<br />
						
						<input type="checkbox" name="hobby" value="블록쌓기" />블록쌓기
						&nbsp; &nbsp;
						<input type="checkbox" name="hobby" value="분유먹기" />분유먹기
						&nbsp; &nbsp;
						<input type="checkbox" name="hobby" value="유튜브보기" />유튜브보기
					</td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td colspan="2">
						<input type="submit" value="가입하기" />
						&nbsp; &nbsp;
						<input type="reset" value="작성 취소" />
					</td>
				</tr>
				
			</table>
		
		</form>

	</section>
	
		
	<%@ include file="/views/common/footer.jsp" %>
	
</body>
</html>