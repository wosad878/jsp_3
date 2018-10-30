<%@page import="java.util.List"%>
<%@page import="com.iu.file.FileDTO"%>
<%@page import="com.iu.file.FileDAO"%>
<%@page import="com.iu.board.BoardDTO"%>
<%@page import="com.iu.notice.NoticeDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%
	String board = (String)request.getAttribute("board");
	BoardDTO boardDTO = (BoardDTO)request.getAttribute("dto");
	/* List<FileDTO> ar = (List<FileDTO>)request.getAttribute("files"); */
%>     --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="../../../temp/bootStrap.jsp"></jsp:include>
</head>
<body>
<jsp:include page="../../../temp/header.jsp"></jsp:include>
	
	<div class="container-fluid">
		<div class="row">
			<h1>${requestScope.board} VIEW</h1>
		</div>
		<div class="row">
			<h1>TITLE : ${dto.title}</h1>
			<h1>WRITER : ${dto.writer}</h1>
			<h1>Contents : ${dto.contents}</h1>
			<c:forEach items="${files}" var="fileDTO">
				<h3><a href="../upload/${fileDTO.fname}">${fileDTO.oname} </a></h3>
			</c:forEach>
		</div>	
	</div>
	<div>
		<a href="./${requestScope.board}List.do">List</a>
		<a href="./${requestScope.board}Update.do?num=${dto.num}">Update</a>
		<a href="./${requestScope.board}Delete.do?num=${dto.num}">Delete</a>
	</div>
	
<jsp:include page="../../../temp/footer.jsp"></jsp:include>
</body>
</html>







