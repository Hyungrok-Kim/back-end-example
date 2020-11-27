<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.jsp.board.model.vo.*, java.util.*"%>
    
<%
	ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	int listCount = pi.getListCount();
	int currentPage = pi.getCurrentPage();
	int maxPage = pi.getMaxPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
%>
  
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
	<%@ include file= "../common/header.jsp" %>
	
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
				
				<% for(Board b : list){ %>
				<tr>
					<input type="hidden" value="<%= b.getBno() %>" />
					<td><%= b.getBno() %></td>
					<td><%= b.getBtitle() %></td>
					<td><%= b.getBwriter() %></td>
					<td><%= b.getBdate() %></td>
					<td><%= b.getBcount() %></td>
					<% if(b.getBoardfile() != null){ %>
					<td> @ </td>
					<% } else {%>
					<td> X </td>
					<%} %>
				</tr>
				
				
				<%} %>
			
			</table>
		</div>
		<%-- 페이지 처리 코드 넣기 --%>
		<div class="pagingArea" align="center">
		<br />
		<button onclick="location.href='<%= request.getContextPath() %>/selectList.bo?currentPage=1'"><<</button>
			<%  if(currentPage <= 1){  %>
			<button disabled><</button>
			<%  }else{ %>
			<button onclick="location.href='<%= request.getContextPath() %>/selectList.bo?currentPage=<%=currentPage - 1 %>'"><</button>
			<%  } %>
			
			<% for(int p = startPage; p <= endPage; p++){
					if(p == currentPage){	
			%>
				<button disabled><%= p %></button>
			<%      }else{ %>
				<button onclick="location.href='<%= request.getContextPath() %>/selectList.bo?currentPage=<%= p %>'"><%= p %></button>
			<%      } %>
			<% } %>
				
			<%  if(currentPage >= maxPage){  %>
			<button disabled>></button>
			<%  }else{ %>
			<button onclick="location.href='<%= request.getContextPath() %>/selectList.bo?currentPage=<%=currentPage + 1 %>'">></button>
			<%  } %>
			<button onclick="location.href='<%= request.getContextPath() %>/selectList.bo?currentPage=<%= maxPage %>'">>></button>
		
		</button>
		
		</div>	
		
		<div class="btnArea" align="center">
			<% if(m != null) {%> <!--  header에서  member를 import하고 있음. -->
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
			 			location.href = "<%= request.getContextPath() %>/selectOne.bo?bno=" + bno;
			 		});
			 	});
			 </script>
			 
			<%} %>
		</div>
	</div>
	
	<%@ include file= "../common/footer.jsp" %>
</body>
</html>