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
			<h1>ID : ${member.id}</h1>
			<h1>Name :${member.name}</h1>
			<h1>Email :${member.email}</h1>
			<img src ="../upload/${member.fname}">
		</div>
		<div>
			<a href="./memberUpdate.do">update</a>
			<a href="./memberDelete.do">delete</a>
		</div>
	</div>

<c:import url="../../../temp/footer.jsp"/>
</body>
</html>