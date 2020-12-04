<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jQuery와 AJAX</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<h1>jQuery와 AJAX</h1>

	<pre>
	-- 사용 방법 --
	jQuery.get() / post()
	== $.get() / $.post()
	get('요청할 URL', '전달할 값', '끝나고 실행할 콜백함수');
	
	$.ajax({
		url : '요청할 URL 주소', 
		type : 'get|post',
		data : '전달할 값', ex) 10
				// 만약 전달할 값이 여러 개라면 {name : '홍길동' , age : 10, hobby : '구슬치기'},
		async : true | flase, (비동기 | 동기), (동기방식을 선택하면 이 ajax가 끝날 때까지 다른 ajax를 못써! 라는 의미)
				// -- 기본은 true이며, false를 쓰는 경우도 종종 있으나 권장하지는 않음 
		dataType : '결과로 받을 값',  // 화면으로 받을 때 결과로 받아올 값
				// 'text/html', 'application/json' ...
		success : function(data){   // data는 성공했을 때 받아올 data
				// 성공했을 때 실행할 함수의 내용과, 결과(data)를 받아온다.
		}, error : function(error, code, msg){
				// 에러 발생 시 처리할 함수 내용 . 마치 try catch같은
		}, complete | done : function(){
				// 성공 /실패 여부와 관계 없이 무조건 실행하는 함수. 마치 finally와 같은 
		}
	});
	</pre>
	
	<h3> 1. 버튼 클릭 시 서버로 데이터 보내기 </h3>
	<input type="text" id="myName" />
	&nbsp;
	<button onclick="test1();">실행 확인</button>
	
	<script>
		
		function test1(){
			$.ajax({
				url : '/ajax/test1.do', 
				type : 'get',	//대소문자 안따짐
				data : {name : $('#myName').val()},
				success : function(){
					alert("성공!!");
				}
			});
		}
		
	</script>
	
	<h3> 2. 버튼 클릭 시 서버에서 보낸 값 확인하기 </h3>
	<input type="text" id="joinName" />
	&nbsp;
	<button id="getResult">ajax 통신 확인</button>
	
	<script>
	$('#getResult').on('click', function(){
		$.ajax({
			url : '/ajax/test2.do',
			type : 'get',
			data : {name : $('#joinName').val()},
			success : function(data){  // data대신 다른 이름을 써도 상관없음. 	매개변수이기 때문에
					alert(data);
			}, error : function(error){
				console.log(error);
			}
		});
	});
	</script>
	
	
	<h3> 3. 서버로 기본 데이터 전송, 결과로 문자열 받아오기</h3>
	<h4>이름과 나이 입력</h4>
	<p>
		이름 : <input type="text" id="userName" />
		<br />
		나이 : <input type="number" id="userAge" />
	</p>

	<button id="testBtn3">실행 확인</button>
	<p id="result3" style="background : lightgreen; height:auto;"></p>
		
	<script>
	$('#testBtn3').on('click', function(){
		$.ajax({
			url : '<%= request.getContextPath() %>/test3.do',
			type : 'get', 
			data : {
				name : $('#userName').val(),
				age : $('#userAge').val(),
			}, success : function(data){
				$('#result3').text(data);
			}, error : function(error, code, msg){
				$('#result3').text(error + "/" + code + "/" + msg);
			}
			
		});
	});

	</script>
	
	<h3> 4. 서버로 json(자바스크립트 객체 형태) 데이터를 전송하고 서버에서 결과 확인하기 </h3>
	<p>
		개인정보 입력창 <br />
		아이디 : <input type="text" id="userId" /><br />
		사번 : <input type="number" id="empNo" /><br />
		이름 : <input type="text" id="userNick"></input>
		연락처 : <input type="text" id="tel" />
		<button id="testBtn4">실행 확인</button>
		
	</p>
	
	<script>
		$('#testBtn4').on('click', function(){
			var obj = {
					userId : $('#userId').val(),
					empNo : $('#empNo').val(),
					userNick : $('#userNick').val(),
					tel : $('#tel').val()
			};
			
			console.log(obj);
			
			$.ajax({
				url : '/ajax/test4.do',
				type : 'get',
				data : obj,
				success : function(data){
					alert("서버 전송 성공!");
				}, error : function(){
					alert("전송 실패!");
				}
			});
		});
	</script>
	
	<h3> 5. 서버로 기본 값을 전송하고, 서버에서 자바 객체 받아오기 </h3>
	<h4>상품 번호를 보내고, 해당 번호와 일치하는 상품 꺼내오기</h4>
	
	<p style="background: chocolate; color:snow; padding : 15px; height:auto;">
		상품 번호 조회 : <input type="number" id="pno" /><br />
		<button id="testBtn5">실행 확인</button> <br />
		<hr />
		<span id="result5"></span>
	</p>
	
	<script>
	$('#testBtn5').on('click', function(){
		$.ajax({
			url : '/ajax/test5.do',
			type : 'get',
			data : {pno : $('#pno').val() },
			success : function(data){
				$('#result5').text(data);
				console.log(data);
			}, error : function(){
				
				$('#result5').text("에러 발생");
			}
		});
	});
	</script>
	
	<h3> 6. 서버로 여러 값을 전달하고, 결과를 리스트 형태로 받아오기</h3>
	<p>
		상품 번호 여러 개를 전송하고, 해당 번호에 맞게 리스트 찾아오기
	</p>
	상품 번호(,) : <input type="text" id="testPno" />
	<br />
	<button id="testBtn6">실행 확인</button>
	
	<table id="productTable" border="1">
		<thead>
			<tr>
				<th>번호</th>
				<th>상품명</th>
				<th>원산지</th>
				<th>상품 유형</th>
			</tr>
		</thead>
	</table>
	<script>
		$('#testBtn6').on('click', function(){
			$.ajax({
				url : '/ajax/test6.do',
				type : 'get', 		// 만약 type을 생략하면 기본은 get 방식
				data : {
					pnoList : $('#testPno').val()
				},
				success : function(data) {
					console.log(data);
					
						var $tbody = $('<tbody>'); // tbody 객체를 만들어 줌.
						
					for(var i = 0; i < data.list.length; i++){
						var product = data.list[i];
						
						var $tr = $('<tr>');
						
						var $pno = $('<td>').text(product.pno);
						var $pname = $('<td>').text(product.pname);
						var $origin = $('<td>').text(product.origin);
						var $type = $('<td>').text(product.type);
						
						$tr.append($pno);		// <tr>
						$tr.append($pname);		// 	  <td> 1 </td>
						$tr.append($origin);	// 	  <td> 2 </td>
						$tr.append($type);		//		...
												// </tr> 형식으로 만들어짐 알아서 닫기도 만들어짐.
						$tbody.append($tr);						
									
					}
					$('#productTable>tbody').remove();
						
					$('#productTable').append($tbody);
				}, error : function(){
					alert("에러 발생~!");
				}
			});
		});
	</script>
	
	<h3> 7. 서버에서 리스트를 가져와 화면의 select 요소로 구현하기</h3>
	
	<button id="testBtn7">실행 확인</button>
	&nbsp;
	<select name="" id="selectTest"></select>
	
	<script>
		$('#testBtn7').on('click', function(){
			$.ajax({
				url : '/ajax/test7.do',
				type : 'get',
				success : function(data){
					//console.log(data);
					
					var $select = $('#selectTest');
					
					$select.empty(); // 셀렉트 박스 초기화
					
					for(var i = 0; i < data.length; i++){
						$select.append("<option value='" + data[i].pno + "'>"
										+ data[i].pname + "</option>");
					}
					
					
				}, error : function(){
					alert("에러 발생!");
				}
			});
		});
	</script>
	
	<h3>8. Gson을 이용한 List 처리</h3>
	
	<button id="testBtn8">실행 확인</button>
	&nbsp;
	<select name="" id="selectGson"></select>
	
	<script>
		$('#testBtn8').on('click', function(){
			$.ajax({
				url : '/ajax/test8.do',
				type : 'get',
				success : function(data){
					//console.log(data);
					
					var $select = $('#selectGson');
					
					$select.empty(); // 셀렉트 박스 초기화
					
					for(var i = 0; i < data.length; i++){
						$select.append("<option value='" + data[i].pno + "'>"
										+ data[i].pname + "</option>");
					}
					
					
				}, error : function(){
					alert("에러 발생!");
				}
			});
		});
	</script>
	
	<h3>9. Gson으로 Map 객체 받아오기</h3>
	<button id="testBtn9">실행 확인</button>
	&nbsp;
	<select id="selectMap"></select>

	<script>
		$('#testBtn9').on('click', function(){
			$.ajax({
				url : '/ajax/test9.do',
				type : 'get',
				success : function(data){
					//console.log(data);
					
					$select = $('#selectMap');
					
					$select.empty();
					
					for(var name in data){
						var $option = $('<option>');
						
						$option.val(data[name].pno);
						$option.text(data[name].pname);
						
						$select.append($option);
					}
					
					
				}, error : function(){
					alert("에러 발생~~~!");
				}
			});
		});
	</script>

	<br />
	<br />
	<br />
	<a href="../index.jsp">메인으로 돌아가기</a>
	
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	
</body>
</html>