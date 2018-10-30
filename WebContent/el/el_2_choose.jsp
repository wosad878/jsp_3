<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setAttribute("level", 3);
	//1 출입제한
	//2 열람제한
	//3 수정제한
	//4 삭제제한
	//5 모든권한
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:choose>
	<c:when test="${level == 1}">
			<h3>출입제한</h3>
		</c:when>
		<c:when test="${level == 2}">
			<h3>열람제한</h3>
		</c:when>
		<c:when test="${level == 3}">
			<h3>수정제한</h3>
		</c:when>
		<c:when test="${level == 4}">
			<h3>삭제제한</h3>
		</c:when>
		<c:when test="${level == 5}">
			<h3>모든권한</h3>
		</c:when>
		<c:otherwise>
			<h3>출입제한</h3>
		</c:otherwise>
	</c:choose>
</body>
</html>