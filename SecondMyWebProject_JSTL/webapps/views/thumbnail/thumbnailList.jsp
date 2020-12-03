<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.*, com.kh.jsp.thumb.model.vo.*" %>
    
<% 
	ArrayList<Thumbnail> list = (ArrayList<Thumbnail>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사진 게시판 목록</title>
<style>
	.outer{
		width : 1000px;
		height : auto;
		background : black;
		color : white;
		margin-left : auto;
		margin-right : auto;
		margin-top : 50px;
	}
	.thumbnailArea {
		width:760px;
		height:auto;
		margin-left:auto;
		margin-right:auto;
	}
	.thumb-list {
		width:220px;
		border:1px solid white;
		display:inline-block;
		margin:10px;
		align:center;
	}
	.thumb-list:hover {
		opacity:0.8;
		cursor:pointer;
	}
	.thumbnailArea .btn{
		width:100px;
		height:30px;
		margin-left:50%;
		margin-right:50%;
	}


	
</style>

</head>

<body>
	<%@ include file="../common/header.jsp" %>
	
	<div class="outer">
		<br />
		<h2 align="center">사진 게시판 </h2>
		<div class="thumbnailArea">
			<% for(Thumbnail thumb : list) { %>
				<div class="thumb-list" align="center">
					<div>
						<input type="hidden" name="bno" value="<%=thumb.getBno() %>" />
						<img src="<%=request.getContextPath()%>/resources/thumbnailUploadFiles/<%=thumb.getBoardfile() %>"
							 width="200px" height="150px"/>				
					</div>
					
					<p>
						No. <%= thumb.getBno() + " " + thumb.getBtitle() %>
						<br />
						조회수 : <%= thumb.getBcount() %>
					</p>
				</div>
			<% } %>
			<br /><br />
			<% if(m != null) {%>
				<button class="thumbnailArea btn" onclick="location.href='views/thumbnail/thumbnailInsertForm.jsp'">작성하기</button>
			
			<%} %>
		</div>
	</div>
	
	
	<script>
		$(function(){
			$('.thumb-list').click(function(){
				//var bno = $(this).children().children().eq(0).val();
				var bno = $(this).find('input').val();   // input태그가 하나밖에 없을 땐 이런식으로 찾아도 된다.
			//	console.log(bno);
				
				location.href = '<%= request.getContextPath() %>/selectOne.tn?bno=' + bno;
			});
		})
	</script>
	
	
	<%@ include file="../common/footer.jsp" %>
</body>
</html>