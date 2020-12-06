<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>웹 API 활용하기</title>
</head>
<body>
	<h1>HTML5 Web API</h1>
	<p>HTML 표현 기술이 HTML5로 진보되면서 <br>
	   사용할 수 있는 부가적인 기능이 확장되었다.<br>
	 따라서 개발자가 직접 만들지 않고도 여러 기술들을 구현할 수<br>
	 있게 되었는데 이 때 사용하는 웹 기술을 웹 API라고 한다.
	 <br><br>
	 -- Web API(Application Programming Interface) 기술 분류 -- <br>
	 - Web Storage : 웹 브라우저 상에서 간단한 데이터(10MB)를 <br>
	                웹 내부 저장소(임시 인터넷 파일 공간)에 담고자 할 때 사용하는 기술 <br>
	 - Web IndexedDB : 웹 스토리지와 유사하나 대용량의 데이터를 <br> 
	                                     저장하는데 사용하는 기술 <br>
	 - Web Socket : 서버와 클라이언트 간 실시간 통신을 구현할 때 사용하는 기술<br>
	 - Web Geolocation : 웹 브라우저가 내장하고 있는 주소 정보를 활용하여 <br>
	 				    지도에 위도와 경도를 표시하는 기술<br>
	 - Web Worker : 웹 브라우저 화면(탭/페이지) 밖에서(백그라운드) <br> 
	                                별도의 스크립트를 동작시키는 기술 <br>
	 - Web RTC (Real-Time Communication): 실시간 화상 채팅, 음성 통신을 <br> 
	                              구현하는 기술 (https 보안 연결 필수) <br>
	 - Web GL (Graphics Library) : 웹 브라우저 상의 그래픽 엔진을 활용하여 2D, 3D<br>
	                애니메이션 등의 효과를 부여할 때 사용하는 기술<br>
	- 결제 API (iamport) : 결제 기능을 제공하는 오픈 API (단, PG사 2개 이상은 유료!)<br>
	- sweettracker (택배 배송조회 API) : 택배 배송지 조회 API (단, 실제 송장 번호 필요!)<br>
	- 기타 오픈 API 사이트들 :<br>
	- https://www.apistore.co.kr/api/apiList.do<br>
	- https://www.culture.go.kr/data/openapi/openapiView.do?id=115&category=A&gubun=A<br>
	- https://www.data.go.kr/tcs/dss/selectDataSetList.do<br>
	- 카카오 지도 API : https://apis.map.kakao.com/ (카카오 개발자 계정 및 개인 식별 키 필수!)<br>
	- 네아로(네이버 아이디로 로그인) : https://developers.naver.com/docs/login/devguide/<br>
	 </p>
	 
	 <h2>웹 스토리지 활용하기</h2>
	 <h3>
	 	<a href="views/storage/localStorage.html">
	 		1. 로컬 스토리지
	 	</a>
	 </h3>
	 <h3>
	 	<a href="views/storage/sessionStorage1.html">
	 		2. 세션 스토리지 - 1 -
	 	</a>
	 </h3>
	 <h3>
	 	<a href="views/storage/sessionStorage2.html">
	 		3. 세션 스토리지 - 2 -
	 	</a>
	 </h3>
	 
	 <h2>웹 소켓 API 활용하기</h2>
	 <h3>
	 	<a href="views/websocket/unicast.jsp">1. 1:1 통신(Unicast)</a>
	 </h3>
	 <h3>
	 	<a href="multiView.do">2. 1:M 통신(Multicast)</a>
	 </h3>
	 <h3>
	 	<a href="views/websocket/broadcast.jsp">3. 1:N 통신(Broadcast)</a>
	 </h3>
	 <h3>
	 	<a href="views/websocket/exercise.jsp">4. 응용하기</a>
	 </h3>
	 
	 <h2>결제 기능 & 사용자 인증 구현하기</h2>
	 <h3>
	 	<a href="views/iamport/pay.jsp">결제 기능 예시</a>
	 </h3>
	 <h3>
	 	<a href="views/iamport/testUserAuth.jsp">사용자 인증 기능 예시</a>
	 </h3>
    <br><br><br><br>
    <br><br><br><br>
    <br><br><br><br>
    <br><br><br><br>
</body>
</html>














