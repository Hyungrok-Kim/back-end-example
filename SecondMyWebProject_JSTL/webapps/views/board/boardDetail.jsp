<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="com.kh.jsp.board.model.vo.*, 
                                  com.kh.jsp.boardComment.model.vo.*, java.util.*"%>

<%
	Board b = (Board) request.getAttribute("board");
ArrayList<BoardComment> clist = (ArrayList<BoardComment>) request.getAttribute("clist");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세보기</title>
<style>
.outer {
	width: 800px;
	height: 500px;
	background: black;
	color: white;
	margin-left: auto;
	margin-right: auto;
	margin-top: 50px;
}

td {
	border: 1px solid black;
	background: black;
	color: white;
}

.tableArea {
	border: 1px solid black;
	background: white;
	color: black;
	width: 800px;
	height: 350px;
	margin-left: auto;
	margin-right: auto;
}

#content {
	height: 230px;
}

.replyArea {
	width: 800px;
	color: white;
	background: black;
	margin-left: auto;
	margin-right: auto;
	padding-bottom: 5px;
}

.replyArea textArea {
	border-radius: 10px;
	resize: none;
}

a:link {
	color: yellow;
}

a:active {
	color: aqua;
}

table[class*="replyList"] * {
	color: black;
}

.replyList1 td {
	background: lavenderblush;
}

.replyList2 td {
	background: honeydew;
}

.replyList3 td {
	background: aliceblue;
}
</style>
</head>
<body>

	<%@ include file="../common/header.jsp"%>

	<div class="outer">
		<br>
		<h2 align="center">게시글 상세보기</h2>
		<div class="tableArea">
			<table align="center" width="800px">
				<tr>
					<td>제목</td>
					<td colspan="5"><span><%=b.getBtitle()%></span></td>
				</tr>
				<tr>
					<td>작성자</td>
					<td><span><%=b.getBwriter()%></span></td>
					<td>작성일</td>
					<td><span><%=b.getBdate()%></span></td>
					<td>조회수</td>
					<td><span><%=b.getBcount()%></span></td>
				</tr>
				<%
					if (b.getBoardfile() != null && b.getBoardfile().length() > 0) {
				%>
				<tr>
					<td>첨부파일</td>
					<td colspan="5"><a
						href="/myWeb/resources/boardUploadFiles/<%=b.getBoardfile()%>"
						download> <%=b.getBoardfile()%>
					</a></td>
				</tr>
				<%
					}
				%>
				<tr>
					<td colspan="6">내용</td>
				</tr>
				<tr>
					<td colspan="6">
						<p id="content"><%=b.getBcontent()%>
					</td>
				</tr>
			</table>
			<br>
		</div>
		<div align="center">
			<button
				onclick="location.href='<%=request.getContextPath()%>/selectList.bo'">메뉴로
				돌아가기</button>
			<%
				if (m != null && m.getUserName().equals(b.getBwriter())) {
			%>
			<button
				onclick="location.href='<%=request.getContextPath()%>/bUpView.bo?bno='+<%=b.getBno()%>">수정하기</button>
			<%
				}
			%>
		</div>

		<div class="replyArea">
			<div class="replyWriteArea">
				<form action="<%=request.getContextPath()%>/insertComment.co"
					method="post">
					<input type="hidden" name="writer" value="<%=m.getUserId()%>">
					<input type="hidden" name="bno" value="<%=b.getBno()%>" /> <input
						type="hidden" name="refcno" value="0" /> <input type="hidden"
						name="clevel" value="1" />

					<table align="center">
						<tr>
							<td>댓글 작성</td>
							<td><textarea name="replyContent" id="replyContent"
									cols="80" rows="3"></textarea></td>
							<td>
								<button type="submit" id="addReply">댓글 등록</button>
							</td>

						</tr>
					</table>




				</form>
			</div>
			<div class="replySelectArea">
				<!-- 댓글 목록 구현 영역 -->
				<%
					if (clist.size() == 0) {
				%>
				<span>댓글을 남겨주세요</span>
				<%
					} else {
				for (BoardComment bco : clist) {
				%>

				<table id="replySelectTable"
					style="margin-left : <%=(bco.getClevel() - 1) * 15%>px;
	      	 		width : <%=800 - ((bco.getClevel() - 1) * 15)%>px;"
					class="replyList<%=bco.getClevel()%>">
					<tr>
						<td rowspan="2"></td>
						<td><b><%=bco.getCwriter()%></b></td>
						<td><%=bco.getCdate()%></td>
						<td align="center">
							<%
								if (m.getUserId().equals(bco.getUserId())) {
							%> <input
							type="hidden" name="cno" value="<%=bco.getCno()%>" />

							<button type="button" class="updateBtn"
								onclick="updateReply(this);">수정하기</button>

							<button type="button" class="updateConfirm"
								onclick="updateConfirm(this);" style="display: none;">수정완료</button>
							&nbsp;&nbsp;

							<button type="button" class="deleteBtn"
								onclick="deleteReply(this);">삭제하기</button> <%
 	} else if (bco.getClevel() < 3) {
 %>
							<input type="hidden" name="writer" value="<%=m.getUserId()%>" />
							<input type="hidden" name="refcno" value="<%=bco.getCno()%>" />
							<input type="hidden" name="clevel" value="<%=bco.getClevel()%>" />
							<button type="button" class="insertBtn"
								onclick="reComment(this);">댓글 달기</button>&nbsp;&nbsp;

							<button type="button" class="insertConfirm"
								onclick="reConfirm(this);" style="display: none;">댓글
								추가 완료</button> <%
 	} else {
 %> <span> 마지막 레벨입니다.</span> <%
 	}
 %>
						</td>
					</tr>
					<tr class="comment replyList<%=bco.getClevel()%>">
						<td colspan="3" style="background: transparent;"><textarea
								class="reply-content" cols="105" rows="3" readonly="readonly"><%=bco.getCcontent()%></textarea>
						</td>
					</tr>
				</table>


				<%
					}
				}
				%>
			</div>

		</div>
		<script>
		
			function reComment(obj) {
				// 추가 완료 버튼
				$(obj).siblings('.insertConfirm').css('display', 'inline');
				
				// 현재 클릭한 버튼 숨기기
				$(obj).css('display', 'none');
				
				// 대댓글 입력공간 만들기
				var htmlForm = 
					'<tr class="comment"><td></td>'
						+'<td colspan="3" style="background : transparent;">'
							+ '<textarea class="reply-content" style="background : ivory;" cols="105" rows="3"></textarea>'
						+ '</td>'
					+ '</tr>';
				
				$(obj).parents('table').append(htmlForm);
			}
		
			function reConfirm(obj){
				// 참조할 댓글 번호 가져오기
				var refcno = $(obj).siblings('input[name=refcno]').val();
				var level = $(obj).siblings('input[name=clevel]').val();
				
				level = Number(level) + 1;
				
				// 게시글 번호 가져오기
				var bno = '<%=b.getBno()%>';
				
				var content = $(obj).parent().parent().siblings()
				              .last().find('textarea').val();
				
				location.href = '<%=request.getContextPath()%>/insertComment.co'
						+ '?writer=<%=m.getUserId()%>'
						+ '&replyContent=' + content
						+ '&bno=' + bno	
						+ '&refcno=' + refcno
						+ '&clevel=' + level;
				
			}
			
			function updateReply(obj) {
				// 현재 버튼의 위치와 가장 가까운 textarea 접근하기 
				$(obj).parent().parent().next().find('textarea').removeAttr('readonly');
				
				// 수정 완료 버튼 보이게 하기
				$(obj).siblings('.updateConfirm').css('display','inline');
				
				// 현재 클릭한 수정 버튼 숨기기 
				$(obj).css('display', 'none');
			}
			
			function updateConfirm(obj) {
				// 수정을 마친 댓글 내용 가져오기 
				var content = $(obj).parent().parent().next().find('textarea').val();
			
				// 댓글 번호 가져오기
				var cno = $(obj).siblings('input').val();
			
				location.href = "<%=request.getContextPath()%>/updateComment.co"
							  + "?bno=<%=b.getBno()%>"
							  + "&cno=" + cno
							  + "&content=" + content;
			}
			
			function deleteReply(obj){
				// 댓글 번호 가져오기 
				var cno = $(obj).siblings('input').val();
				
				// 게시글 번호 가져오기 
				var bno = '<%=b.getBno()%>';
				
				// console.log("삭제 댓글 번호 : " + cno + " / " + bno);
				
				location.href= "<%=request.getContextPath()%>/deleteComment.co"
							 + "?cno=" + cno
							 + "&bno=" + bno;
				
			}
		</script>
		<br /> <br /> <br /> <br /> <br /> <br /> <br /> <br /> <br />
		<br />


	</div>

	<%@ include file="../common/footer.jsp"%>

</body>
</html>