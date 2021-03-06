<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../include/header.jsp" %>
<% request.setCharacterEncoding("UTF-8"); %>
<!-- DAO 객채 생성 -->
<jsp:useBean id="dao" class="bean.MemberDAO"/>

	
<%	if (sid == null) { //로그인pro 수정 수정 후, ==으로 바꿀 것
%>		<script>
			location.href="../member/login.jsp";
		</script>  		
<%	} else {
		String email = dao.getEmail(sid);
		String profileimg = dao.getProfileImg(sid);
	
%>		<title>멤버 등록</title>

		<h1>멤버 등록</h1>

		<section class="section2">
			<form action="s-member_inputPro.jsp" method="post" enctype="multipart/form-data" class="form-box2">
				<div class="wrapper">
					<ul class="profile-info">	
						<li id="profile-img" >
<%						if(profileimg == null){
%>							<img src = "../img/profileimg_default.png">
<%						} else {
%>							<img src = "../uploadFile/profileFile/<%=profileimg%>">
<%						}
%>							
						</li>
						<li id="id">	
							<label>아이디</label>
								<span><%=sid%></span>
								<input type="hidden" name="id" value="<%=sid%>"/>
						</li>
						
						<li id=email>
							<label>이메일</label>
								<span><%=email%></span>
								<input type="hidden" name="email" value="<%=email%>">
						</li>
						
						<li id=phone>
							<label>전화번호</label>
								<input type="text" name="phone" placeholder="-없이 입력">
						</li>
						
						
						<li id=kakao>
							<label>카카오톡</label>
								<input type="text" name="kakao">
						</li>
					</ul>
					
					<ul class="skill-info">
						<li id="field">
							<label>분야</label>
								<select name="field">
									<option value="dev">개발</option>
									<option value="plan">기획</option>
									<option value="design">디자인</option>								
								</select>			
						</li>
						
						<li id="career">
							<label>경력</label>
								<select name="career">
									<option value="new">신입</option>
									<option value="1">1년차</option>
									<option value="2">2년차</option>
									<option value="3">3년차</option>
									<option value="4">4년차</option>
									<option value="5">5년차</option>
									<option value="6">6년차</option>
									<option value="7">7년차이상</option>
								</select>
						</li>
						
						<li id="lang">
							<label>보유 기술</label>								
								<label><input type="checkbox" name="lang" value="Python">Python</label>
								<label><input type="checkbox" name="lang" value="Django">Django</label>
								<label><input type="checkbox" name="lang" value="Java">Java</label>
								<label><input type="checkbox" name="lang" value="JSP">JSP</label>
								<label><input type="checkbox" name="lang" value="Spring">Spring</label>
								<label><input type="checkbox" name="lang" value="Html">Html</label>
								<label><input type="checkbox" name="lang" value="JavaScript">JavaScript</label>
								<label><input type="checkbox" name="lang" value="React">React</label>
								<label><input type="checkbox" name="lang" value="C">C</label>
								<label><input type="checkbox" name="lang" value="C++">C++</label>
								<label><input type="checkbox" name="lang" value="C#">C#</label>
								<label><input type="checkbox" name="lang" value="SQL">SQL</label>				
								<label><input type="checkbox" name="lang" value="R">R</label>								
						</li>
						
						 <li id="portfolio">
							<label>포트폴리오</label>
								<input type="file" name="portfolio" multiple="multiple">
						</li>
						
						<li id="pfdetail">
							<label>포트폴리오 설명</label>
								<textarea name="pfdetail">
								</textarea>
						</li>
					</ul>
					
					<ul class="condition-info">
						<li id="employtype">
							<label>활동 유형</label>
								<label><input type="radio" name="employtype" value="sfree">개인 프리랜서</label>
								<label><input type="radio" name="employtype" value="tfree">팀 프리랜서</label>
								<label><input type="radio" name="employtype" value="sbusiness">개인 사업자</label>
								<label><input type="radio" name="employtype" value="cbusiness">법인 사업자</label>
						</li>
						
						<li id="projecttype">
							<label>찾는 프로젝트 유형</label>
								<label><input type="radio" name="projecttype" value="side">사이드 프로젝트</label>
								<label><input type="radio" name="projecttype" value="main">메인 프로젝트</label>
								<label><input type="radio" name="projecttype" value="sidemain">둘 다</label>
						</li>            
								
						<li id="worktype">
							<label>가능한 업무 방식</label>
								<label><input type="radio" name="worktype" value="online">원격</label>
								<label><input type="radio" name="worktype" value="office">상주</label>
								<label><input type="radio" name="worktype" value="discuss">협의</label>
						</li>	
						
						<li id="location">
							<label>선호 업무 지역</label>
								<select name="location">
									<option value="all">전지역</option>
									<option value="seoul">서울</option>
									<option value="gyunggi">경기</option>
									<option value="incheon">인천</option>
									<option value="gangwon">강원</option>
									<option value="chungbuk">충북</option>
									<option value="chungnam">충남</option>
									<option value="daejeon">대전</option>
									<option value="sejong">세종</option>
									<option value="jeonbuk">전북</option>
									<option value="jeonnam">전남</option>
									<option value="gwangju">광주</option>
									<option value="gyungbuk">경북</option>
									<option value="gyungnam">경남</option>
									<option value="daegu">대구</option>
									<option value="ulsan">울산</option>
									<option value="busan">부산</option>
									<option value="jeju">제주</option>
								</select>
						</li>				
						
						<li id="period">
							<label>근무가능일자</label>
								<input type="date" name="period">
						</li>
						
						<li id="available">
							<label>구직활동여부</label>
								<select name="available">
									<option value="1">on</option>
									<option value="0">off</option>
								</select>
						</li>
						
						 <li id="pay">
							<label>희망 계약금</label>
								<input type="text" name="pay"  placeholder=", 없이 입력">만원
						</li>
						
						<li id="introduce">
							<label>소개</label>
								<textarea name="introduce" cols="60" rows="10">
								</textarea>
						</li>
					</ul>
			
					<hr>
					<div class="wrapper-submit">
						<input type="submit" value="등록하기" />
					</div>
				</div>
			</form>	
		</section>	
<%	}
%>

<%@ include file = "../include/footer.jsp" %>

