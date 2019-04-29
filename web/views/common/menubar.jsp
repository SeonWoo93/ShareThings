<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.project.share.member.model.vo.Member"%>
<%
	Member loginUser = (Member) session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<title>Insert title here</title>
<style>
	div > button {
		float:right;
	}
</style>
</head>
<body>
	<h1 align="center">메인화면</h1>
	<h3 align="right"><%= loginUser.getUserName() %>님 환영합니다.</h3>
	<div>
		<button onclick="logout();">로그아웃</button>
		<!-- 관리자아닌경우 내정보 수정/ 관리자일 경우 관리자 메뉴로-->
		<% if(!loginUser.getUserId().equals("admin")) {%>
		<button onclick="location.href='<%= request.getContextPath() %>/views/member/memberUpdateForm.jsp'">내 정보 수정</button>
		<% } else { %>
		<button onclick="location.href='<%= request.getContextPath() %>/views/admin/adminMain.jsp'">관리자 메뉴로</button>
		<% } %>
	</div>
	<script>
		function logout() {
			var check = window.confirm('로그아웃 하시겠습니까?');
			
			if(check) {
				location.href = '<%= request.getContextPath() %>/logout';
			}
		}
	</script>
</body>
</html>