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
<title>회원 정보 수정 화면</title>
<script src="/jdbc/resources/js/jquery-3.5.1.min.js"></script>
</head>
<body>
	<%@ include file="/views/common/header.jsp" %>
	
	<section style="background : lightcyan; padding : 10px 5px;">
		
		<h1> <%= m.getUserName() %>님의 회원 정보 수정 화면</h1>
		
		<form action="/jdbc/memberUpdate.do" method="post" id="updateFrm">
			<table>
				<tr>
					<td> 아이디 :</td>
					<td> <%= m.getUserId() %></td>
					<td></td>
				</tr>
				<tr>
					<td>변경할 PW :</td>
					<td><input type="password" name="userPwd" id="userPwd" /></td>
					<td></td>
				</tr>
				<tr>
					<td>변경할 PW 확인 :</td>
					<td><input type="password" name="userPwd2" id="userPwd2" /></td>
					<td></td>
				</tr>
				
				<tr>
					<td colspan="3" id="pwdResult"></td>
				</tr>
				<tr>
					<td> 이름 :</td>
					<td> <%= m.getUserName() %></td>
					<td></td>
				</tr>
				<tr>
					<td> 성별 :</td>
					<td>
						 <input type="radio" name="gender" value="M" />남성
						 <input type="radio" name="gender" value="F" />여성
					</td>
					<td></td>
				</tr>
				
				<tr>
					<td> 나이 :</td>
					<td> <input type="number" name="age" min="5" max="80" value="<%= m.getAge() %>"/></td>
					<td></td>
				</tr>
				
				<tr>
					<td> 이메일 :</td>
					<td> <input type="text" name="email" value="<%= m.getEmail() %>"/ ></td>
					<td></td>
				</tr>
				
				<tr>
					<td> 연락처 :</td>
					<td> <input type="text" name="phone" value="<%= m.getPhone() %>" /></td>
					<td></td>
				</tr>
				<tr>
					<td> 주소 : </td>
					<td> <input type="text" name="address" value="<%= m.getAddress() %>" /></td>
					<td></td>
				</tr>
				<tr>
					<td> 취미 :</td>
					<td> 
						<input type="checkbox" name="hobby" value="등산" />등산
						&nbsp;&nbsp;
						<input type="checkbox" name="hobby" value="드라마" />드라마
						&nbsp;&nbsp;
						<input type="checkbox" name="hobby" value="복습" />복습
						<br />
						<input type="checkbox" name="hobby" value="블럭쌓기" />블럭쌓기
						&nbsp;&nbsp;
						<input type="checkbox" name="hobby" value="분유먹기" />분유먹기
						&nbsp;&nbsp;
						<input type="checkbox" name="hobby" value="유튜브보기" />유튜브보기
					</td>
					<td></td>
				</tr>
				
				<tr>
					<td></td>
					<td colspan="2">
						<input type="submit" value="수정하기" />
						<input type="button" value="탈퇴하기" onclick="memberDel();">
					</td>
					
				</tr>
			</table>	
		</form>
	</section>
	
	
	<%@ include file="/views/common/footer.jsp" %>
	<script>
		$(function(){
		// 성별 선택
			$('input:radio').each(function(){
				if($(this).val() == '<%= m.getGender() %>' ) {
					$(this).prop('checked', true);
				} else {
					$(this).prop('checked', false);
				}
			});
		
		
		// 취미 선택
			
			var hobbyArr = '<%= m.getHobby() %>'.split(', ');
	
			console.log(hobbyArr);
			
			$('input:checkbox').each(function(){
				if($.inArray($(this).val() , hobbyArr) >= 0){ // inArray는 $(this).val()이 hobbyArr에 몇번째에 속하는지 알려줌 숫자로, 없으면 -1을 반환
					$(this).prop('checked', true);
				}
			});
			
			// 비밀번호 유효성 체크 후 확인값과 일치할 때만 submit 처리하기 
			$('#updateFrm').submit(function(event){
				var pwd = $('#userPwd').val();
				var pwd2 = $('#userPwd2').val();
				
				// 비밀번호가 확인값과 일치하지 않는다면
				if(pwd != pwd2){
					$('#pwdResult').text("비밀번호와 확인 값이 일치하지 않습니다.")
								   .show().fadeOut(1000);
					console.log("왜 이것만 돼");
				}else if(pwd == ''){
					$('#pwdResult').text("비밀번호를 반드시 입력하세요.")
									.show().fadeOut(1000);
					console.log("뭐야 왜안돼 ");
				}else{
					return; // 정상 종료 및  submit 실행
				}
				
				event.preventDefault();  // submit 동작 취소(현재 수행하는 이벤트 제거 함수)
				
			});	
		});	
		// 회원 탈퇴 기능 함수 
		function memberDel(){
			location.href ='/jdbc/memberDelete.do';
		}
	</script>

</body>
</html>		