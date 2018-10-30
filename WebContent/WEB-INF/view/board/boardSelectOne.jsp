<%@page import="java.util.List"%>
<%@page import="com.iu.file.FileDAO"%>
<%@page import="com.iu.file.FileDTO"%>
<%@page import="com.iu.board.BoardDTO"%>
<%@page import="com.iu.notice.NoticeDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");
	int num = Integer.parseInt(request.getParameter("num"));
	NoticeDAO noticeDAO = new NoticeDAO();
	BoardDTO boardDTO = noticeDAO.selectOne(num);
	FileDAO fileDAO = new FileDAO();
	FileDTO fileDTO = new FileDTO();
	fileDTO.setNum(num);
	fileDTO.setKind("N");
	List<FileDTO> ar = fileDAO.selectList(fileDTO);
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
			<table class="table" id="tableindex">
				<tr>
					<td>SUBJECT</td>
					<td>NAME</td>
					<td>DATE</td>
					<td>HIT</td>
				
				</tr>
				<tr>
					<td><%=boardDTO.getTitle()%></td>
					<td><%=boardDTO.getWriter()%></td>
					<td><%=boardDTO.getReg_date()%></td>
					<td><%=boardDTO.getHit()%></td>
				</tr>
			</table>
			<table class="table" id="tableindex">
				<tr>
					<td><%=boardDTO.getContents()%></td>
				</tr>
				<tr>
					<% for(FileDTO file : ar) {%>
						<td><a href="../upload/<%=file.getFname()%>"><%=file.getOname() %></a></td>
					<%} %>
				</tr>
			</table>
		</div>
		<a href="./noticeList.jsp"
			class="btn btn-primary" id="btn">List</a> 
		<a href="./noticeUpdateForm.jsp?num=<%=boardDTO.getNum()%>"
			class="btn btn-primary" id="btn2">Update</a> 
		<a href="./noticeDeleteProcess.jsp?num=<%=boardDTO.getNum()%>"
			class="btn btn-primary" id="btn3">Delete</a>
	</div>


	<jsp:include page="../../../temp/footer.jsp"></jsp:include>
</body>
</html>