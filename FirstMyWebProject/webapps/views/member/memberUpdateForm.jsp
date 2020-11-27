<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="<%=request.getContextPath()%>/resources/js/jquery-3.5.1.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<title>회원 정보 수정</title>
<style>
	.outer{
		width:650px;
		height:550px;
		background:black;
		color:white;
		margin-left:auto;
		margin-right:auto;
		margin-top:50px;
	}
	
	#idCheck, #ckZip, #goMain, #updateBtn, #deleteBtn {
		background:orangered;
		border-radius:5px;
		width:80px;
		height:25px;
		text-align:center;
	}
	
	#idCheck:hover, #ckZip:hover, #updateBtn:hover, #goMain:hover, , #deleteBtn:hover {
		cursor:pointer;
	}
	td {
		text-align:right;
	}
	#ckZip, #updateBtn {
		background:lightgreen;
		color:black;
	}
	#updateBtn, #goMain, #deleteBtn {
		display:inline-block;
	}
</style>
</head>
<body>
<%@ include file="../common/header.jsp" %>
	<div class="outer">
		<br>
		<h2 align="center">회원 정보 수정</h2>
		
		<form id="updateForm" action="/myWeb/mUpdate.me" method="post">
			
			<table align="center">
				<tr>
					<td width="200px">* 아이디 </td>
					<td style="color:white; text-align: left; padding-left: 20px;"><%= m.getUserId() %></td>
					<td width="200px"></td>
				</tr>
				<tr>
					<td>* 변경 비번</td>
					<td><input type="password" id="userPwd" name="userPwd" required="required"></td>
					<td></td>
				</tr>
				<tr>
					<td>* 변경 비번 확인</td>
					<td><input type="password" id="userPwd2" name="userPwd2"></td>
					<td><label id="pwdResult"></label></td>
				</tr>
				<tr>
					<td>* 이름</td>
					<td style="color:white; text-align: left; padding-left: 20px;"><%= m.getUserName() %></td>
					<td></td>
				</tr>
				<tr>
					<td>성별 </td>
					<td>
						<input type="radio" name="gender" value="M">남성 &nbsp;
						<input type="radio" name="gender" value="F">여성 &nbsp;
					</td>
					<td></td>
				</tr>
				<tr>
					<td>나이 </td>
					<td><input type="number" name="age" min="10" max="100" value="<%= m.getAge() %>"
					      style="width:140px;">&nbsp;세
					</td>
					<td></td>
				</tr>
				<tr>
					<td>연락처 </td>
					<td>
						<input type="text" maxlength="3" name="tel1" size="2">-
						<input type="text" maxlength="4" name="tel2" size="2">-
						<input type="text" maxlength="4" name="tel3" size="2">
					</td>
					<td></td>
				</tr>
				<tr>
					<td>이메일 </td>
					<td><input type="email" name="email" value="<%= m.getEmail() %>"></td>
					<td></td>
				</tr>
				<tr>
					<td>우편번호</td>
					<td><input type="text" id="zipCode" name="zipCode"></td>
					<td><div id="ckZip" onclick="addrSearch();">검색</div></td>
				</tr>
				<tr>
					<td>주소</td>
					<td><input type="text" id="address1" name="address1"></td>
					<td></td>
				</tr>
				<tr>
					<td>상세주소</td>
					<td><input type="text" id="address2" name="address2"></td>
					<td></td>
				</tr>
				<tr>
					<td>취미</td>
					<td style="text-align:left;">
						<input type="checkbox" name="hobby" value="등산" /> 등산
						<input type="checkbox" name="hobby" value="드라마" /> 드라마<br />
						<input type="checkbox" name="hobby" value="복습" /> 복습
						<input type="checkbox" name="hobby" value="블록쌓기" /> 블록쌓기<br />
						<input type="checkbox" name="hobby" value="분유먹기" /> 분유먹기
						<input type="checkbox" name="hobby" value="유튜브보기" /> 유튜브보기
					</td>
					<td></td>
				</tr>
			</table>
			<br>
			<div class="btns" align="center">
				<div id="goMain" onclick="goMain();">메인으로</div> &nbsp;
				<div id="updateBtn" onclick="updateMember();">수정하기</div> &nbsp;
				<div id="deleteBtn" onclick="deleteMember();">회원 탈퇴</div>
			</div>
			</form>
			<script>
				$(function(){
					$('input:radio').each(function(){
						if( $(this).val() == '<%=m.getGender()%>')
						  $(this).prop('checked', true);
						else 
						  $(this).prop('checked', false);
					});
					
					var phoneArr = '<%= m.getPhone() %>'.split('-');
				
					$('input[name*="tel"]').each(function(index){
						$(this).val(phoneArr[index]);
					});
					
					var addressArr = '<%= m.getAddress() %>'.split(', ');
					
					$('#zipCode').val(addressArr[0]);
					$('#address1').val(addressArr[1]);
					$('#address2').val(addressArr[2]);
					
					var hobbyArr = '<%= m.getHobby() %>'.split(', ');
					// console.log(hobbyArr);
					$('input:checkbox').each(function(){
						if($.inArray($(this).val(),hobbyArr) > -1)
							$(this).prop('checked', true);
					});
				});
				
				function updateMember() {
					$("#updateForm").submit();
				}
				
				function deleteMember() {
					location.href = "/myWeb/mDelete.me?mid=<%=m.getUserId()%>";
				}
				
				// 참조 API : http://postcode.map.daum.net/guide
				function addrSearch() {
			        new daum.Postcode({
			            oncomplete: function(data) {
			                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

			                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
			                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
			                var fullAddr = ''; // 최종 주소 변수
			                var extraAddr = ''; // 조합형 주소 변수

			                // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
			                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
			                    fullAddr = data.roadAddress;

			                } else { // 사용자가 지번 주소를 선택했을 경우(J)
			                    fullAddr = data.jibunAddress;
			                }

			                // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
			                if(data.userSelectedType === 'R'){
			                    //법정동명이 있을 경우 추가한다.
			                    if(data.bname !== ''){
			                        extraAddr += data.bname;
			                    }
			                    // 건물명이 있을 경우 추가한다.
			                    if(data.buildingName !== ''){
			                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
			                    }
			                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
			                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
			                }

			                // 우편번호와 주소 정보를 해당 필드에 넣는다.
			                $('#zipCode').val(data.zonecode); //5자리 새우편번호 사용
			                
			                $('#address1').val(fullAddr);

			                // 커서를 상세주소 필드로 이동한다.
			                $('#address2').focus();
			            }
			        }).open();
			    };
				
				function goMain(){
					location.href='/myWeb/index.jsp';
				};
				
			</script>
	</div>
	<%@ include file="../common/footer.jsp" %>
</body>
</html>