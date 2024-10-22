<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.jsp.notice.model.vo.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
	.outer{
		width:800px;
		height:500px;
		background:black;
		color:white;
		margin-left:auto;
		margin-right:auto;
		margin-top:50px;
	}
	table {
		border:1px solid white;
	}

	.tableArea {
		width:450px;
		height:350px;
		margin-left:auto;
		margin-right:auto;
	}
</style>
<title>공지 사항 수정</title>
</head>
<body>
	<c:set var="m" value="${sessionScope.member }" />
	<c:import url="../common/header.jsp" />
	<c:if test="${!empty m and m.userId eq 'admin' }">
	<div class="outer">
		<br>
		<h2 align="center">공지 사항 수정</h2>
		<div class="tableArea">
			<form id="updateForm" method="post">
				<table>
					<tr>
						<td>제목 </td>
						<td colspan="3"> 
							<input type="text" size="50" name="title" 
							       value="${n.ntitle.replace('\','&#34;')}">
							<input type="hidden" name="nno" value="${n.nno}">
						</td>
					</tr>
					<tr>
						<td>작성자 </td>
						<td>
							<input type="text" value="${n.nwriter}" name="writer" readonly>
						</td>
						<td>작성일</td>
						<td><input type="date" name="date" value="${n.ndate }"></td>
					</tr>
					<tr>
						<td>내용 </td>
					</tr>
					<tr>
						<td colspan="4">
							<textarea name="content" cols="60" rows="15" style="resize:none;">${n.ncontent }</textarea>
						</td>
					</tr>
				</table>
				<br>
				<div align="center">
					<button onclick="complete();">작성완료</button>
					<button onclick="deleteNotice();">삭제하기</button>
				</div>
				<script>
					function complete(){
						$("#updateForm").attr("action","${pageContext.request.contextPath}/nUpdate.no");
						
					}
					
					function deleteNotice(){
						// delete 는 자바스크립트 예약어 이므로 deleteNotice 로 ...!
						$("#updateForm").attr("action","${pageContext.request.contextPath}/nDelete.no");
					}
				
				</script>
			</form>
			
		</div>
	</div>
	</c:if> <c:if test="${!empty m and m.userId != 'admin' }">
		request.setAttribute("msg", "관계자 외에 접근이 불가능한 페이지입니다.");
		request.getRequestDispatcher("view/common/errorPage.jsp").forward(request, response);
	 </c:if>
	<c:import url="../common/footer.jsp" />
</body>
</html>