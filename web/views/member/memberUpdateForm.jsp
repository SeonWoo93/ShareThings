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
</head>
<body>
	<h1 align="center"><%= loginUser.getUserName() %>님의 회원 정보 수정</h1>
	<button onclick="deleteMember()">탈퇴하기</button>
	<form action="<%= request.getContextPath() %>/updateMember" method="post">
		<table align="center">
			<tr>
				<td><label>아이디</label></td>
				<td><input type="text" name="userId" 
							value="<%= loginUser.getUserId() %>" readonly>
				</td>
			</tr>
			<tr>
				<td><label>비밀번호</label></td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td><label>비밀번호확인</label></td>
				<td><input type="password" name="password2"></td>
			</tr>
			<tr>
				<td><label>이름</label></td>
				<td><input type="text" name="userName" 
						value="<%= loginUser.getUserName()%>">
				</td>
			</tr>
			<tr>
				<td><label>성별</label></td>
				<td>
					<%
						if(loginUser.getGender().equals("M")) {
					%>
						<input type="radio" name="gender" id="male" value="M" checked>
						<label for="male">남자</label>
						<input type="radio" name="gender" id="female" value="F">
						<label for="female">여자</label>
					<% } else { %>
						<input type="radio" name="gender" id="male" value="M">
						<label for="male">남자</label>
						<input type="radio" name="gender" id="female" value="F" checked>
						<label for="female">여자</label>
					<% } %>
				</td>
			</tr>
			<tr>
				<td>나이</td>
				<td>
					<input type="number" name="age"
							value="<%= loginUser.getAge() %>">
				</td>
			</tr>
			<tr>
				<td>이메일</td>
				<td>
					<input type="email" name="email"
							value="<%= loginUser.getEmail() %>">
				</td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td>
					<input type="tel" name="phone"
							value="<%= loginUser.getPhone() %>">
				</td>
			</tr>
			<tr>
				<td>주소</td>
				<td>
					<input type="text" name="address"
							value="<%= loginUser.getAddress() %>">
				</td>
			</tr>
			<tr>
				<td>취미</td>
				<td>
					<input type="checkbox" name="hobby" id="sports" value="운동">
					<label for="sports">운동</label>
					<input type="checkbox" name="hobby" id="climbing" value="등산">
					<label for="climbing">등산</label>
					<input type="checkbox" name="hobby" id="reading" value="독서">
					<label for="reading">독서</label>
					<input type="checkbox" name="hobby" id="game" value="게임">
					<label for="game">게임</label>
					<input type="checkbox" name="hobby" id="travel" value="여행">
					<label for="travel">여행</label>
				</td>
			</tr>
			<tr align="center">
				<td colspan="2">
					<input type="reset" value="메인으로"
						onclick="location.href='<%= request.getContextPath() %>/views/main/main.jsp'"> &nbsp;
					<input type="submit" value="변경하기">
				</td>
			</tr>
		</table>
	</form>
	<script>
		$(function() {
			$("input[name=hobby]").each(function() {
				var arr = '<%= loginUser.getHobby() %>'.split(",");
				for(var i = 0; i < arr.length; i++) {
					if($(this).val() == arr[i]) {
						$(this).attr("checked", true);
					}
				}
			});
		});
		
		function deleteMember() {
			var answer = window.confirm("정말로 탈퇴하시려구요?");
			
			if(answer) {
				alert("정말로 탈퇴를 누르다니... 실망이에요~");
				location.href = "<%= request.getContextPath() %>/deleteMember";
			} else {
				alert("잘 선택하셨어요~");
			}
		}
	</script>
</body>
</html>