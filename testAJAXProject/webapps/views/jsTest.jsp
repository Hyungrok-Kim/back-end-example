<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자바 스크립트와 Ajax</title>
</head>
<body>
	<h1>자바스크립트와 Ajax</h1>
	
	<p>
		Ajax란, Asynchronous JavaScript And XML로써 <br>
		다른 페이지로 갱신하여 데이터를 전달하지 않고<br>
		현재 페이지에 데이터를 계속 추가해 나가는 방식<br>
		<br>
		별도의 페이지 새로고침이 일어나지 않기 때문에<br>
		사용자는 실시간 같은 느낌을 받으며, 서버에서도<br>
		갱신된 최신 정보를 사용자에게 즉각 전달할 수 있다.<br>
		<br>
		단, 페이지 내부에서 요청했던 정보들이 계속 누적되는 <br>
		방식으로 인해 이를 남용할 경우 페이지가 점점 무거워져 <br>
		사용자의 컴퓨터가 느려질 수 있다.(페이지 리소스 과부하 현상)<br>
		Call back hell 현상이라고도 한다.(콜백 지옥)
	</p>
	
	<h3>버튼 클릭 시 특정 값을 서버에 전달하기</h3>
	<button onclick="callbackFn();">통신 확인하기</button>
	
	
	<script>
		// 1. 서버 요청 객체 선언 
		var httpRequest;
		
		// 2. 서버 요청 객체 생성
		function getHttpRequest(){
			// 웹 브라우저로부터 서버 요청 객체 가져오기 
			
			// 브라우저가 IE 일 때
			if(window.ActiveXObject){
				try{
					// 버전이 9버전 이상일 때 (신버전)
					return new ActiveXObject("Msxml2.XMLHTTP");
				}catch(e1){
					// 버전이 9버전 미만일 때 (구버전)
					return new ActiveXObject("Microsoft.XMLHTTP");
				}
			}else if(window.XMLHttpRequest){
				// 그 외의 브라우저 모두 
				return new XMLHttpRequest();
			}else{
				// Ajax 기술을 지원하지 않는 브라우저 
				return null;
			}
		
			
		}
	
		// 3. ajax 통신 함수 구현
		function callbackFn() {
			// 3-1. 서버 전송 객체 할당 
			httpRequest = getHttpRequest();
			
			// 3-2. 요청 정보 설정
			httpRequest.open("GET", "/ajax/test1.do?name=홍길동", true);
		
			// 3-3. 요청 상태 변경 시 실행할 결과 함수 설정 (callback 함수)
			httpRequest.onreadystatechange = result1;
			
			// 3-4. 위에 설정한 통신 실행하기 
			httpRequest.send();
		}
		
		// 결과 함수 준비
		function result1(){} // 지금 당장 사용 안함
		
		
		
	</script>
	
	<h3>버튼 클릭 시 서버로부터 데이터 가져오기</h3>
	<button onclick="test2();">실행 확인</button>
	
	<script>
		function test2(){
			// 1. 서버와 통신 객체 선언 
			httpRequest = getHttpRequest();
			
			// 2. 요청 정보 설정 
			httpRequest.open("GET", "/ajax/test2.do?name=이순신", true)  // true는 비동기로 할 것이냐? -> true
		
			// 3. 콜백 함수 설정하기
			httpRequest.onreadystatechange = result2;
			
			// 4. 서버로 출발
			httpRequest.send(); // send안에 경로 달아줘도 됨
		}
		
		function result2(){
		//	alert(httpRequest.responseText);
			
			// 택배로 따지면
			// httpRequest.readyState == 0은 아직 주문도 안한 상태
			if(httpRequest.readyState == 1){
				//alert("open 함수는 호출되었지만, 아직 send는 안한 상태"); // 배송 보류 상태
			}else if(httpRequest.readyState == 2){
				//alert("send 함수가 실행되며 어떠한 경로로 연결할 지, 기본 정보만 전달한 형태"); // 어디로 보낼 지 운송장만 나온 상태
			}else if(httpRequest.readyState == 3){
				//alert("send 함수로 데이터가 일부 전송된 상태"); // 배송중
			}else if(httpRequest.readyState == 4){
				// alert("데이터 수신 완료!"); // 배송 완료  // 송신이 아닌 수신임 
				
				if(httpRequest.status == 200){ // 전송 OK (배송완료)	
					alert(httpRequest.responseText); 
				}else{
					alert("문제 발생!! : " + httpRequest.status);
				}
			}	
			
			
		}
	</script>
	
	
	
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	
	<a href="../index.jsp">처음으로 돌아가기</a>
	<br />
	<br />
	<br />
	<br />
</body>
</html>