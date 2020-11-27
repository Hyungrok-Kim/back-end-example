<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.kh.jsp.boardComment.model.vo.*, 
                 com.kh.jsp.thumb.model.vo.*"%>
<%
	Thumbnail t = (Thumbnail)request.getAttribute("thumbnail");
	ArrayList<Attachment> fileList
	   = (ArrayList<Attachment>)request.getAttribute("fileList");
	
	Attachment titleImg = fileList.get(0);
	Attachment detailImg1 = fileList.get(1);
	Attachment detailImg2 = fileList.get(2);
	Attachment detailImg3 = fileList.get(3);
	
	ArrayList<BoardComment> clist
	   = (ArrayList<BoardComment>)request.getAttribute("clist");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사진 상세보기</title>
<style>
	.outer {
		width:1000px;
		height:auto;
		background:black;
		color:white;
		margin-left:auto;
		margin-right:auto;
		margin-top:50px;
	}
	.detail td{
		text-align:center;
		width:1000px;
		border:1px solid white;
	}
	#titleImgArea {
		width:500px;
		height:300px;
		margin-left:auto;
		margin-right:auto;
	}
	#contentArea {
		height:30px;
	}
	.detailImgArea {
		width:250px;
		height:210px;
		margin-left:auto;
		margin-right:auto;
	}
	#titleImg {
		width:500px;
		height:300px;
	}
	.detailImg {
		width:250px;
		height:180px;
	}
</style>
</head>
<body>
	<%@ include file="../common/header.jsp" %>
	<div class="outer">
		<table class="detail" align="center">
			<tr>
				<td width="50px">제목</td>
				<td colspan="5"><label><%= t.getBtitle() %></label></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td><label><%= t.getBwriter() %></label></td>
				<td>조회수</td>
				<td><label><%= t.getBcount() %></label></td>
				<td>작성일</td>
				<td><label><%= t.getBdate() %></label></td>
			</tr>
			<tr>
				<td>대표사진</td>
				<td colspan="4">
					<div id="titleImgArea" align="center">
						<img id="titleImg" src="<%= request.getContextPath() %>/resources/thumbnailUploadFiles/<%= titleImg.getChangename() %>">
					</div>
				</td>
				<td>
					<a href="<%= request.getContextPath() %>/resources/thumbnailUploadFiles/<%= titleImg.getChangename() %>" download="<%= titleImg.getOriginname() %>"><button type="button">다운로드</button></a>
				</td>
			</tr>
			<tr>
				<td>사진메모</td>
				<td colspan="6">
					<p id="contentArea"><%= t.getBcontent() %></p>
				</td>
			</tr>
		</table>
		<table class="detail">
			<tr>
				<td>
					<div class="detailImgArea">
					<% if (detailImg1.getChangename() != null) { %>
						<img id="detailImg1" class="detailImg" src="<%= request.getContextPath() %>/resources/thumbnailUploadFiles/<%= detailImg1.getChangename()%>">
						<a href="<%= request.getContextPath() %>/resources/thumbnailUploadFiles/<%= detailImg1.getChangename()%>" download="<%= detailImg1.getOriginname()%>"><button type="button">다운로드</button></a>
					<% } else { %>
						<img id="detailImg1" class="detailImg" src="<%= request.getContextPath() %>/resources/images/no-image.png">
					<% } %>
					</div>
				</td>
				<td>
					<div class="detailImgArea">
					<% if (detailImg2.getChangename() != null) { %>
						<img id="detailImg2" class="detailImg" src="<%= request.getContextPath() %>/resources/thumbnailUploadFiles/<%= detailImg2.getChangename()%>">
						<a href="<%= request.getContextPath() %>/resources/thumbnailUploadFiles/<%= detailImg2.getChangename()%>" download="<%= detailImg2.getOriginname()%>">
							<button type="button">다운로드</button>
						</a>
					<% } else { %>
						<img id="detailImg2" class="detailImg" src="<%= request.getContextPath() %>/resources/images/no-image.png">
					<% } %>
					</div>
				</td>
				<td>
					<div class="detailImgArea">
					<% if (detailImg3.getChangename() != null) { %>
						<img id="detailImg3" class="detailImg" src="<%= request.getContextPath() %>/resources/thumbnailUploadFiles/<%= detailImg3.getChangename()%>">
						<a href="<%= request.getContextPath() %>/resources/thumbnailUploadFiles/<%= detailImg3.getChangename()%>" download="<%= detailImg3.getOriginname()%>"><button type="button">다운로드</button></a>
					<% } else { %>
						<img id="detailImg3" class="detailImg" src="<%= request.getContextPath() %>/resources/images/no-image.png">
					<% } %>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<button onclick="location.href='<%= request.getContextPath() %>/selectList.tn'">메뉴로 돌아가기</button>
					<% if(m != null && m.getUserId().equals(t.getUserId())){ %>
					<button onclick="location.href='<%= request.getContextPath() %>/updateView.tn?bno='+<%=t.getBno()%>">수정하기</button>
					<button onclick="location.href='<%= request.getContextPath() %>/delete.tn?bno='+<%=t.getBno()%>">삭제하기</button>
					<% } %>
				</td>
			</tr>
		</table>
		<div class="replyArea">
			<div class="replyWriteArea">
				<form action="/myWeb/insertComment.co" method="post">
					<input type="hidden" name="writer" value="<%=m.getUserId()%>"/>
					<input type="hidden" name="bno" value="<%=t.getBno() %>" />
					<input type="hidden" name="refcno" value="0" />
					<input type="hidden" name="clevel" value="1" />
					
					<table align="center">
						<tr>
							<td>댓글 작성</td>
							<td><textArea rows="3" cols="80" id="replyContent" name="replyContent"></textArea></td>
							<td><button type="submit" id="addReply">댓글 등록</button></td>
						</tr>
					</table>
				</form>
			</div>
			<div id="replySelectArea">
			<!-- 게시글의 댓글들을 보여주는 부분  -->
			<% if (clist != null) { %>
				<% for(BoardComment bco : clist) { %>
				
				<table id="replySelectTable"
	      	 style="margin-left : <%= (bco.getClevel()-1) * 15 %>px;
	      	 		width : <%= 800 - ((bco.getClevel()-1) * 15)%>px;"
	      	 class="replyList<%=bco.getClevel()%>">
		  		<tr>
		  			<td rowspan="2"> </td>
					<td><b><%= bco.getCwriter() %></b></td>
					<td><%= bco.getCdate() %></td>
					<td align="center">
 					<%if(m.getUserId().equals(bco.getUserId())) { %>
						<input type="hidden" name="cno" value="<%=bco.getCno()%>"/>
							  
						<button type="button" class="updateBtn" 
							onclick="updateReply(this);">수정하기</button>
							
						<button type="button" class="updateConfirm"
							onclick="updateConfirm(this);"
							style="display:none;" >수정완료</button> &nbsp;&nbsp;
							
						<button type="button" class="deleteBtn"
							onclick="deleteReply(this);">삭제하기</button>
							
					<% } else if( bco.getClevel() < 3) { %>
						<input type="hidden" name="writer" value="<%=m.getUserId()%>"/>
						<input type="hidden" name="refcno" value="<%= bco.getCno() %>" />
						<input type="hidden" name="clevel" value="<%=bco.getClevel() %>" />
						<button type="button" class="insertBtn" 
							 onclick="reComment(this);">댓글 달기</button>&nbsp;&nbsp;
							 
						<button type="button" class="insertConfirm"
							onclick="reConfirm(this);"
							style="display:none;" >댓글 추가 완료</button> 
							
					<% } else {%>
						<span> 마지막 레벨입니다.</span>
					<% } %>
					</td>
				</tr>
				<tr class="comment replyList<%=bco.getClevel()%>">
					<td colspan="3" style="background : transparent;">
					<textarea class="reply-content" cols="105" rows="3"
					 readonly="readonly"><%= bco.getCcontent() %></textarea>
					</td>
				</tr>
			</table>
			
			<% } } else { %>
			<p>현재 등록된 댓글이 없습니다.<br>
			   첫 댓글의 주인공이 되어 보세요!</p>
			<% } %>
			</div>
		</div>
	</div>
	<script>
	function updateReply(obj) {
		// 현재 위치와 가장 근접한 textarea 접근하기
		$(obj).parent().parent().next().find('textarea')
		.removeAttr('readonly');
		
		// 수정 완료 버튼을 화면 보이게 하기
		$(obj).siblings('.updateConfirm').css('display','inline');
		
		// 수정하기 버튼 숨기기
		$(obj).css('display', 'none');
	}
	
	function updateConfirm(obj) {
		// 댓글의 내용 가져오기
		var content
		  = $(obj).parent().parent().next().find('textarea').val();
		
		// 댓글의 번호 가져오기
		var cno = $(obj).siblings('input').val();
		
		// 게시글 번호 가져오기
		var bno = '<%=t.getBno()%>';
		
		location.href="/myWeb/updateComment.co?"
				 +"cno="+cno+"&bno="+bno+"&content="+content;
	}
	
	function deleteReply(obj) {
		// 댓글의 번호 가져오기
		var cno = $(obj).siblings('input').val();
		
		// 게시글 번호 가져오기
		var bno = '<%=t.getBno()%>';
		
		location.href="/myWeb/deleteComment.co"
		+"?cno="+cno+"&bno="+bno;
	}
	
	function reComment(obj){
		// 추가 완료 버튼을 화면 보이게 하기
		$(obj).siblings('.insertConfirm').css('display','inline');
		
		// 클릭한 버튼 숨기기
		$(obj).css('display', 'none');
		
		// 내용 입력 공간 만들기
		var htmlForm = 
			'<tr class="comment"><td></td>'
				+'<td colspan="3" style="background : transparent;">'
					+ '<textarea class="reply-content" style="background : ivory;" cols="105" rows="3"></textarea>'
				+ '</td>'
			+ '</tr>';
		
		$(obj).parents('table').append(htmlForm);
		
	}
	
	function reConfirm(obj) {
		// 댓글의 내용 가져오기
		
		// 참조할 댓글의 번호 가져오기
		var refcno = $(obj).siblings('input[name="refcno"]').val();
		var level = Number($(obj).siblings('input[name="clevel"]').val()) + 1;
		
		// console.log(refcno + " : " + level);
		
		// 게시글 번호 가져오기
		var bno = '<%=t.getBno()%>';
		
		var parent = $(obj).parent();
		var grandparent = parent.parent();
		var siblingsTR = grandparent.siblings().last();
		
		var content = siblingsTR.find('textarea').val();
		
		// console.log(parent.html());
		// console.log(grandparent.html());
		// console.log(siblingsTR.html());
		
		// console.log(content);

		// writer, replyContent
		// bno, refcno, clevel
		
		location.href='/myWeb/insertComment.co'
		           + '?writer=<%= m.getUserId() %>' 
		           + '&replyContent=' + content
		           + '&bno=' + bno
		           + '&refcno=' + refcno
		           + '&clevel=' + level;
	}
	</script>
	<%@ include file="../common/footer.jsp" %>
</body>
</html>
