<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="bean.MemberDAO" %>
<title>emcheck</title>

<jsp:useBean id="dto" class="bean.MemberDTO" />
<jsp:setProperty name="dto" property="*" />

<%	
	MemberDAO dao = new MemberDAO();
	boolean result= dao.emailCheck(dto);
	String str="사용가능";
	if(result==true)
	{		
		str="사용불가능";
	%>
	<script>
		alert("사용중인 이메일입니다.");
		
	</script>
  <%}else{%>
	<script>
	 	alert("사용 가능합니다."); 
	 	
	</script>
<%}%>

<script>
		function returnClose(){
			opener.document.getElementById("emCheck").innerHTML='<font color=red><%=str%></font>';
			self.close();
		}
	
	</script>
	<input type="button" value="닫기" onclick="returnClose();"/>