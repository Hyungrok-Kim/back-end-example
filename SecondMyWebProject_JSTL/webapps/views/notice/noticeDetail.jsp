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
	.tableArea > table {
		width: 650px;
		padding: 20px;
		color: black;
		border:1px solid white;
	}

	.tableArea {
		background:white;
		width:650px;
		height:350px;
		margin:auto;
	}
	span {
		font-size:21pt;
		background: black;
		color: white;
	}
</style>
<title>공지 사항 내용</title>
</head>
<body>
	<c:set var="m" value="${sessionScope.member }" />
	<c:set var="n" value="${notice }"/>
	<c:import url="../common/header.jsp" />
	<div class="outer">
		<br>
		<h2 align="center">공지 사항 내용</h2>
		<div class="tableArea">
				<table>
					<tr>
						<td>제목 : </td>
						<td colspan="3">${n.ntitle }</td>
					</tr>
					<tr>
						<td>작성자 :</td>
						<td>
							${n.nwriter}
						</td>
						<td>작성일 : </td>
						<td>${n.ndate }</td>
					</tr>
					<tr>
						<td>내용 </td>
					</tr>
					<tr>
						<td colspan="4"><br>
							<span>${n.ncontent.charAt(0)}</span>${n.ncontent.substring(1)}
						</td>
					</tr>
				</table>
				<br>
				<div align="center">
					
					<button onclick="location.href='selectList.no'">메뉴로 돌아가기</button>

					<c:if test="${ !empty m and m.userId eq admin }">
						<button onclick="location.href='nUpView.no?nno=${n.nno}'">수정하기</button>
					</c:if>
				</div>
		</div>
	</div>
	<c:import url="../common/footer.jsp" />
</body>
</html>