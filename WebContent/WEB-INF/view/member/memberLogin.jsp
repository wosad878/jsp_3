<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="../../../temp/bootStrap.jsp"/>
<title>Insert title here</title>
</head>
<body>
<c:import url="../../../temp/header.jsp"/>
<div class="container-fluid">
	<div class="row">
		<h3>${message}</h3>
	</div>
		<div class="row">
			<form action="./memberLogin.do" method="post">
				<div class="form-group">
					<label for="id"></label> <input type="text" class="form-control"
						id="id" placeholder="아이디 입력" name="id">
				</div>
				<div class="form-group">
					<label for="pw"></label> <input type="password"
						class="form-control" id="pw" placeholder="비밀번호 입력"
						name="pw">
				</div>
				<label class="radio-inline"> <input type="radio"
					name="kind" value="S">학생
				</label> <label class="radio-inline"> <input type="radio"
					name="kind" value="T">교직원
				</label>
				<div class="form-group">
					<button type="submit" class="btn btn-primary">로그인</button>
				</div>
			</form>
			<hr>
			<h5>
				<a href="./memberJoin.do">회원가입</a>
			</h5>
		</div>
	</div>

<c:import url="../../../temp/footer.jsp"/>
</body>
</html>