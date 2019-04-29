<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.project.share.member.model.vo.Member"%>
<%
	Member loginUser = (Member) session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/views/common/commonLink.jsp" %>
</head>
<body>
	<% if(loginUser == null || !loginUser.getUserId().equals("admin")) {
		request.setAttribute("msg", "잘못된 경로로 접근하셨습니다!");
		request.getRequestDispatcher("/views/common/errorPage.jsp")
			.forward(request, response);
	} else { %>
	<%@ include file="/views/common/sideRentalMenubar.jsp" %>
	<%@ include file="/views/common/header.jsp" %>
	<% } %>
</body>
</html>