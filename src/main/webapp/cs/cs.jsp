<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import="bean.MemberDAO" %>
<%@ page import="bean.MemberDTO" %>
<%@ page import="bean.SmemberDAO" %>
<%@ page import="bean.SmemberDTO" %>
<%@ page import="bean.CsDTO" %>
<%@ page import="bean.CsDAO" %>
<%@ page import="bean.Comment_CsDTO" %>
<%@ page import="bean.Comment_CsDAO" %>
<%@ page import="java.util.List" %>
<%@ include file = "../include/header.jsp" %>

<title>고객센터</title>
<h2>고객센터 [문의사항]</h2>
	<div class="section7">
		<a href="notice.jsp" class="sideicon2"><p>공지사항</p></a>
		<a href="cs.jsp" class="sideicon2"><p>문의사항</p></a>
	</div>


<%
	int pageSize = 10;
	String pageNum = request.getParameter("pageNum");
	String my = request.getParameter("my");
	
	
	 
	if(pageNum == null){		
		pageNum="1";	
	}	
	
	int currentPage = Integer.parseInt(pageNum); 
	int start = (currentPage-1) * pageSize + 1;  
	int end = currentPage * pageSize;
	CsDAO dao = new CsDAO();
		
	int count = 0;
	List<CsDTO> list = null;
	
	if(sid !=null){
	if(sid.equals("admin"))	
		count = dao.getCount(); // 전체글 수 
    if(count > 0){
	    list = dao.getAllList( start , end );
	}else{
		count = dao.getMyCount(sid); // 나의 작성글수 
		if(count > 0){
			list = dao.getMyList(sid, start , end ); 
		}	
	}
}
	
%>
<section class="section1">
    <table border="1" width="800px">

	<%if(count == 0){%>
		<tr>
			<th colspan="6">저장된 글이 없습니다. <br/>본인의 글만 볼수 있습니다.</th>
		</tr>	
	<%}else{%>
	<tr>
		<th>글번호</th><th>작성자</th><th>제목</th><th>작성일</th><th>조회</th><th>답변진행사항</th>
	</tr>
	<%
	
    	for(CsDTO dto : list){%>
	<tr>
		<td><%=dto.getNum()%></td>
		<td><%=dto.getWriter()%></td>
		<td><a href="csContent.jsp?num=<%=dto.getNum()%>&pageNum=<%=pageNum%>"><%=dto.getSubject()%></a></td> 	
		<td><%=dto.getRegdt()%></td>
		<td><%=dto.getReadcount()%></td>
		<%
		Comment_CsDAO cdao = new Comment_CsDAO();
		int ccount = 0;
		ccount = cdao.getCommentCount(dto.getNum());
		if(ccount == 0){%>
			<td colspan="6">답변진행중</td>
	    <%}else{%>
			<td colspan="6">답변완료</td>
		<%	}%>
	</tr>		
<%	}
}%>
</table>
</section>

<section class="section2">
<%
	if(count > 0){
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int startPage = (currentPage / 10) * 10 + 1;
		int pageBlock = 10;
		int endPage = startPage + pageBlock-1;
		if(endPage > pageCount){
			endPage = pageCount;
		}	
		if(startPage > 10){%>
			<a href="list.jsp?pageNum=<%=startPage-10%>">[이전]</a>
		<%}
		for(int i = startPage ; i <= endPage ; i++){
		%>	<a href="list.jsp?pageNum=<%=i%>">[<%=i%>]</a> 	
	  <%}
		if(endPage < pageCount){%>
		<a href="list.jsp?pageNum=<%=startPage + 10%>">[다음]</a>
	<%}	
	}
%>
</section> 

<section class="section2">
<%
	if(sid != null){
%>		<input type="button"  value="글쓰기" onclick="window.location='csWrite.jsp' "/>
<%  } %>
</section> 

<%@ include file = "/include/footer.jsp" %>

    