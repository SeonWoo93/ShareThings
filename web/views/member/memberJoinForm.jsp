<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<h2 align="center">회원 가입 정보입력</h2>
		<br>
		<form action="<%= request.getContextPath() %>/joinMember" method="post">
			<input type="text" placeholder="아이디" name="userId" id="userId"><br>
			<input type="password" placeholder="패스워드"
						name="password" id="password"><br>
			<input type="password" placeholder="패스워드확인"
						name="password2" id="password2"><br>
			<input type="text" placeholder="이름" name="userName" id="userName"><br>
			<input type="email" placeholder="이메일" name="email" id="email"><br>
			<input type="tel" placeholder="휴대폰" name="phone" id="phone"><br>
			<input type="text" placeholder="주소" name="address" id="address"><br>
			
			<label>성별 : </label>
			<input type="radio" name="gender" id="male" value="M">
			<label for="male">남</label>
			<input type="radio" name="gender" id="female" value="F">
			<label for="female">여</label>
			
			<br>
			
			<input type="text" placeholder="나이" name="age" id="age"><br>
			
			<label>취미 : </label>
			<input type="checkbox" name="hobby" id="hobby0" value="운동">
			<label for="hobby0">운동</label>
			<input type="checkbox" name="hobby" id="hobby1" value="등산">
			<label for="hobby1">등산</label>
			<input type="checkbox" name="hobby" id="hobby2" value="독서">
			<label for="hobby2">독서</label>
			<input type="checkbox" name="hobby" id="hobby3" value="게임">
			<label for="hobby3">게임</label>
			<input type="checkbox" name="hobby" id="hobby4" value="여행">
			<label for="hobby4">여행</label>
			
			<br><br>
			
			<input type="submit" value="가입" onclick="return check();"> &nbsp; &nbsp;
			<input type="reset" value="취소"">
		</form>
	</div>
	<script>
		function check() {
			if($("#password").val() != $("#password2").val()) {
				$("#password2").focus();
				alert("비밀번호가 틀립니다.");
				return false;
			} else {
				return true;
			}
		}
		$(function () {
			$("input:reset").click(function() {
				location = "<%= request.getContextPath()%>/index.jsp";
			});
		});
	</script>
</body>
</html>