<%@page import="java.util.List"%>
<%@page import="com.iu.file.FileDTO"%>
<%@page import="com.iu.file.FileDAO"%>
<%@page import="com.iu.board.BoardDTO"%>
<%@page import="com.iu.notice.NoticeDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	BoardDTO boardDTO = (BoardDTO)request.getAttribute("dto");
	List<FileDTO> ar = (List<FileDTO>)request.getAttribute("files");
%>    
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
			<h1>TITLE : <%= boardDTO.getTitle() %> </h1>
			<h1>WRITER : <%= boardDTO.getWriter() %> </h1>
			<h1>Contents : <%= boardDTO.getContents() %></h1>
			<% for(FileDTO file : ar){ %>
				<h3><a href="../upload/<%= file.getFname()%>"><%=file.getOname() %> </a></h3>
			<%} %>
		</div>	
	</div>
	<div>
		<a href="./noticeList.do">List</a>
		<a href="./noticeUpdateForm.do">Update</a>
		<a href="./noticeDelete.do">Delete</a>
	</div>
	
<jsp:include page="../../../temp/footer.jsp"></jsp:include>
</body>
</html>







