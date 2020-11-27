<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
<style>
	/* 스타일 공사 중*/
	.outer {
		width:900px;
		height:500px;
		background:black;
		color:white;
		margin-left:auto;
		margin-right:auto;
		margin-top:50px;
	}
	table {
		padding: 15px;
		border:1px solid white;
	}

	.tableArea {
		width:500px;
		height:350px;
		margin-left:auto;
		margin-right:auto;
	}
</style>
</head>

<body>
	<%@ include file="../common/header.jsp" %>
	
	<% if(m != null) { %>
	<div class="outer">
	
		<br />
		
		<div class="tableArea">
		<h2 align="center">게시글 작성</h2>
		
			<form action="<%= request.getContextPath() %>/bInsert.bo"
				  method="post" enctype="multipart/form-data"> <!-- 폼으로 전송할 때 나는 파일(멀티미디어)도 같이 보내겠다 하면 인코딩을 해야 됨 -->	
				<table>					<!--  이렇게 보내야만 파일을 파일로 인식하고 영상, 사진 등등 멀티미디어 다 받아줌  -->
					<tr>
						<td>제목</td>
						<td colspan="3">
							<input type="text" name="title" size="40" />
						</td>	
					</tr>
					<tr>
						<td>작성자</td>
						<td colspan="3">
							<%= m.getUserName() %>
							<input type="hidden" name="userId" value="<%= m.getUserId() %>" />
						</td>
					</tr>
					<tr>
						<td>첨부파일</td>
						<td colspan="3">
							<input type="file" name="file" id="file" />
						</td>
					</tr>
					<tr>
						<td>내용</td>
						<td colspan="3">
							<textarea name="content" 
									  cols="50" 
									  rows="15"
									  style="resize:none;"></textarea>
						</td>
					</tr>
					
				</table>
				<br />
				<div align="center">
					<button type="submit">게시글 등록</button>
					<button type="reset">작성 취소</button>
				</div>
			</form>
		</div>
	</div>
	
	<%}else {
	
	request.setAttribute("error-msg", "회원만 접근 가능합니다!");
	request.getRequestDispatcher("../common/errorPage.jsp").forward(request, response);
	
	} %>
	<%@ include file="../common/footer.jsp" %>
</body>
</html>