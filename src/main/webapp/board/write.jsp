<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "bean.Cookies" %>
<%@ include file = "../include/header.jsp" %>
<title>글작성</title>

<%
	if(sid==null && cid==null){
%>		<script>
			alert("로그인 후 이용 가능합니다.")
			window.location="/goworker/member/login.jsp"
		</script>
<%	}
								/* 글쓰기 화면 구현 */
	if(sid!=null || cid!=null)
	{
%>	<div style=" padding-right: 15px;  padding-left: 15px;  margin-right: auto;  margin-left: auto;" align="center" >	
		<form action="writePro.jsp" method="post" enctype="multipart/form-data" ">
			작성자 : <%= (sid==null) ? cid:sid %> 
				<input type="hidden" name="writer" value="<%=(sid==null)? cid:sid%>"/><br/>
			<select name="category"  >
				<option value="tab">	탭</option>
				<option value="forum">	자유게시판</option>
				<option value="qna">	묻고답하기</option>
				<option value="notice">	공지</option>
			</select>
				<input placeholder="제목" type="text" name = "subject" style=" width:1040px;" /><br/>
				<label>내용</label><br/>
				<textarea name="content" cols="160" rows="20"></textarea><br/>
				<input placeholder="첨부파일" type="file" name = "filename"/><br/>
				<input type="submit" value="등록" />	
				<input type="button" value="목록" onclick="window.location='board.jsp'"/>
		</form>
	</div>
<%	}else
	{
%>		<script>
			alert("로그인 후 이용하세요");
			window.location="/goworker/member/login.jsp";
		</script>
<%	}
%>
