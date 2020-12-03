<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.jsp.board.model.vo.*, java.util.*"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
<style>
	.outer{
		width:900px;
		height:600px;
		background:black;
		color:white;
		margin-left:auto;
		margin-right:auto;
		margin-top:50px;
	}
	table {
		padding : 20px;
		border:1px solid white;
		text-align:center;
	}
	.tableArea {
		width:750px;
		height:350px;
		margin-left:auto;
		margin-right:auto;
	}
	
</style>
</head>
<body>
	<c:set var="m" value="${sessionScope.member}" />
	<c:import url= "../common/header.jsp" />
	
	<div class="outer">
		<br />
		<h2 align="center">게시판 목록</h2>
		<div class="tableArea">
			<table align="center" id="listArea">
				<tr>
					<th width="100px">글 번호</th>
					<th width="300px">제 목</th>
					<th width="100px">작성자</th>
					<th width="150px">작성일</th>
					<th width="100px">조회수</th>
					<th width="100px">첨부파일</th>
				</tr>
				<c:forEach var="b" items="${list}">
				<tr>
					<input type="hidden" value="${b.bno}" />
					<td>${b.bno }</td>
					<td>${b.btitle }</td>
					<td>${b.bwriter }</td>
					<td>${b.bdate }</td>
					<td>${b.bcount }</td>
					<c:if test="${!empty b.boardfile }">
					<td> @ </td>
					</c:if><c:if test="${empty b.boardfile}">
					<td> X </td>
					</c:if>
				</tr>
				</c:forEach>
			
			</table>
		</div>
		<%-- 페이지 처리 코드 넣기 --%>
		<div class="pagingArea" align="center">
		<br />
		<c:url var="selectList" value="selectList.bo"/>
		
		<button onclick="location.href='${ selectList }?currentPage=1'"><<</button>
			<c:if test="${ pi.currentPage le 1 }">
			
			<button disabled><</button>
			</c:if><c:if test="${pi.currentPage gt 1 }">
			<button onclick="location.href='${selectList }?currentPage=${pi.currentPage-1 }'"><</button>
			</c:if>
			
			<c:forEach var="p" begin="${ pi.startPage }" end="${ pi.endPage }">
				<c:if test="${ p eq pi.currentPage }">
					<button disabled>${p }</button>
				</c:if><c:if test="${ p ne pi.currentPage }">
					<button onclick="location.href='${selectList}?currentPage=${p }'">${p }</button>
				
				</c:if>
				
			</c:forEach>
				
			<c:if test="${pi.currentPage ge pi.maxPage }">
			<button disabled>></button>
			</c:if><c:if test="${pi.currenPage lt pi.maxPage }">
			<button onclick="location.href='${selectList }?currentPage=${pi.currentPage+1}'">></button>
			</c:if>
			<button onclick="location.href='${selectList }?currentPage=${pi.maxPage }'">>></button>
		
		
		</div>	
		
		<div class="btnArea" align="center">
			<c:if test="${!empty member}">
			 <br />
			 <button onclick="location.href='views/board/boardInsertForm.jsp'">작성하기</button>
			 
			 <script>
			 	$(function(){
			 		$('#listArea td').mouseenter(function(){
			 			$(this).parent().css({"background":"grey" , "cursor":"pointer"});
			 		}).mouseout(function(){
			 			$(this).parent().css({"background":"black"});
			 		}).click(function(){
			 			var bno = $(this).parent().find('input').val();
			 			location.href = "${selectList}?bno=" + bno;
			 		});
			 	});
			 </script>
			 
			</c:if>
		</div>
	</div>
	
	<c:import url= "/views/common/footer.jsp" />
</body>
</html>