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
			<!-- 로그인 전 -->
			<a href="./memberLogin.do">LOGIN</a>
			<a href="./memberJoin.do">JOIN</a>
			<!-- 로그인 했을 때 -->
			<a href="./memberLogout.do">LOGOUT</a>
			<a href="./memberMyPage.do">MyPage</a>
		</div>
	</div>

<c:import url="../../../temp/footer.jsp"/>
</body>
</html>